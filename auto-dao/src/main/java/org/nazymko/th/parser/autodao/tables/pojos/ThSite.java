/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.pojos;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


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
@Table(name = "th_site", schema = "thehomeland")
public class ThSite implements Serializable {

	private static final long serialVersionUID = 1926795167;

	private Integer id;
	private String  authority;
	private String  name;
	private String defaultUrl;

	public ThSite() {}

	public ThSite(ThSite value) {
		this.id = value.id;
		this.authority = value.authority;
		this.name = value.name;
		this.defaultUrl = value.defaultUrl;
	}

	public ThSite(
			Integer id,
			String  authority,
			String name,
			String defaultUrl
	) {
		this.id = id;
		this.authority = authority;
		this.name = name;
		this.defaultUrl = defaultUrl;
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

	@Column(name = "authority", length = 256)
	@Size(max = 256)
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Column(name = "name", length = 128)
	@Size(max = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "default_url", nullable = false, length = 1024)
	@NotNull
	@Size(max = 1024)
	public String getDefaultUrl() {
		return this.defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ThSite (");

		sb.append(id);
		sb.append(", ").append(authority);
		sb.append(", ").append(name);
		sb.append(", ").append(defaultUrl);

		sb.append(")");
		return sb.toString();
	}
}
