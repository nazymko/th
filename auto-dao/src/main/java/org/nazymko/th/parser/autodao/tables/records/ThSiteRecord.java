/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.nazymko.th.parser.autodao.tables.ThSite;

import javax.annotation.Generated;


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
public class ThSiteRecord extends UpdatableRecordImpl<ThSiteRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 1110064163;

    /**
     * Create a detached ThSiteRecord
     */
    public ThSiteRecord() {
        super(ThSite.TH_SITE);
    }

    /**
     * Create a detached, initialised ThSiteRecord
     */
    public ThSiteRecord(Integer id, String url, String name) {
        super(ThSite.TH_SITE);

        setValue(0, id);
        setValue(1, url);
        setValue(2, name);
    }

    /**
     * Getter for <code>thehomeland.th_site.id</code>.
     */
    public Integer getId() {
        return (Integer) getValue(0);
    }

    /**
     * Setter for <code>thehomeland.th_site.id</code>.
     */
    public void setId(Integer value) {
        setValue(0, value);
    }

    /**
     * Getter for <code>thehomeland.th_site.url</code>.
     */
    public String getUrl() {
        return (String) getValue(1);
    }

    /**
     * Setter for <code>thehomeland.th_site.url</code>.
     */
    public void setUrl(String value) {
        setValue(1, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>thehomeland.th_site.name</code>.
     */
    public String getName() {
        return (String) getValue(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>thehomeland.th_site.name</code>.
     */
    public void setName(String value) {
        setValue(2, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ThSite.TH_SITE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return ThSite.TH_SITE.URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return ThSite.TH_SITE.NAME;
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
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThSiteRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThSiteRecord value2(String value) {
        setUrl(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public ThSiteRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThSiteRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }
}
