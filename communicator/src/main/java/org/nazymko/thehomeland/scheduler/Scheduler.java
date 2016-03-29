package org.nazymko.thehomeland.scheduler;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Scheduler {
    /*Default scheduled task*/
    void doIt();

    /*Force default process for specific domain*/
    void doIt(String domain);

    /*Force to send all records from date without duplicate send check*/
    void doIt(String domain, Timestamp from, boolean force);

}
