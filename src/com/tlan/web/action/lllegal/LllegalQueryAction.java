package com.tlan.web.action.lllegal;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.tlan.common.action.BaseAction;
import com.tlan.common.utils.FileUtils;
import com.tlan.plugins.lllagel.vo.CityBean;
import com.tlan.plugins.lllagel.vo.QueueBean;
import com.tlan.plugins.lllagel.weiche.QueryUtil;

/**
 * 违章查询
 * 
 * @author magenm
 * @date 2014.2.23
 */
public class LllegalQueryAction extends BaseAction {
	private String carPrefix;
	private String cityPinyin;
	private String carNumber;
	private String engineNumber;
	private String bodyNumber;
	private String id;
	private String carString;

	/**
	 * 违章查询
	 * 
	 * @return
	 */
	public String query() {
		QueueBean bean = QueryUtil.search(carPrefix + carNumber, engineNumber,
				bodyNumber, cityPinyin);
		if ("queued".equals(bean.getStatus())) {
			carString = QueryUtil.getPinyin(carPrefix) + "--" + carNumber;
			setMap(true, carString, bean.getId());
		} else {
			setMap(false, "查询失败", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 违章查询
	 * 
	 * @return
	 */
	public String getResult() {
		QueueBean bean = QueryUtil.search2(id);

		// 判断是否执行结束
		if (!"finished,failed".contains(bean.getStatus())) {
			while (true) { // 循环查询
				bean = QueryUtil.search2(id);
				if ("finished,failed".contains(bean.getStatus())) {
					break;
				} else {
					try {
						Thread.sleep(bean.getSleep() * 1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}

		// 处理结果
		switch (bean.getStatus()) {
		case "finished": // 任务执行结束
			switch (bean.getResult().getVehicle_status()) {
			case "ok": // 车辆信息正确
				String[] cars = carString.split("--");
				carString = QueryUtil.getName(cars[0]) + cars[1];
				setMap(true, carString,
						JSONArray.fromObject(bean.getResult().getViolations()));
				break;
			case "error": // 车辆信息错误
				setMap(false, "车辆信息错误", null);
				break;
			case "net error": // 交管局网络原因暂时无法查询
				setMap(false, "交管局网络原因暂时无法查询", null);
				break;
			case "unknown": //
				setMap(false, "车辆信息正确性未知", null);
				break;
			}
			break;
		case "failed": // 任务执行失败
			setMap(false, "查询失败，请重新查询", null);
			break;
		}

		return this.SUCCESS;
	}

	/**
	 * 获取交管局 每天最多执行5次
	 * 
	 * @return
	 */
	public String createData() {
		try {
			String encoding = "UTF-8";
			List<CityBean> list = QueryUtil.queryCity();
			String provice = QueryUtil.getProviceJson();
			String city = QueryUtil.getCityJson(list);
			FileUtils util = new FileUtils();
			// 获取servlet上下文的绝对路径
			String basepath = ServletActionContext.getServletContext()
					.getRealPath(File.separator);
			util.createFile(basepath + "/scripts/jiaoguanju/provice.js",
					"var proviceData=" + provice.toString(), encoding);
			util.createFile(basepath + "/scripts/jiaoguanju/city.js",
					"var cityData=" + city.toString(), encoding);
			setMap(true, "处理成功", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMap(false, "处理失败", e.getMessage());
		}
		return "success";
	}

	public String getCarPrefix() {
		return carPrefix;
	}

	public void setCarPrefix(String carPrefix) {
		this.carPrefix = carPrefix;
	}

	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getBodyNumber() {
		return bodyNumber;
	}

	public void setBodyNumber(String bodyNumber) {
		this.bodyNumber = bodyNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarString() {
		return carString;
	}

	public void setCarString(String carString) {
		this.carString = carString;
	}
}
