package com.tlan.admin.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.tlan.admin.module.ModuleAction;
import com.tlan.beans.LoginBean;
import com.tlan.beans.MessageBean;
import com.tlan.beans.UserAnswerBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.dao.IBaseDao;
import com.tlan.common.model.Webuser;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxMenu;
import com.tlan.common.model.WxMessageContent;
import com.tlan.common.model.WxModule;
import com.tlan.common.model.WxModuleContent;
import com.tlan.common.model.WxModulePage;
import com.tlan.common.model.WxProject;
import com.tlan.common.model.WxTemplateMessage;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.active.WxModuleContentActive;
import com.tlan.common.model.active.WxModuleContentSign;
import com.tlan.common.model.car.WxModuleContentLoanScheme;
import com.tlan.common.model.mannual.WxModuleContentMannualText;
import com.tlan.common.model.mannual.WxModuleContentMannualType;
import com.tlan.common.model.promotion.WxModuleContentPromotion;
import com.tlan.common.model.questionnaire.WxAnswerQuestionUserView;
import com.tlan.common.model.questionnaire.WxModuleContentQuestion;
import com.tlan.common.service.IBaseService;
import com.tlan.common.view.WxMessageView;
import com.tlan.common.view.WxModuleAssignView;
import com.tlan.common.view.WxModuleContentView;
import com.tlan.common.view.WxUserInfoView;
import com.tlan.contrants.ParamString;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 列表数据集合
 * 
 * @author magenm
 * 
 */
