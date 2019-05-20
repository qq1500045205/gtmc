package com.tlan.common.model.creditactive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tlan.common.model.WxUser;


/**
 * 地址管理表
 * */
@Entity
@Table(name = "wx_user_location_f")
public class WxUserLocationF implements Serializable{

	private static final long serialVersionUID = -4495942665803468175L;
	@Id
	private String  user_location_guid;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_guid")
	private WxUser  wxUser;
	@Column
	private String  user_location_name;
	@Column
	private  String user_location_provice;
	@Column
	private String  user_location_city;
	@Column
	private String user_location_district;
	@Column
	private String user_location_street;
	@Column
	private String user_location_phone;
	@Column
	private String 	created_by;
	@Column
	private String  created_on;
	@Column
	private String	modify_by;
	@Column
	private String   modify_on;
	@Column
	private	int    del_flag;
	@Column
	private int 	update_count;
	public String getUser_location_guid() {
		return user_location_guid;
	}
	public void setUser_location_guid(String user_location_guid) {
		this.user_location_guid = user_location_guid;
	}
	public WxUser getWxUser() {
		return wxUser;
	}
	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}
	public String getUser_location_name() {
		return user_location_name;
	}
	public void setUser_location_name(String user_location_name) {
		this.user_location_name = user_location_name;
	}
	public String getUser_location_provice() {
		return user_location_provice;
	}
	public void setUser_location_provice(String user_location_provice) {
		this.user_location_provice = user_location_provice;
	}
	public String getUser_location_city() {
		return user_location_city;
	}
	public void setUser_location_city(String user_location_city) {
		this.user_location_city = user_location_city;
	}
	public String getUser_location_district() {
		return user_location_district;
	}
	public void setUser_location_district(String user_location_district) {
		this.user_location_district = user_location_district;
	}
	public String getUser_location_street() {
		return user_location_street;
	}
	public void setUser_location_street(String user_location_street) {
		this.user_location_street = user_location_street;
	}
	public String getUser_location_phone() {
		return user_location_phone;
	}
	public void setUser_location_phone(String user_location_phone) {
		this.user_location_phone = user_location_phone;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public String getModify_on() {
		return modify_on;
	}
	public void setModify_on(String modify_on) {
		this.modify_on = modify_on;
	}
	public int getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}
	public int getUpdate_count() {
		return update_count;
	}
	public void setUpdate_count(int update_count) {
		this.update_count = update_count;
	}
	
	
}
