package org.nazymko.thehomeland.parser.db.dao;

import com.google.gson.Gson;
import lombok.Setter;
import org.nazymko.th.parser.autodao.tables.records.RuleRecord;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.nazymko.thehomeland.parser.rule.RuleFactory;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.Rule.RULE;
import static utils.support.rule.RuleStatus.ACTIVE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RuleDao extends AbstractDao<String, JsonRule> {

    @Resource
    RuleResolver resolver;
    @Resource
    @Setter
    private SiteDao siteDao;

    @Override
    public Optional<JsonRule> get(String site) {
        Optional<JsonRule> result = getJdbcTemplate().query("" +
                "SELECT * FROM rule WHERE site=:site AND version = (SELECT  MAX(version) FROM rule )", new MapSqlParameterSource("site", site), new ResultSetExtractor<Optional<JsonRule>>() {
            @Override
            public Optional<JsonRule> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rsToJsonRule(rs);

                }
                return null;
            }
        });

        return result;
    }

    public Optional<JsonRule> getJsonById(int key) {
        Optional<JsonRule> result = getJdbcTemplate().query("" +
                "SELECT * FROM rule WHERE id=:id AND version = (SELECT  MAX(version) FROM rule )", new MapSqlParameterSource("id", key), new ResultSetExtractor<Optional<JsonRule>>() {
            @Override
            public Optional<JsonRule> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rsToJsonRule(rs);

                }
                return Optional.ofNullable(null);
            }
        });


        return result;
    }

    @Override
    public String save(JsonRule rule) {

        saveNewSiteOrIgnore(rule);

        RuleRecord ruleRecord = new RuleRecord();

        String serialized = new Gson().toJson(rule);
        Integer version = ruleVersion(rule.getUrl());
        ruleRecord.setSite(rule.getUrl());
        ruleRecord.setRule(serialized);
        ruleRecord.setVersion(version);
        ruleRecord.setStatus(ACTIVE);

        store(ruleRecord);

        resolver.refresh();
        return rule.getUrl();

    }

    private void saveNewSiteOrIgnore(JsonRule rule) {
        Integer idByUrl = siteDao.getIdByUrl(rule.getUrl());
        if (idByUrl < 0) {
            siteDao.save(new Site(rule.getUrl(), rule.getName()));
        }
    }

    private Integer ruleVersion(String url) {
        MapSqlParameterSource params = new MapSqlParameterSource("site", url);
        return getJdbcTemplate().query("SELECT COUNT(*) number,MAX(version) version FROM rule WHERE site =:site", params, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new RuntimeException("SQL ResultSet next failed");
                }
            }
        });
    }

    public List<JsonRule> getAll() {
        List<JsonRule> ruleList = getJdbcTemplate().query("SELECT site,rule,MAX(version) AS version ,id FROM rule GROUP BY site", new RowMapper<JsonRule>() {
                    @Override
                    public JsonRule mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return getJsonRule(rs);
                    }
                }
        );

        return ruleList;

    }

    public List<RuleRecord> getAllRules() {
        return getDslContext().selectFrom(RULE).where(RULE.STATUS.isNotNull()).fetch();
    }

    private Optional<JsonRule> rsToJsonRule(ResultSet rs) throws SQLException {
        JsonRule jsonRule = getJsonRule(rs);

        return Optional.of(jsonRule);
    }

    private JsonRule getJsonRule(ResultSet rs) throws SQLException {
        int version = rs.getInt("version");//for debug
        int id = rs.getInt("id");

        String rule = rs.getString("rule");
        JsonRule make = RuleFactory.make(rule);

        make.setId(id);
        return make;
    }

    public Optional<RuleRecord> getById(Integer id) {
        RuleRecord ruleRecord = getDslContext().selectFrom(RULE).where(RULE.ID.eq(id)).fetchOne();
        return Optional.ofNullable(ruleRecord);
    }
}
