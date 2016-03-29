/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.pojos;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Entity
@Table(name = "connector_sync_page_log", schema = "thehomeland")
public class ConnectorSyncPageLog implements Serializable {

	private static final long serialVersionUID = 1154100045;

	private Integer id;
	private Integer pageId;
	private String consumer;
	private Timestamp time;

	public ConnectorSyncPageLog() {
	}

	public ConnectorSyncPageLog(ConnectorSyncPageLog value) {
		this.id = value.id;
		this.pageId = value.pageId;
		this.consumer = value.consumer;
		this.time = value.time;
	}

	public ConnectorSyncPageLog(
			Integer id,
			Integer pageId,
			String consumer,
			Timestamp time
	) {
		this.id = id;
		this.pageId = pageId;
		this.consumer = consumer;
		this.time = time;
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

	@Column(name = "page_id", nullable = false, precision = 10)
	@NotNull
	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	@Column(name = "consumer", nullable = false, length = 128)
	@NotNull
	@Size(max = 128)
	public String getConsumer() {
		return this.consumer;
	}

	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}

	@Column(name = "time", nullable = false)
	@NotNull
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ConnectorSyncPageLog (");

		sb.append(id);
		sb.append(", ").append(pageId);
		sb.append(", ").append(consumer);
		sb.append(", ").append(time);

		sb.append(")");
		return sb.toString();
	}
}
