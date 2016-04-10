/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.daos;


import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;

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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ConnectorSyncPageLogDao extends DAOImpl<ConnectorSyncPageLogRecord, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog, Integer> {

	/**
	 * Create a new ConnectorSyncPageLogDao without any configuration
	 */
	public ConnectorSyncPageLogDao() {
		super(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog.class);
	}

	/**
	 * Create a new ConnectorSyncPageLogDao with an attached configuration
	 */
	public ConnectorSyncPageLogDao(Configuration configuration) {
		super(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog> fetchById(Integer... values) {
		return fetch(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog fetchOneById(Integer value) {
		return fetchOne(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.ID, value);
	}

	/**
	 * Fetch records that have <code>page_id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog> fetchByPageId(Integer... values) {
		return fetch(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.PAGE_ID, values);
	}

	/**
	 * Fetch records that have <code>consumer IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog> fetchByConsumer(String... values) {
		return fetch(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.CONSUMER, values);
	}

	/**
	 * Fetch records that have <code>time IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncPageLog> fetchByTime(Timestamp... values) {
		return fetch(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.TIME, values);
	}
}
