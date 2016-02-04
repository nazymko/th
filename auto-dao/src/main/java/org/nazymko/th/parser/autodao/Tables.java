/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao;


import javax.annotation.Generated;

import org.nazymko.th.parser.autodao.tables.AttributeData;
import org.nazymko.th.parser.autodao.tables.Page;
import org.nazymko.th.parser.autodao.tables.Rule;
import org.nazymko.th.parser.autodao.tables.Site;
import org.nazymko.th.parser.autodao.tables.TSchedule;
import org.nazymko.th.parser.autodao.tables.TTask;


/**
 * Convenience access to all tables in thehomeland
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table thehomeland.attribute_data
	 */
	public static final AttributeData ATTRIBUTE_DATA = org.nazymko.th.parser.autodao.tables.AttributeData.ATTRIBUTE_DATA;

	/**
	 * The table thehomeland.page
	 */
	public static final Page PAGE = org.nazymko.th.parser.autodao.tables.Page.PAGE;

	/**
	 * The table thehomeland.rule
	 */
	public static final Rule RULE = org.nazymko.th.parser.autodao.tables.Rule.RULE;

	/**
	 * The table thehomeland.site
	 */
	public static final Site SITE = org.nazymko.th.parser.autodao.tables.Site.SITE;

	/**
	 * The table thehomeland.t_schedule
	 */
	public static final TSchedule T_SCHEDULE = org.nazymko.th.parser.autodao.tables.TSchedule.T_SCHEDULE;

	/**
	 * The table thehomeland.t_task
	 */
	public static final TTask T_TASK = org.nazymko.th.parser.autodao.tables.TTask.T_TASK;
}
