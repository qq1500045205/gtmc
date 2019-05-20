package com.tlan.admin.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jdom.JDOMException;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.admin.module.ModuleAction;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxDynamicContentInstance;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxMessageContent;
import com.tlan.common.model.WxRule;
import com.tlan.common.model.WxUser;
import com.tlan.common.model.WxUserLocation;
import com.tlan.common.model.WxUserStatus;
import com.tlan.common.model.active.WxModuleContentActive;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.common.utils.ReflectUtils;
import com.tlan.common.view.WxRuleContentView;
import com.tlan.common.view.WxRuleKeywordView;
import com.tlan.contrants.ParamString;
import com.tlan.wxkit.WeiXin;
import com.tlan.wxkit.bean.TypeBean;
import com.tlan.wxkit.bean.UserBean;
import com.tlan.wxkit.bean.WeiXinBean;
import com.tlan.wxkit.http.HttpUtils;
import com.tlan.wxkit.vo.recv.WxRecvEventMsg;
import com.tlan.wxkit.vo.recv.WxRecvGeoMsg;
import com.tlan.wxkit.vo.recv.WxRecvLinkMsg;
import com.tlan.wxkit.vo.recv.WxRecvMsg;
import com.tlan.wxkit.vo.recv.WxRecvPicMsg;
import com.tlan.wxkit.vo.recv.WxRecvTextMsg;
import com.tlan.wxkit.vo.send.WxSendMsg;
import com.tlan.wxkit.vo.send.WxSendNewsMsg;
import com.tlan.wxkit.vo.send.WxSendTextMsg;

/**
 * 微信公众平台对接类
 * 
 * @author magenm
 *   
 */
