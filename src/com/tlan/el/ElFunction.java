package com.tlan.el;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

/**
 * 自定义el函数
 * 
 * @author magenm
 * 
 */
public class ElFunction {

	/**
	 * 对字符串进行解码
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String str) throws UnsupportedEncodingException {
		if (DataUtils.isNotNullOrEmpty(str)) {
			return URLDecoder.decode(str,
					PropertiesUtil.getProperty(ParamString.ENCODING));
		}
		return "";
	}

	/**
	 * 获取url
	 * 
	 * @return
	 */
	public static String getBaseUrl(){
		return PropertiesUtil.getProperty(ParamString.API_BASE_URL);
	}
}
