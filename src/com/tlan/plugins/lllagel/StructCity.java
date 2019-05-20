package com.tlan.plugins.lllagel;

import java.util.List;

public class StructCity {
	private String key;
	private List<JiaoGuanJuBean> list;

	public StructCity(String key, List<JiaoGuanJuBean> list) {
		super();
		this.key = key;
		this.list = list;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<JiaoGuanJuBean> getList() {
		return list;
	}

	public void setList(List<JiaoGuanJuBean> list) {
		this.list = list;
	}
}
