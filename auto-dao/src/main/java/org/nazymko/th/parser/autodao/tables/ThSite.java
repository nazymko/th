/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.nazymko.th.parser.autodao.Keys;
import org.nazymko.th.parser.autodao.Thehomeland;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;

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
public class ThSite extends TableImpl<ThSiteRecord> {

    /**
     * The reference instance of <code>thehomeland.th_site</code>
     */
    public static final ThSite TH_SITE = new ThSite();
    private static final long serialVersionUID = 1373314345;
    /**
     * The column <code>thehomeland.th_site.id</code>.
     */
    public final TableField<ThSiteRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>thehomeland.th_site.url</code>.
     */
    public final TableField<ThSiteRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");
    /**
     * The column <code>thehomeland.th_site.name</code>.
     */
    public final TableField<ThSiteRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

    /**
     * Create a <code>thehomeland.th_site</code> table reference
     */
    public ThSite() {
        this("th_site", null);
    }

    /**
     * Create an aliased <code>thehomeland.th_site</code> table reference
     */
    public ThSite(String alias) {
        this(alias, TH_SITE);
    }

    private ThSite(String alias, Table<ThSiteRecord> aliased) {
        this(alias, aliased, null);
    }

    private ThSite(String alias, Table<ThSiteRecord> aliased, Field<?>[] parameters) {
        super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ThSiteRecord> getRecordType() {
        return ThSiteRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ThSiteRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TH_SITE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ThSiteRecord> getPrimaryKey() {
        return Keys.KEY_TH_SITE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ThSiteRecord>> getKeys() {
        return Arrays.<UniqueKey<ThSiteRecord>>asList(Keys.KEY_TH_SITE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThSite as(String alias) {
        return new ThSite(alias, this);
    }

    /**
     * Rename this table
     */
    public ThSite rename(String name) {
        return new ThSite(name, null);
    }
}