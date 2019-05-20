package com.tlan.admin.api;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.tlan.admin.plugins.SmsAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;
import com.tlan.wxkit.http.HttpUtils;
import com.tlan.plugins.sms.jiaqi.SmsUtils;
import com.tlan.plugins.sms.ws.GtmcSms;

public class Test {
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	private static Logger log = Logger.getLogger(WeixinAction.class);
	
     public static void main(String[] args) throws ParseException{
//    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	 java.util.Date date=df.parse("2014-05-21 21:34:00");
//    	 long l=DateUtils.parseDatetime(DateUtils.currentDatetime()).getTime()-date.getTime();
//    	 System.out.println("打印出L:" + l);
// 		long day=l/(24*60*60*1000);
// 		System.out.println("打印出day:" + day);
// 		long hour=(l/(60*60*1000)-day*24);
// 		System.out.println("333333333333333333333333333:" + hour)
    	sms();

 	}
     
    public static void sms(){
	   	// GtmcSms sms = new GtmcSms();
    	String[] phones = {"15876527654"};
	   	 try {
				boolean result = GtmcSms.sendMessage(phones, "测试发送短信lee");
				log.info("发送短信结果:" + result);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
    }
}
