package com.tlan.web.action.plugins;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.plugins.WeixinOrderhistoryViews;
import com.tlan.common.service.IBaseService;

/**
 * 维修履历
 * 
 * @PackageName:com.tlan.web
 * @ClassName:RepairRecordAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年3月10日 上午11:39:03
 * 
 */
public class RepairRecordAction extends BaseAction {

	private static final long serialVersionUID = -8372934289693600141L;
	private static final Log log = LogFactory.getLog(RepairRecordAction.class);

	@Resource(name = "baseService")
	private IBaseService<WeixinOrderhistoryViews> orderhistoryService;

	/**
	 * 根据车牌号或vin获取维修单主体
	 * 
	 * @Title: obtainRecord
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月10日 上午11:40:55
	 */
	public String obtainRecord() {
		List<WeixinOrderhistoryViews> list;
		try {
			DetachedCriteria dc = DetachedCriteria
					.forClass(WeixinOrderhistoryViews.class);
			dc.add(Restrictions.or(Restrictions.eq("vinno", vinno),
					Restrictions.eq("registerno", carNumber)));
			dc.addOrder(Order.desc("salesdate"));
			list = orderhistoryService.findByCriteria(dc);

			setMap(true, "获取数据成功", list);
		} catch (Exception e) {
			log.error("获取数据异常：" + e.getMessage());
			setMap(false, "获取数据失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 车牌号
	 */
	private String carNumber;
	/**
	 * vin码
	 */
	private String vinno;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getVinno() {
		return vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

}
