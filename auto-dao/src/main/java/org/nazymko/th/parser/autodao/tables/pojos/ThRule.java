/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
public class ThRule implements Serializable {

	private static final long serialVersionUID = 195821049;

	private Integer    id;
	private String     rule;
	private Integer    version;
	private RuleStatus status;
	private String     authority;

	public ThRule() {}

	public ThRule(ThRule value) {
		this.id = value.id;
		this.rule = value.rule;
		this.version = value.version;
		this.status = value.status;
		this.authority = value.authority;
	}

	public ThRule(
		Integer    id,
		String     rule,
		Integer    version,
		RuleStatus status,
		String     authority
	) {
		this.id = id;
		this.rule = rule;
		this.version = version;
		this.status = status;
		this.authority = authority;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "rule", nullable = false)
	@NotNull
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Column(name = "version", nullable = false, precision = 10)
	@NotNull
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "status", precision = 10)
	public RuleStatus getStatus() {
		return this.status;
	}

	public void setStatus(RuleStatus status) {
		this.status = status;
	}

	@Column(name = "authority", nullable = false)
	@NotNull
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ThRule (");

		sb.append(id);
		sb.append(", ").append(rule);
		sb.append(", ").append(version);
		sb.append(", ").append(status);
		sb.append(", ").append(authority);

		sb.append(")");
		return sb.toString();
	}
}
