package org.nazymko.scheduler.task;

import lombok.Setter;
import org.nazymko.th.parser.autodao.tables.TTask;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;
import org.nazymko.thehomeland.parser.THLParser;
import org.nazymko.thehomeland.parser.db.dao.ScheduleDao;

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
            scheduleNextTask(tTask);
        }
    }

    protected void scheduleNextTask(TTaskRecord tTask) {

        Optional<TScheduleRecord> tScheduleRecord = scheduleDao.get(tTask.getScheduleSourceId());
        if(tScheduleRecord.isPresent()){

        }

    }


    public abstract void execute(TTaskRecord tTask);


}
