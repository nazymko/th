package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.Site.SITE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SiteDao extends AbstractDao<Integer, SiteRecord> {

    @Override
    public Optional<SiteRecord> get(Integer id) {
        SiteRecord siteRecord = getDslContext().selectFrom(SITE).where(SITE.ID.eq(id)).fetchOne();

        return Optional.of(siteRecord);
    }
/*
    private Site getSite(ResultSet rs) throws SQLException {
        Site site = new Site();

        site.setUrl(rs.getString("url"));
        site.setName(rs.getString("name"));
        site.setId(rs.getInt("id"));
        return site;
    }*/

    @Override
    public Integer save(SiteRecord site) {

        getDslContext().attach(site);
        site.store();

        return site.getId();
    }

    public Integer getIdByUrl(String url) {
        SiteRecord siteRecord = getDslContext().selectFrom(SITE).where(SITE.URL.eq(url)).fetchOne();
        if(siteRecord!=null){
            return siteRecord.getId();
        }
        return -1;
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

    public List<SiteRecord> getList(int pageSize, int page) {
        return getDslContext().selectFrom(SITE).limit(page * pageSize, pageSize).fetch();
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
