/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "wx_user2car")
public class WxUser2car implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxUser2car";
	public static final String ALIAS_USER2CAR_GUID = "user2carGuid";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_CAR_VIN = "carVin";
	public static final String ALIAS_CAR_NUMBER_PFX = "carNumberPfx";
	public static final String ALIAS_CAR_NUMBER = "carNumber";
	public static final String ALIAS_CAR_BELONG_PROJECT = "车属于公众号，现在只有 东莞永佳 一家店";
	public static final String ALIAS_CAR_STATUS = "carStatus";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * user2carGuid       db_column: user2car_guid 
     */ 	
	private java.lang.String user2carGuid;
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * carVin       db_column: car_vin 
     */ 	
	private java.lang.String carVin;
    /**
     * carNumberPfx       db_column: car_number_pfx 
     */ 	
	private java.lang.String carNumberPfx;
    /**
     * carNumber       db_column: car_number 
     */ 	
	private java.lang.String carNumber;
    /**
     * 车属于公众号，现在只有 东莞永佳 一家店       db_column: car_belong_project 
     */ 	
	private java.lang.String carBelongProject;
    /**
     * carStatus       db_column: car_status 
     */ 	
	private java.lang.String carStatus;
	//columns END


	public WxUser2car(){
	}

	public WxUser2car(
		java.lang.String user2carGuid
	){
		this.user2carGuid = user2carGuid;
	}

	public void setUser2carGuid(java.lang.String value) {
		this.user2carGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "user2car_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUser2carGuid() {
		return this.user2carGuid;
	}
	
	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "car_vin", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCarVin() {
		return this.carVin;
	}
	
	public void setCarVin(java.lang.String value) {
		this.carVin = value;
	}
	
	@Column(name = "car_number_pfx", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getCarNumberPfx() {
		return this.carNumberPfx;
	}
	
	public void setCarNumberPfx(java.lang.String value) {
		this.carNumberPfx = value;
	}
	
	@Column(name = "car_number", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getCarNumber() {
		return this.carNumber;
	}
	
	public void setCarNumber(java.lang.String value) {
		this.carNumber = value;
	}
	
	@Column(name = "car_belong_project", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarBelongProject() {
		return this.carBelongProject;
	}
	
	public void setCarBelongProject(java.lang.String value) {
		this.carBelongProject = value;
	}
	
	@Column(name = "car_status", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCarStatus() {
		return this.carStatus;
	}
	
	public void setCarStatus(java.lang.String value) {
		this.carStatus = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("User2carGuid",getUser2carGuid())
			.append("OpenId",getOpenId())
			.append("CarVin",getCarVin())
			.append("CarNumberPfx",getCarNumberPfx())
			.append("CarNumber",getCarNumber())
			.append("CarBelongProject",getCarBelongProject())
			.append("CarStatus",getCarStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUser2carGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxUser2car == false) return false;
		if(this == obj) return true;
		WxUser2car other = (WxUser2car)obj;
		return new EqualsBuilder()
			.append(getUser2carGuid(),other.getUser2carGuid())
			.isEquals();
	}
}

