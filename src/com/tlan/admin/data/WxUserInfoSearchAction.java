package com.tlan.admin.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxUser;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.ExportUtil;
import com.tlan.common.view.WxUserInfoView;

/**
 * 条件查询数据集合
 * 
 * @author magenm
 * 
 */
public class WxUserInfoSearchAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource(name = "baseService")
	private IBaseService<WxUserInfoView> userInfoService;

	private LoginBean loginBean = obtainLoginBean();

	private String data;
	private String filename;
	private InputStream inputStream;

	private String param;

	/**
	 * 导出用户信息
	 * 
	 * @Title: export
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月11日 下午4:03:10
	 */
	public String export() {
		JSONObject json = JSONObject.fromObject(data);
		if ("reg".equals(param)) {
			DetachedCriteria dc = getDc(json);
			List<WxUserInfoView> userInfoList = userInfoService
					.findByCriteria(dc);
			String[] headerNames = new String[] { "用户名", "微信名", "OpenID",
					"手机号码", "用户类型", "车牌号", "VIN码", "省份", "城市", "关注来源",
					"GZHHASH", "注册时间", "修改时间" };
			String[] headers = new String[] { "userName", "nickName", "openId",
					"userTel", "memberLevel", "carNumber", "carVin",
					"province", "city", "sourceFrom", "gzhHash", "createdOn",
					"modifyOn" };
			String[] comments = new String[] { null, null, null, null,
					"0:非会员   1:普通会员   2:高级会员", null, null, null, null, null,
					null, null, null };
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ExportUtil<WxUserInfoView> export = new ExportUtil<WxUserInfoView>();
			export.export("预约列表", headerNames, headers, comments, userInfoList,
					out, "yyyy-MM-dd");
			filename = "已注册用户数据.xls";
			inputStream = new ByteArrayInputStream(out.toByteArray());
		} else {
			DetachedCriteria dc = getGzDc(json);
			List<WxUserInfoView> userInfoList = userInfoService
					.findByCriteria(dc);
			String[] headerNames = new String[] { "用户名", "微信名", "OpenID",
					"手机号码", "状态", "关注来源", "GZHHASH", "关注时间", "取消关注时间" };
			String[] headers = new String[] { "userName", "nickName", "openId",
					"userTel", "status", "sourceFrom", "gzhHash", "createdOn",
					"cancelOn" };
			String[] comments = new String[] { null, null, null, null,
					"1:已关注   2:已取消关注", null, null, null, null };
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ExportUtil<WxUserInfoView> export = new ExportUtil<WxUserInfoView>();
			export.export("预约列表", headerNames, headers, comments, userInfoList,
					out, "yyyy-MM-dd");
			filename = "已关注用户数据.xls";
			inputStream = new ByteArrayInputStream(out.toByteArray());
		}

		return SUCCESS;
	}

	/**
	 * 获取数据列表
	 * 
	 * @return
	 */
	public String searchdata() {
		JSONObject json = JSONObject.fromObject(data);
		if ("reg".equals(param)) {
			DetachedCriteria dc = getDc(json);
			setMap(null, JSONArray.fromObject(userInfoService.findByCriteria(
					dc, getStart(), getLimit())),
					userInfoService.findCountByCriteria(dc));
		} else {
			DetachedCriteria dc = getGzDc(json);
			setMap(null, JSONArray.fromObject(userInfoService.findByCriteria(
					dc, getStart(), getLimit())),
					userInfoService.findCountByCriteria(dc));
		}
		return this.SUCCESS;
	}

	/**
	 * 设置查询条件
	 * 
	 * @Title: getDc
	 * @Description: TODO
	 * @param @param json
	 * @param @param type 1:dist 2：dlr
	 * @param @return
	 * @return DetachedCriteria
	 * @throws
	 * @Date 2014年3月11日 下午4:32:57
	 */
	private DetachedCriteria getDc(JSONObject json) {
		DetachedCriteria dc = DetachedCriteria.forClass(WxUserInfoView.class);
		if (loginBean.getRoleType().equals("ADMIN")
				|| loginBean.getRoleType().equals("DISTADMIN")) {
			// 取出所有的
		} else {
			dc.add(Restrictions.eq("gzhHash", loginBean.getGzhHash()));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("memberLevel"))) {
			dc.add(Restrictions.eq("memberLevel", json.getString("memberLevel")));
		} else {
			dc.add(Restrictions.or(
					Restrictions.eq("memberLevel", WxUser.LEVEL_PTHY),
					Restrictions.eq("memberLevel", WxUser.LEVEL_GJHY)));
		}

		if (DataUtils.isNotNullOrEmpty(json.getString("userName"))) {
			dc.add(Restrictions.eq("userName", json.getString("userName")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("userTel"))) {
			dc.add(Restrictions.eq("userTel", json.getString("userTel")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("userSex"))) {
			dc.add(Restrictions.eq("userSex", json.getString("userSex")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("gzStartDate"))) {
			String end = DataUtils.isNullOrEmpty(json.getString("gzEndDate")) ? DateUtils
					.currentDatetime() : json.getString("gzEndDate");
			dc.add(Restrictions.between("createdOn",
					json.getString("gzStartDate"), end));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("zcStartDate"))) {
			String end = DataUtils.isNullOrEmpty(json.getString("zcEndDate")) ? DateUtils
					.currentDatetime() : json.getString("zcEndDate");
			dc.add(Restrictions.between("registerTime",
					json.getString("zcStartDate"), end));
		}
		return dc;
	}

	/**
	 * 设置关注用户查询条件
	 * 
	 * @Title: getDc
	 * @Description: TODO
	 * @param @param json
	 * @param @param type 1:dist 2：dlr
	 * @param @return
	 * @return DetachedCriteria
	 * @throws
	 * @Date 2014年3月11日 下午4:32:57
	 */
	private DetachedCriteria getGzDc(JSONObject json) {
		DetachedCriteria dc = DetachedCriteria.forClass(WxUserInfoView.class);
		if (loginBean.getRoleType().equals("ADMIN")
				|| loginBean.getRoleType().equals("DISTADMIN")) {
			// 取出所有的
		} else {
			dc.add(Restrictions.eq("gzhHash", loginBean.getGzhHash()));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("userName"))) {
			dc.add(Restrictions.eq("userName", json.getString("userName")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("userTel"))) {
			dc.add(Restrictions.eq("userTel", json.getString("userTel")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("userSex"))) {
			dc.add(Restrictions.eq("userSex", json.getString("userSex")));
		}
		if (DataUtils.isNotNullOrEmpty(json.getString("gzStartDate"))) {
			String end = DataUtils.isNullOrEmpty(json.getString("gzEndDate")) ? DateUtils
					.currentDatetime() : json.getString("gzEndDate");
			dc.add(Restrictions.between("createdOn",
					json.getString("gzStartDate"), end));
		}
		return dc;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
