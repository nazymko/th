package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class ConnectorConsumerDao extends org.nazymko.th.parser.autodao.tables.daos.ConnectorConsumerDao implements ContextSupply {

    @Resource
    DataSource dataSource;

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    public void create(String domain) {
        ConnectorConsumer _cons = new ConnectorConsumer();
        _cons.setDomain(domain);
        insert(_cons);

    }
}
