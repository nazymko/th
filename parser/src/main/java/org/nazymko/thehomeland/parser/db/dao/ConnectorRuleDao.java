package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.tables.pojos.ConnectorRules;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class ConnectorRuleDao extends org.nazymko.th.parser.autodao.tables.daos.ConnectorRulesDao implements ContextSupply {

    @Resource
    DataSource dataSource;

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    public void add(Integer consumerId, Integer siteId, String rule) {
        ConnectorRules rules = new ConnectorRules();
        rules.setRule(rule);
        rules.setConsumerId(consumerId);
        rules.setSiteId(siteId);
        insert(rules);
    }
}
