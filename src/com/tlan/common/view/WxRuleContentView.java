package com.tlan.common.view;

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
@Table(name = "wx_rule_content_view")
public class WxRuleContentView implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "WxRuleContentView";
	public static final String ALIAS_RULE_GUID = "规则guid";
	public static final String ALIAS_NEWS_GUID = "主键guid";
	public static final String ALIAS_NEWS_NAME = "图文别名,多图文时只有父图文有值";
	public static final String ALIAS_NEWS_TITLE = "图文标题";
	public static final String ALIAS_NEWS_AUTHOR = "作者";
	public static final String ALIAS_NEWS_DESCRIPTION = "图文描述";
	public static final String ALIAS_NEWS_PIC = "封面";
	public static final String ALIAS_NEWS_CONTENT = "图文内容";
	public static final String ALIAS_NEWS_URL = "图文连接";
	public static final String ALIAS_NEWS_URL_PARAM_NAME = "url参数名";
	public static final String ALIAS_NEWS_URL_PARAM_CONTENT = "参数内容";
	public static final String ALIAS_NEWS_OTHRT_PARAM = "其他参数";
	public static final String ALIAS_PARENT_GUID = "父图文guid";
	public static final String ALIAS_CREATED_ON = "createdOn";
	public static final String ALIAS_TYPE = "多图文 news  单图文 single   文本 text";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_DYNAMIC_CONTENT = "动态内容";
	public static final String ALIAS_DYNAMIC_CONTENT_INSTANCE_GUID = "动态内容实例guid";
	//状态值
	public static final String SINGLE_NEWS = "single";
	public static final String NEWS = "news";
	public static final String TEXT = "text";
	
	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
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
     * 图文连接       db_column: url_param_name 
     */ 	
	private java.lang.String urlParamName;
	/**
     * 图文连接       db_column: url_param_content 
     */ 	
	private java.lang.String urlParamContent;
	/**
     * 图文连接       db_column: other_url_param 
     */ 	
	private java.lang.String otherUrlParam;
	/**
	 * 父图文guid db_column: parent_guid
	 */
	private java.lang.String parentGuid;
	/**
	 * createdOn db_column: created_on
	 */
	private java.lang.String createdOn;
	/**
	 * 多图文 news 单图文 single 文本 text db_column: type
	 */
	private java.lang.String type;
	/**
	 * 状态 db_column: status
	 */
	private java.lang.String status;
	/**
	 * 状态 db_column: dynamic_content
	 */
	private java.lang.String dynamicContent;
	/**
	 * 状态 db_column: dynamic_content_instance_guid
	 */
	private java.lang.String dynamicContentInstanceGuid;

	// columns END

	public WxRuleContentView() {

	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "news_guid", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getNewsGuid() {
		return this.newsGuid;
	}

	public void setNewsGuid(java.lang.String value) {
		this.newsGuid = value;
	}

	@Column(name = "rule_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
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

	@Column(name = "url_param_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUrlParamName() {
		return this.urlParamName;
	}
	
	public void setUrlParamName(java.lang.String value) {
		this.urlParamName = value;
	}

	@Column(name = "url_param_content", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public java.lang.String getUrlParamContent() {
		return this.urlParamContent;
	}
	
	public void setUrlParamContent(java.lang.String value) {
		this.urlParamContent = value;
	}

	@Column(name = "other_url_param", unique = false, nullable = true, insertable = true, updatable = true, length = 65525)
	public java.lang.String getOtherUrlParam() {
		return this.otherUrlParam;
	}
	
	public void setOtherUrlParam(java.lang.String value) {
		this.otherUrlParam = value;
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

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getType() {
		return this.type;
	}

	public void setType(java.lang.String value) {
		this.type = value;
	}

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	@Column(name = "dynamic_content", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(java.lang.String value) {
		this.dynamicContent = value;
	}
	
	@Column(name = "dynamic_content_instance_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getDynamicContentInstanceGuid() {
		return this.dynamicContentInstanceGuid;
	}

	public void setDynamicContentInstanceGuid(java.lang.String value) {
		this.dynamicContentInstanceGuid = value;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("RuleGuid", getRuleGuid())
				.append("NewsGuid", getNewsGuid())
				.append("NewsName", getNewsName())
				.append("NewsTitle", getNewsTitle())
				.append("NewsAuthor", getNewsAuthor())
				.append("NewsDescription", getNewsDescription())
				.append("NewsPic", getNewsPic())
				.append("NewsContent", getNewsContent())
				.append("NewsUrl", getNewsUrl())
				.append("ParentGuid", getParentGuid())
				.append("CreatedOn", getCreatedOn()).append("Type", getType())
				.append("Status", getStatus())
				.append("DynamicContent", getDynamicContent())
				.append("DynamicContentInstanceGuid", getDynamicContentInstanceGuid())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxRuleContentView == false)
			return false;
		if (this == obj)
			return true;
		WxRuleContentView other = (WxRuleContentView) obj;
		return new EqualsBuilder().isEquals();
	}
}
