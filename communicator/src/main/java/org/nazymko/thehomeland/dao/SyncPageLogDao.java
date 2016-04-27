package org.nazymko.thehomeland.dao;

import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;
import utils.TimeStampHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncPageLogDao extends AbstractDao<Integer, ConnectorSyncPageLogRecord> {
    public Optional<ConnectorSyncPageLogRecord> get(Integer key) {
        return getById(key);
    }

    public Integer save(ConnectorSyncPageLogRecord obj) {
        store(obj);
        return obj.getId();
    }

    public Optional<ConnectorSyncPageLogRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext()
                .selectFrom(CONNECTOR_SYNC_PAGE_LOG)
                .where(CONNECTOR_SYNC_PAGE_LOG.ID.eq(key))
                .fetchOne());
    }

    public Optional<ConnectorSyncPageLogRecord> getById(int key) {
        ConnectorSyncPageLogRecord connectorSyncLogRecord = getDslContext().selectFrom(CONNECTOR_SYNC_PAGE_LOG).where(CONNECTOR_SYNC_PAGE_LOG.ID.eq(key)).fetchOne();
        return Optional.of(connectorSyncLogRecord);
    }

    public List<ConnectorSyncPageLogRecord> all(String consumer) {
        return getDslContext().selectFrom(CONNECTOR_SYNC_PAGE_LOG).where(CONNECTOR_SYNC_PAGE_LOG.CONSUMER.equal(consumer)).fetch();
    }

    public Optional<Timestamp> getLatestDate(String consumer) {
        Record1<Timestamp> record = getDslContext()
                .select(DSL.max(CONNECTOR_SYNC_PAGE_LOG.TIME))
                .from(CONNECTOR_SYNC_PAGE_LOG)
                .where(CONNECTOR_SYNC_PAGE_LOG.CONSUMER.eq(consumer))
                .fetchOne();

        if (record.size() > 0) {
            return Optional.ofNullable(record.getValue(CONNECTOR_SYNC_PAGE_LOG.TIME));
        } else {
            return Optional.of(TimeStampHelper.zero());
        }
    }

    public void save(Integer pageId, String responseText, Integer responseCode, String consumer, Long time) {
        ConnectorSyncPageLogRecord rec = new ConnectorSyncPageLogRecord();

        rec.setPageId(pageId);
        rec.setConsumer(consumer);
        rec.setResponseCode(responseCode);
        rec.setResponseText(responseText);
        rec.setTime(new Timestamp(time));

        store(rec);
    }

    public int getLatestId(String consumer) {
        Record1<Integer> record = getDslContext().select(CONNECTOR_SYNC_PAGE_LOG.PAGE_ID.max())
                .from(CONNECTOR_SYNC_PAGE_LOG)
                .where(CONNECTOR_SYNC_PAGE_LOG.CONSUMER.eq(consumer)).fetchOne();
        if (record.getValue(0) == null) {
            return -1;
        }

        return record.getValue(CONNECTOR_SYNC_PAGE_LOG.PAGE_ID.max());
    }
}
