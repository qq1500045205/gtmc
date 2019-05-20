package com.tlan.admin.sys;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxModulePage;
import com.tlan.common.service.IBaseService;

/**
 * 统计分析
 * 
 * @PackageName:com.tlan.admin.sys
 * @ClassName:StatisticAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年3月2日 下午8:14:54
 * 
 */
public class StatisticAction extends BaseAction {

	@Resource(name = "baseService")
	private IBaseService<WxModulePage> pageService;

	/**
	 * 获取模块编码
	 * 
	 * @Title: getModuleCode
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月2日 下午8:17:41
	 */
	public String getCodeList() {
		List<WxModulePage> pages = this.pageService.getAll(WxModulePage.class);
		JSONArray ary = new JSONArray();
		for (WxModulePage page : pages) {
			JSONObject obj = new JSONObject();
			obj.put("value", page.getPageCode());
			obj.put("text", page.getPageName());
			ary.add(obj);
		}
		jsonObject = ary;
		return SUCCESS;
	}

}
