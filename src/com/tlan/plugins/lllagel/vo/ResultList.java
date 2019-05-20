package com.tlan.plugins.lllagel.vo;

import java.util.List;

/**
 * 
 * @author magenm 2014-1-8
 * 
 */
public class ResultList {
	/**
	 * 车辆状态信息。目前取值有 <br/>
	 * ok。车辆信息正确 <br/>
	 * error。车辆信息错误<br/>
	 * net error。交管局网络原因暂时无法查询<br/>
	 * unknown。车辆信息正确性未知。
	 */
	private String vehicle_status;
	/**
	 * 车辆违章列表
	 */
	private List<ResultBean> violations;

	public String getVehicle_status() {
		return vehicle_status;
	}

	public void setVehicle_status(String vehicle_status) {
		this.vehicle_status = vehicle_status;
	}

	public List<ResultBean> getViolations() {
		return violations;
	}

	public void setViolations(List<ResultBean> violations) {
		this.violations = violations;
	}

}
