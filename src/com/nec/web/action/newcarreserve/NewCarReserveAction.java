package com.nec.web.action.newcarreserve;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable; 
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGcloudProvinceCtiy;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.WxModuleContentNewcarReserve;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.ExportUtil;
import com.tlan.common.utils.PropertiesUtil;


/** 
 * 雷凌预约
 * 
 * @author magenm
 * 
 */
public class NewCarReserveAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentNewcarReserve> {

	@Resource(name = "baseService")
	private IBaseService<WxUser> wxUserService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentNewcarReserve> llrService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> wxProjectService;
	@Resource(name = "baseService")
	private IBaseService<WxGcloudProvinceCtiy> wxGcloudProvinceCtiyService;
	
	private WxModuleContentNewcarReserve llr;
	

	/** 
	 * 雷凌预购
	 * 
	 * @return
	 */
   private String gzhGuid;
		public String getGzhGuid() {
			return gzhGuid;
		}
		public void setGzhGuid(String gzhGuid) {
			this.gzhGuid = gzhGuid;
		}
	private String gzhHash;
	public String getGzhHash() {
		return gzhHash;
	}
	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}
	
	private String dealerCodeS;
	public String getDealerCodeS() {
		return dealerCodeS;
	}
	public void setDealerCodeS(String dealerCodeS) {
		this.dealerCodeS = dealerCodeS;
	}
	public String getDealerCodeE() {
		return dealerCodeE;
	}
	public void setDealerCodeE(String dealerCodeE) {
		this.dealerCodeE = dealerCodeE;
	}
	private String dealerCodeE;

	private String filename;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	private ByteArrayInputStream inputStream;

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	private String startDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private String endDate;
	
	private String dealerAddress;
	public String getDealerAddress() {
		return dealerAddress;
	}
	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}
	
	private String userProvinceCode;
	public String getUserProvinceCode() {
		return userProvinceCode;
	}
	public void setUserProvinceCode(String userProvinceCode) {
		this.userProvinceCode = userProvinceCode;
	}
	
	private String userCityCode;
	public String getUserCityCode() {
		return userCityCode;
	}
	public void setUserCityCode(String userCityCode) {
		this.userCityCode = userCityCode;
	}
	
	private String modleCode;
	public String getModleCode() {
		return modleCode;
	}
	public void setModleCode(String modleCode) {
		this.modleCode = modleCode;
	}
	
	public String save() {
		try {
			
			System.out.print("openid:"+llr.getOpenid());
			System.out.print("gzhHash:"+llr.getGzhHash());
			llr.setDeleteFlag("0");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String rsTime = format.format(new Date());
			String crTime = df.format(new Date());
			Boolean jkRest= false;
			
			llr.setReserveTime(rsTime);
			llr.setCreateTime(crTime);
			llr.setdealerAddress(this.getDealerAddress());
			
			//数据插入
			llrService.save(llr);
			jkRest = this.sendSalesLeads(llr);
			if(jkRest){
				setMap(true, "添加成功", null);
				System.out.println("===传递线索数据至G-Cloud端成功===");
			}else{
				setMap(false, "传递线索数据至G-Cloud端失败", null);
				System.out.println("===传递线索数据至G-Cloud端失败===");
			}

		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "添加失败", null);
			System.out.println(e);
			throw e;
		}
		return SUCCESS;
	}
	
	/**
	 * 调用G-Cloud端接口
	 * @param ncr
	 * @return
	 */
	public Boolean sendSalesLeads(WxModuleContentNewcarReserve ncr){
		//代理服务器端口
		//String host = BaseConfig.get("host").toString();
		//int port = Integer.parseInt(BaseConfig.get("port").toString());
        
		//接口路径 
		//String url="http://203.88.200.24/GCloudWebService.asmx/SetSalesLeads";
		
		//modify by lyn start
		String url = PropertiesUtil.getProperty("gcloud_leiling_reserver_url");
		String mediaCode = PropertiesUtil.getProperty("media_code_leiling_reserver");
		//String url="http://203.88.200.25/GCloudWebService.asmx/SetSalesLeads";
		//modify by lyn end
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat cl = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String crTime = df.format(new Date());
		String clueCode = cl.format(new Date());
		StringBuffer sb = new StringBuffer();
		boolean res = false;
        
		//拼接传递的参数
		sb.append("[");
		//sb.append("{\"media_code\":\"d5eaac95-57df-4e6f-8709-1321c499073e\",");
		//modify by lyn start
		//sb.append("{\"media_code\":\"b1ad3141-c419-4a89-a35d-15a8a86fdb98\",");
		sb.append("{\"media_code\":\""+mediaCode+"\",");
		//modify by lyn end
		sb.append("\"clue_code\":\""+clueCode+"\",");
		sb.append("\"dealer_code\":\""+ncr.getDealerCode()+"\",");
		sb.append("\"is_publicorder\":2,");
		sb.append("\"clue_type\":2,");
		sb.append("\"modle_code\":\""+this.getModleCode()+"\",");
		sb.append("\"modle_name\":\""+ncr.getCarModel()+"\",");
		sb.append("\"series_code\":\"\",");
		sb.append("\"series_name\":\"\",");
		sb.append("\"province_code\":\""+this.getUserProvinceCode()+"\",");
		sb.append("\"province_name\":\""+ncr.getUserProvince()+"\",");
		sb.append("\"city_code\":\""+this.getUserCityCode()+"\",");
		sb.append("\"city_name\":\""+ncr.getUserCity()+"\",");
		sb.append("\"district_code\":\"\",");
		sb.append("\"district_name\":\"\",");
		sb.append("\"content\":\"\",");
		sb.append("\"create_time\":\""+crTime+"\",");
		sb.append("\"status\":0,");
		sb.append("\"customer_name\":\""+ncr.getUserName()+"\",");
		sb.append("\"gender\":\"\",");
		sb.append("\"phone\":\""+ncr.getUserMobile()+"\",");
		sb.append("\"business_phone\":\"\",");
		sb.append("\"mobile\":\"\",");
		sb.append("\"email\":\"\",");
		sb.append("\"address\":\""+ncr.getdealerAddress()+"\",");
		sb.append("\"contact_method\":\"\",");
		sb.append("\"fax\":\"\",");
		sb.append("\"buy_date\":\"\",");
		sb.append("\"communication_time\":\"\",");
		sb.append("\"pre_fetch_time\":\"\",");
		sb.append("\"pre_payment_method\":\"\",");
		sb.append("\"arrangement\":1,");
		sb.append("\"pre_time\":\"\",");
		sb.append("\"promotion_price\":\"\",");
		sb.append("\"remark\":\"\"");
		sb.append("}");
		sb.append("]");
		
		String jsonString = sb.toString();
		System.out.println("传递线索数据至G-Cloud端:"+jsonString);
		HttpClient client=new HttpClient();
		//client.getHostConfiguration().setProxy(host,port);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		PostMethod ps=new PostMethod(url);
		
		//媒体key值		//modify by lyn start
		//ps.addParameter("media_code", "b1ad3141-c419-4a89-a35d-15a8a86fdb98");
		ps.addParameter("media_code", mediaCode);
		//modify by lyn end
		ps.addParameter("Interface_name", "Interface_salesleads");
		ps.addParameter("jsonstring", jsonString);
        
		try{
		    int httpStatusCode=client.executeMethod(ps);
		    System.out.print("--------------系统返回网络信息----------------:"+httpStatusCode);
		    if(httpStatusCode==HttpStatus.SC_OK){
		    	String result= ps.getResponseBodyAsString();
		    	if(result.indexOf("true")>0){
				    //同步成功
		    		res = true;
				}else{
				    //同步失败
					res = false;
				}
		    }else{
		    	res = false;
		    	System.out.print("网络错误:"+httpStatusCode);
		    }
		}catch(Exception ex){
		    ex.printStackTrace();
		    res = false;
		}finally{
		    ps.releaseConnection();
		}
		return res;
	}
	
	/**
	 * 获取指定的预定信息
	 * 
	 * @return
	 */
	public String getOne() {
		try {
			WxModuleContentNewcarReserve signOne = this.llrService.get(
					WxModuleContentNewcarReserve.class,llr.getId());	
			JSONObject obj = new JSONObject();
			obj.put("userName", signOne.getUserName());
			obj.put("userMobile", signOne.getUserMobile());
			obj.put("carModel", signOne.getCarModel());
			obj.put("userProvince", signOne.getUserProvince());
			obj.put("userCity", signOne.getUserCity());
			obj.put("dealerName", signOne.getDealerName());
			obj.put("dealerAddress", signOne.getdealerAddress());
			obj.put("userMobile", signOne.getUserMobile());
			obj.put("userCarNo", signOne.getDealerName());
		

			setMap(true, "获取数据成功", obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 会员中心获取预购记录
	 * 
	 * @return
	 */
	public String getOwnList() {
		try {
			List<WxModuleContentNewcarReserve> signList = this.llrService.getAll(
					WxModuleContentNewcarReserve.class,"openid",llr.getOpenid());
			JSONArray ary = new JSONArray();
			for (WxModuleContentNewcarReserve sign : signList) {
				JSONObject obj = new JSONObject();
				obj.put("reserveTime", sign.getReserveTime());
				obj.put("userName", sign.getUserName());
				obj.put("id", sign.getId());
				ary.add(obj);
			}
			setMap(true, "获取数据成功", ary);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}
	
/***
 * 获取满足条件的雷凌预购集合
 * @return
 */
	public String getAllList() {
		try {
			ArrayList<String[]> aList= preSelect();
			String[]condions=aList.get(0);
			String[]values=aList.get(1);
			String[]ch=aList.get(2);
			List<WxModuleContentNewcarReserve> allList = this.llrService.findPage(WxModuleContentNewcarReserve.class,this.getStart(), this.getLimit(),
					condions,values,ch,"reserveTime desc");	
			long count = this.llrService.getCount(WxModuleContentNewcarReserve.class,condions,values,ch);	
			
		  
			this.setMap(null, JSONArray.fromObject(allList),count);
		} catch (Exception e) {
			System.out.print(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	public String exportExcel() throws UnsupportedEncodingException {
	
		DateFormat format = new SimpleDateFormat("yyMMdd");
	    String s = format.format(new Date());
		ArrayList<String[]> aList= preSelect();
		String[]condions=aList.get(0);
		String[]values=aList.get(1);
		String[]ch=aList.get(2);
		List<WxModuleContentNewcarReserve> allList = this.llrService.findPage(WxModuleContentNewcarReserve.class,0,999999,condions,values,ch,"createTime desc");	
		
		String[] headers = new String[] { "reserveTime", "userName","userMobile","carModel","carConf","userProvince","userCity","dealerCode","dealerName","remark" };
		String[] headerNames = new String[] { "预定日期", "姓名", "电话", "车型","配置","省份","城市","销售店代码","销售店名称","备注" };
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ExportUtil<WxModuleContentNewcarReserve> data = new ExportUtil<WxModuleContentNewcarReserve>();
		data.export("新车预定管理表", headerNames, headers,null, allList, out, "yyyy-MM-dd");

		filename = "新车预定管理表"+s+".xls";
		inputStream = new ByteArrayInputStream(out.toByteArray());

		return SUCCESS;
	}
	private ArrayList<String[]> preSelect() throws UnsupportedEncodingException{
		ArrayList<String[]> result=new ArrayList<String[]>();
		
		String carModel=URLDecoder.decode(this.llr.getCarModel(),"utf-8");
		String startDate=this.getStartDate().replace("-", "/");
		String endDate=this.getEndDate().replace("-", "/");
		String dealerCodeS=this.getDealerCodeS();
		String dealerCodeE=this.getDealerCodeE();

		 String[] condions=new String[6];
		 String[] values=new String[6];
		 String[] ch=new String[6];
		 condions[0]="deleteFlag";
		 values[0]="0";
		 ch[0]="=";  
		 if(carModel.equals("-")){
			 condions[1]="1";
			 values[1]="1";
			 ch[1]="=";
		 }else{
			 condions[1]="carModel";
			 values[1]=carModel;
			 ch[1]="=";
		 }
		 if(startDate==null||startDate.trim().equals("")){
			 condions[2]="1";
		 	 values[2]="1";
		 	 ch[2]="=";
		 }else{
			 condions[2]="reserveTime";
		 	 values[2]=startDate;
		 	 ch[2]=">=";
		 }
		 if(endDate==null||endDate.trim().equals("")){
			 condions[3]="1";
		 	 values[3]="1";
		 	 ch[3]="=";
		 }else{
			 condions[3]="reserveTime";
		 	 values[3]=endDate;
		 	 ch[3]="<=";

		 }
		 if(dealerCodeS==null||dealerCodeS.trim().equals("")){
			 condions[4]="1";
		 	 values[4]="1";
		 	 ch[4]="=";
		 }else{
			 condions[4]="dealerCode";
		 	 values[4]=dealerCodeS;
		 	 ch[4]=">=";
		 }
		 if(dealerCodeE==null||dealerCodeE.trim().equals("")){
			 condions[5]="1";
		 	 values[5]="1";
		 	 ch[5]="=";
		 }else{
			 condions[5]="dealerCode";
		 	 values[5]=dealerCodeE;
		 	 ch[5]="<=";

		 }
		 result.add(condions);
		 result.add(values);
		 result.add(ch);
		 return result;
	}
	public String getDlrName(){
		WxGcloudProvinceCtiy province=new WxGcloudProvinceCtiy();
		WxGcloudProvinceCtiy city=new WxGcloudProvinceCtiy();
		WxGongzhonghao gzh=gzhService.get(WxGongzhonghao.class, "gzhHash",llr.getGzhHash());
		if(gzh!=null){
			WxProject projectDlr=wxProjectService.get(WxProject.class, "project_guid", gzh.getProjectGuid());
			if(projectDlr!=null){
				province=wxGcloudProvinceCtiyService.get(WxGcloudProvinceCtiy.class, "province_code", projectDlr.getDealerProvinceCode());
				city=wxGcloudProvinceCtiyService.get(WxGcloudProvinceCtiy.class, "city_code", projectDlr.getDealerCityCode());
			}
			JSONObject jo=new JSONObject();  
			jo.put("projectName", projectDlr.getProjectName());
			jo.put("projectGuid", projectDlr.getProjectGuid());
			jo.put("address", projectDlr.getAddress());
			jo.put("provinceCode", province.getProvinceCode());
			jo.put("provinceName", province.getProvinceName());
			jo.put("cityCode", city.getCityCode());
			jo.put("cityName", city.getCityName());
			setMap(true, "获取数据成功", jo);

		}else{
			setMap(false, "获取数据失败", null);

		}
		
		return SUCCESS;
	}
	public String getDearCode(){
		WxGongzhonghao gzh=gzhService.get(WxGongzhonghao.class, "gzhGuid",this.getGzhGuid());
		if(gzh!=null){
			JSONObject jo=new JSONObject();
			jo.put("dealerCode", gzh.getProjectGuid());
			setMap(true, "获取数据成功", jo);

		}else{
			setMap(false, "获取数据失败", null);

		}
		
		return SUCCESS;
	}
	public String getGZHHash(){
		WxUser user=wxUserService.get(WxUser.class, "open_id",llr.getOpenid());
		if(user!=null){
			JSONObject jo=new JSONObject();
			jo.put("GZHHash", user.getGzhHash());
			setMap(true, "获取数据成功", jo);

		}else{
			setMap(false, "获取数据失败", null);

		}
		
		return SUCCESS;
	}


	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == llr) {
			llr = new  WxModuleContentNewcarReserve();
		}
	}

	@Override
	public WxModuleContentNewcarReserve getModel() {
		// TODO Auto-generated method stub
		return llr;
	}	

}
