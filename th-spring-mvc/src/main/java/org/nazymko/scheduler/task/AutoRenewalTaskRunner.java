package org.nazymko.scheduler.task;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.nazymko.thehomeland.parser.db.dao.TaskDao;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by nazymko.patronus@gmail.com.
 */

@Log4j2
public class AutoRenewalTaskRunner implements Runnable {
    @Setter
    Runnable _task;

    @Setter
    TaskRunRecord source;

    @Setter
    TaskDao taskDao;

    @Setter
    ScheduleDao scheduleDao;

    @Override
    public void run() {
        try {
            _task.run();
        } catch (Throwable th) {
            log.error(th);
            th.printStackTrace();
        } finally {
            finishTask(source);
            scheduleNext(source);
        }
    }

    private void scheduleNext(TaskRunRecord source) {
        TaskScheduleRecord record = scheduleDao.get(source.getScheduleSourceId()).get();
        Date nextExecutionTime = new CronTrigger(record.getCron()).nextExecutionTime(new SimpleTriggerContext());

        TaskRunRecord taskRecord = new TaskRunRecord();
        taskRecord.setStartAt(new Timestamp(nextExecutionTime.getTime()));
        taskRecord.setScheduleSourceId(source.getScheduleSourceId());
    }


    protected void finishTask(TaskRunRecord tTask) {
        tTask.setFinishAt(new Timestamp(System.currentTimeMillis()));
        taskDao.save(tTask);
    }

}
