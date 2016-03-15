package org.nazymko.thehomeland.parser.db.dao;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.nazymko.th.parser.autodao.tables.records.RuleRecord;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.nazymko.thehomeland.parser.rule.ParsingRule;
import org.nazymko.thehomeland.parser.rule.RuleFactory;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.nazymko.thehomeland.parser.utils.UrlSimplifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import sun.net.util.URLUtil;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.Rule.RULE;
import static utils.support.rule.RuleStatus.ACTIVE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RuleDao extends AbstractDao<String, ParsingRule> {

    @Resource
    RuleResolver resolver;
    @Resource
    UrlSimplifier simplifier;
    @Resource
    @Setter
    private SiteDao siteDao;

    @Override
    public Optional<ParsingRule> get(String site) {
        Optional<ParsingRule> result = getJdbcTemplate().query("" +
                "SELECT * FROM rule WHERE site=:site AND version = (SELECT  MAX(version) FROM rule WHERE site=:site)", new MapSqlParameterSource("site", site), new ResultSetExtractor<Optional<ParsingRule>>() {
            @Override
            public Optional<ParsingRule> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rsToJsonRule(rs);

                }
                return null;
            }
        });

        return result;
    }

    public Optional<ParsingRule> getJsonById(int key) {
        ParsingRule parsingRule = null;
        RuleRecord ruleRecord = getDslContext().selectFrom(RULE).where(RULE.ID.eq(key)).orderBy(RULE.VERSION.desc()).limit(1).fetchOne();
        if (isPresent(ruleRecord)) {
            parsingRule = RuleFactory.make(ruleRecord.getRule());
            parsingRule.setId(ruleRecord.getId());
            parsingRule.setVersion(ruleRecord.getVersion());
        }

        return Optional.ofNullable(parsingRule);
    }

    @Override
    public String save(ParsingRule rule) throws MalformedURLException, URISyntaxException {
        String siteUrl = simplifier.simplify(rule.getUrl());
        saveNewSiteOrIgnore(rule, siteUrl);

        RuleRecord ruleRecord = new RuleRecord();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serialized = gson.toJson(rule);
        Integer version = ruleMaxVersion(rule.getUrl());
        ruleRecord.setSite(siteUrl);
        ruleRecord.setRule(serialized);
        ruleRecord.setVersion(version + 1);
        ruleRecord.setStatus(ACTIVE);

        store(ruleRecord);

        resolver.refresh();
        return rule.getUrl();

    }

    private void saveNewSiteOrIgnore(JsonRule rule, String siteUrl) throws MalformedURLException, URISyntaxException {

        Integer idByUrl = siteDao.getIdByUrl(siteUrl);
        if (idByUrl < 0) {
            SiteRecord site = new SiteRecord();

            site.setUrl(siteUrl);
            site.setName(rule.getName());
            siteDao.save(site);
        }
    }

    private Integer ruleMaxVersion(String url) {
        Record1<Integer> currentMaxVersion = getDslContext().select(DSL.max(RULE.VERSION)).from(RULE).where(RULE.SITE.eq(url)).fetchOne();
        Object value = currentMaxVersion.getValue(0);
        return value == null ? 0 : (Integer) value;
    }


    public List<RuleRecord> getAllRules() {

        return getDslContext().selectFrom(RULE).where(RULE.STATUS.isNotNull()).fetch();
    }

    public List<ParsingRule> getLatestParsingRules() {
        List<RuleRecord> latest = getLatest();
        List<ParsingRule> ruleList = new ArrayList<>();
        for (RuleRecord ruleRecord : latest) {
            ruleList.add(RuleFactory.make(ruleRecord));

        }
        return ruleList;
    }

    public List<RuleRecord> getLatest() {
        Result<RuleRecord> fetch = getDslContext().selectFrom(RULE).where(RULE.STATUS.isNotNull()).orderBy(RULE.SITE.asc(), RULE.VERSION.desc()).fetch();
        ArrayList<RuleRecord> ruleRecords = new ArrayList<>();
        HashMap<String, RuleRecord> latest = new HashMap<>();
        //hardcode to obtain latest rules (with max version)
        for (RuleRecord ruleRecord : fetch) {
            if (latest.containsKey(ruleRecord.getSite())) {
                RuleRecord latestPretentent = latest.get(ruleRecord.getSite());
                if (latestPretentent.getVersion() < ruleRecord.getVersion()) {
                    latest.put(ruleRecord.getSite(), ruleRecord);
                }
            } else {
                latest.put(ruleRecord.getSite(), ruleRecord);
            }
        }
        ruleRecords.addAll(latest.values());
        return ruleRecords;
    }

    public List<ParsingRule> getAllParsingRules() {
        ArrayList<ParsingRule> list = new ArrayList<>();
        Result<RuleRecord> ruleRecords = getDslContext().selectFrom(RULE).where(RULE.STATUS.isNotNull()).fetch();
        for (RuleRecord ruleRecord : ruleRecords) {
            ParsingRule rule = RuleFactory.make(ruleRecord);
            list.add(rule);
        }
        return list;
    }

    private Optional<ParsingRule> rsToJsonRule(ResultSet rs) throws SQLException {
        ParsingRule jsonRule = getJsonRule(rs);

        return Optional.of(jsonRule);
    }

    private ParsingRule getJsonRule(ResultSet rs) throws SQLException {
        int version = rs.getInt("version");//for debug
        int id = rs.getInt("id");

        String rule = rs.getString("rule");
        ParsingRule make = RuleFactory.make(rule);

        make.setId(id);
        return make;
    }

    public Optional<RuleRecord> getById(Integer id) {
        RuleRecord ruleRecord = getDslContext().selectFrom(RULE).where(RULE.ID.eq(id)).fetchOne();
        return Optional.ofNullable(ruleRecord);
    }
}
