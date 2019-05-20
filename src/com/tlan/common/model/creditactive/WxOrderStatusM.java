package com.tlan.common.model.creditactive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单状态表
 * */
@Entity
@Table(name = "wx_order_status_m")
public class WxOrderStatusM implements Serializable{

	private static final long serialVersionUID = -4069535111167169746L;
	
	@Id
	private String 	order_status_guid;
	
	@Column
	private String 	order_status_name;                      //订单状态名称（待审核，已经审核，已经受理，已经发货，发货中，已经签收，拒签）
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
	public String getOrder_status_guid() {
		return order_status_guid;
	}
	public void setOrder_status_guid(String order_status_guid) {
		this.order_status_guid = order_status_guid;
	}
	public String getOrder_status_name() {
		return order_status_name;
	}
	public void setOrder_status_name(String order_status_name) {
		this.order_status_name = order_status_name;
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
