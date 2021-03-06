package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.Tables.TH_PAGE;


/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class PageDao extends AbstractDao<Integer, ThPageRecord> {
    @Resource
    TaskDao taskDao;

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
        return Optional.ofNullable(getDslContext().selectFrom(TH_PAGE).where(TH_PAGE.ID.eq(key)).fetchOne());
    }

    public Optional<ThPageRecord> getByUrl(String url) {
        ThPageRecord record = getDslContext()
                .selectFrom(TH_PAGE)
                .where(TH_PAGE.PAGE_URL.eq(url))
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(record);

   /*     MapSqlParameterSource source = new MapSqlParameterSource();
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
        return Optional.ofNullable(page);*/
    }

    /**
     * @return database Id
     */
    public Integer save(ThPageRecord record) {
        store(record);
        return record.getId();
    }

    public Result<ThPageRecord> getList(int pageSize, int pageNumber) {
        Result<ThPageRecord> fetch = getDslContext().selectFrom(TH_PAGE).orderBy(TH_PAGE.VISITED_AT.desc()).limit(pageNumber * pageSize, pageSize).fetch();
        return fetch;
    }

    public Result<ThPageRecord> getByType(int pageSize, int pageNumber, String type) {
        Result<ThPageRecord> fetch = getDslContext().selectFrom(TH_PAGE).where(TH_PAGE.TYPE.eq(type)).orderBy(TH_PAGE.VISITED_AT.desc()).limit(pageNumber * pageSize, pageSize).fetch();
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

    public Result<ThPageRecord> getByType(String type) {
        return getDslContext().selectFrom(TH_PAGE).where(TH_PAGE.TYPE.eq(type)).fetch();
    }

    public Result<ThPageRecord> getAfter(Integer lastPage, String type) {
        Result<ThPageRecord> records = getDslContext()
                .selectFrom(TH_PAGE)
                .where(TH_PAGE.ID.gt(lastPage))
                .and(TH_PAGE.TYPE.eq(type))
                .and(TH_PAGE.VISITED_AT.isNotNull())
                .fetch();

        return records;
    }

    public Integer countAll() {
        DSLContext dslContext = getDslContext();
        int total = dslContext.fetchCount(dslContext.selectFrom(TH_PAGE));
        return total;
    }

    public List<String> allTypes() {
        DSLContext dslContext = getDslContext();
        Result<Record1<String>> fetch = dslContext.select(TH_PAGE.TYPE).from(TH_PAGE).groupBy(TH_PAGE.TYPE).fetch();
        ArrayList<String> list = new ArrayList<>();
        for (Record1<String> stringRecord1 : fetch) {
            list.add(stringRecord1.value1());
        }


        return list;
    }
}
