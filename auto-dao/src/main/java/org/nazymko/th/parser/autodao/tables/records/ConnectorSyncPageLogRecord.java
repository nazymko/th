/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog;


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
@Table(name = "connector_sync_page_log", schema = "thehomeland")
public class ConnectorSyncPageLogRecord extends UpdatableRecordImpl<ConnectorSyncPageLogRecord> implements Record6<Integer, Integer, String, Timestamp, String, Integer> {

	private static final long serialVersionUID = 287889248;

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.page_id</code>.
	 */
	public void setPageId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.page_id</code>.
	 */
	@Column(name = "page_id", nullable = false, precision = 10)
	@NotNull
	public Integer getPageId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.consumer</code>.
	 */
	public void setConsumer(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.consumer</code>.
	 */
	@Column(name = "consumer", nullable = false, length = 128)
	@NotNull
	@Size(max = 128)
	public String getConsumer() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.time</code>.
	 */
	public void setTime(Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.time</code>.
	 */
	@Column(name = "time", nullable = false)
	@NotNull
	public Timestamp getTime() {
		return (Timestamp) getValue(3);
	}

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.response_text</code>.
	 */
	public void setResponseText(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.response_text</code>.
	 */
	@Column(name = "response_text")
	public String getResponseText() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>thehomeland.connector_sync_page_log.response_code</code>.
	 */
	public void setResponseCode(Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>thehomeland.connector_sync_page_log.response_code</code>.
	 */
	@Column(name = "response_code", precision = 10)
	public Integer getResponseCode() {
		return (Integer) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, String, Timestamp, String, Integer> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, String, Timestamp, String, Integer> valuesRow() {
		return (Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.PAGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.CONSUMER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field4() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.RESPONSE_TEXT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.RESPONSE_CODE;
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
		return getPageId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getConsumer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value4() {
		return getTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getResponseText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getResponseCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value2(Integer value) {
		setPageId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value3(String value) {
		setConsumer(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value4(Timestamp value) {
		setTime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value5(String value) {
		setResponseText(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord value6(Integer value) {
		setResponseCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLogRecord values(Integer value1, Integer value2, String value3, Timestamp value4, String value5, Integer value6) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ConnectorSyncPageLogRecord
	 */
	public ConnectorSyncPageLogRecord() {
		super(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG);
	}

	/**
	 * Create a detached, initialised ConnectorSyncPageLogRecord
	 */
	public ConnectorSyncPageLogRecord(Integer id, Integer pageId, String consumer, Timestamp time, String responseText, Integer responseCode) {
		super(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG);

		setValue(0, id);
		setValue(1, pageId);
		setValue(2, consumer);
		setValue(3, time);
		setValue(4, responseText);
		setValue(5, responseCode);
	}
}
