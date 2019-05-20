package com.nec.tasks;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nec.model.survey.WxSurverSend;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.wxkit.http.HttpUtils;

public class SendSurverMessage implements Runnable {

	private static String sendAPI = PropertiesUtil.getProperty("send_customer_message_ur");
	private static String message =  "尊敬的{0}(先生/女士)，您({1})在{2}做了一次车辆维修(工单号：{3})，感谢您继续选择我们的品牌渠道进行维修保养。为了能够更好的为您提供服务，请{4}，协助我们完成一个非常简单的“售后满意度调查”，就本次维修过程中经销店对您的服务作出评价。您的评价将帮助我们未来的改善。";
	@SuppressWarnings("rawtypes")
	private IBaseService surverSendService;
	private String url;
	
	@SuppressWarnings("rawtypes")
	public SendSurverMessage(IBaseService service ,String url){
		this.surverSendService = service;
		this.url = url;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void run() {
		List<WxSurverSend> sendDataList = surverSendService.getAll(WxSurverSend.class,new String[]{"1"},
												new String[]{"1"},new String[]{"="},"companycode asc");
		if (sendDataList==null){
			return;
		}
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2= new SimpleDateFormat("yyyy年MM月dd日");
		Date now = new Date();
		
		String curcompanycode = "";
		String token=null;
		String reqURL = null;
		
		
		for (int i=0;i<sendDataList.size();i++){
			WxSurverSend d =(WxSurverSend) sendDataList.get(i);
			if (d.getCompanycode()!=curcompanycode){
				token = HttpUtils.getToken(d.getAppid(), d.getAppsecret());
				reqURL = MessageFormat.format(sendAPI, token);
				curcompanycode = d.getCompanycode();
			}
			String msgJson = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
			String linkstr = "<a href=\\\""+url+"?senddate=%s&openid=%s&orderno=%s\\\">点击这里</a>";
			linkstr = String.format(linkstr,format.format(now),d.getOpenId(),d.getOrderno());
			String msgTxt="";
			try {
				msgTxt = MessageFormat.format(message, 
						d.getCustomername(),
						format2.format(format.parse(d.getRepairdate())),
						d.getWxcompanyname(),
						d.getOrderno(),
						linkstr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			msgJson = String.format(msgJson, d.getOpenId(),msgTxt);
			HttpClientUtil.sendPostRequestByJava(reqURL,msgJson);
		}
		String movedatasql = "INSERT INTO wx_surver_send_history SELECT *,curdate() FROM wx_surver_send";
		List<String> sqllist = new ArrayList();
		sqllist.add(movedatasql);
		surverSendService.executeSql(sqllist);
		surverSendService.deleteAll(WxSurverSend.class);
	}
}
