package com.tlan.web.action.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.car.WxModuleContentCar;
import com.tlan.common.model.car.WxModuleContentCarPara;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.model.car.WxModuleContentGzh;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class CarTypeAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentCarType> {
	private static Logger log = Logger.getLogger(CarTypeAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> contentCarTypeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCar> contentCarService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarPara> carPara;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentGzh> contentGzhService;

	private WxModuleContentCarType carType;
	private String carModel;
	private String items;
	private String gzhHash;

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	private LoginBean loginBean = obtainLoginBean();

	public String addCarModel() {
		if (DataUtils.isNotNullOrEmpty(carModel)) {
			JSONObject json = JSONObject.fromObject(carModel);
			// 先保存公众号表中对应关系，然后保存车型表中对应关系
			String carTypeGuid = DataUtils.getUUID();
			carType.setCarTypeGuid(carTypeGuid);
			carType.setCarTypeTitle(json.getString("title"));
			carType.setCarTypeImageUrl(json.getString("picSrc"));
			carType.setCarTypeParamUrl(json.getString("paramPicSrc"));
			carType.setCarTypeMaxprice(json.getString("inputMaxPrice"));
			carType.setCarTypeMinprice(json.getString("inputMinPrice"));
			carType.setCarTypeMerit(json.getString("content"));
			carType.setCarTypeSortid(json.getInt("inputSortId"));
			carType.setCreatedOn(DateUtils.currentDatetime());
			carType.setDeleteTag(0);
			
			WxModuleContentGzh gzh = new WxModuleContentGzh();
			gzh.setGzhGuid(DataUtils.getUUID());
			gzh.setGzhHash(loginBean.getGzhHash());
			gzh.setCarTypeGuid(carTypeGuid);
			gzh.setCreatedOn(DateUtils.currentDatetime());
			gzh.setDeleteTag(0);
			try {
				contentCarTypeService.save(carType);
				contentGzhService.save(gzh);
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("添加车型异常：" + e.getMessage());
				setMap(false, "添加失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	public String editCarModel() {
		if (DataUtils.isNotNullOrEmpty(carModel)) {
			JSONObject json = JSONObject.fromObject(carModel);
			List<WxModuleContentCarType> carModels = contentCarTypeService
					.getAll(WxModuleContentCarType.class, "carTypeGuid",
							json.getString("carTypeGuid"));
			carType = carModels.get(0);
			carType.setCarTypeTitle(json.getString("title"));
			carType.setCarTypeImageUrl(json.getString("picSrc"));
			carType.setCarTypeParamUrl(json.getString("paramPicSrc"));
			carType.setCarTypeMaxprice(json.getString("inputMaxPrice"));
			carType.setCarTypeMinprice(json.getString("inputMinPrice"));
			carType.setCarTypeMerit(json.getString("content"));
			carType.setCarTypeSortid(json.getInt("inputSortId"));
			carType.setCreatedOn(DateUtils.currentDatetime());
			carType.setDeleteTag(0);
			
			//2014-3-17  magenming  add
			carType.setCarTypeCode(json.getString("carTypeCode"));
			carType.setCarTypeName(json.getString("carTypeName"));
			carType.setCarTypeEn(json.getString("carTypeEn"));
			carType.setRemark(json.getString("remark"));
			//2014-3-17  magenming  add
			
			try {
				contentCarTypeService.update(carType);
				setMap(true, "修改成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("修改车型异常：" + e.getMessage());
				setMap(false, "修改失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 真删除一条记录
	public String deleteCarModel() {
		if (null != getItems()) {
			System.out.println(getItems());
			String items[] = getItems().split("\\,");
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				// 删除该车型下面的车款信息,首先需要获取车款的属性信息并且删除
				List<WxModuleContentCar> carsfxs = contentCarService.getAll(
						WxModuleContentCar.class, "carCartypeGuid",
						items[i].trim());
				for (int j = 0; j < carsfxs.size(); j++) {
					// 通过car_id删除WxModuleContentCarPara相关数据
					carPara.delete(WxModuleContentCarPara.class,
							"carParaCarGuid", carsfxs.get(j).getCarGuid());
				}
				// 通过车型id删除WxModuleContentCarType中相关数据
				contentCarTypeService.delete(WxModuleContentCarType.class,
						"carTypeGuid", items[i].trim());
				// 通过关键字，删除一条车型
				contentCarService.delete(WxModuleContentCar.class,
						"carCartypeGuid", items[i].trim());
				// 删除公众号表中的对应的车型信息,双条件
				contentGzhService
						.delete(WxModuleContentGzh.class, new String[] {
								"carTypeGuid", "gzhHash" }, new Object[] {
								items[i].trim(), loginBean.getGzhHash() });
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	public String getCarType() {
		try {
			List<WxModuleContentCarType> carTypes = contentCarTypeService
					.getAll(WxModuleContentCarType.class, "carTypeGuid",
							carType.getCarTypeGuid());
			setMap(true, "success", carTypes.get(0));
		} catch (Exception e) {
			setMap(false, "faile", null);
		}

		return this.SUCCESS;
	}

	public String getCarModels() {
		try {
			List<WxModuleContentCar> carModels = contentCarService.getAll(
					WxModuleContentCar.class, "carCartypeGuid",
					carType.getCarTypeGuid());
			setMap(true, "success", carModels);
		} catch (Exception e) {
			setMap(false, "fail", null);
		}

		return this.SUCCESS;
	}

	public String getAllCarType() {
//		if (getGzhHash() == null) {
//			setMap(false, "没有数据", null);
//		} else {
//			List<WxModuleContentCarType> carTypes = new ArrayList<WxModuleContentCarType>();
//			List<WxModuleContentGzh> Gzhs = new ArrayList<WxModuleContentGzh>();
//
//			Gzhs = contentGzhService.getAll(WxModuleContentGzh.class,
//					"gzhHash", getGzhHash().trim());
//			String guid = "";
//			for (int i = 0; i < Gzhs.size(); i++) {
//				guid += "'" + Gzhs.get(i).getCarTypeGuid() + "',";
//			}
//			guid = guid.substring(0, guid.length() - 1);
//			carTypes = contentCarTypeService.getAll(
//					WxModuleContentCarType.class,
//					new String[] { "carTypeGuid" }, new Object[] { guid },
//					new String[] { "in" });
//
//			Comparator comp = new Comparator<WxModuleContentCarType>() {
//				@Override
//				public int compare(WxModuleContentCarType o1,
//						WxModuleContentCarType o2) {
//					// TODO Auto-generated method stub
//					return new Double(o1.getCarTypeSortid())
//							.compareTo(new Double(o2.getCarTypeSortid()));
//				}
//			};
//
//			Collections.sort(carTypes, comp);
//			setMap(true, "success", carTypes);
//		}
//		return this.SUCCESS;
		
		List<WxModuleContentCarType> carTypes = new ArrayList<WxModuleContentCarType>();

		carTypes = contentCarTypeService.getAll(WxModuleContentCarType.class,
				new String[] {}, new Object[] {}, new String[] {},
				"carTypeSortid asc");
		setMap(true, "success", carTypes);
		
		return this.SUCCESS;
	}

	public String queryCarModel() {
		if (DataUtils.isNotNullOrEmpty(carModel)) {
			JSONObject json = JSONObject.fromObject(carModel);
			List<WxModuleContentCarType> carModels = contentCarTypeService
					.getAll(WxModuleContentCarType.class, "carTypeGuid",
							json.getString("carTypeGuid"));
			System.out.println(json.toString());
			carType = carModels.get(0);
			setMap(true, "查询成功", carType);
		} else {
			setMap(false, "读取数据失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 获取车型列表
	 * 
	 * @return
	 */
	public String getCarModelList() {
		// List<WxModuleContentCarType> carTypes = new
		// ArrayList<WxModuleContentCarType>();
		// List<WxModuleContentCarType> temps = new
		// ArrayList<WxModuleContentCarType>();
		// List<WxModuleContentGzh> Gzhs = new ArrayList<WxModuleContentGzh>();
		//
		// Gzhs = contentGzhService.getAll(WxModuleContentGzh.class, "gzhHash",
		// loginBean.getGzhHash());
		// for (int i = 0; i < Gzhs.size(); i++) {
		// temps = contentCarTypeService.getAll(WxModuleContentCarType.class,
		// new String[] { "carTypeGuid" }, new Object[] { Gzhs.get(i)
		// .getCarTypeGuid() }, "carTypeSortid", "asc");
		// if (temps.size() > 0) {
		// carTypes.add(temps.get(0));
		// }
		// }
		// setMap(null, JSONArray.fromObject(carTypes), carTypes.size());
		// return this.SUCCESS;

		List<WxModuleContentCarType> carTypes = new ArrayList<WxModuleContentCarType>();

		carTypes = contentCarTypeService.getAll(WxModuleContentCarType.class,
				new String[] {}, new Object[] {}, new String[] {},
				"carTypeSortid asc");
		setMap(null, JSONArray.fromObject(carTypes), carTypes.size());
		return this.SUCCESS;
	}

	/**
	 * 获取车型数
	 * 
	 * @return
	 */
	public String getCarModelListCount() {
		// if (null != loginBean.getGzhType()
		// && "DLR".equals(loginBean.getGzhType())) {
		// List<WxModuleContentGzh> Gzhs = new ArrayList<WxModuleContentGzh>();
		//
		// Gzhs = contentGzhService.getAll(WxModuleContentGzh.class,
		// "gzhHash", loginBean.getGzhHash());
		//
		// setMap(true, "success", Gzhs.size() + "");
		// } else {
		// setMap(false, "not DLR", null);
		// }
		//
		// return this.SUCCESS;

		List<WxModuleContentCarType> carTypes = new ArrayList<WxModuleContentCarType>();
		carTypes = contentCarTypeService.getAll(WxModuleContentCarType.class);

		setMap(true, "success", carTypes.size() + "");

		return this.SUCCESS;
	}

	@Override
	public WxModuleContentCarType getModel() {
		// TODO Auto-generated method stub
		return carType;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == carType) {
			carType = new WxModuleContentCarType();
		}
	}

}
