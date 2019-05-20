package com.tlan.admin.module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.BaiduMapBean;
import com.tlan.beans.LoginBean;
import com.tlan.beans.ModulesBean;
import com.tlan.beans.PagesActionBean;
import com.tlan.beans.PagesBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxModule;
import com.tlan.common.model.WxModuleContent;
import com.tlan.common.model.WxModuleGongzhonghao;
import com.tlan.common.model.WxModulePage;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.FileUtils;
import com.tlan.common.view.WxModuleContentView;

public class ModuleAction extends BaseAction implements Preparable,
		ModelDriven<WxModule> {

	private static final long serialVersionUID = -2974074306837574168L;

	private static Logger log = Logger.getLogger(ModuleAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxModule> wxModuleService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContent> wxModuleContentService;
	@Resource(name = "baseService")
	private IBaseService<WxModulePage> wxModulePageService;
	@Resource(name = "baseService")
	private IBaseService<WxProject> wxPojectService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleGongzhonghao> wxModuleGongzhonghaoService;
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentView> contentViewService;

	private String result;
	private String urlResult;
	private WxModule wxModule;
	private String contentTitle;
	private String contentGuid;

	private String resultPage;
	private String[] item;

	private String modules;
	private String gzhs;

	/**
	 * 获取登录信息
	 */
	public LoginBean loginBean = obtainLoginBean();

	public static final String ADMIN_HTML = "wx/html/";
	public static final String ADMIN_MODULE_HTML = "wx/modhtml/";
	public static final String ADMIN_CNT_HTML = "wx/cnthtml/";
	public static final String ADMIN_CHOICEMODULES = "/wx/html/";

	public String getContentPages() {
		List<WxModuleContentView> wxModuleContents = this.contentViewService
				.getAll(WxModuleContentView.class, "gzhHash",
						loginBean.getGzhHash());
		JSONArray ja = new JSONArray();
		for (WxModuleContentView moduleContent : wxModuleContents) {

			String contentGuid = moduleContent.getContentGuid();
			if (contentGuid != null && contentGuid.length() > 0) {
				String contentName = moduleContent.getContentTitle();
				String modPageName = moduleContent.getModPageName();
				System.out.println("modPageName:" + modPageName);
				if (modPageName != null && modPageName.length() > 0) {
					String[] pageNames = modPageName.split(";");
					for (int i = 0; i < pageNames.length; i++) {
						JSONObject jo = new JSONObject();
						jo.put("pageName", contentName + " - " + pageNames[i]);
						jo.put("pageUrl", contentGuid + "-" + i + ".jsp");

						ja.add(jo);
					}
				}
			}
		}
		setMap(true, "success", ja);

		return this.SUCCESS;
	}

	/**
	 * 跳转至设置模块页面
	 * 
	 * @return
	 */
	public String forwardAddModule() {

		List<WxModulePage> wxmPages = wxModulePageService
				.getAll(WxModulePage.class,new String[]{}, new Object[]{}, new String[]{}, "pageCode asc");

		List<ModulesBean> mBeans = new ArrayList<>();
		int idx = 0;
		for (WxModulePage wxModulePage : wxmPages) {
			ModulesBean bean = new ModulesBean();
			bean.setId(wxModulePage.getPageGuid());
			bean.setCode(wxModulePage.getPageCode());
			bean.setSrc(ServletActionContext.getRequest().getContextPath()
					+ ADMIN_CHOICEMODULES + wxModulePage.getPageFtl());
			bean.setIndex(idx++);
			bean.setName(wxModulePage.getPageName());
			mBeans.add(bean);
		}

		jsonObject = JSONArray.fromObject(mBeans).toString();

		return this.SUCCESS;
	}

	/**
	 * 跳转至设置模块内容页面
	 * 
	 * @return
	 */
	public String forwardEditModule() {

		List<WxModule> wxm = wxModuleService.getAll(WxModule.class,
				"moduleGuid", wxModule.getModuleGuid());
		wxModule = wxm.get(0);
		resultPage = wxModule.getModPage().replace(".", "-edit.");
		return this.SUCCESS;
	}

	/**
	 * 保存模块
	 * 
	 * @return
	 */
	public String save() {

		// 获取servlet上下文的绝对路径
		String basepath = ServletActionContext.getServletContext().getRealPath(
				File.separator);

		log.info("basepath:" + basepath);

		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("url", PagesActionBean.class);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String title = (String) jsonObj.get("title");
		String desc = (String) jsonObj.get("description");
		System.out.println(result);

		// 记录首页
		String firstPage = null;
		String modPage = null;
		String guid = DataUtils.getUUID();
		int idx = 0;

		List<PagesBean> pages = new ArrayList<>();
		JSONArray ary = JSONArray.fromObject(jsonObj.get("pages"));

		for (Object object : ary) {
			JSONObject obj = JSONObject.fromObject(object);
			PagesBean bean = (PagesBean) JSONObject.toBean(obj,
					PagesBean.class, classMap);
			pages.add(bean);

		}

		List<PagesBean> newPages = new ArrayList<>();
		for (PagesBean bean : pages) {
			PagesBean newBean = new PagesBean();
			newBean.setModuleId(bean.getModuleId());
			newBean.setName(bean.getName());

			if (null == modPage) {
				modPage = bean.getSrc().substring(
						bean.getSrc().lastIndexOf("/") + 1);
			}

			String newName = guid + "-" + (idx++)
					+ bean.getSrc().substring(bean.getSrc().indexOf("."));
			newBean.setSrc(newName);
			newBean.setUrl(bean.getUrl());
			newPages.add(newBean);

		}

		for (PagesBean bean : newPages) {
			for (PagesActionBean action : bean.getUrl()) {
				if (action.getPageIndex() != -1) {
					action.setSrc(newPages.get(action.getPageIndex()).getSrc());
				}
			}
		}

		for (int i = 0; i < newPages.size(); i++) {
			PagesBean oldBean = pages.get(i);
			PagesBean newBean = newPages.get(i);

			FileUtils fileUtils = new FileUtils();
			try {
				String oldName = oldBean.getSrc();
				String oldFile = basepath + ADMIN_HTML
						+ oldName.substring(oldName.lastIndexOf("/") + 1);

				log.info("oldFile:" + oldFile);

				String replacement = JSONArray.fromObject(newBean.getUrl())
						.toString();
				String html = fileUtils.readTxt(oldFile);
				html = html.replace("</head>", "<script>var PAGE_URLS='"
						+ replacement + "';</script></head>");
				html = html.replace("##URL##", replacement);

				String newName = newBean.getSrc();
				String newFile = basepath + ADMIN_MODULE_HTML + newName;

				log.info("newFile:" + newFile);

				if (null == firstPage) {
					firstPage = newName;
				}

				fileUtils.createFile(newFile, html);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		WxModule wxm = new WxModule();
		wxm.setModuleName(title);
		wxm.setDescription(desc);
		wxm.setContentFirst(firstPage);
		wxm.setModPage(listToString(pages, ";"));
		wxm.setModPageName(NameToString(pages, ";"));
		wxm.setModuleGuid(guid);
		wxm.setPageCount(newPages.size());
		wxm.setCreatedOn(DateUtils.currentDatetime());
		wxm.setCreatedBy(loginBean.getUserName());
		wxm.setModifyOn(DateUtils.currentDatetime());
		wxm.setModifyBy(loginBean.getUserName());

		wxm.setNamePath("内容模块");
		wxm.setGzhHash(loginBean.getGzhHash());

		wxModuleService.save(wxm);

		setMap(true, "保存成功", null);

		return this.SUCCESS;
	}

	/**
	 * 将list 以 split做分割，拼成字符串
	 * 
	 * @param list
	 * @param split
	 * @return
	 */
	private String listToString(List<PagesBean> list, String split) {

		if (null == list || list.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getSrc()
					.substring(list.get(i).getSrc().lastIndexOf("/") + 1));
			// sb.append(list.get(i).getName());
			if (i < list.size() - 1) {
				sb.append(split);
			}
		}

		return sb.toString();
	}

	private String NameToString(List<PagesBean> list, String split) {

		if (null == list || list.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			// sb.append(list.get(i).getSrc()
			// .substring(list.get(i).getSrc().lastIndexOf("/") + 1));
			sb.append(list.get(i).getName());
			if (i < list.size() - 1) {
				sb.append(split);
			}
		}

		return sb.toString();
	}

	/**
	 * 添加内容模块
	 * 
	 * @return
	 */
	public String saveContent() {
		List<WxModule> wxm = wxModuleService.getAll(WxModule.class,
				"moduleGuid", wxModule.getModuleGuid());
		wxModule = wxm.get(0);

		// 创建模版内容对象
		WxModuleContent wmc = new WxModuleContent();
		wmc.setCreatedOn(DateUtils.currentDatetime());
		wmc.setCreatedBy(loginBean.getUserName());
		wmc.setContentTitle(contentTitle);
		// 生成guid
		String guid = DataUtils.getUUID();
		wmc.setContentGuid(guid);
		// 设置所属公众号
		wmc.setGzhHash(loginBean.getGzhHash());
		// 设置使用模块
		wmc.setModuleGuid(wxModule.getModuleGuid());
		wmc.setModuleName(wxModule.getModuleName());
		wmc.setNamePath(wxModule.getNamePath());
		// 获取servlet上下文的绝对路径
		String basepath = ServletActionContext.getServletContext().getRealPath(
				File.separator);

		FileUtils fileUtils = new FileUtils();
		try {
			for (int i = 0; i < wxModule.getPageCount(); i++) {

				StringBuilder sb = new StringBuilder();
				sb.append(wxModule.getModuleGuid()).append("-").append(i)
						.append(".jsp");
				String oldFileName = sb.toString();
				// filename = filename.substring(filename.lastIndexOf("/") + 1);
				String oldFile = basepath + ADMIN_MODULE_HTML + oldFileName;

				log.info("oldFile:" + oldFile);

				String html = fileUtils.readTxt(oldFile);

				// 添加数据
				if (result != null && result.length() > 0) {
					String pageResult = "";
					JSONArray ary = JSONArray.fromObject(result);
					for (Object object : ary) {
						JSONObject obj = JSONObject.fromObject(object);
						int index = obj.getInt("index");
						String value = obj.getString("value");
						if (index == i) {
							System.out.println(value);
							pageResult = value;
						}
					}
					html = html.replace("##DATA##", pageResult);
				}
				
				if (urlResult != null && urlResult.length() > 0) {
					String pageUrl = "";
					JSONArray urlAry = JSONArray.fromObject(urlResult);
					for (Object object : urlAry) {
						JSONObject obj = JSONObject.fromObject(object);
						int index = obj.getInt("index");
						String url = obj.getString("url");
						if (index == i) {
							System.out.println(url);
							pageUrl = url;
						}
					}
					html = html.replace("</head>", "<script>var MODULE_URLS='"
							+ pageUrl + "';</script></head>");
				}
				html = html.replace("##GZHTYPE##", null != loginBean
						.getGzhType() ? loginBean.getGzhType() : "TEST");
				// 添加公众好标识
				html = html.replace("##GZHHASH##", loginBean.getGzhHash());
				// 修改地址
				html = html.replaceAll(wxModule.getModuleGuid(), guid);

				String newFileName = oldFileName.replace(
						wxModule.getModuleGuid(), guid);
				String newFile = basepath + ADMIN_CNT_HTML + newFileName;

				fileUtils.createFile(newFile, html);

				if (i == 0) {
					wmc.setContentPage(newFileName);
					wxModuleContentService.save(wmc);
				}

			}
			setMap(true, "保存成功", null);
		} catch (IOException e) {
			setMap(false, "保存失败", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 编辑内容模块
	 * 
	 * @return
	 */
	public String editContent() {

		List<WxModule> wxm = wxModuleService.getAll(WxModule.class,
				"moduleGuid", wxModule.getModuleGuid());
		wxModule = wxm.get(0);

		List<WxModuleContent> wxmc = wxModuleContentService.getAll(
				WxModuleContent.class, "contentGuid", contentGuid);
		WxModuleContent wmc = wxmc.get(0);

		wmc.setModifyOn(DateUtils.currentDatetime());
		wmc.setModifyBy(loginBean.getUserName());
		wmc.setContentTitle(contentTitle);

		// 获取servlet上下文的绝对路径
		String basepath = ServletActionContext.getServletContext().getRealPath(
				File.separator);

		FileUtils fileUtils = new FileUtils();
		try {
			for (int i = 0; i < wxModule.getPageCount(); i++) {

				StringBuilder sb = new StringBuilder();
				sb.append(wxModule.getModuleGuid()).append("-").append(i)
						.append(".jsp");
				String oldFileName = sb.toString();
				// filename = filename.substring(filename.lastIndexOf("/") + 1);
				String oldFile = basepath + ADMIN_MODULE_HTML + oldFileName;

				log.info("oldFile:" + oldFile);

				String html = fileUtils.readTxt(oldFile);

				// 添加数据
				if (result != null && result.length() > 0) {
					String pageResult = "";
					JSONArray ary = JSONArray.fromObject(result);
					for (Object object : ary) {
						JSONObject obj = JSONObject.fromObject(object);
						int index = obj.getInt("index");
						String value = obj.getString("value");
						if (index == i) {
							pageResult = value;
						}
					}
					html = html.replace("##DATA##", pageResult);
				}

				if (urlResult != null && urlResult.length() > 0) {
					String pageUrl = "";
					JSONArray urlAry = JSONArray.fromObject(urlResult);
					for (Object object : urlAry) {
						JSONObject obj = JSONObject.fromObject(object);
						int index = obj.getInt("index");
						String url = obj.getString("url");
						if (index == i) {
							System.out.println(url);
							pageUrl = url;
						}
					}
					html = html.replace("</head>", "<script>var MODULE_URLS='"
							+ pageUrl + "';</script></head>");
				}

				html = html.replace(
						"##GZHTYPE##",
						(loginBean.getGzhType() == null ? loginBean
								.getGzhType() : "TEST"));
				// 添加公众好标识
				html = html.replace("##GZHHASH##", loginBean.getGzhHash());
				// 修改地址
				html = html.replaceAll(wxModule.getModuleGuid(),
						wmc.getContentGuid());

				String newFileName = oldFileName.replace(
						wxModule.getModuleGuid(), wmc.getContentGuid());
				String newFile = basepath + ADMIN_CNT_HTML + newFileName;

				fileUtils.createFile(newFile, html);

				if (i == 0) {
					wmc.setContentPage(newFileName);
					wxModuleContentService.update(wmc);
				}

			}
			setMap(true, "编辑成功", null);
		} catch (IOException e) {
			setMap(false, "编辑失败", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 保存内容到session
	 * 
	 * @return
	 */
	public String saveSession() {
		// ActionContext.getContext().getSession().put("result", result);
		JSONObject jo = JSONObject.fromObject(result);
		String paramName = jo.getString("name");
		Object paramValue = jo.get("value");

		try {
			map.put(paramName, paramValue);
			setMap(true, "success", null);
		} catch (Exception e) {
			setMap(true, "fail", null);
		}

		return this.SUCCESS;
	}

	/**
	 * 删除模块内容
	 * 
	 * @return
	 */
	public String deleteContent() {
		if (null != item) {
			String basepath = ServletActionContext.getServletContext()
					.getRealPath(File.separator);

			String file = basepath + ADMIN_CNT_HTML;
			for (int i = 0; i < item.length; i++) {
				wxModuleContentService.delete(WxModuleContent.class,
						"contentGuid", item[i]);
				// 删除内容文件
				FileUtils fileUtils = new FileUtils();
				// fileUtils.delAllFile(file, item[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 删除模块
	 * 
	 * @return
	 */
	public String deleteModule() {
		if (null != item) {
			String basepath = ServletActionContext.getServletContext()
					.getRealPath(File.separator);

			String file = basepath + ADMIN_MODULE_HTML;
			for (int i = 0; i < item.length; i++) {
				wxModuleService.delete(WxModule.class, "moduleGuid", item[i]);
				// 删除内容文件
				FileUtils fileUtils = new FileUtils();
				// fileUtils.delAllFile(file, item[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 删除分配的模块
	 * 
	 * @return
	 */
	public String deleteAssignModule() {
		if (null != item) {
			for (int i = 0; i < item.length; i++) {
				wxModuleGongzhonghaoService.delete(WxModuleGongzhonghao.class,
						"mappingGuid", item[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	/**
	 * 保存模块分配
	 * 
	 * @return
	 */
	public String saveAssign() {
		System.out.println(modules);
		JSONArray mds = JSONArray.fromObject(modules);
		JSONArray gs = JSONArray.fromObject(gzhs);

		for (Object object : gs) {
			JSONObject gsObject = JSONObject.fromObject(object);
			for (Object obj : mds) {
				JSONObject mdObject = JSONObject.fromObject(obj);
				WxModuleGongzhonghao wmgzh = new WxModuleGongzhonghao();
				wmgzh.setMappingGuid(DataUtils.getUUID());
				wmgzh.setGzhGuid(gsObject.getString("gzhGuid"));
				wmgzh.setModuleGuid(mdObject.getString("moduleGuid"));
				wmgzh.setCreatedOn(DateUtils.currentDate());
				wmgzh.setCreatedBy(loginBean.getUserName());
				wmgzh.setIsEditable(WxModuleGongzhonghao.EDITABLE);
				wmgzh.setIsPublic(WxModuleGongzhonghao.UNPUBLIC);

				// 检测是否已经分配
				List<WxModuleGongzhonghao> list = wxModuleGongzhonghaoService
						.getAll(WxModuleGongzhonghao.class,
								new String[] { "moduleGuid", "gzhGuid" },
								new Object[] { wmgzh.getModuleGuid(),
										wmgzh.getGzhGuid() });
				// 如果没有分配，则保存，如已分配，则无须分配
				if (list.isEmpty()) {
					// 查询gzhhash
					WxGongzhonghao gzh = gzhService.get(WxGongzhonghao.class,
							"gzhGuid", gsObject.getString("gzhGuid"));
					wmgzh.setGzhGuid(gzh.getGzhGuid());
					wmgzh.setGzhHash(gzh.getGzhHash());
					wxModuleGongzhonghaoService.save(wmgzh);
				}
			}
		}

		setMap(true, "保存成功", null);

		return this.SUCCESS;
	}

	public String getAllModulePages() {

		return this.SUCCESS;
	}

	/**
	 * 获取来访地图信息
	 * 
	 * @return
	 */
	public String getBaiDuMap() {
		WxProject project = wxPojectService.get(WxProject.class, "gzhHash",
				loginBean.getGzhHash());

		BaiduMapBean bean = new BaiduMapBean();
		bean.setAddress(project.getAddress());
		bean.setCity("北京市");
		bean.setDesc(project.getDescription());
		bean.setSrc(project.getImage());
		bean.setTel(project.getMobile());
		bean.setTitle("");

		setMap(true, "获 取成功", JSONObject.fromObject(bean));
		return this.SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUrlResult() {
		return urlResult;
	}

	public void setUrlResult(String urlResult) {
		this.urlResult = urlResult;
	}

	public WxModule getWxModule() {
		return wxModule;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getResultPage() {
		return resultPage;
	}

	public void setResultPage(String resultPage) {
		this.resultPage = resultPage;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public void setGzhs(String gzhs) {
		this.gzhs = gzhs;
	}

	public String getModules() {
		return modules;
	}

	public String getGzhs() {
		return gzhs;
	}

	public String getContentGuid() {
		return contentGuid;
	}

	public void setContentGuid(String contentGuid) {
		this.contentGuid = contentGuid;
	}

	@Override
	public WxModule getModel() {
		// TODO Auto-generated method stub
		return wxModule;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == wxModule) {
			wxModule = new WxModule();
		}
	}

}
