package com.tlan.beans;

import java.util.List;

/**
 * 模块设置返回的动作内容
 * 
 * 
 * @author magenm
 * 
 */
public class PagesBean {
	private String name;
	private String moduleId;
	private String src;
	private List<PagesActionBean> url;

	public PagesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagesBean(String name, String moduleId, String src,
			List<PagesActionBean> url) {
		super();
		this.name = name;
		this.moduleId = moduleId;
		this.src = src;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public List<PagesActionBean> getUrl() {
		return url;
	}

	public void setUrl(List<PagesActionBean> url) {
		this.url = url;
	}

}
