package com.tlan.beans;

public class RuleKeyBean {
	private String name;
	private int type;
	private String kwGuid;

	public RuleKeyBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuleKeyBean(String name, int type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getKwGuid() {
		return kwGuid;
	}

	public void setKwGuid(String kwGuid) {
		this.kwGuid = kwGuid;
	}

}
