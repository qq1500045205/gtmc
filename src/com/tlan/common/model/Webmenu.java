package com.tlan.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "webmenu")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Webmenu implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "Webmenu";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "模块名称";
	public static final String ALIAS_PARENT = "父模块ID";
	public static final String ALIAS_URL = "触发转到页地址";
	public static final String ALIAS_ENABLE = "是否可用  默认可用";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * id db_column: id
	 */

	private java.lang.Integer id;
	/**
	 * 模块名称 db_column: name
	 */
	private java.lang.String name;
	/**
	 * 父模块ID db_column: parent
	 */

	private java.lang.Long parent;
	/**
	 * 触发转到页地址 db_column: url
	 */
	private java.lang.String url;
	/**
	 * 是否可用 默认可用 db_column: enable
	 */

	private java.lang.String rights;
	/**
	 * 是否可用 默认可用 db_column: enable
	 */
	
	private java.lang.Boolean enable;

	// columns END

	public Webmenu() {
	}

	public Webmenu(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}

	@Column(name = "name", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	@Column(name = "parent", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.lang.Long getParent() {
		return this.parent;
	}

	public void setParent(java.lang.Long value) {
		this.parent = value;
	}

	@Column(name = "url", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}

	@Column(name = "rights", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRights() {
		return this.rights;
	}

	public void setRights(java.lang.String value) {
		this.rights = value;
	}
	
	@Column(name = "enable", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public java.lang.Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(java.lang.Boolean value) {
		this.enable = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Name", getName())
				.append("Parent", getParent()).append("Url", getUrl())
				.append("Enable", getEnable()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Webmenu == false)
			return false;
		if (this == obj)
			return true;
		Webmenu other = (Webmenu) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
