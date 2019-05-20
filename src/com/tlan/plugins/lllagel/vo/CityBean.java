package com.tlan.plugins.lllagel.vo;

/**
 * 支持城市
 * 
 * @author magenm 2014-1-8
 * 
 */
public class CityBean {
	private String name;
	private String pinyin;
	private String id;
	private String province_name;
	private String province_pinyin;
	private String engine_length;
	private String body_length;
	private String need_captcha;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getProvince_pinyin() {
		return province_pinyin;
	}

	public void setProvince_pinyin(String province_pinyin) {
		this.province_pinyin = province_pinyin;
	}

	public String getEngine_length() {
		return engine_length;
	}

	public void setEngine_length(String engine_length) {
		this.engine_length = engine_length;
	}

	public String getBody_length() {
		return body_length;
	}

	public void setBody_length(String body_length) {
		this.body_length = body_length;
	}

	public String getNeed_captcha() {
		return need_captcha;
	}

	public void setNeed_captcha(String need_captcha) {
		this.need_captcha = need_captcha;
	}

}
