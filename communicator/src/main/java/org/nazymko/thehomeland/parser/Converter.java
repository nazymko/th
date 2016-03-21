package org.nazymko.thehomeland.parser;

import org.jooq.Record;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Converter<T extends Record> {
    Dto covert(T record);
}
