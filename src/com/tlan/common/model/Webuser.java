/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

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
@Table(name = "webuser")
public class Webuser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Webuser";
	public static final String ALIAS_USER_GUID = "userGuid";
	public static final String ALIAS_LOGIN_NAME = "登录名称";
	public static final String ALIAS_USER_NAME = "用户名";
	public static final String ALIAS_USER_CODE = "编号";
	public static final String ALIAS_USER_PWD = "密码";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_USER_TEL = "手机号";
	public static final String ALIAS_USER_PIC = "头像";
	public static final String ALIAS_USER_BIRTH = "生日";
	public static final String ALIAS_USER_PRIVILEGE_LEVEL = "0:admin, 1:area_admin, 2: project_admin: 3: wx_admin";
	public static final String ALIAS_USER_PRIVILEGES = "ids: 12,14,32,";
	public static final String ALIAS_USER_SEX = "性别  直接存 男或女";
	public static final String ALIAS_USER_DEPT = "部门";
	public static final String ALIAS_STATUS = "状态  0激活 1 冻结";
	public static final String ALIAS_ADDRESS = "住址";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_DESCRIPTION = "个人介绍";
	public static final String ALIAS_CITY = "所在城市";
	public static final String ALIAS_MODIFY_ON = "modifyOn";
	public static final String ALIAS_MODIFY_BY = "modifyBy";
	public static final String ALIAS_GZH_HASH = "所属公众号";
	public static final String ALIAS_RIGHTS = "权限";
	
	//date formats
	

	//可以直接使用: //@Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * userGuid       db_column: user_guid 
     */ 	
	//@Length(max=36)
	private java.lang.String userGuid;
    /**
     * 登录名称       db_column: login_name 
     */ 	
	//@Length(max=50)
	private java.lang.String loginName;
    /**
     * 用户名       db_column: user_name 
     */ 	
	//@Length(max=50)
	private java.lang.String userName;
    /**
     * 编号       db_column: user_code 
     */ 	
	//@Length(max=20)
	private java.lang.String userCode;
    /**
     * 密码       db_column: user_pwd 
     */ 	
	//@Length(max=50)
	private java.lang.String userPwd;
    /**
     * createdBy       db_column: created_by 
     */ 	
	//@Length(max=50)
	private java.lang.String createdBy;
    /**
     * createdOn       db_column: created_on 
     */ 	
	//@Length(max=25)
	private java.lang.String createdOn;
    /**
     * 手机号       db_column: user_tel 
     */ 	
	//@Length(max=20)
	private java.lang.String userTel;
    /**
     * 头像       db_column: user_pic 
     */ 	
	//@Length(max=255)
	private java.lang.String userPic;
    /**
     * 生日       db_column: user_birth 
     */ 	
	//@Length(max=25)
	private java.lang.String userBirth;
    /**
     * 0:admin, 1:area_admin, 2: project_admin: 3: wx_admin       db_column: user_privilege_level 
     */ 	
	
	private java.lang.Integer userPrivilegeLevel;
    /**
     * ids: 12,14,32,       db_column: user_privileges 
     */ 	
	//@Length(max=100)
	private java.lang.String userPrivileges;
    /**
     * 性别  直接存 男或女       db_column: user_sex 
     */ 	
	//@Length(max=5)
	private java.lang.String userSex;
    /**
     * 部门       db_column: user_dept 
     */ 	
	//@Length(max=50)
	private java.lang.String userDept;
    /**
     * 状态  0激活 1 冻结       db_column: status 
     */ 	
	
	private java.lang.Integer status;
    /**
     * 住址       db_column: address 
     */ 	
	//@Length(max=255)
	private java.lang.String address;
    /**
     * 邮箱       db_column: email 
     */ 	
	//@Email //@Length(max=50)
	private java.lang.String email;
    /**
     * 个人介绍       db_column: description 
     */ 	
	//@Length(max=65535)
	private java.lang.String description;
    /**
     * 所在城市       db_column: city 
     */ 	
	//@Length(max=255)
	private java.lang.String city;
    /**
     * modifyOn       db_column: modify_on 
     */ 	
	//@Length(max=25)
	private java.lang.String modifyOn;
    /**
     * modifyBy       db_column: modify_by 
     */ 	
	//@Length(max=50)
	private java.lang.String modifyBy;
    /**
     * 所属公众号       db_column: gzh_hash 
     */ 	
	//@Length(max=36)
	private java.lang.String gzhHash;
	private java.lang.String gzhGuid;
	private java.lang.String parentGuid;
    /**
     * 所属公众号       db_column: rights 
     */ 	
	//@Length(max=64)
	private java.lang.String rights;
	private java.lang.String roleType;
	//columns END


	public Webuser(){
	}

	public Webuser(
		java.lang.String userGuid
	){
		this.userGuid = userGuid;
	}

	

	public void setUserGuid(java.lang.String value) {
		this.userGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "user_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserGuid() {
		return this.userGuid;
	}
	
	@Column(name = "login_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "user_code", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(java.lang.String value) {
		this.userCode = value;
	}
	
	@Column(name = "user_pwd", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserPwd() {
		return this.userPwd;
	}
	
	public void setUserPwd(java.lang.String value) {
		this.userPwd = value;
	}
	
	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
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
	
	@Column(name = "user_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}
	
	@Column(name = "user_pic", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getUserPic() {
		return this.userPic;
	}
	
	public void setUserPic(java.lang.String value) {
		this.userPic = value;
	}
	
	@Column(name = "user_birth", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUserBirth() {
		return this.userBirth;
	}
	
	public void setUserBirth(java.lang.String value) {
		this.userBirth = value;
	}
	
	@Column(name = "user_privilege_level", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserPrivilegeLevel() {
		return this.userPrivilegeLevel;
	}
	
	public void setUserPrivilegeLevel(java.lang.Integer value) {
		this.userPrivilegeLevel = value;
	}
	
	@Column(name = "user_privileges", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserPrivileges() {
		return this.userPrivileges;
	}
	
	public void setUserPrivileges(java.lang.String value) {
		this.userPrivileges = value;
	}
	
	@Column(name = "user_sex", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public java.lang.String getUserSex() {
		return this.userSex;
	}
	
	public void setUserSex(java.lang.String value) {
		this.userSex = value;
	}
	
	@Column(name = "user_dept", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserDept() {
		return this.userDept;
	}
	
	public void setUserDept(java.lang.String value) {
		this.userDept = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	@Column(name = "city", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.String value) {
		this.city = value;
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
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "gzh_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getGzhGuid() {
		return this.gzhGuid;
	}
	
	public void setGzhGuid(java.lang.String value) {
		this.gzhGuid = value;
	}
	
	@Column(name = "parent_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}
	
	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}
	
	@Column(name = "rights", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRights() {
		return this.rights;
	}
	
	public void setRights(java.lang.String value) {
		this.rights = value;
	}
	
	@Column(name = "role_type", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getRoleType() {
		return this.roleType;
	}
	
	public void setRoleType(java.lang.String value) {
		this.roleType = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserGuid",getUserGuid())
			.append("LoginName",getLoginName())
			.append("UserName",getUserName())
			.append("UserCode",getUserCode())
			.append("UserPwd",getUserPwd())
			.append("CreatedBy",getCreatedBy())
			.append("CreatedOn",getCreatedOn())
			.append("UserTel",getUserTel())
			.append("UserPic",getUserPic())
			.append("UserBirth",getUserBirth())
			.append("UserPrivilegeLevel",getUserPrivilegeLevel())
			.append("UserPrivileges",getUserPrivileges())
			.append("UserSex",getUserSex())
			.append("UserDept",getUserDept())
			.append("Status",getStatus())
			.append("Address",getAddress())
			.append("Email",getEmail())
			.append("Description",getDescription())
			.append("City",getCity())
			.append("ModifyOn",getModifyOn())
			.append("ModifyBy",getModifyBy())
			.append("GzhHash",getGzhHash())
			.append("Rights",getRights())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Webuser == false) return false;
		if(this == obj) return true;
		Webuser other = (Webuser)obj;
		return new EqualsBuilder()
			.append(getUserGuid(),other.getUserGuid())
			.isEquals();
	}
}

