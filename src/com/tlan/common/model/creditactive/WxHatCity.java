package com.tlan.common.model.creditactive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wx_hat_city")
public class WxHatCity implements Serializable {

	private static final long serialVersionUID = -6250009779145225611L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	@Column
	private String  cityID;
	@Column
	private String city;
	@Column
	private String father;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	
	
}