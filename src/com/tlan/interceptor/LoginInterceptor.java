package com.tlan.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tlan.beans.LoginBean;
import com.tlan.contrants.ParamString;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3386726706635960700L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// 取得请求相关的ActionContext实例

		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		LoginBean loginBean = (LoginBean) session
				.get(ParamString.USER_LOGIN_SESSION_OBJECT);
		// 判断是否登录
		if (null == loginBean) {
			return "login";
		}
		return invocation.invoke();
	}
}
