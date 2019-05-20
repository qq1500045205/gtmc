package com.tlan.plugins.openfire;

import java.io.Serializable;

/**
 * @ClassName:WMS
 * @Description:短信字段
 * @author:liweiwei
 * @date:2013-8-29 上午11:23:42
 */
public class WechatMessage implements Serializable {

	private static final long serialVersionUID = -3220982916311531549L;
	private String userGuid;
	private String nickName;
	private String guwenGuid;
	private String date;
	private String telephone;
	private String gzhHash;
	private String body;
	private String type; // 收 recv 发 send

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGuwenGuid() {
		return guwenGuid;
	}

	public void setGuwenGuid(String guwenGuid) {
		this.guwenGuid = guwenGuid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
