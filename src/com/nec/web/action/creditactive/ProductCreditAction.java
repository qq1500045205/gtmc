package com.nec.web.action.creditactive;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.creditactive.WxProductListF;
import com.tlan.common.model.creditactive.WxProductListView;
import com.tlan.common.model.creditactive.WxProductTypeM;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.Page;

public class ProductCreditAction extends BaseAction implements Preparable,ModelDriven<WxProductListF>{

	private Log log = LogFactory.getLog(ProductCreditAction.class);
	
	private WxProductListF productListF;
	
	private String checkKey;
	
	private String[] item;
	
	@Resource(name="baseService")
	private IBaseService<WxProductListF> productListFService;
	
	@Resource(name="baseService")
	private IBaseService<WxProductListView> productListView;
	
	@Resource(name="baseService")
	private IBaseService<WxProductTypeM> productTypeMService;
	
	@Resource(name="baseService")
	private IBaseService<WxUser> userService;
	
	
	private String gzhHash;
	
	private String openid;
	private Integer count;
	
	//查询条件
	private Integer condition;
	private String search;
	private String typeid;
	private Integer productStatus;
	private Integer minCredit;
	private Integer maxCredit;
	
	//微信前端分页.
	private Page page;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	

	public String saveProductListF()
	{
		try
		{
			System.out.println(productListF.getProductDoc());
			productListF.setStatus((productListF.getStatus()==null)?0:productListF.getStatus());
			productListF.setProductStock((productListF.getProductStock()==null)?0:productListF.getProductStock());
			productListF.setDelFlag((productListF.getDelFlag()==null)?0:productListF.getDelFlag());
			
			if(productListF.getProductGuid()!=null && !"".equals(productListF.getProductGuid()))
			{
				//判断是否存在这个记录.
				WxProductListF product = productListFService.get(WxProductListF.class, "productGuid", productListF.getProductGuid());
				if(product!=null)
					productListFService.update(productListF);
				this.setMap(true, "修改成功", null);
			}
			else
			{
				productListF.setProductGuid(DataUtils.getUUID());
				productListFService.save(productListF);
				this.setMap(true, "添加成功", null);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String updateProductListF()
	{
		try
		{
			productListF.setStatus((productListF.getStatus()==null)?0:productListF.getStatus());
			productListF.setProductStock((productListF.getProductStock()==null)?0:productListF.getProductStock());
			productListF.setDelFlag((productListF.getDelFlag()==null)?0:productListF.getDelFlag());
			
			productListFService.update(productListF);
			this.setMap(true, "修改成功", null);
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String deleteProductListF()
	{
		WxProductListF product = null; 
		try
		{
			for(String guid : item)
			{
				product = productListFService.get(WxProductListF.class, "productGuid", guid);
//				if(product!=null)
//					productListFService.delete(product);
				if(product!=null)
				{
					product.setDelFlag(1);
					productListFService.update(product);
				}
				
					
			}
			this.setMap(true, "删除成功", null);
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	public String checkProductKey()
	{
		System.out.println(checkKey);
		List<WxProductListF> list = productListFService.getAll(WxProductListF.class,"productKey",checkKey);
		System.out.println(list.size());
		if(list.size()>0)
		{
			this.setMap(false, "重复的商品编码", null);
		}
		else
		{
			this.setMap(true, "", null);
		}
		
		return this.SUCCESS;
	}
	
	public String listProductType()
	{
		List<WxProductTypeM> list = productTypeMService.getAll(WxProductTypeM.class);
		JSONArray arr = new JSONArray();
		for(WxProductTypeM type : list)
		{
			JSONObject obj = new JSONObject();
			obj.put("text", type.getProductName());
			obj.put("value", type.getProductTypeGuid());
			arr.add(obj);
		}
		jsonObject = arr;
		return this.SUCCESS;
	}
	
	public String getAllProduct()
	{
		try
		{
//			List<WxProductListView> list = productListView.getAll(WxProductListView.class);
			DetachedCriteria criteria = DetachedCriteria.forClass(WxProductListView.class);
			criteria.add(Restrictions.eq("delFlag", 0));
			List<WxProductListView> list = productListView.findByCriteria(criteria);
			this.setMap(null, JSONArray.fromObject(list), list.size());
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		
		return this.SUCCESS;
	}
	
	
	
	public String searchByCondition()
	{
		String field = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(WxProductListView.class);
		List<WxProductListView> list = null;
		try
		{
			if(condition!=null && !"".equals(condition))
			{
				switch(condition)
				{
					case 1:
						field = "productName";
						break;
					case 2:
						field = "productKey";
						break;
					default:
						field = null;
						search = null;
						break;
				}
				if(null!=search && !"".equals(search))
				{
					//解决GET乱码..
//					search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
					criteria.add(Restrictions.like(field, "%" + search + "%"));
				}
			}
			
			criteria.add(Restrictions.eq("delFlag", 0));
			
			if(null!=minCredit)
			{
				criteria.add(Restrictions.ge("productCredit", minCredit));
			}
			if(null!=maxCredit)
			{
				criteria.add(Restrictions.le("productCredit", maxCredit));
			}
			
			if(typeid!=null && !"".equals(typeid))
			{
				criteria.add(Restrictions.eq("productTypeId", typeid));
			}
			
			System.out.println(productStatus);
			if(productStatus!=null && !"".equals(productStatus))
			{
				if(productStatus!=2)
				{
					criteria.add(Restrictions.eq("status", productStatus));
				}
			}

			list = productListView.findByCriteria(criteria);
			System.out.println(list.size());
			this.setMap(null, JSONArray.fromObject(list), list.size());
//			this.setMap(true, "", null);
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		return this.SUCCESS;
	}
	
	
	public String getProductByPage()
	{
		if(page==null)
			page = new Page();
		
		page.setPageSize(4);
		List<WxProductListF> list = null;
		try
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(WxProductListF.class);
			criteria.add(Restrictions.eq("delFlag", 0));
			if(typeid !=null && !"0".equals(typeid) && !"".equals(typeid))
			{
				criteria.add(Restrictions.eq("productTypeId", typeid));
				list = productListFService.findPage(WxProductListF.class, (page.getCurrentPage()-1)*page.getPageSize(), page.getPageSize(),new String[]{"productTypeId"},new Object[]{typeid});
			}
			else
			{
				list = productListFService.findPage(WxProductListF.class, (page.getCurrentPage()-1)*page.getPageSize(), page.getPageSize());
			}
			int count = productListFService.findCountByCriteria(criteria);
			page.setRowCount(count);
			count = (count%4!=0)?(count/4)+1:count/4;
			page.setTotalPage(count);
			page.setList(list);
			this.setMap(null, JSONObject.fromObject(page), count);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return SUCCESS;
	}
	
	
	public String getProductById()
	{
		ActionContext context = ActionContext.getContext();
		DetachedCriteria criteria = DetachedCriteria.forClass(WxProductListView.class);
		criteria.add(Restrictions.eq("productGuid", productListF.getProductGuid()));
		List<WxProductListView> list = productListView.findByCriteria(criteria);
		context.put("gzhHash", gzhHash);
		if(list.size()>0)
		{
			context.put("Product", list.get(0));
		}
		
		return this.SUCCESS;
	}
	
	public String checkCredit()
	{
		try
		{
			WxProductListF product = productListFService.get(WxProductListF.class, "productGuid", productListF.getProductGuid());
			
			WxUser user = userService.get(WxUser.class, "openId", openid);
			
			Integer credit = product.getProductCredit() * count;
			
			
			if(user.getCountCredit()==null || user.getCountCredit()<=0)
			{
				this.setMap(false, "积分不够", null);
			}
			else if(user.getCountCredit()>=credit)
			{
				this.setMap(true, "积分足够", null);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			this.setMap(false, "fail", null);
		}
		
		return SUCCESS;
	}

	public String getCheckKey() {
		return checkKey;
	}

	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
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

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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

	public String getGzhHash() {
		return gzhHash;
	}
	public void setGzhHash(String gzhHash) {
		this.gzhHash = gzhHash;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public IBaseService<WxUser> getUserService() {
		return userService;
	}
	public void setUserService(IBaseService<WxUser> userService) {
		this.userService = userService;
	}
	@Override
	public WxProductListF getModel() {
		return productListF;
	}

	@Override
	public void prepare() throws Exception {
		if(null==productListF)
		{
			productListF = new WxProductListF();
		}
	}
}
