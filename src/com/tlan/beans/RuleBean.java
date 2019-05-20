package com.tlan.beans;

import java.util.List;

public class RuleBean {

	private String title;
	private String newsGuid;
	private String type;
	private String typeName;
	private String ruleGuid;
	private List<RuleKeyBean> keys;

	public RuleBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuleBean(String title, String newsGuid, String type,
			String typeName, String ruleGuid, List<RuleKeyBean> keys) {
		super();
		this.title = title;
		this.newsGuid = newsGuid;
		this.type = type;
		this.typeName = typeName;
		this.ruleGuid = ruleGuid;
		this.keys = keys;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsGuid() {
		return newsGuid;
	}

	public void setNewsGuid(String newsGuid) {
		this.newsGuid = newsGuid;
	}

	public List<RuleKeyBean> getKeys() {
		return keys;
	}

	public void setKeys(List<RuleKeyBean> keys) {
		this.keys = keys;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRuleGuid() {
		return ruleGuid;
	}

	public void setRuleGuid(String ruleGuid) {
		this.ruleGuid = ruleGuid;
	}

}
