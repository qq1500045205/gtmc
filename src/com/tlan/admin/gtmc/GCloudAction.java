package com.tlan.admin.gtmc;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.google.gxp.com.google.common.collect.Lists;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGcloudProvinceCtiy;
import com.tlan.common.model.WxProject;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.plugins.bdmap.PlaceBaseBean;
import com.tlan.plugins.bdmap.PlaceUtil;
import com.tlan.plugins.gtmc.CarModelBean;
import com.tlan.plugins.gtmc.CityBean;
import com.tlan.plugins.gtmc.DealerBean;
import com.tlan.plugins.gtmc.GtmcUtil;
import com.tlan.plugins.gtmc.ProvinceBean;

/**
 * 从GCloud同步数据
 * 
 * @author magenm
 * @Date 2014-2-25
 * 
 */
public class GCloudAction extends BaseAction {

	private String method;

	@Resource(name = "baseService")
	private IBaseService<WxGcloudProvinceCtiy> gcloudService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> projService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> carTypeService;

	/**
	 * 获取城市数据
	 * 
	 * @Title: getCity
	 * @Description: TODO
	 * @param @return
	 * @return List<WxGcloudProvinceCtiy>
	 * @throws
	 * @Date 2014年2月26日 下午3:13:51
	 */
	private static List<WxGcloudProvinceCtiy> getCity() {
		String data = GtmcUtil.deal("Interface_city");
		List<?> citys = JSONArray.toList(JSONArray.fromObject(data),
				new CityBean(), new JsonConfig());
		List<WxGcloudProvinceCtiy> cityList = Lists.newArrayList();
		for (Object c : citys) {
			CityBean city = (CityBean) c;
			WxGcloudProvinceCtiy wxCity = new WxGcloudProvinceCtiy();
			wxCity.setProvGuid(city.getCity_code());
			wxCity.setCityCode(city.getCity_code());
			wxCity.setCityName(city.getCity_name());
			wxCity.setConductType(Integer.parseInt(city.getConduct_type()));
			wxCity.setCreateTime(city.getCreate_time());
			wxCity.setLastUpdateTime(city.getLast_update_time());
			wxCity.setRemark(city.getRemark());
			wxCity.setMediaCode(city.getMedia_code());
			wxCity.setStatus("true".equalsIgnoreCase(city.getStatus()) ? 1 : 0);
			cityList.add(wxCity);
		}
		return cityList;
	}

	/**
	 * 根据城市编码获取城市名
	 * 
	 * @Title: getCity
	 * @Description: TODO
	 * @param @param cityList
	 * @param @param cityCode
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月26日 下午3:17:09
	 */
	public static String getCity(List<WxGcloudProvinceCtiy> cityList,
			String cityCode) {
		if (DataUtils.isNotNullOrEmpty(cityCode)) {
			for (WxGcloudProvinceCtiy city : cityList) {
				// System.out.println(city.getCityCode()+","+cityCode);
				if (cityCode.equals(city.getCityCode())) {
					return city.getCityName();
				}
			}
		}
		return null;
	}

