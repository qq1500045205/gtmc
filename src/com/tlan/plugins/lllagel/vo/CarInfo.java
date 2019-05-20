package com.tlan.plugins.lllagel.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class CarInfo {
	 private int id;//记录号
	 private String province;//省份
     private String carNo; //车牌号
     private String engineNo; //发动机号
     private String carType;
     private String color; 
     private String endDate;
     private String status;
     private String hpzl;
     //违规记录列表
     private List<IllegalInfo> list=new ArrayList<IllegalInfo>();
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the hpzl
	 */
	public String getHpzl() {
		return hpzl;
	}
	/**
	 * @param hpzl the hpzl to set
	 */
	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the carNo
	 */
	public String getCarNo() {
		return carNo;
	}
	/**
	 * @param carNo the carNo to set
	 */
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	/**
	 * @return the engineNo
	 */
	public String getEngineNo() {
		return engineNo;
	}
	/**
	 * @param engineNo the engineNo to set
	 */
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}
	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the list
	 */
	public List<IllegalInfo> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<IllegalInfo> list) {
		this.list = list;
	} 
}
