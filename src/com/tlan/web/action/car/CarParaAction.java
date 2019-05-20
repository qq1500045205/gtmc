package com.tlan.web.action.car;

import java.util.ArrayList;
import java.util.List;

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

public class CarParaAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentCarPara> {
	private static Logger log = Logger.getLogger(CarParaAction.class);
	
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarPara> carParaService;

	private WxModuleContentCarPara carParameter;
	private String carPara;
	private String items;// 删除id集合

	public String getCarPara() {
		return carPara;
	}

	public void setCarPara(String carPara) {
		this.carPara = carPara;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	private String carParaGuid;

	public String getCarParaGuid() {
		return carParaGuid;
	}

	public void setCarParaGuid(String carParaGuid) {
		this.carParaGuid = carParaGuid;
	}

	private String carParaGuid1;

	public String getCarParaGuid1() {
		return carParaGuid1;
	}

	public void setCarParaGuid1(String carParaGuid1) {
		this.carParaGuid1 = carParaGuid1;
	}

	private LoginBean loginBean = obtainLoginBean();

	public String getCarParaListByNavi() {
		List<WxModuleContentCarPara> carParas = new ArrayList<WxModuleContentCarPara>();
		// System.out.println(getCarParaGuid1()+"---"+carParaGuid1);
		carParas = carParaService.getAll(WxModuleContentCarPara.class,
				new String[] { "carParaParentId" },
				new Object[] { getCarParaGuid1().toString() }, "carParaSortid",
				"asc");
		setMap(null, JSONArray.fromObject(carParas), carParas.size());
		return this.SUCCESS;
	}

	// 增加导航名
	public String addCarPara() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			carParameter.setCarParaGuid(DataUtils.getUUID());
			carParameter.setCarParaName(json.getString("name"));
			carParameter.setCarParaValue(json.getString("value"));
			carParameter.setCarParaCarGuid(json.getString("carGuid"));
			carParameter.setCarParaParentId(json.getString("carParaGuid"));
			carParameter.setCarParaSortid(json.getString("inputSortId"));
			carParameter.setCreatedOn(DateUtils.currentDatetime());
			carParameter.setDeleteTag(0);
			try {
				carParaService.save(carParameter);
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("添加参数名失败：" + e.getMessage());
				setMap(false, "添加失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 根据导航参数类型查询导航信息
	@SuppressWarnings("static-access")
	public String queryCarPara() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			List<WxModuleContentCarPara> carParas = carParaService.getAll(
					WxModuleContentCarPara.class, "carParaGuid",
					json.getString("carParaGuid"));
			System.out.println(json);
			carParameter = carParas.get(0);
			setMap(true, "查询成功", carParameter);
		} else {
			setMap(false, "读取数据失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 更新导航参数类型信息
	public String updateCarPara() {
		if (DataUtils.isNotNullOrEmpty(carPara)) {
			JSONObject json = JSONObject.fromObject(carPara);
			List<WxModuleContentCarPara> carNavis = carParaService.getAll(
					WxModuleContentCarPara.class, "carParaGuid",
					json.getString("carParaGuid"));
			carParameter = carNavis.get(0);
			carParameter.setCarParaName(json.getString("name"));
			carParameter.setCarParaValue(json.getString("value"));
			carParameter.setCarParaSortid(json.getString("inputSortId"));
			carParameter.setCreatedOn(DateUtils.currentDatetime());
			carParameter.setDeleteTag(0);
			try {
				carParaService.update(carParameter);
				setMap(true, "修改成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("修改参数名异常：" + e.getMessage());
				setMap(false, "修改失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 真删除一条记录
	public String deleteCarPara() {
		if (null != getItems()) {
			System.out.println(getItems());
			String items[] = getItems().split("\\,");
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				// 通过导航参数类型id删除参数名
				carParaService.delete(WxModuleContentCarPara.class,
						"carParaGuid", items[i].trim());
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	@Override
	public WxModuleContentCarPara getModel() {
		// TODO Auto-generated method stub
		return carParameter;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == carParameter) {
			carParameter = new WxModuleContentCarPara();
		}
	}
}
