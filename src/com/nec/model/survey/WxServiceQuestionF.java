package com.nec.model.survey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * WxServiceQuestionF entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_service_question_f")
public class WxServiceQuestionF implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 7467002397069380767L;
	
	private String id;
	private String openid;
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	private String companycode;
	private String companyname;
	private String customername;
	private String vinno;
	private String mobileno;
	private String model;
	private String buydate;
	private String lastmaintenancedate;
	private String orderno;
	private int q1;
	private int q2a;
	private int q2b;
	private int q2c;
	private int q2d;
	private int q2e;
	private int q3a11;
	private int q3a12;
	private int q3a13;
	private int q3a14;
	private int q3a15;
	private int q3a16;
	private String q3a17;
	private String q3areason;
	private int q3b11;
	private int q3b12;
	private int q3b13;
	private int q3b14;
	private int q3b15;
	private int q3b16;
	private String q3b17;
	private String q3breason;
	private int q3c11;
	private int q3c12;
	private int q3c13;
	private int q3c14;
	private int q3c15;
	private String q3c16;
	private String q3creason;
	private int q3d11;
	private int q3d12;
	private int q3d13;
	private int q3d14;
	private int q3d15;
	private String q3d16;
	private String q3dreason;
	private int q3e11;
	private int q3e12;
	private int q3e13;
	private int q3e14;
	private int q3e15;
	private String q3e16;
	private String q3ereason;
	private int q41;
	private int q42;
	private int q43;
	private int q44;
	private int q45;
	private String q46;
	private String remark;
	private String dealerenabled;
	private String replydate;
	private String deleteflag;
	private String creuser;
	private String credate;
	private String updateuser;
	private String updatetime;
	private String q3a17d;
	private String q3b17d;
	private String q3c16d;
	private String q3d16d;
	private String q3e16d;
	private String q46d;
	

	// Constructors
	
	public void setQ46d(String q46d) {
		this.q46d = q46d;
	}

	public void setQ3a17d(String q3a17d) {
		this.q3a17d = q3a17d;
	}

	public void setQ3b17d(String q3b17d) {
		this.q3b17d = q3b17d;
	}

	public void setQ3c16d(String q3c16d) {
		this.q3c16d = q3c16d;
	}

	public void setQ3d16d(String q3d16d) {
		this.q3d16d = q3d16d;
	}

	public void setQ3e16d(String q3e16d) {
		this.q3e16d = q3e16d;
	}
	
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Transient
	public String getQ3a17d(){
		if (this.q3a17!=null && !"0".equals(this.q3a17) && !"99".equals(this.q3a17)){
			return "1";
		}else{
			return this.q3a17;
		}
	}
	@Transient
	public String getQ3b17d(){
		if (this.q3b17!=null && !"0".equals(this.q3b17) && !"99".equals(this.q3b17)){
			return "1";
		}else{
			return this.q3b17;
		}
	}
	@Transient
	public String getQ3c16d(){
		if (this.q3c16!=null && !"0".equals(this.q3c16) && !"99".equals(this.q3c16)){
			return "1";
		}else{
			return this.q3c16;
		}
	}
	@Transient
	public String getQ3d16d(){
		if (this.q3d16!=null && !"0".equals(this.q3d16) && !"99".equals(this.q3d16)){
			return "1";
		}else{
			return this.q3d16;
		}
	}
	@Transient
	public String getQ3e16d(){
		if (this.q3e16!=null && !"0".equals(this.q3e16) && !"99".equals(this.q3e16)){
			return "1";
		}else{
			return this.q3e16;
		}
	}
	
	@Transient
	public String getQ46d(){
		if (this.q46!=null && !"0".equals(this.q46) && !"99".equals(this.q46)){
			return "1";
		}else{
			return this.q46;
		}
	}
	
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

	public String getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	/** default constructor */
	public WxServiceQuestionF() {
		
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	
	public void setData(){
		if (this.q2a==1){
			this.q3b11 = 99;
			this.q3b12 = 99;
			this.q3b13 = 99;
			this.q3b14 = 99;
			this.q3b15 = 99;
			this.q3b16 = 99;
			this.q3b17 = "99";
			this.q3breason = "99";
			this.q3c11 = 99;
			this.q3c12 = 99;
			this.q3c13 = 99;
			this.q3c14 = 99;
			this.q3c15 = 99;
			this.q3c16 = "99";
			this.q3creason = "99";
			this.q3d11 = 99;
			this.q3d12 = 99;
			this.q3d13 = 99;
			this.q3d14 = 99;
			this.q3d15 = 99;
			this.q3d16 = "99";
			this.q3dreason = "99";
			this.q3e11 = 99;
			this.q3e12 = 99;
			this.q3e13 = 99;
			this.q3e14 = 99;
			this.q3e15 = 99;
			this.q3e16 = "99";
			this.q3ereason = "99";
		}else if (this.q2b==1){
			this.q3a11 = 99;
			this.q3a12 = 99;
			this.q3a13 = 99;
			this.q3a14 = 99;
			this.q3a15 = 99;
			this.q3a16 = 99;
			this.q3a17 = "99";
			this.q3areason = "99";
			this.q3c11 = 99;
			this.q3c12 = 99;
			this.q3c13 = 99;
			this.q3c14 = 99;
			this.q3c15 = 99;
			this.q3c16 = "99";
			this.q3creason = "99";
			this.q3d11 = 99;
			this.q3d12 = 99;
			this.q3d13 = 99;
			this.q3d14 = 99;
			this.q3d15 = 99;
			this.q3d16 = "99";
			this.q3dreason = "99";
			this.q3e11 = 99;
			this.q3e12 = 99;
			this.q3e13 = 99;
			this.q3e14 = 99;
			this.q3e15 = 99;
			this.q3e16 = "99";
			this.q3ereason = "99";
		}else if (this.q2c==1){
			this.q3a11 = 99;
			this.q3a12 = 99;
			this.q3a13 = 99;
			this.q3a14 = 99;
			this.q3a15 = 99;
			this.q3a16 = 99;
			this.q3a17 = "99";
			this.q3areason = "99";
			this.q3b11 = 99;
			this.q3b12 = 99;
			this.q3b13 = 99;
			this.q3b14 = 99;
			this.q3b15 = 99;
			this.q3b16 = 99;
			this.q3b17 = "99";
			this.q3breason = "99";
			this.q3d11 = 99;
			this.q3d12 = 99;
			this.q3d13 = 99;
			this.q3d14 = 99;
			this.q3d15 = 99;
			this.q3d16 = "99";
			this.q3dreason = "99";
			this.q3e11 = 99;
			this.q3e12 = 99;
			this.q3e13 = 99;
			this.q3e14 = 99;
			this.q3e15 = 99;
			this.q3e16 = "99";
			this.q3ereason = "99";
		}else if (this.q2d==1){
			this.q3a11 = 99;
			this.q3a12 = 99;
			this.q3a13 = 99;
			this.q3a14 = 99;
			this.q3a15 = 99;
			this.q3a16 = 99;
			this.q3a17 = "99";
			this.q3areason = "99";
			this.q3b11 = 99;
			this.q3b12 = 99;
			this.q3b13 = 99;
			this.q3b14 = 99;
			this.q3b15 = 99;
			this.q3b16 = 99;
			this.q3b17 = "99";
			this.q3breason = "99";
			this.q3c11 = 99;
			this.q3c12 = 99;
			this.q3c13 = 99;
			this.q3c14 = 99;
			this.q3c15 = 99;
			this.q3c16 = "99";
			this.q3creason = "99";
			this.q3e11 = 99;
			this.q3e12 = 99;
			this.q3e13 = 99;
			this.q3e14 = 99;
			this.q3e15 = 99;
			this.q3e16 = "99";
			this.q3ereason = "99";
		}else if (this.q2e==1){
			this.q3a11 = 99;
			this.q3a12 = 99;
			this.q3a13 = 99;
			this.q3a14 = 99;
			this.q3a15 = 99;
			this.q3a16 = 99;
			this.q3a17 = "99";
			this.q3areason = "99";
			this.q3b11 = 99;
			this.q3b12 = 99;
			this.q3b13 = 99;
			this.q3b14 = 99;
			this.q3b15 = 99;
			this.q3b16 = 99;
			this.q3b17 = "99";
			this.q3breason = "99";
			this.q3c11 = 99;
			this.q3c12 = 99;
			this.q3c13 = 99;
			this.q3c14 = 99;
			this.q3c15 = 99;
			this.q3c16 = "99";
			this.q3creason = "99";
			this.q3d11 = 99;
			this.q3d12 = 99;
			this.q3d13 = 99;
			this.q3d14 = 99;
			this.q3d15 = 99;
			this.q3d16 = "99";
			this.q3dreason = "99";
		}
	}

	/** minimal constructor */
	public WxServiceQuestionF(String id, String companycode,
			String companyname, String customername, String vinno,
			String mobileno, String lastmaintenancedate, int q1,
			String dealerenabled, String replydate) {
		this.id = id;
		this.companycode = companycode;
		this.companyname = companyname;
		this.customername = customername;
		this.vinno = vinno;
		this.mobileno = mobileno;
		this.lastmaintenancedate = lastmaintenancedate;
		this.q1 = q1;
		this.dealerenabled = dealerenabled;
		this.replydate = replydate;
	}

	/** full constructor */
	public WxServiceQuestionF(String id, String companycode,
			String companyname, String customername, String vinno,
			String mobileno, String lastmaintenancedate, int q1,
			int q2a, int q2b, int q2c, int q2d, int q2e,
			int q3a11, int q3a12, int q3a13, int q3a14,
			int q3a15, int q3a16, String q3a17, String q3areason,
			int q3b11, int q3b12, int q3b13, int q3b14,
			int q3b15, int q3b16, String q3b17, String q3breason,
			int q3c11, int q3c12, int q3c13, int q3c14,
			int q3c15, String q3c16, String q3creason, int q3d11,
			int q3d12, int q3d13, int q3d14, int q3d15,
			String q3d16, String q3dreason, int q3e11, int q3e12,
			int q3e13, int q3e14, int q3e15, String q3e16,
			String q3ereason, int q41, int q42, int q43,
			int q44, int q45, String q46, String remark,
			String dealerenabled, String replydate) {
		this.id = id;
		this.companycode = companycode;
		this.companyname = companyname;
		this.customername = customername;
		this.vinno = vinno;
		this.mobileno = mobileno;
		this.lastmaintenancedate = lastmaintenancedate;
		this.q1 = q1;
		this.q2a = q2a;
		this.q2b = q2b;
		this.q2c = q2c;
		this.q2d = q2d;
		this.q2e = q2e;
		this.q3a11 = q3a11;
		this.q3a12 = q3a12;
		this.q3a13 = q3a13;
		this.q3a14 = q3a14;
		this.q3a15 = q3a15;
		this.q3a16 = q3a16;
		this.q3a17 = q3a17;
		this.q3areason = q3areason;
		this.q3b11 = q3b11;
		this.q3b12 = q3b12;
		this.q3b13 = q3b13;
		this.q3b14 = q3b14;
		this.q3b15 = q3b15;
		this.q3b16 = q3b16;
		this.q3b17 = q3b17;
		this.q3breason = q3breason;
		this.q3c11 = q3c11;
		this.q3c12 = q3c12;
		this.q3c13 = q3c13;
		this.q3c14 = q3c14;
		this.q3c15 = q3c15;
		this.q3c16 = q3c16;
		this.q3creason = q3creason;
		this.q3d11 = q3d11;
		this.q3d12 = q3d12;
		this.q3d13 = q3d13;
		this.q3d14 = q3d14;
		this.q3d15 = q3d15;
		this.q3d16 = q3d16;
		this.q3dreason = q3dreason;
		this.q3e11 = q3e11;
		this.q3e12 = q3e12;
		this.q3e13 = q3e13;
		this.q3e14 = q3e14;
		this.q3e15 = q3e15;
		this.q3e16 = q3e16;
		this.q3ereason = q3ereason;
		this.q41 = q41;
		this.q42 = q42;
		this.q43 = q43;
		this.q44 = q44;
		this.q45 = q45;
		this.q46 = q46;
		this.remark = remark;
		this.dealerenabled = dealerenabled;
		this.replydate = replydate;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "assigned")
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "companycode", nullable = false, length = 5)
	public String getCompanycode() {
		return this.companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	@Column(name = "companyname", nullable = false, length = 80)
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "customername", nullable = false, length = 20)
	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "vinno", nullable = false, length = 20)
	public String getVinno() {
		return this.vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

	@Column(name = "mobileno", nullable = false, length = 11)
	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	@Column(name = "lastmaintenancedate", nullable = false, length = 10)
	public String getLastmaintenancedate() {
		return this.lastmaintenancedate;
	}

	public void setLastmaintenancedate(String lastmaintenancedate) {
		this.lastmaintenancedate = lastmaintenancedate;
	}

	@Column(name = "q1", nullable = false)
	public int getQ1() {
		return this.q1;
	}

	public void setQ1(int q1) {
		this.q1 = q1;
	}

	@Column(name = "q2a")
	public int getQ2a() {
		return this.q2a;
	}

	public void setQ2a(int q2a) {
		this.q2a = q2a;
	}

	@Column(name = "q2b")
	public int getQ2b() {
		return this.q2b;
	}

	public void setQ2b(int q2b) {
		this.q2b = q2b;
	}

	@Column(name = "q2c")
	public int getQ2c() {
		return this.q2c;
	}

	public void setQ2c(int q2c) {
		this.q2c = q2c;
	}

	@Column(name = "q2d")
	public int getQ2d() {
		return this.q2d;
	}

	public void setQ2d(int q2d) {
		this.q2d = q2d;
	}

	@Column(name = "q2e")
	public int getQ2e() {
		return this.q2e;
	}

	public void setQ2e(int q2e) {
		this.q2e = q2e;
	}

	@Column(name = "q3a11")
	public int getQ3a11() {
		return this.q3a11;
	}

	public void setQ3a11(int q3a11) {
		this.q3a11 = q3a11;
	}

	@Column(name = "q3a12")
	public int getQ3a12() {
		return this.q3a12;
	}

	public void setQ3a12(int q3a12) {
		this.q3a12 = q3a12;
	}

	@Column(name = "q3a13")
	public int getQ3a13() {
		return this.q3a13;
	}

	public void setQ3a13(int q3a13) {
		this.q3a13 = q3a13;
	}

	@Column(name = "q3a14")
	public int getQ3a14() {
		return this.q3a14;
	}

	public void setQ3a14(int q3a14) {
		this.q3a14 = q3a14;
	}

	@Column(name = "q3a15")
	public int getQ3a15() {
		return this.q3a15;
	}

	public void setQ3a15(int q3a15) {
		this.q3a15 = q3a15;
	}

	@Column(name = "q3a16")
	public int getQ3a16() {
		return this.q3a16;
	}

	public void setQ3a16(int q3a16) {
		this.q3a16 = q3a16;
	}

	@Column(name = "q3a17")
	public String getQ3a17() {
		return this.q3a17;
	}

	public void setQ3a17(String q3a17) {
		this.q3a17 = q3a17;
	}

	@Column(name = "q3areason", length = 200)
	public String getQ3areason() {
		return this.q3areason;
	}

	public void setQ3areason(String q3areason) {
		this.q3areason = q3areason;
	}

	@Column(name = "q3b11")
	public int getQ3b11() {
		return this.q3b11;
	}

	public void setQ3b11(int q3b11) {
		this.q3b11 = q3b11;
	}

	@Column(name = "q3b12")
	public int getQ3b12() {
		return this.q3b12;
	}

	public void setQ3b12(int q3b12) {
		this.q3b12 = q3b12;
	}

	@Column(name = "q3b13")
	public int getQ3b13() {
		return this.q3b13;
	}

	public void setQ3b13(int q3b13) {
		this.q3b13 = q3b13;
	}

	@Column(name = "q3b14")
	public int getQ3b14() {
		return this.q3b14;
	}

	public void setQ3b14(int q3b14) {
		this.q3b14 = q3b14;
	}

	@Column(name = "q3b15")
	public int getQ3b15() {
		return this.q3b15;
	}

	public void setQ3b15(int q3b15) {
		this.q3b15 = q3b15;
	}

	@Column(name = "q3b16")
	public int getQ3b16() {
		return this.q3b16;
	}

	public void setQ3b16(int q3b16) {
		this.q3b16 = q3b16;
	}

	@Column(name = "q3b17")
	public String getQ3b17() {
		return this.q3b17;
	}

	public void setQ3b17(String q3b17) {
		this.q3b17 = q3b17;
	}

	@Column(name = "q3breason", length = 200)
	public String getQ3breason() {
		return this.q3breason;
	}

	public void setQ3breason(String q3breason) {
		this.q3breason = q3breason;
	}

	@Column(name = "q3c11")
	public int getQ3c11() {
		return this.q3c11;
	}

	public void setQ3c11(int q3c11) {
		this.q3c11 = q3c11;
	}

	@Column(name = "q3c12")
	public int getQ3c12() {
		return this.q3c12;
	}

	public void setQ3c12(int q3c12) {
		this.q3c12 = q3c12;
	}

	@Column(name = "q3c13")
	public int getQ3c13() {
		return this.q3c13;
	}

	public void setQ3c13(int q3c13) {
		this.q3c13 = q3c13;
	}

	@Column(name = "q3c14")
	public int getQ3c14() {
		return this.q3c14;
	}

	public void setQ3c14(int q3c14) {
		this.q3c14 = q3c14;
	}

	@Column(name = "q3c15")
	public int getQ3c15() {
		return this.q3c15;
	}

	public void setQ3c15(int q3c15) {
		this.q3c15 = q3c15;
	}

	@Column(name = "q3c16")
	public String getQ3c16() {
		return this.q3c16;
	}

	public void setQ3c16(String q3c16) {
		this.q3c16 = q3c16;
	}

	@Column(name = "q3creason", length = 200)
	public String getQ3creason() {
		return this.q3creason;
	}

	public void setQ3creason(String q3creason) {
		this.q3creason = q3creason;
	}

	@Column(name = "q3d11")
	public int getQ3d11() {
		return this.q3d11;
	}

	public void setQ3d11(int q3d11) {
		this.q3d11 = q3d11;
	}

	@Column(name = "q3d12")
	public int getQ3d12() {
		return this.q3d12;
	}

	public void setQ3d12(int q3d12) {
		this.q3d12 = q3d12;
	}

	@Column(name = "q3d13")
	public int getQ3d13() {
		return this.q3d13;
	}

	public void setQ3d13(int q3d13) {
		this.q3d13 = q3d13;
	}

	@Column(name = "q3d14")
	public int getQ3d14() {
		return this.q3d14;
	}

	public void setQ3d14(int q3d14) {
		this.q3d14 = q3d14;
	}

	@Column(name = "q3d15")
	public int getQ3d15() {
		return this.q3d15;
	}

	public void setQ3d15(int q3d15) {
		this.q3d15 = q3d15;
	}

	@Column(name = "q3d16")
	public String getQ3d16() {
		return this.q3d16;
	}

	public void setQ3d16(String q3d16) {
		this.q3d16 = q3d16;
	}

	@Column(name = "q3dreason", length = 200)
	public String getQ3dreason() {
		return this.q3dreason;
	}

	public void setQ3dreason(String q3dreason) {
		this.q3dreason = q3dreason;
	}

	@Column(name = "q3e11")
	public int getQ3e11() {
		return this.q3e11;
	}

	public void setQ3e11(int q3e11) {
		this.q3e11 = q3e11;
	}

	@Column(name = "q3e12")
	public int getQ3e12() {
		return this.q3e12;
	}

	public void setQ3e12(int q3e12) {
		this.q3e12 = q3e12;
	}

	@Column(name = "q3e13")
	public int getQ3e13() {
		return this.q3e13;
	}

	public void setQ3e13(int q3e13) {
		this.q3e13 = q3e13;
	}

	@Column(name = "q3e14")
	public int getQ3e14() {
		return this.q3e14;
	}

	public void setQ3e14(int q3e14) {
		this.q3e14 = q3e14;
	}

	@Column(name = "q3e15")
	public int getQ3e15() {
		return this.q3e15;
	}

	public void setQ3e15(int q3e15) {
		this.q3e15 = q3e15;
	}

	@Column(name = "q3e16")
	public String getQ3e16() {
		return this.q3e16;
	}

	public void setQ3e16(String q3e16) {
		this.q3e16 = q3e16;
	}

	@Column(name = "q3ereason", length = 200)
	public String getQ3ereason() {
		return this.q3ereason;
	}

	public void setQ3ereason(String q3ereason) {
		this.q3ereason = q3ereason;
	}

	@Column(name = "q41")
	public int getQ41() {
		return this.q41;
	}

	public void setQ41(int q41) {
		this.q41 = q41;
	}

	@Column(name = "q42")
	public int getQ42() {
		return this.q42;
	}

	public void setQ42(int q42) {
		this.q42 = q42;
	}

	@Column(name = "q43")
	public int getQ43() {
		return this.q43;
	}

	public void setQ43(int q43) {
		this.q43 = q43;
	}

	@Column(name = "q44")
	public int getQ44() {
		return this.q44;
	}

	public void setQ44(int q44) {
		this.q44 = q44;
	}

	@Column(name = "q45")
	public int getQ45() {
		return this.q45;
	}

	public void setQ45(int q45) {
		this.q45 = q45;
	}

	@Column(name = "q46")
	public String getQ46() {
		return this.q46;
	}

	public void setQ46(String q46) {
		this.q46 = q46;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "dealerenabled", nullable = false, length = 1)
	public String getDealerenabled() {
		return this.dealerenabled;
	}

	public void setDealerenabled(String dealerenabled) {
		this.dealerenabled = dealerenabled;
	}

	@Column(name = "replydate", nullable = false, length = 10)
	public String getReplydate() {
		return this.replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

}