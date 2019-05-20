package com.nec.web.action.creditactive;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.creditactive.WxMemberCreditActiveF;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.http.HttpUtils;

/**
 * 积分活动
 * 
 * @author luo
 * 
 */
public class CreditActiveAction extends BaseAction implements Preparable,
		ModelDriven<WxMemberCreditActiveF> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(CreditActiveAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditActiveF> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;

	private WxMemberCreditActiveF active;
	// 获取登录用户信息
	private LoginBean user = obtainLoginBean();


	/**
	 * 执行数据操作
	 * 
	 * @return
	 */
	public String creditActive() {

		switch (getType()) {
		case "activeSave": {
			// 保存积分活动
			try {
				active.setCreatedBy(user.getUserName());
				active.setCreatedOn(DateUtils.currentDatetime());
				active.setDealerCode("DIST");
				active.setMemberCreditGuid(DataUtils.getUUID());
				active.setStatus(0);
				active.setDelFlag(0);
				active.setSource(0);
				//activeService.save(active);
				//插入二维码相关信息
				if(user.getGzhHash()==null){
					user.setGzhHash("-2042484612");
				}
				int scene=1;
			List list=activeService.hqlQuery("SELECT MAX(act.sceneId) FROM  WxMemberCreditActiveF act WHERE act.gzhHash="+user.getGzhHash()+"");
			if(list.get(0)!=null){
				scene=Integer.parseInt(list.get(0).toString())+1;
			}
				List<WxGongzhonghao> wxGongzhonghaos = gzhService.getAll(
						WxGongzhonghao.class, "gzhHash", user.getGzhHash());
				if (wxGongzhonghaos.size() > 0) {
					String appid = wxGongzhonghaos.get(0).getAppid();
					String appsecret = wxGongzhonghaos.get(0).getAppsecret();
					String accessToken = HttpUtils.getToken(appid, appsecret);
					if (accessToken != null) {
						String getQrCodeUrl = PropertiesUtil.getProperty(ParamString.QRCODE_URL);	
						getQrCodeUrl = getQrCodeUrl.replace("{1}", accessToken);	// 生成获取二维码的链接地址
						String sendData = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "
								+ scene + "}}}";// 生成二维码的post_data
						String qrResult = HttpClientUtil.sendPostRequest(
								getQrCodeUrl, sendData, false);
						JSONObject jo = JSONObject.fromObject(qrResult);
						if (jo.containsKey("ticket")) {
							String ticket = jo.getString("ticket");
							active.setSceneId(scene);
							active.setTicket(ticket);				
							activeService.save(active);
							log.info(active.toString());

							setMap(true, "success", null);
						} else {
							setMap(false, "获取二维码ticket失败，请稍后重试或联系管理员！", null);
						}
					} else {
						setMap(false, "获取access_token失败，请稍后重试或联系管理员！", null);
					}
				}
				setMap(true, "添加成功",null);
			} catch (Exception e) {
				System.out.println(e);
				setMap(false, "添加失败", null);
			}

			break;

		}
		case "activeUpdata": {
			// 更新积分活动
			String actName=active.getActName();
			int  actCredit=active.getActCredit();
			String actDesc=active.getActDesc();
			int actType=active.getActType();
			int  actType2=active.getActType2();
			String startOn=active.getStartOn();
			String endOn=active.getEndOn();
			String actImage=active.getActImage();
			String gzhHash=active.getGzhHash();
			String createdBy=active.getCreatedBy();
			String createdOn=active.getCreatedOn();
			String actContent=active.getActContent();
			try {
				WxMemberCreditActiveF act = this.activeService.get(
						WxMemberCreditActiveF.class, "memberCreditGuid",
						active.getMemberCreditGuid());
				active=act;
				active.setModifyBy(user.getUserName());
				active.setModifyOn(DateUtils.currentDatetime());
				active.setCreatedBy(createdBy);
				active.setCreatedOn(createdOn);
				active.setStatus(0);
				active.setSource(0);
				active.setDelFlag(0);
				active.setDealerCode("DIST");
				active.setActContent(actContent);
				active.setActName(actName);
				active.setActDesc(actDesc);
				active.setActCredit(actCredit);
				active.setActType(actType);
				active.setActType2(actType2);
				active.setActImage(actImage);
				active.setGzhHash(gzhHash);
				active.setStartOn(startOn);
				active.setEndOn(endOn);
				activeService.update(active);
				log.info(active.toString());
				setMap(true, "更新成功", null);
			} catch (Exception e) {
				System.out.println(e);
				setMap(false, "更新失败", null);
			}

			break;
		}
		case "getData": {
			// 获取积分活动
			try {
				WxMemberCreditActiveF act = this.activeService.get(
						WxMemberCreditActiveF.class, "memberCreditGuid",
						active.getMemberCreditGuid());
				JSONObject obj = new JSONObject();
				obj.put("memberCreditGuid", act.getMemberCreditGuid());
				obj.put("actName", act.getActName());
				obj.put("actCredit", act.getActCredit());
				obj.put("actDesc", act.getActDesc());
				obj.put("actType", act.getActType());
				obj.put("actType2", act.getActType2());
				obj.put("startOn", act.getStartOn());
				obj.put("endOn", act.getEndOn());
				obj.put("actImage", act.getActImage());
				obj.put("status", act.getStatus());
				obj.put("createdBy", act.getCreatedBy());
				obj.put("createdOn", act.getCreatedOn());
				obj.put("actContent", act.getActContent());
				setMap(true, "获取数据成功", obj);
			} catch (Exception e) {
				setMap(false, "获取数据失败", null);
			}
			break;
		}
		}
		return CreditActiveAction.SUCCESS;

	}

	@Override
	public WxMemberCreditActiveF getModel() {
		return active;
	}

	@Override
	public void prepare() throws Exception {
		if (null == active) {
			active = new WxMemberCreditActiveF();
		}

	}

}
