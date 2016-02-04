package org.nazymko.scheduler;

import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by nazymko
 */
public class MyCronTrigger {
    @Resource
    TSchedule tSchedule;

    // * */5 * * * *  - every 5 minutes
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {

        System.out.println(new Date());
        CronTrigger cronTrigger = new CronTrigger("*/5 * * * * *");
        Date date = cronTrigger.nextExecutionTime(new SimpleTriggerContext());
        System.out.println("date = " + date);

    }

}
