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
@Table(name = "wx_module_content_gzh")
public class WxModuleContentGzh implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentGzh";
	public static final String ALIAS_GZH_GUID = "gzhGuid";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_CAR_TYPE_GUID = "carTypeGuid";
	public static final String ALIAS_DELETE_TAG = "0表示未删除,1表示已删除";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_DELETE_ON = "deleteOn";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * gzhGuid       db_column: gzh_guid
     */ 	
	private java.lang.String gzhGuid;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	

	private java.lang.String gzhHash;
    /**
     * carTypeGuid       db_column: car_type_guid 
     */ 	

	private java.lang.String carTypeGuid;
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


	public WxModuleContentGzh(){
	}

	public WxModuleContentGzh(
		java.lang.String gzhGuid
	){
		this.gzhGuid = gzhGuid;
	}

	

	public void setGzhGuid(java.lang.String value) {
		this.gzhGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "gzh_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhGuid() {
		return this.gzhGuid;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "car_type_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarTypeGuid() {
		return this.carTypeGuid;
	}
	
	public void setCarTypeGuid(java.lang.String value) {
		this.carTypeGuid = value;
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
			.append("GzhGuid",getGzhGuid())
			.append("GzhHash",getGzhHash())
			.append("CarTypeGuid",getCarTypeGuid())
			.append("DeleteTag",getDeleteTag())
			.append("CreatedOn",getCreatedOn())
			.append("DeleteOn",getDeleteOn())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGzhGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentGzh == false) return false;
		if(this == obj) return true;
		WxModuleContentGzh other = (WxModuleContentGzh)obj;
		return new EqualsBuilder()
			.append(getGzhGuid(),other.getGzhGuid())
			.isEquals();
	}
}

