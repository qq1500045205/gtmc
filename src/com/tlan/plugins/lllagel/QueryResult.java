package com.tlan.plugins.lllagel;

import java.util.List;

public class QueryResult {
	// 是否错误
	private String error;
	// 是否错误
	private String msg;
	// 是否可缴费
	private String canfee;
	// 一次费用
	private String onefee;
	// 车ID
	private String usercarid;

	private List<QueryResultData> data;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCanfee() {
		return canfee;
	}

	public void setCanfee(String canfee) {
		this.canfee = canfee;
	}

	public String getOnefee() {
		return onefee;
	}

	public void setOnefee(String onefee) {
		this.onefee = onefee;
	}

	public String getUsercarid() {
		return usercarid;
	}

	public void setUsercarid(String usercarid) {
		this.usercarid = usercarid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<QueryResultData> getData() {
		return data;
	}

	public void setData(List<QueryResultData> data) {
		this.data = data;
	}

}
