package org.nazymko.dao;

import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncLogRecord;
import org.nazymko.thehomeland.parser.db.dao.Dao;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncLogDao implements Dao<Integer, ConnectorSyncLogRecord> {
    public Optional<ConnectorSyncLogRecord> get(Integer key) {
        return null;
    }

    public Integer save(ConnectorSyncLogRecord obj) throws MalformedURLException, URISyntaxException {
        return null;
    }

    public Optional<ConnectorSyncLogRecord> getById(int key) {
        return null;
    }
}
