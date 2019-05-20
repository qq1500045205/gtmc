package com.tlan.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * json处理
 * 
 * @PackageName:com.tlan.common.utils
 * @ClassName:JsonUtils
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月28日 下午12:50:47
 * 
 */
public class JsonUtils {
	/**
	 * 将java对象转换成json字符串,并设定日期格式
	 * 
	 * @param javaObj
	 * @param dataFormat
	 * @return
	 */
	public static JSONObject getJsonString4JSONObject(Object javaObj,
			String dataFormat) {
		return JSONObject.fromObject(javaObj, configJson(dataFormat));
	}

	/**
	 * 将java对象转换成json字符串,并设定日期格式
	 * 
	 * @param javaObj
	 * @param dataFormat
	 * @return
	 */
	public static JSONArray getJsonString4JSONArray(Object javaObj,
			String dataFormat) {
		return JSONArray.fromObject(javaObj, configJson(dataFormat));
	}

	/**
	 * JSON 时间解析器具
	 * 
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));

		return jsonConfig;
	}

	/**
	 * 
	 * @param excludes
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson(String[] excludes, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));

		return jsonConfig;
	}
}

/**
 * json数据处理
 * 
 * @PackageName:com.tlan.common.utils
 * @ClassName:DateJsonValueProcessor
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月28日 下午12:21:09
 * 
 */
class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DateJsonValueProcessor(String datePattern) {

		if (null == datePattern)
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		else
			dateFormat = new SimpleDateFormat(datePattern);

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang
	 * .Object, net.sf.json.JsonConfig)
	 */
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO 自动生成方法存根
		return process(arg0);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang
	 * .String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO 自动生成方法存根
		return process(arg1);
	}

	private Object process(Object value) {
		if (null == value) {
			return "";
		}
		return dateFormat.format((Date) value);
	}
}
