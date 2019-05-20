package com.tlan.admin.api;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;	
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


	public class testCR {
		public static void main(String[] arga) throws JSONException{
			String resp="";
			Map<String,String> params=new HashMap<String,String>();
			params.put("service","findKeyWord");
				//service表示处理的请求的类别：
				//1.findKeyWord查找关键字
				//2.findUser查看用户是否留资
				//3.register用户留资
				//4.send发送访客的信息给客服
				//5.endChat关闭对话
			
//			
//			//查找关键字
//			params.put("KeyType", "1");//1.表示关键字,2表示问题的编号
//			params.put("userid","oshVGuJn1OswnK5u_qi95AfHeea0");
//			params.put("wd", "凯美瑞 发动机");
//			params.put("MAXCount","10");
//			String res = sendPostRequest("http://203.88.200.33:8080/webcall-live-mobile/weixinservlet", params, "UTF-8","UTF-8");
//			if(res==null)
//				System.out.println("处理失败");
//			System.out.println(res+"\n\n");
//			JSONObject jo=new JSONObject(res);
//			JSONArray ja=jo.getJSONArray("result");
//			for(int i=0;i<ja.length();i++){
//				JSONObject js=ja.getJSONObject(i);
//				System.out.println("PhysicsSequence: "+js.get("PhysicsSequence"));
//				System.out.println("answer: "+js.getString("answer"));
//				System.out.println("question: "+js.getString("question"));
//				System.out.println("LogicSequence: "+js.get("LogicSequence"));
//			}
//			System.out.println("ifFlag: "+jo.getString("ifFlag"));
//			
//			
//			if("-1".equals(jo.getString("ifFlag")))
//				System.out.println("处理失败");
//			else if("1".equals(jo.getString("ifFlag")))
//				System.out.println("处理成功");
////			
//			//根据用户输入的问题返回答案
//			params.clear();
//			params.put("service","findKeyWord");
//			params.put("KeyType", "2");//1.表示关键字,2表示问题的编号
//			params.put("userid","oshVGuJn1OswnK5u_qi95AfHeea0");
//			params.put("wd", "1");
//			JSONObject jo=new JSONObject(sendPostRequest("http://203.88.200.33:8080/webcall-live/weixinservlet", params, "UTF-8","UTF-8"));
//			if("-1".equals(jo.getString("ifFlag")))
//					System.out.println("处理失败");
//			if("1".equals(jo.getString("ifFlag"))){
//				System.out.println("处理失败");
//				System.out.println("result:"+jo.getString("result"));
//			}
			
//			//用户留资
//			params.clear();
//			params.put("service","register");
//			params.put("userid","123");
//			params.put("usersex","m");
//			params.put("username","hxm");
//			params.put("usertel","15618667385");
//			params.put("buycarflag", "y");
//			params.put("hashcode", "15618667385");
//			params.put("purposecarmodel", "凯美瑞");
//			params.put("buyCarModel","保时捷");
//			params.put("buydlrcode", "shanghai");
//			resp=sendPostRequest("http://203.88.200.33:8080/webcall-live-mobile/weixinservlet", params, "UTF-8","UTF-8");
//			System.out.println(resp);
//			if("1".equals(resp))
//				System.out.println("处理成功");
//			else
//				System.out.println("处理失败");
//			
//			//查看用户是否留资
//			params.clear();
//			params.put("service","findUser");
//			params.put("userid", "1");
//			params.put("hashcode", "1");
//			resp=sendPostRequest("http://203.88.200.33:8080/webcall-live-mobile/weixinservlet", params, "UTF-8","UTF-8");
//			JSONObject jo=new JSONObject(resp);
//			System.out.println(resp);
//			resp=jo.get("ifFlag").toString();
//			System.out.println(resp);
//			if("1".equals(resp))
//				System.out.println("处理成功");
//			else
//				System.out.println("处理失败");
			
			//访客发送信息给客服
			params.clear();
			params.put("service", "send");
			params.put("userid","15618667385");
			params.put("hashcode","15618667385");
			params.put("usertext","1");
			params.put("sessionid", "xxxxxxxxxx");
			params.put("username","hxm");
			resp=sendPostRequest("http://203.88.200.33:8080/webcall-live-mobile/weixinservlet", params, "UTF-8","UTF-8");
			System.out.println(resp);
			if("1".equals(resp))
				System.out.println("处理成功");
			else
				System.out.println("处理失败");
//			
			
//			//结束对话
//			params.clear();
//			params.put("service","endChat");
//			params.put("userid","15618667385");
//			params.put("hashcode","15618667385");
//			params.put("usertext","hello world");
//			params.put("sessionid", "xxxxxxxxxx");
//			params.put("username","hxm");
//			resp=sendPostRequest("http://203.88.200.33:8080/webcall-live-mobile/weixinservlet", params, "UTF-8","UTF-8");
//			System.out.println(resp);
//			if("1".equals(resp))
//				System.out.println("处理成功");
//			else
//				System.out.println("处理失败");
				
			
			
		}
			
			
			
			
	 public static String sendPostRequest(String reqURL,
	  			Map<String, String> params, String encodeCharset,
	  			String decodeCharset) {
	  		String responseContent = null;
	  		HttpClient httpClient = new DefaultHttpClient();

	  		HttpPost httpPost = new HttpPost(reqURL);
	  		List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
	  		for (Map.Entry<String, String> entry : params.entrySet()) {
	  			formParams.add(new BasicNameValuePair(entry.getKey(), entry
	  					.getValue()));
	  		}
	  		try {
	  			httpPost.setEntity(new UrlEncodedFormEntity(formParams,
	  					encodeCharset == null ? "UTF-8" : encodeCharset));

	  			HttpResponse response = httpClient.execute(httpPost);
	  			HttpEntity entity = response.getEntity();
	  			if (null != entity) {
	  				responseContent = EntityUtils.toString(entity,
	  						decodeCharset == null ? "UTF-8" : decodeCharset);
	  				EntityUtils.consume(entity);
	  			}
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		} finally {
	  			httpClient.getConnectionManager().shutdown();
	  		}
	  		return responseContent;
	  	}
	     
	  
}