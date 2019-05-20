package com.tlan.common.model.creditactive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wx_hat_area")
public class WxHatArea implements Serializable {

	private static final long serialVersionUID = -6250009779145225611L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	@Column
	private String  areaID;
	@Column
	private String area;
	@Column
	private String father;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
}