package com.tlan.plugins.sms.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

/**
 * G-Could 提供的短信接口
 * 
 * @author magenm 2014-2-10
 */
public class GtmcSms {
	private static Logger log = Logger.getLogger(GtmcSms.class);
	// WEBSERVICE访问路径
	private final static String NET_ADDRESS = PropertiesUtil
			.getProperty(ParamString.GCLOUD_SMS_WS_URL);
	private final static String SIGN = PropertiesUtil
			.getProperty(ParamString.GCLOUD_SMS_WS_SIGN);
	
	/**
	 * 调用webservice 发送短信
	 * 
	 * @param telephones
	 * @param content
	 * @param userIdsendSmsMessage3
	 * @throws Exception
	 */
	public static boolean sendMessage(String[] telephones, String content,
			String userId)  {
		try {
			log.info(telephones+":"+content +":"+ SIGN+":"+userId);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(NET_ADDRESS));
			call.setOperationName(new QName(NET_ADDRESS, "sendSmsMessage3"));// 设置方法名
			return (boolean) call.invoke((new Object[] { telephones,
					content + SIGN, 671, Integer.parseInt(userId) }));// 传入参数调用方法。
		} catch (MalformedURLException e) {
			log.error("sendMessage MalformedURLException :"+e.getMessage());
		} catch (RemoteException e) {
			log.error("sendMessage RemoteException:"+e.getMessage());
		} catch (ServiceException e) {
			log.error("sendMessage ServiceException:"+e.getMessage());
		}
		return false;
	}

	/**
	 * 调用webservice 发送短信
	 * 
	 * @param telephones
	 * @param content
	 * @throws Exception
	 */
	public static boolean sendMessage(String[] telephones, String content)
			throws Exception {
		return sendMessage(telephones, content,
				PropertiesUtil.getProperty(ParamString.GTMC_USER_ID));
	}

}
