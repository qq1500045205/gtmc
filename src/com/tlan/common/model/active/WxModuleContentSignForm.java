package com.tlan.common.model.active;

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
@Table(name = "wx_module_content_sign_form")
public class WxModuleContentSignForm implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	/**
	 * formGuid db_column: form_guid
	 */
	@Length(max = 36)
	private java.lang.String formGuid;
	/**
	 * actGuid db_column: act_guid
	 */
	@Length(max = 36)
	private java.lang.String actGuid;
	/**
	 * 表单设置数据 db_column: form_json
	 */
	@Length(max = 65535)
	private java.lang.String formJson;
	/**
	 * gzhHash db_column: gzh_hash
	 */
	@Length(max = 36)
	private java.lang.String gzhHash;

	// columns END

	public WxModuleContentSignForm() {
	}

	public WxModuleContentSignForm(java.lang.String formGuid) {
		this.formGuid = formGuid;
	}

	public void setFormGuid(java.lang.String value) {
		this.formGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "form_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getFormGuid() {
		return this.formGuid;
	}

	@Column(name = "act_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getActGuid() {
		return actGuid;
	}

	public void setActGuid(java.lang.String actGuid) {
		this.actGuid = actGuid;
	}

	@Column(name = "form_json", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getFormJson() {
		return this.formJson;
	}

	public void setFormJson(java.lang.String value) {
		this.formJson = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("FormGuid", getFormGuid())
				.append("FormJson", getFormJson())
				.append("GzhHash", getGzhHash()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getFormGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxModuleContentSignForm == false)
			return false;
		if (this == obj)
			return true;
		WxModuleContentSignForm other = (WxModuleContentSignForm) obj;
		return new EqualsBuilder().append(getFormGuid(), other.getFormGuid())
				.isEquals();
	}
}
