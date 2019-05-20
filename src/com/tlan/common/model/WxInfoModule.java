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
@Table(name = "wx_info_module")
public class WxInfoModule implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxInfoModule";
	public static final String ALIAS_INFO_MGUID = "infoMguid";
	public static final String ALIAS_RULE_GUID = "规则guid";
	public static final String ALIAS_NEWS_GUID = "内容guid";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * infoMguid db_column: info_m_guid
	 */
	private java.lang.String infoMguid;
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * 内容guid db_column: news_guid
	 */
	private java.lang.String newsGuid;

	// columns END

	public WxInfoModule() {
	}

	public WxInfoModule(java.lang.String infoMguid) {
		this.infoMguid = infoMguid;
	}

	public void setInfoMguid(java.lang.String value) {
		this.infoMguid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "info_m_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getInfoMguid() {
		return this.infoMguid;
	}

	@Column(name = "rule_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	@Column(name = "news_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getNewsGuid() {
		return this.newsGuid;
	}

	public void setNewsGuid(java.lang.String value) {
		this.newsGuid = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("InfoMguid", getInfoMguid())
				.append("RuleGuid", getRuleGuid())
				.append("NewsGuid", getNewsGuid()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getInfoMguid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxInfoModule == false)
			return false;
		if (this == obj)
			return true;
		WxInfoModule other = (WxInfoModule) obj;
		return new EqualsBuilder().append(getInfoMguid(), other.getInfoMguid())
				.isEquals();
	}
}
