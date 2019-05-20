package com.tlan.admin.sys;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

/**
 * web项目管理
 * 
 * @author StevenWung
 * 
 */
public class WebProjectAction extends BaseAction implements Preparable,
		ModelDriven<WxProject> {

	@Resource(name = "baseService")
	private IBaseService<WxProject> webProjectService;
	private WxProject webProject;

	private String[] items;

	public String add() {

		webProject.setCreatedOn(DateUtils.currentDatetime());
		webProject.setProjectGuid(DataUtils.getUUID());
		webProject.setStatus(1);
		webProjectService.save(webProject);
		setMap(true, "添加成功", null);

		return this.SUCCESS;
	}

	public String del() {
		if (null != items) {
			for (int i = 0; i < items.length; i++) {
				// 删除关键字
				webProjectService.delete(WxProject.class, "project_guid",
						items[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}

		return this.SUCCESS;
	}

	@Override
	public WxProject getModel() {
		// TODO Auto-generated method stub
		return this.webProject;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == webProject) {
			webProject = new WxProject();
		}
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	

}
