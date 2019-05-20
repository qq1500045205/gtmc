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
@Table(name = "wx_module_content_car")
public class WxModuleContentCar implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentCar";
	public static final String ALIAS_CAR_GUID = "carGuid";
	public static final String ALIAS_CAR_SORTID = "carSortid";
	public static final String ALIAS_CAR_COLOR = "carColor";
	public static final String ALIAS_CAR_NAME = "carName";
	public static final String ALIAS_CAR_PRICE = "carPrice";
	public static final String ALIAS_CAR_CARTYPE_GUID = "carCartypeGuid";
	public static final String ALIAS_DELETE_TAG = "0表示未删除,1表示已删除";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_DELETE_ON = "deleteOn";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * carGuid       db_column: car_guid 
     */ 	
	private java.lang.String carGuid;
    /**
     * carSortid       db_column: car_sortid 
     */ 	

	private java.lang.String carSortid;
    /**
     * carColor       db_column: car_color 
     */ 	

	private java.lang.String carColor;
    /**
     * carName       db_column: car_name 
     */ 	

	private java.lang.String carName;
    /**
     * carPrice       db_column: car_price 
     */ 	

	private java.lang.String carPrice;
    /**
     * carCartypeGuid       db_column: car_cartype_guid 
     */ 	

	private java.lang.String carCartypeGuid;
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


	public WxModuleContentCar(){
	}

	public WxModuleContentCar(
		java.lang.String carGuid
	){
		this.carGuid = carGuid;
	}

	

	public void setCarGuid(java.lang.String value) {
		this.carGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "car_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarGuid() {
		return this.carGuid;
	}
	
	@Column(name = "car_sortid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarSortid() {
		return this.carSortid;
	}
	
	public void setCarSortid(java.lang.String value) {
		this.carSortid = value;
	}
	
	@Column(name = "car_color", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCarColor() {
		return this.carColor;
	}
	
	public void setCarColor(java.lang.String value) {
		this.carColor = value;
	}
	
	@Column(name = "car_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCarName() {
		return this.carName;
	}
	
	public void setCarName(java.lang.String value) {
		this.carName = value;
	}
	
	@Column(name = "car_price", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarPrice() {
		return this.carPrice;
	}
	
	public void setCarPrice(java.lang.String value) {
		this.carPrice = value;
	}
	
	@Column(name = "car_cartype_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarCartypeGuid() {
		return this.carCartypeGuid;
	}
	
	public void setCarCartypeGuid(java.lang.String value) {
		this.carCartypeGuid = value;
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
			.append("CarGuid",getCarGuid())
			.append("CarSortid",getCarSortid())
			.append("CarColor",getCarColor())
			.append("CarName",getCarName())
			.append("CarPrice",getCarPrice())
			.append("CarCartypeGuid",getCarCartypeGuid())
			.append("DeleteTag",getDeleteTag())
			.append("CreatedOn",getCreatedOn())
			.append("DeleteOn",getDeleteOn())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCarGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentCar == false) return false;
		if(this == obj) return true;
		WxModuleContentCar other = (WxModuleContentCar)obj;
		return new EqualsBuilder()
			.append(getCarGuid(),other.getCarGuid())
			.isEquals();
	}
}

