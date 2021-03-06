package com.tlan.common.model.car;

// Generated 2014-5-15 15:45:17 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxModuleContentCarType generated by hbm2java
 */
@Entity
@Table(name = "wx_module_content_car_type")
public class WxModuleContentCarType implements java.io.Serializable {

	private String carTypeGuid;
	private Integer carTypeSortid;
	private String carTypeTitle;
	private String carTypeName;
	private String carTypeEn;
	private String reserveUrl;
	private String otherinfoUrl;
	private String carTypeCode;
	private String carTypeMinprice;
	private String carTypeMaxprice;
	private String carTypeImageUrl;
	private String carTypeParamUrl;
	private String carTypeParamUrl2;
	private String carTypeParamUrl3;
	private String carTypeMerit;
	private int deleteTag;
	private String createdOn;
	private String deleteOn;
	private String remark;

	public WxModuleContentCarType() {
	}

	public WxModuleContentCarType(String carTypeGuid, int deleteTag) {
		this.carTypeGuid = carTypeGuid;
		this.deleteTag = deleteTag;
	}

	public WxModuleContentCarType(String carTypeGuid, Integer carTypeSortid,
			String carTypeTitle, String carTypeName, String carTypeEn,
			String reserveUrl, String otherinfoUrl, String carTypeCode,
			String carTypeMinprice, String carTypeMaxprice,
			String carTypeImageUrl, String carTypeParamUrl,
			String carTypeParamUrl2, String carTypeParamUrl3,
			String carTypeMerit, int deleteTag, String createdOn,
			String deleteOn, String remark) {
		this.carTypeGuid = carTypeGuid;
		this.carTypeSortid = carTypeSortid;
		this.carTypeTitle = carTypeTitle;
		this.carTypeName = carTypeName;
		this.carTypeEn = carTypeEn;
		this.reserveUrl = reserveUrl;
		this.otherinfoUrl = otherinfoUrl;
		this.carTypeCode = carTypeCode;
		this.carTypeMinprice = carTypeMinprice;
		this.carTypeMaxprice = carTypeMaxprice;
		this.carTypeImageUrl = carTypeImageUrl;
		this.carTypeParamUrl = carTypeParamUrl;
		this.carTypeParamUrl2 = carTypeParamUrl2;
		this.carTypeParamUrl3 = carTypeParamUrl3;
		this.carTypeMerit = carTypeMerit;
		this.deleteTag = deleteTag;
		this.createdOn = createdOn;
		this.deleteOn = deleteOn;
		this.remark = remark;
	}

	@Id
	@Column(name = "car_type_guid", unique = true, nullable = false, length = 36)
	public String getCarTypeGuid() {
		return this.carTypeGuid;
	}

	public void setCarTypeGuid(String carTypeGuid) {
		this.carTypeGuid = carTypeGuid;
	}

	@Column(name = "car_type_sortid")
	public Integer getCarTypeSortid() {
		return this.carTypeSortid;
	}

	public void setCarTypeSortid(Integer carTypeSortid) {
		this.carTypeSortid = carTypeSortid;
	}

	@Column(name = "car_type_title", length = 200)
	public String getCarTypeTitle() {
		return this.carTypeTitle;
	}

	public void setCarTypeTitle(String carTypeTitle) {
		this.carTypeTitle = carTypeTitle;
	}

	@Column(name = "car_type_name")
	public String getCarTypeName() {
		return this.carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	@Column(name = "car_type_en")
	public String getCarTypeEn() {
		return this.carTypeEn;
	}

	public void setCarTypeEn(String carTypeEn) {
		this.carTypeEn = carTypeEn;
	}

	@Column(name = "reserve_url", length = 200)
	public String getReserveUrl() {
		return this.reserveUrl;
	}

	public void setReserveUrl(String reserveUrl) {
		this.reserveUrl = reserveUrl;
	}

	@Column(name = "otherinfo_url", length = 200)
	public String getOtherinfoUrl() {
		return this.otherinfoUrl;
	}

	public void setOtherinfoUrl(String otherinfoUrl) {
		this.otherinfoUrl = otherinfoUrl;
	}

	@Column(name = "car_type_code", length = 20)
	public String getCarTypeCode() {
		return this.carTypeCode;
	}

	public void setCarTypeCode(String carTypeCode) {
		this.carTypeCode = carTypeCode;
	}

	@Column(name = "car_type_minprice", length = 36)
	public String getCarTypeMinprice() {
		return this.carTypeMinprice;
	}

	public void setCarTypeMinprice(String carTypeMinprice) {
		this.carTypeMinprice = carTypeMinprice;
	}

	@Column(name = "car_type_maxprice", length = 36)
	public String getCarTypeMaxprice() {
		return this.carTypeMaxprice;
	}

	public void setCarTypeMaxprice(String carTypeMaxprice) {
		this.carTypeMaxprice = carTypeMaxprice;
	}

	@Column(name = "car_type_imageURL", length = 200)
	public String getCarTypeImageUrl() {
		return this.carTypeImageUrl;
	}

	public void setCarTypeImageUrl(String carTypeImageUrl) {
		this.carTypeImageUrl = carTypeImageUrl;
	}

	@Column(name = "car_type_paramURL", length = 200)
	public String getCarTypeParamUrl() {
		return this.carTypeParamUrl;
	}

	public void setCarTypeParamUrl(String carTypeParamUrl) {
		this.carTypeParamUrl = carTypeParamUrl;
	}

	@Column(name = "car_type_paramURL2", length = 200)
	public String getCarTypeParamUrl2() {
		return this.carTypeParamUrl2;
	}

	public void setCarTypeParamUrl2(String carTypeParamUrl2) {
		this.carTypeParamUrl2 = carTypeParamUrl2;
	}

	@Column(name = "car_type_paramURL3", length = 200)
	public String getCarTypeParamUrl3() {
		return this.carTypeParamUrl3;
	}

	public void setCarTypeParamUrl3(String carTypeParamUrl3) {
		this.carTypeParamUrl3 = carTypeParamUrl3;
	}

	@Column(name = "car_type_merit", length = 65535)
	public String getCarTypeMerit() {
		return this.carTypeMerit;
	}

	public void setCarTypeMerit(String carTypeMerit) {
		this.carTypeMerit = carTypeMerit;
	}

	@Column(name = "delete_tag", nullable = false)
	public int getDeleteTag() {
		return this.deleteTag;
	}

	public void setDeleteTag(int deleteTag) {
		this.deleteTag = deleteTag;
	}

	@Column(name = "created_on", length = 25)
	public String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "delete_on", length = 25)
	public String getDeleteOn() {
		return this.deleteOn;
	}

	public void setDeleteOn(String deleteOn) {
		this.deleteOn = deleteOn;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
