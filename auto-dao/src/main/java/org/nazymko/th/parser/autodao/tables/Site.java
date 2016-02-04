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
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;


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
public class Site extends TableImpl<SiteRecord> {

	private static final long serialVersionUID = -920534024;

	/**
	 * The reference instance of <code>thehomeland.site</code>
	 */
	public static final Site SITE = new Site();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SiteRecord> getRecordType() {
		return SiteRecord.class;
	}

	/**
	 * The column <code>thehomeland.site.id</code>.
	 */
	public final TableField<SiteRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.site.url</code>.
	 */
	public final TableField<SiteRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>thehomeland.site.name</code>.
	 */
	public final TableField<SiteRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

	/**
	 * Create a <code>thehomeland.site</code> table reference
	 */
	public Site() {
		this("site", null);
	}

	/**
	 * Create an aliased <code>thehomeland.site</code> table reference
	 */
	public Site(String alias) {
		this(alias, SITE);
	}

	private Site(String alias, Table<SiteRecord> aliased) {
		this(alias, aliased, null);
	}

	private Site(String alias, Table<SiteRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<SiteRecord, Integer> getIdentity() {
		return Keys.IDENTITY_SITE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<SiteRecord> getPrimaryKey() {
		return Keys.KEY_SITE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<SiteRecord>> getKeys() {
		return Arrays.<UniqueKey<SiteRecord>>asList(Keys.KEY_SITE_PRIMARY, Keys.KEY_SITE_SITE_ID_UINDEX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Site as(String alias) {
		return new Site(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Site rename(String name) {
		return new Site(name, null);
	}
}
