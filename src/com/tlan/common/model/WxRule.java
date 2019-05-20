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
@Table(name = "wx_rule")
public class WxRule implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * 默认
	 */
	public static final int DEFAULT = 1;
	/**
	 * 非默认
	 */
	public static final int NO_DEFAULT = 0;
	/**
	 * 完全匹配
	 */
	public static final int EQUALS = 1;
	/**
	 * 包含
	 */
	public static final int CONTAINS = 0;

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * 模块标识 db_column: module_name
	 */
	private java.lang.String moduleName;
	/**
	 * 模块名称 db_column: module_title
	 */
	private java.lang.String moduleTitle;
	/**
	 * 规则名称 db_column: rule_name
	 */
	private java.lang.String ruleName;
	/**
	 * 创建的规则对应的微信公众标识 db_column: gzh_guid
	 */
	private java.lang.String gzhHash;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * createdBy db_column: created_by
	 */
	private java.lang.String createdBy;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * modifyBy db_column: modify_by
	 */
	private java.lang.String modifyBy;
	/**
	 * publishOn db_column: publish_on
	 */
	private java.lang.String publishOn;
	/**
	 * publishBy db_column: publish_by
	 */
	private java.lang.String publishBy;
	/**
	 * 是否默认 0 否 1 时 db_column: is_default
	 */

	private java.lang.Integer isDefault;
	/**
	 * 状态 db_column: status
	 */

	private java.lang.Integer status;

	// columns END

	public WxRule() {
	}

	public WxRule(java.lang.String ruleGuid) {
		this.ruleGuid = ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "rule_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	@Column(name = "module_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}

	@Column(name = "module_title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getModuleTitle() {
		return this.moduleTitle;
	}

	public void setModuleTitle(java.lang.String value) {
		this.moduleTitle = value;
	}

	@Column(name = "rule_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(java.lang.String value) {
		this.ruleName = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}

	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}

	@Column(name = "publish_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPublishOn() {
		return this.publishOn;
	}

	public void setPublishOn(java.lang.String value) {
		this.publishOn = value;
	}

	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}

	@Column(name = "is_default", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(java.lang.Integer value) {
		this.isDefault = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RuleGuid", getRuleGuid())
				.append("ModuleName", getModuleName())
				.append("ModuleTitle", getModuleTitle())
				.append("RuleName", getRuleName())
				.append("GzhHash", getGzhHash())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("IsDefault", getIsDefault())
				.append("Status", getStatus()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRuleGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxRule == false)
			return false;
		if (this == obj)
			return true;
		WxRule other = (WxRule) obj;
		return new EqualsBuilder().append(getRuleGuid(), other.getRuleGuid())
				.isEquals();
	}
}
