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
@Table(name = "wx_dynamic_content_instance")
public class WxDynamicContentInstance implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxDynamicContentInstance";
	public static final String ALIAS_WX_DYNAMIC_CONTENT_INSTANCE_GUID = "wxDynamicContentInstanceGuid";
	public static final String ALIAS_DYNAMIC_CONTENT_NAME = "dynamicContentName";
	public static final String ALIAS_SHOW_FIELD = "showField";
	public static final String ALIAS_PARAM_FIELD = "paramField";
	public static final String ALIAS_URL_PARAM_FIELD = "urlParamField";
	public static final String ALIAS_REPLY_JSON = "replyJson";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * wxDynamicContentInstanceGuid       db_column: wx_dynamic_content_instance_guid 
     */ 	
	private java.lang.String wxDynamicContentInstanceGuid;
    /**
     * dynamicContentName       db_column: dynamic_content_name 
     */ 	
	private java.lang.String dynamicContentName;
    /**
     * showField       db_column: show_field 
     */ 	
	private java.lang.String showField;
    /**
     * paramField       db_column: param_field 
     */ 	
	private java.lang.String paramField;
    /**
     * urlParamField       db_column: url_param_field 
     */ 	
	private java.lang.String urlParamField;
    /**
     * replyJson       db_column: reply_json 
     */
	private java.lang.String replyJson;
	//columns END


	public WxDynamicContentInstance(){
	}

	public WxDynamicContentInstance(
		java.lang.String wxDynamicContentInstanceGuid
	){
		this.wxDynamicContentInstanceGuid = wxDynamicContentInstanceGuid;
	}

	

	public void setWxDynamicContentInstanceGuid(java.lang.String value) {
		this.wxDynamicContentInstanceGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "wx_dynamic_content_instance_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getWxDynamicContentInstanceGuid() {
		return this.wxDynamicContentInstanceGuid;
	}
	
	@Column(name = "dynamic_content_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContentName() {
		return this.dynamicContentName;
	}
	
	public void setDynamicContentName(java.lang.String value) {
		this.dynamicContentName = value;
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
	
	@Column(name = "url_param_field", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUrlParamField() {
		return this.urlParamField;
	}
	
	public void setUrlParamField(java.lang.String value) {
		this.urlParamField = value;
	}
	
	@Column(name = "reply_json", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getReplyJson() {
		return this.replyJson;
	}
	
	public void setReplyJson(java.lang.String value) {
		this.replyJson = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("WxDynamicContentInstanceGuid",getWxDynamicContentInstanceGuid())
			.append("DynamicContentName",getDynamicContentName())
			.append("ShowField",getShowField())
			.append("ParamField",getParamField())
			.append("UrlParamField",getUrlParamField())
			.append("ReplyJson",getReplyJson())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWxDynamicContentInstanceGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxDynamicContentInstance == false) return false;
		if(this == obj) return true;
		WxDynamicContentInstance other = (WxDynamicContentInstance)obj;
		return new EqualsBuilder()
			.append(getWxDynamicContentInstanceGuid(),other.getWxDynamicContentInstanceGuid())
			.isEquals();
	}
}

