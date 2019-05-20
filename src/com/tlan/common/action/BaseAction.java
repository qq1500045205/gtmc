package com.tlan.common.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tlan.beans.LoginBean;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.Page;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.common.view.WxUserInfoView;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.utils.OpenIdUtil;

public class BaseAction<T> extends ActionSupport {
	private static final long serialVersionUID = -3955042501033772777L;
	private static Logger log = Logger.getLogger(BaseAction.class);
	// 参数类型
	private String type;
	// 下面两项用户json数据分页
	private int start = 0;
	private int limit = 10;
	// 分页对象
	protected Page page = new Page();
	// 高级接口相关
	private String redirectUrl;
	protected String u;
	private String state;
	private String scope;
	private String code;
	// 用户处理异步信息
	protected Object jsonObject;
	
	//带条件查询
	private Object t1;
	private Object t2;
	private Object[] params;
	
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	protected Map<String, Object> map = ServletActionContext.getContext()
			.getSession();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	protected Boolean checkLogin() {
		if (this.map != null
				&& this.map
						.containsKey(ParamString.USER_LOGIN_SESSION_CHECK_TAG)) {
			if (this.map.get(ParamString.USER_LOGIN_SESSION_CHECK_TAG) == "true") {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取登录用户的信息
	 * 
	 * @return
	 */
	protected LoginBean obtainLoginBean() {
		if (this.map != null
				&& this.map.containsKey(ParamString.USER_LOGIN_SESSION_OBJECT)) {
			return (LoginBean) this.map
					.get(ParamString.USER_LOGIN_SESSION_OBJECT);
		}
		return null;
	}

	protected boolean checkValidatorCode(String validatorCode) {
		
		if(this.getSession(ParamString.WX_USER_SESSION_OBJECT)!=null){
			Object obj = this.getSession(ParamString.WX_USER_SESSION_OBJECT);
			WxUserInfoView wxUser = (WxUserInfoView)obj;
			if(wxUser.getMemberLevel() > 0){
				return true;
			}
		}
		
		if (validatorCode.equals("")) {
			this.setMap(false, "failure", "验证码不能为空");
			return false;
		}
		Object obj = this.getSession(ParamString.SMS_VALIDATION_CODE);
		if (obj == null || obj.toString().equals("")) {
			this.setMap(false, "failure", "验证码失效, 请重新获取");
			return false;
		}
		if (obj.toString().equals(validatorCode)) {
			return true;
		}
		this.setMap(false, "codefailure", "验证码失效, 请重新获取");
		return false;
	}

	protected void emptyValidatorCode() {
		// this.setSession(ParamString.SMS_VALIDATION_CODE, null);
	}

	/**
	 * 获取授权地址
	 * 
	 * @Title: getAuthUrl
	 * @Description: TODO
	 * @param @param appid
	 * @param @param gzhHash
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月4日 上午10:10:00
	 */
	protected String getUrl(String appid, String gzhHash) {
		String basepath = getBasePath();
		// ModuleAction.ADMIN_CNT_HTML
		redirectUrl = basepath + "w/a?u=" + redirectUrl;
		log.info("getUrl:" + redirectUrl);
		if (DataUtils.isNullOrEmpty(scope)) {
			scope = PropertiesUtil.getProperty(ParamString.WX_SCOPE);
		}
		return OpenIdUtil.getCode(appid, redirectUrl, scope, gzhHash);
	}

	/**
	 * 获取openid
	 * 
	 * @return
	 */
	protected String getOpenid(String appid, String appsecret) {
		return OpenIdUtil.getOpenid(appid, appsecret, code);
	}

	/**
	 * 获取basepath
	 * 
	 * @return
	 */
	public String getBasePath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String basepath = request.getScheme() + "://"
				+ PropertiesUtil.getProperty(ParamString.BASE_IP)
				+ request.getContextPath() + "/";
		return basepath;
	}

	/**
	 * 设置异步请求提示处理信息
	 * 
	 * @param success
	 * @param msg
	 * @param obj
	 */
	protected void setMap(boolean success, String msg, Object obj) {
		this.dataMap.put("success", success);
		this.dataMap.put("message", msg);
		this.dataMap.put("result", obj);
	}

	/**
	 * 构造grid数据
	 * 
	 * @param colmodel
	 * @param rows
	 * @param total
	 */
	public void setMap(Object colmodel, Object rows, Object total) {
		this.dataMap.put("colmodel", colmodel);
		this.dataMap.put("rows", rows);
		this.dataMap.put("total", total);
	}

	public String getRequest(String key) {
		return (String) ServletActionContext.getRequest().getParameter(key);
	}

	public Page getPage() {
		return page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Object getJsonObject() {
		return jsonObject;
	}

	public void setSession(String key, Object val) {
		if (this.map != null) {
			this.map.put(key, val);
		}
	}

	public Object getSession(String key) {
		if (this.map != null) {
			return this.map.get(key);
		}
		return null;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Object getT1() {
		return t1;
	}

	public void setT1(Object t1) {
		this.t1 = t1;
	}

	public Object getT2() {
		return t2;
	}

	public void setT2(Object t2) {
		this.t2 = t2;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
}
