package com.nec.web.action.creditactive;

import static java.net.URLEncoder.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.nec.common.utils.TwoDimensionCode;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.creditactive.WxMemberCreditActiveF;
import com.tlan.common.model.creditactive.WxMemberCreditEnrollF;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.http.HttpUtils;

/**
 * 积分商城管理
 * 
 * @author yiwei
 * 
 */
public class CreditActiveInfoAction extends BaseAction implements Preparable,
		ModelDriven<WxMemberCreditActiveF> {
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditActiveF> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditEnrollF> enrollService;
	
private String[] item;
	public String[] getItem() {
	return item;
}

public void setItem(String[] item) {
	this.item = item;
}
private LoginBean user = obtainLoginBean();
private String gzhs;
	public String getGzhs() {
	return gzhs;
}

public void setGzhs(String gzhs) {
	this.gzhs = gzhs;
}
	private WxMemberCreditActiveF active;
	private String actCreditS;
	public String getActCreditS() {
		return actCreditS;
	}

	public void setActCreditS(String actCreditS) {
		this.actCreditS = actCreditS;
	}
	private String actCreditE;


	public String getActCreditE() {
		return actCreditE;
	}

	public void setActCreditE(String actCreditE) {
		this.actCreditE = actCreditE;
	}

	public String getAllList() {
		try {
			ArrayList<String[]> aList= preSelect();
			String[]condions=aList.get(0);
			String[]values=aList.get(1);
			String[]ch=aList.get(2);
			List<WxMemberCreditActiveF> allList = this.activeService.findPage(WxMemberCreditActiveF.class,this.getStart(), this.getLimit(),
					condions,values,ch,"startOn desc");	;
			long count =	this.activeService.getCount(WxMemberCreditActiveF.class,condions,values,ch);
			this.setMap(null, JSONArray.fromObject(allList),count);
		} catch (Exception e) {
			System.out.print(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;   
	}

public String createQRCode() throws UnsupportedEncodingException{
	String u= PropertiesUtil.getProperty("qrcodeUrl");
	String url = PropertiesUtil.getProperty("code_url");  
	WxGongzhonghao gzh=gzhService.get(WxGongzhonghao.class, "gzhHash",active.getGzhHash());
	if(gzh!=null){
	try {
		url = url.replace("{1}", gzh.getAppid());
		url = url.replace("{2}", URLEncoder.encode(u,PropertiesUtil.getProperty(com.tlan.contrants.ParamString.ENCODING)));
		url = url.replace("{3}", "snsapi_base");
		url = url.replace("{4}",active.getGzhHash()+"_"+active.getMemberCreditGuid()+"");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	}
	System.out.println("二维码跳转地址:"+url);

	TwoDimensionCode.createQrCode(url);	
	return SUCCESS;

}
/**
 * 删除活动
 * 
 * @return
 */
public String delete() {
	if (null != item) {
		for (int i = 0; i < item.length; i++) {
			activeService.update(WxMemberCreditActiveF.class,
					new String[] { "delFlag" }, new Object[] { 1 },
					"memberCreditGuid = '" + item[i] + "'");
		}
		setMap(true, "删除成功", null);
	} else {
		setMap(false, "删除失败（没有数据）", null);
	}
	return this.SUCCESS;
}

/**
 * 发布活动
 * 
 * @return
 */
public String publish() {
	if (null != item) {
		for (int i = 0; i < item.length; i++) {
			List<WxMemberCreditEnrollF> enrollList=enrollService.getAll(WxMemberCreditEnrollF.class,"memberCreditGuid",item[i]);
			WxMemberCreditActiveF act =activeService.get(WxMemberCreditActiveF.class,"memberCreditGuid",item[i]);

			if(act.getActType()==1&&enrollList.size()<1){
				setMap(false, "发布失败,存在未报名项目", null);
				return this.SUCCESS;

			}
			activeService.update(WxMemberCreditActiveF.class, new String[] {
					"status", "publishOn", "publishBy" }, new Object[] { 1,
					DateUtils.currentDatetime(), user.getUserName() },
					"memberCreditGuid = '" + item[i] + "'");
		}
		setMap(true, "发布成功", null);
	} else {
		setMap(false, "发布失败", null);
	}
	return this.SUCCESS;
}

/**
 * 活动下发
 * 
 * @return
 */
public String assign() {
	if (null != gzhs) {
		active = activeService.get(WxMemberCreditActiveF.class, "memberCreditGuid", active.getMemberCreditGuid());
		JSONArray ary = JSONArray.fromObject(gzhs);
		for (Object object : ary) {
			JSONObject obj = JSONObject.fromObject(object);
			
			active.setGzhHash(obj.get("gzhHash").toString());
			active.setModifyBy(user.getUserName());
			active.setModifyOn(DateUtils.currentDatetime());
			active.setStatus(0);
			active.setSource(1);
			active.setMemberCreditGuid(DataUtils.getUUID());
			active.setDealerCode(getDearCode(active.getGzhHash()));
			
			
			//插入二维码相关信息

			int scene=1;
		List list=activeService.hqlQuery("SELECT MAX(act.sceneId) FROM  WxMemberCreditActiveF act WHERE act.gzhHash="+obj.get("gzhHash").toString()+"");
		if(list.get(0)!=null){
			scene=Integer.parseInt(list.get(0).toString())+1;
		}
			List<WxGongzhonghao> wxGongzhonghaos = gzhService.getAll(
					WxGongzhonghao.class, "gzhHash", obj.get("gzhHash").toString());
			if (wxGongzhonghaos.size() > 0) {
				String appid = wxGongzhonghaos.get(0).getAppid();
				String appsecret = wxGongzhonghaos.get(0).getAppsecret();
				String accessToken = HttpUtils.getToken(appid, appsecret);
				if (accessToken != null) {
					String getQrCodeUrl = PropertiesUtil.getProperty(ParamString.QRCODE_URL);	
					getQrCodeUrl = getQrCodeUrl.replace("{1}", accessToken);	// 生成获取二维码的链接地址
					String sendData = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "
							+ scene + "}}}";// 生成二维码的post_data
					String qrResult = HttpClientUtil.sendPostRequest(
							getQrCodeUrl, sendData, false);
					JSONObject jo = JSONObject.fromObject(qrResult);
					if (jo.containsKey("ticket")) {
						String ticket = jo.getString("ticket");
						active.setSceneId(scene);
						active.setTicket(ticket);				
						activeService.save(active);

						setMap(true, "success", null);
					} else {
						setMap(false, "获取二维码ticket失败，请稍后重试或联系管理员！", null);
					}
				} else {
					setMap(false, "获取access_token失败，请稍后重试或联系管理员！", null);
				}
			}
		}
	
		setMap(true, "下发成功", null);
	} else {
		setMap(false, "下发失败", null);
	}
	return this.SUCCESS;
}

	
private ArrayList<String[]> preSelect() throws UnsupportedEncodingException{
	ArrayList<String[]> result=new ArrayList<String[]>();
	
	String actCreditS=this.getActCreditS();
	String actCreditE=this.getActCreditE();
	String actName=URLDecoder.decode(this.active.getActName(),"utf-8");

	 String[] condions=new String[9];
	 String[] values=new String[9];
	 String[] ch=new String[9];
	 condions[0]="delFlag";  
	 values[0]="0";
	 ch[0]="=";  
	 if(actName.equals("")){
		 condions[1]="1";
		 values[1]="1";
		 ch[1]="=";
	 }else{
		 condions[1]="actName";
		 values[1]="%"+actName+"%";
		 ch[1]="like";
	 }
	 if(active.getStartOn()==null||active.getStartOn().trim().equals("")){
		 condions[2]="1";
	 	 values[2]="1";
	 	 ch[2]="=";
	 }else{
		 condions[2]="startOn";
	 	 values[2]=active.getStartOn();
	 	 ch[2]=">=";
	 }
	 if(active.getEndOn()==null||active.getEndOn().trim().equals("")){
		 condions[3]="1";
	 	 values[3]="1";
	 	 ch[3]="=";
	 }else{
		 condions[3]="endOn";
	 	 values[3]=active.getEndOn();
	 	 ch[3]="<=";
	 }
	 if(actCreditS==null||actCreditS.trim().equals("")){
		 condions[4]="1";
	 	 values[4]="1";
	 	 ch[4]="=";
	 }else{
		 condions[4]="actCredit";
	 	 values[4]=actCreditS;
	 	 ch[4]=">=";
	 }
	 if(actCreditE==null||actCreditE.trim().equals("")){
		 condions[5]="1";
	 	 values[5]="1";
	 	 ch[5]="=";
	 }else{
		 condions[5]="actCredit";
	 	 values[5]=actCreditE;
	 	 ch[5]="<=";
	 }
	 if(this.active.getStatus()==null){
		 condions[6]="1";
		 values[6]="1";  
		 ch[6]="=";
	 }else{
		 condions[6]="status";
		 values[6]=this.active.getStatus()+"";
		 ch[6]="=";
	 }
		if(active.getGzhHash().equals("-2042484612")||active.getGzhHash().equals("")){//如果是DIST或者系统管理员则可检索所有的信息	
			 condions[7]="1";
			 values[7]="1";
			 ch[7]="=";

		}else{
			 condions[7]="gzhHash";
			 values[7]=active.getGzhHash();
			 ch[7]="=";

		}
		 if(this.active.getSource()==null){
			 condions[8]="1";
			 values[8]="1";  
			 ch[8]="=";
		 }else{
			 condions[8]="source";
			 values[8]=this.active.getSource()+"";
			 ch[8]="=";
		 }
	 result.add(condions);
	 result.add(values);
	 result.add(ch);
	 return result;
}
public String getDearCode(String gzhHash){
	WxGongzhonghao gzh=gzhService.get(WxGongzhonghao.class, "gzhHash",gzhHash);

	return gzh.getProjectGuid();
}
	@Override
	public WxMemberCreditActiveF getModel() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == active) {
			active = new WxMemberCreditActiveF();
		}
	}

}
