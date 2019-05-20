package com.tlan.common.model.active;

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
@Table(name = "wx_module_content_active")
public class WxModuleContentActive implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * actGuid db_column: act_guid
	 */
	private java.lang.String actGuid;
	/**
	 * 活动名称 db_column: act_name
	 */
	private java.lang.String actName;
	/**
	 * 活动描述 db_column: act_desc
	 */
	private java.lang.String actDesc;
	/**
	 * 图片 db_column: act_image
	 */
	private java.lang.String actImage;
	/**
	 * 活动详情 db_column: act_content
	 */
	private java.lang.String actContent;
	/**
	 * 1:支持报名 0：不支持报名 db_column: act_type
	 */
	private java.lang.Integer actType;
	/**
	 * 1：针对车主 0：针对非车主 db_column: act_type2
	 */
	private java.lang.Integer actType2;
	/**
	 * 0：未发布 1：已发布 db_column: status
	 */
	private java.lang.Integer status;
	/**
	 * 开始时间 db_column: start_on
	 */
	private java.lang.String startOn;
	/**
	 * 结束时间 db_column: end_on
	 */
	private java.lang.String endOn;
	/**
	 * gzhHash db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * 来源：1：集团下发，0：自行添加，2：提取 db_column: source
	 */
	private java.lang.Integer source;
	/**
	 * 乘车路线 db_column: line
	 */
	private java.lang.String line;
	/**
	 * 成人数上线 db_column: max_num
	 */
	private java.lang.Integer maxNum;
	/**
	 * 孩子数上线 db_column: child_max
	 */
	private java.lang.Integer childMax;
	/**
	 * createdBy db_column: created_by
	 */
	private java.lang.String createdBy;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * modifyBy db_column: modify_by
	 */
	private java.lang.String modifyBy;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * publishOn db_column: publish_on
	 */
	private java.lang.String publishOn;
	/**
	 * publishBy db_column: publish_by
	 */
	private java.lang.String publishBy;
	/**
	 * carTypeGuid db_column: car_type_guid
	 */
	private java.lang.String carTypeGuid;
	/**
	 * carTypeTitle db_column: car_type_title
	 */
	private java.lang.String carTypeTitle;
	/**
	 * delFlag db_column: del_flag
	 */
	private int delFlag;

	// columns END

	public WxModuleContentActive() {
	}

	public WxModuleContentActive(java.lang.String actGuid) {
		this.actGuid = actGuid;
	}

	public void setActGuid(java.lang.String value) {
		this.actGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "uuid")
	@Column(name = "act_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getActGuid() {
		return this.actGuid;
	}

	@Column(name = "act_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getActName() {
		return this.actName;
	}

	public void setActName(java.lang.String value) {
		this.actName = value;
	}

	@Column(name = "act_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getActDesc() {
		return this.actDesc;
	}

	public void setActDesc(java.lang.String value) {
		this.actDesc = value;
	}

	@Column(name = "act_image", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getActImage() {
		return this.actImage;
	}

	public void setActImage(java.lang.String value) {
		this.actImage = value;
	}

	@Column(name = "act_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getActContent() {
		return this.actContent;
	}

	public void setActContent(java.lang.String value) {
		this.actContent = value;
	}

	@Column(name = "act_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActType() {
		return this.actType;
	}

	public void setActType(java.lang.Integer value) {
		this.actType = value;
	}
	
	@Column(name = "act_type2", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActType2() {
		return this.actType2;
	}

	public void setActType2(java.lang.Integer value) {
		this.actType2 = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "start_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getStartOn() {
		return this.startOn;
	}

	public void setStartOn(java.lang.String value) {
		this.startOn = value;
	}

	@Column(name = "end_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getEndOn() {
		return this.endOn;
	}

	public void setEndOn(java.lang.String value) {
		this.endOn = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "source", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getSource() {
		return this.source;
	}

	public void setSource(java.lang.Integer value) {
		this.source = value;
	}

	@Column(name = "line", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getLine() {
		return this.line;
	}

	public void setLine(java.lang.String value) {
		this.line = value;
	}

	@Column(name = "max_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMaxNum() {
		return this.maxNum;
	}

	public void setMaxNum(java.lang.Integer value) {
		this.maxNum = value;
	}

	@Column(name = "child_max", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getChildMax() {
		return this.childMax;
	}

	public void setChildMax(java.lang.Integer value) {
		this.childMax = value;
	}

	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}

	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "publish_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPublishOn() {
		return this.publishOn;
	}

	public void setPublishOn(java.lang.String value) {
		this.publishOn = value;
	}

	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}

	@Column(name = "car_type_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarTypeGuid() {
		return carTypeGuid;
	}

	public void setCarTypeGuid(java.lang.String carTypeGuid) {
		this.carTypeGuid = carTypeGuid;
	}

	@Column(name = "car_type_title", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCarTypeTitle() {
		return carTypeTitle;
	}

	public void setCarTypeTitle(java.lang.String carTypeTitle) {
		this.carTypeTitle = carTypeTitle;
	}

	@Column(name = "del_flag", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("ActGuid", getActGuid())
				.append("ActName", getActName())
				.append("ActDesc", getActDesc())
				.append("ActImage", getActImage())
				.append("ActContent", getActContent())
				.append("ActType", getActType()).append("Status", getStatus())
				.append("StartOn", getStartOn()).append("EndOn", getEndOn())
				.append("GzhHash", getGzhHash()).append("Source", getSource())
				.append("Line", getLine()).append("MaxNum", getMaxNum())
				.append("ChildMax", getChildMax())
				.append("CreatedBy", getCreatedBy())
				.append("CreatedOn", getCreatedOn())
				.append("ModifyBy", getModifyBy())
				.append("ModifyOn", getModifyOn())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("CarTypeGuid", getCarTypeGuid())
				.append("CarTypeTitle", getCarTypeTitle())
				.append("DelFlag", getDelFlag()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getActGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContentActive == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContentActive other = (WxModuleContentActive) obj;
		return new EqualsBuilder().append(getActGuid(), other.getActGuid())
				.isEquals();
	}
}
