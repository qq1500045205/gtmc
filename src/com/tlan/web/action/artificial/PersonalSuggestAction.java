package com.tlan.web.action.artificial;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.tlan.admin.module.ModuleAction;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUserStatus;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.http.HttpUtils;
import com.tlan.wxkit.vo.send.WxSendMsg;
import com.tlan.wxkit.vo.send.WxSendTextMsg;

@SuppressWarnings("serial")
public class PersonalSuggestAction extends BaseAction {
	
	private static Logger log = Logger.getLogger(PersonalSuggestAction.class);
	
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxUserStatus> wxUserStatusService;
	
	private static String sendAPI = PropertiesUtil.getProperty("send_customer_message_ur");
	private String url = "http://" + PropertiesUtil.getProperty(ParamString.BASE_IP)+ "/gtmc_wx/"
                    +ModuleAction.ADMIN_HTML+"suggest-page.jsp";
	
	private String userName;
	private String sex;
	private String userTel;
	private String buycarflag;
	private String purposecarmodel;
	private String buycarmodel;
	private String buydlrcode;
	private String areaCode;
	private String openid;
	private String gzhHash;
	
	private String suggestDec;
	private String suggestInfo;
	private String suggestCode;
	private String suggestInDate;
	private String suggestPostDate;
	
	public String getCheckHas() {
		System.out.println(this.getOpenid());
		System.out.println(this.getGzhHash());
		Map<String,String> params=new HashMap<String,String>();
		String CRUrl= PropertiesUtil.getProperty("send_cr_url");
		
		//判断是否已留资
		params.clear();
		params.put("service","findUser");
        params.put("userid", this.getOpenid());
        params.put("hashcode", this.getGzhHash());
        String findUser=sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
		log.info("findUser:"+findUser);
        JSONObject jo=JSONObject.fromObject(findUser);
        String ifFlag=jo.getString("ifFlag");
        System.out.println(ifFlag);
		if("1".equals(ifFlag)){
			System.out.println("已留资!");
			setMap(false, "添加失败", null);
		}else{
			System.out.println("未留资!");
			setMap(true, "处理成功", null);
		}
		
		return this.SUCCESS;
	}

	public String getInfo() {
		System.out.println(this.getOpenid());
		System.out.println(this.getGzhHash());
		System.out.println(this.getUserName());
		System.out.println(this.getSex());
		System.out.println(this.getBuycarflag());
		String resp="";
		Map<String,String> params=new HashMap<String,String>();
		String CRUrl= PropertiesUtil.getProperty("send_cr_url");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
		log.info("-----------------USERTEL----------:" + this.getUserTel());
		log.info("-----------------ADVISETOPIC----------:" + this.getSuggetDec());
		log.info("-----------------ADVISECONTENT----------:" + this.getSuggestInfo());
		log.info("-----------------ENDTIME----------:" + df.format(new Date()));
		log.info("----########----STARTTIME-----########33-----:" + this.getSuggestInDate());
		//用户留资
		params.clear();
		params.put("service","advice");
		params.put("USERID",this.getOpenid());
		params.put("USERSEX",this.getSex());
		params.put("USERNAME",this.getUserName());
		params.put("USERTEL",this.getUserTel());
		params.put("BUYCARFLAG", this.getBuycarflag());
		params.put("hashcode", this.getGzhHash());
		params.put("PURPOSECARMODEL", this.getPurposecarmodel());
		params.put("BUYCARMODEL",this.getBuycarmodel());
		params.put("BUYDLRCODE", this.getBuydlrcode());
		//add lyn
		params.put("ADVISETOPIC", this.getSuggetDec());
		params.put("ADVISECONTENT", this.getSuggestInfo());
		params.put("STARTTIME", this.getSuggestInDate());
		params.put("ENDTIME", df.format(new Date()));
		//end lyn
		log.info("-----------------CRUrl----------:" + CRUrl);
		resp=sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
		JSONObject jo=JSONObject.fromObject(resp);
		log.info("-----------------joson----------:" + jo);
        String ifFlag=jo.getString("ifFlag");
        log.info("-----------------ifFlag----------:" + ifFlag);
		if("1".equals(ifFlag)){
			System.out.println("处理成功");
//			List<WxUserStatus> userStatuses = wxUserStatusService.getAll(
//					WxUserStatus.class, "openId", this.getOpenid());
//			log.info("wxUserStatuses size:" + userStatuses.size());
			
//			if (userStatuses.size() > 0) {
//				WxUserStatus wxUserStatus = userStatuses.get(0);
//				//if(wxUserStatus.getStatus() == WxUserStatus.crUnReply){
//				log.info("变更会话状态crUnReply："+WxUserStatus.crUnReply);
//				log.info("变更会话状态crReplied："+WxUserStatus.crReplied);
//				log.info("变更会话状态crSession："+WxUserStatus.crSession);
//				
//				wxUserStatus.setStatus(WxUserStatus.crSession);
//				wxUserStatusService.update(wxUserStatus);
//				//}
//			}
			setMap(true, "添加成功", null);
		}else{
			System.out.println("处理失败");
			setMap(false, "添加失败", null);
		}
		
		return this.SUCCESS;
	}
	
