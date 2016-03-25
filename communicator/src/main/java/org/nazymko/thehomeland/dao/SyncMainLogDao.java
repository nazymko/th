package org.nazymko.thehomeland.dao;

import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncMainLogRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;

import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncMainLogDao extends AbstractDao<Integer, ConnectorSyncMainLogRecord> {
    public Optional<ConnectorSyncMainLogRecord> get(Integer key) {
        ConnectorSyncMainLogRecord connectorSyncMainLogRecord = getDslContext().selectFrom(CONNECTOR_SYNC_MAIN_LOG).where(CONNECTOR_SYNC_MAIN_LOG.ID.eq(key)).fetchOne();
        return Optional.ofNullable(connectorSyncMainLogRecord);
    }

    public Integer save(ConnectorSyncMainLogRecord obj) {
        getDslContext().attach(obj);
        obj.store();
        return obj.getId();
    }

    public Optional<ConnectorSyncMainLogRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext()
                .selectFrom(CONNECTOR_SYNC_MAIN_LOG)
                .where(CONNECTOR_SYNC_MAIN_LOG.ID.eq(key))
                .fetchOne());
    }

    public Optional<ConnectorSyncMainLogRecord> getById(int key) {
        return null;
    }
}
