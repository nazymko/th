package org.nazymko.thehomeland.parser.db.dao;

import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.nazymko.th.parser.autodao.tables.TaskRun.TASK_RUN;
import static utils.support.task.TaskStatus.FINISHED;
import static utils.support.task.TaskStatus.RUNNING;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class TaskDao extends AbstractDao<Integer, TaskRunRecord> {


    @Resource
    SiteDao siteDao;

    @Override
    public Optional<TaskRunRecord> get(Integer key) {

        return Optional.ofNullable(getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.ID.eq(key)).fetchOne());

    }

    public Optional<List<TaskRunRecord>> getByScheduleId(Integer id) {
        return Optional.ofNullable(getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.SCHEDULE_SOURCE_ID.eq(id)).fetch());
    }

    @Override
    public Integer save(TaskRunRecord obj) {
        getDslContext().attach(obj);
        if (obj.store() == 0) {
            log.error("Object didn't was saved {}", obj);
        }
        return obj.getId();
    }

    public void attach(TaskRunRecord tTaskRecord) {
        getDslContext().attach(tTaskRecord);
    }

    public Result<TaskRunRecord> getActive() {
        return getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.FINISH_AT.isNull()).fetch();
    }

    public Result<TaskRunRecord> getFinished() {
        return getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.FINISH_AT.isNull()).and(TASK_RUN.STATUS.eq(FINISHED)).fetch();
    }

    public Result<TaskRunRecord> getStarted() {
        return getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.FINISH_AT.isNull()).and(TASK_RUN.STATUS.eq(RUNNING)).fetch();
    }

    public Result<TaskRunRecord> getFinished(int limit) {
        return getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.FINISH_AT.isNotNull()).and(TASK_RUN.STATUS.eq(FINISHED)).maxRows(limit).fetch();
    }

    public ThSiteRecord getSiteBySession(Integer sessionKey) {
        Integer siteId = getDslContext().selectFrom(TASK_RUN).where(TASK_RUN.ID.eq(sessionKey)).fetchOne().getSiteId();

        Optional<ThSiteRecord> siteRecord = siteDao.get(siteId);
        return siteRecord.get();
    }
}
