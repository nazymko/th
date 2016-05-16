package org.nazymko.thehomeland.parser;

import org.jooq.Record;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Converter<T extends Record> {
    Optional<Map> convert(T record);

    HashMap<String, String> getRule(Integer siteId);
}
