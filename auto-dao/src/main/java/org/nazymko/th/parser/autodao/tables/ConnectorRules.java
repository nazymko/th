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
import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;


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
public class ConnectorRules extends TableImpl<ConnectorRulesRecord> {

	private static final long serialVersionUID = -1991798166;

	/**
	 * The reference instance of <code>thehomeland.connector_rules</code>
	 */
	public static final ConnectorRules CONNECTOR_RULES = new ConnectorRules();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ConnectorRulesRecord> getRecordType() {
		return ConnectorRulesRecord.class;
	}

	/**
	 * The column <code>thehomeland.connector_rules.id</code>.
	 */
	public final TableField<ConnectorRulesRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.connector_rules.rule</code>.
	 */
	public final TableField<ConnectorRulesRecord, String> RULE = createField("rule", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>thehomeland.connector_rules.consumer_id</code>.
	 */
	public final TableField<ConnectorRulesRecord, Integer> CONSUMER_ID = createField("consumer_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>thehomeland.connector_rules.site_id</code>.
	 */
	public final TableField<ConnectorRulesRecord, Integer> SITE_ID = createField("site_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>thehomeland.connector_rules</code> table reference
	 */
	public ConnectorRules() {
		this("connector_rules", null);
	}

	/**
	 * Create an aliased <code>thehomeland.connector_rules</code> table reference
	 */
	public ConnectorRules(String alias) {
		this(alias, CONNECTOR_RULES);
	}

	private ConnectorRules(String alias, Table<ConnectorRulesRecord> aliased) {
		this(alias, aliased, null);
	}

	private ConnectorRules(String alias, Table<ConnectorRulesRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ConnectorRulesRecord, Integer> getIdentity() {
		return Keys.IDENTITY_CONNECTOR_RULES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ConnectorRulesRecord> getPrimaryKey() {
		return Keys.KEY_CONNECTOR_RULES_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ConnectorRulesRecord>> getKeys() {
		return Arrays.<UniqueKey<ConnectorRulesRecord>>asList(Keys.KEY_CONNECTOR_RULES_PRIMARY, Keys.KEY_CONNECTOR_RULES_CONNECTOR_RULES_ID_UINDEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<ConnectorRulesRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<ConnectorRulesRecord, ?>>asList(Keys.CONNECTOR_RULES_IBFK_1, Keys.CONNECTOR_RULES_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectorRules as(String alias) {
		return new ConnectorRules(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ConnectorRules rename(String name) {
		return new ConnectorRules(name, null);
	}
}
