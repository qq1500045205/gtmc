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
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;

/**
 * web用户管理
 * 
 * @author magenm
 * 
 */
public class WxDealerAction extends BaseAction implements Preparable,
		ModelDriven<WxProject> {

	
	private Log log = LogFactory.getLog(WxDealerAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxProject> wxDealerService;
	
	private WxProject wxDealer;
	
	private String gzhHash;
	
	private String cityCode;
 
	

	 
	public String getAllListByCity(){
		try {
			ArrayList<Object> retlist = new ArrayList<Object>();
			List<WxProject> dealers = wxDealerService.getAll(WxProject.class, "dealerCityCode", cityCode);
			for(int i=0;i<dealers.size();i++){
				WxProject dealer = dealers.get(i);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("projectGuid", dealer.getProjectGuid());
				map.put("projectName", dealer.getProjectName());
				map.put("areaGuid", dealer.getAreaGuid());
				//map.put("gzhHash", dealer.getGzhHash());
				map.put("dealerCode", dealer.getDealerCode());
				map.put("dealerProvinceCode", dealer.getDealerProvinceCode());
				map.put("dealerCityCode", dealer.getDealerCityCode());
				map.put("address", dealer.getAddress());
			
				retlist.add(map);
				
			}
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
			//this.setMap(null, toJSON(wxCities), wxCities.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String getAllDealersByCity() {
		try {
			List<WxProject> retlist = new ArrayList<WxProject>();
			HashMap<String, String> map = new HashMap<String, String>();
			List<WxProject> projectList = wxDealerService.getAll(WxProject.class, "dealerCityCode", cityCode);
			for (int i = 0; i < projectList.size(); i++) {
				WxProject project = projectList.get(i);
				if (!map.containsKey(project.getDealerCode())) {
					retlist.add(project);
					map.put(project.getDealerCode(), "true");
				}
			}
			JSONArray ja= JSONArray.fromObject(retlist);
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
	
	return this.SUCCESS;
}
	
	
	
	
	@Override
	public WxProject getModel() {
		// TODO Auto-generated method stub
		return this.wxDealer;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxDealer) {
			wxDealer = new WxProject();
		}
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


}
