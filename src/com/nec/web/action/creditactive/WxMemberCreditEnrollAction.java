package com.nec.web.action.creditactive;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.creditactive.WxCreditEnrollView;
import com.tlan.common.model.creditactive.WxMemberCreditActiveF;
import com.tlan.common.model.creditactive.WxMemberCreditEnrollF;
import com.tlan.common.model.creditactive.WxMemberCreditManageView;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

/**
 * 积分活动
 * 
 * @author luo
 * 
 */
public class WxMemberCreditEnrollAction extends BaseAction implements Preparable,
		ModelDriven<WxMemberCreditEnrollF> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(WxMemberCreditEnrollAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditEnrollF> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxCreditEnrollView> wxCreditEnrollViewService;
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditManageView> wxMemberCreditManageView;
	
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditActiveF> wxMemberCreditActiveFService;

	private WxMemberCreditEnrollF active;
	// 获取登录用户信息
	private LoginBean user = obtainLoginBean();
	
	private String[] item;
	
	
	//搜索条件字段
	private Integer condition;
	private String search;
	private String startDate;
	private String endDate;
	

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}
	
	/**
	 * 执行数据操作
	 * 
	 * @return
	 */
	public String Control() {
		switch (getType()) {
		case "activeSave": {
			// 保存积分活动
			try {
				if(active.getWxMemberCreditEnrollGuid()!=null && !"".equals(active.getWxMemberCreditEnrollGuid()))
				{
					active.setCreatedBy(user.getLoginName());
					active.setCreatedOn(DateUtils.currentDatetime());
					active.setDelFlag(0);
					activeService.update(active);
				}
				else
				{
					active.setWxMemberCreditEnrollGuid(DataUtils.getUUID());
					active.setCreatedBy(user.getLoginName());
					active.setCreatedOn(DateUtils.currentDatetime());
					active.setDelFlag(0);
					
					activeService.save(active);
				}
				log.info(active.toString());
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				System.out.println(e);
				setMap(false, "添加失败", null);
			}

			break;

		}
		case "activeUpdata": {
			// 更新积分活动
			try {
				//active.setWxMemberCreditEnrollGuid(DataUtils.getUUID());
				active.setCreatedBy(user.getLoginName());
				active.setCreatedOn(DateUtils.currentDatetime());
				active.setDelFlag(0);
				activeService.update(active);
				log.info(active.toString());
				setMap(true, "更新成功", null);
			} catch (Exception e) {
				System.out.println(e);
				setMap(false, "更新失败", null);
			}

			break;
		}
		case "delete": {
			try {
				if (null != item) {
					for (int i = 0; i < item.length; i++) {
						System.out.println(item[i]);
						activeService.delete(WxMemberCreditEnrollF.class, "wxMemberCreditEnrollGuid",
								item[i].trim());
					}
					setMap(true, "删除成功", null);
				} else {
					setMap(false, "删除失败（没有数据）", null);
				}
			} catch (Exception e) {
				System.out.println(e);
				setMap(false, "更新失败", null);
			}

			break;
		}
		case "getData": {
			// 获取积分活动
			try {
				String a = active.getMemberCreditGuid();
				List<WxMemberCreditEnrollF> wtcs = activeService.findPage(
						WxMemberCreditEnrollF.class, getStart(), getLimit(),
						new String[] { "delFlag","memberCreditGuid" }, new Object[] { "0", a},"itemSort asc,createdOn asc");
				setMap(null, JSONArray.fromObject(wtcs),
						activeService.getCount(WxMemberCreditEnrollF.class,
								new String[] { "memberCreditGuid"  },
								new Object[] { a }, new String[] { "=" }));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setMap(false, "获取数据失败", null);
			}
			break;
		}
		case "getEnrollView": {
			// 查询报名选项
			try {
				String a = active.getMemberCreditGuid();
				//String a = "841760b6-f188-44d2-a6cd-95e423abbefb";
				List<WxCreditEnrollView> wtcs = wxCreditEnrollViewService.getAll(
						WxCreditEnrollView.class,
						"memberCreditGuid", a);
				setMap(true, "获取成功", wtcs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setMap(false, "获取数据失败", null);
			}
			break;
			}
		case "getWxMemberCreditManageView": {
			// 获取积分活动
			try {
				String a = active.getMemberCreditGuid();
				List<WxMemberCreditManageView> wtcs = wxMemberCreditManageView.findPage(
						WxMemberCreditManageView.class, getStart(), getLimit(),new String[] { "memberCreditGuid"},new Object[] {a});
				setMap(null, JSONArray.fromObject(wtcs),
						wxMemberCreditManageView.getCount(WxMemberCreditManageView.class,
								new String[] { "memberCreditGuid"  },
								new Object[] {  a }, new String[] { "=" }));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setMap(false, "获取数据失败", null);
			}
			break;
		}
		}
		return WxMemberCreditEnrollAction.SUCCESS;

	}
	
	public String CheckStatus()
	{
		try
		{
			//WxMemberCreditEnrollF obj = activeService.get(WxMemberCreditEnrollF.class, "wxMemberCreditEnrollGuid", active.getWxMemberCreditEnrollGuid());
			WxMemberCreditActiveF activeF = wxMemberCreditActiveFService.get(WxMemberCreditActiveF.class, "memberCreditGuid", active.getMemberCreditGuid());
			if(activeF.getStatus()==0)
			{
				this.setMap(true, "", null);
			}
			else
			{
				this.setMap(false, "该活动已发布!不能够变更信息", null);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "检查失败", null);
		}
		return this.SUCCESS;
	}
	
	public String SearchByCondition()
	{
		String field = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(WxMemberCreditManageView.class);
		List<WxMemberCreditManageView> allList = null;
		criteria.add(Restrictions.eq("memberCreditGuid", active.getMemberCreditGuid()));
		try
		{
			switch(condition)
			{
				case 1:
					field = "userName";
					break;
				case 2:
					field = "nickName";
					break;
				case 3:
					field = "userTel";
					break;
				default:
					field = null;
					search = null;
			}
			if(field!=null && search!=null && !"".equals(search))
			{
//				search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
				criteria.add(Restrictions.like(field, "%" + search + "%"));
			}
			if(startDate!=null && !"".equals(startDate))
			{
				criteria.add(Restrictions.ge("createdOn", startDate));
			}
			if(endDate!=null && !"".equals(endDate))
			{
				criteria.add(Restrictions.le("createdOn", endDate));
			}
			allList = wxMemberCreditManageView.findByCriteria(criteria);
			this.setMap(null, JSONArray.fromObject(allList), allList.size());
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		
		return this.SUCCESS;
	}
	
	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public WxMemberCreditEnrollF getModel() {
		return active;
	}

	@Override
	public void prepare() throws Exception {
		if (null == active) {
			active = new WxMemberCreditEnrollF();
		}

	}

}
