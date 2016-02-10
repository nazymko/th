package org.nazymko.scheduler;

import lombok.extern.log4j.Log4j2;
import org.nazymko.scheduler.task.AutoRenewalTaskRunner;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParser;
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
    @Resource
    TaskDao taskDao;

    // * */1 * * * *  - every minute
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        log.debug("Cron trigger started");

        Map<TScheduleRecord, TTaskRecord> activeTasks = scheduleDao.getActiveTasks();

        log.info("Found {} schedule records", activeTasks.size());
        for (TScheduleRecord tScheduleRecord : activeTasks.keySet()) {
            TTaskRecord tTaskRecord = activeTasks.get(tScheduleRecord);
            if (tTaskRecord != null) {
                Optional<Site> site = siteDao.get(tScheduleRecord.getSiteid());
                if (site.isPresent()) {
                    Optional<Runnable> task;

                    if (tScheduleRecord.getPageType() == null) {
                        task = thlParser.getRunnableWithoutCheck(site.get().getUrl(), tScheduleRecord.getStartPage(), tScheduleRecord.getPageType());
                    } else {
                        task = thlParser.getRunnableWithoutCheck(site.get().getUrl(), tScheduleRecord.getStartPage(), tScheduleRecord.getPageType(), -1);
                    }

                    if (!task.isPresent()) {
                        log.info("Page {} should not be visited.", site.get().getUrl());
                        return;
                    }

                    AutoRenewalTaskRunner taskRunner = new AutoRenewalTaskRunner();
                    taskRunner.set_task(task.get());
                    taskRunner.setSource(tTaskRecord);
                    taskRunner.setTaskDao(taskDao);

                    thlParser.submit(taskRunner);

                } else {
                    log.error("Site with id {} doesn't exist ! Task starting interrupted", tScheduleRecord.getSiteid());
                }
            } else {

                log.info("Run task missing for {}", tScheduleRecord);
                Date date = new CronTrigger(tScheduleRecord.getCron()).nextExecutionTime(new SimpleTriggerContext());
                Timestamp timestamp = new Timestamp(date.getTime());


                //schedule next run
                TTaskRecord newRecord = new TTaskRecord();
                newRecord.setScheduleSourceId(tScheduleRecord.getId());
                newRecord.setStartAt(timestamp);
//                newRecord.setInstanceId(THLParser.signature);
                newRecord.setIsEnabled(true);

                taskDao.save(newRecord);

            }
        }
    }

}
