package com.tlan.admin.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.beans.MenuBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxMenu;
import com.tlan.common.model.WxModule;
import com.tlan.common.model.WxModuleContent;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.wxkit.bean.TypeBean;

/**
 * 自定义菜单操作类
 * 
 * @author magenm
 * 
 */
public class WxMenuAction extends BaseAction implements Preparable,
		ModelDriven<WxMenu> {

	@Resource(name = "baseService")
	private IBaseService<WxMenu> wxMenuService;
	@Resource(name = "baseService")
	private IBaseService<WxModule> wxModuleService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContent> wxModuleContentService;

	private WxMenu wxMenu;
	private String data;
	/**
	 * 获取登录信息
	 */
	public LoginBean loginBean = obtainLoginBean();


	/**
	 * 跳转至添加菜单页面
	 */
	public String forwardAdd() throws Exception {
		// TODO Auto-generated method stub
		String basepath = ServletActionContext.getServletContext()
				.getContextPath() + "/";

		String gzhHash = "";
		if (loginBean != null) {
			gzhHash = loginBean.getGzhHash();
		} else {
			return LOGIN;
		}

		MenuBean bean = new MenuBean();
		bean.setParent(true);
		bean.setHasChildren(false);
		bean.setParentName("");

		// 内容模块
		// List<WxModuleContent> wmcs = wxModuleContentService.findPage(
		// WxModuleContent.class, getStart(), getLimit(),
		// new String[] { "gzhHash" }, new Object[] { gzhHash },
		// "createdOn desc");
		// List<MessageBean> cntBeans = new ArrayList<>();
		// for (WxModuleContent wmc : wmcs) {
		// MessageBean cntBean = new MessageBean();
		// cntBean.setId(wmc.getContentGuid());
		// cntBean.setValue(wmc.getContentTitle());
		// cntBean.setSrc(basepath + ModuleAction.ADMIN_CNT_HTML
		// + wmc.getContentPage());
		// cntBean.setType(wmc.getNamePath());
		// cntBeans.add(cntBean);
		// }
		// bean.setContent(cntBeans);

		// 消息模块
		// List<WxMessageView> msgviews = wxMessageViewService.findPage(
		// WxMessageView.class, getStart(), getLimit(),
		// new String[] { "gzhHash" }, new Object[] { gzhHash },
		// "createdOn desc");
		// List<MessageBean> msgBeans = new ArrayList<>();
		// for (WxMessageView wmc : msgviews) {
		// MessageBean msgBean = new MessageBean();
		// msgBean.setId(wmc.getRuleGuid());
		// msgBean.setValue(wmc.getRuleName());
		// msgBean.setSrc(basepath + "/web/forwardShowMessage?type="
		// + wmc.getType() + "&newsGuid=" + wmc.getNewsGuid());
		// msgBean.setType("消息模块-" + wmc.getTypeName());
		// msgBeans.add(msgBean);
		// }
		// bean.setMessage(msgBeans);

		jsonObject = bean.toJSON();

		return this.SUCCESS;
	}

	/**
	 * 跳转至添加菜单页面
	 */
	public String forwardAddChild() throws Exception {
		// TODO Auto-generated method stub
		String basepath = ServletActionContext.getServletContext()
				.getContextPath() + "/";

		String gzhHash = "";
		if (loginBean != null) {
			gzhHash = loginBean.getGzhHash();
		} else {
			return LOGIN;
		}

		List<WxMenu> parentMenu = wxMenuService.getAll(WxMenu.class,
				"menuGuid", wxMenu.getParentGuid());

		MenuBean bean = new MenuBean();
		bean.setParent(false);
		bean.setHasChildren(false);
		bean.setParentName(parentMenu.get(0).getMenuName());

		// List<WxModuleContent> wmcs = wxModuleContentService.findPage(
		// WxModuleContent.class, getStart(), getLimit(),
		// new String[] { "gzhHash" }, new Object[] { gzhHash },
		// "createdOn desc");
		//
		// List<MessageBean> cntBeans = new ArrayList<>();
		// for (WxModuleContent wmc : wmcs) {
		// MessageBean cntBean = new MessageBean();
		// cntBean.setId(wmc.getContentGuid());
		// cntBean.setValue(wmc.getContentTitle());
		// cntBean.setSrc(basepath + ModuleAction.ADMIN_CNT_HTML
		// + wmc.getContentPage());
		// cntBean.setType(wmc.getNamePath());
		// cntBeans.add(cntBean);
		// }
		// bean.setContent(cntBeans);

		// 消息模块
		// List<WxMessageView> msgviews = wxMessageViewService.findPage(
		// WxMessageView.class, getStart(), getLimit(),
		// new String[] { "gzhHash" }, new Object[] { gzhHash },
		// "createdOn desc");
		// List<MessageBean> msgBeans = new ArrayList<>();
		// for (WxMessageView wmc : msgviews) {
		// MessageBean msgBean = new MessageBean();
		// msgBean.setId(wmc.getRuleGuid());
		// msgBean.setValue(wmc.getRuleName());
		// msgBean.setSrc(basepath + "/web/forwardShowMessage?type="
		// + wmc.getType() + "&newsGuid=" + wmc.getNewsGuid());
		// msgBean.setType("消息模块-" + wmc.getTypeName());
		// msgBeans.add(msgBean);
		// }
		// bean.setMessage(msgBeans);

		jsonObject = bean.toJSON();

		return this.SUCCESS;
	}

	/**
	 * 添加自定义菜单
	 */
	public String add() throws Exception {
		// TODO Auto-generated method stub

		String gzhHash = "";
		if (loginBean != null) {
			gzhHash = loginBean.getGzhHash();
		} else {
			return LOGIN;
		}

		List<WxMenu> list = wxMenuService.getAll(WxMenu.class, new String[] {
				"parentGuid", "gzhHash" }, new Object[] { "NULL", gzhHash },
				new String[] { "is", "=" });
		if (null != list && list.size() >= 3) {
			setMap(false, "一级菜单最多包括3个", null);
			return this.SUCCESS;
		}

		// 设置序列
		int max = 0;
		for (WxMenu item : list) {
			if (item.getMenuOrder() > max) {
				max = item.getMenuOrder();
			}
		}
		max = max + 10;

		wxMenu.setMenuOrder(max);
		wxMenu.setMenuGuid(DataUtils.getUUID());
		wxMenu.setEnable(WxMenu.ENABLE);
		wxMenu.setGzhHash(gzhHash);

		// wxMenu.setModuleName(wxm.getModuleName());

		// 没有子菜单，直接触发
		if (TypeBean.VIEW.equalsIgnoreCase(wxMenu.getMenuType())) { // 内容模块
			List<WxModuleContent> wmcs = wxModuleContentService.getAll(
					WxModuleContent.class, "contentGuid",
					wxMenu.getModuleContentGuid());
			WxModuleContent wmc = wmcs.get(0);
			wxMenu.setMenuUrl(wmc.getContentPage());

			wxMenu.setIsHaveChild(WxMenu.NOHAVECHILD);
		} else if (TypeBean.CLICK.equalsIgnoreCase(wxMenu.getMenuType())) { // 消息模块
			wxMenu.setMenuKey(wxMenu.getModuleContentGuid());// 使用消息id做菜单key值
			wxMenu.setIsHaveChild(WxMenu.NOHAVECHILD);
		} else {
			// 有子菜单
			wxMenu.setIsHaveChild(WxMenu.HAVECHILD);
		}

		wxMenuService.save(wxMenu);
		setMap(true, "添加成功", null);
		return this.SUCCESS;
	}

	/**
	 * 添加自定义子菜单菜单
	 */
	public String addChild() throws Exception {
		// TODO Auto-generated method stub
		String gzhHash = "";
		if (loginBean != null) {
			gzhHash = loginBean.getGzhHash();
		} else {
			return LOGIN;
		}

		List<WxMenu> list = wxMenuService.getAll(WxMenu.class, new String[] {
				"parentGuid", "gzhHash" },
				new Object[] { wxMenu.getParentGuid(), gzhHash });
		if (null != list && list.size() >= 5) {
			setMap(false, "二级菜单最多包括5个", null);
			return this.SUCCESS;
		}

		// 设置序列
		List<WxMenu> pm = wxMenuService.getAll(WxMenu.class, "menuGuid",
				wxMenu.getParentGuid());
		int max = pm.get(0).getMenuOrder();
		for (WxMenu item : list) {
			if (item.getMenuOrder() > max) {
				max = item.getMenuOrder();
			}
		}
		max = max + 1;

		wxMenu.setMenuOrder(max);

		wxMenu.setMenuGuid(DataUtils.getUUID());
		wxMenu.setEnable(WxMenu.ENABLE);
		wxMenu.setGzhHash(gzhHash);

		if (TypeBean.VIEW.equalsIgnoreCase(wxMenu.getMenuType())) { // 内容模块
			List<WxModuleContent> wmcs = wxModuleContentService.getAll(
					WxModuleContent.class, "contentGuid",
					wxMenu.getModuleContentGuid());

			WxModuleContent wmc = wmcs.get(0);
			wxMenu.setMenuUrl(wmc.getContentPage());

			wxMenu.setIsHaveChild(WxMenu.NOHAVECHILD);
		} else if (TypeBean.CLICK.equalsIgnoreCase(wxMenu.getMenuType())) { // 消息模块
			wxMenu.setMenuKey(wxMenu.getModuleContentGuid());
			wxMenu.setIsHaveChild(WxMenu.NOHAVECHILD);
		} else {

		}

		wxMenuService.save(wxMenu);

		setMap(true, "添加成功", null);

		// 检测一级菜单参数是否正确
		List<WxMenu> list2 = wxMenuService.getAll(WxMenu.class, "menuGuid",
				wxMenu.getParentGuid());
		WxMenu parent = list2.get(0);
		if (parent.getIsHaveChild() == WxMenu.NOHAVECHILD) {
			parent.setIsHaveChild(WxMenu.HAVECHILD);
			wxMenuService.update(parent);
		}
		return this.SUCCESS;
	}

	/**
	 * 删除菜单
	 * 
	 * @return
	 */
	public String delete() {

		WxMenu menu = wxMenuService.get(WxMenu.class, "menuGuid",
				wxMenu.getMenuGuid());

		// 如果是删除父菜单，则将子菜单一并删除
		if (null != menu && DataUtils.isNullOrEmpty(menu.getParentGuid())) {

			List<WxMenu> menus = wxMenuService.getAll(WxMenu.class,
					"parentGuid", wxMenu.getMenuGuid());
			for (WxMenu wxm : menus) {
				wxMenuService.delete(wxm);
			}

			// 删除菜单
			wxMenuService.delete(menu);

			// 删除需要重新编号
			List<WxMenu> menuList = wxMenuService.getAll(WxMenu.class,
					"gzhHash", loginBean.getGzhHash());

			int order = menu.getMenuOrder();
			switch (order) {
			case 10:
				for (WxMenu wxm : menuList) {
					wxm.setMenuOrder(wxm.getMenuOrder() - 10);
					wxMenuService.update(wxm);
				}
				break;
			case 20:
				for (WxMenu wxm : menuList) {
					if (wxm.getMenuOrder() > 20) {
						wxm.setMenuOrder(wxm.getMenuOrder() - 10);
						wxMenuService.update(wxm);
					}
				}
				break;
			}
		} else {
			// 删除菜单
			wxMenuService.delete(menu);

			// 删除需要重新编号
			int order = menu.getMenuOrder();
			List<WxMenu> menus = wxMenuService.getAll(WxMenu.class,
					"parentGuid", wxMenu.getParentGuid());
			switch (order % 10) {
			case 1:
				for (WxMenu wxm : menus) {
					wxm.setMenuOrder(wxm.getMenuOrder() - 1);
					wxMenuService.update(wxm);
				}
				break;
			case 2:
				for (WxMenu wxm : menus) {
					if (wxm.getMenuOrder() % 10 > 2) {
						wxm.setMenuOrder(wxm.getMenuOrder() - 1);
						wxMenuService.update(wxm);
					}
				}
				break;
			case 3:
				for (WxMenu wxm : menus) {
					if (wxm.getMenuOrder() % 10 > 3) {
						wxm.setMenuOrder(wxm.getMenuOrder() - 1);
						wxMenuService.update(wxm);
					}
				}
				break;
			case 4:
				for (WxMenu wxm : menus) {
					if (wxm.getMenuOrder() % 10 > 4) {
						wxm.setMenuOrder(wxm.getMenuOrder() - 1);
						wxMenuService.update(wxm);
					}
				}
				break;
			}
		}

		setMap(true, "删除成功", null);

		return this.SUCCESS;
	}

	/**
	 * 跳转至排序页面
	 * 
	 * @return
	 */
	public String forwardSortPage() {
		/**
		 * 构造数据<br/>
		 * 格式：<br/>
		 * { sort: guid: name: child:[ { sort: guid: name: } ] }
		 * 
		 */
		List<WxMenu> menus = this.wxMenuService.getAll(WxMenu.class, new String[]{"gzhHash"},
				new Object[]{loginBean.getGzhHash()},"menuOrder","asc");

		List<WxMenu> parents = new ArrayList<>();
		List<WxMenu> childs = new ArrayList<>();
		for (WxMenu wxMenu : menus) {
			if (DataUtils.isNullOrEmpty(wxMenu.getParentGuid())) {
				parents.add(wxMenu);
			} else {
				childs.add(wxMenu);
			}
		}

		JSONArray ary = new JSONArray();
		for (WxMenu wxMenu : parents) {
			JSONObject obj = new JSONObject();
			obj.put("sort", wxMenu.getMenuOrder());
			obj.put("guid", wxMenu.getMenuGuid());
			obj.put("name", wxMenu.getMenuName());

			JSONArray childAry = new JSONArray();
			for (WxMenu child : childs) {
				if (child.getParentGuid().equals(wxMenu.getMenuGuid())) {
					JSONObject childObj = new JSONObject();
					childObj.put("sort", child.getMenuOrder());
					childObj.put("guid", child.getMenuGuid());
					childObj.put("name", child.getMenuName());
					childAry.add(childObj);
				}
			}
			obj.put("child", childAry);
			ary.add(obj);
		}
		jsonObject = ary;

		return this.SUCCESS;
	}

	/**
	 * 保存排序
	 * 
	 * @return
	 */
	public String saveSort() {

		if (DataUtils.isNullOrEmpty(data)) {
			setMap(false, "保存失败（未知数据）", null);
			return this.SUCCESS;
		}

		JSONArray ary = JSONArray.fromObject(data);
		for (Object object : ary) {
			JSONObject item = JSONObject.fromObject(object);
			this.wxMenuService.update(WxMenu.class,
					new String[] { "menuOrder" },
					new Object[] { item.getString("sort") }, "menuGuid='"
							+ item.getString("guid") + "'");
		}
		
		setMap(true, "保存成功", null);

		return this.SUCCESS;
	}

	/**
	 * 修改菜单名称
	 * 
	 * @return
	 */
	public String update() {

		if (DataUtils.isNullOrEmpty(wxMenu.getMenuGuid())) {
			setMap(false, "修改失败（未知数据）", null);
			return this.SUCCESS;
		}

		if (DataUtils.isNullOrEmpty(wxMenu.getMenuName())) {
			setMap(false, "修改失败（菜单名不能为空）", null);
			return this.SUCCESS;
		}

		WxMenu menu = this.wxMenuService.get(WxMenu.class, "menuGuid",
				wxMenu.getMenuGuid());
		menu.setMenuName(wxMenu.getMenuName());
		this.wxMenuService.update(menu);
		setMap(true, "修改成功", null);
		return this.SUCCESS;
	}

	@Override
	public WxMenu getModel() {
		// TODO Auto-generated method stub
		return wxMenu;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxMenu) {
			wxMenu = new WxMenu();
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
