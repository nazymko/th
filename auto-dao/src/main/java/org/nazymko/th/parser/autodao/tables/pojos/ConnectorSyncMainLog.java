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
@Table(name = "connector_sync_main_log", schema = "thehomeland")
public class ConnectorSyncMainLog implements Serializable {

	private static final long serialVersionUID = -733524966;

	private Integer id;
	private Integer consumerId;
	private Timestamp syncDate;
	private Integer countNew;
	private Integer countTotal;
	private Long latestPageId;

	public ConnectorSyncMainLog() {
	}

	public ConnectorSyncMainLog(ConnectorSyncMainLog value) {
		this.id = value.id;
		this.consumerId = value.consumerId;
		this.syncDate = value.syncDate;
		this.countNew = value.countNew;
		this.countTotal = value.countTotal;
		this.latestPageId = value.latestPageId;
	}

	public ConnectorSyncMainLog(
			Integer id,
			Integer consumerId,
			Timestamp syncDate,
			Integer countNew,
			Integer countTotal,
			Long latestPageId
	) {
		this.id = id;
		this.consumerId = consumerId;
		this.syncDate = syncDate;
		this.countNew = countNew;
		this.countTotal = countTotal;
		this.latestPageId = latestPageId;
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

	@Column(name = "consumer_id", precision = 10)
	public Integer getConsumerId() {
		return this.consumerId;
	}

	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	@Column(name = "sync_date", nullable = false)
	@NotNull
	public Timestamp getSyncDate() {
		return this.syncDate;
	}

	public void setSyncDate(Timestamp syncDate) {
		this.syncDate = syncDate;
	}

	@Column(name = "count_new", nullable = false, precision = 10)
	@NotNull
	public Integer getCountNew() {
		return this.countNew;
	}

	public void setCountNew(Integer countNew) {
		this.countNew = countNew;
	}

	@Column(name = "count_total", nullable = false, precision = 10)
	@NotNull
	public Integer getCountTotal() {
		return this.countTotal;
	}

	public void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
	}

	@Column(name = "latest_page_id", precision = 19)
	public Long getLatestPageId() {
		return this.latestPageId;
	}

	public void setLatestPageId(Long latestPageId) {
		this.latestPageId = latestPageId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ConnectorSyncMainLog (");

		sb.append(id);
		sb.append(", ").append(consumerId);
		sb.append(", ").append(syncDate);
		sb.append(", ").append(countNew);
		sb.append(", ").append(countTotal);
		sb.append(", ").append(latestPageId);

		sb.append(")");
		return sb.toString();
	}
}
