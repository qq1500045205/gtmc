package com.nec.model.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxSurverSendHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_surver_send_history")
public class WxSurverSendHistory implements java.io.Serializable {

	// Fields

	private String orderno;
	private String openId;
	private String companycode;
	private String wxcompanyname;
	private String appid;
	private String appsecret;
	private String customername;
	private String customerphone;
	private String vinno;
	private String model;
	private String platenumber;
	private String repairdate;
	private String buydate;
	private String credate;
	private String creuser;
	private String updatetime;
	private String updateuser;
	private String senddate;

	// Constructors

	/** default constructor */
	public WxSurverSendHistory() {
	}

	/** full constructor */
	public WxSurverSendHistory(String orderno, String openId,
			String companycode, String wxcompanyname, String customername,
			String customerphone, String vinno, String model,
			String platenumber, String repairdate, String buydate,
			String credate, String creuser, String updatetime,
			String updateuser, String senddate) {
		this.orderno = orderno;
		this.openId = openId;
		this.companycode = companycode;
		this.wxcompanyname = wxcompanyname;
		this.customername = customername;
		this.customerphone = customerphone;
		this.vinno = vinno;
		this.model = model;
		this.platenumber = platenumber;
		this.repairdate = repairdate;
		this.buydate = buydate;
		this.credate = credate;
		this.creuser = creuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.senddate = senddate;
	}

	// Property accessors
	@Id
	@Column(name = "orderno", unique = true, nullable = false, length = 12)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "open_id", nullable = false, length = 50)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "companycode", nullable = false, length = 5)
	public String getCompanycode() {
		return this.companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	@Column(name = "wxcompanyname", nullable = false, length = 80)
	public String getWxcompanyname() {
		return this.wxcompanyname;
	}

	public void setWxcompanyname(String wxcompanyname) {
		this.wxcompanyname = wxcompanyname;
	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	@Column(name = "customername", nullable = false, length = 100)
	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "customerphone", nullable = false, length = 25)
	public String getCustomerphone() {
		return this.customerphone;
	}

	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}

	@Column(name = "vinno", nullable = false, length = 20)
	public String getVinno() {
		return this.vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

	@Column(name = "model", nullable = false, length = 20)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "platenumber", nullable = false, length = 32)
	public String getPlatenumber() {
		return this.platenumber;
	}

	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}

	@Column(name = "repairdate", nullable = false, length = 10)
	public String getRepairdate() {
		return this.repairdate;
	}

	public void setRepairdate(String repairdate) {
		this.repairdate = repairdate;
	}

	@Column(name = "buydate", nullable = false, length = 10)
	public String getBuydate() {
		return this.buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	@Column(name = "credate", nullable = false, length = 20)
	public String getCredate() {
		return this.credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	@Column(name = "creuser", nullable = false, length = 20)
	public String getCreuser() {
		return this.creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	@Column(name = "updatetime", nullable = false, length = 20)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "updateuser", nullable = false, length = 20)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Column(name = "senddate", nullable = false, length = 20)
	public String getSenddate() {
		return this.senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

}