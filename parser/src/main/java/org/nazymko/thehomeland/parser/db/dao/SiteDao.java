package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ThSite.TH_SITE;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SiteDao extends AbstractDao<Integer, ThSiteRecord> {

    @Override
    public Optional<ThSiteRecord> get(Integer id) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.ID.eq(id)).fetchOne();

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
    public Integer save(ThSiteRecord site) {

        getDslContext().attach(site);
        site.store();

        return site.getId();
    }

    public Integer getIdByUrl(String url) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.URL.eq(url)).fetchOne();
        if (siteRecord != null) {
            return siteRecord.getId();
        }
        return -1;
    }

    public Optional<ThSiteRecord> getByUrl(String url) {
        ThSiteRecord siteRecord = getDslContext().selectFrom(TH_SITE).where(TH_SITE.URL.eq(url)).fetchOne();
        if (siteRecord != null) {
            return Optional.of(siteRecord);
        }
        return Optional.empty();
    }

    public List<String> getUrlList(int pageSize, int page) {

        List<String> resultList = getJdbcTemplate().query("SELECT url FROM th_site LIMIT :start, :size", new MapSqlParameterSource("start", page * pageSize).addValue("size", pageSize), new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("url");
            }
        });

        return resultList;
    }

    public List<ThSiteRecord> getList(int pageSize, int page) {
        return getDslContext().selectFrom(TH_SITE).limit(page * pageSize, pageSize).fetch();
    }


    public List<Site> getAll() {

        DSLContext create = getDslContext();
        Result<ThSiteRecord> fetch = create.selectFrom(TH_SITE).fetch();
        return transform(fetch);
    }


    private List<Site> transform(Result<ThSiteRecord> fetch) {

        ArrayList<Site> list = new ArrayList();

        for (ThSiteRecord siteRecord : fetch) {

            Site transformed = new Site(siteRecord.getValue(TH_SITE.URL), siteRecord.getValue(TH_SITE.NAME));
            transformed.setId(siteRecord.getValue(TH_SITE.ID));
            list.add(transformed);

        }
        return list;
    }
}
