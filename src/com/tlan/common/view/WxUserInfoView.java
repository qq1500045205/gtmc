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
import net.sf.oval.constraint.NotBlank;

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
@Table(name = "wx_user_info_view")
public class WxUserInfoView implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxUserInfoView";
	public static final String ALIAS_USER_GUID = "微信关注用户guid";
	public static final String ALIAS_USER_NAME = "userName";
	public static final String ALIAS_USER_SEX = "userSex";
	public static final String ALIAS_NICK_NAME = "微信用户昵称";
	public static final String ALIAS_FAKEID = "微信用户id";
	public static final String ALIAS_OPEN_ID = "微信客户唯一标识";
	public static final String ALIAS_GZH_HASH = "公众号唯一标识";
	public static final String ALIAS_USER_TEL = "userTel";
	public static final String ALIAS_STATUS = " 1 关注   2 取消关注";
	public static final String ALIAS_MEMBER_LEVEL = "0 非会员   1 普通会员   2 高级会员";
	public static final String ALIAS_USER2CAR_GUID = "user2carGuid";
	public static final String ALIAS_CAR_VIN = "carVin";
	public static final String ALIAS_CAR_NUMBER_PFX = "carNumberPfx";
	public static final String ALIAS_CAR_NUMBER = "carNumber";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 微信关注用户guid db_column: user_guid
	 */
	private java.lang.String userGuid;
	/**
	 * userName db_column: user_name
	 */
	private java.lang.String userName;
	/**
	 * userSex db_column: user_sex
	 */
	private java.lang.String userSex;
	/**
	 * 微信用户昵称 db_column: nick_name
	 */
	private java.lang.String nickName;
	/**
	 * 微信用户id db_column: fakeid
	 */
	private java.lang.String fakeid;
	/**
	 * 微信客户唯一标识 db_column: open_id
	 */
	private java.lang.String openId;
	/**
	 * 公众号唯一标识 db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * userTel db_column: user_tel
	 */
	private java.lang.String userTel;
	/**
	 * 1 关注 2 取消关注 db_column: status
	 */

	private java.lang.Integer status;
	/**
	 * 1 非会员 2 普通会员 3 高级会员 db_column: member_level
	 */

	private java.lang.Integer memberLevel;
	/**
	 * user2carGuid db_column: user2car_guid
	 */
	private java.lang.String user2carGuid;
	/**
	 * carVin db_column: car_vin
	 */
	private java.lang.String carVin;
	/**
	 * carNumberPfx db_column: car_number_pfx
	 */
	private java.lang.String carNumberPfx;
	/**
	 * carNumber db_column: car_number
	 */
	private java.lang.String carNumber;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * cancelOn db_column: cancel_on
	 */
	private java.lang.String cancelOn;
	/**
	 * registerTime db_column: register_time
	 */
	private java.lang.String registerTime;
	/**
	 * registerFrom db_column: register_from
	 */
	private java.lang.String registerFrom;
	/**
	 * sourceFrom db_column: source_from
	 */
	private java.lang.String sourceFrom;
	/**
	 * ticket db_column: ticket
	 */
	private java.lang.String ticket;
	/**
	 * 所在城市
	 */
	private java.lang.String city;
	/**
	 * 所在省
	 */
	private java.lang.String province;

	// columns END

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "user_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
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

	@Column(name = "nick_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNickName() {
		return this.nickName;
	}

	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}

	@Column(name = "fakeid", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getFakeid() {
		return this.fakeid;
	}

	public void setFakeid(java.lang.String value) {
		this.fakeid = value;
	}

	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getOpenId() {
		return this.openId;
	}

	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "user_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "member_level", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(java.lang.Integer value) {
		this.memberLevel = value;
	}

	@Column(name = "user2car_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUser2carGuid() {
		return this.user2carGuid;
	}

	public void setUser2carGuid(java.lang.String value) {
		this.user2carGuid = value;
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

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "cancel_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCancelOn() {
		return cancelOn;
	}

	public void setCancelOn(java.lang.String cancelOn) {
		this.cancelOn = cancelOn;
	}
	
	@Column(name = "register_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.lang.String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Column(name = "register_from", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(java.lang.String registerFrom) {
		this.registerFrom = registerFrom;
	}

	@Column(name = "source_from", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(java.lang.String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	@Column(name = "ticket", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTicket() {
		return ticket;
	}

	public void setTicket(java.lang.String ticket) {
		this.ticket = ticket;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getProvince() {
		return province;
	}

	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("UserGuid", getUserGuid())
				.append("UserName", getUserName())
				.append("UserSex", getUserSex())
				.append("NickName", getNickName())
				.append("Fakeid", getFakeid()).append("OpenId", getOpenId())
				.append("GzhHash", getGzhHash())
				.append("UserTel", getUserTel()).append("Status", getStatus())
				.append("MemberLevel", getMemberLevel())
				.append("User2carGuid", getUser2carGuid())
				.append("CarVin", getCarVin())
				.append("CarNumberPfx", getCarNumberPfx())
				.append("CarNumber", getCarNumber()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxUserInfoView == false)
			return false;
		if (this == obj)
			return true;
		WxUserInfoView other = (WxUserInfoView) obj;
		return new EqualsBuilder().isEquals();
	}
}
