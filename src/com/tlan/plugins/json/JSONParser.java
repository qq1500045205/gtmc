package com.tlan.plugins.json;

import net.sf.json.JSONObject;

public class JSONParser {

	/**
	 * 将一个对象转换为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String parse(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

}
