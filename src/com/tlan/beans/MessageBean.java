package com.tlan.beans;

/**
 * 自定义模块绑定模块对应的bean
 * 
 * @author magenm
 * 
 */
public class MessageBean {

	private String id;
	private String value;
	private String src;
	private String type;

	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageBean(String id, String value, String src, String type) {
		super();
		this.id = id;
		this.value = value;
		this.src = src;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
