package org.nazymko.scheduler;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParser;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nazymko
 */
@Log4j2
public class MyCronTrigger {
    @Resource
    THLParser thlParser;
    @Resource
    TSchedule tSchedule;
    @Resource
    ScheduleDao scheduleDao;
    @Resource
    SiteDao siteDao;

    // * */5 * * * *  - every 5 minutes
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        log.debug("Cron trigger started");

        Map<TScheduleRecord, TTaskRecord> activeTasks = scheduleDao.getActiveTasks();

        for (TScheduleRecord tScheduleRecord : activeTasks.keySet()) {
            TTaskRecord tTaskRecord = activeTasks.get(tScheduleRecord);
            if (tScheduleRecord != null) {
                Optional<Site> site = siteDao.get(tScheduleRecord.getSiteid());
                if (site.isPresent()) {
                    Runnable task;
                    if (tScheduleRecord.getPageType() == null) {
                        task = thlParser.create(site.get().getUrl(), tScheduleRecord.getStartPage());
                    } else {
                        task = thlParser.create(site.get().getUrl(), tScheduleRecord.getStartPage(), tScheduleRecord.getPageType(), -1);
                    }
                } else {
                    log.error("Site with id {} doesn't exist ! Task starting interrupted", tScheduleRecord.getSiteid());
                }
            }
        }

        CronTrigger cronTrigger = new CronTrigger("*/5 * * * * *");
        Date date = cronTrigger.nextExecutionTime(new SimpleTriggerContext());

    }

}
