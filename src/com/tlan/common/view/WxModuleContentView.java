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
@Table(name = "wx_module_content_view")
public class WxModuleContentView implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

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

	private java.lang.Integer pageCount;
	/**
	 * 是否可用 0可用 1 不可用 db_column: is_disabled
	 */

	private java.lang.Integer isDisabled;
	/**
	 * 是否是系统模块 db_column: is_system
	 */

	private java.lang.Integer isSystem;
	/**
	 * 模块名称 db_column: module_title
	 */
	private java.lang.String moduleTitle;
	/**
	 * 公众号与模块关系表id db_column: mapping_guid
	 */
	private java.lang.String mappingGuid;
	/**
	 * gzhHash db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * gzhGuid db_column: gzh_guid
	 */
	private java.lang.String gzhGuid;
	/**
	 * 是否可编辑 db_column: is_editable
	 */

	private java.lang.Integer isEditable;
	/**
	 * 主键guid db_column: content_guid
	 */
	private java.lang.String contentGuid;
	/**
	 * 内容标题 db_column: content_title
	 */
	private java.lang.String contentTitle;
	/**
	 * 内容首页 db_column: content_page
	 */
	private java.lang.String contentPage;
	/**
	 * 内容创建时间 db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * 创建人 db_column: created_by
	 */
	private java.lang.String createdBy;
	/**
	 * 修改时间 db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * 修改人 db_column: modify_by
	 */
	private java.lang.String modifyBy;
	/**
	 * publishOn db_column: publish_on
	 */
	private java.lang.String publishOn;
	/**
	 * 发布人 db_column: publish_by
	 */
	private java.lang.String publishBy;
	/**
	 * 0 未发布 1 已发布 2 已删除 db_column: status
	 */

	private java.lang.Integer status;
	/**
	 * 介绍 db_column: description
	 */
	private java.lang.String description;

	// columns END

	public WxModuleContentView() {
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "mapping_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getMappingGuid() {
		return this.mappingGuid;
	}

	public void setMappingGuid(java.lang.String value) {
		this.mappingGuid = value;
	}

	@Column(name = "module_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleGuid() {
		return this.moduleGuid;
	}

	public void setModuleGuid(java.lang.String value) {
		this.moduleGuid = value;
	}

	@Column(name = "module_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
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
		return this.modPage;
	}

	public void setModPage(java.lang.String value) {
		this.modPage = value;
	}
	
	@Column(name = "mod_page_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModPageName() {
		return this.modPageName;
	}

	public void setModPageName(java.lang.String value) {
		this.modPageName = value;
	}

	@Column(name = "page_count", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(java.lang.Integer value) {
		this.pageCount = value;
	}

	@Column(name = "is_disabled", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(java.lang.Integer value) {
		this.isDisabled = value;
	}

	@Column(name = "is_system", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(java.lang.Integer value) {
		this.isSystem = value;
	}

	@Column(name = "module_title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getModuleTitle() {
		return this.moduleTitle;
	}

	public void setModuleTitle(java.lang.String value) {
		this.moduleTitle = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "gzh_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhGuid() {
		return this.gzhGuid;
	}

	public void setGzhGuid(java.lang.String value) {
		this.gzhGuid = value;
	}

	@Column(name = "is_editable", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsEditable() {
		return this.isEditable;
	}

	public void setIsEditable(java.lang.Integer value) {
		this.isEditable = value;
	}

	@Column(name = "content_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getContentGuid() {
		return this.contentGuid;
	}

	public void setContentGuid(java.lang.String value) {
		this.contentGuid = value;
	}

	@Column(name = "content_title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContentTitle() {
		return this.contentTitle;
	}

	public void setContentTitle(java.lang.String value) {
		this.contentTitle = value;
	}

	@Column(name = "content_page", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContentPage() {
		return this.contentPage;
	}

	public void setContentPage(java.lang.String value) {
		this.contentPage = value;
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

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ModuleGuid", getModuleGuid())
				.append("ModuleName", getModuleName())
				.append("IsPublish", getIsPublish())
				.append("ContentFirst", getContentFirst())
				.append("NamePath", getNamePath())
				.append("ModPage", getModPage())
				.append("PageCount", getPageCount())
				.append("IsDisabled", getIsDisabled())
				.append("IsSystem", getIsSystem())
				.append("ModuleTitle", getModuleTitle())
				.append("MappingGuid", getMappingGuid())
				.append("GzhHash", getGzhHash())
				.append("GzhGuid", getGzhGuid())
				.append("IsEditable", getIsEditable())
				.append("ContentGuid", getContentGuid())
				.append("ContentTitle", getContentTitle())
				.append("ContentPage", getContentPage())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("Status", getStatus())
				.append("Description", getDescription()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContentView == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContentView other = (WxModuleContentView) obj;
		return new EqualsBuilder().isEquals();
	}
}
