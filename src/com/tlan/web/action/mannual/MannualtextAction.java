package com.tlan.web.action.mannual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.mannual.WxModuleContentMannualText;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class MannualtextAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentMannualText> {
	private static final long serialVersionUID = -2974074306837574168L;

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualText> mannualTextService;
	private WxModuleContentMannualText MannualText;
	private String[] item;

	public String getMannualList() {
		List<WxModuleContentMannualText> wxMannualTextlist = new ArrayList<WxModuleContentMannualText>();
		wxMannualTextlist = mannualTextService.getAll(
				WxModuleContentMannualText.class, "typeGuid",
				MannualText.getTypeGuid());
		setMap(true, "success", wxMannualTextlist);
		return this.SUCCESS;

	}

	public String getMannualText() {
		List<WxModuleContentMannualText> wxMannualText = new ArrayList<WxModuleContentMannualText>();
		wxMannualText = mannualTextService.getAll(
				WxModuleContentMannualText.class, "textGuid",
				MannualText.getTextGuid());
		setMap(true, "success", wxMannualText);
		return this.SUCCESS;

	}

	public String addMannualText() {
		MannualText.setTextGuid(DataUtils.getUUID());
		MannualText.setCreatedTime(DateUtils.currentDatetime()); 
		mannualTextService.save(MannualText);
		setMap(true, "添加成功", null);
		return this.SUCCESS;

	}
	
	public String editMannualText() {
		mannualTextService.update(MannualText);
		setMap(true, "编辑成功", null);
		return this.SUCCESS;

	}

	public String delMannualText() {
		if (null!= item) {
			for (int i = 0; i < item.length; i++) {
				// 删除关键字
				mannualTextService.delete(WxModuleContentMannualText.class,
						"text_guid", item[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（无数据）", null);
		}

		return this.SUCCESS;
	}

	@Override
	public WxModuleContentMannualText getModel() {
		// TODO Auto-generated method stub
		return MannualText;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == MannualText) {
			MannualText = new WxModuleContentMannualText();
		}
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

}
