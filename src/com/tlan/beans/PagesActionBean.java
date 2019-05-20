package com.tlan.beans;

/**
 * 动作
 * 
 * @author Administrator
 * 
 */
public class PagesActionBean {

	private String targetId; // 替换表情id
	private String src; // 替换连接
	private int pageIndex;

	public PagesActionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagesActionBean(String targetId, String src, int pageIndex) {
		super();
		this.targetId = targetId;
		this.src = src;
		this.pageIndex = pageIndex;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}
