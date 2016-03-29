package org.nazymko.thehomeland.parser.db.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.nazymko.thehomeland.parser.rule.ParsingRule;
import org.nazymko.thehomeland.parser.rule.RuleFactory;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.nazymko.thehomeland.parser.utils.UrlSimplifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ThRule.TH_RULE;
import static utils.support.rule.RuleStatus.ACTIVE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RuleDao extends AbstractDao<String, ParsingRule> {

    @Resource
    RuleResolver resolver;
    @Autowired
    UrlSimplifier simplifier;
    @Resource
    @Setter
    private SiteDao siteDao;

    public Optional<ParsingRule> getJsonById(int key) {
        ParsingRule parsingRule = null;
        ThRuleRecord ruleRecord = getDslContext().selectFrom(TH_RULE).where(TH_RULE.ID.eq(key)).orderBy(TH_RULE.VERSION.desc()).limit(1).fetchOne();
        if (isPresent(ruleRecord)) {
            parsingRule = RuleFactory.make(ruleRecord.getRule());
            parsingRule.setId(ruleRecord.getId());
            parsingRule.setVersion(ruleRecord.getVersion());
        }

        return Optional.ofNullable(parsingRule);
    }

    @Override
    public String save(ParsingRule rule) {
        try {
            String authority = simplifier.authority(rule.getUrl());
            saveNewSiteOrIgnore(rule, authority);

            ThRuleRecord ruleRecord = new ThRuleRecord();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String serialized = gson.toJson(rule);
            Integer version = ruleMaxVersion(authority);
            ruleRecord.setRule(serialized);
            ruleRecord.setVersion(version + 1);
            ruleRecord.setStatus(ACTIVE);
            ruleRecord.setAuthority(authority);

            store(ruleRecord);

            resolver.refresh();
            return rule.getUrl();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<ParsingRule> getById(String site) {
        Optional<ParsingRule> result = getJdbcTemplate().query("SELECT * FROM th_rule WHERE authority=:site AND version = (SELECT  MAX(version) FROM th_rule WHERE authority=:site)", new MapSqlParameterSource("site", site), new ResultSetExtractor<Optional<ParsingRule>>() {
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

    private void saveNewSiteOrIgnore(JsonRule rule, String authority) throws MalformedURLException, URISyntaxException {

        Integer idByUrl = siteDao.getIdByUrl(authority);
        if (idByUrl < 0) {
            ThSiteRecord site = new ThSiteRecord();
            site.setAuthority(authority);
            site.setName(rule.getName());
            siteDao.save(site);
        }
    }


    private Integer ruleMaxVersion(String url) {
        Record1<Integer> currentMaxVersion = getDslContext().select(DSL.max(TH_RULE.VERSION)).from(TH_RULE).where(TH_RULE.AUTHORITY.eq(url)).fetchOne();
        Object value = currentMaxVersion.getValue(0);
        return value == null ? 0 : (Integer) value;
    }


    public List<ThRuleRecord> getAllRules() {

        return getDslContext().selectFrom(TH_RULE).where(TH_RULE.STATUS.isNotNull()).fetch();
    }

    public List<ParsingRule> getLatestParsingRules() {
        List<ThRuleRecord> latest = getLatest();
        List<ParsingRule> ruleList = new ArrayList<>();
        for (ThRuleRecord ruleRecord : latest) {
            ruleList.add(RuleFactory.make(ruleRecord));

        }
        return ruleList;
    }

    public List<ThRuleRecord> getLatest() {
        Result<ThRuleRecord> fetch = getDslContext()
                .selectFrom(TH_RULE)
                .where(TH_RULE.STATUS.isNotNull())
                .orderBy(TH_RULE.VERSION.desc())
                .fetch();

        ArrayList<ThRuleRecord> ruleRecords = new ArrayList<>();
        HashMap<String, ThRuleRecord> latest = new HashMap<>();
        //hardcode to obtain latest rules (with max version)
        for (ThRuleRecord ruleRecord : fetch) {
            if (latest.containsKey(ruleRecord.getAuthority())) {
                ThRuleRecord latestPretentent = latest.get(ruleRecord.getAuthority());
                if (latestPretentent.getVersion() < ruleRecord.getVersion()) {
                    latest.put(ruleRecord.getAuthority(), ruleRecord);
                }
            } else {
                latest.put(ruleRecord.getAuthority(), ruleRecord);
            }
        }
        ruleRecords.addAll(latest.values());
        return ruleRecords;
    }

    public List<ParsingRule> getAllParsingRules() {
        ArrayList<ParsingRule> list = new ArrayList<>();
        Result<ThRuleRecord> ruleRecords = getDslContext().selectFrom(TH_RULE).where(TH_RULE.STATUS.isNotNull()).fetch();
        for (ThRuleRecord ruleRecord : ruleRecords) {
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

    public Optional<ThRuleRecord> getById(Integer id) {
        ThRuleRecord ruleRecord = getDslContext().selectFrom(TH_RULE).where(TH_RULE.ID.eq(id)).fetchOne();
        return Optional.ofNullable(ruleRecord);
    }
}