	public String getArea(){
		//StringBuilder str = new StringBuilder();
		Map<String,String> params=new HashMap<String,String>();
		String CRUrl= PropertiesUtil.getProperty("send_info_cr_url");
		String resp= "";
		//获取地区
		params.clear();
		params.put("service","area");
		System.out.println(CRUrl);
		try{
			resp=sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
			System.out.print(resp);
			//str.append("{'result':"+resp+"}");
			JSONObject jo=JSONObject.fromObject(resp);
			JSONArray result=(JSONArray)jo.get("result");
			setMap(true, result, result.size());
			System.out.print("=============获取数据成功=============");
		}catch(Exception e){
			setMap(false, "获取数据失败", null);
			System.out.print(e);
		}
		return this.SUCCESS;
	}
	
	public String getCarOrg(){
		//StringBuilder str = new StringBuilder();
		Map<String,String> params=new HashMap<String,String>();
		String CRUrl= PropertiesUtil.getProperty("send_info_cr_url");
		String resp= "";
		//获取购买店
		params.clear();
		params.put("service","carOrg");
		params.put("code",this.getAreaCode());
		System.out.println(CRUrl);
		try{
			resp=sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
			System.out.print(resp);
			//str.append("{'result':"+resp+"}");
			JSONObject jo=JSONObject.fromObject(resp);
			JSONArray result=(JSONArray)jo.get("result");
			setMap(true, result, result.size());
		}catch(Exception e){
			setMap(false, "获取数据失败", null);
		}
		return this.SUCCESS;
	}
	
	public String getCarModel(){
		//StringBuilder str = new StringBuilder();
		Map<String,String> params=new HashMap<String,String>();
		String CRUrl= PropertiesUtil.getProperty("send_info_cr_url");
		String resp= "";
		//获取车型
		params.clear();
		params.put("service","carModel");
		params.put("type", this.getBuycarflag());
		System.out.println(CRUrl);
		try{
			resp=sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
			System.out.print(resp);
			//str.append("{'result':"+resp+"}");
			JSONObject jo=JSONObject.fromObject(resp);
			JSONArray result=(JSONArray)jo.get("result");
			setMap(true, result, result.size());
		}catch(Exception e){
			setMap(false, "获取数据失败", null);
		}
		return this.SUCCESS;
	}
	
	public void getSendMsg(){
		String token=null;
		String reqURL = null;
		String strMessage =  "对不起，您提交的联系信息失败，请重新提交。{0}";
		
		WxGongzhonghao wxgzh = gzhService.get(WxGongzhonghao.class, "gzhHash", this.getGzhHash());
		String appid = wxgzh.getAppid();
		String appsecret = wxgzh.getAppsecret();
		String openid = this.getOpenid();
		
		System.out.print(appid);
		System.out.print(appsecret);
		System.out.print(openid);
		
		token = HttpUtils.getToken(appid, appsecret);
		reqURL = MessageFormat.format(sendAPI, token);
		
		String msgJson = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		String linkstr = "<a href=\\\""+url+"?wxopenid=%s&hashcode=%s\\\">点击进入</a>";
		linkstr = String.format(linkstr,openid,this.getGzhHash());
		String msgTxt="";
		
		System.out.print(linkstr);
		
		msgTxt = MessageFormat.format(strMessage,linkstr);
		msgJson = String.format(msgJson, openid,msgTxt);
		HttpClientUtil.sendPostRequestByJava(reqURL,msgJson);	
	}
	
