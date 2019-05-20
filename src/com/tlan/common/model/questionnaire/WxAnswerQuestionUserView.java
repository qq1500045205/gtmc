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
import net.sf.oval.constraint.NotBlank;

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
@Table(name = "wx_answer_question_user_view")
public class WxAnswerQuestionUserView implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxAnswerQuestionUserView";
	public static final String ALIAS_ANSWER_GUID = "answerGuid";
	public static final String ALIAS_ANSWER = "answer";
	public static final String ALIAS_ANSWER_TIME = "answerTime";
	public static final String ALIAS_QUESTION_GUID = "questionGuid";
	public static final String ALIAS_QUESTION_VALUE = "questionValue";
	public static final String ALIAS_QUESTION_TYPE = "1: 选择题     2：10分制题     3：简述题";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_USER_NAME = "userName";
	public static final String ALIAS_NICK_NAME = "微信用户昵称";
	public static final String ALIAS_OPEN_ID = "openId";
	public static final String ALIAS_USER_TEL = "userTel";
	public static final String ALIAS_USER_MEMBER_LEVEL = "1 非会员   2 普通会员   3 高级会员";
	public static final String ALIAS_USER_MEMBER_NO = "会员号";
	public static final String ALIAS_USER_STATUS = " 1 关注   2 取消关注";
	public static final String ALIAS_QUESTION_DELETE_FLAG = " 0 未删除    1 已删除";
	
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
     * answerTime       db_column: answer_time 
     */ 	
	private java.lang.String answerTime;
    /**
     * questionGuid       db_column: question_guid 
     */ 	
	private java.lang.String questionGuid;
    /**
     * questionValue       db_column: question_value 
     */ 	
	private java.lang.String questionValue;
    /**
     * 1: 选择题     2：10分制题     3：简述题       db_column: question_type 
     */ 	
	
	private java.lang.Integer questionType;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	
	private java.lang.String gzhHash;
    /**
     * userName       db_column: user_name 
     */ 	
	private java.lang.String userName;
    /**
     * 微信用户昵称       db_column: nick_name 
     */ 	
	private java.lang.String nickName;
    /**
     * openId       db_column: open_id 
     */ 	
	private java.lang.String openId;
    /**
     * userTel       db_column: user_tel 
     */ 	
	private java.lang.String userTel;
    /**
     * 1 非会员   2 普通会员   3 高级会员       db_column: user_member_level 
     */ 	
	
	private java.lang.Integer userMemberLevel;
    /**
     * 会员号       db_column: user_member_no 
     */ 	
	private java.lang.String userMemberNo;
    /**
     *  1 关注   2 取消关注       db_column: user_status 
     */ 	
	private java.lang.Integer userStatus;
	/**
     * 0 未删除   1 已删除       db_column: question_delete_flag 
     */ 	
	private java.lang.Integer questionDeleteFlag;
	//columns END
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "answer_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getAnswerGuid() {
		return this.answerGuid;
	}
	
	public void setAnswerGuid(java.lang.String value) {
		this.answerGuid = value;
	}
	
	@Column(name = "answer", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(java.lang.String value) {
		this.answer = value;
	}
	
	@Column(name = "answer_time", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getAnswerTime() {
		return this.answerTime;
	}
	
	public void setAnswerTime(java.lang.String value) {
		this.answerTime = value;
	}
	
	@Column(name = "question_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getQuestionGuid() {
		return this.questionGuid;
	}
	
	public void setQuestionGuid(java.lang.String value) {
		this.questionGuid = value;
	}
	
	@Column(name = "question_value", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getQuestionValue() {
		return this.questionValue;
	}
	
	public void setQuestionValue(java.lang.String value) {
		this.questionValue = value;
	}
	
	@Column(name = "question_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getQuestionType() {
		return this.questionType;
	}
	
	public void setQuestionType(java.lang.Integer value) {
		this.questionType = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "nick_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	@Column(name = "open_id", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	public void setOpenId(java.lang.String value) {
		this.openId = value;
	}
	
	@Column(name = "user_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}
	
	@Column(name = "user_member_level", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserMemberLevel() {
		return this.userMemberLevel;
	}
	
	public void setUserMemberLevel(java.lang.Integer value) {
		this.userMemberLevel = value;
	}
	
	@Column(name = "user_member_no", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getUserMemberNo() {
		return this.userMemberNo;
	}
	
	public void setUserMemberNo(java.lang.String value) {
		this.userMemberNo = value;
	}
	
	@Column(name = "user_status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getUserStatus() {
		return this.userStatus;
	}
	
	public void setUserStatus(java.lang.Integer value) {
		this.userStatus = value;
	}
	
	@Column(name = "question_delete_flag", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getQuestionDeleteFlag() {
		return this.questionDeleteFlag;
	}
	
	public void setQuestionDeleteFlag(java.lang.Integer value) {
		this.questionDeleteFlag = value;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("AnswerGuid",getAnswerGuid())
			.append("Answer",getAnswer())
			.append("AnswerTime",getAnswerTime())
			.append("QuestionGuid",getQuestionGuid())
			.append("QuestionValue",getQuestionValue())
			.append("QuestionType",getQuestionType())
			.append("GzhHash",getGzhHash())
			.append("UserName",getUserName())
			.append("NickName",getNickName())
			.append("OpenId",getOpenId())
			.append("UserTel",getUserTel())
			.append("UserMemberLevel",getUserMemberLevel())
			.append("UserMemberNo",getUserMemberNo())
			.append("UserStatus",getUserStatus())
			.append("QuestionDeleteFlag",getQuestionDeleteFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxAnswerQuestionUserView == false) return false;
		if(this == obj) return true;
		WxAnswerQuestionUserView other = (WxAnswerQuestionUserView)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