@Component
@Scope(value = "prototype")
public class CopyOfWeixinAction extends BaseAction implements Preparable,
		ModelDriven<WeiXinBean> {

	private static final long serialVersionUID = -6097283387071968367L;
	private static Logger log = Logger.getLogger(CopyOfWeixinAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxRuleKeywordView> keywordViewService;
	@Resource(name = "baseService")
	private IBaseService<WxRuleContentView> ruleContentViewService;
	@Resource(name = "baseService")
	private IBaseService<WxRule> ruleService;
	@Resource(name = "baseService")
	private IBaseService<WxUser> wxUserService;
	@Resource(name = "baseService")
	private IBaseService<WxMessageContent> msgContentService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> carTypeService;
	@Resource(name = "baseService")
	private IBaseService<WxUserStatus> wxUserStatusService;
	@Resource(name = "baseService")
	private IBaseService<WxDynamicContentInstance> wxDynamicContentInstanceService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentActive> wxActiveService;
	@Resource(name = "baseService")
	private IBaseService<WxUserLocation> wxUserLocationService;

	private WeiXinBean weixinBean;
	private WxGongzhonghao gzh;
	/**
	 * 注入线程池
	 */
	private TaskExecutor taskExecutor;
	
	private String url = "http://" + PropertiesUtil.getProperty(ParamString.BASE_IP)+ "/gtmc_wx/"
            +ModuleAction.ADMIN_HTML+"liuzi-page.jsp";
	private String suggest_url = "http://" + PropertiesUtil.getProperty(ParamString.BASE_IP)+ "/gtmc_wx/"
            +ModuleAction.ADMIN_HTML+"suggest-page.jsp";
	/**
	 * 获取公众号对应的用户以提取相应信息
	 * 
	 */
	public WxGongzhonghao obtainGzh() {
		return gzhService.get(WxGongzhonghao.class, "gzhHash",
				weixinBean.getHash());
	}

	/**
	 * 授权操作
	 * 
	 * @return
	 */
	public String forward() {
		log.info("----授权：开始获取code----");
		WxGongzhonghao gzh = obtainGzh();
		log.info("----gzh:" + gzh.toString());
		String url = getUrl(gzh.getAppid(), gzh.getGzhHash());
		log.info("----url:" + url);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取openid
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auth() throws Exception {
		log.info("----授权：获取code成功----");
		if (DataUtils.isNullOrEmpty(weixinBean.getHash())) {
			weixinBean.setHash(getState());
		}
		WxGongzhonghao gzh = obtainGzh();
		log.info("----gzh:" + gzh.toString());
		String openid = getOpenid(gzh.getAppid(), gzh.getAppsecret());
		log.info("----openid:" + openid);
		if (null != openid) {
			map.put("wxopenid", openid);
			u = getU().contains("?") ? getU() + "&wxopenid=" + openid : getU()
					+ "?wxopenid=" + openid;
			log.info("----authUrl:" + u);
		}
		return SUCCESS;
	}

	/**
	 * 将openid保存到session中
	 * 
	 * @return
	 */
	public String saveOpenid() {
		WxGongzhonghao gzh = obtainGzh();
		String openid = getOpenid(gzh.getAppid(), gzh.getAppsecret());
		if (null != openid) {
			map.put("wxopenid", openid);
		}
		setMap(true, "获取成功", openid);
		return SUCCESS;
	}
	
	/**
	 * 设置与cr开始会话的时间，表示开始与cr系统交互，忽略本系统的关键字匹配
	 * */
    public WxSendMsg setCRTime(WxSendMsg sendMsg,WxRecvMsg msg){
			String keyword = ((WxRecvTextMsg) msg).getContent();
			String openid = ((WxRecvTextMsg) msg).getFromUser();
			log.info("openid:" + openid);
			log.info("keyword:" + keyword);
			List<WxUserStatus> wxUserStatuses = wxUserStatusService.getAll(
					WxUserStatus.class, "openId", openid);
			log.info("wxUserStatuses size:" + wxUserStatuses.size());
			if (wxUserStatuses.size() > 0) {
				WxUserStatus userStatus = wxUserStatuses.get(0);
				//首先判断留资
				String CRUrl= PropertiesUtil.getProperty("send_cr_url");
				
				Map <String,String> params=new HashMap<String,String>(); 
				params.clear();
	            params.put("service","findUser");
	            params.put("userid", openid);
	            params.put("hashcode", weixinBean.getHash());
	            String findUser=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
				log.info("findUser:"+findUser);
	            JSONObject jo=JSONObject.fromObject(findUser);
	            String ifFlag=jo.getString("ifFlag");
	            
	            
	            if (userStatus.getStatus() == WxUserStatus.crSession){
	            	System.out.print("---------------------直接发送信息给CR!");
	            	print("");
	            	//String token = getAccessToken(gzh.getAppid(),gzh.getAppsecret());//查询数据库中的accesstoken是否过期
//	            	String token = HttpUtils.getToken(gzh.getAppid(),gzh.getAppsecret());
//	            	System.out.print("---------------最新的token"+token);
					params.clear();
					params.put("service", "send");
					params.put("userid",openid);
					params.put("hashcode",weixinBean.getHash());
					params.put("usertext",keyword);
					params.put("sessionid",openid);
					params.put("username","");
					log.info("`````````````````````service:"+"send");
					log.info("`````````````````````userid:"+openid);
					log.info("`````````````````````hashcode:"+weixinBean.getHash());
					log.info("`````````````````````usertext:"+keyword);
					log.info("`````````````````````sessionid:"+openid);

					log.info("sessionid:"+openid);
					String send=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
					log.info("send:"+send);
					
					JSONObject jo1=JSONObject.fromObject(send);
					String ifFlag1=jo1.getString("ifFlag");//lynn
					
					if (ifFlag1.equals("1")) {
						System.out.print("已发送 !");
					}
	            }else{
		            if("1".equals(ifFlag)){
		            	System.out.print("已留资!");
		            	System.out.println("处理成功");
		            	print("");
		            	//改状态为session状态
						userStatus.setModifyOn(DateUtils.currentDatetime()); 
						userStatus.setStatus(WxUserStatus.crSession);//变为对话状态并存入数据库		
						wxUserStatusService.update(userStatus);
		            	System.out.print("查询如果已经留资----------------发信息给CR");
		            	//String token = getAccessToken(gzh.getAppid(),gzh.getAppsecret());//查询数据库中的accesstoken是否过期
//		            	String token = HttpUtils.getToken(gzh.getAppid(),gzh.getAppsecret());
//		            	System.out.print("---------------最新的token"+token);
						params.clear();
						params.put("service", "send");
						params.put("userid",openid);
						params.put("hashcode",weixinBean.getHash());
						params.put("usertext",keyword);
						params.put("sessionid",openid);
						params.put("username","");
						log.info("hashcode:"+weixinBean.getHash());
						log.info("sessionid:"+openid);
						String send=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
						log.info("send:"+send);
						
						JSONObject jo1=JSONObject.fromObject(send);
						String ifFlag1=jo1.getString("ifFlag");//lynn
						
						if (ifFlag1.equals("1")) {
							System.out.print("已发送 !");
						}
		            }else{
		            	sendMsg=setInfo(sendMsg);
		            	System.out.print("未留资!");
		            }
	            }
				return sendMsg;
			}else{
				return null;
			}
    }

	/**
	 * 入口
	 * 
	 * @return
	 */
	public String api() {
		log.info("--- api request ---");
		gzh = obtainGzh();
		// 设置token
		weixinBean.setToken(gzh.getToken());
		// 验证签名
		if (WeiXin.access(weixinBean)) {
			/************* 微信接入 *************/
			if (DataUtils.isNotNullOrEmpty(weixinBean.getEchostr())) {
				log.info("微信接入。。。");
				print(weixinBean.getEchostr());
				return this.SUCCESS;
			}
			acceptMsg(gzh);

		} else {
			print("");
		}
		return this.SUCCESS;
	}

	/**
	 * 接收消息处理,并进行回复处理
	 * 
	 */
	private void acceptMsg(WxGongzhonghao gzh) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		WxRecvMsg msg;
		try {
			msg = WeiXin.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXin.builderSendByRecv(msg);
			// 保存openid到session
			map.put("wxopenid", sendMsg.getToUser());
			log.info(sendMsg.getToUser());
			log.info(map.get("wxopenid"));
			// 目前支持的消息有(
			// WxRecvTextMsg/文本消息,
			// WxRecvEventMsg/事件消息,
			// WxRecvGeoMsg/地理位置消息,
			// WxRecvLinkMsg/连接消息,
			// WxRecvPicMsg/图片消息)
			if (msg instanceof WxRecvEventMsg) {
				WxRecvEventMsg m = (WxRecvEventMsg) msg;
				switch (m.getEvent().toLowerCase()) {
				// 关注
				case TypeBean.SUBSCRIBE:
					// 根据欢迎消息规则进行回复
					if (DataUtils.isNotNullOrEmpty(gzh.getRuleGuid())) {
						//sendMsg = obtainContent(sendMsg, gzh.getRuleGuid());
						sendMsg = obtainContent(sendMsg, gzh.getRuleGuid(), msg);
					} else {
						// 不做响应
					}
					// 保存关注用户
					taskExecutor.execute(new DataTask(sendMsg.getToUser(),
							WxUser.GZ, gzh, m.getTicket(), wxUserService));
					break;
				// 取消关注
				case TypeBean.UNSUBSCRIBE:
					// 更新用户信息
					taskExecutor.execute(new DataTask(sendMsg.getToUser(),
							WxUser.GZ_CANCEL, gzh, null, wxUserService));
					break;
				// 自定义菜单点击事情
				case TypeBean.CLICK:
//					sendMsg = obtainContent(sendMsg, m.getEventKey());
					sendMsg = obtainContent(sendMsg, m.getEventKey(), msg);
					break;
				// 扫描带参数二维码事件
				case TypeBean.SCAN:
					break;
				// 上报地理位置
				case TypeBean.LOCATION:
					map.put("latitude", m.getLatitude());
					map.put("longitude", m.getLongitude());
					log.info("geo : " + m.toString());
					try {
						List<WxUserLocation> wxUserLocations = wxUserLocationService
								.getAll(WxUserLocation.class, "openId",
										m.getFromUser());
						WxUserLocation wxUserLocation = new WxUserLocation();
						if (wxUserLocations.size() > 0) {
							wxUserLocation = wxUserLocations.get(0);
							wxUserLocation.setLatitude(m.getLatitude());
							wxUserLocation.setLongitude(m.getLongitude());
							wxUserLocation.setUpdateTime(DateUtils
									.currentDatetime());

							log.info("update openId:"
									+ wxUserLocation.getOpenId());
							wxUserLocationService.update(wxUserLocation);
						} else {
							wxUserLocation.setOpenId(m.getFromUser());
							wxUserLocation.setLatitude(m.getLatitude());
							wxUserLocation.setLongitude(m.getLongitude());
							wxUserLocation.setUpdateTime(DateUtils
									.currentDatetime());

							log.info("save openId:"
									+ wxUserLocation.getOpenId());
							wxUserLocationService.save(wxUserLocation);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			} else if (msg instanceof WxRecvTextMsg) {
				// 处理文本消息回复
				sendMsg = dealTextMsg(sendMsg, msg);
			} else if (msg instanceof WxRecvGeoMsg) {
				// 不作处理,使用默认回复 TODO
				WxRecvGeoMsg geoMsg = (WxRecvGeoMsg) msg;

				List<WxUserLocation> wxUserLocations = wxUserLocationService
						.getAll(WxUserLocation.class, "openId",
								geoMsg.getFromUser());
				WxUserLocation wxUserLocation = new WxUserLocation();
				if (wxUserLocations.size() > 0) {
					wxUserLocation = wxUserLocations.get(0);
					wxUserLocation.setLatitude(geoMsg.getLatitude() + "");
					wxUserLocation.setLongitude(geoMsg.getLongitude() + "");
					wxUserLocation.setUpdateTime(DateUtils.currentDatetime());

					wxUserLocationService.update(wxUserLocation);
				} else {
					wxUserLocation.setOpenId(geoMsg.getFromUser());
					wxUserLocation.setLatitude(geoMsg.getLatitude() + "");
					wxUserLocation.setLongitude(geoMsg.getLongitude() + "");
					wxUserLocation.setUpdateTime(DateUtils.currentDatetime());

					wxUserLocationService.save(wxUserLocation);
				}

				map.put("openId", geoMsg.getFromUser());
				map.put("latitude", geoMsg.getLatitude());
				map.put("longitude", geoMsg.getLongitude());
				log.info("geo:" + geoMsg.getLatitude() + "---"
						+ geoMsg.getLongitude());

			} else if (msg instanceof WxRecvLinkMsg) {
				// 不作处理,使用默认回复
				sendMsg = sendDefualtMsg(sendMsg, msg);
			} else if (msg instanceof WxRecvPicMsg) {
				// 不作处理,使用默认回复
				sendMsg = sendDefualtMsg(sendMsg, msg);
			}

			// 发送回微信
			log.info(sendMsg.toDocument().toString());
			WeiXin.send(sendMsg, response.getOutputStream());
		} catch (JDOMException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 处理文本消息回复
	 * 
	 * @Title: dealTextMsg
	 * @Description: TODO
	 * @param @param sendMsg
	 * @param @param msg
	 * @return void
	 * @throws
	 * @Date 2014年3月6日 下午5:54:06
	 */
	private WxSendMsg dealTextMsg(WxSendMsg sendMsg, WxRecvMsg msg) {
		String keyword = ((WxRecvTextMsg) msg).getContent();
		String openid = ((WxRecvTextMsg) msg).getFromUser();
		//print("");
		List<WxUserStatus> wxUserStatuses = wxUserStatusService.getAll(
				WxUserStatus.class, "openId", openid);
		log.info("openid:" + openid);
		log.info("keyword:" + keyword);
		log.info("wxUserStatuses size:" + wxUserStatuses.size());
		//String CRKeyWord= PropertiesUtil.getProperty("CRKeyWord");//进入人工关键字配置在配置文件中
		//log.info("进入人工客服的关键字CRKeyWord:" + CRKeyWord);
		boolean defaultKeyRule = false;
		if (wxUserStatuses.size() > 0) {
			WxUserStatus wxUserStatus = wxUserStatuses.get(0);
			if (wxUserStatus.getStatus() == WxUserStatus.crReplied) {
				
				
				//if(keyword.trim().equals("#") || keyword.trim().equals("800")){//用户如果回复“#”或者“800”，则进入人工客服状态。
				if(keyword.trim().equals("在线客服")){
					log.info("&&&&&&&&&&&&&&&&&&&&答案状态进入工￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
					sendMsg = obtainCRContent(sendMsg, msg);
					return sendMsg;
				}
			}
			log.info("-----------------------10-------------------------------");
			if (wxUserStatus.getStatus() == WxUserStatus.unReply) {
				// 根据userStatus 获取回复内容
				if(keyword.trim().equals("0")){
					String message = "您好，感谢您关注广汽丰田官方微信！请根据您的需求，输入“车型+关键词”自动获取车辆信息。比如输入“凯美瑞（空格）发动机”，即可获得凯美瑞车型发动机的相关信息。关键词类别如下，请选择进行查询："+"\n"+"发动机；"+"\n"+"悬架；"+"\n"+"驱动；"+"\n"+"音响；"+"\n"+"导航；"+"\n"+"空调；"+"\n"+"安全系统；"+"\n"+"购车流程；"+"\n"+"其他。";	
					String linkstr = "\n如果您有任何宝贵建议请<a href='"+suggest_url+"?wxopenid=%s&hashcode=%s'>单击这里</a>进入，您的每个建议都是我们改进的动力！\n温馨提醒：您的宝贵建议我们可能不能都回复，敬请谅解！";	
					linkstr = String.format(linkstr,sendMsg.getToUser(),weixinBean.getHash());
					
					sendMsg = new WxSendTextMsg(sendMsg,message + linkstr);
					wxUserStatus.setStatus(WxUserStatus.replied);
					wxUserStatusService.update(wxUserStatus);
					return sendMsg;
				}
				
				// 更新为已回复
				log.info("-------------------------------------------是否进入这个状态");
				wxUserStatus.setStatus(WxUserStatus.replied);
				wxUserStatusService.update(wxUserStatus);
				// 根据userStatus 获取回复内容
				switch (wxUserStatus.getDynamicContent()) {
				case "carType":
					try{
						JSONObject json = JSONObject.fromObject(wxUserStatus
								.getJson());
						log.info(json.getString(keyword));
						if (null != json.getString(keyword)
								&& json.getString(keyword).length() > 0) {
							// TODO 回复
							String carTypeGuid = json.getString(keyword);
							sendMsg = getCarNews(sendMsg, carTypeGuid,
									wxUserStatus.getDynamicContentInstanceGuid());
						} else {
							defaultKeyRule = true;
						}
					}catch(Exception e){
						log.info("Can't find Content");
						defaultKeyRule = true;
					}
					break;
				}
			} else {
				//在线客服系统 
				if (wxUserStatus.getStatus() == WxUserStatus.crSession) {//在线客服
					log.info("Customer online Start----------------");
					log.info("直接发信息给CR************************************************************");
					return	setCRTime(sendMsg,msg);
				}
				if (wxUserStatus.getStatus() == WxUserStatus.crUnReply) {//CR需要再次回复
					wxUserStatus.setStatus(WxUserStatus.crReplied);//更新为CR已回复
					wxUserStatusService.update(wxUserStatus);
					try{
							JSONObject json = JSONObject.fromObject(wxUserStatus
									.getJson());
							log.info(json.getString(keyword));
							if (null != json.getString(keyword)
									&& json.getString(keyword).length() > 0) {
								// TODO 调用cr接口并得到返回值
								String carTypeGuid = json.getString(keyword);
								//add by yiwei Start
								String CRUrl= PropertiesUtil.getProperty("send_cr_url");
								String MAXCount=PropertiesUtil.getProperty("MAXCount");
								Map <String,String> params=new HashMap<String,String>(); 
								 params.put("service","findKeyWord");
								 params.put("userid",openid );
						    	 params.put("wd",carTypeGuid );//获取JSON的value
						    	 params.put("KeyType", "2");//第二次查询CR时，查询答案
						    	 params.put("MAXCount",MAXCount);
						    	 
								String str=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
								log.info("AnswerCRResult:"+str+";CRUrl:"+CRUrl);
								
								JSONObject jo=JSONObject.fromObject(str);
								
								String flag=(String)jo.get("ifFlag");
								log.info("ifFlag:"+flag);	
								
								if (jo.getString("ifFlag").equals("1")) {
									try{
										//String answer=jo.getString("result")+"\n"+"注:如有需要，您也可以输入“#”或数字“800”进入人工服务";//lynn
										String answer=jo.getString("result")+"\n"+"感谢您对广汽丰田车型的喜爱，若想了解更多信息，请您输入“在线客服”进入人工咨询，谢谢！温馨提示：由于咨询量大，若未能回复，敬请谅解。为了不耽误您的宝贵时间，建议您可以咨询经销店或者拨打广汽丰田顾客服务中心（全年无休）400/8008308888";//lynn
									log.info("answer:"+answer);
									sendMsg = new WxSendTextMsg(sendMsg, answer);
									}
									catch(Exception e){
										log.info(e);
									}
								}  
	
							} else {
								defaultKeyRule = true;
							}
						}catch(Exception e){
							log.info("Can't find Content");
							defaultKeyRule = true;
						}
					
				} else {
					defaultKeyRule = true;
				}
			}
		} else {
			defaultKeyRule = true;
		}
		log.info("defalutKeyRule：" + defaultKeyRule);
		if (defaultKeyRule) {
			log.info("-----------------------20-------------------------------");
			// 查询
			List<WxRuleKeywordView> keywordList = keywordViewService.getAll(
					WxRuleKeywordView.class,
					new String[] { "kwName", "gzhHash" }, new Object[] {
							keyword, weixinBean.getHash() }, new String[] {
							"like%%", "=" });
			log.info("-----------------------30-------------------------------");
			log.info("keywordList size:" + keywordList.size());
			if (keywordList.size() == 1) {// 只有一个匹配的关键字
				WxRuleKeywordView key = keywordList.get(0);
				log.info("keywordList.get(0)------------------:" + keywordList.get(0));
				if (WxRuleKeywordView.EQUALS == key.getType()) {// 完全匹配
					if (key.getKwName().equals(keyword)) { // 符合规则
						// 构建消息进行发送
						log.info("-----------------------35-------------------------------");
//						sendMsg = obtainContent(sendMsg, key.getRuleGuid());
						sendMsg = obtainContent(sendMsg, key.getRuleGuid(), msg);
					} else {
						// 不符合规则,使用默认回复
						//sendMsg = sendDefualtMsg(sendMsg);
						//在线客服系统 add by lyn 2014/5/7 start
						sendMsg = obtainCRContent(sendMsg, msg);
							//sendMsg = sendDefualtMsg(sendMsg);
						//在线客服系统 add by lyn 2014/5/7 end
					}
				} else if (WxRuleKeywordView.CONTAINS == key.getType()) { // 包含
					// 构建消息进行发送
//					sendMsg = obtainContent(sendMsg, key.getRuleGuid());
					sendMsg = obtainContent(sendMsg, key.getRuleGuid(), msg);
				} else {
					// 不符合规则,使用默认回复
					//sendMsg = sendDefualtMsg(sendMsg);
					//在线客服系统 add by lyn 2014/5/7 start
					sendMsg = obtainCRContent(sendMsg, msg);
						//sendMsg = sendDefualtMsg(sendMsg);
					//在线客服系统 add by lyn 2014/5/7 end
				}
			} else if (keywordList.size() > 1) {// 多个匹配的关键字
				WxRuleKeywordView key = null;
				// 检查是否有完全匹配的
				for (WxRuleKeywordView keywordView : keywordList) {
					if (WxRuleKeywordView.EQUALS == keywordView.getType()) {// 完全匹配
						if (keywordView.getKwName().equals(keyword)) { // 符合规则
							key = keywordView;
							break;
						}
					}
				}
				// 如果没有完全匹配的则不进行回复
				if (null != key) {
					// 构建消息进行发送
//					sendMsg = obtainContent(sendMsg, key.getRuleGuid());
					sendMsg = obtainContent(sendMsg, key.getRuleGuid(), msg);
				} else {
					// 不符合规则,使用默认回复
					//sendMsg = sendDefualtMsg(sendMsg);
					//在线客服系统 add by lyn 2014/5/7 start
					sendMsg = obtainCRContent(sendMsg, msg);
						//sendMsg = sendDefualtMsg(sendMsg);
					//在线客服系统 add by lyn 2014/5/7 end
				}
			} else {
				// 不符合规则,使用默认回复
				//sendMsg = sendDefualtMsg(sendMsg);
				//在线客服系统 add by lyn 2014/5/7 start
				sendMsg = obtainCRContent(sendMsg, msg);
					//sendMsg = sendDefualtMsg(sendMsg);
				//在线客服系统 add by lyn 2014/5/7 end
			}
		}
		return sendMsg;
	}

	/**
	 * 使用默认回复
	 * 
	 * @return
	 */
	private WxSendMsg sendDefualtMsg(WxSendMsg sendMsg, WxRecvMsg msg) {
		log.info("isDefault gzhHash=" + weixinBean.getHash());
		List<WxRule> ruleList = ruleService.getAll(WxRule.class, new String[] {
				"gzhHash", "isDefault" }, new Object[] { weixinBean.getHash(),
				WxRule.DEFAULT });
		log.info("default rule sizes:" + ruleList.size());
		if (ruleList.size() > 0) {
//			sendMsg = obtainContent(sendMsg, ruleList.get(0).getRuleGuid());
			sendMsg = obtainContent(sendMsg, ruleList.get(0).getRuleGuid(), msg);
		}
		return sendMsg;
	}

	/**
	 * 
	 * @param sendMsg
	 * @param carTypeGuid
	 * @return
	 */
	private WxSendMsg getCarNews(WxSendMsg sendMsg, String carTypeGuid,
			String dynamicContentInstanceGuid) {
		List<WxModuleContentCarType> carTypes = carTypeService.getAll(
				WxModuleContentCarType.class, "carTypeGuid", carTypeGuid);
		List<WxDynamicContentInstance> wdcis = wxDynamicContentInstanceService
				.getAll(WxDynamicContentInstance.class,
						"wxDynamicContentInstanceGuid",
						dynamicContentInstanceGuid);
		// List<WxModuleContentActive> carActivitys = wxActiveService.getAll(
		// WxModuleContentActive.class, new String[] { "carTypeGuid" },
		// new Object[] { carTypeGuid });
		List<WxModuleContentActive> carActivitys = wxActiveService.getAll(
				WxModuleContentActive.class, new String[] { "carTypeGuid",
						"status", "delFlag" },
				new Object[] { carTypeGuid, 1, 0 });
		String basepath = getBasePath();
		if (carTypes.size() > 0 && wdcis.size() > 0) {
			WxDynamicContentInstance wdci = wdcis.get(0);
			String urlParam = wdci.getUrlParamField();
			String carJson = wdci.getReplyJson();

			WxModuleContentCarType carType = carTypes.get(0);

			WxSendNewsMsg sendNewsMsg = new WxSendNewsMsg(sendMsg);
			JSONArray ja = JSONArray.fromObject(carJson);
			List<String> urls = new ArrayList<String>();
			for (Object object : ja) {
				JSONObject jo = (JSONObject) object;

				String toUrl = jo.getString("toUrl");
				if (toUrl != null && toUrl.length() > 0) {
					if (!toUrl.startsWith("http") && !toUrl.startsWith("tel:")) {
						toUrl = basepath + toUrl.substring(1);
					}
					if (toUrl.contains("?")) {
						toUrl += "&wxopenid=" + sendMsg.getToUser();
					} else {
						toUrl += "?wxopenid=" + sendMsg.getToUser();
					}
					if (toUrl.contains("?")) {
						toUrl += "&" + urlParam + "=" + carTypeGuid;
					} else {
						toUrl += "?" + urlParam + "=" + carTypeGuid;
					}
					log.info("to url: " + toUrl);
				}
				urls.add(toUrl);
			}
			log.info("车型图片地址：" + basepath + carType.getCarTypeImageUrl());
			sendNewsMsg.addItem(carType.getCarTypeTitle() + "车型鉴赏",
					"",//carType.getCarTypeMerit(),
					basepath + carType.getCarTypeImageUrl(), urls.get(0));
			log.info("车型配置图片地址：" + basepath + carType.getCarTypeParamUrl());
			sendNewsMsg.addItem(carType.getCarTypeTitle() + "价格配置",
					"",//carType.getCarTypeMerit(),
					basepath + carType.getCarTypeParamUrl(), urls.get(1));
		  	//add by yiwei 雷凌上市 Start
			//
						if(carType.getCarTypeParamUrl2()!=null&&!carType.getCarTypeParamUrl2().equals("")){
						log.info("车型立即预定点击地址：" + basepath+carType.getReserveUrl()+"?wxopenid="+ sendMsg.getToUser());

						sendNewsMsg.addItem(carType.getCarTypeTitle() + "立即预定",
								"",
								basepath + carType.getCarTypeParamUrl2(), basepath+carType.getReserveUrl()+"?wxopenid="+ sendMsg.getToUser());
						}
						if(carType.getCarTypeParamUrl3()!=null&&!carType.getCarTypeParamUrl3().equals("")){
							log.info("其他信息图片地址：" + basepath + carType.getCarTypeParamUrl());
							log.info("其他信息点击地址：" + basepath+carType.getOtherinfoUrl()+"?wxopenid="+ sendMsg.getToUser());
							sendNewsMsg.addItem(carType.getCarTypeTitle() + "震撼发声",
									"",
									basepath + carType.getCarTypeParamUrl3(), basepath+carType.getOtherinfoUrl()+"?wxopenid="+ sendMsg.getToUser());
							}
			//add by yiwei 雷凌上市 End
			log.info("车型guid： " + carTypeGuid);
			log.info("车型活动数量：" + carActivitys.size());
			if (carActivitys.size() > 0) {
				log.info("车型活动地址：" + carActivitys.get(0).getActImage());
				sendNewsMsg.addItem(carType.getCarTypeTitle() + "车型活动",
						"",//carType.getCarTypeMerit(),
						basepath + carActivitys.get(0).getActImage(),
						urls.get(2) + "&actGuid="
								+ carActivitys.get(0).getActGuid());
			}
			sendMsg = sendNewsMsg;
		}

		return sendMsg;
	};

	/**
	 * 根据关键字处理回复消息
	 * 
	 * @param sendMsg
	 * @param key
	 * @return
	 */
	private WxSendMsg obtainContent(WxSendMsg sendMsg, String ruleGuid, WxRecvMsg msg) {
		List<WxRuleContentView> contentList = ruleContentViewService.getAll(
				WxRuleContentView.class, "ruleGuid", ruleGuid);
		// 如果没有记录则，返回null
		if (null == contentList || contentList.size() == 0) {
			return sendMsg;
		}
		log.info("|||||||||||||||||||||||||ruleGuid"+ruleGuid);
		WxRuleContentView content = null;
		// 如果同一个规则对应多条记录，则是多图文，需要确定父秦
		// if (contentList.size() > 1) {
		// // 查找父亲节点
		// for (WxRuleContentView view : contentList) {
		// if (DataUtils.isNullOrEmpty(view.getParentGuid())) {
		// content = view;
		// break;
		// }
		// }
		// } else {
		content = contentList.get(0);
		// }
		log.info("$$$$$$$$$$$$$$$$$$$$$content.toString()"+content.toString());
		//处理其他格式在对话状态的case lyn start 2014/5/14
		if (msg instanceof WxRecvPicMsg){
			String message = "您好，对话状态只支持输入文字！谢谢";	
			sendMsg = new WxSendTextMsg(sendMsg,message);
			return sendMsg;
		}
		//处理其他格式在对话状态的case lyn end 2014/5/14
		String path = getBasePath();
		log.info("$$$$$$$$$$$$$$$$$$$$$path"+path);
		log.info("$$$$$$$$$$$$$$$$$$$$$content.getType()"+content.getType());
		switch (content.getType()) {
		case WxRuleContentView.SINGLE_NEWS:// 单图文
			// 构建文本消息进行发送
			String pic = content.getNewsPic();
			// 判断是否为空
			if (DataUtils.isNotNullOrEmpty(pic)) {
				pic = path + pic.substring(1);
			}
			String url = content.getNewsUrl();
			if (content.getUrlParamName() != null
					&& content.getUrlParamName().length() == 0) {
				url += "?" + content.getUrlParamName() + "="
						+ content.getUrlParamContent();
			}
			// 判断是否为空
			if (DataUtils.isNotNullOrEmpty(url)) {
				// 外部连接以http开头
				if (!url.startsWith("http") && !url.startsWith("tel:")) {
					url = path + url.substring(1);
				}
			}

			// 附带openid
			if (url.contains("?")) {
				url += "&wxopenid=" + sendMsg.getToUser();
			} else {
				url += "?wxopenid=" + sendMsg.getToUser();
			}

			sendMsg = new WxSendNewsMsg(sendMsg).addItem(
					content.getNewsTitle(), content.getNewsDescription(), pic,
					url);
			break;
		case WxRuleContentView.NEWS:// 多图文
			pic = content.getNewsPic();
			// 判断是否为空
			if (DataUtils.isNotNullOrEmpty(pic)) {
				pic = path + pic.substring(1);
			}
			url = content.getNewsUrl();
			if (content.getUrlParamName() != null
					&& content.getUrlParamName().length() == 0) {
				url += "?" + content.getUrlParamName() + "="
						+ content.getUrlParamContent();
			}
			// 判断是否为空
			if (DataUtils.isNotNullOrEmpty(url)) {
				// 外部连接以http开头
				if (!url.startsWith("http")) {
					url = path + url.substring(1);
				}
			}

			// 附带openid
			if (url.contains("?")) {
				url += "&wxopenid=" + sendMsg.getToUser();
			} else {
				url += "?wxopenid=" + sendMsg.getToUser();
			}

			WxSendNewsMsg sendNewsMsg = new WxSendNewsMsg(sendMsg);
			sendNewsMsg.addItem(content.getNewsTitle(),
					content.getNewsDescription(), pic, url);

			List<WxMessageContent> cntList = this.msgContentService
					.getAll(WxMessageContent.class, "parentGuid",
							content.getNewsGuid());

			for (WxMessageContent view : cntList) {
				if (content.getNewsGuid().equals(view.getParentGuid())) {
					String childpic = view.getNewsPic();
					// 判断是否为空
					if (DataUtils.isNotNullOrEmpty(childpic)) {
						childpic = path + childpic.substring(1);
					}
					String childurl = view.getNewsUrl();
					if (view.getUrlParamName() != null
							&& view.getUrlParamName().length() == 0) {
						childurl += "?" + view.getUrlParamName() + "="
								+ view.getUrlParamContent();
					}
					// 判断是否为空
					if (DataUtils.isNotNullOrEmpty(childurl)) {
						// 外部连接以http开头
						if (!childurl.startsWith("http")) {
							childurl = path + childurl.substring(1);
						}
					}

					// 附带openid
					if (childurl.contains("?")) {
						childurl += "&wxopenid=" + sendMsg.getToUser();
					} else {
						childurl += "?wxopenid=" + sendMsg.getToUser();
					}

					sendNewsMsg.addItem(view.getNewsTitle(),
							view.getNewsDescription(), childpic, childurl);
				}
			}
			sendMsg = sendNewsMsg;
			break;
		case WxRuleContentView.TEXT:// 文本
			// 构建文本消息进行发送
			String json = "";
			switch (content.getDynamicContent()) {
			case "carType":
				List<WxDynamicContentInstance> wdcis = wxDynamicContentInstanceService
						.getAll(WxDynamicContentInstance.class,
								"wxDynamicContentInstanceGuid",
								content.getDynamicContentInstanceGuid());
				if (wdcis.size() > 0) {
					WxDynamicContentInstance wdci = wdcis.get(0);
					String showField = wdci.getShowField();
					String paramField = wdci.getParamField();

					List<WxModuleContentCarType> carTypes = carTypeService
							.getAll(WxModuleContentCarType.class,
									new String[] {}, new Object[] {},
									new String[] {}, "carTypeSortid asc");
					// TODO 缺排序，及不同公众号下不同车型的设计
					String carJson = "{";
					//json+="根据您的回复得到以下问题列表，请输入相应的数字查询：";
					for (int i = 0; i < carTypes.size(); i++) {
						json += "\n "
								+ (i + 1)
								+ ". "
								+ ReflectUtils.getProperty(showField,
										carTypes.get(i));
						carJson += "\""
								+ (i + 1)
								+ "\":\""
								+ ReflectUtils.getProperty(paramField,
										carTypes.get(i)) + "\",";
					}
					//json+="\n"+"提示:输入#或者800进入人工客服";//
					json+="\n"+" 0.咨询服务";//
					
					carJson = carJson.substring(0, carJson.length() - 1);
					carJson += "}";

					// 设置微信用户状态，当前处于 查看车型状态
					List<WxUserStatus> wxUserStatuses = wxUserStatusService
							.getAll(WxUserStatus.class, "openId",
									sendMsg.getToUser());
					WxUserStatus userStatus = new WxUserStatus();
					if (wxUserStatuses.size() > 0) {
						userStatus = wxUserStatuses.get(0);
					} else {
						userStatus.setUserStatusGuid(DataUtils.getUUID());
					}
					userStatus.setJson(carJson);
					userStatus.setModifyOn(DateUtils.currentDatetime());
					userStatus.setOpenId(sendMsg.getToUser());
					userStatus.setStatus(WxUserStatus.unReply);
					userStatus.setDynamicContentInstanceGuid(content
							.getDynamicContentInstanceGuid());
					userStatus.setDynamicContent(content.getDynamicContent());

					if (wxUserStatuses.size() > 0) {
						wxUserStatusService.update(userStatus);
					} else {
						wxUserStatusService.save(userStatus);
					}
					break;
				} else {
					break;
				}
			}

			String cntStr = content.getNewsContent();
			// 构建文本消息进行发送 将内容中url带上openid
			if (cntStr.indexOf("<a href=\"") > -1 && cntStr.indexOf("\">") > -1) {
				String param = "";
				String cntHref = cntStr
						.substring(cntStr.indexOf("<a href=\"") + 9,
								cntStr.indexOf("\">"));

				if (cntHref.contains("?")) {
					param += "&wxopenid=" + sendMsg.getToUser();
				} else {
					param += "?wxopenid=" + sendMsg.getToUser();
				}
				cntStr = cntStr.replace("\">", param + "\">");
			}
			log.info("-----------------------￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥-------------------cntStr-"+cntStr);
			//add by lyn cr菜单直接弹出在线客服 start
			if(cntStr.equals("CR在线客服")){
				log.info("----进入cr处理-------------*************************--------------");
				String openid = sendMsg.getToUser();
				log.info("openid:" + openid);
				List<WxUserStatus> wxUserStatuses = wxUserStatusService.getAll(
						WxUserStatus.class, "openId", openid);
				log.info("wxUserStatuses size:" + wxUserStatuses.size());
				if (wxUserStatuses.size() > 0) {
					WxUserStatus userStatus = wxUserStatuses.get(0);
					//首先判断留资
					String CRUrl= PropertiesUtil.getProperty("send_cr_url");
					
					Map <String,String> params=new HashMap<String,String>(); 
					params.clear();
		            params.put("service","findUser");
		            params.put("userid", openid);
		            params.put("hashcode", weixinBean.getHash());
		            String findUser=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
					log.info("findUser:"+findUser);
		            JSONObject jo=JSONObject.fromObject(findUser);
		            String ifFlag=jo.getString("ifFlag");
		            

		            if("1".equals(ifFlag)){
		            	System.out.print("已留资!");
		            	System.out.println("处理成功");
		            	print("");
		            	//改状态为session状态
						userStatus.setModifyOn(DateUtils.currentDatetime()); 
						userStatus.setStatus(WxUserStatus.crSession);//变为对话状态并存入数据库		
						wxUserStatusService.update(userStatus);
		            	System.out.print("查询如果已经留资----------------发信息给CR");
		            	//String token = getAccessToken(gzh.getAppid(),gzh.getAppsecret());//查询数据库中的accesstoken是否过期
//			            	String token = HttpUtils.getToken(gzh.getAppid(),gzh.getAppsecret());
//			            	System.out.print("---------------最新的token"+token);
						params.clear();
						params.put("service", "send");
						params.put("userid",openid);
						params.put("hashcode",weixinBean.getHash());
						params.put("usertext","菜单直接进入人工客服");
						params.put("sessionid",openid);
						params.put("username","");
						log.info("hashcode:"+weixinBean.getHash());
						log.info("sessionid:"+openid);
						String send=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
						log.info("send:"+send);
						
						JSONObject jo1=JSONObject.fromObject(send);
						String ifFlag1=jo1.getString("ifFlag");//lynn
						
						if (ifFlag1.equals("1")) {
							System.out.print("已发送 !");
						}
		            }else{
		            	sendMsg=setInfo(sendMsg);
		            	System.out.print("未留资!");
		            }
		            
					return sendMsg;
				}else{
					sendMsg = new WxSendTextMsg(sendMsg, cntStr + json);
				}
			}else{
				sendMsg = new WxSendTextMsg(sendMsg, cntStr + json);
			}
			//sendMsg = new WxSendTextMsg(sendMsg, cntStr + json);
			//add by lyn cr菜单直接弹出在线客服 end
			break;
		default:
		}

		return sendMsg;
	}

	/**
	 * 向请求端发送返回数据
	 * 
	 * @param response
	 * @param content
	 */
	private void print(String content) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().print(content);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public WeiXinBean getModel() {
		return weixinBean;
	}

	@Override
	public void prepare() throws Exception {
		if (null == weixinBean) {
			weixinBean = new WeiXinBean();
		}
	}

	public WeiXinBean getWeixinBean() {
		return weixinBean;
	}

	public void setWeixinBean(WeiXinBean weixinBean) {
		this.weixinBean = weixinBean;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	/**
	 * 处理线程
	 * 
	 * @PackageName:com.tlan.web
	 * @ClassName:DataTask
	 * @Description:TODO
	 * @author magenming@tlan.com.cn
	 * @date 2014年3月10日 下午4:51:00
	 * 
	 */
	private class DataTask implements Runnable {
		private IBaseService<WxUser> wxUserService;
		private String openid;
		private int type;
		private WxGongzhonghao gzh;
		private String ticket;

		public DataTask(String openid, int type, WxGongzhonghao gzh,
				String ticket, IBaseService<WxUser> wxUserService) {
			super();
			this.openid = openid;
			this.type = type;
			this.gzh = gzh;
			this.ticket = ticket;
			this.wxUserService = wxUserService;
		}

		public void run() {
			String token = HttpUtils.getToken(gzh.getAppid(),
					gzh.getAppsecret());
			String url = PropertiesUtil.getProperty(ParamString.WX_USER_INFO);
			url = url.replace("{1}", token);
			url = url.replace("{2}", openid);
			String res = HttpClientUtil.sendGetRequest(url,
					PropertiesUtil.getProperty(ParamString.ENCODING));
			if (res.contains("errcode")) {
				log.error("获取用户信息异常：" + res);
			} else {
				UserBean bean = (UserBean) JSONObject.toBean(
						JSONObject.fromObject(res), UserBean.class);
				saveWxUser(openid, type, gzh, bean, ticket);
			}
		}

		/**
		 * 保存用户信息： 关注 、取消关注
		 */
		private void saveWxUser(String openid, int type, WxGongzhonghao gzh,
				UserBean bean, String ticket) {
			List<WxUser> userList = wxUserService.getAll(WxUser.class,
					new String[] { "openId", "gzhHash" }, new Object[] {
							openid, gzh.getGzhHash() });
			if (userList.size() > 0) {
				WxUser wxUser = userList.get(0);
				if (null != bean) {
					// wxUser.setFakeid(bean.getFakeid());
					wxUser.setCreatetime(bean.getSubscribe_time());
					wxUser.setNickName(bean.getNickname());
					if (bean.getSex() == 1) {
						wxUser.setUserSex("男");
					} else if (bean.getSex() == 2) {
						wxUser.setUserSex("女");
					}
					wxUser.setHeadimgurl(bean.getHeadimgurl());
					wxUser.setCity(bean.getCity());
					wxUser.setProvince(bean.getProvince());
					wxUser.setCountry(bean.getCountry());
				}
				wxUser.setModifyOn(DateUtils.currentDatetime());
				wxUser.setStatus(type);
				// 再次关注 如果有关注来源则记录 ticket
				if (type == WxUser.GZ && ticket != null && ticket.length() > 0) {
					wxUser.setCreatedOnTicket(ticket); // 添加ticket
				}
				wxUserService.update(wxUser);
			} else {
				WxUser wxUser = new WxUser();
				if (null != bean) {
					// wxUser.setFakeid(bean.getFakeid());
					wxUser.setCreatetime(bean.getSubscribe_time());
					wxUser.setNickName(bean.getNickname());
					if (bean.getSex() == 1) {
						wxUser.setUserSex("男");
					} else if (bean.getSex() == 2) {
						wxUser.setUserSex("女");
					}
					wxUser.setHeadimgurl(bean.getHeadimgurl());
					wxUser.setCity(bean.getCity());
					wxUser.setProvince(bean.getProvince());
					wxUser.setCountry(bean.getCountry());
				}
				wxUser.setCreatedOn(DateUtils.currentDatetime());
				wxUser.setGzhHash(gzh.getGzhHash());
				wxUser.setOpenId(openid);
				wxUser.setStatus(type);
				wxUser.setUserGuid(DataUtils.getUUID());
				wxUser.setMemberLevel(0); // 注册
				if (ticket != null && ticket.length() > 0) {
					wxUser.setCreatedOnTicket(ticket); // 添加ticket
				}
				wxUserService.save(wxUser);
			}
		}
	}
	/**
	 * 系统关键字匹配不到的情况下，调用CR
	 * 
	 * @param sendMsg
	 * @param key
	 * @return
	 */
	private WxSendMsg obtainCRContent(WxSendMsg sendMsg,WxRecvMsg msg) {
		log.info("-----------------------40-------------------------------");
		String keyword = ((WxRecvTextMsg) msg).getContent();
		String openid = ((WxRecvTextMsg) msg).getFromUser();
		//print("");
		List<WxUserStatus> wxUserStatuses = wxUserStatusService.getAll(
				WxUserStatus.class, "openId", openid);
		log.info("openid:" + openid);
		log.info("keyword:" + keyword);
		log.info("wxUserStatuses size:" + wxUserStatuses.size());
//		String CRKeyWord= PropertiesUtil.getProperty("CRKeyWord");//进入人工关键字配置在配置文件中
//		log.info("进入人工客服的关键字CRKeyWord:" + CRKeyWord);
		if (wxUserStatuses.size() > 0) {
		WxUserStatus wxUserStatus = wxUserStatuses.get(0);
			//if (wxUserStatus.getStatus() == WxUserStatus.crUnReply) {
				//if(keyword.trim().equals("#") || keyword.trim().equals("800")){
			if(wxUserStatus.getStatus() == WxUserStatus.crReplied){
				if(keyword.trim().equals("在线客服")){
		    		log.info("--- {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}---");
		    			
		    			return	setCRTime(sendMsg,msg);
				} 
			}
			//}
		}
		log.info("-----------------------50-------------------------------");
			//处理调用CR第一次查询的操作
			//add by yiwei Start
			String CRUrl= PropertiesUtil.getProperty("send_cr_url");
			String MAXCount=PropertiesUtil.getProperty("MAXCount");
			log.info("CRUrl"+CRUrl);
			log.info("-----------------------51-------------------------------");
			Map <String,String> params=new HashMap<String,String>(); 
			 params.put("service","findKeyWord");
			 params.put("userid",openid );
	    	 params.put("wd",keyword );
	    	 params.put("KeyType", "1");//第一次查询CR时，查询题目
	    	 params.put("MAXCount",MAXCount);
	    	 log.info("-----------------------52-------------------------------");
			String str=HttpClientUtil.sendPostRequest(CRUrl, params, "UTF-8","UTF-8");
			log.info("QuestionCRResult:"+str);
			JSONObject jo=JSONObject.fromObject(str);
			JSONArray result=(JSONArray)jo.get("result");
			
			String flag=(String)jo.get("ifFlag");
			log.info("ifFlag:"+flag);
			log.info("-----------------------60-------------------------------");
			//add by yiwei End
			//此处写第一次调用cr接口的方法（关键字调用）,得到返回结果是否成功，另外需要返回是否是
			//结果还是问题列表。
			if ("1".equals(flag)) {//查询结果为成功
			String json = "";
			json+="感谢您对广汽丰田的喜爱，请输入以下对应的数字选择你所关注的信息：";
			String carJson = "{";
			log.info("result.size():"+result.size());
			
			for(int i=0;i<result.size();i++){//此处拼接返回来的结果，做成json
				JSONObject res=result.getJSONObject(i);
				String LogicSequence=res.get("LogicSequence")+"";

				String PhysicsSequence=res.get("PhysicsSequence")+"";

				String question=res.getString("question");
				
				json += "\n "
						+ LogicSequence
						+ ". "
						+ question;
				carJson += "\""
						+ LogicSequence
						+ "\":\""
						+ PhysicsSequence + "\",";
				
			
			}
			//json+="\n"+"注:如有需要，您也可以输入“800”进入人工服务";
			carJson = carJson.substring(0, carJson.length() - 1);
					carJson += "}";
					// 设置微信用户状态，当前处于 查看车型状态
		
					WxUserStatus userStatus = new WxUserStatus();
					if (wxUserStatuses.size() > 0) {
						userStatus = wxUserStatuses.get(0);
					} else {
						userStatus.setUserStatusGuid(DataUtils.getUUID());
					}
					userStatus.setJson(carJson);
					userStatus.setModifyOn(DateUtils.currentDatetime());
					userStatus.setOpenId(sendMsg.getToUser());
					userStatus.setStatus(WxUserStatus.crUnReply);
					userStatus.setDynamicContent("carType");//lynn


					if (wxUserStatuses.size() > 0) {
						wxUserStatusService.update(userStatus);
					} else {
						wxUserStatusService.save(userStatus);
					}
					log.info("content:" + json);

					sendMsg = new WxSendTextMsg(sendMsg,json);
				} else {//如果CR没有返回结果，就直接调用系统的默认恢复
					log.info("-----------------------100-------------------------------");
					sendMsg = sendDefualtMsg(sendMsg, msg);
					return sendMsg;
				}


		return sendMsg;
	}

	 public WxSendMsg setInfo(WxSendMsg sendMsg) {
			String strMessage =  "您好，为了更好的为您服务，请先留下您的联系信息。";
			String linkstr = "<a href='"+url+"?wxopenid=%s&hashcode=%s'>点击进入</a>";	
			linkstr = String.format(linkstr,sendMsg.getToUser(),weixinBean.getHash());
			sendMsg = new WxSendTextMsg(sendMsg,strMessage + linkstr);
			return sendMsg;
		}
		
//		public String getAccessToken2(String appid, String appsecret){
//        	String accessToken = "";
//	    	log.info("-----------------appid:" + appid);
//	    	log.info("----------------------appsecret:" + appsecret);
//        	List<WxGongzhonghao> wxgongzhonghaos = gzhService.getAll(WxGongzhonghao.class,new String[]{"appid","appsecret"},new Object[]{appid,appsecret});
//        	WxGongzhonghao m = wxgongzhonghaos.get(0);
//        	//WxGongzhonghao m = gzhService.get(WxGongzhonghao.class, "gzh_hash", hashcode);
//    		log.info("-------------------公共方法获得accesstoken------------------------");
//	    	log.info("appid:" + appid);
//	    	log.info("appsecret:" + appsecret);
//			log.info("obtainCRContent发送人工状态请求并修改数据库的accesstoken保存到数据库中，1小时取一次");
//			long l;
//			try {
//				if(m.getAccessTokenDate() == null || m.getAccessTokenDate().trim().equals("") || m.getAccessToken() == null || m.getAccessToken().trim().equals("")){
//					log.info("-----------此处为第一次获得accessToken:");
//					log.info("----------------accessTokenDate为null或者accessToken为null的case");
//	    			accessToken = HttpUtils.getToken1(m.getAppid(), m.getAppsecret());
//	    	    	m.setAccessToken(accessToken);
//	    	    	m.setAccessTokenDate(DateUtils.currentDatetime());
//	    	    	gzhService.update(m);
//	    	    	log.info("----------------accessToken:" + accessToken);
//				}else{
//					log.info("----------------accessTokenDate不为null");
//					l = DateUtils.parseDatetime(DateUtils.currentDatetime()).getTime() - DateUtils.parseDatetime(m.getAccessTokenDate()).getTime();
//		    		long day=l/(24*60*60*1000);
//		    		long hour=(l/(60*60*1000)-day*24);
//		    		//long min=((l/(60*1000))-day*24*60-hour*60);
//		    		log.info("--------------------hour:" + hour);
//		    		if(hour >= 1 || day > 0){
//		    			accessToken = HttpUtils.getToken1(m.getAppid(), m.getAppsecret());
//		    	    	m.setAccessToken(accessToken);
//		    	    	m.setAccessTokenDate(DateUtils.currentDatetime());
//		    	    	gzhService.update(m);
//		    	    	log.info("----------------accessToken:" + accessToken);
//		    	    	log.info("0000000000000000000此处为重新获得accesstoken00000000000000000000000:" + hour);
//		    		}
//				}
//				accessToken = m.getAccessToken();
//			}catch (ParseException e) {
//				e.printStackTrace();
//			}
//			return accessToken;
//		}

	    /**
		 * 清空cr开始会话的时间，表示结束与cr系统交互
		 * */
	    public String clearCRTime(){
	    	log.info("+++++++++CR++++++++++++++++++CR端开始调用clean方法+++++++++++++++++++++");
	    	String openid= ServletActionContext.getRequest().getParameter("userid");
	    	try{
				List<WxUserStatus> wxUserStatuses = wxUserStatusService.getAll(
						WxUserStatus.class, "openId", openid);
				log.info("openid:" + openid);
				log.info("wxUserStatuses size:" + wxUserStatuses.size());
				boolean defaultKeyRule = false;
				if (wxUserStatuses.size() > 0) {
					WxUserStatus userStatus = wxUserStatuses.get(0);

					userStatus.setModifyOn(DateUtils.currentDatetime());
					userStatus.setOpenId(openid);
					userStatus.setStatus(WxUserStatus.replied);//变为对话状态并存入数据库
					if (wxUserStatuses.size() > 0) {
						wxUserStatusService.update(userStatus);
					} else {
						wxUserStatusService.save(userStatus);
					}

	    		print("1");
				}
	    	}catch (Exception e){
	    		print("-1");
	    		e.printStackTrace();
	    	}
	    	return SUCCESS;
	    	//return "";
	    }
	    
	    public void postJosn(String reqURL, String sendData) throws ClientProtocolException, IOException{
	    	HttpPost httpPost = new HttpPost(reqURL);
	    	StringEntity entity;
			try {
				entity = new StringEntity(sendData.toString(), "UTF-8");
		    	entity.setContentType("application/json");
		    	httpPost.setEntity(entity);
		    	HttpClient client = new DefaultHttpClient();
		    	HttpResponse response = client.execute(httpPost);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    /**
	     * 把cr系统发送过来的客服消息，转发给微信用户
	     * */
	    public String sendMessageByCr(){
	    	log.info("+++++++++++++++++++++++++++CR端开始调用send方法+++++++++++++++++++++");
	    	String sendAPI = PropertiesUtil.getProperty("send_customer_message_ur");
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	String openid= request.getParameter("userid");
	    	String txt= request.getParameter("usertext");
	    	String ugzh = request.getParameter("hashcode");
	    	log.info("openid:" + openid);
	    	log.info("txt:" + txt);
	    	log.info("ugzh:" + ugzh);
//	    	print("");
	    	String accessToken = "";
	    	try{
	    		WxGongzhonghao m = gzhService.get(WxGongzhonghao.class, "gzh_hash", ugzh);
	    		log.info("----CR---------------cr端直接调用数据库中的accesstoken------------------------:");
				//accessToken = m.getAccessToken();
				
				
	    		accessToken = HttpUtils.getToken(m.getAppid(), m.getAppsecret());
				
				
				log.info("accessToken:" + accessToken);
		    	log.info("hashcode:" + ugzh);
		    	log.info("openid:" + openid);
		    	log.info("txt:" + txt);
		    	log.info("--------CR------------发送给用户之前打印出token:" + accessToken);
		    	sendAPI =  MessageFormat.format(sendAPI, accessToken);
		    	String msgJson = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		    	msgJson = String.format(msgJson, openid,txt);
		    	log.info("---------CR------准备发送给指定用户------------:" + accessToken);
		    	HttpClientUtil.sendPostRequestByJava(sendAPI,msgJson);
		    	//postJosn(sendAPI,msgJson);
		    	log.info("-googgooggooggooggooggooggooggooggooggooggooggooggooggooggoog---:" + accessToken);
		    	if(accessToken!=""){
		    		print("1");
		    	}else{
		    		print("-1");
		    	}
	    	}catch(Exception e){
	    		print("打印发送失败log开始------CR--------------");
	    		e.printStackTrace();
	    		print("-1");
	    	}
	    	return SUCCESS;
	    	//return "";
	    }
	    
//	    public String sms(){
//	    	String[] phones = {"15876527654"};
//		   	 try {
//					boolean result = GtmcSms.sendMessage(phones, "测试发送短信lee");
//					log.info("发送短信结果:" + result);
//				} catch (Exception e) {
//					System.out.println(e);
//					e.printStackTrace();
//				}
//		   	return SUCCESS;
//	    }
}
