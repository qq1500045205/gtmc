package com.tlan.plugins.sms.jiaqi;

import java.util.HashMap;
import java.util.Map;

import com.tlan.plugins.sms.HttpClientUtil;

/**
 * 短信工具类
 * 
 * @author magenm
 * 
 */
public class SmsUtils {

	/**
	 * 发送短信
	 * 
	 * @param apikey
	 *            是 用户唯一标识
	 * @param mobile
	 *            接收的手机号
	 * @param text
	 *            是 短信内容
	 * @return { "code":0, "msg":"OK", "result":{ "count":1, //成功发送的短信个数
	 *         "fee":1.0, //扣费条数，70个字一条，超出70个字时按每67字一条计 "sid":1097 //短信id } }
	 * 
	 */
	public static String sendMsg(String apikey, String mobile, String text) {
		try {
			
			Map<String,String> map = new HashMap<String,String>();	
			map.put("apikey", apikey);
			map.put("mobile", mobile);
			map.put("text", text+ParamValues.SING_STRING);
			
			String client = HttpClientUtil.httpPost(ParamValues.SEND_SMS,map, ParamValues.URL_ENCODE);
			return client;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用默认apikey发短信
	 * 
	 * @param mobile
	 * @param text
	 * @return
	 */
	public static String sendMsg(String mobile, String text) {
		return sendMsg(ParamValues.API_KEY, mobile, text);
	}

	/**
	 * 查询账户信息
	 * 
	 * @param apikey
	 * @return
	 * 
	 *         { "code":0, "msg":"OK", "user":{ "nick":"Jacky",
	 *         "gmt_created":"2012-09-11 15:14:00", "mobile":"15268109538",
	 *         "email":"jacky@taovip.com",
	 *         "apikey":"9b11127a9701975c734b8aee81ee3526", "ip_whitelist":null,
	 *         //IP白名单，推荐使用 "api_version":"v1", //api版本号 "send_limit":10000,
	 *         //每天短信发送量限制，默认为1万 "send_count":0 , //当天已发送的短信数 "balance ":0
	 *         //短信剩余条数 } }
	 */
	public static String queryAccount(String apikey) {
		try {
			return HttpClientUtil.get(ParamValues.GET_ACCOUNT + "?apikdy="
					+ apikey, ParamValues.URL_ENCODE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用默认apikey查询
	 * 
	 * @return
	 */
	public static String queryAccount() {
		return queryAccount(ParamValues.API_KEY);
	}

}
