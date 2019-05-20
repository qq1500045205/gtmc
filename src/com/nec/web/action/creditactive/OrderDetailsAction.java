package com.nec.web.action.creditactive;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.creditactive.WxProductOrderF;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;

/**
 * 商品订单管理
 * 
 * @author luo
 * 
 */
public class OrderDetailsAction extends BaseAction implements Preparable,
		ModelDriven<WxProductOrderF> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(OrderDetailsAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxProductOrderF> orderService;

	private WxProductOrderF orderactive;


	/**
	 * 更新数据操作
	 * 
	 * @return
	 */
	public String orderDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		switch (getType()) {
		case "orderUpdate": {
			// 更新订单状态
			String product_order_guid = request.getParameter("productOrderGuid");
			String userGuid =  request.getParameter("userGuid");
			log.info("product_order_guid:"+product_order_guid);
			log.info("modify_by:"+userGuid);
			log.info("modify_on"+DateUtils.currentDatetime());
			try {
				this.orderService.update(
						WxProductOrderF.class,
						new String[] { "order_status_guid", "modify_by",
								"modify_on" },
						new Object[] { "3",userGuid,
								DateUtils.currentDatetime() },
						"product_order_guid ='" + product_order_guid + "'");
				System.out.println("更新成功！");
				setMap(true, "更新成功", null);
			} catch (Exception e) {
				log.info(e.getMessage());
				setMap(false, "更新失败", null);
			}

			break;
		}
		}
		return OrderDetailsAction.SUCCESS;

	}

	@Override
	public WxProductOrderF getModel() {
		return orderactive;
	}

	@Override
	public void prepare() throws Exception {
		if (null == orderactive) {
			orderactive = new WxProductOrderF();
		}

	}

}
