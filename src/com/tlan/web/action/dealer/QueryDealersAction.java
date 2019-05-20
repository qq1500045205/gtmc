package com.tlan.web.action.dealer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;

/**
 * 
 * 查销售店
 * 
 * @author limengjie
 * 
 */
public class QueryDealersAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "baseService")
	private IBaseService<WxProject> webProjectService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;

	private String openid;
	private String gzhHash;
	private float x;
	private float y;
	private String projectGuid;

	// 地球半径
	private double R = 6370996.81;

	/**
	 * 
	 * 获取附近5个销售店
	 */
	public String queryDealerList() {

		// 1 取出所有销售店(1应用给位置查询2.应用与列表筛选)
		JSONArray nearlist = new JSONArray();
		List<WxProject> projlist;
		if (projectGuid.equals("all")) {
			projlist = webProjectService.getAll(WxProject.class,new String[] {"latitude","longitude"},new Object[]{" not null"," not null"},new String[]{"is","is"});
		} else {
			projlist = webProjectService.getAll(WxProject.class,new String[] {"projectGuid","latitude","longitude"},new Object[]{projectGuid," not null"," not null"},new String[]{"=","is","is"});
		}

		// 2 计算每个销售店的距离
		double[] distance = new double[projlist.size()];
		for (int i = 0; i < projlist.size(); i++) {
			double lat = Float.parseFloat(projlist.get(i).getLatitude());
			double lon = Float.parseFloat(projlist.get(i).getLongitude());
			// distance[i] = Math.sqrt((x-lat)*(x-lat) + (y-lon) * (y-lon));

			distance[i] = R
					* Math.acos(Math.cos(lat * Math.PI / 180)
							* Math.cos(x * Math.PI / 180)
							* Math.cos(lon * Math.PI / 180 - y * Math.PI / 180)
							+ Math.sin(lat * Math.PI / 180)
							* Math.sin(x * Math.PI / 180));
		}

		// 3 根据销售店距离进行排序，取前5拼装JSON

		// 设置检查标记，初始化为0
		int[] flagchk = new int[projlist.size()];
		for (int t = 0; t < projlist.size(); t++) {
			flagchk[t] = 0;
		}
		int getnumb = 5;
		if (projlist.size() < 5) {
			getnumb = projlist.size();
		}
		for (int i = 0; i < getnumb; i++) {
			int min = 0;
			while (flagchk[min] == 1)
				min++;
			for (int j = 0; j < projlist.size(); j++) {
				if (distance[j] < distance[min] && flagchk[j] == 0)
					min = j;
			}

			flagchk[min] = 1;

			JSONObject map = new JSONObject();
			map.put("projectGuid", projlist.get(min).getProjectGuid());
			map.put("shopname", projlist.get(min).getProjectName());
			map.put("address", projlist.get(min).getAddress());
			map.put("distance", distance[min]);
			map.put("Latitude", projlist.get(min).getLatitude());
			map.put("Longitude", projlist.get(min).getLongitude());
			if(DataUtils.isNotNullOrEmpty(projlist.get(min).getDescription()))
			{
				map.put("desc", projlist.get(min).getDescription());
			}
			else {
				map.put("desc", "无");
			}
			map.put("tel", projlist.get(min).getMobile());
			nearlist.add(map);
		}

		// 4 返回结果
		if (nearlist.size() > 0) {
			setMap(true, "ok", nearlist);
		} else {
			setMap(false, "no matching result", null);
		}

		return this.SUCCESS;

	}

	public String queryDealerInfo() {

		WxProject projinfo = webProjectService.get(WxProject.class,
				"projectGuid", projectGuid);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new TreeMap<String, Object>();

		map.put("projectName", projinfo.getProjectName());
		map.put("description", projinfo.getDescription());
		map.put("mobile", projinfo.getMobile());
		map.put("asTel", projinfo.getAsTel());
		map.put("projectGuid", projinfo.getProjectGuid());
		map.put("shopname", projinfo.getProjectName());
		map.put("address", projinfo.getAddress());
		map.put("Latitude", projinfo.getLatitude());
		map.put("Longitude", projinfo.getLongitude());
		map.put("desc", projinfo.getDescription());
		map.put("provinceCode", projinfo.getDealerProvinceCode());
		map.put("cityCode", projinfo.getDealerCityCode());

		// 获取公众号name
		WxGongzhonghao wxgzh = new WxGongzhonghao();
		wxgzh = gzhService.get(WxGongzhonghao.class, "projectGuid",
				projinfo.getProjectGuid());
		if (wxgzh != null) {
			map.put("gzhguid", wxgzh.getGzhGuid());
			map.put("gzhname", wxgzh.getGzhName());
		} else {

			map.put("gzhguid", "null");
			map.put("gzhname", "抱歉，此经销商无公众号");
		}
		list.add(map);

		setMap(true, "success", list);
		return this.SUCCESS;

	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getProjectGuid() {
		return projectGuid;
	}

	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}

}
