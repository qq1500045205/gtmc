/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.questionnaire;

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
@Table(name = "wx_module_content_question")
public class WxModuleContentQuestion implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentQuestion";
	public static final String ALIAS_QUESTION_GUID = "questionGuid";
	public static final String ALIAS_QUESTION_VALUE = "questionValue";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_GZH_HASH = "1: 选择题     2：10分制题     3：简述题";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_CREATED_BY = "createdBy";
	public static final String ALIAS_DELETE_FLAG = "delete_flag";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * questionGuid       db_column: question_guid 
     */ 	
	private java.lang.Integer questionGuid;
    /**
     * questionValue       db_column: question_value 
     */ 	
	private java.lang.String questionValue;
    /**
     * 1: 选择题     2：10分制题     3：简述题
     * type       db_column: type 
     */ 	
	
	private java.lang.Integer type;
    /**
     *       db_column: gzh_hash 
     */ 	
	private java.lang.String gzhHash;
    /**
     * createdOn       db_column: created_on 
     */ 	
	private java.lang.String createdOn;
    /**
     * createdBy       db_column: created_by 
     */ 	
	private java.lang.String createdBy;
	/**
     * deleteFlag       db_column: delete_flag 
     */ 	
	private java.lang.Integer deleteFlag;
	//columns END


	public WxModuleContentQuestion(){
	}

	public WxModuleContentQuestion(
		java.lang.Integer questionGuid
	){
		this.questionGuid = questionGuid;
	}

	

	public void setQuestionGuid(java.lang.Integer value) {
		this.questionGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "increment")
	@Column(name = "question_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.Integer getQuestionGuid() {
		return this.questionGuid;
	}
	
	@Column(name = "question_value", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getQuestionValue() {
		return this.questionValue;
	}
	
	public void setQuestionValue(java.lang.String value) {
		this.questionValue = value;
	}
	
	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}
	
	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}
	
	@Column(name = "delete_flag", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(java.lang.Integer value) {
		this.deleteFlag = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("QuestionGuid",getQuestionGuid())
			.append("QuestionValue",getQuestionValue())
			.append("Type",getType())
			.append("GzhHash",getGzhHash())
			.append("CreatedOn",getCreatedOn())
			.append("CreatedBy",getCreatedBy())
			.append("DeleteFlag",getDeleteFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getQuestionGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentQuestion == false) return false;
		if(this == obj) return true;
		WxModuleContentQuestion other = (WxModuleContentQuestion)obj;
		return new EqualsBuilder()
			.append(getQuestionGuid(),other.getQuestionGuid())
			.isEquals();
	}
}

