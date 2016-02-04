package org.nazymko.thehomeland.parser.db.dao;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;
import org.nazymko.thehomeland.parser.db.model.Schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TSchedule.T_SCHEDULE;
import static org.nazymko.th.parser.autodao.tables.TTask.T_TASK;

/**
 * Created by nazymko
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
        return obj.store();
    }

    public List<TScheduleRecord> getSchedules(Integer siteId) {
        return getDslContext().selectFrom(T_SCHEDULE).where(T_SCHEDULE.SITEID.eq(siteId)).fetch();

    }
}
