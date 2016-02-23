package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TSchedule.T_SCHEDULE;
import static org.nazymko.th.parser.autodao.tables.TTask.T_TASK;
import static utils.support.task.TaskStatus.NEW;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class ScheduleDao extends AbstractDao<Integer, TScheduleRecord> {
    @Override
    public Optional<TScheduleRecord> get(Integer key) {
        TScheduleRecord fetch = getDslContext().selectFrom(T_SCHEDULE).where(T_SCHEDULE.ID.eq(key)).fetchOne();
        return Optional.ofNullable(fetch);
    }

    public Optional<List<TScheduleRecord>> getAllForSite(Integer id) {
        List<TScheduleRecord> fetch = getDslContext().selectFrom(T_SCHEDULE).where(T_SCHEDULE.SITEID.eq(id)).fetch();
        return Optional.ofNullable(fetch);
    }

    @Override
    public Integer save(TScheduleRecord obj) {
        getDslContext().attach(obj);
        return obj.store();
    }

    public List<TScheduleRecord> getSchedules(Integer siteId) {
        return getDslContext().selectFrom(T_SCHEDULE).where(T_SCHEDULE.SITEID.eq(siteId)).fetch();

    }

    public List<TScheduleRecord> getAll() {
        return getDslContext().selectFrom(T_SCHEDULE).fetch();

    }


    public Map<TScheduleRecord, TTaskRecord> getActiveTasks() {
        Result<TScheduleRecord> tScheduleRecords = getDslContext()
                .selectFrom(T_SCHEDULE)
                .where(T_SCHEDULE.IS_ENABLED.eq(true))
                .fetch();


        Result<TTaskRecord> tTaskRecords = getDslContext()
                .selectFrom(T_TASK)
                .where(T_TASK.FINISH_AT.isNull())
                .and(T_TASK.STATUS.eq(NEW))
                .groupBy(T_TASK.SCHEDULE_SOURCE_ID)
                .fetch();

        Map<TScheduleRecord, TTaskRecord> recordMap = new HashMap<>();

        for (TScheduleRecord tScheduleRecord : tScheduleRecords) {

            TTaskRecord mainTtask = null;
            for (TTaskRecord proposedTTask : tTaskRecords) {
                if (proposedTTask.getScheduleSourceId().equals(tScheduleRecord.getId())) {
                    if (mainTtask == null) {
                        mainTtask = proposedTTask;
                    } else {
                        mainTtask = mainTtask.getStartAt().after(proposedTTask.getStartAt()) ? mainTtask : proposedTTask;
                    }
                }
            }
            recordMap.put(tScheduleRecord, mainTtask);
        }

        return recordMap;
    }


}
