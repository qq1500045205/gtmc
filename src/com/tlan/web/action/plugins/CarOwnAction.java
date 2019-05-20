package com.tlan.web.action.plugins;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.plugins.WeixinCustomervhcViews;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.JsonUtils;

/**
 * 从G-Cloud验证是否为车主
 * 
 * @PackageName:com.tlan.web
 * @ClassName:CarOwnAction
 * @Description:
 * @author limengjietlan.com.cn (改成自己的）
 * @date 2014年3月1日 下午8:55:10
 * 
 */
public class CarOwnAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(CarOwnAction.class);

	@Resource(name = "baseService")
	private IBaseService<WeixinCustomervhcViews> customervhcService;
	/**
	 * 多个手机号码
	 */
	private String userTels;
	/**
	 * 手机号码
	 */
	private String userTel;
	/**
	 * 车牌号
	 */
	private String carNumber;

	/**
	 * 
	 * @Title: getCarinfolist
	 * @Description:
	 * @param userTel
	 *            /carNumber
	 * @return String
	 * @throws
	 * @Date 2014年3月2日 下午1:19:49
	 */

	public String getCarinfolist() {
		List<WeixinCustomervhcViews> retlist = null;
		List<WeixinCustomervhcViews> listbytel;
		List<WeixinCustomervhcViews> listbycar;
		List<WeixinCustomervhcViews> listbytelandcar;
		boolean status = true;
		int msgtype = 0; // 0:NO;1:OK;2:Tel_And_Car;3:Tel_No_Car;4:No_Tel_And_Car;5:No_Tel_No_Car;
		String msg = "";

		try {
			if (DataUtils.isNotNullOrEmpty(userTel)) {

				/** 应用DetachedCriteria获取listbytel**START **/
				userTel = userTel.trim();
				DetachedCriteria dc = DetachedCriteria
						.forClass(WeixinCustomervhcViews.class);
				dc.add(Restrictions.or(
						/*
						 * Restrictions.like("buyertel1".trim(),userTel.toString(
						 * ),MatchMode.ANYWHERE),
						 * Restrictions.like("buyertel2".trim(),
						 * userTel.toString(),MatchMode.ANYWHERE),
						 */
						Restrictions.like("buyermobil1".trim(),
								userTel.toString(), MatchMode.ANYWHERE),
						Restrictions.like("buyermobil2".trim(),
								userTel.toString(), MatchMode.ANYWHERE),
						/*
						 * Restrictions.like("nomineetel1".trim(),
						 * userTel.toString(),MatchMode.ANYWHERE),
						 * Restrictions.like("nomineetel2".trim(),
						 * userTel.toString(),MatchMode.ANYWHERE),
						 */
						Restrictions.like("nomineemobil1".trim(),
								userTel.toString(), MatchMode.ANYWHERE),
						Restrictions.like("nomineemobil2".trim(),
								userTel.toString(), MatchMode.ANYWHERE),
						/*
						 * Restrictions.like("srvcustomtel1".trim(),
						 * userTel.toString(),MatchMode.ANYWHERE),
						 * Restrictions.like("srvcustomtel2".trim(),
						 * userTel.toString(),MatchMode.ANYWHERE),
						 */
						Restrictions.like("srvcustommobil1".trim(),
								userTel.toString(), MatchMode.ANYWHERE),
						Restrictions.like("srvcustommobil2".trim(),
								userTel.toString(), MatchMode.ANYWHERE)));

				listbytel = customervhcService.findByCriteria(dc);
				/** 应用DetachedCriteria获取listbytel**END **/

				/*
				 * 1. car_number==null 此时只验证user_tel。 1)
				 * Tact:若能获取到相应车辆信息，则返回车辆信息、状态OK 2) Tact: 若不能获取相应车辆信息，则返回
				 * null、状态NO
				 */
				if (DataUtils.isNullOrEmpty(carNumber)) {

					retlist = listbytel;
					if (retlist != null && retlist.size() > 0)// 1.1
					{
						msgtype = 1;
					} else // 1.2
					{
						msgtype = 0;
					}

				} else {

					/*
					 * 2. car_number!=null 此时需同过user_tel及car_number 分别验证车辆信息。 1)
					 * Tact: 若通过user_tel能获取到相应车辆信息，其中能匹配car_number。则返回车辆信息、状态OK。
					 * 2) Tact: 若通过user_tel能获取到相应车辆信息，但不能匹配car_number。
					 * 此时再通过car_number获取车型信息。 2.1)
					 * 若能通过car_number获取车型信息。返回通过user_tel获取的车辆信息、状态Tel_And_Car。
					 * 2.2)若不能通过car_number获取车型信息。返回
					 * 通过user_tel获取的车辆信息、状态Tel_No_Car。 3) Tact:
					 * 若不能通过user_tel获取到相信车辆信息。此时通过car_number获取车型信息。 3.1)
					 * 若能通过car_number获取车型信息
					 * 。返回通过car_number获取的车型信息、状态No_Tel_And_Car。
					 * 3.2)若不能通过car_number获取车型信息。返回 null，状态No_Tel_No_Car。
					 */
					if (carNumber.length() >= 3) {
						carNumber = carNumber.substring(0, 3) + "-"
								+ carNumber.substring(3, carNumber.length());
					}

					listbycar = customervhcService.getAll(
							WeixinCustomervhcViews.class, "registerno",
							carNumber);
					listbytelandcar = customervhcService.getAll(
							WeixinCustomervhcViews.class, new String[] {
									"buyermobil1", "registerno" },
							new String[] { userTel, carNumber });

					int ifmapcarNum = 0;// 判断是否匹配车牌号;0:否;1:是;

					if (listbytel != null && listbytel.size() > 0)// 2=>2)
					{
						for (int i = 0; i < listbytel.size(); i++) {
							WeixinCustomervhcViews customervhcinfo = listbytel
									.get(i);
							if (customervhcinfo.getRegisterno() == carNumber) {
								ifmapcarNum++;
							}
						}
						if (ifmapcarNum == 0) {
							if (listbycar != null && listbycar.size() > 0) // 2=>2)=>2.1)
							{
								retlist = listbytel;
								msgtype = 2;

							} else// 2=>2)=>2.2)
							{
								retlist = listbytel;
								msgtype = 3;
							}

						} else// 2=>1)
						{
							retlist = listbytel;
							msgtype = 1;
							/*
							 * if (listbytelandcar != null &&
							 * listbytelandcar.size() > 0)// 2=>1) { retlist =
							 * listbytelandcar; msgtype = 1; } else { retlist =
							 * listbytelandcar; msgtype = 0; }
							 */
						}
					} else// 2=>3)
					{
						if (listbycar != null && listbycar.size() > 0) // 2=>3)=>3.1)
						{
							retlist = listbycar;
							msgtype = 4;

						} else// 2=>2)=>2.2)
						{

							retlist = listbycar;
							msgtype = 5;
						}

					}

				}

			}
			if (msgtype == 0) {
				msg = "NO";
			}
			if (msgtype == 1) {
				msg = "OK";
			}
			if (msgtype == 2) {
				msg = "Tel_And_Car";
			}
			if (msgtype == 3) {
				msg = "Tel_No_Car";
			}
			if (msgtype == 4) {
				msg = "No_Tel_And_Car";
			}
			if (msgtype == 5) {
				msg = "No_Tel_No_Car";
			}

			if (retlist != null && retlist.size() > 0) {
				status = true;
				setMap(status, msg, JsonUtils.getJsonString4JSONArray(retlist,
						"yyyy-MM-dd HH:mm:ss"));
			} else {
				setMap(false, msg, null);
			}

			// setMap(status, msg,
			// JsonUtils.getJsonString4JSONArray(retlist,"yyyy-MM-dd HH:mm:ss"));

		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
			setMap(false, "failure", null);
		}

		return SUCCESS;

	}

	/***
	 * 
	 * @Title: getCarlist
	 * @Description:
	 * @param @return
	 * @return JSONArray
	 * @throws
	 * @Date 2014年3月3日 下午2:56:47
	 */

	public String getcarlistbyusertels() {
		// 0.0 获取方法执行起始时间。
		String st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
				.format(new Date()); // 获取开始时间
		String msg = "";

		JSONArray retarraylist = new JSONArray();
		List<WeixinCustomervhcViews> retlist = null;
		// String[] usertels={};
		// String msg;

		if (DataUtils.isNotNullOrEmpty(userTels)) {

			DetachedCriteria dc = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			String sqlwhere = userTels.replace(",", "','");
			dc.add(Restrictions.or(
					Restrictions
							.sqlRestriction("(trim({alias}.BUYERMOBIL1) in ('"
									+ sqlwhere + "'))"),// 购车人手机
					Restrictions
							.sqlRestriction("(trim({alias}.BUYERMOBIL2) in ('"
									+ sqlwhere + "'))"),
					Restrictions
							.sqlRestriction("(trim({alias}.NOMINEEMOBIL1) in ('"
									+ sqlwhere + "'))"),// 名义人手机
					Restrictions
							.sqlRestriction("(trim({alias}.NOMINEEMOBIL2) in ('"
									+ sqlwhere + "'))"),
					Restrictions
							.sqlRestriction("(trim({alias}.SRVCUSTOMMOBIL1) in ('"
									+ sqlwhere + "'))"),// 送修人手机
					Restrictions
							.sqlRestriction("(trim({alias}.SRVCUSTOMMOBIL2) in ('"
									+ sqlwhere + "'))")));

			retlist = customervhcService.findByCriteria(dc);
			// msg="qbs查询结果:"+listbyqbs.size()+";for循环结果:"+i+"传入数组参数个数:"+usertels.length+";单个查询结果:"+listbyone.size();
			retarraylist = JsonUtils.getJsonString4JSONArray(retlist,
					"yyyy-MM-dd HH:mm:ss");

		}

		// 0.1 获取方法执行起始时间。
		String se = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
				.format(new Date()); // 获取结束时间

		// msg="qbs查询结果:"+listbyqbs.size()+";for循环结果:"+i+"传入数组参数个数:"+usertels.length+";单个查询结果:"+listbyone.size();
		msg = "方法起始时间:" + st + "方法截止时间:" + se;
		if (retlist != null && retlist.size() > 0) {
			setMap(true, msg, retarraylist);
		} else {
			setMap(false, msg, retarraylist);
		}
		return SUCCESS;

	}

	// 针对传入的号码组，各个字段分别in获取，之后统一组装返回。
	public String getcarlistbyusertels2() {
		// 0.0 获取方法执行起始时间。
		String st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
				.format(new Date()); // 获取开始时间
		String msg = "";

		// 1.定义所需变量。
		JSONArray retarraylist = new JSONArray();
		List<WeixinCustomervhcViews> retlist = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistBUYERMOBIL1 = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistBUYERMOBIL2 = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistNOMINEEMOBIL1 = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistNOMINEEMOBIL2 = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistSRVCUSTOMMOBIL1 = new ArrayList<WeixinCustomervhcViews>();
		List<WeixinCustomervhcViews> retlistSRVCUSTOMMOBIL2 = new ArrayList<WeixinCustomervhcViews>();

		if (DataUtils.isNotNullOrEmpty(userTels)) {
			// 2。1当传入号码组部不为空时，将之转换为sql脚本。
			String sqlwhere = userTels.replace(",", "','");
			sqlwhere = "'" + sqlwhere + "'";
			// 2.2各个字段分别in获取。
			DetachedCriteria dcBUYERMOBIL1 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			DetachedCriteria dcBUYERMOBIL2 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			DetachedCriteria dcNOMINEEMOBIL1 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			DetachedCriteria dcNOMINEEMOBIL2 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			DetachedCriteria dcSRVCUSTOMMOBIL1 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			DetachedCriteria dcSRVCUSTOMMOBIL2 = DetachedCriteria
					.forClass(WeixinCustomervhcViews.class);
			dcBUYERMOBIL1.add(Restrictions
					.sqlRestriction("(trim({alias}.BUYERMOBIL1) in ("
							+ sqlwhere + "))"));// 购车人手机
			dcBUYERMOBIL2.add(Restrictions
					.sqlRestriction("(trim({alias}.BUYERMOBIL2) in ("
							+ sqlwhere + "))"));
			dcNOMINEEMOBIL1.add(Restrictions
					.sqlRestriction("(trim({alias}.NOMINEEMOBIL1) in ("
							+ sqlwhere + "))"));// 名义人手机
			dcNOMINEEMOBIL2.add(Restrictions
					.sqlRestriction("(trim({alias}.NOMINEEMOBIL2) in ("
							+ sqlwhere + "))"));
			dcSRVCUSTOMMOBIL1.add(Restrictions
					.sqlRestriction("(trim({alias}.SRVCUSTOMMOBIL1) in ("
							+ sqlwhere + "))"));// 送修人手机
			dcSRVCUSTOMMOBIL2.add(Restrictions
					.sqlRestriction("(trim({alias}.SRVCUSTOMMOBIL2) in ("
							+ sqlwhere + "))"));
			retlistBUYERMOBIL1 = customervhcService
					.findByCriteria(dcBUYERMOBIL1);
			retlistBUYERMOBIL2 = customervhcService
					.findByCriteria(dcBUYERMOBIL2);
			retlistNOMINEEMOBIL1 = customervhcService
					.findByCriteria(dcNOMINEEMOBIL1);
			retlistNOMINEEMOBIL2 = customervhcService
					.findByCriteria(dcNOMINEEMOBIL2);
			retlistSRVCUSTOMMOBIL1 = customervhcService
					.findByCriteria(dcSRVCUSTOMMOBIL1);
			retlistSRVCUSTOMMOBIL2 = customervhcService
					.findByCriteria(dcSRVCUSTOMMOBIL2);
			// 2.3针对各个字段查询结果集进行组装
			if (retlistBUYERMOBIL1 != null && retlistBUYERMOBIL1.size() > 0) {
				retlist = retlistBUYERMOBIL1;
			}

			if (retlistBUYERMOBIL2 != null && retlistBUYERMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistBUYERMOBIL2.removeAll(retlist);
					retlist.addAll(retlistBUYERMOBIL2);
				} else {
					retlist = retlistBUYERMOBIL2;
				}
			}

			if (retlistNOMINEEMOBIL1 != null && retlistNOMINEEMOBIL1.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistNOMINEEMOBIL1.removeAll(retlist);
					retlist.addAll(retlistNOMINEEMOBIL1);
				} else {
					retlist = retlistNOMINEEMOBIL1;
				}
			}

			if (retlistNOMINEEMOBIL2 != null && retlistNOMINEEMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistNOMINEEMOBIL2.removeAll(retlist);
					retlist.addAll(retlistNOMINEEMOBIL2);
				} else {
					retlist = retlistNOMINEEMOBIL2;
				}
			}

			if (retlistSRVCUSTOMMOBIL1 != null
					&& retlistSRVCUSTOMMOBIL1.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistSRVCUSTOMMOBIL1.removeAll(retlist);
					retlist.addAll(retlistSRVCUSTOMMOBIL1);
				} else {
					retlist = retlistSRVCUSTOMMOBIL1;
				}
			}

			if (retlistSRVCUSTOMMOBIL2 != null
					&& retlistSRVCUSTOMMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistSRVCUSTOMMOBIL2.removeAll(retlist);
					retlist.addAll(retlistSRVCUSTOMMOBIL2);
				} else {
					retlist = retlistSRVCUSTOMMOBIL2;
				}
			}

			List<WeixinCustomervhcViews> ret = new ArrayList<WeixinCustomervhcViews>();

			for (WeixinCustomervhcViews i : retlist) {
				WeixinCustomervhcViews j = i;
				if (!ret.contains(j)) {
					ret.add(i);
				}

			}

			retlist = ret;

		}

		retarraylist = JsonUtils.getJsonString4JSONArray(retlist,
				"yyyy-MM-dd HH:mm:ss");

		// 0.1 获取方法执行起始时间。
		String se = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
				.format(new Date()); // 获取结束时间
		// msg="qbs查询结果:"+listbyqbs.size()+";for循环结果:"+i+"传入数组参数个数:"+usertels.length+";单个查询结果:"+listbyone.size();
		msg = "方法起始时间:" + st + "方法截止时间:" + se;
		if (retlist != null && retlist.size() > 0) {
			setMap(true, msg, retarraylist);
		} else {
			setMap(false, msg, retarraylist);
		}
		return SUCCESS;

	}

	public String getcarlistbyusertels3() {

		JSONArray retarraylist = new JSONArray();
		String[] usertels = {};
		String msg = "";

		/** 单条情况 **/
		List<WeixinCustomervhcViews> listbyone = customervhcService.getAll(
				WeixinCustomervhcViews.class, "buyermobil1", "13887682222",
				"like%%");
		/** for循环情况 **/
		int i = 0;
		if (DataUtils.isNotNullOrEmpty(userTels)) {
			// usertels = userTels.split(","); // {"13887682222","13235153069"};
			for (int j = 0; j < usertels.length; j++) {
				List<WeixinCustomervhcViews> listtest2 = customervhcService
						.getAll(WeixinCustomervhcViews.class, "buyermobil1",
								usertels[j], "like%%");

				if (listtest2 != null && listtest2.size() > 0) {
					i++;
				}

			}
		}

		// msg="qbs查询结果:"+listbyqbs.size()+";for循环结果:"+i+"传入数组参数个数:"+usertels.length+";单个查询结果:"+listbyone.size();

		retarraylist = JsonUtils.getJsonString4JSONArray(listbyone,
				"yyyy-MM-dd HH:mm:ss");

		setMap(false, msg, retarraylist);
		return SUCCESS;
	}

	public String getcarlistbygetall() {
		// 0.0 获取方法执行起始时间。

		long startTime = System.currentTimeMillis(); // 获取开始时间
		log.info("startTime:" + DateUtils.currentDatetime());
		// 1.定义所需变量。
		JSONArray retarraylist = new JSONArray();
		List<WeixinCustomervhcViews> retlist = null;
		List<WeixinCustomervhcViews> retlistBUYERMOBIL1 = null;
		List<WeixinCustomervhcViews> retlistBUYERMOBIL2 = null;
		List<WeixinCustomervhcViews> retlistNOMINEEMOBIL1 = null;
		List<WeixinCustomervhcViews> retlistNOMINEEMOBIL2 = null;
		List<WeixinCustomervhcViews> retlistSRVCUSTOMMOBIL1 = null;
		List<WeixinCustomervhcViews> retlistSRVCUSTOMMOBIL2 = null;
		String msg = "";

		if (DataUtils.isNotNullOrEmpty(userTels)) {
			// 2。1当传入号码组部不为空时，将之转换为sql脚本。
			String sqlwhere = userTels.replace(",", "','");
			sqlwhere = "'" + sqlwhere + "'";
			// 2.2各个字段分别in获取。
			retlistBUYERMOBIL1 = customervhcService
					.getAll(WeixinCustomervhcViews.class, "buyermobil1",
							sqlwhere, "in");// 购车人手机
			retlistBUYERMOBIL2 = customervhcService
					.getAll(WeixinCustomervhcViews.class, "buyermobil2",
							sqlwhere, "in");
			retlistNOMINEEMOBIL1 = customervhcService.getAll(
					WeixinCustomervhcViews.class, "nomineemobil1", sqlwhere,
					"in");// 名义人手机
			retlistNOMINEEMOBIL2 = customervhcService.getAll(
					WeixinCustomervhcViews.class, "nomineemobil2", sqlwhere,
					"in");
			retlistSRVCUSTOMMOBIL1 = customervhcService.getAll(
					WeixinCustomervhcViews.class, "srvcustommobil1", sqlwhere,
					"in");// 送修人手机
			retlistSRVCUSTOMMOBIL2 = customervhcService.getAll(
					WeixinCustomervhcViews.class, "srvcustommobil2", sqlwhere,
					"in");
			// 2.3针对各个字段查询结果集进行组装
			if (retlistBUYERMOBIL1 != null && retlistBUYERMOBIL1.size() > 0) {
				retlist.addAll(retlistBUYERMOBIL1);
			}

			if (retlistBUYERMOBIL2 != null && retlistBUYERMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistBUYERMOBIL2.removeAll(retlist);
				}
				retlist.addAll(retlistBUYERMOBIL2);
			}

			if (retlistNOMINEEMOBIL1 != null && retlistNOMINEEMOBIL1.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistNOMINEEMOBIL1.removeAll(retlist);
				}
				retlist.addAll(retlistNOMINEEMOBIL1);
			}

			if (retlistNOMINEEMOBIL2 != null && retlistNOMINEEMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistNOMINEEMOBIL2.removeAll(retlist);
				}
				retlist.addAll(retlistNOMINEEMOBIL2);
			}

			if (retlistSRVCUSTOMMOBIL1 != null
					&& retlistSRVCUSTOMMOBIL1.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistSRVCUSTOMMOBIL1.removeAll(retlist);
				}
				retlist.addAll(retlistSRVCUSTOMMOBIL1);
			}

			if (retlistSRVCUSTOMMOBIL2 != null
					&& retlistSRVCUSTOMMOBIL2.size() > 0) {
				if (retlist != null && retlist.size() > 0) {
					retlistBUYERMOBIL2.removeAll(retlist);
				}
				retlist.addAll(retlistSRVCUSTOMMOBIL2);
			}

			retarraylist = JsonUtils.getJsonString4JSONArray(retlist,
					"yyyy-MM-dd HH:mm:ss");

		}

		// 0.1 获取方法执行起始时间。
		long endTime = System.currentTimeMillis(); // 获取结束时间
		// msg="qbs查询结果:"+listbyqbs.size()+";for循环结果:"+i+"传入数组参数个数:"+usertels.length+";单个查询结果:"+listbyone.size();
		msg = "方法名:getcarlistbygetall" + "方法起始时间:" + startTime + "方法截止时间:"
				+ endTime;
		log.info("endTime:" + DateUtils.currentDatetime());
		if (retlist != null && retlist.size() > 0) {
			setMap(true, msg, retarraylist);
		} else {
			setMap(false, msg, retarraylist);
		}
		return SUCCESS;

	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getUserTels() {
		return userTels;
	}

	public void setUserTels(String userTels) {
		this.userTels = userTels;
	}

}
