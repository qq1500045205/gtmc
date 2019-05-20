package com.tlan.common.model;

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
@Table(name = "wx_module")
public class WxModule implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxModule";
	public static final String ALIAS_MODULE_GUID = "模块guid";
	public static final String ALIAS_MODULE_NAME = "模块标识，如果rulefields为1，则数据存储在wx_模块标识_replay中";
	public static final String ALIAS_MODULE_TITLE = "模块名称";
	public static final String ALIAS_ABILITY = "功能描述";
	public static final String ALIAS_DESCRIPTION = "介绍";
	public static final String ALIAS_IS_SYSTEM = "是否是系统模块";
	public static final String ALIAS_IS_DISABLED = "是否可用 0可用 1 不可用";
	public static final String ALIAS_GZH_GUID = "gzhGuid";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_MODIFY_ON = "modifyOn";
	public static final String ALIAS_MODIFY_BY = "modifyBy";
	public static final String ALIAS_PUBLISH_ON = "publishOn";
	public static final String ALIAS_PUBLISH_BY = "publishBy";
	public static final String ALIAS_IS_PUBLISH = "是否发布";
	public static final String ALIAS_CONTENT_FIRST = "内容首页地址";
	public static final String ALIAS_NAME_PATH = "动作类型";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 模块guid db_column: module_guid
	 */
	private java.lang.String moduleGuid;
	/**
	 * 模块标识，如果rulefields为1，则数据存储在wx_模块标识_replay中 db_column: module_name
	 */
	private java.lang.String moduleName;
	/**
	 * 模块名称 db_column: module_title
	 */
	private java.lang.String moduleTitle;
	/**
	 * 功能描述 db_column: ability
	 */
	private java.lang.String ability;
	/**
	 * 介绍 db_column: description
	 */
	private java.lang.String description;
	/**
	 * 是否是系统模块 db_column: is_system
	 */

	private java.lang.Integer isSystem;
	/**
	 * 是否可用 0可用 1 不可用 db_column: is_disabled
	 */
	private java.lang.Integer isDisabled;
	/**
	 * gzhGuid db_column: gzh_guid
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
	 * 是否发布 db_column: is_publish
	 */
	private java.lang.Integer isPublish;
	/**
	 * 内容首页地址 db_column: content_first
	 */
	private java.lang.String contentFirst;
	/**
	 * 动作类型 db_column: name_path
	 */
	private java.lang.String namePath;

	/**
	 * 使用的组件页名称 db_column: mod_page
	 */
	private java.lang.String modPage;
	
	/**
	 * 使用的组件页名称 db_column: mod_page_name
	 */
	private java.lang.String modPageName;

	/**
	 * 页面数量 db_column: page_count
	 */
	private int pageCount;

	// columns END

	public WxModule() {
	}

	public WxModule(java.lang.String moduleGuid) {
		this.moduleGuid = moduleGuid;
	}

	public void setModuleGuid(java.lang.String value) {
		this.moduleGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "module_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleGuid() {
		return this.moduleGuid;
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

	@Column(name = "ability", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getAbility() {
		return this.ability;
	}

	public void setAbility(java.lang.String value) {
		this.ability = value;
	}

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	@Column(name = "is_system", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(java.lang.Integer value) {
		this.isSystem = value;
	}

	@Column(name = "is_disabled", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(java.lang.Integer value) {
		this.isDisabled = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(java.lang.String gzhHash) {
		this.gzhHash = gzhHash;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
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

	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
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

	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}

	@Column(name = "is_publish", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsPublish() {
		return this.isPublish;
	}

	public void setIsPublish(java.lang.Integer value) {
		this.isPublish = value;
	}

	@Column(name = "content_first", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContentFirst() {
		return this.contentFirst;
	}

	public void setContentFirst(java.lang.String value) {
		this.contentFirst = value;
	}

	@Column(name = "name_path", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNamePath() {
		return this.namePath;
	}

	public void setNamePath(java.lang.String value) {
		this.namePath = value;
	}

	@Column(name = "mod_page", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModPage() {
		return modPage;
	}

	public void setModPageName(java.lang.String modPageName) {
		this.modPageName = modPageName;
	}
	
	@Column(name = "mod_page_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModPageName() {
		return modPageName;
	}

	public void setModPage(java.lang.String modPage) {
		this.modPage = modPage;
	}

	@Column(name = "page_count", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ModuleGuid", getModuleGuid())
				.append("ModuleName", getModuleName())
				.append("ModuleTitle", getModuleTitle())
				.append("Ability", getAbility())
				.append("Description", getDescription())
				.append("IsSystem", getIsSystem())
				.append("IsDisabled", getIsDisabled())
				.append("GzhHash", getGzhHash())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("IsPublish", getIsPublish())
				.append("ContentFirst", getContentFirst())
				.append("NamePath", getNamePath()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getModuleGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModule == false)
			return false;
		if (this == obj)
			return true;
		WxModule other = (WxModule) obj;
		return new EqualsBuilder().append(getModuleGuid(),
				other.getModuleGuid()).isEquals();
	}
}
