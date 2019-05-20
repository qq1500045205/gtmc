package com.tlan.common.view;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "wx_rule_keyword_view")
public class WxRuleKeywordView implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	public static final int EQUALS = 0;
	public static final int CONTAINS = 1;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * kwName db_column: kw_name
	 */
	private java.lang.String kwName;
	/**
	 * 匹配类型0等价，1包含，2正则 db_column: type
	 */

	private java.lang.Integer type;
	/**
	 * kwGuid db_column: kw_guid
	 */
	private java.lang.String kwGuid;
	/**
	 * 创建的规则对应的微信公众标识 db_column: gzh_hash
	 */
	private java.lang.String gzhHash;

	// columns END

	public WxRuleKeywordView() {
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "kw_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getKwGuid() {
		return this.kwGuid;
	}

	public void setKwGuid(java.lang.String value) {
		this.kwGuid = value;
	}

	@Column(name = "rule_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
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

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RuleGuid", getRuleGuid())
				.append("KwName", getKwName()).append("Type", getType())
				.append("KwGuid", getKwGuid()).append("GzhHash", getGzhHash())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxRuleKeywordView == false)
			return false;
		if (this == obj)
			return true;
		WxRuleKeywordView other = (WxRuleKeywordView) obj;
		return new EqualsBuilder().isEquals();
	}
}
