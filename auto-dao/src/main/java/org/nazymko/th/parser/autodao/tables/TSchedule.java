/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables;


import java.sql.Timestamp;
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
import org.nazymko.th.parser.autodao.tables.records.TScheduleRecord;


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
public class TSchedule extends TableImpl<TScheduleRecord> {

	private static final long serialVersionUID = -2078840671;

	/**
	 * The reference instance of <code>thehomeland.t_schedule</code>
	 */
	public static final TSchedule T_SCHEDULE = new TSchedule();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TScheduleRecord> getRecordType() {
		return TScheduleRecord.class;
	}

	/**
	 * The column <code>thehomeland.t_schedule.id</code>.
	 */
	public final TableField<TScheduleRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>thehomeland.t_schedule.siteId</code>.
	 */
	public final TableField<TScheduleRecord, Integer> SITEID = createField("siteId", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>thehomeland.t_schedule.start_page</code>.
	 */
	public final TableField<TScheduleRecord, String> START_PAGE = createField("start_page", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * The column <code>thehomeland.t_schedule.page_type</code>.
	 */
	public final TableField<TScheduleRecord, String> PAGE_TYPE = createField("page_type", org.jooq.impl.SQLDataType.VARCHAR.length(64).nullable(false), this, "");

	/**
	 * The column <code>thehomeland.t_schedule.start_at</code>.
	 */
	public final TableField<TScheduleRecord, Timestamp> START_AT = createField("start_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>thehomeland.t_schedule.cron</code>.
	 */
	public final TableField<TScheduleRecord, String> CRON = createField("cron", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>thehomeland.t_schedule.is_enabled</code>.
	 */
	public final TableField<TScheduleRecord, Boolean> IS_ENABLED = createField("is_enabled", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * Create a <code>thehomeland.t_schedule</code> table reference
	 */
	public TSchedule() {
		this("t_schedule", null);
	}

	/**
	 * Create an aliased <code>thehomeland.t_schedule</code> table reference
	 */
	public TSchedule(String alias) {
		this(alias, T_SCHEDULE);
	}

	private TSchedule(String alias, Table<TScheduleRecord> aliased) {
		this(alias, aliased, null);
	}

	private TSchedule(String alias, Table<TScheduleRecord> aliased, Field<?>[] parameters) {
		super(alias, Thehomeland.THEHOMELAND, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<TScheduleRecord, Integer> getIdentity() {
		return Keys.IDENTITY_T_SCHEDULE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TScheduleRecord> getPrimaryKey() {
		return Keys.KEY_T_SCHEDULE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TScheduleRecord>> getKeys() {
		return Arrays.<UniqueKey<TScheduleRecord>>asList(Keys.KEY_T_SCHEDULE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TSchedule as(String alias) {
		return new TSchedule(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TSchedule rename(String name) {
		return new TSchedule(name, null);
	}
}
