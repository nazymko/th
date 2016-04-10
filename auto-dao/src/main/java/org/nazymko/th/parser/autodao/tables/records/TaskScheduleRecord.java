/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.nazymko.th.parser.autodao.tables.TaskSchedule;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "task_schedule", schema = "thehomeland")
public class TaskScheduleRecord extends UpdatableRecordImpl<TaskScheduleRecord> implements Record7<Integer, Integer, String, String, Timestamp, String, Boolean> {

	private static final long serialVersionUID = -409667150;

	/**
	 * Create a detached TaskScheduleRecord
	 */
	public TaskScheduleRecord() {
		super(TaskSchedule.TASK_SCHEDULE);
	}

	/**
	 * Create a detached, initialised TaskScheduleRecord
	 */
	public TaskScheduleRecord(Integer id, Integer siteId, String startPage, String pageType, Timestamp startAt, String cron, Boolean isEnabled) {
		super(TaskSchedule.TASK_SCHEDULE);

		setValue(0, id);
		setValue(1, siteId);
		setValue(2, startPage);
		setValue(3, pageType);
		setValue(4, startAt);
		setValue(5, cron);
		setValue(6, isEnabled);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.site_id</code>.
	 */
	@Column(name = "site_id", precision = 10)
	public Integer getSiteId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.site_id</code>.
	 */
	public void setSiteId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.start_page</code>.
	 */
	@Column(name = "start_page", nullable = false, length = 256)
	@NotNull
	@Size(max = 256)
	public String getStartPage() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.start_page</code>.
	 */
	public void setStartPage(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.page_type</code>.
	 */
	@Column(name = "page_type", nullable = false, length = 64)
	@NotNull
	@Size(max = 64)
	public String getPageType() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.page_type</code>.
	 */
	public void setPageType(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.start_at</code>.
	 */
	@Column(name = "start_at", nullable = false)
	@NotNull
	public Timestamp getStartAt() {
		return (Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.start_at</code>.
	 */
	public void setStartAt(Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>thehomeland.task_schedule.cron</code>.
	 */
	@Column(name = "cron", length = 64)
	@Size(max = 64)
	public String getCron() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>thehomeland.task_schedule.cron</code>.
	 */
	public void setCron(String value) {
		setValue(5, value);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * Getter for <code>thehomeland.task_schedule.is_enabled</code>.
	 */
	@Column(name = "is_enabled")
	public Boolean getIsEnabled() {
		return (Boolean) getValue(6);
	}

	// -------------------------------------------------------------------------
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * Setter for <code>thehomeland.task_schedule.is_enabled</code>.
	 */
	public void setIsEnabled(Boolean value) {
		setValue(6, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, String, String, Timestamp, String, Boolean> fieldsRow() {
		return (Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, String, String, Timestamp, String, Boolean> valuesRow() {
		return (Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return TaskSchedule.TASK_SCHEDULE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return TaskSchedule.TASK_SCHEDULE.SITE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return TaskSchedule.TASK_SCHEDULE.START_PAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return TaskSchedule.TASK_SCHEDULE.PAGE_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field5() {
		return TaskSchedule.TASK_SCHEDULE.START_AT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return TaskSchedule.TASK_SCHEDULE.CRON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field7() {
		return TaskSchedule.TASK_SCHEDULE.IS_ENABLED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getSiteId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getStartPage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getPageType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value5() {
		return getStartAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getCron();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value7() {
		return getIsEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value2(Integer value) {
		setSiteId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value3(String value) {
		setStartPage(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value4(String value) {
		setPageType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value5(Timestamp value) {
		setStartAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value6(String value) {
		setCron(value);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord value7(Boolean value) {
		setIsEnabled(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskScheduleRecord values(Integer value1, Integer value2, String value3, String value4, Timestamp value5, String value6, Boolean value7) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		return this;
	}
}