public class GriddataAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource(name = "baseService")
	private IBaseService<WxModulePage> componentService;
	@Resource(name = "baseService")
	private IBaseService<WxModule> moduleService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContent> moduleContentService;
	@Resource(name = "baseService")
	private IBaseService<WxMenu> wxMenuService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> wxGongZhongHaoService;
	@Resource(name = "baseService")
	private IBaseService<Webuser> webuserService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> webProjectService;
	@Resource(name = "baseService")
	private IBaseService<WxMessageContent> wxNewsContentService;
	@Resource(name = "baseService")
	private IBaseService<WxMessageView> wxMessageViewService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleAssignView> assignViewService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentView> wxModuleContentViewService;
	@Resource(name = "baseService")
	private IBaseService<WxUser> wxUserService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContent> wxModuleContentService;
	@Resource(name = "baseService")
	private IBaseService<WxTemplateMessage> wxTemplateMessageService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentActive> activeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentSign> signService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentQuestion> questionService;
	@Resource(name = "baseService")
	private IBaseService<WxAnswerQuestionUserView> answerQuestionUserService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentPromotion> promotionService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentLoanScheme> loanSchemeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualText> manulService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentMannualType> manultypeService;
	@Resource(name = "baseService")
	private IBaseService<WxUserInfoView> userInfoService;

	@Resource(name = "baseService")
	private IBaseService<Webuser> webUserService;

	private LoginBean loginBean = obtainLoginBean();

	private String param;

	private String questionGuid;
	private String actGuid;

	/**
	 * 获取数据列表
	 * 
	 * @return
	 */
	public String griddata() {
		String basepath = ServletActionContext.getServletContext()
				.getContextPath() + "/";
		String gzhHash = loginBean.getGzhHash();

		switch (getType()) {
		case "activeSign": {
			// 活动报名
			List<WxModuleContentSign> wtcs = signService.findPage(
					WxModuleContentSign.class, getStart(), getLimit(),
					new String[] { "actGuid", "delFlag" }, new Object[] {
							actGuid, 0 });
			setMap(null, JSONArray.fromObject(wtcs), activeService.getCount(
					WxModuleContentActive.class, new String[] { "actGuid",
							"delFlag" }, new Object[] { actGuid, 0 },
					new String[] { "=", "=" }));
			break;
		}
		case "active": {
			if (loginBean.getRoleType().equals("ADMIN")
					|| loginBean.getRoleType().equals("DISTADMIN")) { // 取出所有的
				// 品牌活动
				List<WxModuleContentActive> wtcs = activeService.findPage(
						WxModuleContentActive.class, getStart(), getLimit(),
						new String[] { "delFlag" }, new Object[] { "0" });
				setMap(null, JSONArray.fromObject(wtcs),
						activeService.getCount(WxModuleContentActive.class,
								new String[] { "delFlag" },
								new Object[] { "0" }, new String[] { "=" }));
			} else {
				// 品牌活动
				List<WxModuleContentActive> wtcs = activeService.findPage(
						WxModuleContentActive.class, getStart(), getLimit(),
						new String[] { "gzhHash", "delFlag" }, new Object[] {
								loginBean.getGzhHash(), "0" });
				setMap(null, JSONArray.fromObject(wtcs),
						activeService.getCount(WxModuleContentActive.class,
								new String[] { "gzhHash", "delFlag" },
								new Object[] { loginBean.getGzhHash(), "0" },
								new String[] { "=", "=" }));
			}
			break;
		}
		case "msgTemplate": {
			// 模版消息
			List<WxTemplateMessage> wtms = wxTemplateMessageService.findPage(
					WxTemplateMessage.class, getStart(), getLimit());
			setMap(null, JSONArray.fromObject(wtms),
					wxTemplateMessageService.getCount(WxTemplateMessage.class));
			break;
		}
		case "content": {
			// 内容模块
			List<WxModuleContent> wmcs = wxModuleContentService.findPage(
					WxModuleContent.class, getStart(), getLimit(),
					new String[] { "gzhHash" }, new Object[] { gzhHash },
					"createdOn desc");
			List<MessageBean> cntBeans = new ArrayList<>();
			for (WxModuleContent wmc : wmcs) {
				MessageBean cntBean = new MessageBean();
				cntBean.setId(wmc.getContentGuid());
				cntBean.setValue(wmc.getContentTitle());
				cntBean.setSrc(basepath + ModuleAction.ADMIN_CNT_HTML
						+ wmc.getContentPage());
				cntBean.setType(wmc.getNamePath());
				cntBeans.add(cntBean);
			}
			setMap(null, JSONArray.fromObject(cntBeans),
					wxModuleContentService.getCount(WxModuleContent.class,
							new String[] { "gzhHash" },
							new Object[] { gzhHash }, new String[] { "=" }));
			break;
		}
		case "message": {
			// 消息模块
			List<WxMessageView> msgviews = wxMessageViewService.findPage(
					WxMessageView.class, getStart(), getLimit(),
					new String[] { "gzhHash" }, new Object[] { gzhHash },
					"createdOn desc");
			List<MessageBean> msgBeans = new ArrayList<>();
			for (WxMessageView wmc : msgviews) {
				MessageBean msgBean = new MessageBean();
				msgBean.setId(wmc.getRuleGuid());
				msgBean.setValue(wmc.getRuleName());
				msgBean.setSrc(basepath + "/admin1/forwardShowMessage?type="
						+ wmc.getType() + "&newsGuid=" + wmc.getNewsGuid());
				msgBean.setType("消息模块-" + wmc.getTypeName());
				msgBeans.add(msgBean);
			}
			setMap(null, JSONArray.fromObject(msgBeans),
					wxMessageViewService.getCount(WxMessageView.class,
							new String[] { "gzhHash" },
							new Object[] { gzhHash }, new String[] { "=" }));
			break;
		}
		case "allModule": {
			List<WxModule> wxModules = this.moduleService
					.getAll(WxModule.class);
			JSONArray ary = new JSONArray();
			for (WxModule wm : wxModules) {
				JSONObject obj = new JSONObject();
				obj.put("moduleName", wm.getModuleName());
				obj.put("moduleGuid", wm.getModuleGuid());
				ary.add(obj);
			}
			setMap(null, ary, moduleService.getCount(WxModule.class));
			break;
		}
		case "allGzh": {
			List<WxGongzhonghao> gzhs = this.wxGongZhongHaoService
					.getAll(WxGongzhonghao.class);
			JSONArray ary = new JSONArray();
			for (WxGongzhonghao gzh : gzhs) {
				JSONObject obj = new JSONObject();
				obj.put("gzhName", gzh.getGzhName());
				obj.put("gzhGuid", gzh.getGzhGuid());
				ary.add(obj);
			}
			setMap(null, ary,
					wxGongZhongHaoService.getCount(WxGongzhonghao.class));
			break;
		}
		case "wxuser":
			wxuser();
			break;
		case "modulePage": {
			List<WxModulePage> comps = this.componentService.findPage(
					WxModulePage.class, getStart(), getLimit());
			setMap(null, JSONArray.fromObject(comps),
					componentService.getCount(WxModulePage.class, null, null,
							null));
			break;
		}
		case "module": {
			List<WxModule> modules = this.moduleService.findPage(
					WxModule.class, getStart(), getLimit(), "createdOn desc");

			setMap(null, JSONArray.fromObject(modules),
					moduleService.getCount(WxModule.class));
			break;
		}
		case "assignModule": {
			List<WxModuleAssignView> modules = this.assignViewService.findPage(
					WxModuleAssignView.class, getStart(), getLimit(),
					"createdOn desc");

			setMap(null, JSONArray.fromObject(modules),
					assignViewService.getCount(WxModuleAssignView.class));
			break;
		}
		case "moduleContent": {
			List<WxModuleContent> contents = this.moduleContentService
					.findPage(WxModuleContent.class, getStart(), getLimit(),
							new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { IBaseDao.CONTAINS },
							"createdOn desc");

			setMap(null, JSONArray.fromObject(contents),
					moduleContentService.getCount(WxModuleContent.class,
							new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { IBaseDao.CONTAINS }));
			break;
		}
		case "wxmenu": {
			List<WxMenu> wxmenus = this.wxMenuService.findPage(WxMenu.class,
					getStart(), getLimit(), new String[] { "gzhHash" },
					new Object[] { loginBean.getGzhHash() }, "menuOrder");

			JSONArray ary = new JSONArray();
			for (WxMenu wxMenu : wxmenus) {
				if (null == wxMenu.getParentGuid()
						&& wxMenu.getIsHaveChild() == WxMenu.HAVECHILD) {
					wxMenu.setModuleName("");
				}
				ary.add(JSONObject.fromObject(wxMenu));
			}
			setMap(null, JSONArray.fromObject(wxmenus), wxMenuService.getCount(
					WxMenu.class, new String[] { "gzhHash" },
					new Object[] { loginBean.getGzhHash() },
					new String[] { "=" }));
			break;
		}
		case "gzh":
			gzh();
			break;
		case "webuser": {
			if (loginBean.getRoleType().equals("ADMIN")
					|| loginBean.getRoleType().equals("DISTADMIN")) { // 取出所有的
				List<Webuser> webusers = webuserService.findPage(Webuser.class,
						getStart(), getLimit());
				setMap(null, JSONArray.fromObject(webusers),
						webuserService.getCount(Webuser.class));
			} else {
				List<Webuser> webusers = webuserService.findPage(Webuser.class,
						getStart(), getLimit(), new String[] { "gzhGuid" },
						new Object[] { loginBean.getGzhGuid() });
				setMap(null, JSONArray.fromObject(webusers),
						webuserService.getCount(Webuser.class));
			}
			break;
		}
		case "projects": {
			// TODO
			// 这里应该按照用的权限获取projects,
			// TODO

			List<WxProject> projects = webProjectService
					.getAll(WxProject.class);
			List<Object> retProjects = new ArrayList<Object>();
			for (WxProject item : projects) {
				HashMap<Object, Object> map = new HashMap<Object, Object>();
				map.put("projectName", item.getProjectName() == null ? ""
						: item.getProjectName());
				map.put("projectGuid", item.getProjectGuid() == null ? ""
						: item.getProjectGuid());
				retProjects.add(map);
			}
			setMap(null, JSONArray.fromObject(retProjects), retProjects.size());
			break;

		}
		//销售店管理
		case "userprojects": {
			// TODO
			// 这里应该按照用的权限获取projects,
			// TODO

			List<WxProject> projects = webProjectService.findPage(WxProject.class, getStart(), getLimit());
			setMap(null, JSONArray.fromObject(projects), webProjectService.getCount(WxProject.class));
			break;
		}
		
		//销售店管理_广州店
		case "userprojects_gz": {
			// TODO
			// 这里应该按照用的权限获取projects,
			// TODO

			//List<WxProject> projects = webProjectService.findPage(WxProject.class, getStart(), getLimit(),
			//new String[] {"projectGuid"},new String[] {"11A10" });
			
			//使用离线查询
			/*DetachedCriteria detachedCriteria=DetachedCriteria.forClass(WxProject.class);
			if(detachedCriteria!=null) {
				
				
			}*/


			HttpServletRequest request = ServletActionContext.getRequest();
			String projectGuid = request.getParameter("projectGuid");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			//封装条件
			WxProject WxProject1 = new WxProject();
			WxProject WxProject2 = new WxProject();
			WxProject1.setProjectGuid(projectGuid);
			WxProject1.setCreatedOn(beginTime);
			WxProject2.setCreatedOn(endTime);
			
			
			
			List<WxProject> projects = webProjectService.findByCriteria(WxProject1,WxProject2,getParam(),getStart(),getLimit());
			setMap(null, JSONArray.fromObject(projects), webProjectService.getCount(WxProject1,WxProject2,getParam()));
			break;
		}
		
		
		case "wxnewscontent": {
			List<WxMessageContent> wxNewsContents = wxNewsContentService
					.findPage(WxMessageContent.class, getStart(), getLimit(),
							new String[] { "gzhHash", "parentGuid" },
							new Object[] { loginBean.getGzhHash(), "null" },
							new String[] { "=", "is" }, "createdOn desc");

			setMap(null, JSONArray.fromObject(wxNewsContents),
					wxNewsContentService.getCount(WxMessageContent.class,
							new String[] { "gzhHash", "parentGuid" },
							new Object[] { loginBean.getGzhHash(), "null" },
							new String[] { "=", "is" }));
			break;
		}
		case "wxmodulecontent": {
			List<WxModuleContent> wxNewsContents = moduleContentService
					.findPage(WxModuleContent.class, getStart(), getLimit(),
							new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { "=" }, "createdOn desc");

			setMap(null, JSONArray.fromObject(wxNewsContents),
					moduleContentService.getCount(WxModuleContent.class,
							new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { "=" }));
			break;
		}
		case "contentView": {
			List<WxModuleContentView> contentList = wxModuleContentViewService
					.findPage(WxModuleContentView.class, getStart(),
							getLimit(), new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { "=" }, "createdOn desc");

			setMap(null, JSONArray.fromObject(contentList),
					wxModuleContentViewService.getCount(
							WxModuleContentView.class,
							new String[] { "gzhHash" },
							new Object[] { loginBean.getGzhHash() },
							new String[] { "=" }));
			break;
		}
		case "wxrule": {
			List<WxMessageView> wxMessageViews = wxMessageViewService.findPage(
					WxMessageView.class, getStart(), getLimit(),
					new String[] { "gzhHash" },
					new Object[] { loginBean.getGzhHash() }, "createdOn desc");
			setMap(null, JSONArray.fromObject(wxMessageViews),
					wxMessageViewService.getCount(WxMessageView.class,
							new String[] { "gzhHash", },
							new Object[] { loginBean.getGzhHash(), },
							new String[] { "=" }));
			break;
		}
		case "question":
			question();
			break;
		case "userAnswer":
			userAnswer();
			break;
		case "promotion": {
			List<WxModuleContentPromotion> promotions = promotionService
					.findPage(WxModuleContentPromotion.class, getStart(),
							getLimit(), new String[] { "gzhHash", "delFlag" },
							new Object[] { loginBean.getGzhHash(), 0 },
							new String[] { "=", "=" }, "promotionGuid asc");
			setMap(null, JSONArray.fromObject(promotions),
					promotionService.getCount(WxModuleContentPromotion.class,
							new String[] { "gzhHash", "delFlag" },
							new Object[] { loginBean.getGzhHash(), 0 },
							new String[] { "=", "=" }));
			break;
		}
		case "answer": {
			List<WxAnswerQuestionUserView> questions = answerQuestionUserService
					.findPage(WxAnswerQuestionUserView.class, getStart(),
							getLimit(), new String[] { "questionGuid" },
							new Object[] { getQuestionGuid() },
							new String[] { "=" }, "answerTime desc");
			setMap(null, JSONArray.fromObject(questions),
					answerQuestionUserService.getCount(
							WxAnswerQuestionUserView.class,
							new String[] { "questionGuid" },
							new Object[] { getQuestionGuid() },
							new String[] { "=" }));
			break;
		}
		case "loan": {
			List<WxModuleContentLoanScheme> loanSchemes = loanSchemeService
					.findPage(WxModuleContentLoanScheme.class, getStart(),
							getLimit(),
							"loanSchemeName asc, monthNum asc, firstPayPercent asc");

			setMap(null, JSONArray.fromObject(loanSchemes),
					loanSchemeService.getCount(WxModuleContentLoanScheme.class));
			break;
		}

		case "manul": {
			List<WxModuleContentMannualText> mnlList = manulService.findPage(
					WxModuleContentMannualText.class, getStart(), getLimit());

			setMap(null, JSONArray.fromObject(mnlList), manulService.getCount(
					WxModuleContentMannualText.class, null, null, null));
			break;
		}
		case "manultype": {
			List<WxModuleContentMannualType> mnlList = manultypeService
					.findPage(WxModuleContentMannualType.class, getStart(),
							getLimit(), "ord asc");

			setMap(null, JSONArray.fromObject(mnlList),
					manultypeService.getCount(WxModuleContentMannualType.class,
							null, null, null));
			break;
		}
		}
		return this.SUCCESS;

	}

	/**
	 * 微信用户管理
	 * 
	 * @Title: wxuser
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 * @Date 2014年3月3日 下午3:41:55
	 */
	private void wxuser() {
		/************ 注册用户处理 ****************/
		if ("reg".equals(param)) {
			if (loginBean.getRoleType().equals("ADMIN")
					|| loginBean.getRoleType().equals("DISTADMIN")) { // 取出所有的
				List<WxUserInfoView> wxusers = this.userInfoService.findPage(
						WxUserInfoView.class, getStart(), getLimit(),
						new String[] { "memberLevel" },
						new Object[] { WxUser.LEVEL_PTHY + ","
								+ WxUser.LEVEL_GJHY }, new String[] { "in" },
						"createdOn desc");
				setMap(null, JSONArray.fromObject(wxusers),
						userInfoService.getCount(WxUserInfoView.class,
								new String[] { "memberLevel" },
								new Object[] { WxUser.LEVEL_PTHY + ","
										+ WxUser.LEVEL_GJHY },
								new String[] { "in" }));
			} else {
				List<WxUserInfoView> wxusers = this.userInfoService.findPage(
						WxUserInfoView.class, getStart(), getLimit(),
						new String[] { "gzhHash", "memberLevel" },
						new Object[] { loginBean.getGzhHash(),
								WxUser.LEVEL_PTHY + "," + WxUser.LEVEL_GJHY },
						new String[] { "=", "in" }, "createdOn desc");
				setMap(null, JSONArray.fromObject(wxusers),
						userInfoService.getCount(WxUserInfoView.class,
								new String[] { "gzhHash", "memberLevel" },
								new Object[] {
										loginBean.getGzhHash(),
										WxUser.LEVEL_PTHY + ","
												+ WxUser.LEVEL_GJHY },
								new String[] { "=", "in" }));
			}
			/************ 关注用户处理 ****************/
		} else {
			if (loginBean.getRoleType().equals("ADMIN")
					|| loginBean.getRoleType().equals("DISTADMIN")) { // 取出所有的
				List<WxUserInfoView> wxusers = this.userInfoService.findPage(
						WxUserInfoView.class, getStart(), getLimit());
				setMap(null, JSONArray.fromObject(wxusers),
						userInfoService.getCount(WxUserInfoView.class));
			} else {
				List<WxUserInfoView> wxusers = this.userInfoService.findPage(
						WxUserInfoView.class, getStart(), getLimit(),
						new String[] { "gzhHash" },
						new Object[] { loginBean.getGzhHash() },
						"createdOn desc");
				setMap(null, JSONArray.fromObject(wxusers),
						userInfoService.getCount(WxUserInfoView.class,
								new String[] { "gzhHash" },
								new Object[] { loginBean.getGzhHash() },
								new String[] { "=" }));
			}
		}
	}

	private void gzh() {
		LoginBean loginBean = (LoginBean) this.map
				.get(ParamString.USER_LOGIN_SESSION_OBJECT);
		String privileges = loginBean.getPrivileges();
		int privilegeLevel = loginBean.getPrivilegeLevel();
		List<WxGongzhonghao> wxgzhs = null;
		if (privilegeLevel == 0) {
			// admin
			wxgzhs = this.wxGongZhongHaoService.getAll(WxGongzhonghao.class);
		} else if (privilegeLevel == 1) {
			// area admin
			wxgzhs = this.wxGongZhongHaoService.getAll(WxGongzhonghao.class,
					"area", privileges, "in");
		} else if (privilegeLevel == 2) {
			// project admin
			wxgzhs = this.wxGongZhongHaoService.getAll(WxGongzhonghao.class,
					"project_guid", privileges, "in");
		}

		List<Object> rets = new ArrayList<Object>();
		// String apiBase = PropertiesUtil.getProperty("api_base_url");
		for (WxGongzhonghao item : wxgzhs) {
			HashMap<Object, Object> map = new HashMap<Object, Object>();

			// project
			WxProject proj = item.getWxProject();
			String projName = proj.getProjectName();
			// area
			// WxArea area = item.getWxArea();
			// String areaString = area.getAreaName();
			// api
			// String api = item.getGzhHash();
			// String apiUrl = api == null ? apiBase : apiBase + api;

			map.put("gzhGuid", item.getGzhGuid());
			// map.put("area", areaString);
			map.put("projectName", projName);
			// map.put("projectNo", proj.getProjectNumber());
			map.put("gzhname",
					item.getGzhName() == null ? "" : item.getGzhName());
			map.put("gzhAccount",
					item.getGzhAccount() == null ? "" : item.getGzhAccount());
			map.put("token", item.getToken() == null ? "" : item.getToken());
			map.put("api", item.getGzhHash());
			map.put("status", item.getStatus() == null ? "" : item.getStatus());

			rets.add(map);
		}

		System.out.println("------this is a test------" + rets.size());
		// List<String> wxgzhs = new ArrayList<String>();
		// wxgzhs.add("asfsdfadsf1");
		// wxgzhs.add("asfsdfadsf2");
		setMap(null, JSONArray.fromObject(rets), rets.size());
	}

	private void question() {
		if (loginBean.getRoleType().equals("ADMIN")
				|| loginBean.getRoleType().equals("DISTADMIN")) {
			List<WxModuleContentQuestion> questions = questionService.findPage(
					WxModuleContentQuestion.class, getStart(), getLimit(),
					new String[] { "deleteFlag" }, new Object[] { 0 },
					new String[] { "=" }, "questionGuid asc");
			setMap(null, JSONArray.fromObject(questions),
					questionService.getCount(WxModuleContentQuestion.class,
							new String[] { "deleteFlag" }, new Object[] { 0 },
							new String[] { "=" }));

		} else {
			List<WxModuleContentQuestion> questions = questionService.findPage(
					WxModuleContentQuestion.class, getStart(), getLimit(),
					new String[] { "gzhHash", "deleteFlag" }, new Object[] {
							loginBean.getGzhHash(), 0 }, new String[] { "=",
							"=" }, "questionGuid asc");
			setMap(null, JSONArray.fromObject(questions),
					questionService.getCount(WxModuleContentQuestion.class,
							new String[] { "gzhHash", "deleteFlag" },
							new Object[] { loginBean.getGzhHash(), 0 },
							new String[] { "=", "=" }));
		}
	}

	private void userAnswer() {
		List<WxAnswerQuestionUserView> userAnswer = answerQuestionUserService
				.findPage(WxAnswerQuestionUserView.class, getStart() * 5,
						getLimit() * 5, "answerTime desc, openId asc, questionGuid asc");
		List<UserAnswerBean> userAnswerBeans = new ArrayList<UserAnswerBean>();
		for (int i=0; i<userAnswer.size(); i+=5) {
			UserAnswerBean userAnswerBean = new UserAnswerBean();
			userAnswerBean.setUserName(userAnswer.get(i).getUserName());
			userAnswerBean.setUserTel(userAnswer.get(i).getUserTel());
			userAnswerBean.setAnswerTime(userAnswer.get(i).getAnswerTime());
			int index = 0;
			for (int j=i; j<i+5; j++) {
				index ++;
				switch (index) {
				case 1:
					userAnswerBean.setQ1(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA1(userAnswer.get(j).getAnswer());
					break;
				case 2:
					userAnswerBean.setQ2(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA2(userAnswer.get(j).getAnswer());
					break;
				case 3:
					userAnswerBean.setQ3(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA3(userAnswer.get(j).getAnswer());
					break;
				case 4:
					userAnswerBean.setQ4(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA4(userAnswer.get(j).getAnswer());
					break;
				case 5:
					userAnswerBean.setQ5(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA5(userAnswer.get(j).getAnswer());
					break;
				}
			}
			userAnswerBeans.add(userAnswerBean);
		}
		setMap(null, JSONArray.fromObject(userAnswerBeans),
				answerQuestionUserService
						.getCount(WxAnswerQuestionUserView.class)/5);
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getQuestionGuid() {
		return questionGuid;
	}

	public void setQuestionGuid(String questionGuid) {
		this.questionGuid = questionGuid;
	}

	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

}
