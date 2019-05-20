package com.tlan.beans;

/**
 * 伸缩段落元素
 * 
 * @author magenm
 * 
 */
public class ExpendPageElement {

	public final static String PIC_TEXT = "pic-text"; // 有图片
	public final static String TEXT = "text"; // 无图片

	private String type;
	private String mainTitle;
	private String secondTitle;
	private String picSrc;
	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSecondTitle() {
		return secondTitle;
	}

	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
