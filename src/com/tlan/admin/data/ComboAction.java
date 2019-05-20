package com.tlan.admin.data;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxDynamicContent;
import com.tlan.common.model.WxDynamicContentReplyConfig;
import com.tlan.common.model.WxGcloudProvinceCtiy;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxMessageContent;
import com.tlan.common.model.WxModule;
import com.tlan.common.model.WxModulePage;
import com.tlan.common.model.WxProject;
import com.tlan.common.model.WxRuleKeyword;
import com.tlan.common.model.car.WxModuleContentCar;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.model.car.WxModuleContentGzh;
import com.tlan.common.model.mannual.WxModuleContentMannualType;
import com.tlan.common.service.IBaseService;
import com.tlan.common.view.WxModuleContentView;

public class ComboAction extends BaseAction {

	private static final long serialVersionUID = -275842586647421265L;
	@Resource(name = "baseService")
	private IBaseService<WxModulePage> wxPageService;
	@Resource(name = "baseService")
	private IBaseService<WxModule> wxModuleService;
	@Resource(name = "baseService")
	private IBaseService<WxMessageContent> wxMessageService;
	@Resource(name = "baseService")
	private IBaseService<WxRuleKeyword> wxRuleService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentView> contentViewService;
	@Resource(name = "baseService")
	private IBaseService<WxDynamicContent> dynamicContentService;
	@Resource(name = "baseService")
	private IBaseService<WxDynamicContentReplyConfig> dynamicContentReplyService;
	@Resource(name = "baseService")
	private IBaseService<WxGcloudProvinceCtiy> gcloudProvinceCtiyService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> projectService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> carTypeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCar> carModelService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentGzh> cntGzhService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualType> mannualtypeService;

	private LoginBean loginBean = obtainLoginBean();

	private String ruleGuid;
	private String carTypeGuid;
	private String gzhHash;
	private String provinceCode;
	private String cityCode;
	private String dynamicContentName;

