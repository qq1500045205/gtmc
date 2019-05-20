package com.tlan.common.model.active;

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
@Table(name = "wx_module_content_sign")
public class WxModuleContentSign implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * signGuid db_column: sign_guid
	 */
	@Length(max = 36)
	private java.lang.String signGuid;
	/**
	 * 活动id db_column: act_guid
	 */
	@Length(max = 36)
	private java.lang.String actGuid;
	/**
	 * 用户id db_column: user_guid
	 */
	@Length(max = 36)
	private java.lang.String userGuid;
	/**
	 * openid db_column: openid
	 */
	@Length(max = 50)
	private java.lang.String openid;
	/**
	 * 参加时间 db_column: attend_on
	 */
	@Length(max = 25)
	private java.lang.String attendOn;
	/**
	 * 到场方式 db_column: arrive_by
	 */
	@Length(max = 255)
	private java.lang.String arriveBy;
	/**
	 * 参加成人数 db_column: attend_num
	 */

	private java.lang.Integer attendNum;
	/**
	 * 参加孩子数 db_column: attend_child
	 */

	private java.lang.Integer attendChild;
	/**
	 * 备注 db_column: remark
	 */
	@Length(max = 1000)
	private java.lang.String remark;
	/**
	 * 活动名称 db_column: act_name
	 */
	@Length(max = 255)
	private java.lang.String actName;
	/**
	 * 所属公众 号 db_column: gzh_hash
	 */
	@Length(max = 36)
	private java.lang.String gzhHash;
	/**
	 * 用户名称 db_column: user_name
	 */
	@Length(max = 50)
	private java.lang.String userName;
	/**
	 * 用户手机号码 db_column: user_mobile
	 */
	@Length(max = 20)
	private java.lang.String userMobile;
	/**
	 * 用户车牌号 db_column: user_car_no
	 */
	@Length(max = 50)
	private java.lang.String userCarNo;
	/**
	 * 乘车路线 db_column: arrive_line
	 */
	@Length(max = 255)
	private java.lang.String arriveLine;

	// columns END

	public WxModuleContentSign() {
	}

	public WxModuleContentSign(java.lang.String signGuid) {
		this.signGuid = signGuid;
	}

	public void setSignGuid(java.lang.String value) {
		this.signGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "uuid")
	@Column(name = "sign_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getSignGuid() {
		return this.signGuid;
	}

	@Column(name = "act_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getActGuid() {
		return this.actGuid;
	}

	public void setActGuid(java.lang.String value) {
		this.actGuid = value;
	}

	@Column(name = "user_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}

	@Column(name = "openid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getOpenid() {
		return this.openid;
	}

	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}

	@Column(name = "attend_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getAttendOn() {
		return this.attendOn;
	}

	public void setAttendOn(java.lang.String value) {
		this.attendOn = value;
	}

	@Column(name = "arrive_by", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getArriveBy() {
		return this.arriveBy;
	}

	public void setArriveBy(java.lang.String value) {
		this.arriveBy = value;
	}

	@Column(name = "attend_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAttendNum() {
		return this.attendNum;
	}

	public void setAttendNum(java.lang.Integer value) {
		this.attendNum = value;
	}

	@Column(name = "attend_child", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getAttendChild() {
		return this.attendChild;
	}

	public void setAttendChild(java.lang.Integer value) {
		this.attendChild = value;
	}

	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	@Column(name = "act_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getActName() {
		return this.actName;
	}

	public void setActName(java.lang.String value) {
		this.actName = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	@Column(name = "user_mobile", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(java.lang.String value) {
		this.userMobile = value;
	}

	@Column(name = "user_car_no", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserCarNo() {
		return this.userCarNo;
	}

	public void setUserCarNo(java.lang.String value) {
		this.userCarNo = value;
	}

	@Column(name = "arrive_line", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getArriveLine() {
		return this.arriveLine;
	}

	public void setArriveLine(java.lang.String value) {
		this.arriveLine = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("SignGuid", getSignGuid())
				.append("ActGuid", getActGuid())
				.append("UserGuid", getUserGuid())
				.append("Openid", getOpenid())
				.append("AttendOn", getAttendOn())
				.append("ArriveBy", getArriveBy())
				.append("AttendNum", getAttendNum())
				.append("AttendChild", getAttendChild())
				.append("Remark", getRemark()).append("ActName", getActName())
				.append("GzhHash", getGzhHash())
				.append("UserName", getUserName())
				.append("UserMobile", getUserMobile())
				.append("UserCarNo", getUserCarNo())
				.append("ArriveLine", getArriveLine()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getSignGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContentSign == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContentSign other = (WxModuleContentSign) obj;
		return new EqualsBuilder().append(getSignGuid(), other.getSignGuid())
				.isEquals();
	}
}
