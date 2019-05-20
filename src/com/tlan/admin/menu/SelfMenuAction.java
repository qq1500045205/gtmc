package com.tlan.admin.menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tlan.admin.module.ModuleAction;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxMenu;
import com.tlan.common.service.IBaseService;
import com.tlan.wxkit.bean.MenuBean;
import com.tlan.wxkit.bean.MenuToJSON;
import com.tlan.wxkit.bean.TypeBean;
import com.tlan.wxkit.http.HttpUtils;

/**
 * 自定义菜单操作类
 * 
 * @author magenm
 * 
 */
public class SelfMenuAction extends BaseAction {
	private static final long serialVersionUID = -2974074306837574168L;
	
	@Resource(name = "baseService")
	private IBaseService<WxMenu> wxMenuService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;

	/**
	 * 创建自定义菜单
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			String gzh = obtainLoginBean().getGzhHash();
			WxGongzhonghao obtainGzh = obtainGzh(gzh);
			List<WxMenu> menus = wxMenuService.getAll(WxMenu.class, new String[]{"gzhHash"},new Object[]{ gzh},
					"menuOrder", "asc");

			List<WxMenu> parents = new ArrayList<>();
			List<WxMenu> childs = new ArrayList<>();
			for (WxMenu wxMenu : menus) {
				if (null == wxMenu.getParentGuid()) {
					parents.add(wxMenu);
				} else {
					childs.add(wxMenu);
				}
			}

			List<MenuBean> menuBeans = new ArrayList<>();
			for (WxMenu wxMenu : parents) {
				if (wxMenu.getIsHaveChild() == WxMenu.HAVECHILD) {
					List<MenuBean> childBeans = new ArrayList<>();
					MenuBean mneuBean = new MenuBean(wxMenu.getMenuName(),
							childBeans);
					for (WxMenu child : childs) {
						if (wxMenu.getMenuGuid().equals(child.getParentGuid())) {
							MenuBean childBean;
							if (TypeBean.CLICK.equalsIgnoreCase(child
									.getMenuType())) {
								childBean = new MenuBean(child.getMenuName(),
										child.getMenuKey());
								childBeans.add(childBean);
							} else if (TypeBean.VIEW.equalsIgnoreCase(child
									.getMenuType())) {
								
								setRedirectUrl(child.getMenuUrl());
								childBean = new MenuBean(TypeBean.VIEW,
										child.getMenuName(), getUrl(obtainGzh.getAppid(), gzh));
								childBeans.add(childBean);
							} else {
								childBean = null;
							}
						}
					}
					menuBeans.add(mneuBean);
				} else {
					MenuBean mneuBean;
					if (TypeBean.CLICK.equalsIgnoreCase(wxMenu.getMenuType())) {
						mneuBean = new MenuBean(wxMenu.getMenuName(),
								wxMenu.getMenuKey());
						menuBeans.add(mneuBean);
					} else if (TypeBean.VIEW.equalsIgnoreCase(wxMenu
							.getMenuType())) {
						
						setRedirectUrl(wxMenu.getMenuUrl());
						mneuBean = new MenuBean(TypeBean.VIEW,
								wxMenu.getMenuName(), getUrl(obtainGzh.getAppid(), gzh));
						menuBeans.add(mneuBean);
					} else {
						mneuBean = null;
					}
				}
			}

			MenuToJSON json = new MenuToJSON(menuBeans);
			String token = HttpUtils.getToken(obtainGzh.getAppid(),
					obtainGzh.getAppsecret());

			String menuStr = json.toJSON().toString();
			System.out.println(obtainGzh.getGzhAccount()+"=="+menuStr);
			boolean flag = HttpUtils.generator(token, menuStr);

			if (flag) {
				setMap(true, "发布成功", null);
			} else {
				setMap(false, "发布失败", null);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMap(false, "发布失败", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 获取公众号对应的用户以提取相应信息
	 * 
	 */
	public WxGongzhonghao obtainGzh(String hash) {
		return gzhService.get(WxGongzhonghao.class, "gzhHash", hash);
	}
}
