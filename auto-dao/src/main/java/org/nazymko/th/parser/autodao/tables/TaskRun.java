/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import utils.support.runtype.RunType;
import utils.support.runtype.RunTypeConverter;
import utils.support.task.TaskStatus;
import utils.support.task.TaskStatusConverter;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.Arrays;
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
public class TaskRun extends TableImpl<TaskRunRecord> {

	/**
	 * The reference instance of <code>thehomeland.task_run</code>
	 */
	public static final TaskRun TASK_RUN = new TaskRun();
	private static final long serialVersionUID = -484429167;
	/**
	 * The column <code>thehomeland.task_run.id</code>.
	 */
	public final TableField<TaskRunRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.task_run.start_at</code>.
	 */
	public final TableField<TaskRunRecord, Timestamp> START_AT = createField("start_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.task_run.schedule_source_id</code>.
	 */
	public final TableField<TaskRunRecord, Integer> SCHEDULE_SOURCE_ID = createField("schedule_source_id", org.jooq.impl.SQLDataType.INTEGER, this, "");
	/**
	 * The column <code>thehomeland.task_run.status</code>.
	 */
	public final TableField<TaskRunRecord, TaskStatus> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER, this, "", new TaskStatusConverter());
	/**
	 * The column <code>thehomeland.task_run.message</code>.
	 */
	public final TableField<TaskRunRecord, String> MESSAGE = createField("message", org.jooq.impl.SQLDataType.CLOB, this, "");
	/**
	 * The column <code>thehomeland.task_run.is_enabled</code>.
	 */
	public final TableField<TaskRunRecord, Boolean> IS_ENABLED = createField("is_enabled", org.jooq.impl.SQLDataType.BOOLEAN, this, "");
	/**
	 * The column <code>thehomeland.task_run.finish_at</code>.
	 */
	public final TableField<TaskRunRecord, Timestamp> FINISH_AT = createField("finish_at", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");
	/**
	 * The column <code>thehomeland.task_run.run_type</code>.
	 */
	public final TableField<TaskRunRecord, RunType> RUN_TYPE = createField("run_type", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "", new RunTypeConverter());
	/**
	 * The column <code>thehomeland.task_run.site_id</code>.
	 */
	public final TableField<TaskRunRecord, Integer> SITE_ID = createField("site_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>thehomeland.task_run</code> table reference
	 */
	public TaskRun() {
		this("task_run", null);
	}

	/**
	 * Create an aliased <code>thehomeland.task_run</code> table reference
	 */
	public TaskRun(String alias) {
		this(alias, TASK_RUN);
	}

	private TaskRun(String alias, Table<TaskRunRecord> aliased) {
		this(alias, aliased, null);
	}

	private TaskRun(String alias, Table<TaskRunRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TaskRunRecord> getRecordType() {
		return TaskRunRecord.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<TaskRunRecord, Integer> getIdentity() {
		return Keys.IDENTITY_TASK_RUN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TaskRunRecord> getPrimaryKey() {
		return Keys.KEY_TASK_RUN_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TaskRunRecord>> getKeys() {
		return Arrays.<UniqueKey<TaskRunRecord>>asList(Keys.KEY_TASK_RUN_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<TaskRunRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<TaskRunRecord, ?>>asList(Keys.TASK_RUN_IBFK_1, Keys.TASK_RUN_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskRun as(String alias) {
		return new TaskRun(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TaskRun rename(String name) {
		return new TaskRun(name, null);
	}
}
