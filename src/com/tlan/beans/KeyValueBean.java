package com.tlan.beans;

public class KeyValueBean {
	private String k;
	private String v;

	public KeyValueBean() {
		super();
	}

	public KeyValueBean(String key, String value) {
		super();
		this.k = key;
		this.v = value;
	}

	public String getK() {
		return k;
	}

	public void setK(String key) {
		this.k = key;
	}

	public String getV() {
		return v;
	}

	public void setV(String value) {
		this.v = value;
	}

}
