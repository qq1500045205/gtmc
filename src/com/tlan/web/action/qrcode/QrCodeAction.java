package com.tlan.web.action.qrcode;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.admin.api.WeixinAction;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.active.WxModuleContentSign;
import com.tlan.common.model.promotion.WxModuleContentPromotion;
import com.tlan.common.model.qrcode.WxQrCode;
import com.tlan.common.model.questionnaire.WxModuleContentQuestion;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.http.HttpUtils;

/**
 * 促销信息
 * 
 * @PackageName:com.tlan.admin.gtmc
 * @ClassName:CarPromotionAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月26日 下午9:16:22
 * 
 */
@Component
@Scope(value="prototype")
public class QrCodeAction extends BaseAction {

	@Resource(name = "baseService")
	private IBaseService<WxQrCode> qrcodeService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;

	private static Logger log = Logger.getLogger(QrCodeAction.class);
	
	private LoginBean loginBean = obtainLoginBean();
	private String sourceFrom;
	
	private String items;

	/**
	 * 获取所有促销信息
	 * 
	 * @Title: list
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月27日 上午11:04:55
	 */
	public String list() {
		List<WxQrCode> qrCodes = qrcodeService.findPage(WxQrCode.class,
				getStart(), getLimit(), new String[] { "gzhHash", "deleteFlag" },
				new Object[] { loginBean.getGzhHash(), 0 });
		setMap(null, qrCodes, qrcodeService.getCount(WxQrCode.class,
				new String[] { "gzhHash", "deleteFlag" },
				new Object[] { loginBean.getGzhHash(), 0 }, new String[] { "=", "=" }));
		return SUCCESS;

	}

	public String addQrCode() {
		WxQrCode qrCode = new WxQrCode();
		List<WxQrCode> qrCodes = qrcodeService.getAll(WxQrCode.class,
				new String[] { "gzhHash", "sourceFrom", "deleteFlag" }, new Object[] {
						loginBean.getGzhHash(), sourceFrom, 0 });
		if (qrCodes.size() == 0) {
			qrCode.setGzhHash(loginBean.getGzhHash());
			qrCode.setSourceFrom(sourceFrom);
			qrCode.setDeleteFlag(0);
			qrcodeService.save(qrCode);
			log.info("scene_id="+qrCode.getId());
			List<WxGongzhonghao> wxGongzhonghaos = gzhService.getAll(
					WxGongzhonghao.class, "gzhHash", loginBean.getGzhHash());
			if (wxGongzhonghaos.size() > 0) {
				String appid = wxGongzhonghaos.get(0).getAppid();
				String appsecret = wxGongzhonghaos.get(0).getAppsecret();
				String accessToken = HttpUtils.getToken(appid, appsecret);
				if (accessToken != null) {
					String getQrCodeUrl = PropertiesUtil.getProperty(ParamString.QRCODE_URL);	
					getQrCodeUrl = getQrCodeUrl.replace("{1}", accessToken);	// 生成获取二维码的链接地址
					String sendData = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "
							+ qrCode.getId() + "}}}";// 生成二维码的post_data
					String qrResult = HttpClientUtil.sendPostRequest(
							getQrCodeUrl, sendData, false);
					JSONObject jo = JSONObject.fromObject(qrResult);
					if (jo.containsKey("ticket")) {
						String ticket = jo.getString("ticket");
						qrCode.setTicket(ticket);
						qrcodeService.update(qrCode);
						
						setMap(true, "success", null);
					} else {
						setMap(false, "获取二维码ticket失败，请稍后重试或联系管理员！", null);
					}
				} else {
					setMap(false, "获取access_token失败，请稍后重试或联系管理员！", null);
				}
			} else {
				setMap(false, "登录过期，请重新登录", null);
			}
		} else {
			setMap(false, "不允许重复添加二维码的应用场景", null);
		}

		return this.SUCCESS;
	}
	
	// 真删除一条记录
		public String deleteQrCode() {
			if (null != getItems()) {
				System.out.println("items=" + getItems());
				String items[] = getItems().split(",");
				String guids = "";
				for (int i = 0; i < items.length; i++) {
					System.out.println(items[i]);
					// 通过关键字，删除一条车型
					guids += "'" + items[i].trim() + "',";
				}
				guids = guids.substring(0, guids.length() - 1);
				List<WxQrCode> qlist = qrcodeService.getAll(
						WxQrCode.class,
						new String[] { "id" }, new Object[] { guids },
						new String[] { "in" });
				for (WxQrCode q : qlist) {
					q.setDeleteFlag(1);
					qrcodeService.update(q);
				}

				setMap(true, "删除成功", null);
			} else {
				setMap(false, "删除失败（没有数据）", null);
			}
			return this.SUCCESS;
		}

	public String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
