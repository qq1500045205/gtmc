package com.tlan.plugins.lllagel;

public class QueryResultData {
	// 时间
	private String time;
	// 地点
	private String address;
	// 违章编号
	private String number;
	// 采集机关
	private String agency;
	// 违章内容
	private String content;
	// 罚款金额
	private String price;
	// 违章代号
	private String legalnum;
	// 扣分
	private String score;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLegalnum() {
		return legalnum;
	}

	public void setLegalnum(String legalnum) {
		this.legalnum = legalnum;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
