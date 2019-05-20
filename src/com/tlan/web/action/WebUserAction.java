package com.tlan.web.action;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUser;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.EncryptDecryptData;
import com.tlan.common.view.WxUserCarProjectView;
import com.tlan.contrants.ParamString;
import com.tlan.plugins.sms.jiaqi.SmsUtils;
import com.tlan.plugins.sms.ws.GtmcSms;

/**
 * web用户管理
 * 
 * @author limengjie
 * 
 */
public class WebUserAction extends BaseAction implements Preparable,
		ModelDriven<Webuser> {

	private Log log = LogFactory.getLog(WebUserAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxUser> wxuserService;
	@Resource(name = "baseService")
	private IBaseService<Webuser> webUserService;

	private Webuser webuser;

	private String openid;
	private String gzh_guid;
	
	

	private String user_guid;

	

	private String validatorCode;

	/**
	 * 获取后台web用户信息
	 * 
	 * @return
	 */
	
	public String addUser(){
		try {
			EncryptDecryptData cryptData = new EncryptDecryptData();
			LoginBean loginBean = this.obtainLoginBean();
			this.webuser.setUserPwd(cryptData.encrypt(webuser.getUserPwd()));
			this.webuser.setUserGuid(DataUtils.getUUID());
			
			this.webuser.setParentGuid(loginBean.getUserGuid());
			this.webuser.setRights("0");
			if(loginBean.getRoleType().equals("ADMIN")){
				//
			}else{
				this.webuser.setGzhGuid(loginBean.getGzhGuid());
				this.webuser.setRoleType(loginBean.getRoleType());
			}
			this.webUserService.save(this.webuser);
			setMap(true, "success", webuser);
		}catch(Exception e){
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	public String updateUser(){
		try {
			this.webUserService.update(this.webuser);
			setMap(true, "success", null);
		}catch(Exception e){
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	public String deleteUser(){
		try {
			this.webUserService.delete(this.webuser);
			setMap(true, "success", null);
		}catch(Exception e){
			return this.ERROR;
		}
		return this.SUCCESS;
	}
	
	
	
	public String getwebuserlist()
	{
		
		ArrayList<Object> arrayList=new  ArrayList<Object>();
		
		List<Webuser> webuserlist=webUserService.getAll(Webuser.class,"status",0);
		
		for (int i = 0; i < webuserlist.size(); i++) {
			Webuser webuserinfo=webuserlist.get(i);
			HashMap<String, String> map=new HashMap<String,String>();
			map.put("userGuid", webuserinfo.getUserGuid());
			map.put("loginName", webuserinfo.getLoginName());
			map.put("userName", webuserinfo.getUserName());
			map.put("userCode", webuserinfo.getUserCode());
			map.put("gzhHash", webuserinfo.getGzhHash());
			map.put("rights", webuserinfo.getRights());
			arrayList.add(map);
			
		}
		if (webuserlist.size() > 0) {
			setMap(true, "ok", webuserlist);
		} else {
			setMap(false, "no matching result", null);
		}
		return this.SUCCESS;
	}
	
	
	public String getsubwebuserlist()
	{
		LoginBean loginBean = this.obtainLoginBean();
		
		ArrayList<Object> arrayList=new  ArrayList<Object>();
		
		List<Webuser> webuserlist=webUserService.getAll(Webuser.class,"parent_guid", loginBean.getUserGuid());
		
		for (int i = 0; i < webuserlist.size(); i++) {
			Webuser webuserinfo=webuserlist.get(i);
			HashMap<String, String> map=new HashMap<String,String>();
			map.put("userGuid", webuserinfo.getUserGuid());
			map.put("loginName", webuserinfo.getLoginName());
			map.put("userName", webuserinfo.getUserName());
			map.put("userCode", webuserinfo.getUserCode());
			map.put("gzhHash", webuserinfo.getGzhHash());
			map.put("rights", webuserinfo.getRights());
			arrayList.add(map);
			
		}
		 
		setMap(null, JSONArray.fromObject(webuserlist), webuserlist.size());
		 
		return this.SUCCESS;
	}
	
	
	public String getUserRight(){
		ArrayList<Object> arrayList=new  ArrayList<Object>();
		List<Webuser> webuserlist=webUserService.getAll(Webuser.class,"user_guid", user_guid);
		
		for (int i = 0; i < webuserlist.size(); i++) {
			Webuser webuserinfo=webuserlist.get(i);
			HashMap<String, String> map=new HashMap<String,String>();
			map.put("userGuid", webuserinfo.getUserGuid());
			map.put("rights", webuserinfo.getRights());
			arrayList.add(map);
			
		}
		if (webuserlist.size() > 0) {
			//setMap(true, "ok", arrayList);
			setMap(null, JSONArray.fromObject(webuserlist), webuserlist.size());
		} else {
			setMap(false, "no matching result", null);
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getValidatorCode() {
		return validatorCode;
	}

	public void setValidatorCode(String validatorCode) {
		this.validatorCode = validatorCode;
	}
	public String getUser_guid() {
		return user_guid;
	}

	public void setUser_guid(String user_guid) {
		this.user_guid = user_guid;
	}
	public String getGzhGuid() {
		return gzh_guid;
	}

	public void setGzhGuid(String gzhGuid) {
		this.gzh_guid = gzhGuid;
	}

}
