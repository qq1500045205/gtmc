package com.tlan.common.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "wx_gongzhonghao")
public class WxGongzhonghao implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxGongzhonghao";
	public static final String ALIAS_GZH_GUID = "微信公众号主键";
	public static final String ALIAS_GZH_HASH = "用户标识. 随机生成保持不重复";
	public static final String ALIAS_TOKEN = "随机生成密钥";
	public static final String ALIAS_GZH_NAME = "公众号名称";
	public static final String ALIAS_GZH_FANS = "粉丝数";
	public static final String ALIAS_GZH_ACCOUNT = "微信公众号帐号";
	public static final String ALIAS_GZH_PWD = "微信公众号密码";
	public static final String ALIAS_ORIGINAL = "原始帐号";
	public static final String ALIAS_SIGNATURE = "功能介绍";
	public static final String ALIAS_COUNTRY = "country";
	public static final String ALIAS_PROVINCE = "province";
	public static final String ALIAS_AREA = "area";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_MODIFY_ON = "modifyOn";
	public static final String ALIAS_MODIFY_BY = "modifyBy";
	public static final String ALIAS_APPID = "第三方用户唯一凭证";
	public static final String ALIAS_APPSECRET = "第三方用户唯一凭证密钥，既appsecret";
	public static final String ALIAS_RULE_GUID = "欢迎消息对应的规则guid";
	public static final String ALIAS_PUBLISH_ON = "发布状态";
	public static final String ALIAS_PUBLISH_BY = "发布人";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_PROJECT_GUID = "项目GUID, 外键";
	//modify by lyn 2014/5/14 start
	public static final String ALIAS_ACCESS_TOKEN = "公众号的即时access_token";
	public static final String ALIAS_ACCESS_TOKEN_DATE = "上一次取公众号的日期access_token_date";
	//modify by lyn 2014/5/14 end
	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 微信公众号主键 db_column: gzh_guid
	 */
	private java.lang.String gzhGuid;
	/**
	 * 用户标识. 随机生成保持不重复 db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * 随机生成密钥 db_column: token
	 */
	private java.lang.String token;
	/**
	 * 公众号名称 db_column: gzh_name
	 */
	private java.lang.String gzhName;
	/**
	 * 粉丝数 db_column: gzh_fans
	 */

	private java.lang.Integer gzhFans;
	/**
	 * 微信公众号帐号 db_column: gzh_account
	 */
	private java.lang.String gzhAccount;
	/**
	 * 微信公众号密码 db_column: gzh_pwd
	 */
	private java.lang.String gzhPwd;
	/**
	 * 原始帐号 db_column: original
	 */
	private java.lang.String original;
	/**
	 * 功能介绍 db_column: signature
	 */
	private java.lang.String signature;
	/**
	 * country db_column: country
	 */
	private java.lang.String country;
	/**
	 * province db_column: province
	 */
	private java.lang.String province;
	/**
	 * area db_column: area
	 */
	private java.lang.String area;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * createdBy db_column: created_by
	 */
	private java.lang.String createdBy;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * modifyBy db_column: modify_by
	 */
	private java.lang.String modifyBy;
	/**
	 * 第三方用户唯一凭证 db_column: appid
	 */
	private java.lang.String appid;
	/**
	 * 第三方用户唯一凭证密钥，既appsecret db_column: appsecret
	 */
	private java.lang.String appsecret;
	/**
	 * 欢迎消息对应的规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * 发布状态 db_column: publish_on
	 */
	private java.lang.String publishOn;
	/**
	 * 发布人 db_column: publish_by
	 */
	private java.lang.String publishBy;
	/**
	 * 状态 db_column: status
	 */

	private java.lang.Integer status;
	/**
	 * 项目GUID, 外键 db_column: project_guid
	 */
	private java.lang.String projectGuid;
	//add by lyn 2014/5/14 start
	/**
	 * 功能介绍 db_column: 公众号的即时access_token
	 */
	private java.lang.String accessToken;
	/**
	 * 功能介绍 db_column: 上一次取公众号的日期access_token_date
	 */
	private java.lang.String accessTokenDate;
	//add by lyn 2014/5/14 end
	// columns END

	public WxGongzhonghao() {
	}

	public void setGzhGuid(java.lang.String value) {
		this.gzhGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "gzh_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhGuid() {
		return this.gzhGuid;
	}

	@Column(name = "gzh_hash", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "token", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getToken() {
		return this.token;
	}

	public void setToken(java.lang.String value) {
		this.token = value;
	}

	@Column(name = "gzh_name", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public java.lang.String getGzhName() {
		return this.gzhName;
	}

	public void setGzhName(java.lang.String value) {
		this.gzhName = value;
	}

	@Column(name = "gzh_fans", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getGzhFans() {
		return this.gzhFans;
	}

	public void setGzhFans(java.lang.Integer value) {
		this.gzhFans = value;
	}

	@Column(name = "gzh_account", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getGzhAccount() {
		return this.gzhAccount;
	}

	public void setGzhAccount(java.lang.String value) {
		this.gzhAccount = value;
	}

	@Column(name = "gzh_pwd", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getGzhPwd() {
		return this.gzhPwd;
	}

	public void setGzhPwd(java.lang.String value) {
		this.gzhPwd = value;
	}

	@Column(name = "original", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getOriginal() {
		return this.original;
	}

	public void setOriginal(java.lang.String value) {
		this.original = value;
	}

	@Column(name = "signature", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getSignature() {
		return this.signature;
	}

	public void setSignature(java.lang.String value) {
		this.signature = value;
	}

	@Column(name = "country", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String value) {
		this.country = value;
	}

	@Column(name = "province", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public java.lang.String getProvince() {
		return this.province;
	}

	public void setProvince(java.lang.String value) {
		this.province = value;
	}

	@Column(name = "area", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getArea() {
		return this.area;
	}

	public void setArea(java.lang.String value) {
		this.area = value;
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

	@Column(name = "appid", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getAppid() {
		return this.appid;
	}

	public void setAppid(java.lang.String value) {
		this.appid = value;
	}

	@Column(name = "appsecret", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getAppsecret() {
		return this.appsecret;
	}

	public void setAppsecret(java.lang.String value) {
		this.appsecret = value;
	}

	@Column(name = "rule_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	@Column(name = "publish_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPublishOn() {
		return this.publishOn;
	}

	public void setPublishOn(java.lang.String value) {
		this.publishOn = value;
	}

	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	@Column(name = "project_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getProjectGuid() {
		return this.projectGuid;
	}

	public void setProjectGuid(java.lang.String value) {
		this.projectGuid = value;
	}
	//add by lyn 2014/5/14 start
	@Column(name = "access_token", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public java.lang.String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(java.lang.String value) {
		this.accessToken = value;
	}
	
	@Column(name = "access_token_date", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getAccessTokenDate() {
		return this.accessTokenDate;
	}

	public void setAccessTokenDate(java.lang.String value) {
		this.accessTokenDate = value;
	}
	//add by lyn 2014/5/14 end
	private WxProject wxProject;

	public void setWxProject(WxProject wxProject) {
		this.wxProject = wxProject;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "project_guid", nullable = false, insertable = false, updatable = false) })
	public WxProject getWxProject() {
		return wxProject;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("GzhGuid", getGzhGuid())
				.append("GzhHash", getGzhHash()).append("Token", getToken())
				.append("GzhName", getGzhName())
				.append("GzhFans", getGzhFans())
				.append("GzhAccount", getGzhAccount())
				.append("GzhPwd", getGzhPwd())
				.append("Original", getOriginal())
				.append("Signature", getSignature())
				.append("Country", getCountry())
				.append("Province", getProvince()).append("Area", getArea())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy()).append("Appid", getAppid())
				.append("Appsecret", getAppsecret())
				.append("RuleGuid", getRuleGuid())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("Status", getStatus())
				//modify by lyn 2014/5/14 start
				//.append("ProjectGuid", getProjectGuid()).toString();
				.append("ProjectGuid", getProjectGuid())
				.append("AccessToken", getAccessToken())
				.append("AccessTokenDate", getAccessTokenDate()).toString();
				//modify by lyn 2014/5/14 end
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getGzhGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxGongzhonghao == false)
			return false;
		if (this == obj)
			return true;
		WxGongzhonghao other = (WxGongzhonghao) obj;
		return new EqualsBuilder().append(getGzhGuid(), other.getGzhGuid())
				.isEquals();
	}
}
