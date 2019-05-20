package com.tlan.beans;

/**
 * 模块设置
 * 
 * 给模块设置页传送的内容Bean
 * 
 * @author magenm
 * 
 */
public class ModulesBean {

	private int index;
	private String code;
	private String name;
	private String id;
	private String src;

	public ModulesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModulesBean(int index, String name, String id, String src) {
		super();
		this.index = index;
		this.name = name;
		this.id = id;
		this.src = src;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
