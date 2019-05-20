package com.tlan.admin.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.EncryptDecryptData;

/**
 * web用户管理
 * 
 * @author magenm
 * 
 */
public class WebuserAction extends BaseAction implements Preparable,
		ModelDriven<Webuser> {

	private Log log = LogFactory.getLog(WebuserAction.class);

	@Resource(name = "baseService")
	private IBaseService<Webuser> webuserService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;

	private Webuser webuser;
	
	private String gzhHash;

	

	private String[] items;

	private LoginBean loginBean = obtainLoginBean();

	/**
	 * 添加Webuser
	 * 
	 * @return
	 */
	public String addUser() {
		switch (webuser.getUserPrivilegeLevel()) {
		case 0: // 0:admin,
			break;
		case 1:// 1:area_admin,
			break;
		case 2:// 2: project_admin:
			break;
		case 3:// 3: wx_admin
			WxGongzhonghao gzh = gzhService.get(WxGongzhonghao.class,
					"gzhGuid", webuser.getUserPrivileges());
			webuser.setGzhHash(gzh.getGzhHash());
		}

		try {
			
			LoginBean loginBean = this.obtainLoginBean();
			EncryptDecryptData cryptData = new EncryptDecryptData();
			webuser.setUserPwd(cryptData.encrypt(webuser.getUserPwd()));

			webuser.setCreatedBy(loginBean.getUserName());
			webuser.setCreatedOn(DateUtils.currentDatetime());
			webuser.setUserGuid(DataUtils.getUUID());
			if(!loginBean.getRoleType().equals("ADMIN")){
				webuser.setRoleType(loginBean.getGzhGuid());
			}
			if(loginBean.getRoleType().equals("DISTADMIN") || loginBean.getRoleType().equals("ADMIN")){
			}
			if(loginBean.getRoleType().equals("DLRADMIN")){
				webuser.setRoleType("DLRADMIN");
			}
			webuserService.save(webuser);

			setMap(true, "添加成功", null);
		} catch (Exception e) {
			log.error("添加用户异常：" + e.getMessage());
			setMap(false, "添加失败", null);
		}

		return this.SUCCESS;
	}

	public String getAllUser() {
		List<Webuser> webUsers = new ArrayList<Webuser>();
		webUsers = webuserService.getAll(Webuser.class);

		setMap(true, "success", webUsers);

		return this.SUCCESS;
	}
	
	public String getAllGzhUser() {
		List<Webuser> webUsers = new ArrayList<Webuser>();
		webUsers = webuserService.getAll(Webuser.class, "gzhHash", gzhHash, "=");

		setMap(true, "success", webUsers);

		return this.SUCCESS;
	}

	/**
	 * 删除后台用户
	 * 
	 * @return
	 */
	public String deleteWebuser() {
		if (null != items) {
			for (int i = 0; i < items.length; i++) {
				// 删除关键字
				webuserService.delete(Webuser.class, "user_guid", items[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}

		return this.SUCCESS;
	}

	@Override
	public Webuser getModel() {
		// TODO Auto-generated method stub
		return webuser;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == webuser) {
			webuser = new Webuser();
		}
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}
}
