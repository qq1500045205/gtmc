package com.tlan.webservice;

import com.tlan.plugins.sms.ws.GtmcSms;

public class WebServiceAction {

	private String[] telephones;
	private String content;

	/**
	 * 发送短信
	 * 
	 * @throws Exception
	 */
	public void sendsms() throws Exception {
		GtmcSms sms = new GtmcSms();
		boolean result = sms.sendMessage(telephones, content);
		if (result) {
			System.out.println("发送成功");
		} else {
			System.out.println("发送失败");
		}
	}
	
	public void sendValidationCode(){
		
	}
}
