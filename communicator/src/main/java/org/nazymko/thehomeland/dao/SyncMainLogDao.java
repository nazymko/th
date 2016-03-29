package org.nazymko.thehomeland.dao;

import org.jooq.Record1;
import org.jooq.impl.DSL;
import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncMainLogRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG;
import static utils.TimeStampHelper.now;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncMainLogDao extends AbstractDao<Integer, ConnectorSyncMainLogRecord> {

    @Qualifier("consumerDao")
    @Autowired
    private SyncConsumerDao consumerDao;

    public Optional<ConnectorSyncMainLogRecord> get(Integer key) {
        ConnectorSyncMainLogRecord connectorSyncMainLogRecord = getDslContext().selectFrom(CONNECTOR_SYNC_MAIN_LOG).where(CONNECTOR_SYNC_MAIN_LOG.ID.eq(key)).fetchOne();
        return Optional.ofNullable(connectorSyncMainLogRecord);
    }

    public Integer save(ConnectorSyncMainLogRecord obj) {
        store(obj);
        return obj.getId();
    }

    public Optional<ConnectorSyncMainLogRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext()
                .selectFrom(CONNECTOR_SYNC_MAIN_LOG)
                .where(CONNECTOR_SYNC_MAIN_LOG.ID.eq(key))
                .fetchOne());
    }


    public Long getLastPage(String consumer) {
        ConnectorConsumerRecord consumerRecord = consumerDao.getByDomain(consumer).get();
        Record1<Long> stringRecord1 = getDslContext()
                .select(DSL.max(CONNECTOR_SYNC_MAIN_LOG.LATEST_PAGE_ID))
                .from(CONNECTOR_SYNC_MAIN_LOG)
                .where(CONNECTOR_SYNC_MAIN_LOG.CONSUMER_ID.eq(consumerRecord.getId()))
                .fetchOne();
        return stringRecord1.getValue(0) == null ? -1 : (Long) stringRecord1.getValue(0);
    }

    public void logSync(String domain, Integer added, Long latestId) {
        ConnectorSyncMainLogRecord _old = getLastLog(domain);
        ConnectorSyncMainLogRecord _new = new ConnectorSyncMainLogRecord();
        getDslContext().attach(_new);

        ConnectorConsumerRecord consumerRecord = consumerDao.getByDomain(domain).get();

        _new.setConsumerId(consumerRecord.getId());
        _new.setCountNew(added);
        _new.setCountTotal(added + _old.getCountTotal());
        _new.setLatestPageId(latestId);
        _new.setSyncDate(now());
        _new.store();

    }

    private ConnectorSyncMainLogRecord getLastLog(String domain) {

        ConnectorConsumerRecord consumerRecord = consumerDao.getByDomain(domain).get();
        return getDslContext().selectFrom(CONNECTOR_SYNC_MAIN_LOG)
                .where(CONNECTOR_SYNC_MAIN_LOG.CONSUMER_ID.eq(consumerRecord.getId()))
                .orderBy(CONNECTOR_SYNC_MAIN_LOG.SYNC_DATE.desc()).limit(1)
                .fetchOne();
    }

    public Optional<ConnectorSyncMainLogRecord> getById(int key) {
        return null;
    }
}
