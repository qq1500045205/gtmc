package com.tlan.admin.plugins;

import com.tlan.common.action.BaseAction;
import com.tlan.plugins.sms.jiaqi.SmsUtils;

public class SmsAction extends BaseAction{
	private static final long serialVersionUID = -2974074306837574168L;
	
	public String sendMsg(){
		
		String result = SmsUtils.sendMsg("15876527654", "测试发信息给lee");
		System.out.println(result);
		return this.SUCCESS;
	}
	
}
