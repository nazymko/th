/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.ConnectorsSendHeadersRecord;


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
public class ConnectorsSendHeaders extends TableImpl<ConnectorsSendHeadersRecord> {

	private static final long serialVersionUID = -472487139;

	/**
	 * The reference instance of <code>thehomeland.connectors_send_headers</code>
	 */
	public static final ConnectorsSendHeaders CONNECTORS_SEND_HEADERS = new ConnectorsSendHeaders();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ConnectorsSendHeadersRecord> getRecordType() {
		return ConnectorsSendHeadersRecord.class;
	}

	/**
	 * The column <code>thehomeland.connectors_send_headers.id</code>.
	 */
	public final TableField<ConnectorsSendHeadersRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.connectors_send_headers.header</code>.
	 */
	public final TableField<ConnectorsSendHeadersRecord, String> HEADER = createField("header", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

	/**
	 * The column <code>thehomeland.connectors_send_headers.value</code>.
	 */
	public final TableField<ConnectorsSendHeadersRecord, String> VALUE = createField("value", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>thehomeland.connectors_send_headers.consumer_id</code>.
	 */
	public final TableField<ConnectorsSendHeadersRecord, Integer> CONSUMER_ID = createField("consumer_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>thehomeland.connectors_send_headers</code> table reference
	 */
	public ConnectorsSendHeaders() {
		this("connectors_send_headers", null);
	}

	/**
	 * Create an aliased <code>thehomeland.connectors_send_headers</code> table reference
	 */
	public ConnectorsSendHeaders(String alias) {
		this(alias, CONNECTORS_SEND_HEADERS);
	}

	private ConnectorsSendHeaders(String alias, Table<ConnectorsSendHeadersRecord> aliased) {
		this(alias, aliased, null);
	}

	private ConnectorsSendHeaders(String alias, Table<ConnectorsSendHeadersRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ConnectorsSendHeadersRecord, Integer> getIdentity() {
		return Keys.IDENTITY_CONNECTORS_SEND_HEADERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ConnectorsSendHeadersRecord> getPrimaryKey() {
		return Keys.KEY_CONNECTORS_SEND_HEADERS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ConnectorsSendHeadersRecord>> getKeys() {
		return Arrays.<UniqueKey<ConnectorsSendHeadersRecord>>asList(Keys.KEY_CONNECTORS_SEND_HEADERS_PRIMARY, Keys.KEY_CONNECTORS_SEND_HEADERS_CONNECTORS_SEND_HEADERS_ID_UINDEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<ConnectorsSendHeadersRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<ConnectorsSendHeadersRecord, ?>>asList(Keys.CONNECTORS_SEND_HEADERS_CONNECTOR_CONSUMER_ID_FK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorsSendHeaders as(String alias) {
		return new ConnectorsSendHeaders(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ConnectorsSendHeaders rename(String name) {
		return new ConnectorsSendHeaders(name, null);
	}
}
