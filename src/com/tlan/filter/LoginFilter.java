package com.tlan.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tlan.admin.sys.AdminAction;
import com.tlan.contrants.ParamString;

public class LoginFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//arg2.doFilter(arg0, arg1);
		//暂时去掉
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;

		HttpSession session = req.getSession(true);

		if (null != session.getAttribute(ParamString.USER_LOGIN_SESSION_OBJECT)) {
			arg2.doFilter(arg0, arg1);
		} else {
			// 过滤jsp action由拦截器处理
			if (req.getRequestURI().contains(".jsp")) {
				// 不过滤登录页面
				//TODO
				if (req.getRequestURI().contains(AdminAction.LOGIN)
						|| req.getRequestURI().contains("/wx/")) {// /wx/cnthtml/
					arg2.doFilter(arg0, arg1);
				} else {
					String jsp = req.getContextPath() + AdminAction.LOGIN;
					res.sendRedirect(jsp);
					return;
				}

			} else if (req.getRequestURI().endsWith("/")) {
				String jsp = req.getContextPath() + AdminAction.LOGIN;
				res.sendRedirect(jsp);
				return;
			} else {
				arg2.doFilter(arg0, arg1);
			}
		}
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
