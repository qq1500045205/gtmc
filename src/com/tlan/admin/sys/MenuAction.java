package com.tlan.admin.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webmenu;
import com.tlan.common.service.IBaseService;

public class MenuAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODOlong
	 */
	private static final long serialVersionUID = 5066058399594726532L;
	@Resource(name = "baseService")
	private IBaseService<Webmenu> baseService;
	// 接受json数据 更新普通属性
	private String datas;

	/**
	 * 生成菜单js文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String create() throws Exception {
		// TODO Auto-generated method stub

		List<Webmenu> menus = this.baseService.getAll(Webmenu.class);
		JSONArray jsonMenus = new JSONArray();
		boolean isopen = true;
		for (int i = 0; i < menus.size(); i++) {
			Webmenu item = menus.get(i);
			JSONObject obj = new JSONObject();
			obj.put("id", item.getId());
			obj.put("text", item.getName());
			obj.put("pid", item.getParent());
			obj.put("url", item.getUrl());

			menus.remove(item);
			iterateJosn(menus, item.getId(), obj);
			
			if(isopen && null != obj.get("children")){
				isopen = false;
				obj.put("expanded", true);
			}
			
			jsonMenus.add(obj);
			i--;
		}

		jsonObject = jsonMenus;

		return this.SUCCESS;
	}

	private void iterateJosn(List<Webmenu> menus, long id, JSONObject obj) {
		System.out.println("id:"+id+",obj:"+obj);
		List<Webmenu> tmps = getChildNode(menus, id);
		JSONArray childs = new JSONArray();
		if (tmps.size() > 0) {
			for (Webmenu menu : tmps) {
				JSONObject child = new JSONObject();
				child.put("id", menu.getId());
				child.put("text", menu.getName());
				child.put("pid", menu.getParent());
				child.put("url", menu.getUrl());
				menus.remove(menu);
				iterateJosn(menus, menu.getId(), child);
				childs.add(child);
			}
			obj.put("children", childs);
		}
	}

	private List<Webmenu> getChildNode(List<Webmenu> menus, long id) {
		List<Webmenu> childNodes = new ArrayList<Webmenu>();
		for (Webmenu m : menus) {
			if (m.getParent() != null && m.getParent() == id) {
				childNodes.add(m);
			}
		}
		return childNodes;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

}
