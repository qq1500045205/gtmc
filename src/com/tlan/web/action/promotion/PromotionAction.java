package com.tlan.web.action.promotion;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.promotion.WxModuleContentPromotion;
import com.tlan.common.service.IBaseService;

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
public class PromotionAction extends BaseAction {

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentPromotion> promotionService;

	private String gzhHash;
	private String promotionGuid;

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
		List<WxModuleContentPromotion> promotions = promotionService.getAll(
				WxModuleContentPromotion.class, "gzhHash", gzhHash);
		setMap(true, "获取数据成功", JSONArray.fromObject(promotions));
		return SUCCESS;
	}

	/**
	 * 根据guid获取活动
	 * 
	 * @Title: getPromotionByGuid
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月26日 下午9:14:55
	 */
	public String getOne() {
		try {
			WxModuleContentPromotion promotion = promotionService.get(
					WxModuleContentPromotion.class, "promotionGuid",
					promotionGuid);
			if (null != promotion) {
				setMap(true, "获取成功", JSONObject.fromObject(promotion));
			} else {
				setMap(false, "获取失败", null);
			}

		} catch (Exception e) {
			setMap(false, "获取失败", null);
		}
		return this.SUCCESS;
	}

	public String getGzhHash() {
		return gzhHash;
	}

	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}

	public String getPromotionGuid() {
		return promotionGuid;
	}

	public void setPromotionGuid(String promotionGuid) {
		this.promotionGuid = promotionGuid;
	}

}
