package org.nazymko.thehomeland.dao;

import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncLogRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;

import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ConnectorSyncLog.CONNECTOR_SYNC_LOG;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncLogDao extends AbstractDao<Integer, ConnectorSyncLogRecord> {
    public Optional<ConnectorSyncLogRecord> get(Integer key) {
        return getById(key);
    }

    public Integer save(ConnectorSyncLogRecord obj) {
        getDslContext().attach(obj);
        obj.store();
        return obj.getId();
    }

    public Optional<ConnectorSyncLogRecord> getById(int key) {
        ConnectorSyncLogRecord connectorSyncLogRecord = getDslContext().selectFrom(CONNECTOR_SYNC_LOG).where(CONNECTOR_SYNC_LOG.ID.eq(key)).fetchOne();
        return Optional.of(connectorSyncLogRecord);
    }
}
