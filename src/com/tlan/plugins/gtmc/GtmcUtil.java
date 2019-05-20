package com.tlan.plugins.gtmc;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

/**
 * webservice数据获取
 * 
 * @author magenm
 * 
 */
public class GtmcUtil {
	private static Logger log = Logger.getLogger(GtmcUtil.class);

	/**
	 * 
	 * 获取数据，默认为1：试驾申请
	 * 
	 * @param method
	 *            对应的ws方面名，如：GetINFOBYMEDIA
	 * @return
	 */
	public static String deal(String interfaceName) {
		return deal("GetINFOBYMEDIA", interfaceName, 1);
	}

	/**
	 * 获取数据
	 * 
	 * @param method
	 *            对应的ws方面名，如：GetPROVINCEBYMEDIA
	 * @param type
	 *            1：试驾申请 2：保养申请
	 * @return
	 */
	public static String deal(String method, String interfaceName, int type) {
		StringBuffer sb = new StringBuffer();
		sb.append(PropertiesUtil.getProperty(ParamString.GCLOUD_WS_URL));
		sb.append("/");
		sb.append(method);
		sb.append("?");
		sb.append("media_code=");
		if (type == 2) {
			sb.append(PropertiesUtil
					.getProperty(ParamString.MEDIA_CODE_BAOYANG));
		} else {
			sb.append(PropertiesUtil.getProperty(ParamString.MEDIA_CODE_SHIJIA));
		}
		sb.append("&");
		sb.append("Interface_name=");
		sb.append(interfaceName);
		sb.append("&");
		sb.append("Conduct_type=");
		sb.append(PropertiesUtil.getProperty(ParamString.CONDUCT_TYPE));

		// System.out.println(sb.toString());
		String result = HttpClientUtil.sendGetRequest(sb.toString(), "UTF-8");
		// System.out.println(result);
		return result;
	}

	/**
	 * 获取数据
	 * 
	 * @param method
	 *            对应的ws方面名，如：GetPROVINCEBYMEDIA
	 * @param type
	 *            1：试驾申请 2：保养申请
	 * @return
	 */
	public static boolean setData(String json, int type) {
		StringBuffer sb = new StringBuffer();
		sb.append(PropertiesUtil.getProperty(ParamString.GCLOUD_WS_URL));
		sb.append("/");
		sb.append("SetSalesLeads");

		Map<String, String> params = Maps.newHashMap();
		if (type == 2) {
			params.put("media_code",
					PropertiesUtil.getProperty(ParamString.MEDIA_CODE_BAOYANG));
		} else {
			params.put("media_code",
					PropertiesUtil.getProperty(ParamString.MEDIA_CODE_SHIJIA));
		}
		System.out.println(sb.toString());
		params.put("Interface_name", "Interface_salesleads");
		params.put("JSONSTRING", json);

		// System.out.println(sb.toString());
		String result = HttpClientUtil.sendPostRequest(sb.toString(), params,
				"utf-8", "utf-8");
		System.out.println(result);
		if (result.contains("true")) {
			return true;
		}
		return false;
	}

	/**
	 * 验证用户是否登录gcloud系统
	 * 
	 * 需要gcloud跳转时将usercode和dlrcode传过来，dlrcode用于判断角色
	 * 
	 * @Title: isLogin
	 * @Description: TODO
	 * @param @param userCode
	 * @param @param request
	 * @param @return
	 * @return boolean
	 * @throws
	 * @Date 2014年3月12日 下午6:55:50
	 */
	public static boolean isLogin(String userCode, HttpServletRequest request) {
		try {
			log.info("login : " + userCode);
			String NET_ADDRESS = PropertiesUtil
					.getProperty(ParamString.GCLOUD_LOGIN_URL);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(NET_ADDRESS));
			call.setOperationName(new QName(NET_ADDRESS, "IsLogined"));// 设置方法名
			return (boolean) call.invoke((new Object[] { userCode,
					getIpAddr(request) }));// 传入参数调用方法。
		} catch (MalformedURLException e) {
			log.error("isLogin MalformedURLException :" + e.getMessage());
		} catch (RemoteException e) {
			log.error("isLogin RemoteException:" + e.getMessage());
		} catch (ServiceException e) {
			log.error("isLogin ServiceException:" + e.getMessage());
		}
		return false;
	}
	
	/**
	 * 获取访问端ip
	 * 
	 * @Title: getIpAddr
	 * @Description: TODO
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月12日 下午6:56:10
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
