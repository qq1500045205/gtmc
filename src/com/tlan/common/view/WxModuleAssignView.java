package com.tlan.common.view;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "wx_module_assign_view")
public class WxModuleAssignView implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * moduleGuid db_column: moduleGuid
	 */
	private java.lang.String moduleGuid;
	/**
	 * 公众号与模块关系表id db_column: mappingGuid
	 */
	private java.lang.String mappingGuid;
	/**
	 * 模块标识，如果rulefields为1，则数据存储在wx_模块标识_replay中 db_column: moduleName
	 */
	private java.lang.String moduleName;
	/**
	 * 公众号名称 db_column: gzhName
	 */
	private java.lang.String gzhName;
	/**
	 * 用户标识. 随机生成保持不重复 db_column: gzhHash
	 */
	private java.lang.String gzhHash;
	/**
	 * 微信公众号主键 db_column: gzhGuid
	 */
	private java.lang.String gzhGuid;
	/**
	 * 是否可用 0可用 1 不可用 db_column: isDisabled
	 */

	private java.lang.Integer isDisabled;
	/**
	 * 是否是系统模块 db_column: isSystem
	 */

	private java.lang.Integer isSystem;
	/**
	 * 是否发布 db_column: isPublish
	 */

	private java.lang.Integer isPublish;
	/**
	 * projectGuid db_column: projectGuid
	 */
	private java.lang.String projectGuid;
	/**
	 * projectName db_column: project_name
	 */
	private java.lang.String projectName;
	/**
	 * 是否公用 db_column: isPublic
	 */

	private java.lang.Integer isPublic;
	/**
	 * 是否可编辑 db_column: isEditable
	 */

	private java.lang.Integer isEditable;
	/**
	 * createdOn db_column: createdOn
	 */
	private java.lang.String createdOn;
	/**
	 * createdBy db_column: createdBy
	 */
	private java.lang.String createdBy;
	/**
	 * modifyOn db_column: modifyOn
	 */
	private java.lang.String modifyOn;
	/**
	 * modifyBy db_column: modifyBy
	 */
	private java.lang.String modifyBy;

	// columns END

	public WxModuleAssignView() {
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "mappingGuid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getMappingGuid() {
		return this.mappingGuid;
	}

	public void setMappingGuid(java.lang.String value) {
		this.mappingGuid = value;
	}

	@Column(name = "moduleGuid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleGuid() {
		return this.moduleGuid;
	}

	public void setModuleGuid(java.lang.String value) {
		this.moduleGuid = value;
	}

	@Column(name = "moduleName", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}

	@Column(name = "gzhName", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getGzhName() {
		return this.gzhName;
	}

	public void setGzhName(java.lang.String value) {
		this.gzhName = value;
	}

	@Column(name = "gzhHash", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "gzhGuid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhGuid() {
		return this.gzhGuid;
	}

	public void setGzhGuid(java.lang.String value) {
		this.gzhGuid = value;
	}

	@Column(name = "isDisabled", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(java.lang.Integer value) {
		this.isDisabled = value;
	}

	@Column(name = "isSystem", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsSystem() {
		return this.isSystem;
	}

	public void setIsSystem(java.lang.Integer value) {
		this.isSystem = value;
	}

	@Column(name = "isPublish", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsPublish() {
		return this.isPublish;
	}

	public void setIsPublish(java.lang.Integer value) {
		this.isPublish = value;
	}

	@Column(name = "projectGuid", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getProjectGuid() {
		return this.projectGuid;
	}

	public void setProjectGuid(java.lang.String value) {
		this.projectGuid = value;
	}

	@Column(name = "projectName", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(java.lang.String value) {
		this.projectName = value;
	}

	@Column(name = "isPublic", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(java.lang.Integer value) {
		this.isPublic = value;
	}

	@Column(name = "isEditable", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsEditable() {
		return this.isEditable;
	}

	public void setIsEditable(java.lang.Integer value) {
		this.isEditable = value;
	}

	@Column(name = "createdOn", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "createdBy", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}

	@Column(name = "modifyOn", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "modifyBy", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ModuleGuid", getModuleGuid())
				.append("MappingGuid", getMappingGuid())
				.append("ModuleName", getModuleName())
				.append("GzhName", getGzhName())
				.append("GzhHash", getGzhHash())
				.append("GzhGuid", getGzhGuid())
				.append("IsDisabled", getIsDisabled())
				.append("IsSystem", getIsSystem())
				.append("IsPublish", getIsPublish())
				.append("ProjectGuid", getProjectGuid())
				.append("ProjectName", getProjectName())
				.append("IsPublic", getIsPublic())
				.append("IsEditable", getIsEditable())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleAssignView == false)
			return false;
		if (this == obj)
			return true;
		WxModuleAssignView other = (WxModuleAssignView) obj;
		return new EqualsBuilder().isEquals();
	}
}
