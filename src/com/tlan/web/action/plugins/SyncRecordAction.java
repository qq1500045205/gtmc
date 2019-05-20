package com.tlan.web.action.plugins;

//引入json日期处理
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.plugins.WeixinCustomervhcViews;
import com.tlan.common.model.plugins.WeixinOrderhistoryViews;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.JsonUtils;
import com.tlan.common.view.WxUserInfoView;

/**
 * 同步处理： 1、组织当前系统中微信用户数据 2、回写数据到当前系统
 * 
 * @PackageName:com.tlan.web.action.plugins
 * @ClassName:SyncRecordAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年3月10日 下午5:21:10
 * 
 */
public class SyncRecordAction extends BaseAction {

	private static final long serialVersionUID = -4637693014189647889L;
	private static final Log log = LogFactory.getLog(SyncRecordAction.class);
	@Resource(name = "baseService")
	private IBaseService<WxUserInfoView> userInfoService;
	@Resource(name = "baseService")
	private IBaseService<WeixinOrderhistoryViews> orderService;
	@Resource(name = "baseService")
	private IBaseService<WeixinCustomervhcViews> customervhcService;

	private String data;

	/**
	 * 组织微信用户数据
	 * 
	 * @Title: getWxUser
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月10日 下午5:27:11
	 */
	public String getWxUser() {
		List<WxUserInfoView> list = userInfoService
				.getAll(WxUserInfoView.class);
		JSONArray ary = new JSONArray();
		for (WxUserInfoView wxUserInfoView : list) {
			JSONObject obj = new JSONObject();
			obj.put("userTel", wxUserInfoView.getUserTel());
			obj.put("carVin", wxUserInfoView.getCarVin());
			obj.put("carNumberPfx", wxUserInfoView.getCarNumberPfx());
			obj.put("carNumber", wxUserInfoView.getCarNumber());
			if(obj.size() > 0){
				ary.add(obj);
			}
		}
		
		jsonObject = ary;
		return SUCCESS;
	}

	/**
	 * 保存维修履历，如存在则更新
	 * 
	 * @Title: addOrderHistory
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月10日 下午5:33:02
	 */
	public String addOrderHistory() {
		log.info("wx data:" + data);
		if (null != data) {
			if (!data.contains("error")) {
				List<WeixinOrderhistoryViews> orderList = JSONArray.toList(
						JSONArray.fromObject(data),
						new WeixinOrderhistoryViews(), new JsonConfig());
				orderService.saveList(orderList);
				log.info("保存成功：" + orderList.size());
			} else {
				log.info("保存失败：" + data);
			}
		} else {
			log.info("保存失败：" + data);
		}

		return SUCCESS;
	}
	
	/**
	 * 保存用户信息
	 * 
	 * @Title: addOrderHistory
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月10日 下午5:33:02
	 */
	public String addUser() {
		log.info("wx data:" + data);
		if (null != data) {
			if (!data.contains("error")) {
				List<WeixinCustomervhcViews> userList = JSONArray.toList(
						JSONArray.fromObject(data),
						new WeixinCustomervhcViews(), new JsonConfig());
				customervhcService.saveList(userList);
				log.info("保存用户成功：" + userList.size());
			} else {
				log.info("保存用户失败：" + data);
			}
		} else {
			log.info("保存用户失败：" + data);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: addOrderHistory
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月10日 下午9:29:55
	 */
	public String addCarInfo() {
		log.info("wx data:" + data);
		if (null != data) {
			if (!data.contains("error")) {
				JSONArray a = JsonUtils.getJsonString4JSONArray(data,
						"yyyy-MM-dd HH:mm:ss");
				@SuppressWarnings("unchecked")
				// 增加配置属性
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setIgnoreDefaultExcludes(false);
				jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
						new DateJsonValueProcessor("yyyy-MM-dd"));
				JSONArray.fromObject(JSONArray.fromObject(a), jsonConfig)
						.toString();
				List<WeixinCustomervhcViews> carinfoList = JSONArray.toList(
				// JSONArray.fromObject(data),
						a, new WeixinCustomervhcViews(), jsonConfig);

				customervhcService.saveList(carinfoList);
				log.info("保存成功：" + carinfoList.size());
			} else {
				log.info("保存失败：" + data);
			}
		} else {
			log.info("保存失败：" + data);
		}

		return SUCCESS;
	}

	public void setData(String data) {
		this.data = data;
	}

	public class DateJsonValueProcessor implements JsonValueProcessor {
		public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
		private DateFormat dateFormat;

		public DateJsonValueProcessor(String datePattern) {
			try {
				dateFormat = new SimpleDateFormat(datePattern);
			} catch (Exception ex) {
				dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
			}
		}

		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			return process(value);
		}

		public Object processObjectValue(String key, Object value,
				JsonConfig jsonConfig) {
			return process(value);
		}

		private Object process(Object value) {
			return dateFormat.format((Date) value);
		}
	}

}
