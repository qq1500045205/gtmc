package com.tlan.web.action.car;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

public class UsedCarValueAction extends BaseAction {
	private static final String apiKey = "48b722f0-cf00-4c5b-81e9-e3cefb3ac054";
	private static final String screct = "417e2357-68e7-494d-8cb4-e4a2c3393124";
	private static final String host = "http://car.iautos.cn/Maverick";

	private LoginBean loginBean = obtainLoginBean();

	private String[] items;

	private String key;
	private String params;
	private String Nid;
	private String Ccsj;
	private String Vin;

	public String getCarInfo() {

		String src = host + "/" + key + "/ApiKey/" + apiKey;
		JSONArray ja = JSONArray.fromObject(params);
		for (Object obj : ja) {
			JSONObject jo = (JSONObject) obj;
			String key = jo.getString("key");
			String value = jo.getString("value");
			src += "/" + key + "/" + value;
		}
		// src =
		// "http://car.iautos.cn/Maverick/Year/ApiKey/48b722f0-cf00-4c5b-81e9-e3cefb3ac054/Nid/4";
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(src);
		try {

			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			setMap(true, "success", result);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		}

		return this.SUCCESS;
	}

	public String getCarAllInfosByNidAndCcsj() {
		String now = DateUtils.currentDatetime();
		String[] dateStr = now.split(" ");
		String date = "", hour = "", minute = "", second = "";
		if (dateStr.length == 2) {
			date = dateStr[0];
			String[] timeStr = dateStr[1].split(":");
			if (timeStr.length == 3) {
				hour = timeStr[0];
				minute = timeStr[1];
				second = timeStr[2];
			}
		}
		
		String screctStr = DataUtils.md5(Nid + Ccsj + now + screct);
		String src = host + "/CarTrim/GetTrimInfoByNidAndCCSJ/" + apiKey;
		src += "/" + Nid + "/" + Ccsj + "/" + date + "/" + hour + "/" + minute
				+ "/" + second + "/" + screctStr;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(src);
		try {

			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			setMap(true, "success", result);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		}

		return this.SUCCESS;
	}

	public String getCarAllInfosByVin() {
		
		String now = DateUtils.currentDatetime();
		String[] dateStr = now.split(" ");
		String date = "", hour = "", minute = "", second = "";
		if (dateStr.length == 2) {
			date = dateStr[0];
			String[] timeStr = dateStr[1].split(":");
			if (timeStr.length == 3) {
				hour = timeStr[0];
				minute = timeStr[1];
				second = timeStr[2];
			}
		}
		
		String screctStr = DataUtils.md5(Vin + now + screct);
		String src = host + "/Vin/GetTrimInfoByVin/" + apiKey;
		src += "/" + Vin +  "/" + date + "/" + hour + "/" + minute
				+ "/" + second + "/" + screctStr;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(src);
		try {

			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			setMap(true, "success", result);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMap(true, "fail", null);
			e.printStackTrace();
		}

		return this.SUCCESS;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getNid() {
		return Nid;
	}

	public void setNid(String nid) {
		Nid = nid;
	}

	public String getCcsj() {
		return Ccsj;
	}

	public void setCcsj(String ccsj) {
		Ccsj = ccsj;
	}

	public String getVin() {
		return Vin;
	}

	public void setVin(String vin) {
		Vin = vin;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

}
