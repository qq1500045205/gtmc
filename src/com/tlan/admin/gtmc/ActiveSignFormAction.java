package com.tlan.admin.gtmc;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.active.WxModuleContentActive;
import com.tlan.common.model.active.WxModuleContentSign;
import com.tlan.common.model.active.WxModuleContentSignForm;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;

/**
 * 品牌活动报名
 * 
 * @author magenm
 * 
 */
public class ActiveSignFormAction extends BaseAction {

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentSignForm> signFormService;

	// 获取登录用户信息
	private LoginBean user = obtainLoginBean();

	private String[] item;
	private String data;
	private String actGuid;

	/**
	 * 保存表单设置
	 * 
	 * @return
	 */
	public String saveSignForm() {
		try {
			JSONObject json = JSONObject.fromObject(data);
			WxModuleContentSignForm form = new WxModuleContentSignForm();
			form.setFormJson(data);
			form.setGzhHash(user.getGzhHash());
			form.setFormGuid(actGuid); // 为保证活动唯一使用活动id
			form.setActGuid(actGuid);

			signFormService.saveOrUpdate(form);

			setMap(true, "保存成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "保存失败(检查是否重复添加)", null);
		}
		return SUCCESS;
	}
	
	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

}
