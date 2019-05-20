/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.tlan.common.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "wx_rule_keyword")
public class WxRuleKeyword implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxRuleKeyword";
	public static final String ALIAS_KW_GUID = "kwGuid";
	public static final String ALIAS_KW_NAME = "kwName";
	public static final String ALIAS_TYPE = "匹配类型1等价，2包含，3正则";
	public static final String ALIAS_RULE_GUID = "规则id";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * kwGuid db_column: kw_guid
	 */
	private java.lang.String kwGuid;
	/**
	 * kwName db_column: kw_name
	 */
	private java.lang.String kwName;
	/**
	 * 匹配类型0等价，1包含，2正则 db_column: type
	 */

	private java.lang.Integer type;
	/**
	 * 规则id db_column: rule_guid
	 */
	private java.lang.String ruleGuid;

	// columns END

	public WxRuleKeyword() {
	}

	public WxRuleKeyword(java.lang.String kwGuid) {
		this.kwGuid = kwGuid;
	}

	public void setKwGuid(java.lang.String value) {
		this.kwGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "kw_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getKwGuid() {
		return this.kwGuid;
	}

	@Column(name = "kw_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getKwName() {
		return this.kwName;
	}

	public void setKwName(java.lang.String value) {
		this.kwName = value;
	}

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}

	public void setType(java.lang.Integer value) {
		this.type = value;
	}

	@Column(name = "rule_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("KwGuid", getKwGuid()).append("KwName", getKwName())
				.append("Type", getType()).append("RuleGuid", getRuleGuid())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getKwGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxRuleKeyword == false)
			return false;
		if (this == obj)
			return true;
		WxRuleKeyword other = (WxRuleKeyword) obj;
		return new EqualsBuilder().append(getKwGuid(), other.getKwGuid())
				.isEquals();
	}
}
