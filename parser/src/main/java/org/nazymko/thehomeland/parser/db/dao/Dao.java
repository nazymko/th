package org.nazymko.thehomeland.parser.db.dao;

import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Dao<K, T> {
    Optional<T> get(K key);

    K save(T obj);

    Optional<T> getById(int key);
}
