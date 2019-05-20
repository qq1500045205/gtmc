package com.nec.model.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wx_service_question_m")
public class WxServiceQuestionM implements java.io.Serializable{

	private static final long serialVersionUID = -8905317945919397097L;

	private String sequence;
	private String qtext;
	private String creuser;
	private String credate;
	private String updateuser;
	private String updatetime;
	
	public String getCreuser() {
		return creuser;
	}
	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}
	public String getCredate() {
		return credate;
	}
	public void setCredate(String credate) {
		this.credate = credate;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	@Id
	@GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "assigned")
	@Column(name = "sequence", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Column(name = "qtext", unique = true, nullable = false, insertable = true, updatable = true, length = 500)
	public String getQtext() {
		return qtext;
	}
	public void setQtext(String qtext) {
		this.qtext = qtext;
	}
}
