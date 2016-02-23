package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TTask.T_TASK;
import static utils.support.task.TaskStatus.FINISHED;
import static utils.support.task.TaskStatus.RUNNING;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class TaskDao extends AbstractDao<Integer, TTaskRecord> {


    @Override
    public Optional<TTaskRecord> get(Integer key) {

        return Optional.ofNullable(getDslContext().selectFrom(T_TASK).where(T_TASK.ID.eq(key)).fetchOne());

    }

    public Optional<List<TTaskRecord>> getByScheduleId(Integer id) {
        return Optional.ofNullable(getDslContext().selectFrom(T_TASK).where(T_TASK.SCHEDULE_SOURCE_ID.eq(id)).fetch());
    }

    @Override
    public Integer save(TTaskRecord obj) {
        getDslContext().attach(obj);
        if (obj.store() == 0) {
            log.error("Object didn't was saved {}", obj);
        }
        return obj.getId();
    }

    public void attach(TTaskRecord tTaskRecord) {
        getDslContext().attach(tTaskRecord);
    }

    public Result<TTaskRecord> getActive() {
        return getDslContext().selectFrom(T_TASK).where(T_TASK.FINISH_AT.isNull()).fetch();
    }

    public Result<TTaskRecord> getFinished() {
        return getDslContext().selectFrom(T_TASK).where(T_TASK.FINISH_AT.isNull()).and(T_TASK.STATUS.eq(FINISHED)).fetch();
    }

    public Result<TTaskRecord> getStarted() {
        return getDslContext().selectFrom(T_TASK).where(T_TASK.FINISH_AT.isNull()).and(T_TASK.STATUS.eq(RUNNING)).fetch();
    }

    public Result<TTaskRecord> getFinished(int limit) {
        return getDslContext().selectFrom(T_TASK).where(T_TASK.FINISH_AT.isNotNull()).and(T_TASK.STATUS.eq(FINISHED)).maxRows(limit).fetch();
    }
}
