/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.car;

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
@Table(name = "wx_module_content_car_para")
public class WxModuleContentCarPara implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentCarPara";
	public static final String ALIAS_CAR_PARA_GUID = "carParaGuid";
	public static final String ALIAS_CAR_PARA_PARENT_ID = "carParaParentId";
	public static final String ALIAS_CAR_PARA_SORTID = "carParaSortid";
	public static final String ALIAS_CAR_PARA_NAME = "carParaName";
	public static final String ALIAS_CAR_PARA_VALUE = "carParaValue";
	public static final String ALIAS_CAR_PARA_CAR_GUID = "carParaCarGuid";
	public static final String ALIAS_DELETE_TAG = "0表示未删除,1表示已删除";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_DELETE_ON = "deleteOn";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * carParaGuid       db_column: car_para_guid 
     */ 	

	private java.lang.String carParaGuid;
    /**
     * carParaParentId       db_column: car_para_parent_id 
     */ 	

	private java.lang.String carParaParentId;
    /**
     * carParaSortid       db_column: car_para_sortid 
     */ 	

	private java.lang.String carParaSortid;
    /**
     * carParaName       db_column: car_para_name 
     */ 	

	private java.lang.String carParaName;
    /**
     * carParaValue       db_column: car_para_value 
     */ 	

	private java.lang.String carParaValue;
    /**
     * carParaCarGuid       db_column: car_para_car_guid 
     */ 	

	private java.lang.String carParaCarGuid;
    /**
     * 0表示未删除,1表示已删除       db_column: delete_tag 
     */ 	

	private java.lang.Integer deleteTag;
    /**
     * createdOn       db_column: created_on 
     */ 	

	private java.lang.String createdOn;
    /**
     * deleteOn       db_column: delete_on 
     */ 	

	private java.lang.String deleteOn;
	//columns END


	public WxModuleContentCarPara(){
	}

	public WxModuleContentCarPara(
		java.lang.String carParaGuid
	){
		this.carParaGuid = carParaGuid;
	}

	

	public void setCarParaGuid(java.lang.String value) {
		this.carParaGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "car_para_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarParaGuid() {
		return this.carParaGuid;
	}
	
	@Column(name = "car_para_parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarParaParentId() {
		return this.carParaParentId;
	}
	
	public void setCarParaParentId(java.lang.String value) {
		this.carParaParentId = value;
	}
	
	@Column(name = "car_para_sortid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarParaSortid() {
		return this.carParaSortid;
	}
	
	public void setCarParaSortid(java.lang.String value) {
		this.carParaSortid = value;
	}
	
	@Column(name = "car_para_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCarParaName() {
		return this.carParaName;
	}
	
	public void setCarParaName(java.lang.String value) {
		this.carParaName = value;
	}
	
	@Column(name = "car_para_value", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarParaValue() {
		return this.carParaValue;
	}
	
	public void setCarParaValue(java.lang.String value) {
		this.carParaValue = value;
	}
	
	@Column(name = "car_para_car_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarParaCarGuid() {
		return this.carParaCarGuid;
	}
	
	public void setCarParaCarGuid(java.lang.String value) {
		this.carParaCarGuid = value;
	}
	
	@Column(name = "delete_tag", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDeleteTag() {
		return this.deleteTag;
	}
	
	public void setDeleteTag(java.lang.Integer value) {
		this.deleteTag = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "delete_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getDeleteOn() {
		return this.deleteOn;
	}
	
	public void setDeleteOn(java.lang.String value) {
		this.deleteOn = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("CarParaGuid",getCarParaGuid())
			.append("CarParaParentId",getCarParaParentId())
			.append("CarParaSortid",getCarParaSortid())
			.append("CarParaName",getCarParaName())
			.append("CarParaValue",getCarParaValue())
			.append("CarParaCarGuid",getCarParaCarGuid())
			.append("DeleteTag",getDeleteTag())
			.append("CreatedOn",getCreatedOn())
			.append("DeleteOn",getDeleteOn())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCarParaGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentCarPara == false) return false;
		if(this == obj) return true;
		WxModuleContentCarPara other = (WxModuleContentCarPara)obj;
		return new EqualsBuilder()
			.append(getCarParaGuid(),other.getCarParaGuid())
			.isEquals();
	}
}

