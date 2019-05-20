package com.tlan.admin.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxGongzhonghao;
import com.tlan.common.model.WxUserStatus;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.wxkit.bean.WeiXinBean;

@Component
@Scope(value = "prototype")
public class CR extends BaseAction implements Preparable,
ModelDriven<WeiXinBean>{
	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	@Resource(name = "baseService")
	private IBaseService<WxGongzhonghao> gzhService;
	@Resource(name = "baseService")
	private IBaseService<WxUserStatus> wxUserStatusService;
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
//    	print("");
    	String accessToken = "";
    	try{
    		WxGongzhonghao m = gzhService.get(WxGongzhonghao.class, "gzh_hash", ugzh);
    		log.info("----CR---------------cr端直接调用数据库中的accesstoken------------------------:");
			accessToken = m.getAccessToken();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
