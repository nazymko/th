package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.tables.pojos.ConnectorsSendHeaders;
import org.nazymko.th.parser.autodao.tables.records.ConnectorsSendHeadersRecord;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class ConnectorSendHeaderDao extends org.nazymko.th.parser.autodao.tables.daos.ConnectorsSendHeadersDao implements ContextSupply {

    @Resource
    DataSource dataSource;

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    public void addHeader(Integer consumerId, String header, String value) {
        ConnectorsSendHeaders _header = new ConnectorsSendHeaders();
        _header.setConsumerId(consumerId);
        _header.setHeader(header);
        _header.setValue(value);
        insert(_header);
    }
}
