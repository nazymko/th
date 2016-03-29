/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;

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
public class ConnectorSyncPageLog extends TableImpl<ConnectorSyncPageLogRecord> {

	/**
	 * The reference instance of <code>thehomeland.connector_sync_page_log</code>
	 */
	public static final ConnectorSyncPageLog CONNECTOR_SYNC_PAGE_LOG = new ConnectorSyncPageLog();
	private static final long serialVersionUID = 1249045077;
	/**
	 * The column <code>thehomeland.connector_sync_page_log.id</code>.
	 */
	public final TableField<ConnectorSyncPageLogRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_page_log.page_id</code>.
	 */
	public final TableField<ConnectorSyncPageLogRecord, Integer> PAGE_ID = createField("page_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_page_log.consumer</code>.
	 */
	public final TableField<ConnectorSyncPageLogRecord, String> CONSUMER = createField("consumer", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");
	/**
	 * The column <code>thehomeland.connector_sync_page_log.time</code>.
	 */
	public final TableField<ConnectorSyncPageLogRecord, Timestamp> TIME = createField("time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * Create a <code>thehomeland.connector_sync_page_log</code> table reference
	 */
	public ConnectorSyncPageLog() {
		this("connector_sync_page_log", null);
	}

	/**
	 * Create an aliased <code>thehomeland.connector_sync_page_log</code> table reference
	 */
	public ConnectorSyncPageLog(String alias) {
		this(alias, CONNECTOR_SYNC_PAGE_LOG);
	}

	private ConnectorSyncPageLog(String alias, Table<ConnectorSyncPageLogRecord> aliased) {
		this(alias, aliased, null);
	}

	private ConnectorSyncPageLog(String alias, Table<ConnectorSyncPageLogRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ConnectorSyncPageLogRecord> getRecordType() {
		return ConnectorSyncPageLogRecord.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ConnectorSyncPageLogRecord> getPrimaryKey() {
		return Keys.KEY_CONNECTOR_SYNC_PAGE_LOG_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ConnectorSyncPageLogRecord>> getKeys() {
		return Arrays.<UniqueKey<ConnectorSyncPageLogRecord>>asList(Keys.KEY_CONNECTOR_SYNC_PAGE_LOG_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<ConnectorSyncPageLogRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<ConnectorSyncPageLogRecord, ?>>asList(Keys.CONNECTOR_SYNC_PAGE_LOG_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorSyncPageLog as(String alias) {
		return new ConnectorSyncPageLog(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ConnectorSyncPageLog rename(String name) {
		return new ConnectorSyncPageLog(name, null);
	}
}
