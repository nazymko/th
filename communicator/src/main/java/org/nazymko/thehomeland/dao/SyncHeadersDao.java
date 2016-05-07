package org.nazymko.thehomeland.dao;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.pojos.ConnectorsSendHeaders;
import org.nazymko.th.parser.autodao.tables.records.ConnectorsSendHeadersRecord;
import org.nazymko.thehomeland.parser.db.dao.AbstractDao;

import java.util.HashMap;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.Tables.CONNECTORS_SEND_HEADERS;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class SyncHeadersDao extends AbstractDao<Integer, ConnectorsSendHeadersRecord> {

    @Override
    public Integer save(ConnectorsSendHeadersRecord obj) {
        store(obj);
        return obj.getId();
    }

    @Override
    public Optional<ConnectorsSendHeadersRecord> getById(Integer key) {
        ConnectorsSendHeadersRecord record = getDslContext().selectFrom(CONNECTORS_SEND_HEADERS)
                .where(CONNECTORS_SEND_HEADERS.ID.eq(key))
                .fetchOne();
        return Optional.ofNullable(record);
    }

    public ConnectorsSendHeaders header(Integer consumerId, String name) {
        return getDslContext().selectFrom(CONNECTORS_SEND_HEADERS)
                .where(CONNECTORS_SEND_HEADERS.CONSUMER_ID.eq(consumerId))
                .and(CONNECTORS_SEND_HEADERS.HEADER.eq(name))
                .fetchOneInto(ConnectorsSendHeaders.class);
    }

    public HashMap<String, Object> getByConsumer(Integer id) {
        HashMap<String, Object> headers = new HashMap<>();
        Result<ConnectorsSendHeadersRecord> fetch = getDslContext().selectFrom(CONNECTORS_SEND_HEADERS).where(CONNECTORS_SEND_HEADERS.CONSUMER_ID.eq(id)).fetch();
        for (ConnectorsSendHeadersRecord rec : fetch) {
            headers.put(rec.getHeader(), rec.getValue());
        }

        return headers;
    }
}
