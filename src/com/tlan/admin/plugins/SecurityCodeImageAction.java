/**
 * @File:SecurityCodeImageAction.java
 * @Package cn.com.canon.qrc.action
 * @Description:TODO
 * @Copyright:Copyright(c)2012
 * @Company:Canon
 * @author:guess
 * @date:Nov 16, 2012 2:10:22 PM
 * @version:V1.0
 */

package com.tlan.admin.plugins;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.tlan.common.utils.SecurityCode;
import com.tlan.common.utils.SecurityImage;
import com.tlan.contrants.ParamString;

/**
 * 提供图片验证码
 * 
 * @ClassName:SecurityCodeImageAction
 * @Description:TODO
 * @author:magenm
 * @date:Nov 16, 2012 2:10:22 PM
 */

public class SecurityCodeImageAction extends ActionSupport implements
		SessionAware {
	private static final long serialVersionUID = 8043122482830595975L;
	// 图片流
	private ByteArrayInputStream imageStream;
	// session域
	private Map<String, Object> session;

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String execute() throws Exception {
		// 如果开启Hard模式，可以不区分大小写
		// String securityCode =
		// SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard,
		// false).toLowerCase();

		// 获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		// 放入session中
		session.put(ParamString.VALIDATE_CODE,
				securityCode);
		return SUCCESS;
	}
}