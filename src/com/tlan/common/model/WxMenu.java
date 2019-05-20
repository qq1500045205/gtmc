package com.tlan.common.model;

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
@Table(name = "wx_menu")
public class WxMenu implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	public final static int ENABLE = 1;
	public final static int HAVECHILD = 1;
	public final static int NOHAVECHILD = 0;
	
	public final static String LEFT = "左";
	public final static String MIDDLE = "中";
	public final static String RIGHT = "右";
	
	public final static String TOP = "顶";
	public final static String UP = "上";
	public final static String VMIDDLE = "中";
	public final static String DOWN = "下";
	public final static String BOTTOM = "底";

	// date formats

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * 微信菜单guid db_column: menu_guid
	 */
	private java.lang.String menuGuid;
	/**
	 * 菜单名称 db_column: menu_name
	 */
	private java.lang.String menuName;
	/**
	 * 菜单类型为CLICK时，使用菜单key db_column: menu_key
	 */
	private java.lang.String menuKey;
	/**
	 * 菜单类型为VIEW时，使用跳转地址 db_column: menu_url
	 */
	private java.lang.String menuUrl;
	/**
	 * 菜单类型 CLICK VIEW db_column: menu_type
	 */
	private java.lang.String menuType;
	/**
	 * 父菜单guid db_column: parent_guid
	 */
	private java.lang.String parentGuid;
	/**
	 * 是否可用 0(不可用)1（可用） 默认可用 db_column: enable
	 */

	private java.lang.Integer enable;
	/**
	 * 菜单顺序 db_column: menu_order
	 */

	private java.lang.Integer menuOrder;
	/**
	 * 微信公众号标识 db_column: gzh_hash
	 */
	private java.lang.String gzhHash;
	/**
	 * 模块guid db_column: module_guid
	 */
	private java.lang.String moduleContentGuid;
	/**
	 * 是否有孩子 db_column: is_have_child
	 */

	private java.lang.Integer isHaveChild;
	/**
	 * 规则guid db_column: rule_guid
	 */
	private java.lang.String ruleGuid;
	/**
	 * 动作模块类型 db_column: menu_path
	 */
	private java.lang.String menuPath;
	
	private java.lang.String moduleName;

	// columns END

	public WxMenu() {
	}

	public WxMenu(java.lang.String menuGuid) {
		this.menuGuid = menuGuid;
	}

	public void setMenuGuid(java.lang.String value) {
		this.menuGuid = value;
	}

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "menu_guid", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public java.lang.String getMenuGuid() {
		return this.menuGuid;
	}

	@Column(name = "menu_name", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public java.lang.String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}

	@Column(name = "menu_key", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(java.lang.String value) {
		this.menuKey = value;
	}

	@Column(name = "menu_url", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public java.lang.String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(java.lang.String value) {
		this.menuUrl = value;
	}

	@Column(name = "menu_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(java.lang.String value) {
		this.menuType = value;
	}

	@Column(name = "parent_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getParentGuid() {
		return this.parentGuid;
	}

	public void setParentGuid(java.lang.String value) {
		this.parentGuid = value;
	}

	@Column(name = "enable", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getEnable() {
		return this.enable;
	}

	public void setEnable(java.lang.Integer value) {
		this.enable = value;
	}

	@Column(name = "menu_order", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(java.lang.Integer value) {
		this.menuOrder = value;
	}

	@Column(name = "gzh_hash", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getGzhHash() {
		return this.gzhHash;
	}

	public void setGzhHash(java.lang.String value) {
		this.gzhHash = value;
	}

	@Column(name = "module_content_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getModuleContentGuid() {
		return moduleContentGuid;
	}

	public void setModuleContentGuid(java.lang.String moduleContentGuid) {
		this.moduleContentGuid = moduleContentGuid;
	}

	@Column(name = "is_have_child", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getIsHaveChild() {
		return this.isHaveChild;
	}

	public void setIsHaveChild(java.lang.Integer value) {
		this.isHaveChild = value;
	}

	@Column(name = "rule_guid", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getRuleGuid() {
		return this.ruleGuid;
	}

	public void setRuleGuid(java.lang.String value) {
		this.ruleGuid = value;
	}

	@Column(name = "menu_path", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getMenuPath() {
		return this.menuPath;
	}

	public void setMenuPath(java.lang.String value) {
		this.menuPath = value;
	}
	
	@Column(name = "module_name", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public java.lang.String getModuleName() {
		return moduleName;
	}

	public void setModuleName(java.lang.String moduleName) {
		this.moduleName = moduleName;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("MenuGuid", getMenuGuid())
				.append("MenuName", getMenuName())
				.append("MenuKey", getMenuKey())
				.append("MenuUrl", getMenuUrl())
				.append("MenuType", getMenuType())
				.append("ParentGuid", getParentGuid())
				.append("Enable", getEnable())
				.append("MenuOrder", getMenuOrder())
				.append("GzhHash", getGzhHash())
				.append("ModuleContentGuid", getModuleContentGuid())
				.append("IsHaveChild", getIsHaveChild())
				.append("RuleGuid", getRuleGuid())
				.append("MenuPath", getMenuPath()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getMenuGuid()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof WxMenu == false)
			return false;
		if (this == obj)
			return true;
		WxMenu other = (WxMenu) obj;
		return new EqualsBuilder().append(getMenuGuid(), other.getMenuGuid())
				.isEquals();
	}
}
