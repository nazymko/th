/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.pojos;


import utils.support.runtype.RunType;
import utils.support.task.TaskStatus;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
@Table(name = "task_run", schema = "thehomeland")
public class TaskRun implements Serializable {

	private static final long serialVersionUID = 224900268;

	private Integer    id;
	private Timestamp  startAt;
	private Integer    scheduleSourceId;
	private TaskStatus status;
	private String     message;
	private Boolean    isEnabled;
	private Timestamp  finishAt;
	private RunType    runType;
	private Integer    siteId;

	public TaskRun() {}

	public TaskRun(TaskRun value) {
		this.id = value.id;
		this.startAt = value.startAt;
		this.scheduleSourceId = value.scheduleSourceId;
		this.status = value.status;
		this.message = value.message;
		this.isEnabled = value.isEnabled;
		this.finishAt = value.finishAt;
		this.runType = value.runType;
		this.siteId = value.siteId;
	}

	public TaskRun(
		Integer    id,
		Timestamp  startAt,
		Integer    scheduleSourceId,
		TaskStatus status,
		String     message,
		Boolean    isEnabled,
		Timestamp  finishAt,
		RunType    runType,
		Integer    siteId
	) {
		this.id = id;
		this.startAt = startAt;
		this.scheduleSourceId = scheduleSourceId;
		this.status = status;
		this.message = message;
		this.isEnabled = isEnabled;
		this.finishAt = finishAt;
		this.runType = runType;
		this.siteId = siteId;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "start_at", nullable = false)
	@NotNull
	public Timestamp getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Timestamp startAt) {
		this.startAt = startAt;
	}

	@Column(name = "schedule_source_id", precision = 10)
	public Integer getScheduleSourceId() {
		return this.scheduleSourceId;
	}

	public void setScheduleSourceId(Integer scheduleSourceId) {
		this.scheduleSourceId = scheduleSourceId;
	}

	@Column(name = "status", precision = 10)
	public TaskStatus getStatus() {
		return this.status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Column(name = "message")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "is_enabled")
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(name = "finish_at")
	public Timestamp getFinishAt() {
		return this.finishAt;
	}

	public void setFinishAt(Timestamp finishAt) {
		this.finishAt = finishAt;
	}

	@Column(name = "run_type", nullable = false, precision = 10)
	@NotNull
	public RunType getRunType() {
		return this.runType;
	}

	public void setRunType(RunType runType) {
		this.runType = runType;
	}

	@Column(name = "site_id", nullable = false, precision = 10)
	@NotNull
	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TaskRun (");

		sb.append(id);
		sb.append(", ").append(startAt);
		sb.append(", ").append(scheduleSourceId);
		sb.append(", ").append(status);
		sb.append(", ").append(message);
		sb.append(", ").append(isEnabled);
		sb.append(", ").append(finishAt);
		sb.append(", ").append(runType);
		sb.append(", ").append(siteId);

		sb.append(")");
		return sb.toString();
	}
}
