package com.tlan.common.model;

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
@Table(name = "wx_user")
public class WxUser implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	// 1：关组 2：取消关注
	public static final int GZ = 1;
	public static final int GZ_CANCEL = 2;

	public static final int LEVEL_FHY = 0;
	public static final int LEVEL_PTHY = 1;
	public static final int LEVEL_GJHY = 2;

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

	private java.lang.String userSex;
	/**
	 * w 微信用户昵称 db_column: nick_name
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
	 * createdOn db_column: created_time
	 */
	private java.lang.String createtime;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * createdOnTicket db_column: created_on_ticket
	 */
	private java.lang.String createdOnTicket;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * cancelOn db_column: cancel_on
	 */
	private java.lang.String cancelOn;
	/**
	 * 1关组 2：取消关注 db_column: status
	 */
	private java.lang.Integer status;
	/**
	 * 0非注册会员 1：注册会员 2：高级会员 db_column: memberLevel
	 */
	private java.lang.Integer memberLevel;
	/**
	 * 会员卡号 db_column: memberNo
	 */
	private java.lang.String memberNo;
	/**
	 * 用户常用车辆guid db_column: user2car_guid
	 */
	private java.lang.String user2carGuid;
	/**
	 * 注册时间 db_column: register_time
	 */
	private java.lang.String registerTime;
	/**
	 * 注册来源 db_column: register_from
	 */
	private java.lang.String registerFrom;
	/**
	 * 用户头像
	 */
	private java.lang.String headimgurl;
	/**
	 * 所在城市
	 */
	private java.lang.String city;
	/**
	 * 所在省
	 */
	private java.lang.String province;
	/**
	 * 国家
	 */
	private java.lang.String country;

	private Integer countCredit;
	// columns END

	public WxUser() {
	}

	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "user_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}

	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
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

	@Column(name = "user_sex", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(java.lang.String value) {
		this.userSex = value;
	}

	@Column(name = "createtime", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.lang.String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "created_on_ticket", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getCreatedOnTicket() {
		return this.createdOnTicket;
	}

	public void setCreatedOnTicket(java.lang.String value) {
		this.createdOnTicket = value;
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

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "member_level", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(java.lang.Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Column(name = "member_no", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(java.lang.String memberNo) {
		this.memberNo = memberNo;
	}

	@Column(name = "user2car_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getUser2carGuid() {
		return user2carGuid;
	}

	public void setUser2carGuid(java.lang.String user2carGuid) {
		this.user2carGuid = user2carGuid;
	}

	@Column(name = "register_time", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(java.lang.String registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "register_from", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(java.lang.String registerFrom) {
		this.registerFrom = registerFrom;
	}

	@Column(name = "headimgurl", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		this.headimgurl = headimgurl;
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

	@Column(name = "country", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCountry() {
		return country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	@Column(name = "count_credit",length = 25)
	public Integer getCountCredit() {
		return countCredit;
	}
	
	public void setCountCredit(Integer countCredit) {
		this.countCredit = countCredit;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("UserGuid", getUserGuid())
				.append("UserName", getUserName())
				.append("UserSex", getUserName())
				.append("NickName", getNickName())
				.append("Fakeid", getFakeid()).append("OpenId", getOpenId())
				.append("GzhHash", getGzhHash())
				.append("UserTel", getUserTel())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedTime", getCreatetime())
				.append("ModifyOn", getModifyOn())
				.append("Status", getStatus())
				.append("MemberLevel", getMemberLevel())
				.append("MemberNo", getMemberNo())
				.append("User2carGuid", getUser2carGuid())
				.append("RegisterTime", getRegisterTime())
				.append("RegisterFrom", getRegisterFrom()).toString();
	}


	public int hashCode() {
		return new HashCodeBuilder().append(getUserGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxUser == false)
			return false;
		if (this == obj)
			return true;
		WxUser other = (WxUser) obj;
		return new EqualsBuilder().append(getUserGuid(), other.getUserGuid())
				.isEquals();
	}
}
