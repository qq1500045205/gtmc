package com.tlan.wxkit.http;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.HttpClientUtil;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;  
import java.sql.DriverManager;           
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement; 
import java.text.ParseException;
import java.util.Properties;
/**
 * HTTP请求类
 * 
 * @author magenm
 * 
 */
public class HttpUtils {
	private static Logger log = Logger.getLogger(HttpUtils.class);
	/**
	 * 获取token
	 * 
	 * @param gzh
	 * @return
	 */
	public static String getToken(String appid, String appsecret) {
		//modify by lyn start
		String accessToken = "";
		Properties props;
		props = new Properties();
		InputStream fis = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream("cfg/jdbc.properties");
		Connection conn = null;  
        Statement statement = null;  
        ResultSet rs = null;  
		try {
			props.load(fis);
			String driver = props.getProperty("jdbc.driver");
			String url = props.getProperty("jdbc.url");
			String user = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
	        statement = conn.createStatement();
	        String sql = "select * from wx_gongzhonghao s where s.appid = '"+ appid +"' and s.appsecret = '"+ appsecret +"'";
	        log.info("-----------10--------------:"+sql);
            rs = statement.executeQuery(sql);  
            long l;
            if(rs.next()){
            	log.info("-----HttpUtilsHttpUtils------20--------------:");
            	log.info("-----HttpUtilsHttpUtils------@access_token--------------:"+rs.getString("access_token"));
				if(rs.getString("access_token") == null || rs.getString("access_token_date") == null){
            	//if(rs.getString("access_token") == null){
					log.info("-----------此处为第一次获得accessToken:");
					log.info("----------------accessTokenDate为null或者accessToken为null的case");
	    			accessToken = getTokenOld(appid, appsecret);
	    	    	log.info("----------------accessToken:" + accessToken);
	    	    	
	    	    	if (statement != null ) statement .close();
					statement = conn.createStatement();
	    	        String sql2 = "update wx_gongzhonghao s set s.access_token = '"+ accessToken +"',s.access_token_date = SYSDATE() where s.appid = '"+ appid +"' and s.appsecret = '"+ appsecret +"'"; 
	    	        statement.executeUpdate(sql2);
	    	        log.info("-----HttpUtilsHttpUtils------30--------------:");
				}else{
					log.info("------HttpUtilsHttpUtils----------accessTokenDate不为null");
					l = DateUtils.parseDatetime(DateUtils.currentDatetime()).getTime() - DateUtils.parseDatetime(rs.getString("access_token_date")).getTime();
		    		long day=l/(24*60*60*1000);
		    		long hour=(l/(60*60*1000)-day*24);
		    		//long min=((l/(60*1000))-day*24*60-hour*60);
		    		log.info("--------HttpUtilsHttpUtils------------hour:" + hour);
		    		if(hour >= 1 || day > 0){
		    			accessToken = HttpUtils.getTokenOld(appid, appsecret);
		    	    	log.info("----------------accessToken:" + accessToken);
		    	    	log.info("0000000000000000000此处为重新获得accesstoken00000000000000000000000:" + hour);

		    	    	if (statement != null ) statement .close();
						statement = conn.createStatement();
		    	        String sql2 = "update wx_gongzhonghao s set s.access_token = '"+ accessToken +"',s.access_token_date = SYSDATE() where s.appid = '"+ appid +"' and s.appsecret = '"+ appsecret +"'"; 
		    	        statement.executeUpdate(sql2);
		    	        log.info("----HttpUtilsHttpUtils-------40--------------:");
		    		}else{
		    			accessToken = rs.getString("access_token");
		    		}
				}
            }
            log.info("-------HttpUtilsHttpUtils----50--------------:"+accessToken);
	        if(conn != null) conn.close();
	        if (statement != null ) statement .close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}  catch (ParseException e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	public static String getTokenOld(String appid, String appsecret) {
		String accessToken = null;
		try {
			String url = PropertiesUtil.getProperty(ParamString.GET_TOKEN_URL);
			url = url.replace("{1}", appid);
			url = url.replace("{2}", appsecret);
			// 发送请求
			String response = HttpClientUtil.sendGetRequest(url, null);
			log.info("getToken:"+response);
			JSONObject jsonArray = JSONObject.fromObject(response);
			accessToken = (String) jsonArray.get(PropertiesUtil.getProperty(ParamString.ACCESS_TOKEN));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
		//modify by lyn end
	}
	/**
	 * 生成菜单
	 * 
	 * @param gzh
	 * @return
	 */
	public static boolean generator(String token, String menuJson) {
		// post方式创建菜单
		String response = HttpClientUtil.sendPostRequestByJava(
				PropertiesUtil.getProperty(ParamString.CREATE_MENU).replace("{1}", token), menuJson);
		log.info("generator:"+response);
		JSONObject jsonObj = JSONObject.fromObject(response);
		if ("0".equals(jsonObj.getString("errcode"))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 生成菜单
	 * 
	 * @param gzh
	 * @return
	 */
	public static boolean templateMessage(String token, String json) {
		// post方式创建菜单
		String response = HttpClientUtil.sendPostRequestByJava(
				PropertiesUtil.getProperty(ParamString.TEMPLATE_MESSAGE).replace("{1}", token), json);
		log.info("templateMessage:"+response);
		JSONObject jo = JSONObject.fromObject(response);
		//modify by yiwei start
		//if ("0".equals(jo.get("errcode"))) {
		if ("0".equals(jo.getString("errcode"))) {
		//modify by yiwei end
			return true;
		} else {
			return false;
		}
	}

}
