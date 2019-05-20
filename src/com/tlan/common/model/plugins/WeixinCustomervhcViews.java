package com.tlan.common.model.plugins;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@IdClass(WeixinCustomervhcViewsKey.class)
@Table(name = "weixin_customervhc_views")
public class WeixinCustomervhcViews {
	private String dealercode;
	private String registerno;
	private String vinno;
	private String nomineecode;
	private String nomineename;
	private String nomineetel1;
	private String nomineetel2;
	private String nomineemobil1;
	private String nomineemobil2;
	private String buyercode;
	private String buyername;
	private String buyertel1;
	private String buyertel2;
	private String buyermobil1;
	private String buyermobil2;
	private String srvcustomercode;
	private String srvcustomname;
	private String srvcustomtel1;
	private String srvcustomtel2;
	private String srvcustommobil1;
	private String srvcustommobil2;
	private String latestsndcustname;
	private String latestsndcusttel1;
	private String latestsndcusttel2;
	private String vhccode;
	private String vhcname;
	private String colorcode;
	private String color;
	private String exhaust;
	private String grade;
	private String mission;
	private String engine;
	private Date vhcsalesdate;

	@Id
	@Column(name = "dealercode", insertable = false, updatable = false, length = 5)
	public String getDealercode() {
		return dealercode;
	}

	public void setDealercode(String dealercode) {
		this.dealercode = dealercode;
	}

	@Id
	@Column(name = "registerno", insertable = false, updatable = false, length = 32)
	public String getRegisterno() {
		return registerno;
	}

	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}

	@Id
	@Column(name = "vinno", insertable = false, updatable = false, length = 20)
	public String getVinno() {
		return vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

	@Column(name = "nomineecode", length = 10)
	public String getNomineecode() {
		return nomineecode;
	}

	public void setNomineecode(String nomineecode) {
		this.nomineecode = nomineecode;
	}

	@Column(name = "nomineename", length = 80)
	public String getNomineename() {
		return nomineename;
	}

	public void setNomineename(String nomineename) {
		this.nomineename = nomineename;
	}

	@Column(name = "buyercode", length = 10)
	public String getBuyercode() {
		return buyercode;
	}

	public void setBuyercode(String buyercode) {
		this.buyercode = buyercode;
	}

	@Column(name = "buyername", length = 80)
	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	@Column(name = "srvcustomercode", length = 10)
	public String getSrvcustomercode() {
		return srvcustomercode;
	}

	public void setSrvcustomercode(String srvcustomercode) {
		this.srvcustomercode = srvcustomercode;
	}

	@Column(name = "srvcustomname", length = 80)
	public String getSrvcustomname() {
		return srvcustomname;
	}

	public void setSrvcustomname(String srvcustomname) {
		this.srvcustomname = srvcustomname;
	}

	@Column(name = "vhccode", length = 5)
	public String getVhccode() {
		return vhccode;
	}

	public void setVhccode(String vhccode) {
		this.vhccode = vhccode;
	}

	@Column(name = "vhcname", length = 40)
	public String getVhcname() {
		return vhcname;
	}

	public void setVhcname(String vhcname) {
		this.vhcname = vhcname;
	}

	@Column(name = "colorcode", length = 8)
	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	@Column(name = "color", length = 20)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "exhaust", length = 30)
	public String getExhaust() {
		return exhaust;
	}

	public void setExhaust(String exhaust) {
		this.exhaust = exhaust;
	}

	@Column(name = "grade", length = 30)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "mission", length = 30)
	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	@Column(name = "engine", length = 30)
	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getVhcsalesdate() {
		return vhcsalesdate;
	}

	public void setVhcsalesdate(Date vhcsalesdate) {
		this.vhcsalesdate = vhcsalesdate;
	}

	// 新增字段 addbylimengjie
	@Column(name = "nomineetel1", length = 30)
	public String getNomineetel1() {
		return nomineetel1;
	}

	public void setNomineetel1(String nomineetel1) {
		this.nomineetel1 = nomineetel1;
	}

	@Column(name = "nomineetel2", length = 30)
	public String getNomineetel2() {
		return nomineetel2;
	}

	public void setNomineetel2(String nomineetel2) {
		this.nomineetel2 = nomineetel2;
	}

	@Column(name = "nomineemobil1", length = 30)
	public String getNomineemobil1() {
		return nomineemobil1;
	}

	public void setNomineemobil1(String nomineemobil1) {
		this.nomineemobil1 = nomineemobil1;
	}

	@Column(name = "nomineemobil2", length = 30)
	public String getNomineemobil2() {
		return nomineemobil2;
	}

	public void setNomineemobil2(String nomineemobil2) {
		this.nomineemobil2 = nomineemobil2;
	}

	@Column(name = "buyertel1", length = 30)
	public String getBuyertel1() {
		return buyertel1;
	}

	public void setBuyertel1(String buyertel1) {
		this.buyertel1 = buyertel1;
	}

	@Column(name = "buyertel2", length = 30)
	public String getBuyertel2() {
		return buyertel2;
	}

	public void setBuyertel2(String buyertel2) {
		this.buyertel2 = buyertel2;
	}

	@Column(name = "buyermobil1", length = 30)
	public String getBuyermobil1() {
		return buyermobil1;
	}

	public void setBuyermobil1(String buyermobil1) {
		this.buyermobil1 = buyermobil1;
	}

	@Column(name = "buyermobil2", length = 30)
	public String getBuyermobil2() {
		return buyermobil2;
	}

	public void setBuyermobil2(String buyermobil2) {
		this.buyermobil2 = buyermobil2;
	}

	@Column(name = "srvcustomtel1", length = 30)
	public String getSrvcustomtel1() {
		return srvcustomtel1;
	}

	public void setSrvcustomtel1(String srvcustomtel1) {
		this.srvcustomtel1 = srvcustomtel1;
	}

	@Column(name = "srvcustomtel2", length = 30)
	public String getSrvcustomtel2() {
		return srvcustomtel2;
	}

	public void setSrvcustomtel2(String srvcustomtel2) {
		this.srvcustomtel2 = srvcustomtel2;
	}

	@Column(name = "srvcustommobil1", length = 30)
	public String getSrvcustommobil1() {
		return srvcustommobil1;
	}

	public void setSrvcustommobil1(String srvcustommobil1) {
		this.srvcustommobil1 = srvcustommobil1;
	}

	@Column(name = "srvcustommobil2", length = 30)
	public String getSrvcustommobil2() {
		return srvcustommobil2;
	}

	public void setSrvcustommobil2(String srvcustommobil2) {
		this.srvcustommobil2 = srvcustommobil2;
	}

	@Column(name = "latestsndcustname", length = 80)
	public String getLatestsndcustname() {
		return latestsndcustname;
	}

	public void setLatestsndcustname(String latestsndcustname) {
		this.latestsndcustname = latestsndcustname;
	}

	@Column(name = "latestsndcusttel1", length = 30)
	public String getLatestsndcusttel1() {
		return latestsndcusttel1;
	}

	public void setLatestsndcusttel1(String latestsndcusttel1) {
		this.latestsndcusttel1 = latestsndcusttel1;
	}

	@Column(name = "latestsndcusttel2", length = 30)
	public String getLatestsndcusttel2() {
		return latestsndcusttel2;
	}

	public void setLatestsndcusttel2(String latestsndcusttel2) {
		this.latestsndcusttel2 = latestsndcusttel2;
	}

}
