package com.tlan.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tlan.beans.LoginBean;
import com.tlan.contrants.ParamString;

public class LoginInterceptor2 extends AbstractInterceptor {

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
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter pw = response.getWriter();
			// 对ajax请求的拦截
			if (request.getHeader("X-Requested-With") != null
					&& request.getHeader("X-Requested-With").equalsIgnoreCase(
							"XMLHttpRequest")) {
				response.setCharacterEncoding("text/html;charset=utf-8");
				response.setContentType("text/html;charset=utf-8");
				JSONObject obj = new JSONObject();
				obj.put("status", "301");
				obj.put("timeout", "1");
				obj.put("success", "true");
				obj.put("message", "操作失败，请登录后重试！");
				pw.write(obj.toString());
				return null;
				// 不是异步请求的拦截
			} else {
				response.setCharacterEncoding("text/html;charset=utf-8");
				return "login";
			}
		}
		return invocation.invoke();
	}
}
