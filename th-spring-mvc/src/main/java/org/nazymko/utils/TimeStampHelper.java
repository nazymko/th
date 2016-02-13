package org.nazymko.utils;

import org.jooq.util.derby.sys.Sys;

import java.sql.Timestamp;

/**
 * Created by nazymko
 */
public class TimeStampHelper {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
