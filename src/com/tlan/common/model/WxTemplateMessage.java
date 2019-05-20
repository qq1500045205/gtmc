package com.tlan.common.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Entity
@Table(name = "wx_template_message")
public class WxTemplateMessage implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
    /**
     * tmpGuid       db_column: tmp_guid 
     */ 	
	private java.lang.String tmpGuid;
    /**
     * 模版消息对应的名字如：title       db_column: tmp_field 
     */ 	
	private java.lang.String tmpField;
    /**
     * field对应的说明       db_column: tmp_desc 
     */ 	
	private java.lang.String tmpDesc;
    /**
     * 模版消息说明，如：标题       db_column: tmp_name 
     */ 	
	private java.lang.String tmpName;
    /**
     * 颜色       db_column: tmp_color 
     */ 	
	private java.lang.String tmpColor;
    /**
     * 模版id       db_column: tmp_id 
     */ 	
	private java.lang.String tmpId;
    /**
     * 我们给模版进行的编码：如服务预约通知（fwyy1）       db_column: tmp_code 
     */ 	
	private java.lang.String tmpCode;
    /**
     * createdBy       db_column: created_by 
     */ 	
	private java.lang.String createdBy;
    /**
     * createdOn       db_column: created_on 
     */ 	
	private java.lang.String createdOn;
    /**
     * modifyBy       db_column: modify_by 
     */ 	
	private java.lang.String modifyBy;
    /**
     * modifyOn       db_column: modify_on 
     */ 	
	private java.lang.String modifyOn;
    /**
     * 标题颜色       db_column: top_color 
     */ 	
	private java.lang.String topColor;
	//columns END


	public WxTemplateMessage(){
	}

	public WxTemplateMessage(
		java.lang.String tmpGuid
	){
		this.tmpGuid = tmpGuid;
	}

	

	public void setTmpGuid(java.lang.String value) {
		this.tmpGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "uuid")
	@Column(name = "tmp_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getTmpGuid() {
		return this.tmpGuid;
	}
	
	@Column(name = "tmp_field", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTmpField() {
		return this.tmpField;
	}
	
	public void setTmpField(java.lang.String value) {
		this.tmpField = value;
	}
	
	@Column(name = "tmp_desc", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTmpDesc() {
		return this.tmpDesc;
	}
	
	public void setTmpDesc(java.lang.String value) {
		this.tmpDesc = value;
	}
	
	@Column(name = "tmp_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getTmpName() {
		return this.tmpName;
	}
	
	public void setTmpName(java.lang.String value) {
		this.tmpName = value;
	}
	
	@Column(name = "tmp_color", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.lang.String getTmpColor() {
		return this.tmpColor;
	}
	
	public void setTmpColor(java.lang.String value) {
		this.tmpColor = value;
	}
	
	@Column(name = "tmp_id", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getTmpId() {
		return this.tmpId;
	}
	
	public void setTmpId(java.lang.String value) {
		this.tmpId = value;
	}
	
	@Column(name = "tmp_code", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public java.lang.String getTmpCode() {
		return this.tmpCode;
	}
	
	public void setTmpCode(java.lang.String value) {
		this.tmpCode = value;
	}
	
	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}
	
	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}
	
	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}
	
	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}
	
	@Column(name = "top_color", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public java.lang.String getTopColor() {
		return this.topColor;
	}
	
	public void setTopColor(java.lang.String value) {
		this.topColor = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("TmpGuid",getTmpGuid())
			.append("TmpField",getTmpField())
			.append("TmpDesc",getTmpDesc())
			.append("TmpName",getTmpName())
			.append("TmpColor",getTmpColor())
			.append("TmpId",getTmpId())
			.append("TmpCode",getTmpCode())
			.append("CreatedBy",getCreatedBy())
			.append("CreatedOn",getCreatedOn())
			.append("ModifyBy",getModifyBy())
			.append("ModifyOn",getModifyOn())
			.append("TopColor",getTopColor())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTmpGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxTemplateMessage == false) return false;
		if(this == obj) return true;
		WxTemplateMessage other = (WxTemplateMessage)obj;
		return new EqualsBuilder()
			.append(getTmpGuid(),other.getTmpGuid())
			.isEquals();
	}
}

