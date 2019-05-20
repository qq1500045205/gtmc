package com.tlan.plugins.lllagel.weiche;

import java.util.List;

import com.tlan.plugins.lllagel.vo.CityBean;

public class StructCity {
	private String key;
	private List<CityBean> list;

	public StructCity(String key, List<CityBean> list) {
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

	public List<CityBean> getList() {
		return list;
	}

	public void setList(List<CityBean> list) {
		this.list = list;
	}
}
