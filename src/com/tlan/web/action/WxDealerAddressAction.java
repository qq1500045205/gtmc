package com.tlan.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGcloudProvinceCtiy;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;

/**
 * web用户管理
 * 
 * @author magenm
 * 
 */
public class WxDealerAddressAction extends BaseAction implements Preparable,
		ModelDriven<WxGcloudProvinceCtiy> {

	
	private Log log = LogFactory.getLog(WxDealerAddressAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxGcloudProvinceCtiy> wxGcloudProvinceCityService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> wxProjectService;
	
	private WxGcloudProvinceCtiy wxProvinceCity;
	
	private String openid;
	private String gzhHash;
 
	private String province_code;
	
	private String city_code;
	

	

	/**
	 * 获取用户信息 及车辆信息
	 * 
	 * @return
	 */
	public String getAllProvinces() {
		try {
			ArrayList<Object> retlist = new ArrayList<Object>();
			HashMap<String, String> map = new HashMap<String, String>(); 
			List<WxGcloudProvinceCtiy> wxProvinces = wxGcloudProvinceCityService.getAll(WxGcloudProvinceCtiy.class);
			for(int i=0;i<wxProvinces.size();i++){
				WxGcloudProvinceCtiy province = wxProvinces.get(i);
				if(!map.containsKey(province.getProvinceCode())){
					retlist.add(province);
					map.put(province.getProvinceCode(), "true");
				}
			}
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String getAllCitiesByProvince() {
		try {
			ArrayList<Object> retlist = new ArrayList<Object>();
			HashMap<String, String> map = new HashMap<String, String>(); 
			List<WxGcloudProvinceCtiy> wxCities = wxGcloudProvinceCityService.getAll(WxGcloudProvinceCtiy.class,"cityCode", province_code,"like%%");
			for(int i=0;i<wxCities.size();i++){
				WxGcloudProvinceCtiy city = wxCities.get(i);
				if(!map.containsKey(city.getCityCode())){
					retlist.add(city);
					map.put(city.getCityCode(), "true");
				}
			}
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
/*	public String getAllDealersByCity(){
		try {
			ArrayList<Object> retlist = new ArrayList<Object>();
			HashMap<String, String> map = new HashMap<String, String>(); 
			List<WxProject> projectList = wxProjectService.getAll(WxProject.class, "dealerCityCode", city_code);
			for(int i=0;i<projectList.size();i++){
				WxProject project = projectList.get(i);
				if(!map.containsKey(project.getDealerCode())){
					retlist.add(project);
					map.put(project.getDealerCode(), "true");
				}
			}
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		
		
		return this.SUCCESS;
	}*/
	
	@Override
	public WxGcloudProvinceCtiy getModel() {
		// TODO Auto-generated method stub
		return wxProvinceCity;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxProvinceCity) {
			wxProvinceCity = new WxGcloudProvinceCtiy();
		}
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

}
