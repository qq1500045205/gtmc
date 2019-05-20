package com.tlan.beans;

/**
 * 图文消息对应的bean
 * 
 * @author magenm
 * 
 */
public class SingleNewsBean {

	private String name;
	private String desc;
	private String author;
	private String content;
	private String picSrc;
	private String title;
	private String url;
	private String urlParamName;
	private String urlParamContent;
	private String otherUrlParam;
	private String newsGuid;

	public SingleNewsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SingleNewsBean(String name, String desc, String author,
			String content, String picSrc, String title, String url,
			String urlParamName, String urlParamContent, String otherUrlParam,
			String newsGuid) {
		super();
		this.name = name;
		this.desc = desc;
		this.author = author;
		this.content = content;
		this.picSrc = picSrc;
		this.title = title;
		this.url = url;
		this.urlParamName = urlParamName;
		this.urlParamContent = urlParamContent;
		this.otherUrlParam = otherUrlParam;
		this.newsGuid = newsGuid;
	}

	public String getNewsGuid() {
		return newsGuid;
	}

	public void setNewsGuid(String newsGuid) {
		this.newsGuid = newsGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlParamName() {
		return urlParamName;
	}

	public void setUrlParamName(String urlParamName) {
		this.urlParamName = urlParamName;
	}

	public String getUrlParamContent() {
		return urlParamContent;
	}

	public void setUrlParamContent(String urlParamContent) {
		this.urlParamContent = urlParamContent;
	}

	public String getOtherUrlParam() {
		return otherUrlParam;
	}

	public void setOtherUrlParam(String otherUrlParam) {
		this.otherUrlParam = otherUrlParam;
	}

}
