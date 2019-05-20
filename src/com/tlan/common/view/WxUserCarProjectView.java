/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.view;

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
@Table(name = "wx_user_car_project_view")
public class WxUserCarProjectView implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxUserCarProjectView";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_USER_NAME = "userName";
	public static final String ALIAS_USER_SEX = "userSex";
	public static final String ALIAS_USER_TEL = "userTel";
	public static final String ALIAS_CAR_VIN = "carVin";
	public static final String ALIAS_CAR_NUMBER_PFX = "carNumberPfx";
	public static final String ALIAS_CAR_NUMBER = "carNumber";
	public static final String ALIAS_PROJECT_GUID = "projectGuid";
	public static final String ALIAS_PROJECT_NAME = "projectName";
	public static final String ALIAS_DEALER_PROVINCE_CODE = "所属省份编码";
	public static final String ALIAS_DEALER_CITY_CODE = "所属城市编码";
	public static final String ALIAS_DEALER_LONGITUDE = "经度";
	public static final String ALIAS_DEALER_LATITUDE = "纬度";
	public static final String ALIAS_DEALER_HELP_TEL = "救援电话";
	public static final String ALIAS_DEALER_AS_TEL = "售后电话";
	public static final String ALIAS_DEALER_GZH_TYPE = "厂商DIST  经销商 DLR";
	public static final String ALIAS_DEALER_ADDRESS = "详细地址";
	public static final String ALIAS_DEALER_CODE = "销售店";
	public static final String ALIAS_DEALER_MOBILE = "咨询电话";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * userName       db_column: user_name 
     */ 	
	private java.lang.String userName;
    /**
     * userSex       db_column: user_sex 
     */ 	
	private java.lang.String userSex;
    /**
     * userTel       db_column: user_tel 
     */ 	
	private java.lang.String userTel;
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
     * projectGuid       db_column: project_guid 
     */ 	
	private java.lang.String projectGuid;
    /**
     * projectName       db_column: project_name 
     */ 	
	private java.lang.String projectName;
    /**
     * 所属省份编码       db_column: dealer_province_code 
     */ 	
	private java.lang.String dealerProvinceCode;
    /**
     * 所属城市编码       db_column: dealer_city_code 
     */ 	
	private java.lang.String dealerCityCode;
    /**
     * 经度       db_column: dealer_longitude 
     */ 	
	private java.lang.String dealerLongitude;
    /**
     * 纬度       db_column: dealer_latitude 
     */ 	
	private java.lang.String dealerLatitude;
    /**
     * 救援电话       db_column: dealer_help_tel 
     */ 	
	private java.lang.String dealerHelpTel;
    /**
     * 售后电话       db_column: dealer_as_tel 
     */ 	
	private java.lang.String dealerAsTel;
    /**
     * 厂商DIST  经销商 DLR       db_column: dealer_gzh_type 
     */ 	
	private java.lang.String dealerGzhType;
    /**
     * 详细地址       db_column: dealer_address 
     */ 	
	private java.lang.String dealerAddress;
    /**
     * 销售店       db_column: dealer_code 
     */ 	
	private java.lang.String dealerCode;
    /**
     * 咨询电话       db_column: dealer_mobile 
     */ 	
	private java.lang.String dealerMobile;
	/**
     * 咨询电话       db_column: user2car_guid 
     */ 	
	private java.lang.String user2carGuid;
	//columns END
	
	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "user2car_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUser2carGuid() {
		return this.openId;
	}
	
	public void setUser2carGuid(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "user_sex", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserSex() {
		return this.userSex;
	}
	
	public void setUserSex(java.lang.String value) {
		this.userSex = value;
	}
	
	@Column(name = "user_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
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
	
	@Column(name = "project_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getProjectGuid() {
		return this.projectGuid;
	}
	
	public void setProjectGuid(java.lang.String value) {
		this.projectGuid = value;
	}
	
	@Column(name = "project_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(java.lang.String value) {
		this.projectName = value;
	}
	
	@Column(name = "dealer_province_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getDealerProvinceCode() {
		return this.dealerProvinceCode;
	}
	
	public void setDealerProvinceCode(java.lang.String value) {
		this.dealerProvinceCode = value;
	}
	
	@Column(name = "dealer_city_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getDealerCityCode() {
		return this.dealerCityCode;
	}
	
	public void setDealerCityCode(java.lang.String value) {
		this.dealerCityCode = value;
	}
	
	@Column(name = "dealer_longitude", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDealerLongitude() {
		return this.dealerLongitude;
	}
	
	public void setDealerLongitude(java.lang.String value) {
		this.dealerLongitude = value;
	}
	
	@Column(name = "dealer_latitude", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDealerLatitude() {
		return this.dealerLatitude;
	}
	
	public void setDealerLatitude(java.lang.String value) {
		this.dealerLatitude = value;
	}
	
	@Column(name = "dealer_help_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getDealerHelpTel() {
		return this.dealerHelpTel;
	}
	
	public void setDealerHelpTel(java.lang.String value) {
		this.dealerHelpTel = value;
	}
	
	@Column(name = "dealer_as_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getDealerAsTel() {
		return this.dealerAsTel;
	}
	
	public void setDealerAsTel(java.lang.String value) {
		this.dealerAsTel = value;
	}
	
	@Column(name = "dealer_gzh_type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getDealerGzhType() {
		return this.dealerGzhType;
	}
	
	public void setDealerGzhType(java.lang.String value) {
		this.dealerGzhType = value;
	}
	
	@Column(name = "dealer_address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDealerAddress() {
		return this.dealerAddress;
	}
	
	public void setDealerAddress(java.lang.String value) {
		this.dealerAddress = value;
	}
	
	@Column(name = "dealer_code", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDealerCode() {
		return this.dealerCode;
	}
	
	public void setDealerCode(java.lang.String value) {
		this.dealerCode = value;
	}
	
	@Column(name = "dealer_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getDealerMobile() {
		return this.dealerMobile;
	}
	
	public void setDealerMobile(java.lang.String value) {
		this.dealerMobile = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OpenId",getOpenId())
			.append("UserName",getUserName())
			.append("UserSex",getUserSex())
			.append("UserTel",getUserTel())
			.append("CarVin",getCarVin())
			.append("CarNumberPfx",getCarNumberPfx())
			.append("CarNumber",getCarNumber())
			.append("ProjectGuid",getProjectGuid())
			.append("ProjectName",getProjectName())
			.append("DealerProvinceCode",getDealerProvinceCode())
			.append("DealerCityCode",getDealerCityCode())
			.append("DealerLongitude",getDealerLongitude())
			.append("DealerLatitude",getDealerLatitude())
			.append("DealerHelpTel",getDealerHelpTel())
			.append("DealerAsTel",getDealerAsTel())
			.append("DealerGzhType",getDealerGzhType())
			.append("DealerAddress",getDealerAddress())
			.append("DealerCode",getDealerCode())
			.append("DealerMobile",getDealerMobile())
			.append("User2carGuid",getUser2carGuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxUserCarProjectView == false) return false;
		if(this == obj) return true;
		WxUserCarProjectView other = (WxUserCarProjectView)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

