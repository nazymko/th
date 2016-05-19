package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.DSLContext;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.List;

import static org.nazymko.th.parser.autodao.Tables.CONNECTOR_CONSUMER;
import static org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class ConnectorSyncPageLogDao extends org.nazymko.th.parser.autodao.tables.daos.ConnectorSyncPageLogDao implements ContextSupply {
    @Resource
    private DataSource source;


    @Override
    public DataSource getDatasource() {
        return source;

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
