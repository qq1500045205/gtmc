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
@Table(name="wx_module_content_manual_type")
public class WxModuleContentMannualType implements  java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentMannualType";
	public static final String ALIAS_TYPE_GUID = "typeGuid";
	public static final String ALIAS_TYPE_NAME = "typeName"; 
	public static final String ALIAS_PIC = "pic"; 
	public static final String ALIAS_ORD = "ord"; 
	public static final String ALIAS_GZH_HASH = "gzhHash"; 
	
	 /**
     * typeGuid       db_column: type_guid 
     */ 
	private java.lang.String typeGuid;
	/**
     * typeName       db_column: type_name 
     */ 
	
	private java.lang.String typeName;
	/**
     * pic       db_column: pic 
     */ 
	private java.lang.String pic;
	/**
     * order       db_column: gzh_hash 
     */ 
	private java.lang.String gzhHash;
	
	/**
     * order       db_column: ord 
     */ 
	private java.lang.String ord;
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
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
	
	@Column(name = "pic", unique = true, nullable = false, insertable = true, updatable = true, length = 200)
	public java.lang.String getPic() {
		return pic;
	}
	public void setPic(java.lang.String pic) {
		this.pic = pic;
	}
	
	@Column(name = "ord", unique = true, nullable = false, insertable = true, updatable = true)
	public java.lang.String getOrd() {
		return ord;
	}
	public void setOrd(java.lang.String order) {
		this.ord = order;
	}
	
	@Column(name = "gzh_hash", unique = true, nullable = false, insertable = true, updatable = true)
	public java.lang.String getGzhHash() {
		return gzhHash;
	}
	public void setGzhHash(java.lang.String gzhHash) {
		this.gzhHash = gzhHash;
	}
 
	
}
