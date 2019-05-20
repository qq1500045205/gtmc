/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.yuyue;

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
@Table(name = "wx_module_content_yuyue")
public class WxModuleContentYuyue implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentYuyue";
	public static final String ALIAS_YUYUE_GUID = "yuyueGuid";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_MODIFIED_BY = "modifiedBy";
	public static final String ALIAS_MODIFIED_ON = "modifiedOn";
	public static final String ALIAS_OPENID = "openid";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_USER_GUID = "userGuid";
	public static final String ALIAS_YUYUE_FIELD1 = "yuyueField1";
	public static final String ALIAS_YUYUE_FIELD2 = "yuyueField2";
	public static final String ALIAS_YUYUE_FIELD3 = "yuyueField3";
	public static final String ALIAS_YUYUE_FIELD4 = "yuyueField4";
	public static final String ALIAS_YUYUE_FIELD5 = "yuyueField5";
	public static final String ALIAS_YUYUE_FIELD6 = "yuyueField6";
	public static final String ALIAS_YUYUE_MEMO = "yuyueMemo";
	public static final String ALIAS_YUYUE_TIME = "yuyueTime";
	public static final String ALIAS_YUYUE_TYPE = "1: 试驾 2: 保养 3: 二手车 4: 年审";
	
	//date formats
	

	//可以直接使用: //@Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * yuyueGuid       db_column: yuyue_guid 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueGuid;
    /**
     * createdBy       db_column: created_by 
     */ 	
	//@Length(max=64)
	private java.lang.String createdBy;
    /**
     * createdOn       db_column: created_on 
     */ 	
	//@Length(max=64)
	private java.lang.String createdOn;
    /**
     * modifiedBy       db_column: modified_by 
     */ 	
	//@Length(max=64)
	private java.lang.String modifiedBy;
    /**
     * modifiedOn       db_column: modified_on 
     */ 	
	//@Length(max=64)
	private java.lang.String modifiedOn;
    /**
     * openid       db_column: openid 
     */ 	
	//@Length(max=64)
	private java.lang.String openid;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	
	//@Length(max=64)
	private java.lang.String gzhHash;
    /**
     * userGuid       db_column: user_guid 
     */ 	
	//@Length(max=200)
	private java.lang.String userGuid;
	
	private java.lang.String dealerGuid;
    /**
     * yuyueField1       db_column: yuyue_field_1 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField1;
    /**
     * yuyueField2       db_column: yuyue_field_2 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField2;
    /**
     * yuyueField3       db_column: yuyue_field_3 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField3;
    /**
     * yuyueField4       db_column: yuyue_field_4 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField4;
    /**
     * yuyueField5       db_column: yuyue_field_5 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField5;
    /**
     * yuyueField6       db_column: yuyue_field_6 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField6;
    /**
     * yuyueMemo       db_column: yuyue_memo 
     */ 	
	//@Length(max=200)
	private java.lang.String yuyueMemo;
    /**
     * yuyueTime       db_column: yuyue_time 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueTime;
    /**
     * 1: 试驾 2: 保养 3: 二手车 4: 年审       db_column: yuyue_type 
     */ 	
	//@Length(max=10)
	private java.lang.String yuyueType;
	//columns END


	public WxModuleContentYuyue(){
	}

	public WxModuleContentYuyue(
		java.lang.String yuyueGuid
	){
		this.yuyueGuid = yuyueGuid;
	}

	

	public void setYuyueGuid(java.lang.String value) {
		this.yuyueGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "yuyue_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueGuid() {
		return this.yuyueGuid;
	}
	
	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "modified_by", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getModifiedBy() {
		return this.modifiedBy;
	}
	
	public void setModifiedBy(java.lang.String value) {
		this.modifiedBy = value;
	}
	
	@Column(name = "modified_on", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getModifiedOn() {
		return this.modifiedOn;
	}
	
	public void setModifiedOn(java.lang.String value) {
		this.modifiedOn = value;
	}
	
	@Column(name = "openid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "dealer_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getDealerGuid() {
		return this.dealerGuid;
	}
	
	public void setDealerGuid(java.lang.String value) {
		this.dealerGuid = value;
	}
	
	@Column(name = "user_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}
	
	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}
	
	@Column(name = "yuyue_field_1", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField1() {
		return this.yuyueField1;
	}
	
	public void setYuyueField1(java.lang.String value) {
		this.yuyueField1 = value;
	}
	
	@Column(name = "yuyue_field_2", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField2() {
		return this.yuyueField2;
	}
	
	public void setYuyueField2(java.lang.String value) {
		this.yuyueField2 = value;
	}
	
	@Column(name = "yuyue_field_3", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField3() {
		return this.yuyueField3;
	}
	
	public void setYuyueField3(java.lang.String value) {
		this.yuyueField3 = value;
	}
	
	@Column(name = "yuyue_field_4", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField4() {
		return this.yuyueField4;
	}
	
	public void setYuyueField4(java.lang.String value) {
		this.yuyueField4 = value;
	}
	
	@Column(name = "yuyue_field_5", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField5() {
		return this.yuyueField5;
	}
	
	public void setYuyueField5(java.lang.String value) {
		this.yuyueField5 = value;
	}
	
	@Column(name = "yuyue_field_6", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField6() {
		return this.yuyueField6;
	}
	
	public void setYuyueField6(java.lang.String value) {
		this.yuyueField6 = value;
	}
	
	@Column(name = "yuyue_memo", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getYuyueMemo() {
		return this.yuyueMemo;
	}
	
	public void setYuyueMemo(java.lang.String value) {
		this.yuyueMemo = value;
	}
	
	@Column(name = "yuyue_time", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueTime() {
		return this.yuyueTime;
	}
	
	public void setYuyueTime(java.lang.String value) {
		this.yuyueTime = value;
	}
	
	@Column(name = "yuyue_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getYuyueType() {
		return this.yuyueType;
	}
	
	public void setYuyueType(java.lang.String value) {
		this.yuyueType = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("YuyueGuid",getYuyueGuid())
			.append("CreatedBy",getCreatedBy())
			.append("CreatedOn",getCreatedOn())
			.append("ModifiedBy",getModifiedBy())
			.append("ModifiedOn",getModifiedOn())
			.append("Openid",getOpenid())
			.append("GzhHash",getGzhHash())
			.append("DealerGuid",getGzhHash())
			.append("UserGuid",getUserGuid())
			.append("YuyueField1",getYuyueField1())
			.append("YuyueField2",getYuyueField2())
			.append("YuyueField3",getYuyueField3())
			.append("YuyueField4",getYuyueField4())
			.append("YuyueField5",getYuyueField5())
			.append("YuyueField6",getYuyueField6())
			.append("YuyueMemo",getYuyueMemo())
			.append("YuyueTime",getYuyueTime())
			.append("YuyueType",getYuyueType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getYuyueGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentYuyue == false) return false;
		if(this == obj) return true;
		WxModuleContentYuyue other = (WxModuleContentYuyue)obj;
		return new EqualsBuilder()
			.append(getYuyueGuid(),other.getYuyueGuid())
			.isEquals();
	}
}

