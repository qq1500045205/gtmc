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
@Table(name = "wx_module_gongzhonghao")
public class WxModuleGongzhonghao implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final int EDITABLE = 1;
	public static final int UNEDITABLE = 0;
	public static final int PUBLIC = 1;
	public static final int UNPUBLIC = 0;
	
	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 公众号与模块关系表id db_column: mapping_guid
	 */
	private java.lang.String mappingGuid;
	/**
	 * moduleGuid db_column: module_guid
	 */
	private java.lang.String moduleGuid;
	/**
	 * gzhHash db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * gzhGuid db_column: gzh_guid
	 */
	private java.lang.String gzhGuid;
	/**
	 * 是否公用 db_column: is_public
	 */
	private java.lang.Integer isPublic;
	/**
	 * 是否可编辑 db_column: is_editable
	 */
	private java.lang.Integer isEditable;
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

	// columns END

	public WxModuleGongzhonghao() {
	}

	public WxModuleGongzhonghao(java.lang.String mappingGuid) {
		this.mappingGuid = mappingGuid;
	}

	public void setMappingGuid(java.lang.String value) {
		this.mappingGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "mapping_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getMappingGuid() {
		return this.mappingGuid;
	}

	@Column(name = "module_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleGuid() {
		return this.moduleGuid;
	}

	public void setModuleGuid(java.lang.String value) {
		this.moduleGuid = value;
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

	@Column(name = "is_public", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(java.lang.Integer value) {
		this.isPublic = value;
	}

	@Column(name = "is_editable", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsEditable() {
		return this.isEditable;
	}

	public void setIsEditable(java.lang.Integer value) {
		this.isEditable = value;
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

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("MappingGuid", getMappingGuid())
				.append("ModuleGuid", getModuleGuid())
				.append("GzhHash", getGzhHash())
				.append("GzhGuid", getGzhGuid())
				.append("IsPublic", getIsPublic())
				.append("IsEditable", getIsEditable())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getMappingGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleGongzhonghao == false)
			return false;
		if (this == obj)
			return true;
		WxModuleGongzhonghao other = (WxModuleGongzhonghao) obj;
		return new EqualsBuilder().append(getMappingGuid(),
				other.getMappingGuid()).isEquals();
	}
}
