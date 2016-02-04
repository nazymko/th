package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.TTaskRecord;

import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TTask.T_TASK;

/**
 * Created by nazymko
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
        if (obj.store() == 0) {
            log.error("Object didn't was saved {}", obj);
        }
        return obj.getId();
    }
}
