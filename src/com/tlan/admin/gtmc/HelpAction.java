package com.tlan.admin.gtmc;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.view.WxUserCarProjectView;

/**
 * 
 * 一键救援对应的处理 
 * @author miya
 * 
 */
public class HelpAction extends BaseAction implements Preparable,
ModelDriven<WxProject> {
	@Resource(name = "baseService")
	private IBaseService<WxProject> webProjectService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxUserCarProjectView> wxUser2shopService;
	private WxProject wxProj;
	
	private String openid; 
	//？
	private String gzhHash;
	private double x; //latitude
	private double y; //longitude
	
	//地球半径
	private double R = 6370996.81; 
	

	// getNear()： 获取周边4S店救援电话 
	
	public String getNear() { 
		JSONArray nearlist = new JSONArray(); 
		
		// 1 取出所有销售店
		List<WxProject> projlist = webProjectService.getAll(WxProject.class);
		 
		//2 计算每个销售店的距离
		double[] distance = new double[projlist.size()]; 
		 for(int i = 0 ; i < projlist.size() ; i++){ 
			 if( (projlist.get(i)==null) || (projlist.get(i).getLatitude() == null) || (projlist.get(i).getLongitude()==null) ){
				 distance[i] = 10000000;
				 continue;
			 }
			 double lat = Float.parseFloat(projlist.get(i).getLatitude());
			 double lon = Float.parseFloat(projlist.get(i).getLongitude()); 
			 //distance[i] = Math.sqrt((x-lat)*(x-lat) + (y-lon) * (y-lon)); 
			 
			 double earthRadius = 3958.75;
			 double dLat = Math.toRadians(lat-x);
			 double dLng = Math.toRadians(lon-y);
			 double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			           Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(lat)) *
			           Math.sin(dLng/2) * Math.sin(dLng/2);
			 double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			 double dist = earthRadius * c;
			
			 System.out.println("index: " + i);
			 
			 distance[i] = (float) (dist * 1609);
			 
			 //distance[i] = R * Math.acos(Math.cos(lat * Math.PI / 180 ) * Math.cos(x * Math.PI / 180) * Math.cos(lon * Math.PI / 180 - y * Math.PI / 180)+Math.sin(lat * Math.PI / 180 ) * Math.sin(x * Math.PI / 180));
		 }
		 
		 //3 根据销售店距离进行排序，取前5拼装JSON 
		 
		//设置检查标记，初始化为0 
		int[] flagchk = new int[projlist.size()]; 
		for(int t = 0; t<projlist.size() ;t++){
			flagchk[t] = 0;
		} 
		
		for(int i = 0; i < 5; i++){
			int min = 0;
			while(flagchk[min]==1)min++;  
			for(int j = 0; j < projlist.size(); j++){ 
				if (distance[j] < distance[min] && flagchk[j] ==0)
					min = j; 
			} 
			
			flagchk[min] = 1;
			
			JSONObject item = new JSONObject();
			item.put("shopname", projlist.get(min).getProjectName());
			item.put("helptel",(projlist.get(min).getHelpTel()==null ? "" :projlist.get(min).getHelpTel()));
			item.put("distance", distance[min]); 
			nearlist.add(item);
		}
		
		//4 返回结果
		if(nearlist.size()>0){
			setMap(true, "ok", nearlist); 
		}else{
			setMap(false, "no matching result", null);
		}
		
		return this.SUCCESS;
	}
	
	// getUsershop()：若用户是车主，获取对应购车销售店信息
	public String getUsershop(){ 
		 
		
		JSONArray usershop = new JSONArray();
		 JSONObject ushop = new JSONObject();
		 /* 暂用假数据
		 ushop.put("shopname", "广汽丰田总店");
		 ushop.put("distance", "433");
		 ushop.put("helptel", "10010");
		 usershop.add(ushop);
		  
		 setMap(true,"ok",usershop);  
		  */
		 
		// 查找微信用户的购车信息
		 List<WxUserCarProjectView> usershopList = wxUser2shopService.getAll(WxUserCarProjectView.class, "openId", openid,  "=");
		
		 if(usershopList.size()!=0){
			 for(int i=0;i<usershopList.size();i++){
				//计算距离
					double lat = Float.parseFloat(usershopList.get(i).getDealerLatitude());
					double lon = Float.parseFloat(usershopList.get(i).getDealerLongitude());
					// double distance = Math.sqrt((x-lat)*(x-lat) + (y-lon) * (y-lon));
					double distance = R * Math.acos(Math.cos(lat * Math.PI / 180 ) * Math.cos(x * Math.PI / 180) * Math.cos(lon * Math.PI / 180 - y * Math.PI / 180)+Math.sin(lat * Math.PI / 180 ) * Math.sin(x * Math.PI / 180));
					 
					 
				 ushop.put("shopname", usershopList.get(i).getProjectName());
				 ushop.put("distance", distance);
				 ushop.put("helptel", (usershopList.get(i).getDealerHelpTel()==null ? "" :usershopList.get(i).getDealerHelpTel()));
				 usershop.add(ushop);
			 }
			 setMap(true,"ok",usershop);
		 }
		 /*	
		//1 根据微信用户openid，获取手机号(数据库表：WxUser)
		List<WxUser> userList = wxUserService.getAll(WxUser.class, "openId", openid,  "=");
		if( userList.size() > 0 ){
			
			//2 根据用户手机号，获取匹配的客户购车记录列表 (数据库表：WxUser2Car,需建表，包括字段客户手机号tel、销售店代码shopNum)
			List<WxUser2Car>shopList =  wxUser2CarService.getAll(WxUser2Car.class,"tel",userList.get(0).getUserTel(),"=");
				if(shopList.size()>0){
				
					// 3 根据销售店代码，获取购车销售店信息(数据库表：WxProject)
					for(int i = 0;i<shopList.size();i++){ 
							List<WxProject>usershopList = wxProjectService.getAll(WxProject.class,"projectNumber",shopList.get(i).getShopNum(),"=");
							 
							 //计算距离
							double lat = Float.parseFloat(usershopList.get(0).getLatitude());
							double lon = Float.parseFloat(usershopList.get(0).getLongitude());
							double distance = Math.sqrt((x-lat)*(x-lat) + (y-lon) * (y-lon)); 
							
							//拼装JSON	
							ushop.put("shopname",usershopList.get(0).getProjectName());
							ushop.put("distance",distance);
							ushop.put("helptel",usershopList.get(0).getHelpTel());
							usershop.add(ushop);
							 
					}
					setMap(true,"ok",usershop); 
				}else{
					//有购车记录，缺失或无匹配销售店信息
					setMap(false,"2",null); 
				}
			*/
			
		else{
			//无购车记录
			setMap(false,"1",null);  
		}  
		 return this.SUCCESS;
		
		 
	} 
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}
	@Override
	public WxProject getModel() {
		// TODO Auto-generated method stub
		return wxProj;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxProj) {
			wxProj = new WxProject();
		}
	}
	 
}
