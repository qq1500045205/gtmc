/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.oval.constraint.Length;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "wx_message_content")
public class WxMessageContent implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String NEWS = "news";
	public static final String SINGLE = "single";
	public static final String TEXT = "text";
	public static final String NEWS_NAME = "多图文消息";
	public static final String SINGLE_NAME = "单图文消息";
	public static final String TEXT_NAME = "文本消息";
	
	public static final String NO_PUBLISH = "未发布";
	public static final String FINISH_PUBLISH = "已发布";
	
	//alias
	public static final String TABLE_ALIAS = "WxMessageContent";
	public static final String ALIAS_NEWS_GUID = "主键guid";
	public static final String ALIAS_NEWS_NAME = "图文别名,多图文时只有父图文有值";
	public static final String ALIAS_NEWS_TITLE = "图文标题";
	public static final String ALIAS_NEWS_AUTHOR = "作者";
	public static final String ALIAS_NEWS_DESCRIPTION = "图文描述";
	public static final String ALIAS_NEWS_PIC = "封面";
	public static final String ALIAS_NEWS_CONTENT = "图文内容";
	public static final String ALIAS_NEWS_URL = "图文连接";
	public static final String ALIAS_NEWS_URL_PARAM_NAME = "url参数名";
	public static final String ALIAS_NEWS_URL_PARAM_CONTENT = "参数内容";
	public static final String ALIAS_NEWS_OTHRT_PARAM = "其他参数";
	public static final String ALIAS_PARENT_GUID = "父图文guid";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_MODIFY_ON = "modifyOn";
	public static final String ALIAS_MODIFY_BY = "modifyBy";
	public static final String ALIAS_PUBLISH_ON = "publishOn";
	public static final String ALIAS_PUBLISH_BY = "publishBy";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_TYPE_NAME = "消息类型  多图文消息   单图文消息 等";
	public static final String ALIAS_TYPE = "多图文 news  单图文 single   文本 text";
	public static final String ALIAS_DYNAMIC_CONTENT = "dynamicContent";
	public static final String ALIAS_DYNAMIC_CONTENT_INSTANCE_GUID = "dynamicContentInstanceGuid";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 主键guid       db_column: news_guid 
     */ 	
	private java.lang.String newsGuid;
    /**
     * 图文别名,多图文时只有父图文有值       db_column: news_name 
     */ 	
	private java.lang.String newsName;
    /**
     * 图文标题       db_column: news_title 
     */ 	
	private java.lang.String newsTitle;
    /**
     * 作者       db_column: news_author 
     */ 	
	private java.lang.String newsAuthor;
    /**
     * 图文描述       db_column: news_description 
     */ 	
	private java.lang.String newsDescription;
    /**
     * 封面       db_column: news_pic 
     */ 	
	private java.lang.String newsPic;
    /**
     * 图文内容       db_column: news_content 
     */ 	
	private java.lang.String newsContent;
    /**
     * 图文连接       db_column: news_url 
     */ 	
	private java.lang.String newsUrl;
	/**
     * 图文连接       db_column: url_param_name 
     */ 	
	private java.lang.String urlParamName;
	/**
     * 图文连接       db_column: url_param_content 
     */ 	
	private java.lang.String urlParamContent;
	/**
     * 图文连接       db_column: other_url_param 
     */ 	
	private java.lang.String otherUrlParam;
    /**
     * 父图文guid       db_column: parent_guid 
     */ 	
	private java.lang.String parentGuid;
    /**
     * createdOn       db_column: created_on 
     */ 	
	private java.lang.String createdOn;
    /**
     * createdBy       db_column: created_by 
     */ 	
	private java.lang.String createdBy;
    /**
     * modifyOn       db_column: modify_on 
     */ 	
	private java.lang.String modifyOn;
    /**
     * modifyBy       db_column: modify_by 
     */ 	
	private java.lang.String modifyBy;
    /**
     * publishOn       db_column: publish_on 
     */ 	
	private java.lang.String publishOn;
    /**
     * publishBy       db_column: publish_by 
     */ 	
	private java.lang.String publishBy;
    /**
     * 状态       db_column: status 
     */ 	
	private java.lang.String status;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	
	private java.lang.String gzhHash;
    /**
     * 消息类型  多图文消息   单图文消息 等       db_column: type_name 
     */ 	
	private java.lang.String typeName;
    /**
     * 多图文 news  单图文 single   文本 text       db_column: type 
     */ 	
	private java.lang.String type;
    /**
     * dynamicContent       db_column: dynamic_content 
     */ 	
	private java.lang.String dynamicContent;
    /**
     * dynamicContentInstanceGuid       db_column: dynamic_content_instance_guid 
     */ 	
	private java.lang.String dynamicContentInstanceGuid;
	//columns END


	public WxMessageContent(){
	}

	public WxMessageContent(
		java.lang.String newsGuid
	){
		this.newsGuid = newsGuid;
	}

	

	public void setNewsGuid(java.lang.String value) {
		this.newsGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "news_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getNewsGuid() {
		return this.newsGuid;
	}
	
	@Column(name = "news_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsName() {
		return this.newsName;
	}
	
	public void setNewsName(java.lang.String value) {
		this.newsName = value;
	}
	
	@Column(name = "news_title", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNewsTitle() {
		return this.newsTitle;
	}
	
	public void setNewsTitle(java.lang.String value) {
		this.newsTitle = value;
	}
	
	@Column(name = "news_author", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNewsAuthor() {
		return this.newsAuthor;
	}
	
	public void setNewsAuthor(java.lang.String value) {
		this.newsAuthor = value;
	}
	
	@Column(name = "news_description", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsDescription() {
		return this.newsDescription;
	}
	
	public void setNewsDescription(java.lang.String value) {
		this.newsDescription = value;
	}
	
	@Column(name = "news_pic", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getNewsPic() {
		return this.newsPic;
	}
	
	public void setNewsPic(java.lang.String value) {
		this.newsPic = value;
	}
	
	@Column(name = "news_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getNewsContent() {
		return this.newsContent;
	}
	
	public void setNewsContent(java.lang.String value) {
		this.newsContent = value;
	}
	
	@Column(name = "news_url", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsUrl() {
		return this.newsUrl;
	}
	
	public void setNewsUrl(java.lang.String value) {
		this.newsUrl = value;
	}

	@Column(name = "url_param_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUrlParamName() {
		return this.urlParamName;
	}
	
	public void setUrlParamName(java.lang.String value) {
		this.urlParamName = value;
	}

	@Column(name = "url_param_content", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUrlParamContent() {
		return this.urlParamContent;
	}
	
	public void setUrlParamContent(java.lang.String value) {
		this.urlParamContent = value;
	}

	@Column(name = "other_url_param", unique = false, nullable = true, insertable = true, updatable = true, length = 65525)
	public java.lang.String getOtherUrlParam() {
		return this.otherUrlParam;
	}
	
	public void setOtherUrlParam(java.lang.String value) {
		this.otherUrlParam = value;
	}
	
	@Column(name = "parent_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}
	
	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}
	
	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}
	
	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}
	
	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}
	
	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}
	
	@Column(name = "publish_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPublishOn() {
		return this.publishOn;
	}
	
	public void setPublishOn(java.lang.String value) {
		this.publishOn = value;
	}
	
	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}
	
	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "type_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getTypeName() {
		return this.typeName;
	}
	
	public void setTypeName(java.lang.String value) {
		this.typeName = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	@Column(name = "dynamic_content", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContent() {
		return this.dynamicContent;
	}
	
	public void setDynamicContent(java.lang.String value) {
		this.dynamicContent = value;
	}
	
	@Column(name = "dynamic_content_instance_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getDynamicContentInstanceGuid() {
		return this.dynamicContentInstanceGuid;
	}
	
	public void setDynamicContentInstanceGuid(java.lang.String value) {
		this.dynamicContentInstanceGuid = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("NewsGuid",getNewsGuid())
			.append("NewsName",getNewsName())
			.append("NewsTitle",getNewsTitle())
			.append("NewsAuthor",getNewsAuthor())
			.append("NewsDescription",getNewsDescription())
			.append("NewsPic",getNewsPic())
			.append("NewsContent",getNewsContent())
			.append("NewsUrl",getNewsUrl())
			.append("ParentGuid",getParentGuid())
			.append("CreatedOn",getCreatedOn())
			.append("CreatedBy",getCreatedBy())
			.append("ModifyOn",getModifyOn())
			.append("ModifyBy",getModifyBy())
			.append("PublishOn",getPublishOn())
			.append("PublishBy",getPublishBy())
			.append("Status",getStatus())
			.append("GzhHash",getGzhHash())
			.append("TypeName",getTypeName())
			.append("Type",getType())
			.append("DynamicContent",getDynamicContent())
			.append("DynamicContentInstanceGuid",getDynamicContentInstanceGuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getNewsGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxMessageContent == false) return false;
		if(this == obj) return true;
		WxMessageContent other = (WxMessageContent)obj;
		return new EqualsBuilder()
			.append(getNewsGuid(),other.getNewsGuid())
			.isEquals();
	}
}

