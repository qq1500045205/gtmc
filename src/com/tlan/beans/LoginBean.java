package com.tlan.beans;

/**
 * 用于封装登录信息，保存session使用
 * 
 * @author magenm
 * 
 */
public class LoginBean {
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 项目id
	 */
	private String projectGuid;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 公众号标识
	 */
	private String gzhHash;
	/**
	 * 厂商标识
	 */
	private String gzhType;
	/**
	 * 公众号名称
	 */
	private String gzhName;
	/**
	 * 公众号标识
	 */
	private String gzhGuid;
	/**
	 * 权限/角色
	 */
	private int privilegeLevel;

	// TODO
	private String privileges;

	// 获取用户guid
	private String userGuid;

	// 获取用户rights
	private String roleType;
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

		// 获取用户rights
		private String rights;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectGuid() {
		return projectGuid;
	}

	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		if (privileges.endsWith(",")) {
			this.privileges = privileges.substring(0, privileges.length() - 1);
		} else {
			this.privileges = privileges;
		}
	}

	public int getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(int privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public String getGzhGuid() {
		return gzhGuid;
	}

	public void setGzhGuid(String gzhGuid) {
		this.gzhGuid = gzhGuid;
	}

	public String getGzhName() {
		return gzhName;
	}

	public void setGzhName(String gzhName) {
		this.gzhName = gzhName;
	}

	public String getGzhType() {
		return gzhType;
	}

	public void setGzhType(String gzhType) {
		this.gzhType = gzhType;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}
	

}
