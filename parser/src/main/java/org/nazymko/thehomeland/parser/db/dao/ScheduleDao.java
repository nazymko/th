package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TaskRun.TASK_RUN;
import static org.nazymko.th.parser.autodao.tables.TaskSchedule.TASK_SCHEDULE;
import static utils.support.task.TaskStatus.NEW;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class ScheduleDao extends AbstractDao<Integer, TaskScheduleRecord> {


    public Optional<List<TaskScheduleRecord>> getAllForSite(Integer id) {
        List<TaskScheduleRecord> fetch = getDslContext().selectFrom(TASK_SCHEDULE).where(TASK_SCHEDULE.SITE_ID.eq(id)).fetch();
        return Optional.ofNullable(fetch);
    }

    @Override
    public Integer save(TaskScheduleRecord obj) {
        store(obj);
        return obj.getId();
    }

    @Override
    public Optional<TaskScheduleRecord> getById(Integer key) {
        TaskScheduleRecord fetch = getDslContext().selectFrom(TASK_SCHEDULE).where(TASK_SCHEDULE.ID.eq(key)).fetchOne();
        return Optional.ofNullable(fetch);
    }

    public List<TaskScheduleRecord> getSchedules(Integer siteId) {
        return getDslContext().selectFrom(TASK_SCHEDULE).where(TASK_SCHEDULE.SITE_ID.eq(siteId)).fetch();

    }

    public List<TaskScheduleRecord> getAll() {
        return getDslContext().selectFrom(TASK_SCHEDULE).fetch();

    }


    public Map<TaskScheduleRecord, TaskRunRecord> getActiveTasks() {
        Result<TaskScheduleRecord> tScheduleRecords = getDslContext()
                .selectFrom(TASK_SCHEDULE)
                .where(TASK_SCHEDULE.IS_ENABLED.eq(true))
                .fetch();


        Result<TaskRunRecord> tTaskRecords = getDslContext()
                .selectFrom(TASK_RUN)
                .where(TASK_RUN.FINISH_AT.isNull())
                .and(TASK_RUN.STATUS.eq(NEW))
                .groupBy(TASK_RUN.SCHEDULE_SOURCE_ID)
                .fetch();

        Map<TaskScheduleRecord, TaskRunRecord> recordMap = new HashMap<>();

        for (TaskScheduleRecord tScheduleRecord : tScheduleRecords) {

            TaskRunRecord mainTtask = null;
            for (TaskRunRecord proposedTTask : tTaskRecords) {
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
