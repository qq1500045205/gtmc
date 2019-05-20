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
@Table(name = "wx_user_status")
public class WxUserStatus implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final int unReply = 1;
	public static final int replied = 2;
	public static final int overdue = 3;
	//在线客服系统 add by lyn 2014/5/7 start
	public static final int crUnReply = 4;//cr问题列表
	public static final int crReplied = 5;//cr答案
	public static final int crSession = 6;//人工状态
	//在线客服系统 add by lyn 2014/5/7 end
	
	//alias
	public static final String TABLE_ALIAS = "WxUserStatus";
	public static final String ALIAS_USER_STATUS_GUID = "userStatusGuid";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_JSON = "答案json";
	//在线客服系统 modify by lyn 2014/5/7 start
	public static final String ALIAS_STATUS = "1 未回复   2 已回复   3 已过期   4 CR再search 5 CR对话中 6 人工状态";
	//在线客服系统 modify by lyn 2014/5/7 end
	public static final String ALIAS_MODIFY_ON = "modifyOn";
	public static final String ALIAS_DYNAMIC_CONTENT_INSTANCE_GUID = "dynamicContentInstanceGuid";
	public static final String ALIAS_DYNAMIC_CONTENT = "dynamicContent";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * userStatusGuid       db_column: user_status_guid 
     */ 	
	private java.lang.String userStatusGuid;
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * 答案json       db_column: json 
     */ 	
	private java.lang.String json;
    /**
     * 1 未回复   2 已回复   3 已过期       db_column: status 
     */ 	
	
	private java.lang.Integer status;
    /**
     * modifyOn       db_column: modify_on 
     */ 	
	private java.lang.String modifyOn;
    /**
     * dynamicContentInstanceGuid       db_column: dynamic_content_instance_guid 
     */ 	
	private java.lang.String dynamicContentInstanceGuid;
    /**
     * dynamicContent       db_column: dynamic_content 
     */ 	
	private java.lang.String dynamicContent;
	//columns END


	public WxUserStatus(){
	}

	public WxUserStatus(
		java.lang.String userStatusGuid
	){
		this.userStatusGuid = userStatusGuid;
	}

	

	public void setUserStatusGuid(java.lang.String value) {
		this.userStatusGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "user_status_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getUserStatusGuid() {
		return this.userStatusGuid;
	}
	
	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "json", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getJson() {
		return this.json;
	}
	
	public void setJson(java.lang.String value) {
		this.json = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}
	
	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}
	
	@Column(name = "dynamic_content_instance_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getDynamicContentInstanceGuid() {
		return this.dynamicContentInstanceGuid;
	}
	
	public void setDynamicContentInstanceGuid(java.lang.String value) {
		this.dynamicContentInstanceGuid = value;
	}
	
	@Column(name = "dynamic_content", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContent() {
		return this.dynamicContent;
	}
	
	public void setDynamicContent(java.lang.String value) {
		this.dynamicContent = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserStatusGuid",getUserStatusGuid())
			.append("OpenId",getOpenId())
			.append("Json",getJson())
			.append("Status",getStatus())
			.append("ModifyOn",getModifyOn())
			.append("DynamicContentInstanceGuid",getDynamicContentInstanceGuid())
			.append("DynamicContent",getDynamicContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserStatusGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxUserStatus == false) return false;
		if(this == obj) return true;
		WxUserStatus other = (WxUserStatus)obj;
		return new EqualsBuilder()
			.append(getUserStatusGuid(),other.getUserStatusGuid())
			.isEquals();
	}
}

