package com.tlan.common.model.mannual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author lishiru
 */

@Entity
@Table(name="wx_module_content_manual_text")
public class WxModuleContentMannualText implements  java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "WxModuleContentMannualText";
	public static final String ALIAS_TEXT_GUID = "textGuid";
	public static final String ALIAS_TYPE_GUID = "typeGuid";
	public static final String ALIAS_TYPE_NAME = "typeName";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_CONTENT = "content"; 
	public static final String ALIAS_CREATED_TIME = "createdTime"; 
	public static final String ALIAS_GZH_HASH = "gzhHash";
	
	 /**
     * textGuid       db_column: text_guid 
     */ 
	private java.lang.String textGuid;
	
	/**
     * typeGuid       db_column: type_guid 
     */ 
	private java.lang.String typeGuid;
	
	
	/**
     * typeName      db_column: type_name
     */ 
	private java.lang.String typeName;
	
	
	/**
	 * gzhHash        db_column:gzh_hash
	 **/
	private java.lang.String gzhHash;
	
	/**
     * title       db_column: title 
     */ 
	private java.lang.String title;
	
	/**
     * content       db_column: content 
     */ 
	private java.lang.String content;
	
	/**
     * createdTime       db_column: created_time 
     */ 
	private java.lang.String createdTime;
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "text_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getTextGuid() {
		return textGuid;
	}
	public void setTextGuid(java.lang.String textGuid) {
		this.textGuid = textGuid;
	}
	
	@Column(name = "type_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getTypeGuid() {
		return typeGuid;
	}
	public void setTypeGuid(java.lang.String typeGuid) {
		this.typeGuid = typeGuid;
	}
	
	@Column(name = "type_name", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getTypeName() {
		return typeName;
	}
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name = "title", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	@Column(name = "content", unique = true, nullable = false, insertable = true, updatable = true)
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	@Column(name = "created_time", unique = true, nullable = false, insertable = true, updatable = true)
	public java.lang.String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(java.lang.String createdTime) {
		this.createdTime = createdTime;
	}
	
	@Column(name = "gzh_hash", unique = true, nullable = false, insertable = true, updatable = true)
	public java.lang.String getGzhHash() {
		return gzhHash;
	}
	public void setGzhHash(java.lang.String gzhHash) {
		this.gzhHash = gzhHash;
	}
	
	
}
