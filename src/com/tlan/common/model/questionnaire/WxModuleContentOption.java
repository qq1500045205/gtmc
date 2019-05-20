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
@Table(name = "wx_module_content_option")
public class WxModuleContentOption implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentOption";
	public static final String ALIAS_OPTION_GUID = "optionGuid";
	public static final String ALIAS_OPTION_INDEX = "optionIndex";
	public static final String ALIAS_OPTION_VALUE = "optionValue";
	public static final String ALIAS_QUESTION_GUID = "questionGuid";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * optionGuid       db_column: option_guid 
     */ 	
	private java.lang.String optionGuid;
    /**
     * optionIndex       db_column: option_index 
     */ 	
	private java.lang.String optionIndex;
    /**
     * optionValue       db_column: option_value 
     */ 	
	private java.lang.String optionValue;
    /**
     * questionGuid       db_column: question_guid 
     */ 	
	private java.lang.String questionGuid;
	//columns END


	public WxModuleContentOption(){
	}

	public WxModuleContentOption(
		java.lang.String optionGuid
	){
		this.optionGuid = optionGuid;
	}

	

	public void setOptionGuid(java.lang.String value) {
		this.optionGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "option_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getOptionGuid() {
		return this.optionGuid;
	}
	
	@Column(name = "option_index", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getOptionIndex() {
		return this.optionIndex;
	}
	
	public void setOptionIndex(java.lang.String value) {
		this.optionIndex = value;
	}
	
	@Column(name = "option_value", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getOptionValue() {
		return this.optionValue;
	}
	
	public void setOptionValue(java.lang.String value) {
		this.optionValue = value;
	}
	
	@Column(name = "question_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getQuestionGuid() {
		return this.questionGuid;
	}
	
	public void setQuestionGuid(java.lang.String value) {
		this.questionGuid = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("OptionGuid",getOptionGuid())
			.append("OptionIndex",getOptionIndex())
			.append("OptionValue",getOptionValue())
			.append("QuestionGuid",getQuestionGuid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOptionGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentOption == false) return false;
		if(this == obj) return true;
		WxModuleContentOption other = (WxModuleContentOption)obj;
		return new EqualsBuilder()
			.append(getOptionGuid(),other.getOptionGuid())
			.isEquals();
	}
}

