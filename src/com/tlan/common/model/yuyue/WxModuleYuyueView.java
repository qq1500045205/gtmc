/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.tlan.common.model.yuyue;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.text.SimpleDateFormat;
import java.util.*;

import com.tlan.common.model.yuyue.WxModuleYuyueViewKey;
import com.tlan.common.utils.DateUtils;
/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Entity
@IdClass(WxModuleYuyueViewKey.class)
@Table(name = "wx_module_yuyue_view")
public class WxModuleYuyueView implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "WxModuleYuyueView";
	public static final String ALIAS_YUYUE_GUID = "yuyueGuid";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_OPENID = "openid";
	public static final String ALIAS_GZH_HASH = "gzhHash";
	public static final String ALIAS_DEALER_GUID = "dealerGuid";
	public static final String ALIAS_DEALER_CODE = "与project中dealer_code关联";
	public static final String ALIAS_YUYUE_FIELD1 = "yuyueField1";
	public static final String ALIAS_YUYUE_FIELD2 = "yuyueField2";
	public static final String ALIAS_YUYUE_MEMO = "yuyueMemo";
	public static final String ALIAS_YUYUE_TIME = "yuyueTime";
	public static final String ALIAS_YUYUE_TYPE = "1: 试驾 2: 保养 3: 二手车 4: 年审";
	public static final String ALIAS_ADDRESS = "详细地址";
	public static final String ALIAS_USER_NAME = "userName";
	public static final String ALIAS_USER_TEL = "userTel";
	public static final String ALIAS_USER_SEX = "userSex";
	public static final String ALIAS_PROJECT_NAME = "project_name";
	 

	//可以直接使用: //@Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * yuyueGuid       db_column: yuyue_guid 
     */ 	
	//@NotBlank //@Length(max=64)
	private java.lang.String yuyueGuid;
    /**
     * createdOn       db_column: created_on 
     */ 	
	
	private java.util.Date createdOn;
    /**
     * openid       db_column: openid 
     */ 	
	//@Length(max=64)
	private java.lang.String openid;
    /**
     * gzhHash       db_column: gzh_hash 
     */ 	
	//@Length(max=64)
	private java.lang.String gzhHash;
    /**
     * dealerGuid       db_column: dealer_guid 
     */ 	
	//@Length(max=64)
	private java.lang.String dealerGuid;
    /**
     * 与project中dealer_code关联       db_column: dealer_code 
     */ 	
	//@Length(max=64)
	private java.lang.String dealerCode;
	private java.lang.String projectName;
    /**
     * yuyueField1       db_column: yuyue_field_1 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField1;
    /**
     * yuyueField2       db_column: yuyue_field_2 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueField2;
    /**
     * yuyueMemo       db_column: yuyue_memo 
     */ 	
	//@Length(max=200)
	private java.lang.String yuyueMemo;
    /**
     * yuyueTime       db_column: yuyue_time 
     */ 	
	//@Length(max=64)
	private java.lang.String yuyueTime;
    /**
     * 1: 试驾 2: 保养 3: 二手车 4: 年审       db_column: yuyue_type 
     */ 	
	//@Length(max=10)
	private java.lang.String yuyueType;
    /**
     * 详细地址       db_column: address 
     */ 	
	//@Length(max=255)
	private java.lang.String address;
    /**
     * userName       db_column: user_name 
     */ 	
	//@Length(max=100)
	private java.lang.String userName;
    /**
     * userTel       db_column: user_tel 
     */ 	
	//@Length(max=25)
	private java.lang.String userTel;
    /**
     * userSex       db_column: user_sex 
     */ 	
	//@Length(max=20)
	private java.lang.String userSex;
	//columns END


	public WxModuleYuyueView(){
	}


	
	@Id
	@Column(name = "yuyue_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueGuid() {
		return this.yuyueGuid;
	}
	
	public void setYuyueGuid(java.lang.String value) {
		this.yuyueGuid = value;
	}
	
	@Transient
	public String getCreatedOnString() {
		//return DateUtil.format(getCreatedOn(), FORMAT_CREATED_ON);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(getCreatedOn());
		//return DateUtil.formatDate(getCreatedOn());
	}
	public void setCreatedOnString(String value) {
		//setCreatedOn(DateConvertUtils.parse(value, FORMAT_CREATED_ON,java.util.Date.class));
		try {
			setCreatedOn(DateUtil.parseDate(value));
		} catch (DateParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(java.util.Date value) {
		this.createdOn = value;
	}
	
	@Column(name = "openid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	
	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}
	
	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}
	
	@Column(name = "dealer_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getDealerGuid() {
		return this.dealerGuid;
	}
	
	public void setDealerGuid(java.lang.String value) {
		this.dealerGuid = value;
	}
	
	@Column(name = "dealer_code", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getDealerCode() {
		if(this.dealerCode==null){
			return "";
		}
		return this.dealerCode;
	}
	
	public void setDealerCode(java.lang.String value) {
		if(value==null){
			dealerCode = "";
		}
		this.dealerCode = value;
	}
	
	@Column(name = "project_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getProjectName() {
		if(this.projectName==null){
			return "";
		}
		return this.projectName;
	}
	
	public void setProjectName(java.lang.String value) {
		if(value==null){
			projectName = "";
		}
		this.projectName = value;
	}
	
	@Column(name = "yuyue_field_1", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField1() {
		return this.yuyueField1;
	}
	
	public void setYuyueField1(java.lang.String value) {
		this.yuyueField1 = value;
	}
	
	@Column(name = "yuyue_field_2", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueField2() {
		return this.yuyueField2;
	}
	
	public void setYuyueField2(java.lang.String value) {
		this.yuyueField2 = value;
	}
	
	@Column(name = "yuyue_memo", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getYuyueMemo() {
		return this.yuyueMemo;
	}
	
	public void setYuyueMemo(java.lang.String value) {
		this.yuyueMemo = value;
	}
	
	@Column(name = "yuyue_time", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public java.lang.String getYuyueTime() {
		return this.yuyueTime;
	}
	
	public void setYuyueTime(java.lang.String value) {
		this.yuyueTime = value;
	}
	
	@Column(name = "yuyue_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getYuyueType() {
		return this.yuyueType;
	}
	
	public void setYuyueType(java.lang.String value) {
		this.yuyueType = value;
	}
	
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getAddress() {
		if(this.address==null){
			return "";
		}
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	@Column(name = "user_tel", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getUserTel() {
		return this.userTel;
	}
	
	public void setUserTel(java.lang.String value) {
		this.userTel = value;
	}
	
	@Column(name = "user_sex", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getUserSex() {
		return this.userSex;
	}
	
	public void setUserSex(java.lang.String value) {
		this.userSex = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("YuyueGuid",getYuyueGuid())
			.append("CreatedOn",getCreatedOn())
			.append("Openid",getOpenid())
			.append("GzhHash",getGzhHash())
			.append("DealerGuid",getDealerGuid())
			.append("DealerCode",getDealerCode())
			.append("YuyueField1",getYuyueField1())
			.append("YuyueField2",getYuyueField2())
			.append("YuyueMemo",getYuyueMemo())
			.append("YuyueTime",getYuyueTime())
			.append("YuyueType",getYuyueType())
			.append("Address",getAddress())
			.append("UserName",getUserName())
			.append("UserTel",getUserTel())
			.append("UserSex",getUserSex())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxModuleYuyueView == false) return false;
		if(this == obj) return true;
		WxModuleYuyueView other = (WxModuleYuyueView)obj;
		return new EqualsBuilder()
			.isEquals();
	}
}

