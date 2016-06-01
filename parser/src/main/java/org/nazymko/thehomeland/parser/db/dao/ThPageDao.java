package org.nazymko.thehomeland.parser.db.dao;

import org.nazymko.th.parser.autodao.Tables;
import org.nazymko.th.parser.autodao.tables.pojos.ConnectorConsumer;
import org.nazymko.th.parser.autodao.tables.pojos.ThPage;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.nazymko.th.parser.autodao.Tables.TH_PAGE;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class ThPageDao extends org.nazymko.th.parser.autodao.tables.daos.ThPageDao implements ContextSupply {

    @Resource
    DataSource dataSource;

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    public List<ThPage> getVisited(Integer siteId) {
        return getDsl().selectFrom(TH_PAGE)
                .where(TH_PAGE.SITE_ID.eq(siteId))
                .and(TH_PAGE.VISITED_AT.isNotNull())
                .orderBy(TH_PAGE.VISITED_AT.desc())
                .fetchInto(ThPage.class);
    }

}
