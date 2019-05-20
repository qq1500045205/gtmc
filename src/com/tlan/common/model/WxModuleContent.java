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
@Table(name = "wx_module_content")
public class WxModuleContent implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxModuleContent";
	public static final String ALIAS_CONTENT_GUID = "主键guid";
	public static final String ALIAS_MODULE_GUID = "模块guid";
	public static final String ALIAS_CREATED_ON = "内容创建时间";
	public static final String ALIAS_CREATED_BY = "创建人";
	public static final String ALIAS_MODIFY_ON = "修改时间";
	public static final String ALIAS_MODIFY_BY = "修改人";
	public static final String ALIAS_PUBLISH_ON = "publishOn";
	public static final String ALIAS_PUBLISH_BY = "发布人";
	public static final String ALIAS_CONTENT_PAGE = "内容首页";
	public static final String ALIAS_STATUS = "0 未发布  1 已发布  2  已删除";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 主键guid db_column: content_guid
	 */
	private java.lang.String contentGuid;
	/**
	 * 模块guid db_column: module_guid
	 */
	private java.lang.String moduleGuid;
	/**
	 * 模块名称guid db_column: module_name
	 */
	private java.lang.String moduleName;
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
	 * 内容首页 db_column: content_page
	 */
	private java.lang.String contentPage;
	/**
	 * 0 未发布 1 已发布 2 已删除 db_column: status
	 */

	private java.lang.Integer status;
	/**
	 * 公众号标识
	 */
	private java.lang.String gzhHash;
	/**
	 * 内容标题
	 */
	private java.lang.String contentTitle;

	private java.lang.String namePath;
	/**
	 * 页面数量 db_column: page_count
	 */
	private int pageCount;

	// columns END

	public WxModuleContent() {
	}

	public WxModuleContent(java.lang.String contentGuid) {
		this.contentGuid = contentGuid;
	}

	public void setContentGuid(java.lang.String value) {
		this.contentGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "content_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getContentGuid() {
		return this.contentGuid;
	}

	@Column(name = "module_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleGuid() {
		return this.moduleGuid;
	}

	public void setModuleGuid(java.lang.String value) {
		this.moduleGuid = value;
	}

	@Column(name = "module_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModuleName() {
		return moduleName;
	}

	public void setModuleName(java.lang.String moduleName) {
		this.moduleName = moduleName;
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

	@Column(name = "content_page", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContentPage() {
		return this.contentPage;
	}

	public void setContentPage(java.lang.String value) {
		this.contentPage = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(java.lang.String gzhHash) {
		this.gzhHash = gzhHash;
	}

	@Column(name = "content_title", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(java.lang.String contentTitle) {
		this.contentTitle = contentTitle;
	}

	@Column(name = "name_path", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNamePath() {
		return namePath;
	}

	public void setNamePath(java.lang.String namePath) {
		this.namePath = namePath;
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
				.append("ContentGuid", getContentGuid())
				.append("ModuleGuid", getModuleGuid())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("ContentPage", getContentPage())
				.append("Status", getStatus()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getContentGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContent == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContent other = (WxModuleContent) obj;
		return new EqualsBuilder().append(getContentGuid(),
				other.getContentGuid()).isEquals();
	}
}
