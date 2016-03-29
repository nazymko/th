package org.nazymko.thehomeland.dao;

import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;

import java.util.Optional;

import static org.nazymko.th.parser.autodao.Tables.CONNECTOR_CONSUMER;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncConsumerDao extends AbstractDao<Integer, ConnectorConsumerRecord> {
    public Integer save(ConnectorConsumerRecord obj) {
        store(obj);
        return obj.getId();
    }

    public Optional<ConnectorConsumerRecord> getById(Integer key) {
        return Optional.ofNullable(getDslContext().selectFrom(CONNECTOR_CONSUMER).where(CONNECTOR_CONSUMER.ID.eq(key)).fetchOne());
    }

    public Optional<ConnectorConsumerRecord> getByDomain(String domain) {
        return Optional.ofNullable(getDslContext().selectFrom(CONNECTOR_CONSUMER).where(CONNECTOR_CONSUMER.DOMAIN.eq(domain)).fetchOne());
    }
}
