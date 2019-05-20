package com.tlan.plugins.lllagel.vo;

/**
 * 查询结构条目
 * 
 * @author magenm 2014-1-8
 * 
 */
public class ResultBean {

	/**
	 * 违章时间
	 */
	private String time;
	/**
	 * 违章地点
	 */
	private String address;
	/**
	 * 罚款
	 */
	private String fine;
	/**
	 * 罚分。目前取值有 -1。 未知 >=0。 罚分
	 */
	private String point;
	/**
	 * 是否已处理
	 */
	private boolean handled;
	private String agency;
	private String violation_type;

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

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean handled) {
		this.handled = handled;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getViolation_type() {
		return violation_type;
	}

	public void setViolation_type(String violation_type) {
		this.violation_type = violation_type;
	}

}
