package org.nazymko.scheduler;

import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
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

import static utils.TimeStampHelper.now;
import static utils.support.runtype.RunType.CRON;
import static utils.support.task.TaskStatus.*;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class ScheduleCronTrigger {

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
    @Resource
    private TaskFactory taskFac;

    // * */1 * * * *  - every minute
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        log.info("Cron trigger started");
        disableFinished();
        scheduleNextOrRun();
    }

    private void disableFinished() {
        Result<TTaskRecord> running = taskDao.getStarted();
        log.info("Found {} running tasks.", running.size());
        if (!running.isEmpty()) {
            log.info("{}", running);
        }
        for (TTaskRecord tTaskRecord : running) {
            Integer tasks = runner.tasks(tTaskRecord.getId());
            if (tasks <= 0) {
                log.info("Task run {} finished", tTaskRecord.getId());
                tTaskRecord.setFinishAt(now());
                tTaskRecord.setStatus(FINISHED);
                tTaskRecord.store();
            }
        }
    }

    private void scheduleNextOrRun() {
        Map<TScheduleRecord, TTaskRecord> activeTasks = scheduleDao.getActiveTasks();
        log.info("Found {} schedule records", activeTasks.size());

        for (TScheduleRecord scheduleRecord : activeTasks.keySet()) {
            TTaskRecord tTaskRecord = activeTasks.get(scheduleRecord);
            if (tTaskRecord == null) {
                scheduleNew(scheduleRecord);
            } else if (tTaskRecord.getStartAt().before(now())) {
                executeRecord(scheduleRecord, tTaskRecord);
            }

        }
    }

    private void executeRecord(TScheduleRecord scheduleRecord, TTaskRecord tTaskRecord) {
        Optional<SiteRecord> site = siteDao.get(scheduleRecord.getSiteid());
        if (site.isPresent()) {
            //Ok, lets start the job
            Runnable runnable = taskFac.makeScheduledTask(scheduleRecord.getStartPage(), scheduleRecord.getPageType(), -1, tTaskRecord.getId());
            runner.submit(runnable);

            tTaskRecord.setStatus(RUNNING);
            tTaskRecord.store();

        } else {
            log.error("Site with id {} doesn't exist ! Task starting interrupted", scheduleRecord.getSiteid());
        }
    }

    private void scheduleNew(TScheduleRecord scheduleRecord) {
        log.info("Run task missing for {}", scheduleRecord);
        Date startAt = new Date(scheduleRecord.getStartAt().getTime());
        Date date = new CronTrigger(scheduleRecord.getCron()).nextExecutionTime(new SimpleTriggerContext(startAt, startAt, startAt));
        log.info("Next run scheduled for {}", date);
        Timestamp timestamp = new Timestamp(date.getTime());


        //schedule next run
        TTaskRecord newRecord = new TTaskRecord();
        newRecord.setStatus(NEW);
        newRecord.setRunType(CRON);
        newRecord.setSiteId(scheduleRecord.getSiteid());
        newRecord.setScheduleSourceId(scheduleRecord.getId());
        newRecord.setStartAt(timestamp);
        newRecord.setIsEnabled(true);

        taskDao.save(newRecord);
    }

}
