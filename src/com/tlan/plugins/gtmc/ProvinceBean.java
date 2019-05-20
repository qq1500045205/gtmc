package com.tlan.plugins.gtmc;

/**
 * 城市信息封装
 * 
 * @author magenm
 *
 */
public class ProvinceBean {
	private String media_code;
	private String province_name;
	private String province_code;
	private String status;
	private String conduct_type;
	private String create_time;
	private String last_update_time;
	private String remark;

	public String getMedia_code() {
		return media_code;
	}

	public void setMedia_code(String media_code) {
		this.media_code = media_code;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConduct_type() {
		return conduct_type;
	}

	public void setConduct_type(String conduct_type) {
		this.conduct_type = conduct_type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
