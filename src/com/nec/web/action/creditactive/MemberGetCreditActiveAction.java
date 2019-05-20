package com.nec.web.action.creditactive;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.session.Session;
import org.apache.struts2.ServletActionContext;
import org.jsoup.helper.DataUtil;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.creditactive.WxMemberCreditActiveF;
import com.tlan.common.model.creditactive.WxMemberCreditEnrollF;
import com.tlan.common.model.creditactive.WxMemberCreditManageF;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.wxkit.utils.OpenIdUtil;

/**
 * 积分商城管理
 * 
 * @author yiwei
 * 
 */

public class MemberGetCreditActiveAction extends BaseAction implements Preparable,
		ModelDriven<WxMemberCreditActiveF> {
	
	private static final long serialVersionUID = -4807016706157727150L;

	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditActiveF> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxUser> userService;
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditManageF> wxMemberCreditManageFService;
	@Resource(name = "baseService")
    private IBaseService<WxMemberCreditEnrollF>   wxMemberCreditEnrollFService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao>   wxGongZhongHaoService;
	private WxMemberCreditActiveF active;
private String hash;
public String getHash() {
	return hash;
}

public void setHash(String hash) {
	this.hash = hash;
}

public String getGuid() {
	return guid;
}

public void setGuid(String guid) {
	this.guid = guid;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

private String guid;
private String code;
	/**
	 *获取积分活动
	 * @author 郭红圣

	 * @version 2.0 2014年6月12日 下午2:02:22  
	 */
	public String getIntegralActivites() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String creditGuid=request.getParameter("guid");
//			String openid=request.getParameter("openid");
			WxMemberCreditActiveF act = this.activeService.get(
					WxMemberCreditActiveF.class, "member_credit_guid",creditGuid);
			JSONObject obj = new JSONObject();
			obj.put("title", act.getActName());
			obj.put("startTime", act.getStartOn());
			obj.put("endTime", act.getEndOn());   
			obj.put("picSrc", act.getActImage() );
			obj.put("actGuid", act.getMemberCreditGuid());
			obj.put("desc", act.getActDesc());
			obj.put("content", act.getActContent());
			obj.put("actType",act.getActType());        //act_Type  0:不用报名 ； 1：报名
			obj.put("actCredit",act.getActCredit());        
//			if(DataUtils.isNullOrEmpty(openid)){
//				 HttpSession session=request.getSession();
//				 session.setAttribute("openid",null);
//			}
			setMap(true, "获取数据成功", obj);
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 *积分活动验证
	 * @author 郭红圣

	 * @version 2.0 2014年6月12日 下午2:02:22  
	 */
   public String getIntegralActivitesCheck(){
	   
	   try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String openid=request.getParameter("openid");
		
	//		String hash=request.getParameter("hash");
			System.out.println("hash:"+this.getHash());
			String endTime = request.getParameter("endTime");
			String actType=request.getParameter("actType");
			String actGuid = request.getParameter("actGuid");
			System.out.println("actGuid:"+actGuid);
	//		String code= request.getParameter("code");	
//			System.out.println("code:"+this.getCode());
//			 HttpSession session=request.getSession();
//			 if(DataUtils.isNullOrEmpty(openid)){
//			 if(session.getAttribute("openid")!=null){
//					System.out.println("获取Session中的openid");
//					openid=session.getAttribute("openid").toString();
//			 }else{
//					System.out.println("请求新的openid");
//					WxGongzhonghao wxghz=this.wxGongZhongHaoService.get(WxGongzhonghao.class, "gzh_hash", this.getHash());
//					 openid= OpenIdUtil.getOpenid(wxghz.getAppid(), wxghz.getAppsecret(), this.getCode());
//					 session.setAttribute("openid",openid);
//			 }
//		 }
//			System.out.println("openid:"+openid);
//		if(openid==null){
//			setMap(false, "网络超时，请重新扫描！", null);
//			return SUCCESS;
//		}
			JSONObject obj = new JSONObject();  
//			 obj.put("openid", openid);
			System.out.println("进行活动判定！");
			List<WxUser> list_user = this.userService.getAll(WxUser.class, new String[]{"open_id","gzh_hash"}, new Object[]{openid,hash});
			List<WxMemberCreditActiveF> list_f=this.activeService.getAll(WxMemberCreditActiveF.class, new String[]{"member_credit_guid"}, new Object[]{actGuid});
			//0：您还不是会员 ； 1：活动已过期 	；2：您已参加过活动	  3：跳转到报名页面 ； 4：直接获得积分	;5:该活动还未发布											
			System.out.println(list_user.size());
			if(list_user.get(0).getMemberLevel()==0){
						obj.put("actStatue", 0);   
					}else{
						obj.put("userGuid", list_user.get(0).getUserGuid());
						obj.put("userName", list_user.get(0).getUserName());
						if(list_f.get(0).getStatus()==0){
							 obj.put("actStatue", 5);     
						}else{
							DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
							Date actEndTime=fmt.parse(endTime); 
							Date	actStartTime = fmt.parse(list_f.get(0).getStartOn()); 
							String now=fmt.format(new Date()); 
							Date Today=fmt.parse(now); 
							 if(Today.after(actEndTime)||list_f.get(0).getDelFlag()==1){   
								 obj.put("actStatue", 1);     
							 } else if(actStartTime.after(Today)) {
								 obj.put("actStatue", 5);  
							 }else {
								  String user_guid=list_user.get(0).getUserGuid(); 			
									List<WxMemberCreditManageF> list_wxMemberCreditManageF = this.wxMemberCreditManageFService.getAll(
											WxMemberCreditManageF.class,  new String[]{"member_guid","act_guid"}, new Object[]{user_guid,actGuid}); //判定是否参加过活动
									if(list_wxMemberCreditManageF.size()>0){
									 obj.put("actStatue", 2);            
								 }else{
									 if(actType.equals("1")){
										 obj.put("actStatue", 3);
									 }else{
										 obj.put("actStatue", 4);
									 }
								 }
					 	}
					 }
			}
			System.out.println("对应事件："+obj+"://0：您还不是会员 ； 1：活动已过期 	；2：您已参加过活动3：跳转到报名页面 ； 4：直接获得积分	;5:该活动还未发布");
			setMap(true, "获取数据成功", obj);
		}catch (Exception e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
	   	return SUCCESS;
   }
	
   /**
   *获得积分，不需要注册

   * @author 郭红圣

   * @version 2.0 2014年6月13日 下午1:03:51  
   */
   public String getIntegral(){
	   HttpServletRequest request =ServletActionContext.getRequest();
	   try{
	   String userGuid = request.getParameter("userGuid");
	   String actGuid = request.getParameter("guid");
	   String hash = request.getParameter("hash");
	   String actCredit= request.getParameter("actCredit");
	   String gq_hash = "-2042484612";
	   String gzhHash=hash;
	   String memberActGuid = DataUtils.getUUID();
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   String time= df.format(new Date());// new Date()为获取当前系统时间
	   WxMemberCreditManageF mcf=new WxMemberCreditManageF();
	   mcf.setActGuid(actGuid);
	   mcf.setMemberActGuid(memberActGuid);
	   mcf.setMemberGuid(userGuid);
	   mcf.setCreatedOn(time);
	   mcf.setCreatedBy(userGuid);
	   if(hash.equals(gq_hash)){
		   gzhHash = hash;
	   }else{
		   List<String> tel_list = getAllGQ_USER();
		   WxUser gzUser = userService.get(WxUser.class, "userGuid", userGuid);
		   if(tel_list.contains(gzUser.getUserTel())){
			   List<WxUser> gqUser = userService.getAll(WxUser.class, new String[]{"gzhHash","userTel"},new Object[]{gq_hash,gzUser.getUserTel()});  
			   for(int i=0;i<gqUser.size();i++){
			   String gquserGuid = gqUser.get(i).getUserGuid();
			   String gqmemberActGuid = DataUtils.getUUID();
			   WxMemberCreditManageF gqmcf=new WxMemberCreditManageF();
			   
			   System.out.println("开始保存广丰会员积分!");
			   WxUser user_gq =userService.get(WxUser.class, "userGuid", gquserGuid);
		       System.out.println("------------下面开始保存广汽积分,原积分为"+user_gq.getCountCredit());
		       user_gq.setCountCredit(Integer.parseInt(user_gq.getCountCredit()+actCredit));
		       userService.update(user_gq);
			   
			   gqmcf.setActGuid(actGuid);
			   gqmcf.setMemberActGuid(gqmemberActGuid);
			   gqmcf.setMemberGuid(gquserGuid);
			   gqmcf.setCreatedOn(time);
			   gqmcf.setCreatedBy(gquserGuid);
			   gqmcf.setGzhHash(gzhHash);
			   wxMemberCreditManageFService.save(gqmcf);
			   }
		   }
	   }
	   mcf.setGzhHash(gzhHash);
       wxMemberCreditManageFService.save(mcf);
       System.out.println(userGuid+"----userGuid");
       WxUser user =userService.get(WxUser.class, "userGuid", userGuid);
       System.out.println("------------下面开始保存积分,原积分为"+user.getCountCredit());
       int count1 = user.getCountCredit() + Integer.parseInt(actCredit);
       user.setCountCredit(count1);
       userService.update(user);
	   setMap(true, "成功添加", null);
	   }catch(Exception e){
		   setMap(false, "系统繁忙，请稍后再试！", null);
	   };
	   return SUCCESS;
   }
	
   /**
   *获得积分，需要报名，查询报名选项
   * @author 郭红圣

   * @version 2.0 2014年6月13日 下午2:03:51  
   */
   public String getIntegralActivitesByLogin(){
	   HttpServletRequest request =ServletActionContext.getRequest();
	   String actGuid = request.getParameter("guid");
	   JSONArray ary = new JSONArray();
	    List<WxMemberCreditEnrollF> list = this.wxMemberCreditEnrollFService.getAll(
	    		WxMemberCreditEnrollF.class, new String[]{"member_credit_guid"}, new Object[]{actGuid},new String[]{"="} , "item_sort ASC,created_on ASC");
	    for (int i=0;i<list.size();i++){
			JSONObject obj = JSONObject.fromObject(list.get(i));
			ary.add(obj);
		}
	    setMap(true, "成功添加", ary);
	    return SUCCESS;
   }
   
   /**
   *获得积分，需要报名，报名选项
   * @author 郭红圣

   * @version 2.0 2014年6月13日 下午4:03:51  
   */
   public String saveScoreByLogin(){
	   HttpServletRequest request =ServletActionContext.getRequest();
	   try{
	   String paramName = request.getParameter("paramName");
	   System.out.println("----乱码没有----"+paramName);
	   String actGuid = request.getParameter("guid");
	   String userGuid = request.getParameter("userGuid");
	   String hash = request.getParameter("hash");
	   String actCredit= request.getParameter("actCredit");
	   String gq_hash = "-2042484612";
	   String gzhHash=hash;
	   String[] T = paramName.split("&");
	   String memberActGuid = DataUtils.getUUID();
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   String time= df.format(new Date());// new Date()为获取当前系统时间
	   WxMemberCreditManageF mcf=new WxMemberCreditManageF();
	   mcf.setMemberActGuid(memberActGuid);
	   mcf.setMemberGuid(userGuid);
	   mcf.setActGuid(actGuid);
	   mcf.setCreatedOn(time);
	   mcf.setCreatedBy(userGuid);
	   for(int i=0;i<T.length;i++){
		   if(i==0){
			   mcf.setT1(T[i].split("tealActName_0")[1].replaceAll("=", ""));
		   }
		   if(i==1){
			   mcf.setT2(T[i].split("tealActName_1")[1].replaceAll("=", ""));
			   }
		   if(i==2){
			   mcf.setT3(T[i].split("tealActName_2")[1].replaceAll("=", ""));
			   }
		   if(i==3){
			   mcf.setT4(T[i].split("tealActName_3")[1].replaceAll("=", ""));
			   }
		   if(i==4){
			   mcf.setT5(T[i].split("tealActName_4")[1].replaceAll("=", ""));
			   }
		   if(i==5){
			   mcf.setT6(T[i].split("tealActName_5")[1].replaceAll("=", ""));
			   }
		   if(i==6){
			   mcf.setT7(T[i].split("tealActName_6")[1].replaceAll("=", ""));
			   }
		   if(i==7){
			   mcf.setT8(T[i].split("tealActName_7")[1].replaceAll("=", ""));
			   }
		   if(i==8){
			   mcf.setT9(T[i].split("tealActName_8")[1].replaceAll("=", ""));
			   }
		   if(i==9){
			   mcf.setT10(T[i].split("tealActName_9")[1].replaceAll("=", ""));
			   }
	   }
	   if(hash.equals(gq_hash)){
		   gzhHash = hash;
	   }else{
		   List<String> tel_list = getAllGQ_USER();
		   WxUser gzUser = userService.get(WxUser.class, "userGuid", userGuid);
		   if(tel_list.contains(gzUser.getUserTel())){
			   List<WxUser> gqUser = userService.getAll(WxUser.class, new String[]{"gzhHash","userTel"},new Object[]{gq_hash,gzUser.getUserTel()});  
			   for(int j =0;j<gqUser.size();j++){
				   WxMemberCreditManageF gqmcf=new WxMemberCreditManageF();
				   String gquserGuid = gqUser.get(j).getUserGuid();
				   System.out.println("开始保存广丰会员积分!");
				   
				   WxUser user_gq =userService.get(WxUser.class, "userGuid", gquserGuid);
			       System.out.println("------------下面开始保存广汽积分,原积分为"+user_gq.getCountCredit());
			       user_gq.setCountCredit(Integer.parseInt(user_gq.getCountCredit()+actCredit));
			       userService.update(user_gq);
				   
				   String gqmemberActGuid = DataUtils.getUUID();
				   gqmcf.setActGuid(actGuid);
				   gqmcf.setMemberActGuid(gqmemberActGuid);
				   gqmcf.setMemberGuid(gquserGuid);
				   gqmcf.setCreatedOn(time);
				   gqmcf.setCreatedBy(gquserGuid);
				   gqmcf.setGzhHash(gzhHash);
				   for(int i=0;i<T.length;i++){
					   if(i==0){
						   gqmcf.setT1(T[i].split("tealActName_0")[1].replaceAll("=", ""));
					   }
					   if(i==1){
						   gqmcf.setT2(T[i].split("tealActName_1")[1].replaceAll("=", ""));
						   }
					   if(i==2){
						   gqmcf.setT3(T[i].split("tealActName_2")[1].replaceAll("=", ""));
						   }
					   if(i==3){
						   gqmcf.setT4(T[i].split("tealActName_3")[1].replaceAll("=", ""));
						   }
					   if(i==4){
						   gqmcf.setT5(T[i].split("tealActName_4")[1].replaceAll("=", ""));
						   }
					   if(i==5){
						   gqmcf.setT6(T[i].split("tealActName_5")[1].replaceAll("=", ""));
						   }
					   if(i==6){
						   gqmcf.setT7(T[i].split("tealActName_6")[1].replaceAll("=", ""));
						   }
					   if(i==7){
						   gqmcf.setT8(T[i].split("tealActName_7")[1].replaceAll("=", ""));
						   }
					   if(i==8){
						   gqmcf.setT9(T[i].split("tealActName_8")[1].replaceAll("=", ""));
						   }
					   if(i==9){
						   gqmcf.setT10(T[i].split("tealActName_9")[1].replaceAll("=", ""));
						   }
				   }
				   wxMemberCreditManageFService.save(gqmcf);
			   }
		   }
	   }
	   mcf.setGzhHash(gzhHash);
	   wxMemberCreditManageFService.save(mcf);
	   System.out.println(userGuid+"----userGuid");
	   WxUser user =userService.get(WxUser.class, "userGuid", userGuid);
       System.out.println("------------下面开始保存积分,原积分为"+user.getCountCredit());
       int count1 = user.getCountCredit() + Integer.parseInt(actCredit);
       user.setCountCredit(count1);
       userService.update(user);
	   		setMap(true, "成功添加", null);
	   }catch(Exception e){
		   setMap(false, "系统繁忙，请稍后再试！", null);
	   };
	   return SUCCESS;
   }
   
   public List<String> getAllGQ_USER(){
	   String hash = "-2042484612";
	   List<WxUser> gq_user = userService.getAll(WxUser.class, "gzhHash", hash);
	   List<String>   tel_list = new ArrayList<>();
	   for(int i=0;i<gq_user.size();i++){
		   tel_list.add(gq_user.get(i).getUserTel());
	   }
	   return tel_list;
   }
   
   
	public WxMemberCreditActiveF getModel() {
		// TODO Auto-generated method stub
		return active;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == active) {
			active = new WxMemberCreditActiveF();
		}
	}

		
}
