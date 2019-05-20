package com.tlan.common.model.creditactive;

// Generated 2014-7-8 10:25:16 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * WxMemberCreditCountViewId generated by hbm2java
 */
@Embeddable
@Entity
@Table(name = "wx_member_credit_count_view")
public class WxMemberCreditCountView implements java.io.Serializable {

	private String actName;
	private String startOn;
	private String endOn;
	private Integer actCredit;
	private String gzhGuid;
	private String gzhName;
	private String gzhHash;
	private Integer gzhFans;
	private String gzhAccount;
	private String projectGuid;
	private String userGuid;
	private String userName;
	private String userSex;
	private String nickName;
	private String fakeid;
	private String openId;
	private String userTel;
	private String createtime;
	private String createdOnTicket;
	private String modifyOn;
	private String cancelOn;
	private Integer status;
	private Integer memberLevel;
	private String memberNo;
	private String user2carGuid;
	private String registerTime;
	private String registerFrom;
	private String followedFrom;
	private String headimgurl;
	private String city;
	private String province;
	private String country;
	private String createdOn;
	private Integer currentCredit;

	public WxMemberCreditCountView() {
	}

	public WxMemberCreditCountView(String gzhGuid, String gzhHash) {
		this.gzhGuid = gzhGuid;
		this.gzhHash = gzhHash;
	}

	public WxMemberCreditCountView(String actName, String startOn,
			String endOn, String gzhGuid, String gzhName, String gzhHash,
			Integer gzhFans, String gzhAccount, String projectGuid,
			String userGuid, String userName, String userSex, String nickName,
			String fakeid, String openId, String userTel, String createtime,
			String createdOnTicket, String modifyOn, String cancelOn,
			Integer status, Integer memberLevel, String memberNo,
			String user2carGuid, String registerTime, String registerFrom,
			String followedFrom, String headimgurl, String city,
			String province, String country, String createdOn,
			Integer countCredit) {
		this.actName = actName;
		this.startOn = startOn;
		this.endOn = endOn;
		this.gzhGuid = gzhGuid;
		this.gzhName = gzhName;
		this.gzhHash = gzhHash;
		this.gzhFans = gzhFans;
		this.gzhAccount = gzhAccount;
		this.projectGuid = projectGuid;
		this.userGuid = userGuid;
		this.userName = userName;
		this.userSex = userSex;
		this.nickName = nickName;
		this.fakeid = fakeid;
		this.openId = openId;
		this.userTel = userTel;
		this.createtime = createtime;
		this.createdOnTicket = createdOnTicket;
		this.modifyOn = modifyOn;
		this.cancelOn = cancelOn;
		this.status = status;
		this.memberLevel = memberLevel;
		this.memberNo = memberNo;
		this.user2carGuid = user2carGuid;
		this.registerTime = registerTime;
		this.registerFrom = registerFrom;
		this.followedFrom = followedFrom;
		this.headimgurl = headimgurl;
		this.city = city;
		this.province = province;
		this.country = country;
		this.createdOn = createdOn;
		this.currentCredit = countCredit;
	}

	@Column(name = "act_name", length = 100)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "start_on", length = 25)
	public String getStartOn() {
		return this.startOn;
	}

	public void setStartOn(String startOn) {
		this.startOn = startOn;
	}

	@Column(name = "end_on", length = 25)
	public String getEndOn() {
		return this.endOn;
	}

	public void setEndOn(String endOn) {
		this.endOn = endOn;
	}
	
	@Column(name = "act_credit", length = 25)
	public Integer getActCredit() {
		return actCredit;
	}

	public void setActCredit(Integer actCredit) {
		this.actCredit = actCredit;
	}

	@Column(name = "gzh_guid", nullable = false, length = 36)
	public String getGzhGuid() {
		return this.gzhGuid;
	}

	public void setGzhGuid(String gzhGuid) {
		this.gzhGuid = gzhGuid;
	}

	@Column(name = "gzh_name", length = 30)
	public String getGzhName() {
		return this.gzhName;
	}

	public void setGzhName(String gzhName) {
		this.gzhName = gzhName;
	}

	@Column(name = "gzh_hash", nullable = false, length = 36)
	public String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	@Column(name = "gzh_fans")
	public Integer getGzhFans() {
		return this.gzhFans;
	}

	public void setGzhFans(Integer gzhFans) {
		this.gzhFans = gzhFans;
	}

	@Column(name = "gzh_account", length = 50)
	public String getGzhAccount() {
		return this.gzhAccount;
	}

	public void setGzhAccount(String gzhAccount) {
		this.gzhAccount = gzhAccount;
	}

	@Column(name = "project_guid", length = 64)
	public String getProjectGuid() {
		return this.projectGuid;
	}

	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "user_guid", length = 36)
	public String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_sex", length = 20)
	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Column(name = "nick_name", length = 100)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "fakeid", length = 50)
	public String getFakeid() {
		return this.fakeid;
	}

	public void setFakeid(String fakeid) {
		this.fakeid = fakeid;
	}

	@Column(name = "open_id", length = 50)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "user_tel", length = 25)
	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Column(name = "createtime", length = 25)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "created_on_ticket", length = 200)
	public String getCreatedOnTicket() {
		return this.createdOnTicket;
	}

	public void setCreatedOnTicket(String createdOnTicket) {
		this.createdOnTicket = createdOnTicket;
	}

	@Column(name = "modify_on", length = 25)
	public String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	@Column(name = "cancel_on", length = 25)
	public String getCancelOn() {
		return this.cancelOn;
	}

	public void setCancelOn(String cancelOn) {
		this.cancelOn = cancelOn;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "member_level")
	public Integer getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Column(name = "member_no", length = 50)
	public String getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Column(name = "user2car_guid", length = 36)
	public String getUser2carGuid() {
		return this.user2carGuid;
	}

	public void setUser2carGuid(String user2carGuid) {
		this.user2carGuid = user2carGuid;
	}

	@Column(name = "register_time", length = 25)
	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "register_from", length = 100)
	public String getRegisterFrom() {
		return this.registerFrom;
	}

	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	@Column(name = "followed_from", length = 100)
	public String getFollowedFrom() {
		return this.followedFrom;
	}

	public void setFollowedFrom(String followedFrom) {
		this.followedFrom = followedFrom;
	}

	@Column(name = "headimgurl")
	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 100)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "country", length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "created_on", length = 25)
	public String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "current_credit", precision = 32, scale = 0)
	public Integer getCurrentCredit() {
		return currentCredit;
	}
	
	public void setCurrentCredit(Integer currentCredit) {
		this.currentCredit = currentCredit;
	}

}
