package com.tlan.wxkit.fakeid;

public class FakeBean {

	private String fakeid;
	private String openid;
	private String nickname;
	private String createtime;

	public String getFakeid() {
		return fakeid;
	}

	public void setFakeid(String fakeid) {
		this.fakeid = fakeid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "FakeBean [fakeid=" + fakeid + ", openid=" + openid
				+ ", nickname=" + nickname + ", createtime=" + createtime + "]";
	}

}
