package utils;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class TimeStampHelper {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp zero() {
        return new Timestamp(0);
    }
}
