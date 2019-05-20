package com.tlan.admin.page;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxModulePage;
import com.tlan.common.service.IBaseService;

public class PageAction extends BaseAction implements Preparable,
		ModelDriven<WxModulePage> {

	private WxModulePage modulePage;

	@Resource(name = "baseService")
	private IBaseService<WxModulePage> modulePageService;

	private String[] item;
	
	/**
	 * 添加组件页
	 * 
	 * @return
	 */
	public String addPage() {
		try {
			modulePageService.save(modulePage);
			setMap(true, "添加成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "添加失败", e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 删除组件页
	 * 
	 * @return
	 */
	public String delPage() {
		try {
			for (String it : item) {
				modulePageService.delete(new WxModulePage(it));
			}
			setMap(true, "删除成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "删除失败", e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public WxModulePage getModel() {
		// TODO Auto-generated method stub
		return modulePage;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == modulePage) {
			modulePage = new WxModulePage();
		}
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

}
