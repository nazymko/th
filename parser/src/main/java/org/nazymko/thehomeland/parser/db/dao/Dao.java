package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.TableRecord;

import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Dao<K, T > {
    K save(T obj);

    Optional<T> getById(K key);
}
