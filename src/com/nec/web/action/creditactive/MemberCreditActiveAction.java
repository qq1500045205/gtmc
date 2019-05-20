package com.nec.web.action.creditactive;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.creditactive.WxCreditActiveView;
import com.tlan.common.model.creditactive.WxMemberCreditActiveF;
import com.tlan.common.model.creditactive.WxMemberCreditActiveView;
import com.tlan.common.service.IBaseService;
import com.tlan.contrants.ParamString;

public class MemberCreditActiveAction extends BaseAction implements Preparable,ModelDriven<Object>{

	private Log log = LogFactory.getLog(MemberCreditActiveAction.class);
	
	@Resource(name="baseService")
	private IBaseService<WxMemberCreditActiveView> memberCreditActiveService;
	
	@Resource(name="baseService")
	private IBaseService<WxMemberCreditActiveF> creditActiveFService;
	
	@Resource(name="baseService")
	private IBaseService<WxCreditActiveView> creditActiveService; 
	
	@Resource(name="baseService")
	private IBaseService<Webuser> webuserService;
	
	@Resource(name="baseService")
	private IBaseService<WxGongzhonghao> gongzhonghaoService;
	
	private WxMemberCreditActiveView creditActive;
	
	private String memberGuid;
	
	private Integer searchCredit;
	private Integer minCredit;
	//最小积分(查询)
	private Integer maxCredit;
	//最大积分(查询)
	private String search;
	//查询的关键字

	private Integer condition;
	//查询的条件
	
	
	
	
	public String getAllUserCredit()
	{
		List<WxMemberCreditActiveView> allList = null;
		try
		{
			LoginBean loginBean = (LoginBean) ActionContext.getContext().getSession().get(ParamString.USER_LOGIN_SESSION_OBJECT);
			if(loginBean.getGzhGuid()==null || "".equals(loginBean.getGzhGuid()))
			{
				allList = memberCreditActiveService.getAll(WxMemberCreditActiveView.class);
			}
			else if(loginBean.getGzhHash()!=null)
			{
				allList = memberCreditActiveService.getAll(WxMemberCreditActiveView.class,"gzhHash",loginBean.getGzhHash());
			}
			
			System.out.println(JSONArray.fromObject(allList));
			this.setMap(null,JSONArray.fromObject(allList),allList.size());
		}catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return SUCCESS;
	}
	
	public String getCreditRecordByUser()
	{
		try
		{
			
			List<WxCreditActiveView> list = creditActiveService.getAll(WxCreditActiveView.class,"memberGuid",memberGuid);			
			this.setMap(null,JSONArray.fromObject(list),list.size());
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false,"fail",null);
		}
		return SUCCESS;
	}
	
	public String searchCreditRecord()
	{
		System.out.println(searchCredit);
		try
		{
			List<WxMemberCreditActiveView> list = null;
			String field = null;
			String creditField = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WxMemberCreditActiveView.class);
			switch(condition)
			{
				case 1:
					field = "gzhName";
					break;
				case 2:
					field = "userName";
					break;
				case 3:
					field = "userTel";
					break;
				default:
					field=null;
					search=null;
					break;
			}
			
			
			LoginBean loginBean = (LoginBean) ActionContext.getContext().getSession().get(ParamString.USER_LOGIN_SESSION_OBJECT);
			if(loginBean.getGzhHash()!=null && !"".equals(loginBean.getGzhGuid()))
			{
				criteria.add(Restrictions.eq("gzhHash",loginBean.getGzhHash()));
			}
			
			if(null!=search && !"".equals(search))
			{
				//System.out.println("解决乱码前:" + search);
				//解决GET乱码..
//				search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
//				System.out.println("解决乱码后:" + search);
				criteria.add(Restrictions.like(field, "%" + search + "%"));
			}
			
			if(searchCredit==0)
			{
				creditField = "currentCredit";
			}
			else
			{
				creditField = "countCredit";
			}
			
			if(null!=minCredit)
			{
				criteria.add(Restrictions.ge(creditField, minCredit));
			}
			
			if(null!=maxCredit)
			{
				criteria.add(Restrictions.le(creditField, maxCredit));
			}
			list = memberCreditActiveService.findByCriteria(criteria);
			
			this.setMap(null, JSONArray.fromObject(list), list.size());
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		
		
		return SUCCESS;
	}
	
	public String getMemberGuid() {
		return memberGuid;
	}

	public void setMemberGuid(String memberGuid) {
		this.memberGuid = memberGuid;
	}

	public Integer getMinCredit() {
		return minCredit;
	}

	public void setMinCredit(Integer minCredit) {
		this.minCredit = minCredit;
	}

	public Integer getMaxCredit() {
		return maxCredit;
	}

	public void setMaxCredit(Integer maxCredit) {
		this.maxCredit = maxCredit;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getSearchCredit() {
		return searchCredit;
	}

	public void setSearchCredit(Integer searchCredit) {
		this.searchCredit = searchCredit;
	}

	@Override
	public Object getModel() {
		return creditActive;
	}

	@Override
	public void prepare() throws Exception {
		if(null==creditActive)
		{
			creditActive = new WxMemberCreditActiveView();
		}
	}
}
