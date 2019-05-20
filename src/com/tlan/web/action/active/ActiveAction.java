package com.tlan.web.action.active;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.active.WxModuleContentActive;
import com.tlan.common.model.active.WxModuleContentSign;
import com.tlan.common.model.active.WxModuleContentSignForm;
import com.tlan.common.service.IBaseService;

/**
 * 品牌活动报名
 * 
 * @author magenm
 * 
 */
public class ActiveAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentSign> {

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentActive> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentSign> signService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentSignForm> signFormService;

	private WxModuleContentSign sign;

	// validator
	private String validatorCode;

	/**
	 * 保存活动报名
	 * 
	 * @return
	 */
	public String sign() {
		try {
			if ("1".equals(getType()) && this.checkValidatorCode(validatorCode)) { // 如果需要验证，则判断验证码是否正确
				signService.save(sign);
				setMap(true, "添加成功", null);
			} else if ("0".equals(getType())) {
				signService.save(sign);
				setMap(true, "添加成功", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "添加失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 获取活动
	 * 
	 * @return
	 */
	public String get() {
		try {
			List<WxModuleContentActive> activeList = this.activeService.getAll(
					WxModuleContentActive.class, new String[] { "gzhHash",
							"status", "delFlag" },
					new Object[] { sign.getGzhHash(), 1, 0 });
			JSONArray ary = new JSONArray();
			for (WxModuleContentActive act : activeList) {
				JSONObject obj = new JSONObject();
				obj.put("picSrc", act.getActImage() + "");
				obj.put("title", act.getActName() + "");
				obj.put("date", act.getCreatedOn());
				obj.put("actGuid", act.getActGuid());
				obj.put("type", act.getActType());
				obj.put("type2", act.getActType2());
				obj.put("content", act.getActContent() + "");
				obj.put("desc", act.getActDesc() + "");
				obj.put("start", act.getStartOn());
				obj.put("end", act.getEndOn());
				ary.add(obj);
			}
			setMap(true, "获取数据成功", ary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 获取指定活动
	 * 
	 * @return
	 */
	public String getOne() {
		try {
			WxModuleContentActive act = this.activeService.get(
					WxModuleContentActive.class, "actGuid", sign.getActGuid());
			JSONObject obj = new JSONObject();
			obj.put("picSrc", act.getActImage() + "");
			obj.put("title", act.getActName() + "");
			obj.put("date", act.getCreatedOn());
			obj.put("actGuid", act.getActGuid());
			obj.put("type", act.getActType());
			obj.put("type2", act.getActType2());
			obj.put("content", act.getActContent() + "");
			obj.put("desc", act.getActDesc() + "");
			obj.put("start", act.getStartOn());
			obj.put("end", act.getEndOn());
			setMap(true, "获取数据成功", obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 会员中心获取活动报名记录
	 * 
	 * @return
	 */
	public String getOwnList() {
		try {
			List<WxModuleContentSign> signList = this.signService.getAll(
					WxModuleContentSign.class, new String[] { "openid" },
					new Object[] { sign.getOpenid() });
			JSONArray ary = new JSONArray();
			for (WxModuleContentSign act : signList) {
				JSONObject obj = new JSONObject();
				obj.put("actGuid", act.getActGuid() + "");
				obj.put("title", act.getActName());
				obj.put("date",
						null == act.getAttendOn() ? "" : act.getAttendOn());
				obj.put("remark",
						null == act.getRemark() ? "" : act.getRemark());
				int num = 0;
				if (null != act.getAttendNum()) {
					num += act.getAttendNum();
				}
				if (null != act.getAttendChild()) {
					num += act.getAttendChild();
				}
				obj.put("number", num > 0 ? "" : num);
				obj.put("arriveBy",
						null == act.getArriveBy() ? "" : act.getArriveBy());
				ary.add(obj);
			}
			setMap(true, "获取数据成功", ary);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 获取表单设置
	 * 
	 * @return
	 */
	public String getForm() {
		try {
			WxModuleContentSignForm signForm = this.signFormService
					.get(WxModuleContentSignForm.class, "actGuid",
							sign.getActGuid());
			setMap(true, "添加成功", signForm.getFormJson());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "添加失败", null);
		}
		return SUCCESS;
	}

	@Override
	public WxModuleContentSign getModel() {
		// TODO Auto-generated method stub
		return sign;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == sign) {
			sign = new WxModuleContentSign();
		}
	}

	public String getValidatorCode() {
		return validatorCode;
	}

	public void setValidatorCode(String validatorCode) {
		this.validatorCode = validatorCode;
	}

}
