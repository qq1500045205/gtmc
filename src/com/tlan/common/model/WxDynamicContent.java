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
@Table(name = "wx_dynamic_content")
public class WxDynamicContent implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxDynamicContent";
	public static final String ALIAS_DYNAMIC_CONTENT_NAME = "dynamicContentName";
	public static final String ALIAS_DYNAMIC_CONTENT_TITLE = "dynamicContentTitle";
	public static final String ALIAS_SHOW_FIELD = "显示字段，组showJson用到";
	public static final String ALIAS_PARAM_FIELD = "参数字段，组paramJson用到";
	public static final String ALIAS_URL_PARAM_NAME = "链接传参参数名";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * dynamicContentName       db_column: dynamic_content_name 
     */ 	
	private java.lang.String dynamicContentName;
    /**
     * dynamicContentTitle       db_column: dynamic_content_title 
     */ 	
	private java.lang.String dynamicContentTitle;
    /**
     * 显示字段，组showJson用到       db_column: show_field 
     */ 	
	private java.lang.String showField;
    /**
     * 参数字段，组paramJson用到       db_column: param_field 
     */ 	
	private java.lang.String paramField;
    /**
     * 链接传参参数名       db_column: url_param_name 
     */ 	
	private java.lang.String urlParamName;
	//columns END


	public WxDynamicContent(){
	}

	public WxDynamicContent(
		java.lang.String dynamicContentName
	){
		this.dynamicContentName = dynamicContentName;
	}

	

	public void setDynamicContentName(java.lang.String value) {
		this.dynamicContentName = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "dynamic_content_name", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContentName() {
		return this.dynamicContentName;
	}
	
	@Column(name = "dynamic_content_title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContentTitle() {
		return this.dynamicContentTitle;
	}
	
	public void setDynamicContentTitle(java.lang.String value) {
		this.dynamicContentTitle = value;
	}
	
	@Column(name = "show_field", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getShowField() {
		return this.showField;
	}
	
	public void setShowField(java.lang.String value) {
		this.showField = value;
	}
	
	@Column(name = "param_field", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getParamField() {
		return this.paramField;
	}
	
	public void setParamField(java.lang.String value) {
		this.paramField = value;
	}
	
	@Column(name = "url_param_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUrlParamName() {
		return this.urlParamName;
	}
	
	public void setUrlParamName(java.lang.String value) {
		this.urlParamName = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("DynamicContentName",getDynamicContentName())
			.append("DynamicContentTitle",getDynamicContentTitle())
			.append("ShowField",getShowField())
			.append("ParamField",getParamField())
			.append("UrlParamName",getUrlParamName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDynamicContentName())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxDynamicContent == false) return false;
		if(this == obj) return true;
		WxDynamicContent other = (WxDynamicContent)obj;
		return new EqualsBuilder()
			.append(getDynamicContentName(),other.getDynamicContentName())
			.isEquals();
	}
}

