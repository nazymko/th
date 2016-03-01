/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.RuleRecord;

import utils.support.rule.RuleStatus;
import utils.support.rule.RuleStatusConverter;


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
public class Rule extends TableImpl<RuleRecord> {

	private static final long serialVersionUID = 123781347;

	/**
	 * The reference instance of <code>thehomeland.rule</code>
	 */
	public static final Rule RULE = new Rule();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RuleRecord> getRecordType() {
		return RuleRecord.class;
	}

	/**
	 * The column <code>thehomeland.rule.site</code>.
	 */
	public final TableField<RuleRecord, String> SITE = createField("site", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * The column <code>thehomeland.rule.rule</code>.
	 */
	public final TableField<RuleRecord, String> RULE_ = createField("rule", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.rule.version</code>.
	 */
	public final TableField<RuleRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>thehomeland.rule.id</code>.
	 */
	public final TableField<RuleRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.rule.status</code>.
	 */
	public final TableField<RuleRecord, RuleStatus> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER, this, "", new RuleStatusConverter());

	/**
	 * Create a <code>thehomeland.rule</code> table reference
	 */
	public Rule() {
		this("rule", null);
	}

	/**
	 * Create an aliased <code>thehomeland.rule</code> table reference
	 */
	public Rule(String alias) {
		this(alias, RULE);
	}

	private Rule(String alias, Table<RuleRecord> aliased) {
		this(alias, aliased, null);
	}

	private Rule(String alias, Table<RuleRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<RuleRecord, Integer> getIdentity() {
		return Keys.IDENTITY_RULE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RuleRecord> getPrimaryKey() {
		return Keys.KEY_RULE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RuleRecord>> getKeys() {
		return Arrays.<UniqueKey<RuleRecord>>asList(Keys.KEY_RULE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rule as(String alias) {
		return new Rule(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Rule rename(String name) {
		return new Rule(name, null);
	}
}
