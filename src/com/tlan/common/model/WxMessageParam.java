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
@Table(name = "wx_message_param")
public class WxMessageParam implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxMessageParam";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PARAM_NAME = "paramName";
	public static final String ALIAS_TABLE_NAME = "tableName";
	public static final String ALIAS_KEY = "key";
	public static final String ALIAS_VALUE = "value";
	public static final String ALIAS_KEY_NAME = "key_name";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */ 	
	
	private java.lang.Integer id;
    /**
     * paramName       db_column: param_name 
     */ 	
	private java.lang.String paramName;
    /**
     * tableName       db_column: table_name 
     */ 	
	private java.lang.String tableName;
    /**
     * key       db_column: key 
     */ 	
	private java.lang.String key;
    /**
     * value       db_column: value 
     */ 	
	private java.lang.String value;
	/**
     * keyName       db_column: key_name 
     */ 	
	private java.lang.String keyName;
	//columns END


	public WxMessageParam(){
	}

	public WxMessageParam(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}
	
	@Column(name = "param_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getParamName() {
		return this.paramName;
	}
	
	public void setParamName(java.lang.String value) {
		this.paramName = value;
	}
	
	@Column(name = "table_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}
	
	@Column(name = "key", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getKey() {
		return this.key;
	}
	
	public void setKey(java.lang.String value) {
		this.key = value;
	}
	
	@Column(name = "value", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	@Column(name = "key_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getKeyName() {
		return this.keyName;
	}
	
	public void setKeyName(java.lang.String value) {
		this.keyName = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ParamName",getParamName())
			.append("TableName",getTableName())
			.append("Key",getKey())
			.append("Value",getValue())
			.append("KeyName",getKeyName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxMessageParam == false) return false;
		if(this == obj) return true;
		WxMessageParam other = (WxMessageParam)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

