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
import com.tlan.common.model.car.WxModuleContentGzh;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class CarAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentCar> {

	private static Logger log = Logger.getLogger(CarAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCar> contentCarService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarPara> carPara;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentGzh> contentGzhService;

	private WxModuleContentCar car;

	private String carTypeGuid;

	private String carSfx;
	private String items;// 删除id集合
	public static WxModuleContentGzh gzh = new WxModuleContentGzh();

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getCarSfx() {
		return carSfx;
	}

	public void setCarSfx(String carSfx) {
		this.carSfx = carSfx;
	}

	public String getCarTypeGuid() {
		return carTypeGuid;
	}

	public void setCarTypeGuid(String carTypeGuid) {
		this.carTypeGuid = carTypeGuid;
	}

	private LoginBean loginBean = obtainLoginBean();

	public String getCarSfxListByModel() {
		List<WxModuleContentCar> cars = new ArrayList<WxModuleContentCar>();
		if (getCarTypeGuid().trim() == null) {
			List<WxModuleContentGzh> gzhs = new ArrayList<WxModuleContentGzh>();
			gzhs = contentGzhService.getAll(WxModuleContentGzh.class,
					"gzhHash", loginBean.getGzhHash());
			if (cars.size() > 0) {
				cars = contentCarService.getAll(WxModuleContentCar.class,
						new String[] { "carCartypeGuid" }, new Object[] { gzhs
								.get(0).getCarTypeGuid() }, "carPrice", "asc");
			}
		} else {
			cars = contentCarService.getAll(WxModuleContentCar.class,
					new String[] { "carCartypeGuid" },
					new Object[] { getCarTypeGuid().toString() }, "carPrice",
					"asc");
		}

		Comparator comp = new Comparator<WxModuleContentCar>() {
			@Override
			public int compare(WxModuleContentCar o1, WxModuleContentCar o2) {
				// TODO Auto-generated method stub
				return new Double(o1.getCarPrice()).compareTo(new Double(o2
						.getCarPrice()));
			}
		};

		Collections.sort(cars, comp);

		setMap(null, JSONArray.fromObject(cars), cars.size());
		return this.SUCCESS;
	}

	// 增加车款
	public String addCarSfx() {
		if (DataUtils.isNotNullOrEmpty(carSfx)) {
			JSONObject json = JSONObject.fromObject(carSfx);
			car.setCarGuid(DataUtils.getUUID());
			car.setCarName(json.getString("name"));
			car.setCarPrice(json.getString("price"));
			car.setCarCartypeGuid(json.getString("carTypeGuid"));
			car.setCarSortid(json.getString("inputSortId"));
			car.setCreatedOn(DateUtils.currentDatetime());
			car.setDeleteTag(0);
			try {
				contentCarService.save(car);
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("添加车款异常：" + e.getMessage());
				setMap(false, "添加失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 根据车款id查询车款
	@SuppressWarnings("static-access")
	public String queryCarSfx() {
		if (DataUtils.isNotNullOrEmpty(carSfx)) {
			JSONObject json = JSONObject.fromObject(carSfx);
			List<WxModuleContentCar> carModels = contentCarService.getAll(
					WxModuleContentCar.class, "carGuid",
					json.getString("carGuid"));
			System.out.println(json);
			car = carModels.get(0);
			setMap(true, "查询成功", car);
		} else {
			setMap(false, "读取数据失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 更新车款信息
	public String updateCarSfx() {
		if (DataUtils.isNotNullOrEmpty(carSfx)) {
			JSONObject json = JSONObject.fromObject(carSfx);
			List<WxModuleContentCar> carSfxs = contentCarService.getAll(
					WxModuleContentCar.class, "carGuid",
					json.getString("carGuid"));
			car = carSfxs.get(0);
			car.setCarName(json.getString("name"));
			car.setCarPrice(json.getString("price"));
			car.setCarSortid(json.getString("inputSortId"));
			car.setCreatedOn(DateUtils.currentDatetime());
			car.setDeleteTag(0);
			try {
				contentCarService.update(car);
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
	public String deleteCarSfx() {
		if (null != getItems()) {
			System.out.println(getItems());
			String items[] = getItems().split("\\,");
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				carPara.delete(WxModuleContentCarPara.class, "carParaCarGuid",
						items[i].trim());
				// 通过关键字，删除一条车型
				contentCarService.delete(WxModuleContentCar.class, "carGuid",
						items[i].trim());
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	@Override
	public WxModuleContentCar getModel() {
		// TODO Auto-generated method stub
		return car;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == car) {
			car = new WxModuleContentCar();
		}
	}
}
