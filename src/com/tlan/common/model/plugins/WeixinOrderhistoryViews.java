package com.tlan.common.model.plugins;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(WeixinOrderhistoryViewsKey.class)
@Table(name = "weixin_orderhistory_views")
public class WeixinOrderhistoryViews implements Serializable {
    private static final long serialVersionUID = 1L;
	private String dealercode;
	private String dealername;
	private String orderno;
	private Date orderdate;
	private Date salesdate;
	private Date financesalesdate;
	private String customername;
	private String tel1;
	private String tel2;
	private String email;
	private String registerno;
	private String vinno;
	private String typicalsrvname;
	private int mileage;
	private int salestotal;
	private int labordiscount;
	private int partsdiscount;
	private String custchargename;
	private int custchargeamount;
	private String warrachargename;
	private int warrachargeamount;
	private String twcuser;
	private String twctel1;
	private String twctel2;
	private Date caracceptdate;

	@Id
	@Column(name = "dealercode", insertable = false, updatable = false, length = 5)
	public String getDealercode() {
		return dealercode;
	}

	public void setDealercode(String dealercode) {
		this.dealercode = dealercode;
	}

	@Id
	@Column(name = "orderno", insertable = false, updatable = false, length = 12)
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "dealername", length = 48)
	public String getDealername() {
		return dealername;
	}

	public void setDealername(String dealername) {
		this.dealername = dealername;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSalesdate() {
		return salesdate;
	}

	public void setSalesdate(Date salesdate) {
		this.salesdate = salesdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFinancesalesdate() {
		return financesalesdate;
	}

	public void setFinancesalesdate(Date financesalesdate) {
		this.financesalesdate = financesalesdate;
	}

	@Column(name = "customername", length = 40)
	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "tel1", length = 15)
	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	@Column(name = "tel2", length = 15)
	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "registerno", length = 16)
	public String getRegisterno() {
		return registerno;
	}

	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}

	@Column(name = "vinno", length = 20)
	public String getVinno() {
		return vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

	@Column(name = "typicalsrvname", length = 40)
	public String getTypicalsrvname() {
		return typicalsrvname;
	}

	public void setTypicalsrvname(String typicalsrvname) {
		this.typicalsrvname = typicalsrvname;
	}

	@Column(name = "mileage")
	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Column(name = "salestotal")
	public int getSalestotal() {
		return salestotal;
	}

	public void setSalestotal(int salestotal) {
		this.salestotal = salestotal;
	}

	@Column(name = "labordiscount")
	public int getLabordiscount() {
		return labordiscount;
	}

	public void setLabordiscount(int labordiscount) {
		this.labordiscount = labordiscount;
	}

	@Column(name = "partsdiscount")
	public int getPartsdiscount() {
		return partsdiscount;
	}

	public void setPartsdiscount(int partsdiscount) {
		this.partsdiscount = partsdiscount;
	}

	@Column(name = "custchargename", length = 40)
	public String getCustchargename() {
		return custchargename;
	}

	public void setCustchargename(String custchargename) {
		this.custchargename = custchargename;
	}

	@Column(name = "custchargeamount")
	public int getCustchargeamount() {
		return custchargeamount;
	}

	public void setCustchargeamount(int custchargeamount) {
		this.custchargeamount = custchargeamount;
	}

	@Column(name = "warrachargename", length = 40)
	public String getWarrachargename() {
		return warrachargename;
	}

	public void setWarrachargename(String warrachargename) {
		this.warrachargename = warrachargename;
	}

	@Column(name = "warrachargeamount", length = 40)
	public int getWarrachargeamount() {
		return warrachargeamount;
	}

	public void setWarrachargeamount(int warrachargeamount) {
		this.warrachargeamount = warrachargeamount;
	}

	@Column(name = "twcuser", length = 40)
	public String getTwcuser() {
		return twcuser;
	}

	public void setTwcuser(String twcuser) {
		this.twcuser = twcuser;
	}

	@Column(name = "twctel1", length = 15)
	public String getTwctel1() {
		return twctel1;
	}

	public void setTwctel1(String twctel1) {
		this.twctel1 = twctel1;
	}

	@Column(name = "twctel2", length = 15)
	public String getTwctel2() {
		return twctel2;
	}

	public void setTwctel2(String twctel2) {
		this.twctel2 = twctel2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCaracceptdate() {
		return caracceptdate;
	}

	public void setCaracceptdate(Date caracceptdate) {
		this.caracceptdate = caracceptdate;
	}

}
