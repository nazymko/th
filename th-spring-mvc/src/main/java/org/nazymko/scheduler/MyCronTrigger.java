package org.nazymko.scheduler;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.TaskFactory;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.nazymko.thehomeland.parser.db.model.Site;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static utils.TimeStampHelper.*;

/**
 * Created by nazymko
 */
@Log4j2
public class MyCronTrigger {

    @Resource
    private TaskFactory taskFac;
    @Resource
    THLParserRunner runner;
    @Resource
    TSchedule tSchedule;
    @Resource
    ScheduleDao scheduleDao;
    @Resource
    SiteDao siteDao;
    @Resource
    TaskDao taskDao;

    // * */1 * * * *  - every minute
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        log.info("Cron trigger started");

        Map<TScheduleRecord, TTaskRecord> activeTasks = scheduleDao.getActiveTasks();

        log.info("Found {} schedule records", activeTasks.size());
        for (TScheduleRecord scheduleRecord : activeTasks.keySet()) {
            TTaskRecord tTaskRecord = activeTasks.get(scheduleRecord);
            if (tTaskRecord == null) {

                log.info("Run task missing for {}", scheduleRecord);
                Date date = new CronTrigger(scheduleRecord.getCron()).nextExecutionTime(new SimpleTriggerContext());
                log.info("Next run scheduled for {}", date);
                Timestamp timestamp = new Timestamp(date.getTime());


                //schedule next run
                TTaskRecord newRecord = new TTaskRecord();
                newRecord.setScheduleSourceId(scheduleRecord.getId());
                newRecord.setStartAt(timestamp);
                newRecord.setIsEnabled(true);

                taskDao.save(newRecord);
                continue;
            } else if (tTaskRecord.getStartAt().before(now())) {
                Optional<Site> site = siteDao.get(scheduleRecord.getSiteid());
                if (site.isPresent()) {
                    Optional<Runnable> task;//Ok, lets start the job
                    Runnable runnable = taskFac.makeScheduledTask(scheduleRecord.getStartPage(), scheduleRecord.getPageType(), -1, tTaskRecord.getId());
                    runner.submit(runnable);

                } else {
                    log.error("Site with id {} doesn't exist ! Task starting interrupted", scheduleRecord.getSiteid());
                }
            }

        }
    }

}
