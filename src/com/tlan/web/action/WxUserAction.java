package com.tlan.web.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.WxUser2car;
import com.tlan.common.model.WxUserLocation;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.view.WxUserCarProjectView;
import com.tlan.common.view.WxUserInfoView;
import com.tlan.contrants.ParamString;
import com.tlan.plugins.sms.jiaqi.SmsUtils;
import com.tlan.plugins.sms.ws.GtmcSms;

/**
 * web用户管理
 * 
 * @author magenm
 * 
 */
public class WxUserAction extends BaseAction implements Preparable,
		ModelDriven<WxUser> {

	private Log log = LogFactory.getLog(WxUserAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxUser> wxuserService;
	@Resource(name = "baseService")
	private IBaseService<WxUserInfoView> wxuserInfoViewService;
	@Resource(name = "baseService")
	private IBaseService<WxUser2car> wxuser2carService;
	@Resource(name = "baseService")
	private IBaseService<WxUserLocation> wxuserLocationService;
	@Resource(name = "baseService")
	private IBaseService<WxUserCarProjectView> wxUserCarProjcetService;

	private WxUser wxuser;

	private String carVin = "";
	private String carNumberPfx = "";
	private String carNumber = "";
	private String carBelongProject = "";
	private String carStatus = "";

	private String openid;
	private String gzhHash;

	private String validatorCode;
	private int vali;

	public String getLocation() {
		try {
			List<WxUserLocation> wxUserLocations = wxuserLocationService
					.getAll(WxUserLocation.class, "openId", openid);
			if (wxUserLocations.size() > 0) {
				WxUserLocation wu = wxUserLocations.get(0);
				if (DateUtils.between(
						DateUtils.parseDatetime(wu.getUpdateTime()),
						DateUtils.dateAdd(wu.getUpdateTime(),Calendar.HOUR, 1),
						DateUtils.now())) {
					setMap(true, "success", wu);
				} else {
					setMap(false, "您的地理位置已过期，您还没有开通位置信息，请开通位置信息或发送位置信息", null);
				}
			} else {
				setMap(false, "您还没有开通位置信息，请开通位置信息或发送位置信息", null);
			}
		} catch (Exception e) {
			setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 获取用户信息 及车辆信息
	 * 
	 * @return
	 */
	public String getInfo() {
		try {
			List<WxUserInfoView> wxUsers = wxuserInfoViewService.getAll(
					WxUserInfoView.class, "openId", openid);
			if (wxUsers.size() > 0) {
				WxUserInfoView wu = wxUsers.get(0);
				
				this.setSession(ParamString.WX_USER_SESSION_OBJECT, wu);
				
				setMap(true, "success", wu);
			} else {
				setMap(false, "no user exists", null);
			}
		} catch (Exception e) {
			setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	public String upgrade() {
		try {
			List<WxUser> wxUsers = wxuserService.getAll(WxUser.class, "openId",
					wxuser.getOpenId());
			if (wxUsers.size() > 0) {
				wxuser = wxUsers.get(0);
				wxuser.setMemberLevel(2);
				wxuserService.update(wxuser);

				setMap(true, "success", null);
			} else {
				setMap(false, "no user exists", null);
			}
		} catch (Exception e) {
			setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 升级所有 普通会员为高级灰源
	 * 
	 * @return
	 */
	public String syncTactCarInfo() {
		List<WxUser> wxUsers = wxuserService.getAll(WxUser.class, new String[] {
				"memberLevel", "status" }, new Object[] { 1, 1 });
		int newCarOwner = 0;
		String src = "http://203.88.200.34/gtmc_web/carinfo/getcarlistbyusertels";
		String userTels = "";
		int j = 0;
		for (int i = 0; i < wxUsers.size(); i++) {
			userTels += wxUsers.get(i).getUserTel() + ",";
			if ((i > 0 && i % 50 == 0)||i==wxUsers.size()-1) {
				userTels = userTels.substring(0, userTels.length()-1);
				String param = "?userTels=" + userTels;
				System.out.println("userTels="+userTels);
				HttpClient client = new DefaultHttpClient();
				System.out.println("before:"+DateUtils.currentDatetime());
				HttpGet get = new HttpGet(src + param);
				
				try {
					HttpResponse response = client.execute(get);
					System.out.println("after:"+DateUtils.currentDatetime());
					String result = EntityUtils.toString(response.getEntity());
					System.out.println(result);
					JSONObject returnData = JSONObject.fromObject(result);
					if (returnData.getBoolean("success")) {
						Object result2 = returnData.get("result");
						JSONArray res = JSONArray.fromObject(result2);
						for (int k = j; k <= i; k++) {
							WxUser wu = wxUsers.get(k);
							boolean upgrade = false;
							for (Object object : res) {
								JSONObject jo = (JSONObject) object;
								if (wu.getUserTel().equals(jo.getString("buyermobil1").trim())
										|| wu.getUserTel().equals(jo.getString("buyermobil2").trim())
										|| wu.getUserTel().equals(jo.getString("nomineemobil1").trim())
										|| wu.getUserTel().equals(jo.getString("nomineemobil2").trim())
										|| wu.getUserTel().equals(jo.getString("srvcustommobil1").trim())
										|| wu.getUserTel().equals(jo.getString("srvcustommobil2").trim())) {
									upgrade = true;

									WxUser2car wxUser2car = new WxUser2car();
									wxUser2car.setCarBelongProject(jo
											.getString("dealercode").trim());
									wxUser2car.setCarStatus("OK");
									wxUser2car.setCarVin(jo.getString("vinno").trim());
									wxUser2car.setOpenId(wu.getOpenId());
									String str = jo.getString("registerno").trim();
									wxUser2car.setCarNumberPfx(str.substring(0, 1));
									wxUser2car.setCarNumber(str.substring(1,str.length()).split("-")[0]
											+ str.substring(1, str.length()).split("-")[1]);
									wxUser2car.setUser2carGuid(DataUtils.getUUID());

									wxuser2carService.save(wxUser2car);
								}
							}
							if (upgrade) {
								newCarOwner++;
								wu.setMemberLevel(2);
								wxuserService.update(wu);
								//推消息，六天以内
								
							}
						}
					} else {
						System.out.println("sync failed!");
						setMap(false, "failed", "0");
						return this.SUCCESS;
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
					setMap(false, "server failed", "0");
					return this.SUCCESS;
				} catch (IOException e) {
					e.printStackTrace();
					setMap(false, "io failed", "0");
					return this.SUCCESS;
				}
				j = i+1;
				userTels = "";
			}
		}

		setMap(true, "success", newCarOwner + "");
		return this.SUCCESS;
	}

	public String getCarInfoFromTact() {
		String src = "http://203.88.200.34/gtmc_web/carinfo/getCarinfolist";// TODO
		String param = "?userTel=" + wxuser.getUserTel() + "&carNumber="
				+ carNumberPfx + carNumber;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(src + param);
		try {
			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			JSONObject res = JSONObject.fromObject(result);
			String message = res.getString("message");
			String carInfoStr = res.getString("result");
			JSONArray carInfo = JSONArray.fromObject(carInfoStr);

			setMap(true, message, carInfo);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			setMap(false, "ERROR", null);
		} catch (IOException e) {
			e.printStackTrace();
			setMap(false, "ERROR", null);
		}

		return this.SUCCESS;
	}

	public String addCar() {
		try {
			List<WxUser2car> wxUser2cars = wxuser2carService.getAll(
					WxUser2car.class, new String[] { "openId", "carNumberPfx",
							"carNumber" }, new Object[] { wxuser.getOpenId(),
							carNumberPfx, carNumber });
			WxUser2car wxUser2car = new WxUser2car();
			if (wxUser2cars.size() > 0) {
				wxUser2car = wxUser2cars.get(0);
				wxUser2car.setCarBelongProject(carBelongProject);
				wxUser2car.setCarStatus(carStatus);
				wxUser2car.setCarVin(carVin);
				wxuser2carService.update(wxUser2car);
			} else {
				wxUser2car.setCarBelongProject(carBelongProject);
				wxUser2car.setCarStatus(carStatus);
				wxUser2car.setCarVin(carVin);
				wxUser2car.setOpenId(wxuser.getOpenId());
				wxUser2car.setCarNumberPfx(carNumberPfx);
				wxUser2car.setCarNumber(carNumber);
				wxUser2car.setUser2carGuid(DataUtils.getUUID());

				wxuser2carService.save(wxUser2car);
			}
			setMap(true, "添加车型成功", wxUser2car);
		} catch (Exception e) {
			setMap(false, "添加车型失败", null);
		}

		return SUCCESS;
	}

	public String addInfo() {
		if (vali == 0 || (vali == 1 && this.checkValidatorCode(validatorCode))) {
			List<WxUser> wxUsers = wxuserService.getAll(WxUser.class, "openId",
					wxuser.getOpenId());

			if (wxUsers.size() > 0) {
				WxUser wu = wxUsers.get(0);
				List<WxUser2car> wxUser2cars = wxuser2carService.getAll(
						WxUser2car.class, new String[] { "openId",
								"carNumberPfx", "carNumber" }, new Object[] {
								wu.getOpenId(), carNumberPfx, carNumber });
				String user2carGuid = DataUtils.getUUID();
				boolean carChanged = false;
				if (wxUser2cars.size() == 0 && carNumber != null
						&& carNumber.length() > 0) {
					WxUser2car wxUser2car = new WxUser2car();
					wxUser2car.setOpenId(wu.getOpenId());
					wxUser2car.setCarNumber(carNumber);
					wxUser2car.setCarNumberPfx(carNumberPfx);
					wxUser2car.setUser2carGuid(user2carGuid);

					wxuser2carService.save(wxUser2car);
					carChanged = true;
				}

				// 如果该客户是非会员，则注册为普通会员。
				// 如果已经是会员则，会员身份不变
				if (wu.getMemberLevel() == null || wu.getMemberLevel() < 1) {
					wu.setMemberLevel(1);
				}
				if (wu.getRegisterTime() == null
						|| wu.getRegisterTime().length() == 0) {
					wu.setRegisterTime(DateUtils.currentDatetime());
					wu.setRegisterFrom(wxuser.getRegisterFrom());
				}
				wu.setModifyOn(DateUtils.currentDatetime());
				wu.setStatus(WxUser.GZ);
				wu.setUserName(wxuser.getUserName());
				wu.setUserSex(wxuser.getUserSex());
				wu.setUserTel(wxuser.getUserTel());
				if (carChanged) {
					wu.setUser2carGuid(user2carGuid);
				}

				wxuserService.update(wu);
				this.emptyValidatorCode();

				setMap(true, "success", wu);
			} else {
				setMap(false, "no user exists", null);
			}
		}
		return this.SUCCESS;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @Title: sendSMSValidatorCode
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月28日 下午2:50:41
	 */
	public String sendSMSValidatorCode() {
		GtmcSms sms = new GtmcSms();
		String validatorCode = DataUtils.getRandomIntString(5);
		// String result = SmsUtils.sendMsg(wxuser.getUserTel(), validatorCode);
		try {
			boolean result = GtmcSms.sendMessage(
					new String[] { wxuser.getUserTel() }, validatorCode);
			// String result = SmsUtils
			// .sendMsg(wxuser.getUserTel(), validatorCode);
			if (result) {
				this.setSession(ParamString.SMS_VALIDATION_CODE, validatorCode);
				System.out.println("发送成功");
				setMap(true, "发送成功", null);
			} else {
				System.out.println("发送失败");
				setMap(false, "发送失败", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败");
			setMap(false, "发送失败", e.getMessage());
		}

		/*
		 * String[] tels = new String[]{userTel}; boolean result = false; try{
		 * result = sms.sendMessage(tels, content); if (result) {
		 * System.out.println("发送成功"); setMap(true, "success", content); } else
		 * { System.out.println("发送失败"); setMap(false, "success", null); }
		 * }catch(Exception e){ setMap(false, "success", null); }
		 */

		return this.SUCCESS;
	}

	public String getUCPInfo() {
		try {
			List<WxUserCarProjectView> userCarProjectViews = wxUserCarProjcetService
					.getAll(WxUserCarProjectView.class, "openId",
							wxuser.getOpenId());
			if (userCarProjectViews.size() > 0) {
				setMap(true, "success", userCarProjectViews);
			} else {
				setMap(false, "no user exists", null);
			}
		} catch (Exception e) {
			setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}

	@Override
	public WxUser getModel() {
		// TODO Auto-generated method stub
		return wxuser;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxuser) {
			wxuser = new WxUser();
		}
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getValidatorCode() {
		return validatorCode;
	}

	public void setValidatorCode(String validatorCode) {
		this.validatorCode = validatorCode;
	}

	public String getCarVin() {
		return carVin;
	}

	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}

	public String getCarNumberPfx() {
		return carNumberPfx;
	}

	public void setCarNumberPfx(String carNumberPfx) {
		this.carNumberPfx = carNumberPfx;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarBelongProject() {
		return carBelongProject;
	}

	public void setCarBelongProject(String carBelongProject) {
		this.carBelongProject = carBelongProject;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public int getVali() {
		return vali;
	}

	public void setVali(int vali) {
		this.vali = vali;
	}

}
