package com.tlan.common.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "wx_log")
public class WxLog implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxLog";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_WXUSER = "微信用户id";
	public static final String ALIAS_KEYVALUE = "日志关键词";
	public static final String ALIAS_TYPE = "类型  如关注、文本回复、单机事件等";
	public static final String ALIAS_VISITTIME = "访问时间";
	public static final String ALIAS_VISITLOCAL = "访问位置  记录坐标  如 12.99:30.11";
	public static final String ALIAS_MODEL = "模块标识";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * id db_column: id
	 */

	private java.lang.Integer id;
	/**
	 * 微信用户id db_column: wxuser
	 */
	private java.lang.String wxuser;
	/**
	 * 日志关键词 db_column: keyvalue
	 */
	private java.lang.String keyvalue;
	/**
	 * 类型 如关注、文本回复、单机事件等 db_column: type
	 */
	private java.lang.String type;
	/**
	 * 访问时间 db_column: visittime
	 */

	private String visittime;
	/**
	 * 访问位置 记录坐标 如 12.99:30.11 db_column: visitlocal
	 */
	private java.lang.String visitlocal;
	/**
	 * 模块标识 db_column: model
	 */
	private java.lang.String model;

	// columns END

	public WxLog() {
	}

	public WxLog(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getId() {
		return this.id;
	}

	@Column(name = "wxuser", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getWxuser() {
		return this.wxuser;
	}

	public void setWxuser(java.lang.String value) {
		this.wxuser = value;
	}

	@Column(name = "keyvalue", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getKeyvalue() {
		return this.keyvalue;
	}

	public void setKeyvalue(java.lang.String value) {
		this.keyvalue = value;
	}

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String value) {
		this.type = value;
	}

	@Column(name = "visittime", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public String getVisittime() {
		return this.visittime;
	}

	public void setVisittime(String value) {
		this.visittime = value;
	}

	@Column(name = "visitlocal", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getVisitlocal() {
		return this.visitlocal;
	}

	public void setVisitlocal(java.lang.String value) {
		this.visitlocal = value;
	}

	@Column(name = "model", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModel() {
		return this.model;
	}

	public void setModel(java.lang.String value) {
		this.model = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Wxuser", getWxuser())
				.append("Keyvalue", getKeyvalue()).append("Type", getType())
				.append("Visittime", getVisittime())
				.append("Visitlocal", getVisitlocal())
				.append("Model", getModel()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxLog == false)
			return false;
		if (this == obj)
			return true;
		WxLog other = (WxLog) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
