package org.nazymko.scheduler;

import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.TaskFactory;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
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
public class ParsingTriger {

    @Resource
    THLParserRunner runner;
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
        log.debug("Cron trigger started");
        disableFinished();
        scheduleNextOrRun();
    }

    private void disableFinished() {
        Result<TaskRunRecord> running = taskDao.getStarted();
        log.debug("Found {} running tasks.", running.size());
        if (!running.isEmpty()) {
            log.debug("{}", running);
        }
        for (TaskRunRecord tTaskRecord : running) {
            Integer tasks = runner.tasks(tTaskRecord.getId());
            if (tasks <= 0) {
                log.debug("Task run {} finished", tTaskRecord.getId());
                tTaskRecord.setFinishAt(now());
                tTaskRecord.setStatus(FINISHED);
                tTaskRecord.store();
            }
        }
    }

    private void scheduleNextOrRun() {
        Map<TaskScheduleRecord, TaskRunRecord> activeTasks = scheduleDao.getActiveTasks();
        log.debug("Found {} schedule records", activeTasks.size());

        for (TaskScheduleRecord scheduleRecord : activeTasks.keySet()) {
            TaskRunRecord tTaskRecord = activeTasks.get(scheduleRecord);
            if (tTaskRecord == null) {
                scheduleNew(scheduleRecord);
            } else if (tTaskRecord.getStartAt().before(now())) {
                executeRecord(scheduleRecord, tTaskRecord);
            }

        }
    }

    private void executeRecord(TaskScheduleRecord scheduleRecord, TaskRunRecord tTaskRecord) {
        Optional<ThSiteRecord> site = siteDao.get(scheduleRecord.getSiteid());
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

    private void scheduleNew(TaskScheduleRecord scheduleRecord) {
        log.debug("Run task missing for {}", scheduleRecord);
        Date startAt = new Date(scheduleRecord.getStartAt().getTime());
        Date date = new CronTrigger(scheduleRecord.getCron()).nextExecutionTime(new SimpleTriggerContext(startAt, startAt, startAt));
        log.debug("Next run scheduled for {}", date);
        Timestamp timestamp = new Timestamp(date.getTime());


        //schedule next run
        TaskRunRecord newRecord = new TaskRunRecord();
        newRecord.setStatus(NEW);
        newRecord.setRunType(CRON);
        newRecord.setSiteId(scheduleRecord.getSiteid());
        newRecord.setScheduleSourceId(scheduleRecord.getId());
        newRecord.setStartAt(timestamp);
        newRecord.setIsEnabled(true);

        taskDao.save(newRecord);
    }

}
