package com.tlan.web.action.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.car.WxModuleContentCarPara;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class CarNaviAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentCarPara> {
	private static Logger log = Logger.getLogger(CarNaviAction.class);
	
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarPara> carParaService;

	private WxModuleContentCarPara carParaContent;
	private String carPara;
	private String items;// 删除id集合

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getCarPara() {
		return carPara;
	}

	public void setCarPara(String carPara) {
		this.carPara = carPara;
	}

	private LoginBean loginBean = obtainLoginBean();
	private String carGuid;

	public String getCarGuid() {
		return carGuid;
	}

	public void setCarGuid(String carGuid) {
		this.carGuid = carGuid;
	}

	public String carGuid1;

	public String getCarGuid1() {
		return carGuid1;
	}

	public void setCarGuid1(String carGuid1) {
		this.carGuid1 = carGuid1;
	}

	public String getCarNaviListByCarGuid() {
		List<WxModuleContentCarPara> tempPara = new ArrayList<WxModuleContentCarPara>();
		tempPara = carParaService.getAll(WxModuleContentCarPara.class,
				new String[] { "carParaCarGuid" }, new Object[] { getCarGuid()
						.toString() }, "carParaSortid", "asc");
		List<WxModuleContentCarPara> carNavis = new ArrayList<WxModuleContentCarPara>();
		for (int i = 0; i < tempPara.size(); i++) {
			if (tempPara.get(i).getCarParaParentId().equals("0")) {
				carNavis.add(tempPara.get(i));
			}
		}
		setMap(null, JSONArray.fromObject(carNavis), carNavis.size());
		return this.SUCCESS;
	}

	// 增加导航参数类型
	public String addCarNavi() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			carParaContent.setCarParaGuid(DataUtils.getUUID());
			carParaContent.setCarParaName(json.getString("name"));
			carParaContent.setCarParaParentId("0");
			carParaContent.setCarParaCarGuid(json.getString("carGuid"));
			carParaContent.setCarParaSortid(json.getString("inputSortId"));
			carParaContent.setCreatedOn(DateUtils.currentDatetime());
			carParaContent.setDeleteTag(0);
			try {
				carParaService.save(carParaContent);
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("添加参数类型失败：" + e.getMessage());
				setMap(false, "添加失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 根据导航参数类型查询导航信息
	@SuppressWarnings("static-access")
	public String queryCarNavi() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			List<WxModuleContentCarPara> carPara = carParaService.getAll(
					WxModuleContentCarPara.class, "carParaGuid",
					json.getString("carParaGuid"));
			System.out.println(json);
			carParaContent = carPara.get(0);
			setMap(true, "查询成功", carParaContent);
		} else {
			setMap(false, "读取数据失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 更新导航参数类型信息
	public String updateCarNavi() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			List<WxModuleContentCarPara> carNavis = carParaService.getAll(
					WxModuleContentCarPara.class, "carParaGuid",
					json.getString("carParaGuid"));
			carParaContent = carNavis.get(0);
			carParaContent.setCarParaName(json.getString("name"));
			carParaContent.setCarParaSortid(json.getString("inputSortId"));
			carParaContent.setCreatedOn(DateUtils.currentDatetime());
			carParaContent.setDeleteTag(0);
			try {
				carParaService.update(carParaContent);
				setMap(true, "修改成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("修改导航参数类型异常：" + e.getMessage());
				setMap(false, "修改失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 真删除一条记录
	public String deleteCarNavi() {
		if (null != getItems()) {
			System.out.println(getItems());
			String items[] = getItems().split("\\,");
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				carParaService.delete(WxModuleContentCarPara.class,
						"carParaGuid", items[i].trim());
				carParaService.delete(WxModuleContentCarPara.class,
						"carParaParentId", items[i].trim());
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 通过carGuid获取车辆所有信息
	/*
	 * var data =
	 * [{type:"基本参数",subtype:[{typeName:"生产时间",typeValue:"2012"},{typeName
	 * :"发动机位置",typeValue:"前置"},{typeName:"驱动形式",typeValue:"前驱"},
	 * {typeName:"综合油耗(L/100km)"
	 * ,typeValue:""},{typeName:"最高车速(km/h)",typeValue:"180"}]},
	 * {type:"车身",subtype
	 * :[{typeName:"生产时间",typeValue:"2012"},{typeName:"发动机位置",typeValue
	 * :"前置"},{typeName:"驱动形式",typeValue:"前驱"},
	 * {typeName:"综合油耗(L/100km)",typeValue
	 * :""},{typeName:"最高车速(km/h)",typeValue:"180"}]},
	 * {type:"发动机",subtype:[{typeName
	 * :"生产时间",typeValue:"2012"},{typeName:"发动机位置",
	 * typeValue:"前置"},{typeName:"驱动形式",typeValue:"前驱"},
	 * {typeName:"综合油耗(L/100km)"
	 * ,typeValue:""},{typeName:"最高车速(km/h)",typeValue:"180"}]},
	 * {type:"底盘",subtype
	 * :[{typeName:"生产时间",typeValue:"2012"},{typeName:"发动机位置",typeValue
	 * :"前置"},{typeName:"驱动形式",typeValue:"前驱"},
	 * {typeName:"综合油耗(L/100km)",typeValue
	 * :""},{typeName:"最高车速(km/h)",typeValue:"180"}]},
	 * {type:"操控配置",subtype:[{typeName
	 * :"生产时间",typeValue:"2012"},{typeName:"发动机位置"
	 * ,typeValue:"前置"},{typeName:"驱动形式",typeValue:"前驱"},
	 * {typeName:"综合油耗(L/100km)"
	 * ,typeValue:""},{typeName:"最高车速(km/h)",typeValue:"180"}]} ];
	 */
	public String getCarInfoByCarGuid() {
		List<WxModuleContentCarPara> carNavis = new ArrayList<WxModuleContentCarPara>();
		System.out.println(getCarGuid1());
		carNavis = carParaService.getAll(WxModuleContentCarPara.class,
				new String[] { "carParaCarGuid" }, new Object[] { getCarGuid1()
						.toString() }, "carParaSortid", "asc");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String paraName;
		String paraValue;
		// System.out.println(carNavis.size());
		for (int i = 0; i < carNavis.size(); i++) {
			if (carNavis.get(i).getCarParaParentId().equals("0")) {
				Map<String, Object> map = new TreeMap<String, Object>();
				// System.out.println(carNavis.get(i).getCarParaName());
				map.put("type", carNavis.get(i).getCarParaName());
				List<WxModuleContentCarPara> carParaServices = new ArrayList<WxModuleContentCarPara>();
				for (int j = 0; j < carNavis.size(); j++) {
					if (carNavis.get(i).getCarParaGuid()
							.equals(carNavis.get(j).getCarParaParentId())) {
						// System.out.println("carParentID:"+carNavis.get(j).getCarParaParentId());
						carParaServices.add(carNavis.get(j));
					}
				}
				map.put("subType", carParaServices);
				list.add(map);
			}
		}
		setMap(true, "success", list);
		return this.SUCCESS;
	}

	@Override
	public WxModuleContentCarPara getModel() {
		// TODO Auto-generated method stub
		return carParaContent;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == carParaContent) {
			carParaContent = new WxModuleContentCarPara();
		}
	}
}
