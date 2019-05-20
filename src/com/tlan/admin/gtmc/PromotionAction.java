package com.tlan.admin.gtmc;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.promotion.WxModuleContentPromotion;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

/**
 * 活动促销
 * 
 * @PackageName:com.tlan.admin.gtmc
 * @ClassName:CarPromotionAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月26日 下午9:16:22
 * 
 */
public class PromotionAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentPromotion> {

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentPromotion> promotionService;

	private WxModuleContentPromotion promotion;
	private LoginBean loginBean = obtainLoginBean();

	private String[] items;

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
	public String getByGuid() {
		try {
			List<WxModuleContentPromotion> promotions = promotionService
					.getAll(WxModuleContentPromotion.class, "promotionGuid",
							promotion.getPromotionGuid());
			if (promotions.size() > 0) {
				setMap(true, "获取成功", promotions.get(0));
			} else {
				setMap(false, "获取失败", null);
			}

		} catch (Exception e) {
			setMap(false, "获取失败", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 添加促销
	 * 
	 * @Title: addCarPromotion
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月26日 下午9:16:08
	 */
	public String add() {
		try {
			promotion.setGzhHash(loginBean.getGzhHash());
			promotion.setStatus(0); // 默认未发布
			promotion.setDelFlag(0); // 默认正常
			promotion.setGzhHash(loginBean.getGzhHash());
			promotion.setCreatedOn(DateUtils.currentDatetime());
			promotion.setCreatedBy(loginBean.getLoginName());
			promotion.setModifyOn(DateUtils.currentDatetime());
			promotion.setModifyBy(loginBean.getLoginName());

			promotionService.saveOrUpdate(promotion);

			setMap(true, "添加成功", null);
		} catch (Exception e) {
			setMap(false, "添加失败", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 删除促销
	 * 
	 * @Title: delete
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月26日 下午9:18:37
	 */
	public String delete() {
		if (null != items) {
			for (int i = 0; i < items.length; i++) {
				promotionService.update(WxModuleContentPromotion.class,
						new String[] { "delFlag" }, new Object[] { 1 },
						"promotionGuid = '" + items[i] + "'");
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 编辑
	 * 
	 * @Title: edit
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年2月26日 下午9:18:45
	 */
	public String edit() {
		try {
			promotion.setPromotionGuid(DataUtils.getUUID());
			promotion.setCreatedOn(DateUtils.currentDatetime());
			promotion.setCreatedBy(loginBean.getLoginName());
			promotion.setModifyOn(DateUtils.currentDatetime());
			promotion.setModifyBy(loginBean.getLoginName());

			promotionService.update(promotion);

			setMap(true, "编辑成功", null);
		} catch (Exception e) {
			setMap(false, "编辑失败", null);
		}
		return this.SUCCESS;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	@Override
	public WxModuleContentPromotion getModel() {
		// TODO Auto-generated method stub
		return promotion;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (promotion == null) {
			promotion = new WxModuleContentPromotion();
		}
	}

}