	/**
	 * 获取combo组件
	 * 
	 * @return
	 */
	public String combo() {
		switch (getType()) {

		/**
		 * 增加省市类获取列表
		 */
		// 省市获取start

		case "wxProv": {

			/*
			 * 调用邵波提供方法 WxDealerAddressAction a=new WxDealerAddressAction();
			 * ary2=a.getAllProvinces();
			 */

			List<WxGcloudProvinceCtiy> provlist = gcloudProvinceCtiyService
					.getAll(WxGcloudProvinceCtiy.class);

			JSONArray ary = new JSONArray();
			for (WxGcloudProvinceCtiy prov : provlist) {
				JSONObject obj = new JSONObject();
				obj.put("provCode", prov.getProvinceCode());
				obj.put("text", prov.getProvinceName());
				obj.put("value", prov.getProvinceCode());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "wxCity": {
			List<WxGcloudProvinceCtiy> citylist = gcloudProvinceCtiyService
					.getAll(WxGcloudProvinceCtiy.class, "provinceCode",
							provinceCode);
			JSONArray ary = new JSONArray();
			for (WxGcloudProvinceCtiy city : citylist) {
				JSONObject obj = new JSONObject();
				obj.put("cityCode", city.getCityCode());
				obj.put("text", city.getCityName());
				obj.put("value", city.getCityCode());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "wxProject": {
			List<WxProject> projectlist = projectService
					.getAll(WxProject.class);
			JSONArray ary = new JSONArray();
			for (WxProject project : projectlist) {
				JSONObject obj = new JSONObject();
				obj.put("projectGuid", project.getProjectGuid());
				obj.put("text", project.getProjectName());
				obj.put("value", project.getProjectGuid());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}

		// 省市获取end

		case "wxModulePage": {
			List<WxModulePage> wxPages = this.wxPageService
					.getAll(WxModulePage.class);
			jsonObject = JSONArray.fromObject(wxPages);
			break;
		}
		case "wxModule": {
			List<WxModule> wxModules = this.wxModuleService
					.getAll(WxModule.class);
			jsonObject = JSONArray.fromObject(wxModules);
			break;
		}
		case "wxModuleContent": {
			List<WxModuleContentView> wxModuleContents = this.contentViewService
					.getAll(WxModuleContentView.class, "gzhHash",
							loginBean.getGzhHash());
			JSONArray ary = new JSONArray();
			for (WxModuleContentView wm : wxModuleContents) {
				JSONObject obj = new JSONObject();
				obj.put("moduleName", wm.getModuleName());
				obj.put("contentGuid", wm.getContentGuid());
				obj.put("contentPage", wm.getContentPage());
				ary.add(obj);
			}
			jsonObject = JSONArray.fromObject(ary);
			break;
		}
		// 获取动态内容数据 ----by guojing
		case "wxDynamicContent": {
			List<WxDynamicContent> dynamicContents = this.dynamicContentService
					.getAll(WxDynamicContent.class);
			jsonObject = JSONArray.fromObject(dynamicContents);
			break;
		}
		// 获取动态数据 回复内容----by guojing
		case "wxDynamicContentReply": {
			List<WxDynamicContentReplyConfig> dynamicContentReplys = this.dynamicContentReplyService
					.getAll(WxDynamicContentReplyConfig.class,
							new String[] { "dynamicContentName" },
							new Object[] { dynamicContentName },
							new String[] { "=" }, "order asc");
			jsonObject = JSONArray.fromObject(dynamicContentReplys);
			break;
		}

		case "wxMessage": {
			List<WxMessageContent> wxMessages = this.wxMessageService.getAll(
					WxMessageContent.class, "gzhHash", loginBean.getGzhHash());
			jsonObject = JSONArray.fromObject(wxMessages);
			break;
		}
		case "wxKeys": {
			List<WxRuleKeyword> wxKeys = this.wxRuleService.getAll(
					WxRuleKeyword.class, "ruleGuid", ruleGuid);
			JSONArray ary = new JSONArray();
			for (WxRuleKeyword key : wxKeys) {
				JSONObject obj = new JSONObject();
				obj.put("name", key.getKwName());
				obj.put("kwGuid", key.getKwGuid());
				obj.put("type", key.getType());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "assignModule": {
			List<WxModule> wxModules = this.wxModuleService
					.getAll(WxModule.class);
			JSONArray ary = new JSONArray();
			for (WxModule wm : wxModules) {
				JSONObject obj = new JSONObject();
				obj.put("text", wm.getModuleName());
				obj.put("value", wm.getModuleGuid());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "assignGzh": {
			List<WxGongzhonghao> gzhs = this.gzhService
					.getAll(WxGongzhonghao.class);
			JSONArray ary = new JSONArray();
			for (WxGongzhonghao gzh : gzhs) {
				JSONObject obj = new JSONObject();
				obj.put("text", gzh.getGzhName());
				obj.put("value", gzh.getGzhGuid());
				obj.put("gzhHash", gzh.getGzhHash());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "assignGzhHash": {
			List<WxGongzhonghao> gzhs = this.gzhService
					.getAll(WxGongzhonghao.class);
			JSONArray ary = new JSONArray();
			for (WxGongzhonghao gzh : gzhs) {
				JSONObject obj = new JSONObject();
				obj.put("text", gzh.getGzhName());
				obj.put("value", gzh.getGzhHash());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "carType": {
			List<WxModuleContentGzh> list = cntGzhService
					.getAll(WxModuleContentGzh.class, "gzhHash",
							loginBean.getGzhHash());
			StringBuffer sb = new StringBuffer();
			int idx = -1;
			for (WxModuleContentGzh gzh : list) {
				sb.append("'");
				sb.append(gzh.getCarTypeGuid());
				sb.append("'");
				idx++;
				if (idx < list.size() - 1) {
					sb.append(",");
				}
			}
			List<WxModuleContentCarType> carTypes = carTypeService.getAll(
					WxModuleContentCarType.class,
					new String[] { "carTypeGuid" },
					new String[] { sb.toString() }, new String[] { "in" },
					"carTypeSortid asc");
			JSONArray ary = new JSONArray();
			for (WxModuleContentCarType carType : carTypes) {
				JSONObject obj = new JSONObject();
				obj.put("carTypeGuid", carType.getCarTypeGuid());
				obj.put("text", carType.getCarTypeTitle());
				obj.put("value", carType.getCarTypeGuid());
				ary.add(obj);
			}
			jsonObject = ary;

			break;
		}
		case "carWxType": {

			List<WxModuleContentGzh> list = cntGzhService.getAll(
					WxModuleContentGzh.class, "gzhHash", getGzhHash());
			StringBuffer sb = new StringBuffer();
			int idx = -1;
			for (WxModuleContentGzh gzh : list) {
				sb.append("'");
				sb.append(gzh.getCarTypeGuid());
				sb.append("'");
				idx++;
				if (idx < list.size() - 1) {
					sb.append(",");
				}
			}
			List<WxModuleContentCarType> carTypes = carTypeService.getAll(
					WxModuleContentCarType.class,
					new String[] { "carTypeGuid" },
					new String[] { sb.toString() }, new String[] { "in" },
					"carTypeSortid asc");
			JSONArray ary = new JSONArray();
			for (WxModuleContentCarType carType : carTypes) {
				JSONObject obj = new JSONObject();
				obj.put("carTypeGuid", carType.getCarTypeGuid());
				obj.put("text", carType.getCarTypeTitle());
				obj.put("value", carType.getCarTypeGuid());
				ary.add(obj);
			}
			jsonObject = ary;

			break;
		}
		case "carWxModel": {
			List<WxModuleContentCar> carWxModels = carModelService.getAll(
					WxModuleContentCar.class,
					new String[] { "carCartypeGuid" },
					new String[] { carTypeGuid }, "carPrice", "asc");
			JSONArray ary = new JSONArray();
			for (WxModuleContentCar carModel : carWxModels) {
				JSONObject obj = new JSONObject();
				obj.put("carGuid", carModel.getCarGuid());
				obj.put("text", carModel.getCarName());
				obj.put("value", carModel.getCarGuid());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		case "mannualType": {
			List<WxModuleContentMannualType> types = this.mannualtypeService
					.getAll(WxModuleContentMannualType.class);
			JSONArray ary = new JSONArray();
			for (WxModuleContentMannualType type : types) {
				JSONObject obj = new JSONObject();
				obj.put("typename", type.getTypeName());
				obj.put("typeguid", type.getTypeGuid());
				ary.add(obj);
			}
			jsonObject = ary;
			break;
		}
		}

		return this.SUCCESS;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhhash) {
		this.gzhHash = gzhhash;
	}

	public String getRuleGuid() {
		return ruleGuid;
	}

	public void setRuleGuid(String ruleGuid) {
		this.ruleGuid = ruleGuid;
	}

	public String getCarTypeGuid() {
		return carTypeGuid;
	}

	public void setCarTypeGuid(String carTypeGuid) {
		this.carTypeGuid = carTypeGuid;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDynamicContentName() {
		return dynamicContentName;
	}

	public void setDynamicContentName(String dynamicContentName) {
		this.dynamicContentName = dynamicContentName;
	}

}
