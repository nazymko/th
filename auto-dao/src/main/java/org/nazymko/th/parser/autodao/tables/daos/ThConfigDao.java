/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.nazymko.th.parser.autodao.tables.ThConfig;
import org.nazymko.th.parser.autodao.tables.records.ThConfigRecord;


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
public class ThConfigDao extends DAOImpl<ThConfigRecord, org.nazymko.th.parser.autodao.tables.pojos.ThConfig, Integer> {

	/**
	 * Create a new ThConfigDao without any configuration
	 */
	public ThConfigDao() {
		super(ThConfig.TH_CONFIG, org.nazymko.th.parser.autodao.tables.pojos.ThConfig.class);
	}

	/**
	 * Create a new ThConfigDao with an attached configuration
	 */
	public ThConfigDao(Configuration configuration) {
		super(ThConfig.TH_CONFIG, org.nazymko.th.parser.autodao.tables.pojos.ThConfig.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.nazymko.th.parser.autodao.tables.pojos.ThConfig object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ThConfig> fetchById(Integer... values) {
		return fetch(ThConfig.TH_CONFIG.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.nazymko.th.parser.autodao.tables.pojos.ThConfig fetchOneById(Integer value) {
		return fetchOne(ThConfig.TH_CONFIG.ID, value);
	}

	/**
	 * Fetch records that have <code>name IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ThConfig> fetchByName(String... values) {
		return fetch(ThConfig.TH_CONFIG.NAME, values);
	}

	/**
	 * Fetch a unique record that has <code>name = value</code>
	 */
	public org.nazymko.th.parser.autodao.tables.pojos.ThConfig fetchOneByName(String value) {
		return fetchOne(ThConfig.TH_CONFIG.NAME, value);
	}

	/**
	 * Fetch records that have <code>value IN (values)</code>
	 */
	public List<org.nazymko.th.parser.autodao.tables.pojos.ThConfig> fetchByValue(String... values) {
		return fetch(ThConfig.TH_CONFIG.VALUE, values);
	}
}
