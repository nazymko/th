package org.nazymko.thehomeland.parser.db.dao;

import lombok.Getter;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public abstract class AbstractDao<K, T > implements Dao<K, T> {

    @Getter
    @Resource
    DataSource source;
    @Resource
    private NamedParameterJdbcTemplate jdbcTemplate;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
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


    protected boolean isPresent(Optional optional) {
        return optional.isPresent();
    }

    protected boolean isPresent(Record record) {
        return record != null;
    }



  /*  protected Table<? extends TableRecord> getRecord() {

    }*/
}