	/**
	 * 指定方法名，进行数据处理
	 * 
	 * @return
	 */
	public String syncData() {

		switch (method) {
		case "GetCITYBYMEDIA": {
			gcloudService.saveList(getCity());
			break;
		}
		case "GetDEALERSBYMEDIA": {// 获取销售店数据
			String data = GtmcUtil.deal("Interface_dealers");
			List<?> dealers = JSONArray.toList(JSONArray.fromObject(data),
					new DealerBean(), new JsonConfig());

			// 获取城市数据
			List<WxGcloudProvinceCtiy> cityList = getCity();

			List<WxProject> projList = Lists.newArrayList();
			for (Object dealer : dealers) {
				DealerBean dlr = (DealerBean) dealer;
				WxProject proj = new WxProject();
				proj.setProjectGuid(dlr.getDealer_code()); // 为了同步处理方便，将dlrcode赋值给主键
				proj.setProjectName(dlr.getDealer_name());

				proj.setDealerCode(dlr.getDealer_code());
				proj.setDealerArea(dlr.getDealer_area());
				proj.setDealerCityCode(dlr.getDealer_city_code());
				proj.setDealerProvinceCode(dlr.getDealer_province_code());
				proj.setDealerUrl(dlr.getDealer_url());

				proj.setBrand(dlr.getBrand());
				proj.setAsTel(dlr.getAs_tel());
				proj.setConductType(dlr.getConduct_type());
				proj.setCreatedOn(DateUtils.currentDatetime());
				proj.setCreateTime(dlr.getCreate_time());
				proj.setLastUpdateTime(dlr.getLast_update_time());

				// 获取经纬度
				// System.out.println(getCity(cityList,
				// dlr.getDealer_city_code()));
				PlaceBaseBean place = PlaceUtil.getPoint(dlr.getAddress(),
						getCity(cityList, dlr.getDealer_city_code()));
				if (null != place) {
					proj.setLatitude(place.getResult().getLocation().getLat());
					proj.setLongitude(place.getResult().getLocation().getLng());
				}

				proj.setEmail(dlr.getEmail());
				proj.setFax(dlr.getFax());
				proj.setAddress(dlr.getAddress());
				proj.setGzhType("DLR");
				proj.setMobile(dlr.getTel());
				proj.setStatus(dlr.getStatus());
				proj.setRemark(dlr.getRemark());
				// proj.setHelpTel(dlr.getAs_tel());//通过xls导入
				// proj.setImage();//通过xls导入
				projList.add(proj);
			}
			projService.saveList(projList);
			break;
		}
		case "GetINFOBYMEDIA": { // 获取车型数据
			String data = GtmcUtil.deal("Interface_modle");
			List<?> carmodlels = JSONArray.toList(JSONArray.fromObject(data),
					new CarModelBean(), new JsonConfig());

			List<WxModuleContentCarType> carTypeList = carTypeService
					.getAll(WxModuleContentCarType.class);
			for (WxModuleContentCarType carType : carTypeList) {
				for (Object c : carmodlels) {
					CarModelBean carm = (CarModelBean) c;
					if (DataUtils.search(carm.getModle_name(),
							carType.getCarTypeTitle()).length() >= (carm
							.getModle_name().length() * 2 / 3)) {
						carType.setCarTypeCode(carm.getModle_code());
						carType.setCarTypeEn(carm.getModle_name_en());
						carType.setCarTypeName(carm.getModle_name());
						// 对"凯美瑞 尊瑞"特殊处理
						if (carType.getCarTypeTitle().contains("凯美瑞 尊瑞")) {
							carType.setRemark("凯美瑞 尊瑞");
						}
						carTypeService.update(carType);
						break;
					}
				}
			}
			break;
		}
		case "GetPROVINCEBYMEDIA": { // 获取省份数据
			String data = GtmcUtil.deal("Interface_province");
			List<?> provinces = JSONArray.toList(JSONArray.fromObject(data),
					new ProvinceBean(), new JsonConfig());
			List<WxGcloudProvinceCtiy> provList = Lists.newArrayList();
			for (Object p : provinces) {
				ProvinceBean prov = (ProvinceBean) p;
				WxGcloudProvinceCtiy wxProv = new WxGcloudProvinceCtiy();
				wxProv.setProvGuid(prov.getProvince_code());
				wxProv.setProvinceCode(prov.getProvince_code());
				wxProv.setProvinceName(prov.getProvince_name());
				wxProv.setConductType(Integer.parseInt(prov.getConduct_type()));
				wxProv.setCreateTime(prov.getCreate_time());
				wxProv.setLastUpdateTime(prov.getLast_update_time());
				wxProv.setRemark(prov.getRemark());
				wxProv.setMediaCode(prov.getMedia_code());
				wxProv.setStatus("true".equalsIgnoreCase(prov.getStatus()) ? 1
						: 0);
				provList.add(wxProv);

			}
			gcloudService.saveList(provList);
			break;
		}
		case "GetMODLEBYMEDIA":

			break;
		case "GetSERIESBYMEDIA":

			break;
		case "SetSalesLeads":

			break;
		}

		setMap(true, "处理成功", null);
		return SUCCESS;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
