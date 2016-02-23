package org.nazymko.thehomeland.parser.db.dao;

import lombok.Getter;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public abstract class AbstractDao<K, T> implements Dao<K, T> {

    T v;
    @Getter
    @Resource
    DataSource source;
    @Resource
    private NamedParameterJdbcTemplate jdbcTemplate;

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

    protected void store(UpdatableRecord target) {
        DSLContext dslContext = getDslContext();
        dslContext.attach(target);
        target.store();
        dslContext.close();
    }


}
