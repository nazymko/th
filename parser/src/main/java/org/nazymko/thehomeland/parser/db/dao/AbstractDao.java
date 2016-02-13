package org.nazymko.thehomeland.parser.db.dao;

import lombok.Getter;
import lombok.Setter;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by nazymko
 */
public abstract class AbstractDao<K, T> implements Dao<K, T> {

    T v;

    @Resource
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Getter
    @Resource
    DataSource source;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public Optional<T> getById(int key) {
        throw new NotImplementedException();
    }

    protected DSLContext getDslContext() {
        return DSL.using(getSource(), SQLDialect.MYSQL);
    }

    protected Timestamp currentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }



}
