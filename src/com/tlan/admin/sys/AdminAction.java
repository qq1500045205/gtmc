package com.tlan.admin.sys;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.EncryptDecryptData;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.plugins.gtmc.GtmcUtil;

public class AdminAction extends BaseAction implements Preparable,
		ModelDriven<Webuser> {

	private Log log = LogFactory.getLog(AdminAction.class);
	@Resource(name = "baseService")
	private IBaseService<Webuser> webUserService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> projectService;

	public static final String WECLOME = "/admin/welcome.jsp";
	//public static final String LOGIN = "/admin/user/login.jsp";

	private Webuser webUser;
	private String gzhGuid;
	private String loginIp;

	public String welcome() {
		if (this.checkLogin() == true) {
			return this.SUCCESS;
		}
		return this.ERROR;
	}

	public String login() {
		try {
			String loginName = this.webUser.getLoginName();
			HttpServletRequest request = ServletActionContext.getRequest();
			String gIp = PropertiesUtil.getProperty(ParamString.GCLOUD_IP);
			String ip = request.getHeader("referer");
			log.info(loginName+":gIp:"+gIp);
			log.info(loginName+":ip:"+ip);
			// 检测是否来自g-cloud系统
			if (ip.contains(gIp)) {
				log.info("query webuser");
				loginIp = ip;
				Webuser webuser = this.webUserService.get(Webuser.class,
						"loginName", loginName);
				this.map.put(ParamString.USER_LOGIN_SESSION_CHECK_TAG, "true");
				this.map.put(ParamString.USER_LOGIN_SESSION_LOGIN_NAME,
						webuser.getLoginName());
				this.map.put(ParamString.USER_LOGIN_SESSION_DISPLAY_NAME,
						webuser.getUserName());

				LoginBean loginBean = new LoginBean();
				loginBean.setLoginName(webuser.getLoginName());
				loginBean.setUserName(webuser.getUserName());
				// loginBean.setPrivilegeLevel(webuser.getUserPrivilegeLevel());
				// loginBean.setPrivileges(webuser.getUserPrivileges());
				loginBean.setRights(webuser.getRights());
				loginBean.setUserGuid(webuser.getUserGuid());
				loginBean.setGzhGuid(webuser.getGzhGuid());
				loginBean.setRoleType(webuser.getRoleType());

				WxGongzhonghao gzh = this.gzhService.get(WxGongzhonghao.class,
						"gzhHash", webuser.getGzhHash());
				if (null != gzh) {
					loginBean.setGzhName(gzh.getGzhName());
					loginBean.setGzhHash(gzh.getGzhHash());
					loginBean.setGzhGuid(gzh.getGzhGuid());

					WxProject project = projectService.get(WxProject.class,
							"projectGuid", gzh.getProjectGuid());
					loginBean.setGzhType(project.getGzhType());
				}

				this.map.put(ParamString.USER_LOGIN_SESSION_OBJECT, loginBean);
				this.changeGzh();
				setMap(true, "登录成功！", WECLOME);
				log.info("login success");
				return this.SUCCESS;
			}else{
				return this.ERROR;
			}
		} catch (Exception e) {
			log.error("登录异常：【" + this.webUser.toString() + "】\n"
					+ e.getMessage());
			setMap(false, "系统异常,请联系管理员！！", ERROR);
			return this.ERROR;
		}
	}

	public String login2() {
		try {
			String validatorCode = (String) ServletActionContext.getRequest()
					.getParameter("validator");
			String tmp = (String) this.map.get(ParamString.VALIDATE_CODE);
			if (this.map == null || !tmp.equals(validatorCode)) {
				setMap(false, "验证码错误！", LOGIN);
				return this.SUCCESS;
			}
			String loginName = this.webUser.getLoginName();
			Webuser webuser = this.webUserService.get(Webuser.class,
					"loginName", loginName);
			try {
				EncryptDecryptData cryptData = new EncryptDecryptData();
				this.webUser.setUserPwd(cryptData.encrypt(this.webUser
						.getUserPwd()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setMap(false, "用户名或密码错误！", LOGIN);
				return this.SUCCESS;
			}
			if (webuser == null
					|| !webuser.getUserPwd().equals(this.webUser.getUserPwd())) {
				setMap(false, "用户名或密码错误！", LOGIN);
				return this.SUCCESS;
			}
			this.map.put(ParamString.USER_LOGIN_SESSION_CHECK_TAG, "true");
			this.map.put(ParamString.USER_LOGIN_SESSION_LOGIN_NAME,
					webuser.getLoginName());
			this.map.put(ParamString.USER_LOGIN_SESSION_DISPLAY_NAME,
					webuser.getUserName());

			LoginBean loginBean = new LoginBean();
			loginBean.setLoginName(webuser.getLoginName());
			loginBean.setUserName(webuser.getUserName());
			// loginBean.setPrivilegeLevel(webuser.getUserPrivilegeLevel());
			// loginBean.setPrivileges(webuser.getUserPrivileges());
			loginBean.setRights(webuser.getRights());
			loginBean.setUserGuid(webuser.getUserGuid());
			loginBean.setGzhGuid(webuser.getGzhGuid());
			loginBean.setRoleType(webuser.getRoleType());

			WxGongzhonghao gzh = this.gzhService.get(WxGongzhonghao.class,
					"gzhHash", webuser.getGzhHash());
			if (null != gzh) {
				loginBean.setGzhName(gzh.getGzhName());
				loginBean.setGzhHash(gzh.getGzhHash());
				loginBean.setGzhGuid(gzh.getGzhGuid());

				WxProject project = projectService.get(WxProject.class,
						"projectGuid", gzh.getProjectGuid());
				loginBean.setGzhType(project.getGzhType());
			}

			this.map.put(ParamString.USER_LOGIN_SESSION_OBJECT, loginBean);

			//
			this.changeGzh();
			//

			setMap(true, "登录成功！", WECLOME);
			return this.SUCCESS;
		} catch (Exception e) {
			log.error("登录异常：【" + this.webUser.toString() + "】\n"
					+ e.getMessage());
			setMap(false, "系统异常,请联系管理员！！", LOGIN);
			return this.SUCCESS;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String changeGzh() {
		LoginBean loginBean = obtainLoginBean();
		WxGongzhonghao gzh = gzhService.get(WxGongzhonghao.class, "gzhGuid",
				loginBean.getGzhGuid());
		if (null != gzh) {
			loginBean.setGzhName(gzh.getGzhName());
			loginBean.setGzhHash(gzh.getGzhHash());

			WxProject project = projectService.get(WxProject.class,
					"projectGuid", gzh.getProjectGuid());
			loginBean.setGzhType(project.getGzhType());

			this.map.put(ParamString.USER_LOGIN_SESSION_OBJECT, loginBean);
			// setMap(true, "切换成功", null);
		} else {
			// setMap(true, "切换失败（公众号 不存在）", null);
		}

		return this.SUCCESS;
	}

	public String getMyInfo() {

		HashMap<String, String> map = new HashMap<String, String>();
		LoginBean loginBean = obtainLoginBean();
		map.put("gzhGuid", loginBean.getGzhGuid());
		map.put("userName", loginBean.getUserName());
		map.put("roleType", loginBean.getRoleType());
		map.put("rights", loginBean.getRights());
		map.put("gzhGuid", loginBean.getGzhGuid());

		return this.SUCCESS;
	}

	public String logout() {
		this.map.remove(ParamString.USER_LOGIN_SESSION_CHECK_TAG);
		this.map.remove(ParamString.USER_LOGIN_SESSION_LOGIN_NAME);
		this.map.remove(ParamString.USER_LOGIN_SESSION_DISPLAY_NAME);
		this.map.remove(ParamString.USER_LOGIN_SESSION_OBJECT);

		return this.SUCCESS;
	}

	@Override
	public Webuser getModel() {
		// TODO Auto-generated method stub
		return this.webUser;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == webUser) {
			webUser = new Webuser();
		}
	}

	public String getGzhGuid() {
		return gzhGuid;
	}

	public void setGzhGuid(String gzhGuid) {
		this.gzhGuid = gzhGuid;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}