	public void sendHelp(){
		String token=null;
		String reqURL = null;
		String message = "您好，您的宝贵建议已经提交，谢谢！";
		System.out.print(message);
		System.out.println(this.getOpenid());
		System.out.println(this.getGzhHash());
		
		WxGongzhonghao wxgzh = gzhService.get(WxGongzhonghao.class, "gzhHash", this.getGzhHash());
		String appid = wxgzh.getAppid();
		String appsecret = wxgzh.getAppsecret();
		String openid = this.getOpenid();
		
		System.out.print(appid);
		System.out.print(appsecret);
		
		token = HttpUtils.getToken(appid, appsecret);
		System.out.print(token);
		System.out.print("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		reqURL = MessageFormat.format(sendAPI, token);
		
		String msgJson = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		String msgTxt="";
		
		msgTxt = MessageFormat.format(message,"");
		msgJson = String.format(msgJson, openid,msgTxt);
		HttpClientUtil.sendPostRequestByJava(reqURL,msgJson);
	}
	
	public  String sendPostRequest(String reqURL,
  			Map<String, String> params, String encodeCharset,
  			String decodeCharset) {
  		String responseContent = null;
  		HttpClient httpClient = new DefaultHttpClient();

  		HttpPost httpPost = new HttpPost(reqURL);
  		List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
  		for (Map.Entry<String, String> entry : params.entrySet()) {
  			formParams.add(new BasicNameValuePair(entry.getKey(), entry
  					.getValue()));
  		}
  		try {
  			httpPost.setEntity(new UrlEncodedFormEntity(formParams,
  					encodeCharset == null ? "UTF-8" : encodeCharset));

  			HttpResponse response = httpClient.execute(httpPost);
  			HttpEntity entity = response.getEntity();
  			if (null != entity) {
  				responseContent = EntityUtils.toString(entity,
  						decodeCharset == null ? "UTF-8" : decodeCharset);
  				EntityUtils.consume(entity);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			httpClient.getConnectionManager().shutdown();
  		}
  		return responseContent;
  	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getBuycarflag() {
		return buycarflag;
	}

	public void setBuycarflag(String buycarflag) {
		this.buycarflag = buycarflag;
	}

	public String getPurposecarmodel() {
		return purposecarmodel;
	}

	public void setPurposecarmodel(String purposecarmodel) {
		this.purposecarmodel = purposecarmodel;
	}

	public String getBuycarmodel() {
		return buycarmodel;
	}

	public void setBuycarmodel(String buycarmodel) {
		this.buycarmodel = buycarmodel;
	}

	public String getBuydlrcode() {
		return buydlrcode;
	}

	public void setBuydlrcode(String buydlrcode) {
		this.buydlrcode = buydlrcode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}
	
	public String getSuggetDec() {
		return suggestDec;
	}

	public void setSuggestDec(String suggestDec) {
		this.suggestDec = suggestDec;
	}
	
	public String getSuggestInfo() {
		return suggestInfo;
	}
	
	public void setSuggestInfo(String suggestInfo) {
		this.suggestInfo = suggestInfo;
	}
	
	public String getSuggestCode() {
		return suggestCode;
	}
	
	public void setSuggestCode(String suggestCode) {
		this.suggestCode = suggestCode;
	}
	
	public String getSuggestInDate() {
		return suggestInDate;
	}
	
	public void setSuggestInDate(String suggestInDate) {
		this.suggestInDate = suggestInDate;
	}
	
	public String getSuggestPostDate() {
		return suggestPostDate;
	}
	
	public void setSuggestPostDate(String suggestPostDate) {
		this.suggestPostDate = suggestPostDate;
	}
}
