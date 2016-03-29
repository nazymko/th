/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.daos;


import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncMainLog;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncMainLogRecord;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ConnectorSyncMainLogDao extends DAOImpl<ConnectorSyncMainLogRecord, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog, Integer> {

	/**
	 * Create a new ConnectorSyncMainLogDao without any configuration
	 */
	public ConnectorSyncMainLogDao() {
		super(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog.class);
	}

	/**
	 * Create a new ConnectorSyncMainLogDao with an attached configuration
	 */
	public ConnectorSyncMainLogDao(Configuration configuration) {
		super(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG, org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchById(Integer... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog fetchOneById(Integer value) {
		return fetchOne(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.ID, value);
	}

	/**
	 * Fetch records that have <code>consumer_id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchByConsumerId(Integer... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.CONSUMER_ID, values);
	}

	/**
	 * Fetch records that have <code>sync_date IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchBySyncDate(Timestamp... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.SYNC_DATE, values);
	}

	/**
	 * Fetch records that have <code>count_new IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchByCountNew(Integer... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.COUNT_NEW, values);
	}

	/**
	 * Fetch records that have <code>count_total IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchByCountTotal(Integer... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.COUNT_TOTAL, values);
	}

	/**
	 * Fetch records that have <code>latest_page_id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ConnectorSyncMainLog> fetchByLatestPageId(Long... values) {
		return fetch(ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG.LATEST_PAGE_ID, values);
	}
}
