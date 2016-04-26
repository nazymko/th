/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.records;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.nazymko.th.parser.autodao.tables.ThRule;

import utils.support.rule.RuleStatus;


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
@Entity
@Table(name = "th_rule", schema = "thehomeland")
public class ThRuleRecord extends UpdatableRecordImpl<ThRuleRecord> implements Record5<Integer, String, Integer, RuleStatus, String> {

	private static final long serialVersionUID = 173087809;

	/**
	 * Setter for <code>thehomeland.th_rule.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>thehomeland.th_rule.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>thehomeland.th_rule.rule</code>.
	 */
	public void setRule(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>thehomeland.th_rule.rule</code>.
	 */
	@Column(name = "rule", nullable = false)
	@NotNull
	public String getRule() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>thehomeland.th_rule.version</code>.
	 */
	public void setVersion(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>thehomeland.th_rule.version</code>.
	 */
	@Column(name = "version", nullable = false, precision = 10)
	@NotNull
	public Integer getVersion() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>thehomeland.th_rule.status</code>.
	 */
	public void setStatus(RuleStatus value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>thehomeland.th_rule.status</code>.
	 */
	@Column(name = "status", precision = 10)
	public RuleStatus getStatus() {
		return (RuleStatus) getValue(3);
	}

	/**
	 * Setter for <code>thehomeland.th_rule.authority</code>.
	 */
	public void setAuthority(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>thehomeland.th_rule.authority</code>.
	 */
	@Column(name = "authority", nullable = false)
	@NotNull
	public String getAuthority() {
		return (String) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, Integer, RuleStatus, String> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, Integer, RuleStatus, String> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ThRule.TH_RULE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return ThRule.TH_RULE.RULE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return ThRule.TH_RULE.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<RuleStatus> field4() {
		return ThRule.TH_RULE.STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return ThRule.TH_RULE.AUTHORITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getRule();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RuleStatus value4() {
		return getStatus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getAuthority();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord value2(String value) {
		setRule(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord value3(Integer value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord value4(RuleStatus value) {
		setStatus(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord value5(String value) {
		setAuthority(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThRuleRecord values(Integer value1, String value2, Integer value3, RuleStatus value4, String value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ThRuleRecord
	 */
	public ThRuleRecord() {
		super(ThRule.TH_RULE);
	}

	/**
	 * Create a detached, initialised ThRuleRecord
	 */
	public ThRuleRecord(Integer id, String rule, Integer version, RuleStatus status, String authority) {
		super(ThRule.TH_RULE);

		setValue(0, id);
		setValue(1, rule);
		setValue(2, version);
		setValue(3, status);
		setValue(4, authority);
	}
}
