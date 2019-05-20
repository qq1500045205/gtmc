package com.tlan.common.model.creditactive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tlan.common.model.WxUser;

/**
 * 订单管理表
 * */
@Entity
@Table(name = "wx_product_order_f")
public class WxProductOrderF implements Serializable {

	private static final long serialVersionUID = 8044635005183580484L;
	
	@Id
	private String  product_order_guid;
	
	@Column
	private String  product_order_num;
	
	@Column
	private String  product_order_date;
	
	@Column
	private Integer	product_count;
	
	@Column
	private Integer	credit_flag;      	  //订单签收标示；0：未签收  ；  1：签收
	
	@Column
	private Integer	take_credit;       //该订单所需积分
	
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_guid")
	private WxUser user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_location_guid")
	private WxUserLocationFTemp	userLocationFTemp;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_guid")
	private WxProductListF productListF;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_status_guid")
	private WxOrderStatusM orderStatusM;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_type_guid")
	private WxProductTypeM	productTypeM;
	
	public String getProduct_order_guid() {
		return product_order_guid;
	}
	public void setProduct_order_guid(String product_order_guid) {
		this.product_order_guid = product_order_guid;
	}
	public String getProduct_order_num() {
		return product_order_num;
	}
	public void setProduct_order_num(String product_order_num) {
		this.product_order_num = product_order_num;
	}
	public String getProduct_order_date() {
		return product_order_date;
	}
	public void setProduct_order_date(String product_order_date) {
		this.product_order_date = product_order_date;
	}
	public WxUserLocationFTemp getUserLocationFTemp() {
		return userLocationFTemp;
	}
	public void setUserLocationFTemp(WxUserLocationFTemp userLocationFTemp) {
		this.userLocationFTemp = userLocationFTemp;
	}
	public WxProductListF getProductListF() {
		return productListF;
	}
	public void setProductListF(WxProductListF productListF) {
		this.productListF = productListF;
	}
	public WxOrderStatusM getOrderStatusM() {
		return orderStatusM;
	}
	public void setOrderStatusM(WxOrderStatusM orderStatusM) {
		this.orderStatusM = orderStatusM;
	}
	public WxProductTypeM getProductTypeM() {
		return productTypeM;
	}
	public void setProductTypeM(WxProductTypeM productTypeM) {
		this.productTypeM = productTypeM;
	}
	public Integer getProduct_count() {
		return product_count;
	}
	public void setProduct_count(Integer product_count) {
		this.product_count = product_count;
	}
	public WxUser getUser() {
		return user;
	}
	public void setUser(WxUser user) {
		this.user = user;
	}
	public Integer getCredit_flag() {
		return credit_flag;
	}
	public void setCredit_flag(Integer credit_flag) {
		this.credit_flag = credit_flag;
	}
	public Integer getTake_credit() {
		return take_credit;
	}
	public void setTake_credit(Integer take_credit) {
		this.take_credit = take_credit;
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
