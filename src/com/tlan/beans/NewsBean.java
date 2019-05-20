package com.tlan.beans;

/**
 * 图文消息对应的bean
 * 
 * @author magenm
 * 
 */
public class NewsBean {

	private String author;
	private String content;
	private String picSrc;
	private String title;
	private String url;
	private String outlink;
	private String urlParamName;
	private String urlParamContent;
	private String otherUrlParam;
	private String newsGuid;
	private String date;

	public NewsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsBean(String author, String content, String picSrc, String title,
			String url, String outlink, String urlParamName, String urlParamContent,
			String otherUrlParam, String newsGuid) {
		super();
		this.author = author;
		this.content = content;
		this.picSrc = picSrc;
		this.title = title;
		this.url = url;
		this.outlink = outlink;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getOutlink() {
		return outlink;
	}

	public void setOutlink(String outlink) {
		this.outlink = outlink;
	}

}
