package com.tlan.admin.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.Var;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webmenu;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxUser;
import com.tlan.common.service.IBaseService;

public class WebMenuAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODOlong
	 */
	private static final long serialVersionUID = 5066058399594726532L;
	@Resource(name = "baseService")
	private IBaseService<Webmenu> baseService;
	@Resource(name = "baseService")
	private IBaseService<Webuser> webuserService;
	// 接受json数据 更新普通属性
	private String datas;
	// 接收userGuid
	private String userGuid;
	// 接收userGuid
	private String rights;

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
			obj.put("rights", item.getRights());

			menus.remove(item);
			iterateJosn(menus, item.getId(), obj);

			if (isopen && null != obj.get("children")) {
				isopen = false;
				obj.put("expanded", true);
			}

			jsonMenus.add(obj);
			i--;
		}

		jsonObject = jsonMenus;

		return this.SUCCESS;
	}

	public String getusermenu() throws Exception {
		// TODO Auto-generated method stub
		List<Webmenu> menuslist = this.baseService.getAll(Webmenu.class);
		List<Webmenu> menus = new ArrayList<Webmenu>();

		// 剔除不符合权限要求菜单Start
		LoginBean loginBean = this.obtainLoginBean();
		String rightsTest = loginBean.getRights().toString();
		char[] rightsChar = rightsTest.toCharArray();
		for (int i = 0; i < menuslist.size(); i++) {
			Webmenu item = menuslist.get(i);
			char[] menuchar = item.getRights().toCharArray();
			if (rightsChar.length >= menuchar.length) {
				char right = rightsChar[rightsChar.length - menuchar.length];
				if (right == '1') {
					menus.add(item);
				}
			}
		}
		// 剔除不符合权限要求菜单End

		JSONArray jsonMenus = new JSONArray();
		boolean isopen = true;
		for (int i = 0; i < menus.size(); i++) {
			Webmenu item = menus.get(i);
			JSONObject obj = new JSONObject();
			obj.put("id", item.getId());
			obj.put("text", item.getName());
			obj.put("pid", item.getParent());
			obj.put("url", item.getUrl());
			obj.put("rights", item.getRights());
			menus.remove(item);
			iterateJosn(menus, item.getId(), obj);

			if (isopen && null != obj.get("children")) {
				isopen = false;
				obj.put("expanded", true);
			}

			jsonMenus.add(obj);
			i--;
		}

		jsonObject = jsonMenus;

		return this.SUCCESS;
	}

	public String saveusermemu() throws Exception {
		LoginBean loginBean = this.obtainLoginBean();
		List<Webuser> userinfos = this.webuserService.getAll( 
				Webuser.class, 
				new String[]{"userGuid", "parentGuid"}, 
				new Object[]{userGuid, loginBean.getUserGuid()}, 
				new String[]{"=","="}
			);
		if(userinfos.size()>0){
			Webuser userinfo = userinfos.get(0);
			userinfo.setRights(rights);
			this.webuserService.update(userinfo);
	
			JSONArray jsonMenus = new JSONArray();
			jsonObject = jsonMenus;
			this.setMap(true, "success", null);
		}else{
			this.setMap(true, "failure", "权限不够");
		}
		return this.SUCCESS;
	}

	private void iterateJosn(List<Webmenu> menus, long id, JSONObject obj) {
		// System.out.println("id:"+id+",obj:"+obj);
		List<Webmenu> tmps = getChildNode(menus, id);
		JSONArray childs = new JSONArray();
		if (tmps.size() > 0) {
			for (Webmenu menu : tmps) {
				JSONObject child = new JSONObject();
				child.put("id", menu.getId());
				child.put("text", menu.getName());
				child.put("pid", menu.getParent());
				child.put("url", menu.getUrl());
				child.put("rights", menu.getRights());
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

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

}
