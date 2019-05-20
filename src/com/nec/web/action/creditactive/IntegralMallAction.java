package com.nec.web.action.creditactive;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxLog;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.creditactive.WxHatArea;
import com.tlan.common.model.creditactive.WxHatCity;
import com.tlan.common.model.creditactive.WxHatProvince;
import com.tlan.common.model.creditactive.WxOrderStatusM;
import com.tlan.common.model.creditactive.WxProductListF;
import com.tlan.common.model.creditactive.WxProductOrderF;
import com.tlan.common.model.creditactive.WxProductTypeM;
import com.tlan.common.model.creditactive.WxUserLocationF;
import com.tlan.common.model.creditactive.WxUserLocationFTemp;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class IntegralMallAction extends BaseAction {

	private static final long serialVersionUID = -7129238822128446215L;

	@Resource(name = "baseService")
	private IBaseService<WxProductOrderF>  productOrderFService;
	@Resource(name = "baseService")
	private IBaseService<WxOrderStatusM>	orderStatusMService;  
	@Resource(name = "baseService")
	private IBaseService<WxProductListF> productListFService	;  
	@Resource(name = "baseService")
	private IBaseService<WxProductTypeM> productTypeMService;
	@Resource(name = "baseService")
	private IBaseService<WxUserLocationF> userLocationFService;
	@Resource(name = "baseService")
	private IBaseService<WxUserLocationFTemp> userLocationFTempService;
	@Resource(name = "baseService")
	private IBaseService<WxHatProvince> hatProvinceService;
	@Resource(name = "baseService")
	private IBaseService<WxHatCity> hatCityService;
	@Resource(name = "baseService")
	private IBaseService<WxHatArea> hatAreaService;
	@Resource(name = "baseService")
	private IBaseService<WxUser> usersService;
	/**
	 *管理所有积分商品
	 * @author 郭红圣
	 * @version 2.0 2014年6月18日 下午12:03:51  
	 */
	public String getIntegralorders(){
		System.out.println("获得所有订单！");
		try{
		List<WxProductOrderF> list = this.productOrderFService.findPage(WxProductOrderF.class, getStart(), getLimit(), new String[]{"del_flag"}, new Object[]{"0"}, "product_order_date desc");
		List<Object> li=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Map<String, String> map = new HashMap<>();
			map.put("userName",list.get(i).getUser().getUserName()+"");
			map.put("nickName",list.get(i).getUser().getNickName()+"");
			map.put("product_order_date",list.get(i).getProduct_order_date()+"");
			map.put("order_status_name", list.get(i).getOrderStatusM().getOrder_status_name()+"");
			map.put("product_order_num",list.get(i).getProduct_order_num()+"");
			map.put("user_location_name",list.get(i).getUserLocationFTemp().getUser_location_name()+"");
			map.put("product_name",list.get(i).getProductTypeM().getProductName()+"");
			map.put("product_namedoc",list.get(i).getProductListF().getProductName()+"");
			map.put("product_credit",String.valueOf( list.get(i).getProductListF().getProductCredit())+"");
			map.put("product_count",String.valueOf(list.get(i).getProduct_count())+"");
			map.put("detail","<a  href='javascript:void(0)'  onclick='openOrderDetail("+'"'+list.get(i).getProduct_order_num()+""+'"'+")'>查看详情</a>");
			li.add(map);
		}
		setMap(null,JSONArray.fromObject(li),productOrderFService.getCount(WxProductOrderF.class));   
		}catch(Exception e ){
			System.out.println(e);
		}
		return SUCCESS;
	}
	
	/**
	 *通过订单号获得订单详情
	 * @author 郭红圣
	 * @version 2.0 2014年6月18日 下午5:59:32  
	 */
	public String getDetailOrderByGuid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ordernum=request.getParameter("ordernum");
		WxProductOrderF productOrderF = this.productOrderFService.get(WxProductOrderF.class, "product_order_num", ordernum);
		request.setAttribute("productOrderF", productOrderF);
		return "success";
	}
	/**
	 *通过订单号修改订单状态
	 * @author 郭红圣
	 * @version 2.0 2014年6月19日 下午1:05:24  
	 */
	public String changeDetailOrderByOrderNum(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String odernum = request.getParameter("oderguid");
		String order_status_name= request.getParameter("order_status_name");
		this.productOrderFService.update(WxProductOrderF.class, new String[]{"order_status_guid"},
				new Object[]{order_status_name}, "product_order_num = '" + odernum + "'");
		setMap(true, "修改成功", null);
		return SUCCESS;
	}
	
	/**
	 *获得所有订单状态
	 * @author 郭红圣
	 * @version 2.0 2014年6月20日 上午11:49:08  
	 */
	public String getALLOrderStatus(){
		List <WxOrderStatusM> list = this.orderStatusMService.getAll(WxOrderStatusM.class);
		List<Object> li=new ArrayList<>();
		Map<String, String> map1 = new HashMap<>();
		map1.put("value","");
		map1.put("text"," ");
		li.add(map1);
		for(int i=0;i<list.size();i++){
			Map<String, String> map = new HashMap<>();
			map.put("value",list.get(i).getOrder_status_guid());
			map.put("text",list.get(i).getOrder_status_name());
			li.add(map);
		}
		jsonObject=JSONArray.fromObject(li);
		return SUCCESS;
	}
	
	/**
	 *获得所有商品类别
	 * @author 郭红圣
	 * @version 2.0 2014年6月20日 上午11:01:28  
	 */
	public String getALLProductType(){
		List<WxProductTypeM> list = this.productTypeMService.getAll(WxProductTypeM.class);
		JSONArray ary = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("text", " ");
		obj1.put("value", "");
		ary.add(obj1);
		for (WxProductTypeM type : list) {
			JSONObject obj = new JSONObject();
			obj.put("text", type.getProductName());
			obj.put("value",type.getProductTypeGuid());
			ary.add(obj);
		}
		System.out.println(ary);
		jsonObject = ary;
		return SUCCESS;
	}
	
	/**
	 *高级查询获得数据
	 * @author 郭红圣
	 * @version 2.0 2014年6月20日 上午11:01:28  
	 * @throws UnsupportedEncodingException 
	 */
	public String getOrderByQuery() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String queryConditions=request.getParameter("queryConditions");
		String keyWord=request.getParameter("keyWord");
		keyWord=URLDecoder.decode(keyWord,"UTF-8");
		String saleInformation=request.getParameter("saleInformation");
		String queryOrderStatus=request.getParameter("queryOrderStatus");
		String exchangeIntegral=request.getParameter("exchangeIntegral");
		String startOn=request.getParameter("startOn");
		String endOn=request.getParameter("endOn");
		System.out.println("11111");
		try{
		String[] str = new String[7];
		Object[] obj =new Object[7];
		String[] ch = new String[7];
		if(queryConditions.equals("0")){
			str[0]="1";
			obj[0]="1";
			ch[0]="=";
		}else{
			str[0]=queryConditions;
			obj[0]="%"+keyWord+"%";
			ch[0]="like";
		}
		if(DataUtils.isNullOrEmpty(saleInformation)){
			str[1]="1";
			obj[1]="1";
			ch[1]="=";
		}else{
			str[1]="product_type_guid";
			obj[1]=saleInformation;
			ch[1]="is";
		}
		if(DataUtils.isNullOrEmpty(queryOrderStatus)){
			str[2]="1";
			obj[2]="1";
			ch[2]="=";
		}else{
			str[2]="order_status_guid";
			obj[2]=queryOrderStatus;
			ch[2]="is";
		}
		if(DataUtils.isNullOrEmpty(startOn)){
			str[3]="1";
			obj[3]="1";
			ch[3]="=";
		}else {
			str[3]="product_order_date";
			obj[3]=startOn;
			ch[3]=">=";
		}
		if (DataUtils.isNullOrEmpty(endOn)){
			str[4]="1";
			obj[4]="1";
			ch[4]="=";
		}else {
			str[4]="product_order_date";
			obj[4]=endOn;
			ch[4]="<=";
		}
		if (DataUtils.isNullOrEmpty(exchangeIntegral)){
			str[5]="1";
			obj[5]="1";
			ch[5]="=";
		}else {
			str[5]="productListF.productCredit";
			obj[5]=exchangeIntegral;
			ch[5]="=";
		}
			str[6]="del_flag";
			obj[6]="0";
			ch[6]="=";
		List<WxProductOrderF> list = this.productOrderFService.findPage(WxProductOrderF.class, getStart(), getLimit(), str, obj, ch, "product_order_date  desc");
		List<Object> li=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Map<String, String> map = new HashMap<>();
			map.put("userName",list.get(i).getUser().getUserName()+"");
			map.put("nickName",list.get(i).getUser().getNickName()+"");
			map.put("product_order_date",list.get(i).getProduct_order_date()+"");
			map.put("order_status_name", list.get(i).getOrderStatusM().getOrder_status_name()+"");
			map.put("product_order_num",list.get(i).getProduct_order_num()+"");
			map.put("user_location_name",list.get(i).getUserLocationFTemp().getUser_location_name()+"");
			map.put("product_name",list.get(i).getProductTypeM().getProductName()+"");
			map.put("product_namedoc",list.get(i).getProductListF().getProductName()+"");
			map.put("product_credit",String.valueOf( list.get(i).getProductListF().getProductCredit())+"");
			map.put("product_count",String.valueOf(list.get(i).getProduct_count())+"");
			map.put("detail","<a  href='javascript:void(0)'  onclick='openOrderDetail("+'"'+list.get(i).getProduct_order_num()+'"'+")'>查看详情</a>");
			li.add(map);
		}
		setMap(null,JSONArray.fromObject(li),productOrderFService.getCount(WxProductOrderF.class));   
		}catch(Exception e){
			System.out.println(e);
		}
		return SUCCESS;
		
	}
	public String getUserLocation(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String open_id = request.getParameter("open_id");
		String gzh_hash = request.getParameter("gzh_hash");
		try{
		List<WxUser> list1 = this.usersService.getAll(WxUser.class, new String[]{"open_id","gzh_hash"}, new Object[]{open_id,gzh_hash});
		System.out.println("获得地址！");
		List<WxUserLocationF> list = this.userLocationFService.getAll(WxUserLocationF.class,"wxUser.userGuid",list1.get(0).getUserGuid());
		List<Object> li=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Map<String, String> map = new HashMap<>();
			map.put("userGuid",list.get(i).getWxUser().getUserGuid());
			map.put("userLocationName", list.get(i).getUser_location_name());
			map.put("userLocationProvice",list.get(i).getUser_location_provice());
			map.put("userLocationCity",list.get(i).getUser_location_city());
			map.put("userLocationArea",list.get(i).getUser_location_district());
			map.put("userLocationStreet",list.get(i).getUser_location_street());
			map.put("userLocationPhone",list.get(i).getUser_location_phone());
			map.put("userLocationGuid",list.get(i).getUser_location_guid());
			li.add(map);
		}
		System.out.println("用户订单地址获取成功");
		    setMap(true, "地址获取成功", JSONArray.fromObject(li));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		    return SUCCESS;
	}
	/**
	 *获得所有省
	 * @author 郭红圣
	 * @version 2.0 2014年6月18日 下午5:59:32  
	 */
	public String getOderProvince(){
		List<WxHatProvince> list = this.hatProvinceService.getAll(WxHatProvince.class);
		   JSONArray ary = new JSONArray();
		    for (int i=0;i<list.size();i++){
				JSONObject obj = JSONObject.fromObject(list.get(i));
				ary.add(obj);
			}
		    setMap(true, "数据获取成功", ary);
		    return SUCCESS;
	}
	public String getOderCityByprovinceID(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String provinceID=request.getParameter("provinceID");
		List<WxHatCity> list =this.hatCityService.getAll(WxHatCity.class, "father", provinceID);
		 JSONArray ary = new JSONArray();
		    for (int i=0;i<list.size();i++){
				JSONObject obj = JSONObject.fromObject(list.get(i));
				ary.add(obj);
			}
		    setMap(true, "数据获取成功", ary);
		return SUCCESS;
	}
	public String getOderAreaBycityID(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String provinceID=request.getParameter("cityID");
		List<WxHatArea> list =this.hatAreaService.getAll(WxHatArea.class, "father", provinceID);
		 JSONArray ary = new JSONArray();
		    for (int i=0;i<list.size();i++){
				JSONObject obj = JSONObject.fromObject(list.get(i));
				ary.add(obj);
			}
		    setMap(true, "数据获取成功", ary);
		return SUCCESS;
	}
	public String setOrderInformation(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			String userName=request.getParameter("userName");
			String hp=request.getParameter("hp");
			String hc=request.getParameter("hc");
			String ha=request.getParameter("ha");
			String fullArea=request.getParameter("fullArea");
			String mobile=request.getParameter("mobile");
			String gzh_hash=request.getParameter("gzh_hash");
			String open_id=request.getParameter("open_id");
			String credit= request.getParameter("credit");
			//以上为地址表信息，下面参数为商品信息
			String product_guid=request.getParameter("product_guid");
			String product_count=request.getParameter("product_count");
			List<WxUser> list1 = this.usersService.getAll(WxUser.class, new String[]{"open_id","gzh_hash"}, new Object[]{open_id,gzh_hash});
			String user_location_guid = request.getParameter("userLocationGuid");
			WxUserLocationF wulf = new WxUserLocationF();
			WxUserLocationFTemp wulft = new WxUserLocationFTemp();
			wulf.setWxUser(list1.get(0));
			if(DataUtils.isNullOrEmpty(user_location_guid)){
				user_location_guid = DataUtils.getUUID();
			}
				wulf.setUser_location_guid(user_location_guid);
				wulf.setUser_location_name(userName);
				wulf.setUser_location_provice(hp);
				wulf.setUser_location_city(hc);
				wulf.setUser_location_district(ha);
				wulf.setUser_location_street(fullArea);
				wulf.setUser_location_phone(mobile);
				userLocationFService.saveOrUpdate(wulf);
				System.out.println("----------------------------------保存订单地址到F表成功！下面开始保存地址到Temp表");
			
				String user_location_guidTemp = DataUtils.getUUID();
				wulft.setUser_location_guid(user_location_guidTemp);
				wulft.setUser_location_name(userName);
				wulft.setUser_location_provice(hp);
				wulft.setUser_location_city(hc);
				wulft.setUser_location_district(ha);
				wulft.setUser_location_street(fullArea);
				wulft.setUser_location_phone(mobile);
				userLocationFTempService.saveOrUpdate(wulft);
				System.out.println("----------------------------------保存订单地址到temp表成功！下面开始保存订单信息");
				WxProductOrderF pof = new WxProductOrderF();
				WxProductListF productListF= productListFService.get(WxProductListF.class, "product_guid", product_guid);				//商品订单表
				WxOrderStatusM orderStatusM = orderStatusMService.get(WxOrderStatusM.class, "order_status_guid", "1");			//订单状态
				WxProductTypeM	productTypeM = productTypeMService.get(WxProductTypeM.class, "product_type_guid",productListF.getProductTypeId());    //订单分类
				int count =Integer.parseInt(product_count);                                     //商品数量
				int creditScore = Integer.parseInt(credit)*count;                               //当前订单所需积分
				String date = DateUtils.currentDate();												//当前日期
				String time =  DateUtils.currentDatetime();
				time = time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
				String num = DataUtils.getRandomIntString(5);
				String oderNum = time + num ;                                                       //订单号
				String product_order_guid =DataUtils.getUUID();
				pof.setUser(list1.get(0));
				pof.setOrderStatusM(orderStatusM);
				pof.setProduct_count(count);
				pof.setProduct_order_date(date);
				pof.setProduct_order_guid(product_order_guid);
				pof.setProductListF(productListF);
				pof.setProductTypeM(productTypeM);
				pof.setUserLocationFTemp(wulft);
				pof.setProduct_order_num(oderNum);
				pof.setTake_credit(creditScore);
				JSONObject obj = new JSONObject();
				System.out.println("当前时间唯一随机订单："+oderNum+"原积分"+list1.get(0).getCountCredit()+"下面减去积分！");
				WxUser user = list1.get(0);
				int leaveCount = (user.getCountCredit() - creditScore);
				System.out.println(leaveCount);
				user.setCountCredit(leaveCount);
				System.out.println("减去订单数量后，保存商品列表");
				int productStock = productListF.getProductStock()-Integer.parseInt(product_count);
				productListF.setProductStock(productStock);
				productListFService.update(productListF);
				productOrderFService.save(pof);
				usersService.update(user);
				obj.put("oderNum", oderNum);
				setMap(true, "订单生成成功！", obj);
			}catch(Exception e){
				setMap(false, "系统繁忙，请稍后再试！", null);
				}
			return SUCCESS;
	}
	
	/**
	 *   通过已有地址直接生成订单
	 * */
	public String setOrderInformationBy(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
		String userLocationGuid=request.getParameter("userLocationGuid");
		String product_guid=request.getParameter("product_guid");
		String product_count=request.getParameter("product_count");
		String userGuid=request.getParameter("userGuid");
		String credit= request.getParameter("credit");
		WxProductOrderF pof = new WxProductOrderF();
		WxUser	user = usersService.get(WxUser.class, "userGuid", userGuid);
		WxUserLocationF userLocationF = userLocationFService.get(WxUserLocationF.class, "user_location_guid", userLocationGuid); //跟具用户地址表查出地址temp表，更新到pof中
		System.out.println("-----------取出F表中的数据插入到Temp表后，插入到pof表");
		WxUserLocationFTemp wulft= new WxUserLocationFTemp();
		String user_location_guidTemp = DataUtils.getUUID();
		wulft.setUser_location_guid(user_location_guidTemp);
		wulft.setUser_location_name(userLocationF.getUser_location_name());
		wulft.setUser_location_phone(userLocationF.getUser_location_phone());
		wulft.setUser_location_provice(userLocationF.getUser_location_provice());
		wulft.setUser_location_city(userLocationF.getUser_location_city());
		wulft.setUser_location_district(userLocationF.getUser_location_district());
		wulft.setUser_location_street(userLocationF.getUser_location_street());
		userLocationFTempService.save(wulft);
		WxProductListF productListF= productListFService.get(WxProductListF.class, "product_guid", product_guid);				//商品订单表
		WxOrderStatusM orderStatusM = orderStatusMService.get(WxOrderStatusM.class, "order_status_guid", "1");			//订单状态
		WxProductTypeM	productTypeM = productTypeMService.get(WxProductTypeM.class, "product_type_guid",productListF.getProductTypeId());    //订单分类
		int count =Integer.parseInt(product_count);                                     //商品数量
		int creditScore = Integer.parseInt(credit)*count;                               //当前订单所需积分
		String date = DateUtils.currentDate();												//当前日期
		String time =  DateUtils.currentDatetime();
		time = time.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		String num = DataUtils.getRandomIntString(5);
		String oderNum = time + num ;                                                       //订单号
		String product_order_guid =DataUtils.getUUID();
		pof.setUser(user);
		pof.setOrderStatusM(orderStatusM);
		pof.setProduct_count(count);
		pof.setProduct_order_date(date);
		pof.setProduct_order_guid(product_order_guid);
		pof.setProductListF(productListF);
		pof.setProductTypeM(productTypeM);
		pof.setUserLocationFTemp(wulft);;
		pof.setProduct_order_num(oderNum);
		pof.setTake_credit(creditScore);
		JSONObject obj = new JSONObject();
		System.out.println("当前时间唯一随机订单："+oderNum+"原积分"+user.getCountCredit()+"下面开始减去积分");
		int leaveCount = (user.getCountCredit() - creditScore);
		System.out.println(leaveCount);
		user.setCountCredit(leaveCount);
		System.out.println("减去订单数量后，保存商品列表");
		int productStock = productListF.getProductStock()-Integer.parseInt(product_count);
		productListF.setProductStock(productStock);
		productListFService.update(productListF);
		productOrderFService.save(pof);
		usersService.update(user);
		obj.put("oderNum", oderNum);
		setMap(true, "订单生成成功！", obj);
		}catch(Exception e){
			setMap(false, "系统繁忙，请稍后再试！", null);
			}
		return SUCCESS;
	}
	
	public String delLocation(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
		String userLocationGuid= request.getParameter("userLocationGuid");
		userLocationFService.delete(WxUserLocationF.class, "user_location_guid", userLocationGuid);
		System.out.println("地址删除成功！");
		setMap(true, "删除成功！", null);
		}catch(Exception e){
			setMap(false, "系统繁忙，请稍后再试！", null);
		}
		return SUCCESS;
	}
	
	//删除订单，将标记列改为1
	public String deleteOrder(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String[] items = request.getParameterValues("item");
			for(int i=0;i<items.length;i++){
				WxProductOrderF productOrderF = this.productOrderFService.get(WxProductOrderF.class, "product_order_num", items[i]);
				productOrderF.setDel_flag(1);
				productOrderFService.update(productOrderF);
			}
			System.out.println("订单删除成功！");
			setMap(true, "删除成功！", null);
		}catch(Exception e){
			setMap(false, "系统繁忙，请稍后再试！", null);
		}
		return SUCCESS;
	}
}
