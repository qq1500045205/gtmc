package com.nec.web.action.creditactive;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.creditactive.WxOrderManageView;
import com.tlan.common.service.IBaseService;

/**
 * 商品订单管理
 * 
 * @author luo
 * 
 */
public class ProductOrderManageAction extends BaseAction implements Preparable,
		ModelDriven<WxOrderManageView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(ProductOrderManageAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxOrderManageView> activeService;

	private WxOrderManageView active;

	private boolean orderFlag = true;

	/**
	 * 执行数据操作
	 * 
	 * @return
	 */
	public String orderManage() {
		switch (getType()) {
		case "getList": {
			// 获取订单列表
			ArrayList<String[]> aList = null;
			try {
				aList = preSelect(orderFlag);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String[] condions = aList.get(0);
			String[] values = aList.get(1);
			String[] ch = aList.get(2);
			List<WxOrderManageView> orderList = this.activeService.getAll(
					WxOrderManageView.class, condions, values, ch);
			log.info(active.toString());

			setMap(null, JSONArray.fromObject(orderList), orderList.size());
			break;
		}
		case "getData": {
			// 获取积分活动
			try {
				WxOrderManageView act = this.activeService.get(
						WxOrderManageView.class, "productOrderGuid",
						active.getProductOrderGuid());
				JSONObject obj = new JSONObject();
				obj.put("productLogo", act.getProductLogo());
				obj.put("productCredit", act.getProductCredit());
				obj.put("orderStatusName", act.getOrderStatusName());
				obj.put("productOrderDate", act.getProductOrderDate());
				obj.put("productOrderNum", act.getProductOrderNum());
				obj.put("productCount", act.getProductCount());
				obj.put("userLocationName", act.getUserLocationName());
				obj.put("userLocationProvice", act.getUserLocationProvice());
				obj.put("userLocationCity", act.getUserLocationCity());
				obj.put("userLocationDistrict", act.getUserLocationDistrict());
				obj.put("userLocationStreet", act.getUserLocationStreet());
				obj.put("userLocationPhone", act.getUserLocationPhone());
				obj.put("productOrderGuid", act.getProductOrderGuid());
				obj.put("orderStatusGuid", act.getOrderStatusGuid());
				obj.put("userGuid", act.getUserGuid());
				setMap(true, "获取数据成功", obj);
			} catch (Exception e) {
				setMap(false, "获取数据失败", null);
			}
			break;
		}
		}
		return ProductOrderManageAction.SUCCESS;

	}

	private ArrayList<String[]> preSelect(boolean flag)
			throws UnsupportedEncodingException {
		ArrayList<String[]> result = new ArrayList<String[]>();

		String openId = active.getOpenId();
		String gzhHash = active.getGzhHash();
		String orderStatusGuid = null;
		System.out.println(orderStatusGuid);
		String[] condions = new String[3];
		String[] values = new String[3];
		String[] ch = new String[3];
		if (openId.equals("")) {
			condions[0] = "1";
			values[0] = "1";
			ch[0] = "=";
		} else {
			condions[0] = "openId";
			values[0] = openId;
			ch[0] = "=";
		}
		if (gzhHash.equals("")) {
			condions[1] = "1";
			values[1] = "1";
			ch[1] = "=";
		} else {
			condions[1] = "gzhHash";
			values[1] = gzhHash;
			ch[1] = "=";
		}
		if (flag) {
			orderStatusGuid = "1,2";
			condions[2] = "orderStatusGuid";
			values[2] = orderStatusGuid;
			ch[2] = "in";
		} else {
			orderStatusGuid = "3";
			condions[2] = "orderStatusGuid";
			values[2] = orderStatusGuid;
			ch[2] = "=";
		}
		result.add(condions);
		result.add(values);
		result.add(ch);
		return result;
	}

	public boolean getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(boolean orderFlag) {
		this.orderFlag = orderFlag;
	}

	@Override
	public WxOrderManageView getModel() {
		return active;
	}

	@Override
	public void prepare() throws Exception {
		if (null == active) {
			active = new WxOrderManageView();
		}
	}

}
