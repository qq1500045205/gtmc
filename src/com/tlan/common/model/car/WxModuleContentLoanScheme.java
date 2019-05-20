/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.car;

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
@Table(name = "wx_module_content_loan_scheme")
public class WxModuleContentLoanScheme implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleContentLoanScheme";
	public static final String ALIAS_LOAN_SCHEME_GUID = "loanSchemeGuid";
	public static final String ALIAS_LOAN_SCHEME_NAME = "loanSchemeName";
	public static final String ALIAS_FIRST_PAY_PERCENT = "firstPayPercent";
	public static final String ALIAS_LAST_PAY_PERCENT = "lastPayPercent";
	public static final String ALIAS_MONTH_NUM = "monthNum";
	public static final String ALIAS_PAY_PER_MONTH_PERCENT = "payPerMonthPercent";
	
	//date formats
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * loanSchemeGuid       db_column: loan_scheme_guid 
     */ 	
	private java.lang.String loanSchemeGuid;
    /**
     * loanSchemeName       db_column: loan_scheme_name 
     */ 	
	private java.lang.String loanSchemeName;
    /**
     * firstPayPercent       db_column: first_pay_percent 
     */ 	
	private java.lang.String firstPayPercent;
    /**
     * lastPayPercent       db_column: last_pay_percent 
     */ 	
	private java.lang.String lastPayPercent;
    /**
     * monthNum       db_column: month_num 
     */ 	
	
	private java.lang.Integer monthNum;
    /**
     * payPerMonthPercent       db_column: pay_per_month_percent 
     */ 	
	private java.lang.String payPerMonthPercent;
	//columns END


	public WxModuleContentLoanScheme(){
	}

	public WxModuleContentLoanScheme(
		java.lang.String loanSchemeGuid
	){
		this.loanSchemeGuid = loanSchemeGuid;
	}

	

	public void setLoanSchemeGuid(java.lang.String value) {
		this.loanSchemeGuid = value;
	}
	
	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "loan_scheme_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getLoanSchemeGuid() {
		return this.loanSchemeGuid;
	}
	
	@Column(name = "loan_scheme_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getLoanSchemeName() {
		return this.loanSchemeName;
	}
	
	public void setLoanSchemeName(java.lang.String value) {
		this.loanSchemeName = value;
	}
	
	@Column(name = "first_pay_percent", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getFirstPayPercent() {
		return this.firstPayPercent;
	}
	
	public void setFirstPayPercent(java.lang.String value) {
		this.firstPayPercent = value;
	}
	
	@Column(name = "last_pay_percent", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getLastPayPercent() {
		return this.lastPayPercent;
	}
	
	public void setLastPayPercent(java.lang.String value) {
		this.lastPayPercent = value;
	}
	
	@Column(name = "month_num", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMonthNum() {
		return this.monthNum;
	}
	
	public void setMonthNum(java.lang.Integer value) {
		this.monthNum = value;
	}
	
	@Column(name = "pay_per_month_percent", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getPayPerMonthPercent() {
		return this.payPerMonthPercent;
	}
	
	public void setPayPerMonthPercent(java.lang.String value) {
		this.payPerMonthPercent = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("LoanSchemeGuid",getLoanSchemeGuid())
			.append("LoanSchemeName",getLoanSchemeName())
			.append("FirstPayPercent",getFirstPayPercent())
			.append("LastPayPercent",getLastPayPercent())
			.append("MonthNum",getMonthNum())
			.append("PayPerMonthPercent",getPayPerMonthPercent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLoanSchemeGuid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleContentLoanScheme == false) return false;
		if(this == obj) return true;
		WxModuleContentLoanScheme other = (WxModuleContentLoanScheme)obj;
		return new EqualsBuilder()
			.append(getLoanSchemeGuid(),other.getLoanSchemeGuid())
			.isEquals();
	}
}

