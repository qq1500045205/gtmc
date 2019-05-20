package com.tlan.admin.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.EncryptDecryptData;

/**
 * web公共号管理
 * 
 * @author StevenWung
 * 
 */
public class WebGzhAction extends BaseAction implements Preparable,
		ModelDriven<WxGongzhonghao> {
	private static final long serialVersionUID = -2974074306837574168L;
	
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> webGzhService;
	private WxGongzhonghao webGzh;

	public LoginBean loginBean = obtainLoginBean();

	public String addGzh() {
		webGzh.setCreatedOn(DateUtils.currentDatetime());
		webGzh.setGzhGuid(DataUtils.getUUID());
		webGzh.setCreatedBy(loginBean.getUserName());
		webGzh.setGzhHash(DataUtils.hash(webGzh.getAppid()
				+ webGzh.getAppsecret()));
		webGzh.setToken(DataUtils.getSimpleUUID());

		try {
			EncryptDecryptData encrypt = new EncryptDecryptData();
			webGzh.setGzhPwd(encrypt.encrypt(webGzh.getGzhPwd()));
		} catch (Exception e) {
		}

		webGzhService.save(webGzh);

		setMap(true, "添加成功", null);

		return this.SUCCESS;
	}

	public String delGzh() {
		// TODO
		// 验证
		webGzhService.delete(webGzh);
		setMap(true, "删除成功", null);
		return this.SUCCESS;
	}

	public String getAllGzh() {
		List<WxGongzhonghao> webGzhs = new ArrayList<WxGongzhonghao>();
		webGzhs = webGzhService.getAll(WxGongzhonghao.class);

		setMap(true, "success", webGzhs);

		return this.SUCCESS;
	}

	public WxGongzhonghao getOne() {
		WxGongzhonghao gzh = this.webGzhService.get(WxGongzhonghao.class,
				"gzhGuid", webGzh.getGzhGuid());
		return null;
	}

	@Override
	public WxGongzhonghao getModel() {
		// TODO Auto-generated method stub
		return this.webGzh;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == webGzh) {
			webGzh = new WxGongzhonghao();
		}
	}

}
