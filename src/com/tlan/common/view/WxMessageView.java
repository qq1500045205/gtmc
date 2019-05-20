package com.tlan.common.view;

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
@Table(name = "wx_message_view")
public class WxMessageView implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String ADMIN_RULE = "/admin/rule/";

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 创建的规则对应的微信公众标识 db_column: rule_gzh_hash
	 */
	private java.lang.String ruleGzhHash;
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * keyword db_column: keyword
	 */
	private java.lang.String keyword;
	/**
	 * 规则名称 db_column: rule_name
	 */
	private java.lang.String ruleName;
	/**
	 * 模块标识 db_column: module_name
	 */
	private java.lang.String moduleName;
	/**
	 * 模块名称 db_column: module_title
	 */
	private java.lang.String moduleTitle;
	/**
	 * 是否默认 0 否 1 时 db_column: is_default
	 */

	private java.lang.Integer isDefault;
	/**
	 * 状态 db_column: rule_status
	 */

	private java.lang.Integer ruleStatus;
	/**
	 * infoMguid db_column: info_m_guid
	 */
	private java.lang.String infoMguid;
	/**
	 * 主键guid db_column: news_guid
	 */
	private java.lang.String newsGuid;
	/**
	 * 图文别名,多图文时只有父图文有值 db_column: news_name
	 */
	private java.lang.String newsName;
	/**
	 * 图文标题 db_column: news_title
	 */
	private java.lang.String newsTitle;
	/**
	 * 作者 db_column: news_author
	 */
	private java.lang.String newsAuthor;
	/**
	 * 图文描述 db_column: news_description
	 */
	private java.lang.String newsDescription;
	/**
	 * 封面 db_column: news_pic
	 */
	private java.lang.String newsPic;
	/**
	 * 图文内容 db_column: news_content
	 */
	private java.lang.String newsContent;
	/**
	 * 图文连接 db_column: news_url
	 */
	private java.lang.String newsUrl;
	/**
	 * 父图文guid db_column: parent_guid
	 */
	private java.lang.String parentGuid;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * createdBy db_column: created_by
	 */
	private java.lang.String createdBy;
	/**
	 * modifyOn db_column: modify_on
	 */
	private java.lang.String modifyOn;
	/**
	 * modifyBy db_column: modify_by
	 */
	private java.lang.String modifyBy;
	/**
	 * publishOn db_column: publish_on
	 */
	private java.lang.String publishOn;
	/**
	 * publishBy db_column: publish_by
	 */
	private java.lang.String publishBy;
	/**
	 * 状态 db_column: status
	 */
	private java.lang.String status;
	/**
	 * gzhHash db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * 消息类型 多图文消息 单图文消息 等 db_column: type_name
	 */
	private java.lang.String typeName;
	/**
	 * 多图文 news 单图文 single 文本 text db_column: type
	 */
	private java.lang.String type;

	// columns END

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "rule_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	@Column(name = "rule_gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGzhHash() {
		return this.ruleGzhHash;
	}

	public void setRuleGzhHash(java.lang.String value) {
		this.ruleGzhHash = value;
	}

	@Column(name = "keyword", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(java.lang.String value) {
		this.keyword = value;
	}

	@Column(name = "rule_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(java.lang.String value) {
		this.ruleName = value;
	}

	@Column(name = "module_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}

	@Column(name = "module_title", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getModuleTitle() {
		return this.moduleTitle;
	}

	public void setModuleTitle(java.lang.String value) {
		this.moduleTitle = value;
	}

	@Column(name = "is_default", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(java.lang.Integer value) {
		this.isDefault = value;
	}

	@Column(name = "rule_status", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getRuleStatus() {
		return this.ruleStatus;
	}

	public void setRuleStatus(java.lang.Integer value) {
		this.ruleStatus = value;
	}

	@Column(name = "info_m_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getInfoMguid() {
		return this.infoMguid;
	}

	public void setInfoMguid(java.lang.String value) {
		this.infoMguid = value;
	}

	@Column(name = "news_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getNewsGuid() {
		return this.newsGuid;
	}

	public void setNewsGuid(java.lang.String value) {
		this.newsGuid = value;
	}

	@Column(name = "news_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsName() {
		return this.newsName;
	}

	public void setNewsName(java.lang.String value) {
		this.newsName = value;
	}

	@Column(name = "news_title", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(java.lang.String value) {
		this.newsTitle = value;
	}

	@Column(name = "news_author", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getNewsAuthor() {
		return this.newsAuthor;
	}

	public void setNewsAuthor(java.lang.String value) {
		this.newsAuthor = value;
	}

	@Column(name = "news_description", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsDescription() {
		return this.newsDescription;
	}

	public void setNewsDescription(java.lang.String value) {
		this.newsDescription = value;
	}

	@Column(name = "news_pic", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public java.lang.String getNewsPic() {
		return this.newsPic;
	}

	public void setNewsPic(java.lang.String value) {
		this.newsPic = value;
	}

	@Column(name = "news_content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public java.lang.String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(java.lang.String value) {
		this.newsContent = value;
	}

	@Column(name = "news_url", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getNewsUrl() {
		return this.newsUrl;
	}

	public void setNewsUrl(java.lang.String value) {
		this.newsUrl = value;
	}

	@Column(name = "parent_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}

	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}

	@Column(name = "created_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(java.lang.String value) {
		this.createdOn = value;
	}

	@Column(name = "created_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		this.createdBy = value;
	}

	@Column(name = "modify_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getModifyOn() {
		return this.modifyOn;
	}

	public void setModifyOn(java.lang.String value) {
		this.modifyOn = value;
	}

	@Column(name = "modify_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(java.lang.String value) {
		this.modifyBy = value;
	}

	@Column(name = "publish_on", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public java.lang.String getPublishOn() {
		return this.publishOn;
	}

	public void setPublishOn(java.lang.String value) {
		this.publishOn = value;
	}

	@Column(name = "publish_by", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getPublishBy() {
		return this.publishBy;
	}

	public void setPublishBy(java.lang.String value) {
		this.publishBy = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "type_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(java.lang.String value) {
		this.typeName = value;
	}

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String value) {
		this.type = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RuleGzhHash", getRuleGzhHash())
				.append("RuleGuid", getRuleGuid())
				.append("Keyword", getKeyword())
				.append("RuleName", getRuleName())
				.append("ModuleName", getModuleName())
				.append("ModuleTitle", getModuleTitle())
				.append("IsDefault", getIsDefault())
				.append("RuleStatus", getRuleStatus())
				.append("InfoMguid", getInfoMguid())
				.append("NewsGuid", getNewsGuid())
				.append("NewsName", getNewsName())
				.append("NewsTitle", getNewsTitle())
				.append("NewsAuthor", getNewsAuthor())
				.append("NewsDescription", getNewsDescription())
				.append("NewsPic", getNewsPic())
				.append("NewsContent", getNewsContent())
				.append("NewsUrl", getNewsUrl())
				.append("ParentGuid", getParentGuid())
				.append("CreatedOn", getCreatedOn())
				.append("CreatedBy", getCreatedBy())
				.append("ModifyOn", getModifyOn())
				.append("ModifyBy", getModifyBy())
				.append("PublishOn", getPublishOn())
				.append("PublishBy", getPublishBy())
				.append("Status", getStatus()).append("GzhHash", getGzhHash())
				.append("TypeName", getTypeName()).append("Type", getType())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxMessageView == false)
			return false;
		if (this == obj)
			return true;
		WxMessageView other = (WxMessageView) obj;
		return new EqualsBuilder().isEquals();
	}
}
