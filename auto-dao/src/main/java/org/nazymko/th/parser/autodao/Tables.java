/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao;


import javax.annotation.Generated;

import org.nazymko.th.parser.autodao.tables.ConnectorConsumer;
import org.nazymko.th.parser.autodao.tables.ConnectorRules;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncMainLog;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog;
import org.nazymko.th.parser.autodao.tables.TaskRun;
import org.nazymko.th.parser.autodao.tables.TaskSchedule;
import org.nazymko.th.parser.autodao.tables.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.ThPage;
import org.nazymko.th.parser.autodao.tables.ThRule;
import org.nazymko.th.parser.autodao.tables.ThSite;


/**
 * Convenience access to all tables in thehomeland
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table thehomeland.connector_consumer
	 */
	public static final ConnectorConsumer CONNECTOR_CONSUMER = org.nazymko.th.parser.autodao.tables.ConnectorConsumer.CONNECTOR_CONSUMER;

	/**
	 * The table thehomeland.connector_rules
	 */
	public static final ConnectorRules CONNECTOR_RULES = org.nazymko.th.parser.autodao.tables.ConnectorRules.CONNECTOR_RULES;

	/**
	 * The table thehomeland.connector_sync_main_log
	 */
	public static final ConnectorSyncMainLog CONNECTOR_SYNC_MAIN_LOG = org.nazymko.th.parser.autodao.tables.ConnectorSyncMainLog.CONNECTOR_SYNC_MAIN_LOG;

	/**
	 * The table thehomeland.connector_sync_page_log
	 */
	public static final ConnectorSyncPageLog CONNECTOR_SYNC_PAGE_LOG = org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG;

	/**
	 * The table thehomeland.task_run
	 */
	public static final TaskRun TASK_RUN = org.nazymko.th.parser.autodao.tables.TaskRun.TASK_RUN;

	/**
	 * The table thehomeland.task_schedule
	 */
	public static final TaskSchedule TASK_SCHEDULE = org.nazymko.th.parser.autodao.tables.TaskSchedule.TASK_SCHEDULE;

	/**
	 * The table thehomeland.th_attribute_data
	 */
	public static final ThAttributeData TH_ATTRIBUTE_DATA = org.nazymko.th.parser.autodao.tables.ThAttributeData.TH_ATTRIBUTE_DATA;

	/**
	 * The table thehomeland.th_page
	 */
	public static final ThPage TH_PAGE = org.nazymko.th.parser.autodao.tables.ThPage.TH_PAGE;

	/**
	 * The table thehomeland.th_rule
	 */
	public static final ThRule TH_RULE = org.nazymko.th.parser.autodao.tables.ThRule.TH_RULE;

	/**
	 * The table thehomeland.th_site
	 */
	public static final ThSite TH_SITE = org.nazymko.th.parser.autodao.tables.ThSite.TH_SITE;
}
