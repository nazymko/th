package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.Record7;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

import static org.nazymko.th.parser.autodao.Tables.CONNECTOR_CONSUMER;
import static org.nazymko.th.parser.autodao.Tables.TH_PAGE;
import static org.nazymko.th.parser.autodao.Tables.TH_SITE;
import static org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG;

/**
 * Created by nazymko.patronus@gmail.com
 */
@Log4j2
public class ConnectorSyncPageLogDao extends org.nazymko.th.parser.autodao.tables.daos.ConnectorSyncPageLogDao implements ContextSupply {
    @Resource
    private DataSource source;

    @Override
    public DataSource getDatasource() {
        return source;
    }

    public Result<Record7<Integer, String, Integer, String, String, Timestamp, Integer>> fetchByConsumerIdJoinUrl(Integer id) {
        DSLContext context = getDsl();
        SelectConditionStep<Record7<Integer, String, Integer, String, String, Timestamp, Integer>> where = context.select(
                TH_PAGE.ID,
                TH_PAGE.PAGE_URL,

                TH_SITE.ID,
                TH_SITE.DEFAULT_URL,

                CONNECTOR_SYNC_PAGE_LOG.CONSUMER,
                CONNECTOR_SYNC_PAGE_LOG.TIME,
                CONNECTOR_SYNC_PAGE_LOG.RESPONSE_CODE
        )
                .from(CONNECTOR_SYNC_PAGE_LOG.join(TH_PAGE)
                        .on(CONNECTOR_SYNC_PAGE_LOG.PAGE_ID.eq(TH_PAGE.ID))
                        .join(TH_SITE)
                        .on(TH_PAGE.SITE_ID.eq(TH_SITE.ID)))
                .where(CONNECTOR_SYNC_PAGE_LOG.CONSUMER.eq(context.select(CONNECTOR_CONSUMER.DOMAIN)
                        .from(CONNECTOR_CONSUMER).where(CONNECTOR_CONSUMER.ID.eq(id))));
//        log.debug(where.getSQL());
        Result<Record7<Integer, String, Integer, String, String, Timestamp, Integer>> fetch = where.fetch();
        return fetch;
    }
    public List<ConnectorSyncPageLogRecord> fetchByConsumerIdWithoutMessage(Integer id) {
        DSLContext context = getDsl();
        return context.select(
                CONNECTOR_SYNC_PAGE_LOG.PAGE_ID,
                CONNECTOR_SYNC_PAGE_LOG.TIME,
                CONNECTOR_SYNC_PAGE_LOG.CONSUMER_ENDPOINT,
                CONNECTOR_SYNC_PAGE_LOG.RESPONSE_CODE,
                CONNECTOR_SYNC_PAGE_LOG.MESSAGE
        )
                .from(CONNECTOR_SYNC_PAGE_LOG)
                .where(CONNECTOR_SYNC_PAGE_LOG.CONSUMER.eq(context.select(CONNECTOR_CONSUMER.DOMAIN)
                        .from(CONNECTOR_CONSUMER).where(CONNECTOR_CONSUMER.ID.eq(id))))

                .fetchInto(ConnectorSyncPageLogRecord.class);
    }
}
