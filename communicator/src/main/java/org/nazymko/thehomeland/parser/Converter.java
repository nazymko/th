package org.nazymko.thehomeland.parser;

import org.jooq.Record;

import java.util.Map;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Converter<T extends Record> {
    Map convert(T record, Map<String, String> roadMap);
}
