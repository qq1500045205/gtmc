package com.tlan.plugins.sms;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.tlan.plugins.sms.jiaqi.ParamValues;

/**
 * HTTP请求类 magenm
 * 
 */

public class HttpClientUtil {
	static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public static String get(String url) throws Exception {
		return get(url, "utf-8");
	}

	public static String get(String url, String encoding) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = httpClient.execute(httpGet);
		return getContent(res, encoding);
	}

	public static String get(String url, String encoding,
			DefaultHttpClient client) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = client.execute(httpGet);
		return getContent(res, encoding);
	}

	public static String post(String url, StringEntity se, String host,
			String referer, String encoding) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(se);
		httpPost.setHeader("Host", host);
		httpPost.setHeader("Referer", referer);
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Accept-Language", "zh-cn");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("UA-CPU", "x86");
		httpPost.setHeader(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; InfoPath.2; CIBA)");
		httpPost.setHeader("Connection", "close");
		HttpResponse response = httpClient.execute(httpPost);

		return getContent(response, encoding);
	}

	public static String httpPost(String url, Map<String, String> params,
			String encoding) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		System.out.println("url:"+url);
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			postData.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
			System.out.println(entry.getValue());
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,
				ParamValues.URL_ENCODE);
		post.setEntity(entity);
		HttpResponse response = httpClient.execute(post);

		return getContent(response, encoding);
	}

	public static String getContent(HttpResponse res, String encoding)
			throws Exception {
		HttpEntity ent = res.getEntity();
		String result = IOUtils.toString(ent.getContent(), encoding);
		ent.consumeContent();
		return result;
	}

	public static InputStream getStream(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse res = httpClient.execute(httpGet);
		return res.getEntity().getContent();
	}

	public static InputStream getStream(String url, DefaultHttpClient client)
			throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; InfoPath.2; CIBA)");
		httpGet.setHeader("Referer",
				"http://reg.126.com/regmail126/userRegist.do?action=fillinfo");
		// httpGet.setHeader("Accept", "*/*");
		// httpGet.setHeader("Accept-Language", "zh-cn");
		// httpGet.setHeader("Accept-Encoding", "gzip, deflate");
		httpGet.setHeader("Connection", "close");
		HttpResponse res = client.execute(httpGet);
		return res.getEntity().getContent();
	}

}
