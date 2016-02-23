package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.PageRecord;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.Page.PAGE;


/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class PageDao extends AbstractDao<String, Page> {
    public static final String PAGE_BY_ID = "SELECT url,(SELECT url FROM site s WHERE s.id=p.site_id) AS site_url,id,type,registered_at,visited_at FROM page p WHERE p.id=:id";
    public static final String NEWEST_PAGE_BY_URL = "SELECT url,(SELECT url FROM site s WHERE s.id=p.site_id) AS site_url,id,type,version,visited_at,registered_at FROM page p WHERE p.url=:url AND version = (SELECT MAX(version) FROM page WHERE url = :url)";

    /**
     * Get newest page by URL
     */
    @Override
    public Optional<Page> get(String key) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("url", key);
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

    private Page readPage(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String url = rs.getString("url");
        String siteUrl = rs.getString("site_url");
        String type = rs.getString("type");
        Timestamp visitedAt = rs.getTimestamp("visited_at");
        Timestamp registeredAt = rs.getTimestamp("registered_at");

        return new Page(id, siteUrl, url, type, visitedAt, registeredAt);
    }

    @Override
    public String save(Page obj) {

        MapSqlParameterSource source = new MapSqlParameterSource();
        int version = getVersion(obj.getPage());

        source.addValue("site", obj.getSite());
        source.addValue("url", obj.getPage());
        source.addValue("type", obj.getType());
        source.addValue("sourcePage", obj.getSourcePage());
        source.addValue("version", version + 1);

        int update = getJdbcTemplate().update("INSERT INTO page(site_id,url,type,version,registered_at,sourcePage) VALUES ((SELECT id FROM site s WHERE s.url = :site),:url,:type,:version,now(),:sourcePage)", source);
        return obj.getPage();
    }
    /**
     * @return database Id
     */
    public Integer save(PageRecord record) {

        getDslContext().attach(record);
        record.store();

        return record.getId();
    }

    private int getVersion(String page) {

        return getJdbcTemplate().query("SELECT MAX(version) max  FROM page WHERE url=:url", new MapSqlParameterSource("url", page), new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getInt("max");
            }
        });
    }

    @Override
    public Optional<Page> getById(int key) {
        return getJdbcTemplate().query(PAGE_BY_ID, new MapSqlParameterSource("id", key), new ResultSetExtractor<Optional<Page>>() {
            @Override
            public Optional<Page> extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next())
                    return Optional.of(readPage(rs));
                else
                    return null;
            }
        });
    }

    public void visit(String link) {
        Optional<Page> page = get(link);
        if (page.isPresent()) {
            Integer id = page.get().getId();
            getJdbcTemplate().update("UPDATE page SET visited_at = now() WHERE id = :id", new MapSqlParameterSource("id", id));
        }
    }

    public List<Page> getList(int pageSize, int pageNumber) {
        List<Page> list = getJdbcTemplate().query("SELECT url,(SELECT url FROM site s WHERE s.id=p.site_id) AS site_url,id,type,version,visited_at,registered_at FROM page p ORDER BY visited_at DESC LIMIT :start OFFSET :offset", new MapSqlParameterSource("start", pageSize).addValue("offset", pageSize * pageNumber), new RowMapper<Page>() {
            @Override
            public Page mapRow(ResultSet resultSet, int i) throws SQLException {
                return readPage(resultSet);
            }
        });

        return list;
    }

    public List<Page> getLatestVersion(Integer siteId) {
        return getJdbcTemplate().query("SELECT  *,max(version), s.url AS site_url  FROM page p JOIN site s WHERE s.id=p.site_id AND p.site_id=:siteId AND p.visited_at IS NOT NULL GROUP BY p.url",
                new MapSqlParameterSource("siteId", siteId),
                new RowMapper<Page>() {
                    @Override
                    public Page mapRow(ResultSet resultSet, int i) throws SQLException {
                        return readPage(resultSet);
                    }
                });
    }

    public PageRecord getPageByUrlAndSession(String link, Integer sessionKey) {
        log.error("session key: {} , link {}",sessionKey,link);
        PageRecord pageRecord = getDslContext().selectFrom(PAGE).where(PAGE.URL.eq(link)).and(PAGE.TASK_RUN_ID.eq(sessionKey)).fetchOne();
        return pageRecord;
    }
}
