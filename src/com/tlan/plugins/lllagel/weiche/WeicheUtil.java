package com.tlan.plugins.lllagel.weiche;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;

public class WeicheUtil {

	private final static String METHOD_GET = "GET";
	private final static String METHOD_POST = "POST";

	/**
	 * 计算签名信息
	 * 
	 * @param METHOD
	 *            api 请求方法（GET POST）
	 * @param API
	 *            api。例 /v2/cities
	 * @param DATE
	 *            日期格式：Fri, 12 Jul 2013 09:13:05 GMT (RFC 1123)格式
	 * @param CONTENT_LENGTH
	 *            请求content长度，如果为GET，则长度为0
	 * @param APP_SECRET
	 * @return
	 */
	public String signatrue(String method, String api, String date,
			int content_length, String app_secret) {
		return DataUtils.md5(method + "&" + api + "&" + date + "&"
				+ content_length + "&" + DataUtils.md5(app_secret));

	}

	/**
	 * 
	 * @param method
	 * @return
	 */
	public String sendRequest(String data) {
		System.out.println(data);
		String host = "api.buding.cn";
		String api = "/v2/jobs";
		String date = DateUtils.fromatRFC1123(new Date());
		String secret = PropertiesUtil.getProperty("lllegal_app_secret");
		String key = PropertiesUtil.getProperty("lllegal_appkey");
		String auth = signatrue(METHOD_POST, api, date, data.length(), secret);
		String result = HttpClientUtil.httpPostData(host, api,
				key + ":" + auth, data);
		System.out.println(result);
		return "";
	}

	/**
	 * 构造请求参数
	 * 
	 * @param carNumber
	 *            车牌号 必填
	 * @param engineNumber
	 *            发动机号 必填
	 * @param bodyNumber
	 *            车架号 ka
	 * @param cityPinyin
	 * @return
	 */
	public static String setData(String carNumber, String engineNumber,
			String bodyNumber, String cityPinyin) {
		String json = "{\"license_plate_num\":\"" + carNumber + "\",";
		json += "\"engine_num\":\"" + engineNumber + "\",";
		json += "\"body_num\":\"" + bodyNumber + "\",";
		json += "\"city_pinyin\":\"" + cityPinyin + "\"}";
		return json;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		new WeicheUtil()
				.sendRequest(setData("\u4eacn0n181",
						"c393388", "", "beijing"));
	}

}
