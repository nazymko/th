package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

public interface ContextSupply {
    default DSLContext getDsl() {
        return DSL.using(getDatasource(), SQLDialect.MYSQL);
    }

    DataSource getDatasource();
}
