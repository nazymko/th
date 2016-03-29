package org.nazymko.thehomeland.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class HourlyScheduler implements Scheduler {


    @Scheduled(cron = "0 */1 * * *")
    public void doIt() {


    }

    public void doIt(String domain) {

    }

    public void doIt(String domain, Timestamp from, boolean force) {

    }
}
