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
@Table(name = "wx_gcloud_province_ctiy")
public class WxGcloudProvinceCtiy implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
    /**
     * provGuid       db_column: prov_guid 
     */ 	
	@Length(max=36)
	private java.lang.String provGuid;
    /**
     * 媒体代码       db_column: media_code 
     */ 	
	@Length(max=36)
	private java.lang.String mediaCode;
    /**
     * 省份名称       db_column: province_name 
     */ 	
	@Length(max=50)
	private java.lang.String provinceName;
    /**
     * 省份编码       db_column: province_code 
     */ 	
	@Length(max=10)
	private java.lang.String provinceCode;
    /**
     * 城市名称       db_column: city_name 
     */ 	
	@Length(max=50)
	private java.lang.String cityName;
    /**
     * 城市编码       db_column: city_code 
     */ 	
	@Length(max=10)
	private java.lang.String cityCode;
    /**
     * 1:有效 0：失效 默认值1       db_column: status 
     */ 	
	
	private java.lang.Integer status;
    /**
     * 1同步       db_column: conduct_type 
     */ 	
	
	private java.lang.Integer conductType;
    /**
     * 创建时间       db_column: create_time 
     */ 	
	@Length(max=20)
	private java.lang.String createTime;
    /**
     * 最后修改时间       db_column: last_update_time 
     */ 	
	@Length(max=20)
	private java.lang.String lastUpdateTime;
    /**
     * 备注       db_column: remark 
     */ 	
	@Length(max=255)
	private java.lang.String remark;
	//columns END


	public WxGcloudProvinceCtiy(){
	}

	public WxGcloudProvinceCtiy(
		java.lang.String provGuid
	){
		this.provGuid = provGuid;
	}

	

	public void setProvGuid(java.lang.String value) {
		this.provGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "uuid")
	@Column(name = "prov_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getProvGuid() {
		return this.provGuid;
	}
	
	@Column(name = "media_code", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getMediaCode() {
		return this.mediaCode;
	}
	
	public void setMediaCode(java.lang.String value) {
		this.mediaCode = value;
	}
	
	@Column(name = "province_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getProvinceName() {
		return this.provinceName;
	}
	
	public void setProvinceName(java.lang.String value) {
		this.provinceName = value;
	}
	
	@Column(name = "province_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getProvinceCode() {
		return this.provinceCode;
	}
	
	public void setProvinceCode(java.lang.String value) {
		this.provinceCode = value;
	}
	
	@Column(name = "city_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(java.lang.String value) {
		this.cityName = value;
	}
	
	@Column(name = "city_code", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getCityCode() {
		return this.cityCode;
	}
	
	public void setCityCode(java.lang.String value) {
		this.cityCode = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "conduct_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getConductType() {
		return this.conductType;
	}
	
	public void setConductType(java.lang.Integer value) {
		this.conductType = value;
	}
	
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	@Column(name = "last_update_time", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(java.lang.String value) {
		this.lastUpdateTime = value;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProvGuid",getProvGuid())
			.append("MediaCode",getMediaCode())
			.append("ProvinceName",getProvinceName())
			.append("ProvinceCode",getProvinceCode())
			.append("CityName",getCityName())
			.append("CityCode",getCityCode())
			.append("Status",getStatus())
			.append("ConductType",getConductType())
			.append("CreateTime",getCreateTime())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getProvGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxGcloudProvinceCtiy == false) return false;
		if(this == obj) return true;
		WxGcloudProvinceCtiy other = (WxGcloudProvinceCtiy)obj;
		return new EqualsBuilder()
			.append(getProvGuid(),other.getProvGuid())
			.isEquals();
	}
}

