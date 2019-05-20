package com.tlan.admin.gtmc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxMenu;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;

/**
 * 
 * 查销售店
 * 
 * @author limengjie
 * 
 */
public class QueryShopsAction extends BaseAction {
	@Resource(name = "baseService")
	private IBaseService<WxProject> webProjectService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	
	private WxProject wxProj;
	private String openid;
	private String gzhHash;
	private float x;
	private float y;
/**
 * 
 * 获取附近5个销售店
 */
	public String queryShops() {
		
		JSONObject result = new JSONObject();
		
		List<WxProject> projlist = webProjectService.getAll(WxProject.class,
				"projectGuid", wxProj.getProjectGuid());
		
		 JSONArray nearshoplist = new JSONArray();
		 
		for(int i = 0 ; i <projlist.size() ; i++){
			JSONObject item = new JSONObject();
			item.put("shopname", projlist.get(i).getProjectName());
			item.put("address", projlist.get(i));
			item.put("distance", projlist.get(i));
		}

		JSONArray nearlist = new JSONArray();

		result.put("nearby", nearlist);
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

}
