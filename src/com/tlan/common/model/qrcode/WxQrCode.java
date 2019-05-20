/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.qrcode;

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
@Table(name = "wx_qr_code")
public class WxQrCode implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxQrCode";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SOURCE_FROM = "sourceFrom";
	public static final String ALIAS_TICKET = "ticket";
	public static final String ALIAS_PIC = "pic";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_DELETE_FLAG = "delete_flag";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */ 	
	
	private java.lang.Integer id;
    /**
     * sourceFrom       db_column: source_from 
     */ 	
	private java.lang.String sourceFrom;
    /**
     * ticket       db_column: ticket 
     */ 	
	private java.lang.String ticket;
    /**
     * pic       db_column: pic 
     */ 	
	private java.lang.String pic;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	
	private java.lang.String gzhHash;
	/**
     * deleteFlag       db_column: delete_flag 
     */ 	
	private java.lang.Integer deleteFlag;
	//columns END


	public WxQrCode(){
	}

	public WxQrCode(
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
	
	@Column(name = "source_from", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getSourceFrom() {
		return this.sourceFrom;
	}
	
	public void setSourceFrom(java.lang.String value) {
		this.sourceFrom = value;
	}
	
	@Column(name = "ticket", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getTicket() {
		return this.ticket;
	}
	
	public void setTicket(java.lang.String value) {
		this.ticket = value;
	}
	
	@Column(name = "pic", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getPic() {
		return this.pic;
	}
	
	public void setPic(java.lang.String value) {
		this.pic = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "delete_flag", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SourceFrom",getSourceFrom())
			.append("Ticket",getTicket())
			.append("Pic",getPic())
			.append("GzhHash",getGzhHash())
			.append("DeleteFlag",getDeleteFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxQrCode == false) return false;
		if(this == obj) return true;
		WxQrCode other = (WxQrCode)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

