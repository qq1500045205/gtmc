package com.tlan.plugins.lllagel.vo;

/***
 * 违规信息
 * @author magenm
 *序号，发生时间，违法地点 ，行为，情况
 */
public class IllegalInfo {
	private int id;
	private int carId;
	private String IllegalNo;//违规序号
	private String createDate;
	private String local;
	private String lawfulAct; //违法行为
	private String handResult; //处理结果 
	private int count;//积分
	private int money ;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}
	/**
	 * @return the illegalNo
	 */
	public String getIllegalNo() {
		return IllegalNo;
	}
	/**
	 * @param illegalNo the illegalNo to set
	 */
	public void setIllegalNo(String illegalNo) {
		IllegalNo = illegalNo;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}
	/**
	 * @param local the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}
	/**
	 * @return the lawfulAct
	 */
	public String getLawfulAct() {
		return lawfulAct;
	}
	/**
	 * @param lawfulAct the lawfulAct to set
	 */
	public void setLawfulAct(String lawfulAct) {
		this.lawfulAct = lawfulAct;
	}
	/**
	 * @return the handResult
	 */
	public String getHandResult() {
		return handResult;
	}
	/**
	 * @param handResult the handResult to set
	 */
	public void setHandResult(String handResult) {
		this.handResult = handResult;
	}

}
