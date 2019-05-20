package com.tlan.common.model.promotion;

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
@Table(name = "wx_module_content_promotion")
public class WxModuleContentPromotion implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * promotionGuid db_column: promotion_guid
	 */
	@Length(max = 64)
	private java.lang.String promotionGuid;
	/**
	 * promotionTitle db_column: promotion_title
	 */
	@Length(max = 200)
	private java.lang.String promotionTitle;
	/**
	 * promotionSummary db_column: promotion_summary
	 */
	@Length(max = 400)
	private java.lang.String promotionSummary;
	/**
	 * 截至日期 db_column: promotion_deadline
	 */
	@Length(max = 25)
	private java.lang.String promotionDeadline;
	/**
	 * promotionPicture db_column: promotion_picture
	 */
	@Length(max = 200)
	private java.lang.String promotionPicture;
	/**
	 * promotionContent db_column: promotion_content
	 */
	@Length(max = 65535)
	private java.lang.String promotionContent;
	/**
	 * order db_column: order
	 */

	private java.lang.Integer sort;
	/**
	 * 0 车型无关 1车型有关 db_column: car_type
	 */

	private java.lang.Integer carType;
	/**
	 * carTypeGuid db_column: car_type_guid
	 */
	@Length(max = 36)
	private java.lang.String carTypeGuid;
	/**
	 * carTypeName db_column: car_type_name
	 */
	@Length(max = 200)
	private java.lang.String carTypeName;
	/**
	 * 0 未发布 1 已发布 db_column: status
	 */

	private java.lang.Integer status;
	/**
	 * 0 正常 1 删除 db_column: del_flag
	 */

	private java.lang.Integer delFlag;
	/**
	 * 公众号hash db_column: gzh_hash
	 */
	@Length(max = 36)
	private java.lang.String gzhHash;
	/**
	 * createdOn db_column: created_on
	 */
	@Length(max = 25)
	private java.lang.String createdOn;
	/**
	 * createdBy db_column: created_by
	 */
	@Length(max = 50)
	private java.lang.String createdBy;
	/**
	 * modifyOn db_column: modify_on
	 */
	@Length(max = 25)
	private java.lang.String modifyOn;
	/**
	 * modifyBy db_column: modify_by
	 */
	@Length(max = 50)
	private java.lang.String modifyBy;
	/**
	 * mobile db_column: mobile
	 */
	@Length(max = 20)
	private java.lang.String mobile;

	// columns END

	public WxModuleContentPromotion() {
	}

	public WxModuleContentPromotion(java.lang.String promotionGuid) {
		this.promotionGuid = promotionGuid;
	}

	public void setPromotionGuid(java.lang.String value) {
		this.promotionGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "uuid")
	@Column(name = "promotion_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getPromotionGuid() {
		return this.promotionGuid;
	}

	@Column(name = "promotion_title", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPromotionTitle() {
		return this.promotionTitle;
	}

	public void setPromotionTitle(java.lang.String value) {
		this.promotionTitle = value;
	}

	@Column(name = "promotion_summary", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	public java.lang.String getPromotionSummary() {
		return this.promotionSummary;
	}

	public void setPromotionSummary(java.lang.String value) {
		this.promotionSummary = value;
	}

	@Column(name = "promotion_deadline", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPromotionDeadline() {
		return this.promotionDeadline;
	}

	public void setPromotionDeadline(java.lang.String value) {
		this.promotionDeadline = value;
	}

	@Column(name = "promotion_picture", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPromotionPicture() {
		return this.promotionPicture;
	}

	public void setPromotionPicture(java.lang.String value) {
		this.promotionPicture = value;
	}

	@Column(name = "promotion_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPromotionContent() {
		return this.promotionContent;
	}

	public void setPromotionContent(java.lang.String value) {
		this.promotionContent = value;
	}

	@Column(name = "sort", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSort() {
		return this.sort;
	}

	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}

	@Column(name = "car_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getCarType() {
		return this.carType;
	}

	public void setCarType(java.lang.Integer value) {
		this.carType = value;
	}

	@Column(name = "car_type_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarTypeGuid() {
		return this.carTypeGuid;
	}

	public void setCarTypeGuid(java.lang.String value) {
		this.carTypeGuid = value;
	}

	@Column(name = "car_type_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCarTypeName() {
		return this.carTypeName;
	}

	public void setCarTypeName(java.lang.String value) {
		this.carTypeName = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "del_flag", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(java.lang.Integer value) {
		this.delFlag = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}

	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}

	@Column(name = "mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("PromotionGuid", getPromotionGuid())
				.append("PromotionTitle", getPromotionTitle())
				.append("PromotionSummary", getPromotionSummary())
				.append("PromotionDeadline", getPromotionDeadline())
				.append("PromotionPicture", getPromotionPicture())
				.append("PromotionContent", getPromotionContent())
				.append("Order", getSort()).append("CarType", getCarType())
				.append("CarTypeGuid", getCarTypeGuid())
				.append("CarTypeName", getCarTypeName())
				.append("Status", getStatus()).append("DelFlag", getDelFlag())
				.append("GzhHash", getGzhHash())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getPromotionGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContentPromotion == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContentPromotion other = (WxModuleContentPromotion) obj;
		return new EqualsBuilder().append(getPromotionGuid(),
				other.getPromotionGuid()).isEquals();
	}
}
