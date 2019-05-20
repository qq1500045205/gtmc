package com.tlan.beans;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 自定义模块绑定模块对应的bean
 * 
 * @author magenm
 * 
 */
public class MenuBean {

	private boolean isParent;
	private boolean hasChildren;
	private String parentName;

	private List<MessageBean> message;
	private List<MessageBean> content;

	public MenuBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuBean(boolean isParent, boolean hasChildren, String parentName,
			List<MessageBean> message, List<MessageBean> content) {
		super();
		this.isParent = isParent;
		this.hasChildren = hasChildren;
		this.parentName = parentName;
		this.message = message;
		this.content = content;
	}

	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("isParent", isParent);
		obj.put("hasChildren", hasChildren);
		obj.put("parentName", parentName);

		if (null != message) {
			JSONArray ary = new JSONArray();
			for (MessageBean bean : message) {
				JSONObject item = JSONObject.fromObject(bean);
				ary.add(item);
			}
			obj.put("message", ary);
		}

		if (null != content) {
			JSONArray ary = new JSONArray();
			for (MessageBean bean : content) {
				JSONObject item = JSONObject.fromObject(bean);
				ary.add(item);
			}
			obj.put("content", ary);
		}
		return obj;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<MessageBean> getMessage() {
		return message;
	}

	public void setMessage(List<MessageBean> message) {
		this.message = message;
	}

	public List<MessageBean> getContent() {
		return content;
	}

	public void setContent(List<MessageBean> content) {
		this.content = content;
	}

}
