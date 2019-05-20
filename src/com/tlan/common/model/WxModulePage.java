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
@Table(name = "wx_module_page")
public class WxModulePage implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxModulePage";
	public static final String ALIAS_PAGE_GUID = "pageGuid";
	public static final String ALIAS_PAGE_NAME = "pageName";
	public static final String ALIAS_PAGE_CODE = "pageCode";
	public static final String ALIAS_PAGE_DISCRIPTION = "pageDiscription";
	public static final String ALIAS_CPT_GUID = "cptGuid";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * pageGuid db_column: page_guid
	 */
	private java.lang.String pageGuid;
	/**
	 * pageName db_column: page_name
	 */
	private java.lang.String pageName;
	/**
	 * pageCode db_column: page_code
	 */
	private java.lang.String pageCode;
	/**
	 * pageFtl db_column: page_ftl
	 */
	private java.lang.String pageFtl;
	/**
	 * pageDiscription db_column: page_discription
	 */
	private java.lang.String pageDiscription;

	// columns END

	public WxModulePage() {
	}

	public WxModulePage(java.lang.String pageGuid) {
		this.pageGuid = pageGuid;
	}

	public void setPageGuid(java.lang.String value) {
		this.pageGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "uuid")
	@Column(name = "page_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getPageGuid() {
		return this.pageGuid;
	}

	@Column(name = "page_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPageName() {
		return this.pageName;
	}

	public void setPageName(java.lang.String value) {
		this.pageName = value;
	}

	@Column(name = "page_code", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPageCode() {
		return this.pageCode;
	}

	public void setPageCode(java.lang.String value) {
		this.pageCode = value;
	}

	@Column(name = "page_discription", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getPageDiscription() {
		return this.pageDiscription;
	}

	public void setPageDiscription(java.lang.String value) {
		this.pageDiscription = value;
	}

	@Column(name = "page_ftl", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getPageFtl() {
		return pageFtl;
	}

	public void setPageFtl(java.lang.String pageFtl) {
		this.pageFtl = pageFtl;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("PageGuid", getPageGuid())
				.append("PageName", getPageName())
				.append("PageCode", getPageCode())
				.append("PageDiscription", getPageDiscription())
				.append("PageFtl", getPageFtl()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getPageGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModulePage == false)
			return false;
		if (this == obj)
			return true;
		WxModulePage other = (WxModulePage) obj;
		return new EqualsBuilder().append(getPageGuid(), other.getPageGuid())
				.isEquals();
	}
}
