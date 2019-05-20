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
@Table(name = "wx_project")
public class WxProject implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
    /**
     * projectGuid       db_column: project_guid 
     */ 	
	private java.lang.String projectGuid;
    /**
     * projectName       db_column: project_name 
     */ 	
	private java.lang.String projectName;
    /**
     * createdOn       db_column: created_on 
     */ 	
	private java.lang.String createdOn;
    /**
     * areaGuid       db_column: area_guid 
     */ 	
	private java.lang.String areaGuid;
    /**
     * 详细地址       db_column: address 
     */ 	
	private java.lang.String address;
    /**
     * 咨询电话       db_column: mobile 
     */ 	
	private java.lang.String mobile;
    /**
     * 展示图片       db_column: image 
     */ 	
	private java.lang.String image;
    /**
     * 项目描述       db_column: description 
     */ 	
	private java.lang.String description;
	/**
     * gzhType       db_column: gzh_type 
     */ 	
	private java.lang.String gzhType;
    /**
     * 救援电话       db_column: help_tel 
     */ 	
	private java.lang.String helpTel;
    /**
     * 销售店       db_column: dealer_code 
     */ 	
	private java.lang.String dealerCode;
    /**
     * 1：有效 0无效 默认值1       db_column: status 
     */ 	
	
	private java.lang.Integer status;
    /**
     * 所属省份编码       db_column: dealer_province_code 
     */ 	
	private java.lang.String dealerProvinceCode;
    /**
     * 所属城市编码       db_column: dealer_city_code 
     */ 	
	private java.lang.String dealerCityCode;
    /**
     * 1全量同步       db_column: conduct_type 
     */ 	
	
	private java.lang.Integer conductType;
    /**
     * dealerArea       db_column: dealer_area 
     */ 	
	private java.lang.String dealerArea;
    /**
     * 传真       db_column: fax 
     */ 	
	private java.lang.String fax;
    /**
     * 经营品牌       db_column: brand 
     */ 	
	private java.lang.String brand;
    /**
     * 网址       db_column: dealer_url 
     */ 	
	private java.lang.String dealerUrl;
    /**
     * 售后电话       db_column: as_tel 
     */ 	
	private java.lang.String asTel;
    /**
     * 邮箱       db_column: email 
     */ 	
	private java.lang.String email;
    /**
     * 经度       db_column: longitude 
     */ 	
	private java.lang.String longitude;
    /**
     * 纬度       db_column: latitude 
     */ 	
	private java.lang.String latitude;
    /**
     * 备注       db_column: remark 
     */ 	
	private java.lang.String remark;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	private java.lang.String createTime;
    /**
     * 最后更新时间       db_column: last_update_time 
     */ 	
	private java.lang.String lastUpdateTime;
	//columns END


	public WxProject(){
	}

	public WxProject(
		java.lang.String projectGuid
	){
		this.projectGuid = projectGuid;
	}

	

	public void setProjectGuid(java.lang.String value) {
		this.projectGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "project_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getProjectGuid() {
		return this.projectGuid;
	}
	
	@Column(name = "project_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getProjectName() {
		return this.projectName;
	}
	
	public void setProjectName(java.lang.String value) {
		this.projectName = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "area_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getAreaGuid() {
		return this.areaGuid;
	}
	
	public void setAreaGuid(java.lang.String value) {
		this.areaGuid = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	@Column(name = "image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getImage() {
		return this.image;
	}
	
	public void setImage(java.lang.String value) {
		this.image = value;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	@Column(name = "gzh_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getGzhType() {
		return gzhType;
	}

	public void setGzhType(java.lang.String gzhType) {
		this.gzhType = gzhType;
	}

	@Column(name = "help_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getHelpTel() {
		return this.helpTel;
	}
	
	public void setHelpTel(java.lang.String value) {
		this.helpTel = value;
	}
	
	@Column(name = "dealer_code", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDealerCode() {
		return this.dealerCode;
	}
	
	public void setDealerCode(java.lang.String value) {
		this.dealerCode = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
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
	
	@Column(name = "conduct_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getConductType() {
		return this.conductType;
	}
	
	public void setConductType(java.lang.Integer value) {
		this.conductType = value;
	}
	
	@Column(name = "dealer_area", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public java.lang.String getDealerArea() {
		return this.dealerArea;
	}
	
	public void setDealerArea(java.lang.String value) {
		this.dealerArea = value;
	}
	
	@Column(name = "fax", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getFax() {
		return this.fax;
	}
	
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	
	@Column(name = "brand", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getBrand() {
		return this.brand;
	}
	
	public void setBrand(java.lang.String value) {
		this.brand = value;
	}
	
	@Column(name = "dealer_url", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getDealerUrl() {
		return this.dealerUrl;
	}
	
	public void setDealerUrl(java.lang.String value) {
		this.dealerUrl = value;
	}
	
	@Column(name = "as_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAsTel() {
		return this.asTel;
	}
	
	public void setAsTel(java.lang.String value) {
		this.asTel = value;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "longitude", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(java.lang.String value) {
		this.longitude = value;
	}
	
	@Column(name = "latitude", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(java.lang.String value) {
		this.latitude = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	@Column(name = "last_update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(java.lang.String value) {
		this.lastUpdateTime = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProjectGuid",getProjectGuid())
			.append("ProjectName",getProjectName())
			.append("CreatedOn",getCreatedOn())
			.append("AreaGuid",getAreaGuid())
			.append("Address",getAddress())
			.append("Mobile",getMobile())
			.append("Image",getImage())
			.append("Description",getDescription())
			.append("HelpTel",getHelpTel())
			.append("DealerCode",getDealerCode())
			.append("Status",getStatus())
			.append("DealerProvinceCode",getDealerProvinceCode())
			.append("DealerCityCode",getDealerCityCode())
			.append("ConductType",getConductType())
			.append("DealerArea",getDealerArea())
			.append("Fax",getFax())
			.append("Brand",getBrand())
			.append("DealerUrl",getDealerUrl())
			.append("AsTel",getAsTel())
			.append("Email",getEmail())
			.append("Longitude",getLongitude())
			.append("Latitude",getLatitude())
			.append("Remark",getRemark())
			.append("CreateTime",getCreateTime())
			.append("LastUpdateTime",getLastUpdateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProjectGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxProject == false) return false;
		if(this == obj) return true;
		WxProject other = (WxProject)obj;
		return new EqualsBuilder()
			.append(getProjectGuid(),other.getProjectGuid())
			.isEquals();
	}
}

