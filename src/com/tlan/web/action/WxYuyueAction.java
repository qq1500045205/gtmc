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
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.model.yuyue.WxModuleContentYuyue;
import com.tlan.common.model.yuyue.WxModuleYuyueView;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.plugins.gtmc.GtmcUtil;

/**
 * web用户管理
 * 
 * @author magenm
 * 
 */
public class WxYuyueAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentYuyue> {

	private Log log = LogFactory.getLog(WxYuyueAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentYuyue> wxYuyueService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleYuyueView> wxYuyueViewService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> contentCarTypeService;

	private WxModuleContentYuyue wxYuyue;

	private String open_id;

	private String gzhHash;

	// 预约试驾
	private String carTypeGuid;
	private String carTypeName;
	// 维修保养
	private String carNumber;

	// 预约列表返回
	private String yuyue_type;

	private String carNumberPfx;
	// 发送短信
	private String userTel;

	private String validatorCode;
	private String mediaCode;

	private String dealerProvince;
	private String dealerProvinceName;
	private String dealerCity;
	private String dealerCityName;
	private String provinceName;
	private String cityName;
	private String dealerCode;
	private String userName;

	public String addShijia() {
		// 添加预约试驾

		// YuyueType = 1
		// Field1 = CarTypeGuid
		// Field2 = CarTypeName

		if (this.checkValidatorCode(validatorCode)) {
			String yuyueGuid = DataUtils.getUUID();
			this.wxYuyue.setYuyueGuid(yuyueGuid);
			this.wxYuyue.setYuyueType("1");
			this.wxYuyue.setDealerGuid(dealerCode);
			this.wxYuyue.setYuyueField1(carTypeGuid);
			this.wxYuyue.setYuyueField2(carTypeName);
			this.wxYuyue.setCreatedOn(DateUtils.currentDatetime());
			// To Save
			this.wxYuyueService.save(this.wxYuyue);

			setMap(true, "success", wxYuyue);
		}

		{
			WxModuleContentCarType wcc = contentCarTypeService.get(
					WxModuleContentCarType.class, "carTypeGuid", carTypeGuid);
			String json = "[{";
			json += "\"media_code\": \""
					+ PropertiesUtil.getProperty(ParamString.MEDIA_CODE_SHIJIA)
					+ "\",";
			json += "\"clue_code\": \"" + DateUtils.millis() + "\",";
			json += "\"dealer_code\": \"" + dealerCode + "\",";
			json += "\"clue_type\": \"1\",";
			json += "\"is_publicorder\": \"1\",";
			json += "\"modle_code\": \"" + wcc.getCarTypeCode() + "\",";
			json += "\"modle_name\": \""
					+ DataUtils.chinaToUnicode(wcc.getCarTypeName()) + "\",";
			json += "\"status\": \"0\",";
			json += "\"province_code\": \"" + dealerProvince + "\",";
			json += "\"province_name\": \""
					+ DataUtils.chinaToUnicode(dealerProvinceName) + "\",";
			json += "\"city_code\": \"" + dealerCity + "\",";

			json += "\"city_name\": \""
					+ DataUtils.chinaToUnicode(dealerCityName) + "\",";
			json += "\"create_time\": \""
					+ DateUtils.currDate("yyyy-MM-dd HH:mm:ss") + "\",";
			json += "\"customer_name\": \""
					+ DataUtils.chinaToUnicode(userName) + "\",";

			json += "\"mobile\": \"" + userTel + "\",";

			if (DataUtils.isNotNullOrEmpty(wcc.getRemark())) {
				json += "\"remark\": \""
						+ DataUtils.chinaToUnicode(wcc.getRemark()) + "\"";
			}
			json += "}]";
			log.info("addShijia:" + json);
			boolean result = GtmcUtil.setData(json, 1);//  type 1:试驾申请
			log.info("deal result :" + result);
		}

		return this.SUCCESS;
	}

	public String addWeiXiuBaoYang() {
		// 添加维修保养

		// YuyueType = 2
		// Field1 = CarNo
		if (this.checkValidatorCode(validatorCode)) {
			String yuyueGuid = DataUtils.getUUID();
			this.wxYuyue.setYuyueGuid(yuyueGuid);
			this.wxYuyue.setYuyueType("2");
			this.wxYuyue.setDealerGuid(dealerCode);
			this.wxYuyue.setYuyueField1(carNumberPfx);
			this.wxYuyue.setYuyueField2(carNumber);
			this.wxYuyue.setCreatedOn(DateUtils.currentDatetime());

			// To Save
			this.wxYuyueService.save(this.wxYuyue);
			setMap(true, "success", wxYuyue);
		}

		{
			String json = "[{";
			json += "'media_code': '" + mediaCode + "',";
			json += "'clue_code': '" + 1 + "',";
			json += "'dealer_code': '" + dealerCode + "',";
			json += "'clue_type': '1',";
			json += "'is_publicorder': '1',";
			json += "'modle_code': '1',";
			json += "'province_code': '" + dealerProvince + "',";
			json += "'province_name': '" + dealerProvinceName + "',";
			json += "'city_code': '" + dealerCity + "',";
			json += "'city_name': '" + dealerCityName + "',";
			json += "'create_time': '" + DateUtils.currentDatetime() + "',";
			json += "'customer_name': '" + userName + "',";
			json += "'phone': '" + userTel + "',";
			json += "}]";

			// GtmcUtil.setData(json, 1);//  type 1:试驾申请,保养的数据暂不回写
		}

		return this.SUCCESS;
	}

	public String addNianShen() {
		// 添加年审

		// YuyueType = 3
		// Field1 = CarNo
		if (this.checkValidatorCode(validatorCode)) {
			String yuyueGuid = DataUtils.getUUID();
			this.wxYuyue.setYuyueGuid(yuyueGuid);
			this.wxYuyue.setYuyueType("3");
			this.wxYuyue.setDealerGuid(dealerCode);
			this.wxYuyue.setYuyueField1(carNumberPfx);
			this.wxYuyue.setYuyueField2(carNumber);
			this.wxYuyue.setCreatedOn(DateUtils.currentDatetime());

			// To Save
			this.wxYuyueService.save(this.wxYuyue);
			setMap(true, "success", wxYuyue);
		}
		return this.SUCCESS;
	}

	public String getAllList() {
		
		try {
			long count;
			LoginBean loginBean = this.obtainLoginBean();
			String gzhHash = loginBean.getGzhHash();
			List<WxModuleYuyueView> yuyueList = null;
			if (loginBean.getRoleType().equals("ADMIN")
					|| loginBean.getRoleType().equals("DISTADMIN")) {
				yuyueList = this.wxYuyueViewService.findPage(
						WxModuleYuyueView.class, this.getStart(),
						this.getLimit(), new String[] { "yuyue_type" },
						new Object[] { yuyue_type });
				count = this.wxYuyueViewService.getCount(WxModuleYuyueView.class,
						new String[] { "yuyue_type" },
						new Object[] { yuyue_type }, new String[] { "=" });
			} else {
				if (yuyue_type == null) {
					yuyueList = this.wxYuyueViewService.findPage(
							WxModuleYuyueView.class, this.getStart(),
							this.getLimit(), new String[] { "gzhHash" },
							new Object[] { gzhHash }, new String[] { "=" },
							"created_on desc");
					count = this.wxYuyueViewService.getCount(WxModuleYuyueView.class,
							new String[] { "gzhHash" },
							new Object[] { gzhHash }, new String[] { "=" });
				} else {
					yuyueList = this.wxYuyueViewService.findPage(
							WxModuleYuyueView.class, this.getStart(),
							this.getLimit(), new String[] { "yuyue_type",
									"gzhHash" }, new Object[] { yuyue_type,
									gzhHash }, new String[] { "=","=" },
							"created_on desc");
					count = this.wxYuyueViewService.getCount(WxModuleYuyueView.class,
							new String[] { "yuyue_type","gzhHash" },
							new Object[] { yuyue_type,gzhHash }, new String[] { "=","=" });
				}
			}
			/*
			 * type = 1 : 预约试驾 type = 2 : 预约保养 type = 3 : 预约年审
			 */
			this.setMap(null, JSONArray.fromObject(yuyueList), count);
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	public String getAllOwnList() {
		try {
			List<WxModuleYuyueView> yuyueList = null;

			yuyueList = this.wxYuyueViewService.findPage(
					WxModuleYuyueView.class, this.getStart(), this.getLimit(),
					new String[] { "openid" }, new Object[] { open_id },
					new String[] { "=" }, "yuyue_guid desc");

			/*
			 * type = 1 : 预约试驾 type = 2 : 预约保养 type = 3 : 预约年审
			 */
			ArrayList<Object> retlist = new ArrayList<Object>();
			for (int i = 0; i < yuyueList.size(); i++) {
				WxModuleYuyueView yuyue = yuyueList.get(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("yuyueTime",
						yuyue.getYuyueTime() == null ? "" : yuyue
								.getYuyueTime());
				map.put("address",
						yuyue.getAddress() == null ? "" : yuyue.getAddress());
				map.put("createdOnTime", yuyue.getCreatedOnString());
				map.put("projectName", yuyue.getProjectName() == null ? ""
						: yuyue.getProjectName());
				map.put("yuyueField1", yuyue.getYuyueField1() == null ? ""
						: yuyue.getYuyueField1());
				map.put("yuyueField2", yuyue.getYuyueField2() == null ? ""
						: yuyue.getYuyueField2());
				map.put("yuyueType", yuyue.getYuyueType());
				map.put("yuyueMemo",
						yuyue.getYuyueMemo() == null ? "" : yuyue
								.getYuyueMemo());
				retlist.add(map);

			}
			this.setMap(null, JSONArray.fromObject(retlist), retlist.size());
		} catch (Exception e) {
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	@Override
	public WxModuleContentYuyue getModel() {
		// TODO Auto-generated method stub
		return wxYuyue;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxYuyue) {
			wxYuyue = new WxModuleContentYuyue();
		}
	}

	public String getCarTypeGuid() {
		return carTypeGuid;
	}

	public void setCarTypeGuid(String carTypeGuid) {
		this.carTypeGuid = carTypeGuid;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getValidatorCode() {
		return validatorCode;
	}

	public void setValidatorCode(String validatorCode) {
		this.validatorCode = validatorCode;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getMediaCode() {
		return mediaCode;
	}

	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}

	public String getDealerProvinceName() {
		return dealerProvinceName;
	}

	public void setDealerProvinceName(String dealerProvinceName) {
		this.dealerProvinceName = dealerProvinceName;
	}

	public String getDealerCityName() {
		return dealerCityName;
	}

	public void setDealerCityName(String dealerCityName) {
		this.dealerCityName = dealerCityName;
	}

	public String getYuyue_type() {
		return yuyue_type;
	}

	public void setYuyue_type(String yuyue_type) {
		this.yuyue_type = yuyue_type;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarNumberPfx() {
		return carNumberPfx;
	}

	public void setCarNumberPfx(String carNumberPfx) {
		this.carNumberPfx = carNumberPfx;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerProvince() {
		return dealerProvince;
	}

	public void setDealerProvince(String dealerProvince) {
		this.dealerProvince = dealerProvince;
	}

	public String getDealerCity() {
		return dealerCity;
	}

	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
