package com.tlan.common.model.creditactive;

// Generated 2014-6-17 14:39:59 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * WxProductListF generated by hbm2java
 */
@Entity
@Table(name = "wx_product_list_f")
public class WxProductListF implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716649305074505174L;
	private String productGuid;
	private String productName;
	private String productKey;
	private String productPrice;
	private String productDate;
	private String productLogo;
	private String productDoc;
	private String productSpec;
	private Integer productCredit;
	private String productTypeId;
	private Integer productStock;
	private Integer status;
	private String publishOn;
	private String publishBy;
	private String createdBy;
	private String createdOn;
	private String modifyBy;
	private String modifyOn;
	private Integer delFlag;
	private Integer updateCount;
	
	
	public WxProductListF() {
	}

	public WxProductListF(String productGuid) {
		this.productGuid = productGuid;
	}

	public WxProductListF(String productGuid, String productName,
			String productKey, String productPrice, String productDate,
			String productLogo, String productDoc, String productSpec,
			Integer productCredit, String productTypeId, Integer productStock,
			Integer status, String publishOn, String publishBy,
			String createdBy, String createdOn, String modifyBy,
			String modifyOn, Integer delFlag, Integer updateCount) {
		this.productGuid = productGuid;
		this.productName = productName;
		this.productKey = productKey;
		this.productPrice = productPrice;
		this.productDate = productDate;
		this.productLogo = productLogo;
		this.productDoc = productDoc;
		this.productSpec = productSpec;
		this.productCredit = productCredit;
		this.productTypeId = productTypeId;
		this.productStock = productStock;
		this.status = status;
		this.publishOn = publishOn;
		this.publishBy = publishBy;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifyBy = modifyBy;
		this.modifyOn = modifyOn;
		this.delFlag = delFlag;
		this.updateCount = updateCount;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "product_guid", unique = true, nullable = false, length = 36)
	public String getProductGuid() {
		return this.productGuid;
	}

	public void setProductGuid(String productGuid) {
		this.productGuid = productGuid;
	}

	@Column(name = "product_name", length = 100)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "product_key", length = 100)
	public String getProductKey() {
		return this.productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	@Column(name = "product_price", length = 100)
	public String getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "product_date", length = 25)
	public String getProductDate() {
		return this.productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	@Column(name = "product_logo", length = 200)
	public String getProductLogo() {
		return this.productLogo;
	}

	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}

	@Column(name = "product_doc", length = 65535)
	public String getProductDoc() {
		return this.productDoc;
	}

	public void setProductDoc(String productDoc) {
		this.productDoc = productDoc;
	}

	@Column(name = "product_spec", length = 65535)
	public String getProductSpec() {
		return this.productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	@Column(name = "product_credit")
	public Integer getProductCredit() {
		return this.productCredit;
	}

	public void setProductCredit(Integer productCredit) {
		this.productCredit = productCredit;
	}

	@Column(name = "product_type_id", length = 36)
	public String getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(name = "product_stock")
	public Integer getProductStock() {
		return this.productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "publish_on", length = 25)
	public String getPublishOn() {
		return this.publishOn;
	}

	public void setPublishOn(String publishOn) {
		this.publishOn = publishOn;
	}

	@Column(name = "publish_by", length = 36)
	public String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(String publishBy) {
		this.publishBy = publishBy;
	}

	@Column(name = "created_by", length = 36)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_on", length = 25)
	public String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "modify_by", length = 36)
	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@Column(name = "modify_on", length = 25)
	public String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	@Column(name = "del_flag")
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "update_count")
	public Integer getUpdateCount() {
		return this.updateCount;
	}

	public void setUpdateCount(Integer updateCount) {
		this.updateCount = updateCount;
	}

}
