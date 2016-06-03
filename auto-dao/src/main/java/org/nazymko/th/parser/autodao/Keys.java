/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.nazymko.th.parser.autodao.tables.ConnectorConsumer;
import org.nazymko.th.parser.autodao.tables.ConnectorRules;
import org.nazymko.th.parser.autodao.tables.ConnectorSyncPageLog;
import org.nazymko.th.parser.autodao.tables.ConnectorsSendHeaders;
import org.nazymko.th.parser.autodao.tables.SchemaVersion;
import org.nazymko.th.parser.autodao.tables.TaskRun;
import org.nazymko.th.parser.autodao.tables.TaskSchedule;
import org.nazymko.th.parser.autodao.tables.ThAttributeData;
import org.nazymko.th.parser.autodao.tables.ThPage;
import org.nazymko.th.parser.autodao.tables.ThRule;
import org.nazymko.th.parser.autodao.tables.ThSite;
import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;
import org.nazymko.th.parser.autodao.tables.records.ConnectorsSendHeadersRecord;
import org.nazymko.th.parser.autodao.tables.records.SchemaVersionRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskRunRecord;
import org.nazymko.th.parser.autodao.tables.records.TaskScheduleRecord;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;


/**
 * A class modelling foreign key relationships between tables of the <code>thehomeland</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<ConnectorsSendHeadersRecord, Integer> IDENTITY_CONNECTORS_SEND_HEADERS = Identities0.IDENTITY_CONNECTORS_SEND_HEADERS;
	public static final Identity<ConnectorConsumerRecord, Integer> IDENTITY_CONNECTOR_CONSUMER = Identities0.IDENTITY_CONNECTOR_CONSUMER;
	public static final Identity<ConnectorRulesRecord, Integer> IDENTITY_CONNECTOR_RULES = Identities0.IDENTITY_CONNECTOR_RULES;
	public static final Identity<ConnectorSyncPageLogRecord, Integer> IDENTITY_CONNECTOR_SYNC_PAGE_LOG = Identities0.IDENTITY_CONNECTOR_SYNC_PAGE_LOG;
	public static final Identity<TaskRunRecord, Integer> IDENTITY_TASK_RUN = Identities0.IDENTITY_TASK_RUN;
	public static final Identity<TaskScheduleRecord, Integer> IDENTITY_TASK_SCHEDULE = Identities0.IDENTITY_TASK_SCHEDULE;
	public static final Identity<ThAttributeDataRecord, Integer> IDENTITY_TH_ATTRIBUTE_DATA = Identities0.IDENTITY_TH_ATTRIBUTE_DATA;
	public static final Identity<ThPageRecord, Integer> IDENTITY_TH_PAGE = Identities0.IDENTITY_TH_PAGE;
	public static final Identity<ThRuleRecord, Integer> IDENTITY_TH_RULE = Identities0.IDENTITY_TH_RULE;
	public static final Identity<ThSiteRecord, Integer> IDENTITY_TH_SITE = Identities0.IDENTITY_TH_SITE;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<ConnectorsSendHeadersRecord> KEY_CONNECTORS_SEND_HEADERS_PRIMARY = UniqueKeys0.KEY_CONNECTORS_SEND_HEADERS_PRIMARY;
	public static final UniqueKey<ConnectorsSendHeadersRecord> KEY_CONNECTORS_SEND_HEADERS_CONNECTORS_SEND_HEADERS_ID_UINDEX = UniqueKeys0.KEY_CONNECTORS_SEND_HEADERS_CONNECTORS_SEND_HEADERS_ID_UINDEX;
	public static final UniqueKey<ConnectorConsumerRecord> KEY_CONNECTOR_CONSUMER_PRIMARY = UniqueKeys0.KEY_CONNECTOR_CONSUMER_PRIMARY;
	public static final UniqueKey<ConnectorConsumerRecord> KEY_CONNECTOR_CONSUMER_UNIQUE_ID = UniqueKeys0.KEY_CONNECTOR_CONSUMER_UNIQUE_ID;
	public static final UniqueKey<ConnectorRulesRecord> KEY_CONNECTOR_RULES_PRIMARY = UniqueKeys0.KEY_CONNECTOR_RULES_PRIMARY;
	public static final UniqueKey<ConnectorRulesRecord> KEY_CONNECTOR_RULES_CONNECTOR_RULES_ID_UINDEX = UniqueKeys0.KEY_CONNECTOR_RULES_CONNECTOR_RULES_ID_UINDEX;
	public static final UniqueKey<ConnectorSyncPageLogRecord> KEY_CONNECTOR_SYNC_PAGE_LOG_PRIMARY = UniqueKeys0.KEY_CONNECTOR_SYNC_PAGE_LOG_PRIMARY;
	public static final UniqueKey<SchemaVersionRecord> KEY_SCHEMA_VERSION_PRIMARY = UniqueKeys0.KEY_SCHEMA_VERSION_PRIMARY;
	public static final UniqueKey<TaskRunRecord> KEY_TASK_RUN_PRIMARY = UniqueKeys0.KEY_TASK_RUN_PRIMARY;
	public static final UniqueKey<TaskScheduleRecord> KEY_TASK_SCHEDULE_PRIMARY = UniqueKeys0.KEY_TASK_SCHEDULE_PRIMARY;
	public static final UniqueKey<ThAttributeDataRecord> KEY_TH_ATTRIBUTE_DATA_PRIMARY = UniqueKeys0.KEY_TH_ATTRIBUTE_DATA_PRIMARY;
	public static final UniqueKey<ThAttributeDataRecord> KEY_TH_ATTRIBUTE_DATA_UNIQUE_ID = UniqueKeys0.KEY_TH_ATTRIBUTE_DATA_UNIQUE_ID;
	public static final UniqueKey<ThPageRecord> KEY_TH_PAGE_PRIMARY = UniqueKeys0.KEY_TH_PAGE_PRIMARY;
	public static final UniqueKey<ThRuleRecord> KEY_TH_RULE_PRIMARY = UniqueKeys0.KEY_TH_RULE_PRIMARY;
	public static final UniqueKey<ThSiteRecord> KEY_TH_SITE_PRIMARY = UniqueKeys0.KEY_TH_SITE_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final ForeignKey<ConnectorsSendHeadersRecord, ConnectorConsumerRecord> CONNECTORS_SEND_HEADERS_CONNECTOR_CONSUMER_ID_FK = ForeignKeys0.CONNECTORS_SEND_HEADERS_CONNECTOR_CONSUMER_ID_FK;
	public static final ForeignKey<ConnectorRulesRecord, ConnectorConsumerRecord> CONNECTOR_RULES_IBFK_1 = ForeignKeys0.CONNECTOR_RULES_IBFK_1;
	public static final ForeignKey<ConnectorSyncPageLogRecord, ThPageRecord> CONNECTOR_SYNC_PAGE_LOG_IBFK_1 = ForeignKeys0.CONNECTOR_SYNC_PAGE_LOG_IBFK_1;
	public static final ForeignKey<ThAttributeDataRecord, ThPageRecord> TH_ATTRIBUTE_DATA_IBFK_3 = ForeignKeys0.TH_ATTRIBUTE_DATA_IBFK_3;
	public static final ForeignKey<ThPageRecord, TaskRunRecord> TH_PAGE_IBFK_2 = ForeignKeys0.TH_PAGE_IBFK_2;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<ConnectorsSendHeadersRecord, Integer> IDENTITY_CONNECTORS_SEND_HEADERS = createIdentity(ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS, ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS.ID);
		public static Identity<ConnectorConsumerRecord, Integer> IDENTITY_CONNECTOR_CONSUMER = createIdentity(ConnectorConsumer.CONNECTOR_CONSUMER, ConnectorConsumer.CONNECTOR_CONSUMER.ID);
		public static Identity<ConnectorRulesRecord, Integer> IDENTITY_CONNECTOR_RULES = createIdentity(ConnectorRules.CONNECTOR_RULES, ConnectorRules.CONNECTOR_RULES.ID);
		public static Identity<ConnectorSyncPageLogRecord, Integer> IDENTITY_CONNECTOR_SYNC_PAGE_LOG = createIdentity(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG, ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.ID);
		public static Identity<TaskRunRecord, Integer> IDENTITY_TASK_RUN = createIdentity(TaskRun.TASK_RUN, TaskRun.TASK_RUN.ID);
		public static Identity<TaskScheduleRecord, Integer> IDENTITY_TASK_SCHEDULE = createIdentity(TaskSchedule.TASK_SCHEDULE, TaskSchedule.TASK_SCHEDULE.ID);
		public static Identity<ThAttributeDataRecord, Integer> IDENTITY_TH_ATTRIBUTE_DATA = createIdentity(ThAttributeData.TH_ATTRIBUTE_DATA, ThAttributeData.TH_ATTRIBUTE_DATA.ID);
		public static Identity<ThPageRecord, Integer> IDENTITY_TH_PAGE = createIdentity(ThPage.TH_PAGE, ThPage.TH_PAGE.ID);
		public static Identity<ThRuleRecord, Integer> IDENTITY_TH_RULE = createIdentity(ThRule.TH_RULE, ThRule.TH_RULE.ID);
		public static Identity<ThSiteRecord, Integer> IDENTITY_TH_SITE = createIdentity(ThSite.TH_SITE, ThSite.TH_SITE.ID);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<ConnectorsSendHeadersRecord> KEY_CONNECTORS_SEND_HEADERS_PRIMARY = createUniqueKey(ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS, ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS.ID);
		public static final UniqueKey<ConnectorsSendHeadersRecord> KEY_CONNECTORS_SEND_HEADERS_CONNECTORS_SEND_HEADERS_ID_UINDEX = createUniqueKey(ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS, ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS.ID);
		public static final UniqueKey<ConnectorConsumerRecord> KEY_CONNECTOR_CONSUMER_PRIMARY = createUniqueKey(ConnectorConsumer.CONNECTOR_CONSUMER, ConnectorConsumer.CONNECTOR_CONSUMER.ID);
		public static final UniqueKey<ConnectorConsumerRecord> KEY_CONNECTOR_CONSUMER_UNIQUE_ID = createUniqueKey(ConnectorConsumer.CONNECTOR_CONSUMER, ConnectorConsumer.CONNECTOR_CONSUMER.ID);
		public static final UniqueKey<ConnectorRulesRecord> KEY_CONNECTOR_RULES_PRIMARY = createUniqueKey(ConnectorRules.CONNECTOR_RULES, ConnectorRules.CONNECTOR_RULES.ID);
		public static final UniqueKey<ConnectorRulesRecord> KEY_CONNECTOR_RULES_CONNECTOR_RULES_ID_UINDEX = createUniqueKey(ConnectorRules.CONNECTOR_RULES, ConnectorRules.CONNECTOR_RULES.ID);
		public static final UniqueKey<ConnectorSyncPageLogRecord> KEY_CONNECTOR_SYNC_PAGE_LOG_PRIMARY = createUniqueKey(ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG, ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.ID);
		public static final UniqueKey<SchemaVersionRecord> KEY_SCHEMA_VERSION_PRIMARY = createUniqueKey(SchemaVersion.SCHEMA_VERSION, SchemaVersion.SCHEMA_VERSION.INSTALLED_RANK);
		public static final UniqueKey<TaskRunRecord> KEY_TASK_RUN_PRIMARY = createUniqueKey(TaskRun.TASK_RUN, TaskRun.TASK_RUN.ID);
		public static final UniqueKey<TaskScheduleRecord> KEY_TASK_SCHEDULE_PRIMARY = createUniqueKey(TaskSchedule.TASK_SCHEDULE, TaskSchedule.TASK_SCHEDULE.ID);
		public static final UniqueKey<ThAttributeDataRecord> KEY_TH_ATTRIBUTE_DATA_PRIMARY = createUniqueKey(ThAttributeData.TH_ATTRIBUTE_DATA, ThAttributeData.TH_ATTRIBUTE_DATA.ID);
		public static final UniqueKey<ThAttributeDataRecord> KEY_TH_ATTRIBUTE_DATA_UNIQUE_ID = createUniqueKey(ThAttributeData.TH_ATTRIBUTE_DATA, ThAttributeData.TH_ATTRIBUTE_DATA.ID);
		public static final UniqueKey<ThPageRecord> KEY_TH_PAGE_PRIMARY = createUniqueKey(ThPage.TH_PAGE, ThPage.TH_PAGE.ID);
		public static final UniqueKey<ThRuleRecord> KEY_TH_RULE_PRIMARY = createUniqueKey(ThRule.TH_RULE, ThRule.TH_RULE.ID);
		public static final UniqueKey<ThSiteRecord> KEY_TH_SITE_PRIMARY = createUniqueKey(ThSite.TH_SITE, ThSite.TH_SITE.ID);
	}

	private static class ForeignKeys0 extends AbstractKeys {
		public static final ForeignKey<ConnectorsSendHeadersRecord, ConnectorConsumerRecord> CONNECTORS_SEND_HEADERS_CONNECTOR_CONSUMER_ID_FK = createForeignKey(org.nazymko.th.parser.autodao.Keys.KEY_CONNECTOR_CONSUMER_PRIMARY, ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS, ConnectorsSendHeaders.CONNECTORS_SEND_HEADERS.CONSUMER_ID);
		public static final ForeignKey<ConnectorRulesRecord, ConnectorConsumerRecord> CONNECTOR_RULES_IBFK_1 = createForeignKey(org.nazymko.th.parser.autodao.Keys.KEY_CONNECTOR_CONSUMER_PRIMARY, ConnectorRules.CONNECTOR_RULES, ConnectorRules.CONNECTOR_RULES.CONSUMER_ID);
		public static final ForeignKey<ConnectorSyncPageLogRecord, ThPageRecord> CONNECTOR_SYNC_PAGE_LOG_IBFK_1 = createForeignKey(org.nazymko.th.parser.autodao.Keys.KEY_TH_PAGE_PRIMARY, ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG, ConnectorSyncPageLog.CONNECTOR_SYNC_PAGE_LOG.PAGE_ID);
		public static final ForeignKey<ThAttributeDataRecord, ThPageRecord> TH_ATTRIBUTE_DATA_IBFK_3 = createForeignKey(org.nazymko.th.parser.autodao.Keys.KEY_TH_PAGE_PRIMARY, ThAttributeData.TH_ATTRIBUTE_DATA, ThAttributeData.TH_ATTRIBUTE_DATA.PAGE_ID);
		public static final ForeignKey<ThPageRecord, TaskRunRecord> TH_PAGE_IBFK_2 = createForeignKey(org.nazymko.th.parser.autodao.Keys.KEY_TASK_RUN_PRIMARY, ThPage.TH_PAGE, ThPage.TH_PAGE.TASK_RUN_ID);
	}
}
