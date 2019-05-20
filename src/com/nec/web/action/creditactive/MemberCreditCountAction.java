package com.nec.web.action.creditactive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.creditactive.WxMemberCreditActiveView;
import com.tlan.common.model.creditactive.WxMemberCreditCountView;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.ExportUtil;
import com.tlan.contrants.ParamString;

public class MemberCreditCountAction extends BaseAction implements ModelDriven<WxMemberCreditCountView>,Preparable{
	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditCountView> creditCountService;
	
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gongzhonghaoService;
	
	private WxMemberCreditCountView creditCount;
	
	
	private Integer condition;
	private String search;
	private String startDate;
	private String endDate;
	private String searchGzhGuid;
	private String searchProvince;
	private String searchCity;
	private String searchCountry;
	private String filename;
	private ByteArrayInputStream inputStream;
	
	
	
	public String listGongzhonghao()
	{
		List<WxGongzhonghao> list = null;
		try
		{
			list = gongzhonghaoService.getAll(WxGongzhonghao.class);
			JSONArray arr = new JSONArray();
			for(WxGongzhonghao gzh : list)
			{
				JSONObject obj = new JSONObject();
				obj.put("text",gzh.getGzhName());
				obj.put("value", gzh.getGzhGuid());
				arr.add(obj);
			}
			jsonObject = arr;
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String listMemberCreditCount()
	{
		List<WxMemberCreditCountView> list = null;
		try
		{
			LoginBean loginBean = (LoginBean) ActionContext.getContext().getSession().get(ParamString.USER_LOGIN_SESSION_OBJECT);
			if(loginBean.getGzhHash()!=null && !"".equals(loginBean.getGzhGuid()))
			{
				list = creditCountService.getAll(WxMemberCreditCountView.class,"gzhHash",loginBean.getGzhHash());
			}
			else
			{
				list = creditCountService.getAll(WxMemberCreditCountView.class);
			}
			System.out.println(JSONArray.fromObject(list));
			this.setMap(null, JSONArray.fromObject(list), 2);
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	
	public String searchCreditCount()
	{
		List<WxMemberCreditCountView> list = null;
		DetachedCriteria criteria = getCriteria();
		try{
			list = creditCountService.findByCriteria(criteria);
			this.setMap(null, JSONArray.fromObject(list), list.size());
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		
		return this.SUCCESS;
	}
	

	public String exportExcel() throws UnsupportedEncodingException {

		DetachedCriteria criteria = getCriteria();
		List<WxMemberCreditCountView> exportAllList = null;
		exportAllList = creditCountService.findByCriteria(criteria);
		int index = 0;
		
		String[] headers = new String[] { "actName", "startOn", "endOn",
				"gzhGuid", "gzhName", "gzhHash", "gzhFans", "gzhAccount",
				"projectGuid", "userGuid", "userName", "userSex", "nickName",
				"fakeid", "openId", "userTel", "createtime", "createdOnTicket",
				"modifyOn", "cancelOn", "status", "memberLevel", "memberNo",
				"user2carGuid", "registerTime", "registerFrom", "followedFrom",
				"headimgurl", "city", "province", "country", "createdOn",
				"countCredit" };
		String[] headerNames = new String[] { "活动名称", "开始时间", "结束时间",
				"gzh_guid", "公众号名称", "gzh_hash", "粉丝数", "微信公众号帐号",
				"project_guid", "user_guid", "用户名", "性别", "微信用户昵称", "微信用户id",
				"open_id", "用户手机号", "微信用户关注时间", "created_on_ticket", "修改时间",
				"取消关注时间", "关注状态", "会员级别", "会员号", "user2car_guid",
				"register_time", "register_from", "关注来源", "headimgurl", "市",
				"省", "国家", "用户参与日期", "总积分" };
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ExportUtil<WxMemberCreditCountView> data = new ExportUtil<WxMemberCreditCountView>();
		data.export("用户积分查询统计", headerNames, headers, null, exportAllList, out,
				"yyyy-MM-dd");
		filename = "用户积分查询统计.xls";
		inputStream = new ByteArrayInputStream(out.toByteArray());
		return SUCCESS;
	}
	
	public DetachedCriteria getCriteria(){
		DetachedCriteria criteria = DetachedCriteria.forClass(WxMemberCreditCountView.class);
		String field = null;
		try
		{
			switch(condition)
			{
			case 1:
				field = "userName";
				break;
			case 2:
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
				criteria.add(Restrictions.eq("gzhHash", loginBean.getGzhHash()));
			}
			
			
			if(null!=search && !"".equals(search))
			{
				//System.out.println("解决乱码前:" + search);
				//解决GET乱码..
//				search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
//				System.out.println("解决乱码后:" + search);
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
			
			if(searchGzhGuid!=null && !"".equals(searchGzhGuid))
			{
				criteria.add(Restrictions.eq("gzhGuid", searchGzhGuid));
			}
			
			if(searchProvince!=null && !"".equals(searchProvince))
			{
//				searchProvince = new String(searchProvince.getBytes("ISO-8859-1"),"UTF-8");
//				System.out.println("解决乱码后:" + searchProvince);
				criteria.add(Restrictions.like("province", "%" + searchProvince +"%"));
			}
			
			if(searchCity!=null && !"".equals(searchCity))
			{
//				searchCity = new String(searchCity.getBytes("ISO-8859-1"),"UTF-8");
//				System.out.println("解决乱码后:" + searchCity);
				criteria.add(Restrictions.like("city","%" + searchCity + "%"));
			}
			
			if(searchCountry!=null && !"".equals(searchCountry))
			{
//				searchCountry = new String(searchCountry.getBytes("ISO-8859-1"),"UTF-8");
//				System.out.println("解决乱码后:" + searchCountry);
				criteria.add(Restrictions.like("country","%" + searchCountry + "%"));
			}
		}catch(Exception e)
			{
				System.out.println(e);
				criteria=null;
			}
		return criteria;
	}

	public String getFilename() {
		try {
			return new String(filename.getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void setFilename(String filename) {
		try {
			this.filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public WxMemberCreditCountView getCreditCount() {
		return creditCount;
	}

	public void setCreditCount(WxMemberCreditCountView creditCount) {
		this.creditCount = creditCount;
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

	public String getSearchGzhGuid() {
		return searchGzhGuid;
	}

	public void setSearchGzhGuid(String searchGzhGuid) {
		this.searchGzhGuid = searchGzhGuid;
	}

	public String getSearchProvince() {
		return searchProvince;
	}

	public void setSearchProvince(String searchProvince) {
		this.searchProvince = searchProvince;
	}

	public String getSearchCity() {
		return searchCity;
	}

	public void setSearchCity(String searchCity) {
		this.searchCity = searchCity;
	}

	public String getSearchCountry() {
		return searchCountry;
	}

	public void setSearchCountry(String searchCountry) {
		this.searchCountry = searchCountry;
	}

	@Override
	public void prepare() throws Exception {
		if(null==creditCount)
		{
			creditCount = new WxMemberCreditCountView();
		}
	}

	@Override
	public WxMemberCreditCountView getModel() {
		return creditCount;
	}
}
