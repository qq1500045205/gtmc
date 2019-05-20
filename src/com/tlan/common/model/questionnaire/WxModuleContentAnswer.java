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
@Table(name = "wx_module_content_answer")
public class WxModuleContentAnswer implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentAnswer";
	public static final String ALIAS_ANSWER_GUID = "answerGuid";
	public static final String ALIAS_ANSWER = "answer";
	public static final String ALIAS_QUESTION_GUID = "questionGuid";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_ANSWER_TIME = "answerTime";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * answerGuid       db_column: answer_guid 
     */ 	
	private java.lang.String answerGuid;
    /**
     * answer       db_column: answer 
     */ 	
	private java.lang.String answer;
    /**
     * questionGuid       db_column: question_guid 
     */ 	
	private java.lang.Integer questionGuid;
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * answerTime       db_column: answer_time 
     */ 	
	private java.lang.String answerTime;
	//columns END


	public WxModuleContentAnswer(){
	}

	public WxModuleContentAnswer(
		java.lang.String answerGuid
	){
		this.answerGuid = answerGuid;
	}

	

	public void setAnswerGuid(java.lang.String value) {
		this.answerGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "answer_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getAnswerGuid() {
		return this.answerGuid;
	}
	
	@Column(name = "answer", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(java.lang.String value) {
		this.answer = value;
	}
	
	@Column(name = "question_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.Integer getQuestionGuid() {
		return this.questionGuid;
	}
	
	public void setQuestionGuid(java.lang.Integer value) {
		this.questionGuid = value;
	}
	
	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "answer_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getAnswerTime() {
		return this.answerTime;
	}
	
	public void setAnswerTime(java.lang.String value) {
		this.answerTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("AnswerGuid",getAnswerGuid())
			.append("Answer",getAnswer())
			.append("QuestionGuid",getQuestionGuid())
			.append("OpenId",getOpenId())
			.append("AnswerTime",getAnswerTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAnswerGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentAnswer == false) return false;
		if(this == obj) return true;
		WxModuleContentAnswer other = (WxModuleContentAnswer)obj;
		return new EqualsBuilder()
			.append(getAnswerGuid(),other.getAnswerGuid())
			.isEquals();
	}
}

