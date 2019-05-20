package com.tlan.plugins.sms.esoft;

import java.util.HashMap;
import java.util.Map;

import com.tlan.common.utils.PropertiesUtil;
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
	 * @param mobile
	 * @param content
	 * @param sendtime
	 * @return 0 提交成功 –1 账号未注册 –2 其他错误 –3 帐号或密码错误 –5 余额不足，请先充值 –6
	 *         定时发送时间不是有效的时间格式 –8 发送内容需在1到500字之间 -9 发送号码为空 -10 定时时间不能小于当前系统时间
	 *         -100 限制此IP访问 -101 调用接口速度太快
	 */
	public static String sendMsg(String mobile, String content, String sendtime) {
		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put(ParamValues.CORPID,
					PropertiesUtil.getProperty(ParamValues.CORPID));
			map.put(ParamValues.PWD,
					PropertiesUtil.getProperty(ParamValues.PWD));
			map.put("Mobile", content);
			map.put("Content", content);
			map.put("Cell", ""); // 群发时使用
			map.put("SendTime", sendtime);

			String client = HttpClientUtil.httpPost(
					PropertiesUtil.getProperty(ParamValues.SMS_URL), map,
					PropertiesUtil.getProperty(ParamValues.SMS_ENCODE));
			return client;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
