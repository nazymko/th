package org.nazymko.thehomeland.parser.db.dao;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.Site.SITE;

/**
 * Created by nazymko
 */
public class SiteDao extends AbstractDao<Integer, Site> {

    @Override
    public Optional<Site> get(Integer key) {
        Site url = getJdbcTemplate().query("SELECT id,url,name FROM site WHERE id=:id", new MapSqlParameterSource("id", key), new ResultSetExtractor<Site>() {
            @Override
            public Site extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {

                    Site site = getSite(rs);

                    return site;
                } else {
                    return null;
                }
            }
        });

        return Optional.ofNullable(url);
    }

    private Site getSite(ResultSet rs) throws SQLException {
        Site site = new Site();

        site.setUrl(rs.getString("url"));
        site.setName(rs.getString("name"));
        site.setId(rs.getInt("id"));
        return site;
    }

    @Override

    public Integer save(Site site) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource("site", site.getUrl())
                .addValue("name", site.getName());

        int updated = getJdbcTemplate().update("INSERT INTO site(url,name) VALUES (:site,:name)",
                paramSource);

        return updated;
    }

    public Integer getIdByUrl(String url) {
        Integer query = getJdbcTemplate().query("SELECT id FROM site WHERE url=:url", new MapSqlParameterSource("url", url), new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        });
        return query;
    }


    public List<String> getUrlList(int pageSize, int page) {

        List<String> resultList = getJdbcTemplate().query("SELECT url FROM site LIMIT :start, :size", new MapSqlParameterSource("start", page * pageSize).addValue("size", pageSize), new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("url");
            }
        });

        return resultList;
    }


    public List<Site> getList(int pageSize, int page) {

        List<Site> resultList = getJdbcTemplate().query("SELECT url,name,id FROM site LIMIT :start, :size", new MapSqlParameterSource("start", page * pageSize).addValue("size", pageSize), new RowMapper<Site>() {
            @Override
            public Site mapRow(ResultSet resultSet, int i) throws SQLException {
                return getSite(resultSet);
            }
        });

        return resultList;
    }


    public List<Site> getAll() {

        DSLContext create = getDslContext();
        Result<SiteRecord> fetch = create.selectFrom(SITE).fetch();
        return transform(fetch);
    }


    private List<Site> transform(Result<SiteRecord> fetch) {

        ArrayList<Site> list = new ArrayList();

        for (SiteRecord siteRecord : fetch) {

            Site transformed = new Site(siteRecord.getValue(SITE.URL), siteRecord.getValue(SITE.NAME));
            transformed.setId(siteRecord.getValue(SITE.ID));
            list.add(transformed);

        }
        return list;
    }
}
