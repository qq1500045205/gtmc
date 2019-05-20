package com.nec.web.action.creditactive;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.creditactive.WxCreditActiveView;
import com.tlan.common.model.creditactive.WxMemberCreditActiveView;
import com.tlan.common.service.IBaseService;

/**
 * 积分活动
 * 
 * @author luo
 * 
 */
public class CreditActiveDetailAction extends BaseAction implements Preparable,
		ModelDriven<WxCreditActiveView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(CreditActiveDetailAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxCreditActiveView> activeService;

	@Resource(name = "baseService")
	private IBaseService<WxMemberCreditActiveView> creditActiveViewService;
	
	
	@Resource(name = "baseService")
	private IBaseService<WxUser> userService;
	
	private WxCreditActiveView active;

	/**
	 * 执行数据操作
	 * 
	 * @return
	 */
	public String creditDetail() {

		int countCredit = 0;
		int currentCredit = 0;
		switch (getType()) {
		case "getData": {
			// 获取总积分
			List<WxCreditActiveView> criditList = this.activeService.getAll(
					WxCreditActiveView.class, new String[] { "openId",
							"gzhHash" }, new Object[] { active.getOpenId(),
							active.getGzhHash() });
//			for (WxCreditActiveView cridit : criditList) {
//				int actcredit = cridit.getActCredit();
//				creditcount = actcredit + creditcount;
//			}
			
			
			WxUser user = userService.get(WxUser.class, "openId", active.getOpenId());
			
			WxMemberCreditActiveView creditActiveView = creditActiveViewService.get(WxMemberCreditActiveView.class, "userGuid", user.getUserGuid());
			
			countCredit = (creditActiveView.getCountCredit()==null)?0:creditActiveView.getCountCredit().intValue();
			currentCredit = (creditActiveView.getCurrentCredit()==null)?0:creditActiveView.getCurrentCredit().intValue();
			
			
			System.out.println(countCredit);
			System.out.println(currentCredit);
			
			// 获取积分活动
			List<WxCreditActiveView> actList = this.activeService.findPage(
					WxCreditActiveView.class, getStart(), getLimit(),
					new String[] { "openId", "gzhHash" },
					new Object[] { active.getOpenId(), active.getGzhHash() });
			log.info(active.toString());

			JSONArray ary = new JSONArray();
			for (WxCreditActiveView act : actList) {
				JSONObject obj = new JSONObject();
				obj.put("userName", act.getUserName());
				obj.put("memberCreditGuid", act.getMemberCreditGuid());
				obj.put("actName", act.getActName());
				obj.put("creDate", act.getCreatedOn());
				obj.put("actCredit", act.getActCredit());
				obj.put("start", getStart());
				obj.put("countCredit", countCredit);
				obj.put("currentCredit", currentCredit);
				ary.add(obj);
			}
			setMap(null, ary, activeService.getCount(WxCreditActiveView.class,
					new String[] { "openId", "gzhHash" },
					new Object[] { active.getOpenId(), active.getGzhHash() },
					new String[] { "=", "=" }));
			break;
		}
		}
		return CreditActiveDetailAction.SUCCESS;

	}

	@Override
	public WxCreditActiveView getModel() {
		return active;
	}

	@Override
	public void prepare() throws Exception {
		if (null == active) {
			active = new WxCreditActiveView();
		}

	}

}
