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

import net.sf.oval.constraint.Length;

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
@Table(name = "wx_user_location")
public class WxUserLocation implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxUserLocation";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_LATITUDE = "latitude";
	public static final String ALIAS_LONGITUDE = "longitude";
	public static final String ALIAS_UPDATE_TIME = "updateTime";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * latitude       db_column: latitude 
     */ 	
	
	private java.lang.String latitude;
    /**
     * longitude       db_column: longitude 
     */ 	
	
	private java.lang.String longitude;
    /**
     * updateTime       db_column: update_time 
     */ 	
	private java.lang.String updateTime;
	//columns END


	public WxUserLocation(){
	}

	public WxUserLocation(
		java.lang.String openId
	){
		this.openId = openId;
	}

	

	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "open_id", unique = true, nullable = false, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	@Column(name = "latitude", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(java.lang.String value) {
		this.latitude = value;
	}
	
	@Column(name = "longitude", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(java.lang.String value) {
		this.longitude = value;
	}
	
	@Column(name = "update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OpenId",getOpenId())
			.append("Latitude",getLatitude())
			.append("Longitude",getLongitude())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOpenId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxUserLocation == false) return false;
		if(this == obj) return true;
		WxUserLocation other = (WxUserLocation)obj;
		return new EqualsBuilder()
			.append(getOpenId(),other.getOpenId())
			.isEquals();
	}
}

