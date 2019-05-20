package com.nec.web.action.survey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.nec.model.survey.*;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUser;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;

public class SurveyAction extends BaseAction implements Preparable,ModelDriven<WxServiceQuestionF> {

	private static final long serialVersionUID = -2211377394914366102L;
	
	@Resource(name = "baseService")
	private IBaseService<WxServiceQuestionF> questionService;
	@Resource(name = "baseService")
	private IBaseService<WxServiceQuestionM> qmService;
	@Resource(name = "baseService")
	private IBaseService<WxUser> userService;
	@Resource(name = "baseService")
	private IBaseService<WxSurverSendHistory> surverSendService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	
	private WxServiceQuestionF question;
	private String formdata;
	private String openId;
	private LoginBean loginBean = obtainLoginBean();
	private ByteArrayInputStream inputStream;
	private String filename;
	private String dlrcodeS;
	private String dlrcodeE;
	private String number;
	private String replySDate;
	private String replyEDate;
	private String clickId;
	private String orderNo;


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getClickId() {
		return clickId;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	private static String[] feilds;
	private static String[] compch;
	private static String[] values;
	

	public String getDlrcodeS() {
		return dlrcodeS;
	}

	public void setDlrcodeS(String dlrcodeS) {
		this.dlrcodeS = dlrcodeS;
	}

	public String getDlrcodeE() {
		return dlrcodeE;
	}

	public void setDlrcodeE(String dlrcodeE) {
		this.dlrcodeE = dlrcodeE;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getReplySDate() {
		return replySDate;
	}

	public void setReplySDate(String replySDate) {
		this.replySDate = replySDate;
	}

	public String getReplyEDate() {
		return replyEDate;
	}

	public void setReplyEDate(String replyEDate) {
		this.replyEDate = replyEDate;
	}

	public String getFilename() {
		try {
			return new String(filename.getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void setFilename(String filename) {
		try {
			this.filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	@Override
	public WxServiceQuestionF getModel() {
		return question;
	}

	@Override
	public void prepare() throws Exception {
		if (null == question) {
			question = new WxServiceQuestionF();
		}
	}

	//获取当前登录用户的角色
	public String getAuth(){
		if (loginBean==null){
			loginBean = obtainLoginBean();
		}
		WxGongzhonghao gzh = gzhService.get(WxGongzhonghao.class, "gzh_guid",loginBean.getGzhGuid());
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("auth", loginBean.getRoleType());
		obj.put("dlrcode", gzh.getProjectGuid());
		ary.add(obj);
		setMap(true, "获取数据成功", ary);
		return SUCCESS;
	}
	
	//获取当前登录用户的角色
	public String checkOrderno(){
		long count = questionService.getCount(WxServiceQuestionF.class, new String[]{"orderno"},
				new String[]{orderNo}, new String[]{"="});
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("count", count);
		ary.add(obj);
		setMap(true, "获取数据成功", ary);
		return SUCCESS;
	}
	
	public String getQuestion(){
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			List<WxServiceQuestionM> questions = qmService.getAll(WxServiceQuestionM.class);
			for (int i=0;i<questions.size();i++){
				WxServiceQuestionM m = questions.get(i);
				obj.put(m.getSequence(), m.getQtext());
			}
			ary.add(obj);
			setMap(true, "获取数据成功", ary);
		}catch (Exception e) {
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}
	
	public String getOneAnswer(){
		JSONArray ary = new JSONArray();
		WxServiceQuestionF q = questionService.get(WxServiceQuestionF.class, "id",clickId);
		JSONObject obj = JSONObject.fromObject(q);
		ary.add(obj);
		setMap(true, "数据获取成功", ary);
		return SUCCESS;
	}
	
	public String getQAList(){
		JSONArray ary = new JSONArray();
		List<WxServiceQuestionF> qs = questionService.getAll(WxServiceQuestionF.class,new String[]{"openid"}, 
				new Object[]{openId});
		for (int i=0;i<qs.size();i++){
			JSONObject obj = JSONObject.fromObject(qs.get(i));
			ary.add(obj);
		}
		setMap(true, "数据获取成功", ary);
		return SUCCESS;
	}
	
	//客户回馈信息
	public String addQuestionInfo(){
		JSONArray data = JSONArray.fromObject(formdata);
		     
		try{
			if (data.size()>0){
				JSONObject j = data.getJSONObject(0);
				WxServiceQuestionF qf =  (WxServiceQuestionF)JSONObject.toBean(j, WxServiceQuestionF.class); 
				List<WxSurverSendHistory> users = surverSendService.getAll(WxSurverSendHistory.class,"open_id",qf.getOpenid());
				SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				WxSurverSendHistory u;
				if (users.size()>0){
					u = users.get(0);
				}else{
					u = new WxSurverSendHistory();
				}
				qf.setId(UUID.randomUUID().toString());
				qf.setCustomername(u.getCustomername());
				qf.setMobileno(u.getCustomerphone());
				qf.setReplydate(format.format(now));
				qf.setLastmaintenancedate(u.getRepairdate());
				qf.setDeleteflag("0");
				qf.setCreuser(u.getCustomername());
				qf.setCredate(format2.format(now));
				qf.setUpdateuser(u.getCustomername());
				qf.setUpdatetime(format2.format(now));
				
				qf.setModel(u.getModel());
				qf.setBuydate(u.getBuydate());
				qf.setCompanycode(u.getCompanycode());
				qf.setCompanyname(u.getWxcompanyname());
				qf.setVinno(u.getVinno());
				
				
				qf.setData();
				questionService.save(qf);
				setMap(true, "添加数据成功", null);
			}
		}catch (Exception e) {
			setMap(true, "添加数据失败", null);
		}

		return SUCCESS;
	}
	
	private void getinputArray(){
		feilds = new String[7];
		values = new String[7];
		compch = new String[7];
		feilds[0] = "deleteflag";
		compch[0] = "=";
		values[0] = "0";
		if (dlrcodeS!=null && !"".equals(dlrcodeS.trim())){
				feilds[1] = "companycode";
				compch[1] = ">=";
				values[1] = dlrcodeS;
		}else{
			feilds[1] = "1";
			compch[1] = "=";
			values[1] ="1";
		}
		
		if (replySDate!=null && !"".equals(replySDate.trim())){
			feilds[2] = "replydate";
			compch[2] = ">=";
			values[2] = replySDate;
		}else{
			feilds[2] = "1";
			compch[2] = "=";
			values[2] = "1";
		}
		
		if (replyEDate!=null && !"".equals(replyEDate.trim())){
			feilds[3] = "replydate";
			compch[3] = "<=";
			values[3] = replyEDate;
		}else{
			feilds[3] = "1";
			compch[3] = "=";
			values[3] = "1";
		}
		
		if (number!=null && !"-".equals(number.trim())){
			feilds[4] = "q1";
			compch[4] = "=";
			values[4] = number;
		}else{
			feilds[4] = "1";
			compch[4] = "=";
			values[4] = "1";
		}
		
		if (loginBean.getRoleType().equals("DISTADMIN") || loginBean.getRoleType().equals("ADMIN")){
			feilds[5] = "1";
			compch[5] = "=";
			values[5] = "1";
		}else{
			feilds[5] = "dealerenabled";
			compch[5] = "=";
			values[5] = "1";
		}
		
		if (dlrcodeE!=null && !"".equals(dlrcodeE.trim())){
			feilds[6] = "companycode";
			compch[6] = "<=";
			values[6] = dlrcodeE;
		}else{
			feilds[6] = "1";
			compch[6] = "=";
			values[6] ="1";
		}
	}
	
	public String getAnswerList(){
		getinputArray();
		List<WxServiceQuestionF> qfs = questionService.findPage(WxServiceQuestionF.class, 
				getStart(), getLimit(),
				feilds,
				values,
				compch, 
				"replydate asc");
		setMap(true, 
			   JSONArray.fromObject(qfs), 
			   questionService.getCount(WxServiceQuestionF.class,feilds, values,compch)
			  );
		return SUCCESS;
	}
	
	public String exportAnswerList() throws IOException{
		long count = questionService.getCount(WxServiceQuestionF.class,feilds, values,compch);
		
		List<WxServiceQuestionF> qfs = questionService.findPage(WxServiceQuestionF.class,
				0,(int)count,
				feilds, values,
				compch, "replydate asc");
		
		if (qfs!=null && qfs.size()>0){
			export(qfs);
		}
		
		return SUCCESS;
	}
	
	private void export(List<WxServiceQuestionF> qfs) throws IOException{
    	InputStream is = new FileInputStream(ServletActionContext.getServletContext().getRealPath("/static-data/temp/servicequestion.xls"));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        HSSFSheet sheet;
        String[] fields;
        HSSFRow row;
        //gtmc
  		if (loginBean.getRoleType().equals("DISTADMIN") || loginBean.getRoleType().equals("ADMIN")){
  			sheet = hssfWorkbook.getSheetAt(1);
  			hssfWorkbook.removeSheetAt(0);
  	        row = sheet.getRow(0);
  	        row.getCell(0).setCellValue("售后微信调查问卷汇总");
  	        fields =new String[] {"Replydate","Companycode","Companyname","Customername","Vinno","Mobileno","model","buydate","Lastmaintenancedate",
  					"q1","q2a","q2b","q2c","q2d","q2e",
  					"q3a11","q3a12","q3a13","q3a14","q3a15","q3a16","q3a17d","q3a17","q3areason",
  					"q3b11","q3b12","q3b13","q3b14","q3b15","q3b16","q3b17d","q3b17","q3breason",
  					"q3c11","q3c12","q3c13","q3c14","q3c15","q3c16d","q3c16","q3creason",
  					"q3d11","q3d12","q3d13","q3d14","q3d15","q3d16d","q3d16","q3dreason",
  					"q3e11","q3e12","q3e13","q3e14","q3e15","q3e16d","q3e16","q3ereason",
  					"q41","q42","q43","q44","q45","q46d","q46","dealerenabled","remark"};
  		}else{//dlr
  			sheet = hssfWorkbook.getSheetAt(0);
  			hssfWorkbook.removeSheetAt(1);
  			 row = sheet.getRow(0);
   	        row.getCell(0).setCellValue(qfs.get(0).getCompanycode()+"店售后微信调查问卷汇总");
  	        fields =new String[] {"Replydate","Companycode","Companyname","Customername","Vinno","Mobileno","Lastmaintenancedate",
  					"q1","q2a","q2b","q2c","q2d","q2e",
  					"q3a11","q3a12","q3a13","q3a14","q3a15","q3a16","q3a17d","q3a17","q3areason",
  					"q3b11","q3b12","q3b13","q3b14","q3b15","q3b16","q3b17d","q3b17","q3breason",
  					"q3c11","q3c12","q3c13","q3c14","q3c15","q3c16d","q3c16","q3creason",
  					"q3d11","q3d12","q3d13","q3d14","q3d15","q3d16d","q3d16","q3dreason",
  					"q3e11","q3e12","q3e13","q3e14","q3e15","q3e16d","q3e16","q3ereason",
  					"q41","q42","q43","q44","q45","q46d","q46","remark"};
  		}
       
        
        int rowindex=3;
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		
        for (int i=0;i<qfs.size();i++){
	    	WxServiceQuestionF qf = qfs.get(i);
	    	row = sheet.getRow(rowindex);
	    	if (row==null){
	    		row = sheet.createRow(rowindex);
	    	}
	    	rowindex++;
	    	
	    	HSSFCell cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue(i+1);
	    	for (int j = 0; j < fields.length; j++) {
	    		cell = row.createCell(j+1);
	    		cell.setCellStyle(style);
	    		sheet.autoSizeColumn(j);
	    		String fieldName = fields[j];
	    		String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
	    		try {
					Class tCls = qf.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(qf, new Object[] {});
					// 判断值的类型后进行强制类型转换
					HSSFRichTextString textValue = null;

					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						textValue = new HSSFRichTextString(
								String.valueOf(dValue));
						cell.setCellValue(textValue);
					} else if (value instanceof String) {
						cell.setCellValue((String) value);
					}

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
				}
	    	}
        }
		hssfWorkbook.write(out);
		SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
		filename =  "售后满意度调查报告"+format.format(new Date())+".xls";
		inputStream = new ByteArrayInputStream(out.toByteArray()); 
	}
}
