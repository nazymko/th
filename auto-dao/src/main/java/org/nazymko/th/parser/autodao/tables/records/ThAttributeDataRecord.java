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
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import org.nazymko.th.parser.autodao.tables.ThAttributeData;


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
@Table(name = "th_attribute_data", schema = "thehomeland")
public class ThAttributeDataRecord extends UpdatableRecordImpl<ThAttributeDataRecord> implements Record9<Integer, Integer, Integer, String, String, Integer, String, String, Integer> {

	private static final long serialVersionUID = -1150623274;

	/**
	 * Setter for <code>thehomeland.th_attribute_data.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.site_id</code>.
	 */
	public void setSiteId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.site_id</code>.
	 */
	@Column(name = "site_id", precision = 10)
	public Integer getSiteId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.page_id</code>.
	 */
	public void setPageId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.page_id</code>.
	 */
	@Column(name = "page_id", precision = 10)
	public Integer getPageId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.attribute_name</code>.
	 */
	public void setAttributeName(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.attribute_name</code>.
	 */
	@Column(name = "attribute_name", nullable = false, length = 64)
	@NotNull
	@Size(max = 64)
	public String getAttributeName() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.attribute_value</code>.
	 */
	public void setAttributeValue(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.attribute_value</code>.
	 */
	@Column(name = "attribute_value")
	public String getAttributeValue() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.attribute_index</code>.
	 */
	public void setAttributeIndex(Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.attribute_index</code>.
	 */
	@Column(name = "attribute_index", precision = 10)
	public Integer getAttributeIndex() {
		return (Integer) getValue(5);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.attribute_type</code>.
	 */
	public void setAttributeType(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.attribute_type</code>.
	 */
	@Column(name = "attribute_type", length = 32)
	@Size(max = 32)
	public String getAttributeType() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.attribute_format</code>.
	 */
	public void setAttributeFormat(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.attribute_format</code>.
	 */
	@Column(name = "attribute_format", length = 256)
	@Size(max = 256)
	public String getAttributeFormat() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>thehomeland.th_attribute_data.rule_id</code>.
	 */
	public void setRuleId(Integer value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>thehomeland.th_attribute_data.rule_id</code>.
	 */
	@Column(name = "rule_id", precision = 10)
	public Integer getRuleId() {
		return (Integer) getValue(8);
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
	// Record9 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Integer, Integer, Integer, String, String, Integer, String, String, Integer> fieldsRow() {
		return (Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<Integer, Integer, Integer, String, String, Integer, String, String, Integer> valuesRow() {
		return (Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.SITE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.PAGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ATTRIBUTE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ATTRIBUTE_VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ATTRIBUTE_INDEX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ATTRIBUTE_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.ATTRIBUTE_FORMAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field9() {
		return ThAttributeData.TH_ATTRIBUTE_DATA.RULE_ID;
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
	public Integer value2() {
		return getSiteId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getPageId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getAttributeName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getAttributeValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getAttributeIndex();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getAttributeType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getAttributeFormat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value9() {
		return getRuleId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value2(Integer value) {
		setSiteId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value3(Integer value) {
		setPageId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value4(String value) {
		setAttributeName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value5(String value) {
		setAttributeValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value6(Integer value) {
		setAttributeIndex(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value7(String value) {
		setAttributeType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value8(String value) {
		setAttributeFormat(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord value9(Integer value) {
		setRuleId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThAttributeDataRecord values(Integer value1, Integer value2, Integer value3, String value4, String value5, Integer value6, String value7, String value8, Integer value9) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ThAttributeDataRecord
	 */
	public ThAttributeDataRecord() {
		super(ThAttributeData.TH_ATTRIBUTE_DATA);
	}

	/**
	 * Create a detached, initialised ThAttributeDataRecord
	 */
	public ThAttributeDataRecord(Integer id, Integer siteId, Integer pageId, String attributeName, String attributeValue, Integer attributeIndex, String attributeType, String attributeFormat, Integer ruleId) {
		super(ThAttributeData.TH_ATTRIBUTE_DATA);

		setValue(0, id);
		setValue(1, siteId);
		setValue(2, pageId);
		setValue(3, attributeName);
		setValue(4, attributeValue);
		setValue(5, attributeIndex);
		setValue(6, attributeType);
		setValue(7, attributeFormat);
		setValue(8, ruleId);
	}
}
