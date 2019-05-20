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
@Table(name = "wx_dynamic_content_reply_config")
public class WxDynamicContentReplyConfig implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxDynamicContentReplyConfig";
	public static final String ALIAS_DYNAMIC_CONTENT_REPLY_CONFIG_GUID = "dynamicContentReplyConfigGuid";
	public static final String ALIAS_DYNAMIC_CONTENT_REPLY_CONFIG_NAME = "dynamicContentReplyConfigName";
	public static final String ALIAS_DYNAMIC_CONTENT_NAME = "dynamicContentName";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_IMG_URL = "imgUrl";
	public static final String ALIAS_TO_URL = "toUrl";
	public static final String ALIAS_ORDER = "order";
	public static final String ALIAS_CONFIG_TYPE = "configType";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * dynamicContentReplyConfigGuid       db_column: dynamic_content_reply_config_guid 
     */ 	
	private java.lang.String dynamicContentReplyConfigGuid;
    /**
     * dynamicContentReplyConfigName       db_column: dynamic_content_reply_config_name 
     */ 	
	private java.lang.String dynamicContentReplyConfigName;
    /**
     * dynamicContentName       db_column: dynamic_content_name 
     */ 	
	private java.lang.String dynamicContentName;
    /**
     * title       db_column: title 
     */ 	
	private java.lang.String title;
    /**
     * content       db_column: content 
     */ 	
	private java.lang.String content;
    /**
     * imgUrl       db_column: img_url 
     */ 	
	private java.lang.String imgUrl;
    /**
     * toUrl       db_column: to_url 
     */ 	
	private java.lang.String toUrl;
    /**
     * order       db_column: order 
     */ 	
	
	private java.lang.Integer order;
    /**
     * configType       db_column: config_type 
     */ 	
	private java.lang.String configType;
	//columns END


	public WxDynamicContentReplyConfig(){
	}

	public WxDynamicContentReplyConfig(
		java.lang.String dynamicContentReplyConfigGuid
	){
		this.dynamicContentReplyConfigGuid = dynamicContentReplyConfigGuid;
	}

	

	public void setDynamicContentReplyConfigGuid(java.lang.String value) {
		this.dynamicContentReplyConfigGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "dynamic_content_reply_config_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getDynamicContentReplyConfigGuid() {
		return this.dynamicContentReplyConfigGuid;
	}
	
	@Column(name = "dynamic_content_reply_config_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContentReplyConfigName() {
		return this.dynamicContentReplyConfigName;
	}
	
	public void setDynamicContentReplyConfigName(java.lang.String value) {
		this.dynamicContentReplyConfigName = value;
	}
	
	@Column(name = "dynamic_content_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContentName() {
		return this.dynamicContentName;
	}
	
	public void setDynamicContentName(java.lang.String value) {
		this.dynamicContentName = value;
	}
	
	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	@Column(name = "img_url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	@Column(name = "to_url", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getToUrl() {
		return this.toUrl;
	}
	
	public void setToUrl(java.lang.String value) {
		this.toUrl = value;
	}
	
	@Column(name = "order", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getOrder() {
		return this.order;
	}
	
	public void setOrder(java.lang.Integer value) {
		this.order = value;
	}
	
	@Column(name = "config_type", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getConfigType() {
		return this.configType;
	}
	
	public void setConfigType(java.lang.String value) {
		this.configType = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DynamicContentReplyConfigGuid",getDynamicContentReplyConfigGuid())
			.append("DynamicContentReplyConfigName",getDynamicContentReplyConfigName())
			.append("DynamicContentName",getDynamicContentName())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("ImgUrl",getImgUrl())
			.append("ToUrl",getToUrl())
			.append("Order",getOrder())
			.append("ConfigType",getConfigType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDynamicContentReplyConfigGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxDynamicContentReplyConfig == false) return false;
		if(this == obj) return true;
		WxDynamicContentReplyConfig other = (WxDynamicContentReplyConfig)obj;
		return new EqualsBuilder()
			.append(getDynamicContentReplyConfigGuid(),other.getDynamicContentReplyConfigGuid())
			.isEquals();
	}
}

