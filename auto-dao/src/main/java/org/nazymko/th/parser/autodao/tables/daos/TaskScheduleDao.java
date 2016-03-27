/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.daos;


import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.nazymko.th.parser.autodao.tables.TaskSchedule;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.7.2"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TaskScheduleDao extends DAOImpl<TaskScheduleRecord, org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule, Integer> {

    /**
     * Create a new TaskScheduleDao without any configuration
     */
    public TaskScheduleDao() {
        super(TaskSchedule.TASK_SCHEDULE, org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule.class);
    }

    /**
     * Create a new TaskScheduleDao with an attached configuration
     */
    public TaskScheduleDao(Configuration configuration) {
        super(TaskSchedule.TASK_SCHEDULE, org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchById(Integer... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule fetchOneById(Integer value) {
        return fetchOne(TaskSchedule.TASK_SCHEDULE.ID, value);
    }

    /**
     * Fetch records that have <code>siteId IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchBySiteid(Integer... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.SITEID, values);
    }

    /**
     * Fetch records that have <code>start_page IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchByStartPage(String... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.START_PAGE, values);
    }

    /**
     * Fetch records that have <code>page_type IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchByPageType(String... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.PAGE_TYPE, values);
    }

    /**
     * Fetch records that have <code>start_at IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchByStartAt(Timestamp... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.START_AT, values);
    }

    /**
     * Fetch records that have <code>cron IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchByCron(String... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.CRON, values);
    }

    /**
     * Fetch records that have <code>is_enabled IN (values)</code>
     */
    public List<org.nazymko.th.parser.autodao.tables.pojos.TaskSchedule> fetchByIsEnabled(Boolean... values) {
        return fetch(TaskSchedule.TASK_SCHEDULE.IS_ENABLED, values);
    }
}
