/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.ThRuleRecord;
import utils.support.rule.RuleStatus;
import utils.support.rule.RuleStatusConverter;

import javax.annotation.Generated;
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
public class ThRule extends TableImpl<ThRuleRecord> {

	/**
	 * The reference instance of <code>thehomeland.th_rule</code>
	 */
	public static final ThRule TH_RULE = new ThRule();
	private static final long serialVersionUID = 1294365993;
	/**
	 * The column <code>thehomeland.th_rule.id</code>.
	 */
	public final TableField<ThRuleRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.th_rule.rule</code>.
	 */
	public final TableField<ThRuleRecord, String> RULE = createField("rule", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");
	/**
	 * The column <code>thehomeland.th_rule.version</code>.
	 */
	public final TableField<ThRuleRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");
	/**
	 * The column <code>thehomeland.th_rule.status</code>.
	 */
	public final TableField<ThRuleRecord, RuleStatus> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER, this, "", new RuleStatusConverter());
	/**
	 * The column <code>thehomeland.th_rule.authority</code>.
	 */
	public final TableField<ThRuleRecord, String> AUTHORITY = createField("authority", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * Create a <code>thehomeland.th_rule</code> table reference
	 */
	public ThRule() {
		this("th_rule", null);
	}

	/**
	 * Create an aliased <code>thehomeland.th_rule</code> table reference
	 */
	public ThRule(String alias) {
		this(alias, TH_RULE);
	}

	private ThRule(String alias, Table<ThRuleRecord> aliased) {
		this(alias, aliased, null);
	}

	private ThRule(String alias, Table<ThRuleRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ThRuleRecord> getRecordType() {
		return ThRuleRecord.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<ThRuleRecord, Integer> getIdentity() {
		return Keys.IDENTITY_TH_RULE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ThRuleRecord> getPrimaryKey() {
		return Keys.KEY_TH_RULE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ThRuleRecord>> getKeys() {
		return Arrays.<UniqueKey<ThRuleRecord>>asList(Keys.KEY_TH_RULE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRule as(String alias) {
		return new ThRule(alias, this);
	}

	/**
	 * Rename this table
	 */
	public ThRule rename(String name) {
		return new ThRule(name, null);
	}
}
