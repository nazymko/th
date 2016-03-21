package org.nazymko.thehomeland.parser.db.dao;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Dao<K, T> {
    Optional<T> get(K key);

    K save(T obj) throws MalformedURLException /*WTF*/, URISyntaxException;

    Optional<T> getById(int key);
}
