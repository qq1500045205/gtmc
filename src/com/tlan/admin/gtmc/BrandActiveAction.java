package com.tlan.admin.gtmc;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.active.WxModuleContentActive;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;

/**
 * 品牌活动
 * 
 * @author magenm
 * 
 */
public class BrandActiveAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentActive> {

	private static Log log = LogFactory.getLog(BrandActiveAction.class);
	
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentActive> activeService;

	private WxModuleContentActive active;
	// 获取登录用户信息
	private LoginBean user = obtainLoginBean();

	private String[] item;
	private String gzhs;

	/**
	 * 保存活动
	 * 
	 * @return
	 */
	public String add() {
		try {
			active.setCreatedBy(user.getUserName());
			active.setCreatedOn(DateUtils.currentDatetime());
			active.setModifyBy(user.getUserName());
			active.setModifyOn(DateUtils.currentDatetime());
			active.setGzhHash(user.getGzhHash());
			active.setStatus(0);
			active.setDelFlag(0);
			activeService.save(active);
			log.info(active.toString());
			setMap(true, "添加成功", active.getActGuid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMap(false, "添加失败", null);
		}
		return SUCCESS;
	}

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	public String delete() {
		if (null != item) {
			for (int i = 0; i < item.length; i++) {
				activeService.update(WxModuleContentActive.class,
						new String[] { "delFlag" }, new Object[] { 1 },
						"actGuid = '" + item[i] + "'");
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 发布活动
	 * 
	 * @return
	 */
	public String publish() {
		if (null != item) {
			for (int i = 0; i < item.length; i++) {
				activeService.update(WxModuleContentActive.class, new String[] {
						"status", "publishOn", "publishBy" }, new Object[] { 1,
						DateUtils.currentDatetime(), user.getUserName() },
						"actGuid = '" + item[i] + "'");
			}
			setMap(true, "发布成功", null);
		} else {
			setMap(false, "发布失败", null);
		}
		return this.SUCCESS;
	}
	
	/**
	 * 活动下发
	 * 
	 * @return
	 */
	public String assign() {
		if (null != gzhs) {
			active = activeService.get(WxModuleContentActive.class, "actGuid", active.getActGuid());
			JSONArray ary = JSONArray.fromObject(gzhs);
			for (Object object : ary) {
				JSONObject obj = JSONObject.fromObject(object);
				
				active.setGzhHash(obj.get("gzhHash").toString());
				active.setModifyBy(user.getUserName());
				active.setModifyOn(DateUtils.currentDatetime());
				active.setStatus(0);
				active.setSource(1);
				active.setActGuid(null);
				
				activeService.save(active);
			}
		
			setMap(true, "下发成功", null);
		} else {
			setMap(false, "下发失败", null);
		}
		return this.SUCCESS;
	}

	@Override
	public WxModuleContentActive getModel() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == active) {
			active = new WxModuleContentActive();
		}
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public String getGzhs() {
		return gzhs;
	}

	public void setGzhs(String gzhs) {
		this.gzhs = gzhs;
	}

}
