package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.Tables.TH_PAGE;


/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class PageDao extends AbstractDao<Integer, ThPageRecord> {
    public static final String PAGE_BY_ID = "SELECT authority,(SELECT authority FROM th_site s WHERE s.id=p.site_id) AS site_url,id,type,registered_at,visited_at FROM th_page p WHERE p.id=:id";
    public static final String NEWEST_PAGE_BY_URL = "SELECT authority,(SELECT authority FROM th_site s WHERE s.id=p.site_id) AS site_url,id,type,version,visited_at,registered_at FROM th_page p WHERE p.authority=:url AND version = (SELECT MAX(version) FROM th_page WHERE authority = :url)";
    @Resource
    TaskDao taskDao;
    @Qualifier("siteDao")
    @Autowired
    private SiteDao siteDao;

    /**
     * Get newest page by URL
     */
    private Page readPage(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String url = rs.getString("authority");
        String siteUrl = rs.getString("site_url");
        String type = rs.getString("type");
        Timestamp visitedAt = rs.getTimestamp("visited_at");
        Timestamp registeredAt = rs.getTimestamp("registered_at");

        return new Page(id, siteUrl, url, type, visitedAt, registeredAt);
    }

    @Override
    public Optional<ThPageRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext().selectFrom(TH_PAGE).where(TH_PAGE.SITE_ID.eq(key)).fetchOne());
    }

    public Optional<Page> getByUrl(String url) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("url", url);
        Page page = getJdbcTemplate().query(NEWEST_PAGE_BY_URL,
                source, new ResultSetExtractor<Page>() {
                    @Override
                    public Page extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            return readPage(rs);
                        }
                        return null;
                    }
                });
        return Optional.ofNullable(page);
    }

    /**
     * @return database Id
     */
    public Integer save(ThPageRecord record) {
        store(record);
        return record.getId();
    }

    private int getVersion(String page) {

        return getJdbcTemplate().query("SELECT MAX(version) max  FROM th_page WHERE authority=:url", new MapSqlParameterSource("url", page), new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getInt("max");
            }
        });
    }


    public void visit(String link) {

//        getDslContext().selectFrom(PAGE).where(PAGE.URL.eq(link)).and()
        Optional<Page> page = getByUrl(link);
        if (page.isPresent()) {
            Integer id = page.get().getId();
            getJdbcTemplate().update("UPDATE th_page SET visited_at = now() WHERE id = :id", new MapSqlParameterSource("id", id));
        }
    }

    public Result<ThPageRecord> getList(int pageSize, int pageNumber) {
        Result<ThPageRecord> fetch = getDslContext().selectFrom(TH_PAGE).orderBy(TH_PAGE.VISITED_AT.desc()).limit(pageNumber * pageSize, pageSize).fetch();
        return fetch;
    }

    public List<Page> getLatestVersion(Integer siteId) {
        return getJdbcTemplate().query("SELECT  *,max(version), s.authority AS site_url  FROM th_page p JOIN th_site s WHERE s.id=p.site_id AND p.site_id=:siteId AND p.visited_at IS NOT NULL GROUP BY p.authority",
                new MapSqlParameterSource("siteId", siteId),
                new RowMapper<Page>() {
                    @Override
                    public Page mapRow(ResultSet resultSet, int i) throws SQLException {
                        return readPage(resultSet);
                    }
                });
    }

    public ThPageRecord getPageByUrlAndSession(String link, Integer sessionKey) {
        log.debug("session key: {} , link {}", sessionKey, link);
        ThPageRecord pageRecord = getDslContext().selectFrom(TH_PAGE).where(TH_PAGE.PAGE_URL.eq(link))
                .and(TH_PAGE.TASK_RUN_ID.eq(sessionKey))
                .limit(1)
                .fetchOne();
        return pageRecord;
    }

    //TODO: how to identify consumer - authority ?
    public Result<ThPageRecord> getAfter(Integer lastPage, String consumer) {
        Integer siteId = siteDao.getIdByUrl(consumer);

        Result<ThPageRecord> records = getDslContext()
                .selectFrom(TH_PAGE)
                .where(TH_PAGE.SITE_ID.eq(siteId))
                .and(TH_PAGE.ID.gt(lastPage))
                .fetch();
        return records;
    }
}
