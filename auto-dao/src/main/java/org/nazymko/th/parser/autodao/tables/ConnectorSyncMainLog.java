/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncMainLogRecord;

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
public class ConnectorSyncMainLog extends TableImpl<ConnectorSyncMainLogRecord> {

	/**
	 * The reference instance of <code>thehomeland.connector_sync_main_log</code>
	 */
	public static final ConnectorSyncMainLog CONNECTOR_SYNC_MAIN_LOG = new ConnectorSyncMainLog();
	private static final long serialVersionUID = -1631729626;
	/**
	 * The column <code>thehomeland.connector_sync_main_log.id</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_main_log.consumer</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, String> CONSUMER = createField("consumer", org.jooq.impl.SQLDataType.VARCHAR.length(1024).nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_main_log.sync_date</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, Timestamp> SYNC_DATE = createField("sync_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_main_log.count_new</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, Integer> COUNT_NEW = createField("count_new", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_main_log.count_total</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, Integer> COUNT_TOTAL = createField("count_total", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_main_log.latest_page_id</code>.
	 */
	public final TableField<ConnectorSyncMainLogRecord, Long> LATEST_PAGE_ID = createField("latest_page_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>thehomeland.connector_sync_main_log</code> table reference
	 */
	public ConnectorSyncMainLog() {
		this("connector_sync_main_log", null);
	}

	/**
	 * Create an aliased <code>thehomeland.connector_sync_main_log</code> table reference
	 */
	public ConnectorSyncMainLog(String alias) {
		this(alias, CONNECTOR_SYNC_MAIN_LOG);
	}

	private ConnectorSyncMainLog(String alias, Table<ConnectorSyncMainLogRecord> aliased) {
		this(alias, aliased, null);
	}

	private ConnectorSyncMainLog(String alias, Table<ConnectorSyncMainLogRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ConnectorSyncMainLogRecord> getRecordType() {
		return ConnectorSyncMainLogRecord.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ConnectorSyncMainLogRecord> getPrimaryKey() {
		return Keys.KEY_CONNECTOR_SYNC_MAIN_LOG_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ConnectorSyncMainLogRecord>> getKeys() {
		return Arrays.<UniqueKey<ConnectorSyncMainLogRecord>>asList(Keys.KEY_CONNECTOR_SYNC_MAIN_LOG_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncMainLog as(String alias) {
		return new ConnectorSyncMainLog(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ConnectorSyncMainLog rename(String name) {
		return new ConnectorSyncMainLog(name, null);
	}
}
