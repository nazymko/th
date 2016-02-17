package utils;

import org.jooq.util.derby.sys.Sys;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class TimeStampHelper {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
