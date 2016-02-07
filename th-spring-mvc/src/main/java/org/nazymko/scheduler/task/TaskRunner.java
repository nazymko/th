package org.nazymko.scheduler.task;

import lombok.Setter;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParser;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * Created by nazymko
 */
public abstract class TaskRunner implements Runnable {

    @Setter
    TTaskRecord tTask;

    @Setter
    THLParser parser;

    @Setter
    ScheduleDao scheduleDao;

    @Override
    public void run() {
        try {
            execute(tTask);
        } finally {
            finishTask(tTask);
        }
    }

    protected void finishTask(TTaskRecord tTask) {
        tTask.setFinishAt(new Timestamp(System.currentTimeMillis()));
        tTask.store();
    }

    public abstract void execute(TTaskRecord tTask);


}
