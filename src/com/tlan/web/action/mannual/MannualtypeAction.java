package com.tlan.web.action.mannual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.mannual.WxModuleContentMannualText;
import com.tlan.common.model.mannual.WxModuleContentMannualType;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;

public class MannualtypeAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentMannualType> {
	private static final long serialVersionUID = -2974074306837574168L;

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualType> mannualTypeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualText> mannualTextService;

	private WxModuleContentMannualType mannualType;

	private LoginBean loginBean = obtainLoginBean();
	private String[] item;

	public String getMannualType() {
		List<WxModuleContentMannualType> wxMannualType = new ArrayList<WxModuleContentMannualType>();
		wxMannualType = mannualTypeService.getAll(
				WxModuleContentMannualType.class, new String[] { "gzhHash" },
				new Object[] { mannualType.getGzhHash() },
				new String[] { "=" }, "ord asc");
		setMap(true, "success", wxMannualType);
		return this.SUCCESS;

	}

	public String addMannualType() {
		mannualType.setTypeGuid(DataUtils.getUUID());
		mannualType.setGzhHash(loginBean.getGzhHash());
		mannualTypeService.save(mannualType);
		setMap(true, "添加成功", null);
		return this.SUCCESS;
	}

	public String delMannualType() {
		if (null != item) {
			for (int i = 0; i < item.length; i++) {
				// 删除关键字
				mannualTypeService.delete(WxModuleContentMannualType.class,
						"type_guid", item[i]);
				mannualTextService.delete(WxModuleContentMannualText.class,
						"type_guid", item[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（无数据）", null);
		}

		return this.SUCCESS;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	@Override
	public WxModuleContentMannualType getModel() {
		// TODO Auto-generated method stub
		return mannualType;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == mannualType) {
			mannualType = new WxModuleContentMannualType();
		}
	}

}
