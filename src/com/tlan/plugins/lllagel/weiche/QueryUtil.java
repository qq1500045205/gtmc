package com.tlan.plugins.lllagel.weiche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.plugins.lllagel.vo.CityBean;
import com.tlan.plugins.lllagel.vo.QueueBean;
import com.tlan.plugins.lllagel.vo.ResultBean;


/**
 * 违章查询 search("京n0n181", "c393388", "", "beijing");
 * 
 * 剩余测试查询 querySycs()
 * 
 * 查询支持城市queryCity()
 * 
 * @author Administrator
 * 
 */
public class QueryUtil {

	public final static String METHOD_GET = "GET";
	public final static String METHOD_POST = "POST";

	private static List<Struct> covertList;
	static {
		covertList = new ArrayList<>();
		covertList.add(new Struct("京", "beijing", "北京市"));
		covertList.add(new Struct("沪", "shanghai", "上海市"));
		covertList.add(new Struct("吉", "jilin", "吉林省"));
		covertList.add(new Struct("鲁", "shandong", "山东省"));
		covertList.add(new Struct("冀", "hebei", "河北省"));
		covertList.add(new Struct("湘", "hunan", "湖南省"));
		covertList.add(new Struct("青", "qinghai", "青海省"));
		covertList.add(new Struct("苏", "jiangsu", "江苏省"));
		covertList.add(new Struct("浙", "zhejiang", "浙江省"));
		covertList.add(new Struct("粤", "guangdong", "广东省"));
		covertList.add(new Struct("甘", "gansu", "甘肃省"));
		covertList.add(new Struct("川", "sichuan", "四川省"));
		covertList.add(new Struct("黑", "heilongjiang", "黑龙江省"));
		covertList.add(new Struct("蒙", "neimenggu", "内蒙古"));
		covertList.add(new Struct("新", "xinjiang", "新疆维吾尔"));
		covertList.add(new Struct("津", "tianjin", "天津市"));
		covertList.add(new Struct("渝", "chongqing", "重庆市"));
		covertList.add(new Struct("辽", "liaoning", "辽宁省"));
		covertList.add(new Struct("豫", "henan", "河南省"));
		covertList.add(new Struct("鄂", "hubei", "湖北省"));
		covertList.add(new Struct("晋", "shan1xi", "山西省"));
		covertList.add(new Struct("皖", "anhui", "安徽省"));
		covertList.add(new Struct("赣", "jiangxi", "江西省"));
		covertList.add(new Struct("闽", "fujian", "福建省"));
		covertList.add(new Struct("琼", "hainan", "海南省"));
		covertList.add(new Struct("陕", "shan3xi", "陕西省"));
		covertList.add(new Struct("云", "yunnan", "云南省"));
		covertList.add(new Struct("贵", "guizhou", "贵州省"));
		covertList.add(new Struct("藏", "xizang", "西藏"));
		covertList.add(new Struct("宁", "ningxia", "宁夏回族"));
		covertList.add(new Struct("桂", "guangxi", "广西壮族"));
	}

	/**
	 * 计算签名信息
	 * 
	 * @param METHOD
	 *            api 请求方法（GET POST）
	 * @param API
	 *            api。例 /v2/cities
	 * @param DATE
	 *            日期格式：Fri, 12 Jul 2013 09:13:05 GMT (RFC 1123)格式
	 * @param CONTENT_LENGTH
	 *            请求content长度，如果为GET，则长度为0
	 * @param APP_SECRET
	 * @return
	 */
	private static String signatrue(String method, String api, String date,
			long content_length, String app_secret) {
		return DataUtils.md5(method + "&" + api + "&" + date + "&"
				+ content_length + "&" + DataUtils.md5(app_secret));

	}

	/**
	 * 构造请求参数
	 * 
	 * @param carNumber
	 *            车牌号 必填
	 * @param engineNumber
	 *            发动机号 必填
	 * @param bodyNumber
	 *            车架号 ka
	 * @param cityPinyin
	 * @return
	 */
	private static List<NameValuePair> getData(String carNumber,
			String engineNumber, String bodyNumber, String cityPinyin) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("license_plate_num", carNumber));
		params.add(new BasicNameValuePair("engine_num", engineNumber));
		params.add(new BasicNameValuePair("body_num", bodyNumber));
		params.add(new BasicNameValuePair("city_pinyin", cityPinyin));
		return params;
	}

	/**
	 * 增加违章查询任务
	 * 
	 * @param host
	 *            域名
	 * @param uri
	 *            bathpath，如/v2/jobs
	 * @param data
	 *            请求参数
	 * @return
	 */
	private static QueueBean httpClientPost(String host, String uri,
			List<NameValuePair> data) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://" + host + uri);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,
					"UTF-8");
			post.setEntity(entity);

			String date = DateUtils.fromatRFC1123(new Date());
			String secret = PropertiesUtil.getProperty("lllegal_app_secret");
			String key = PropertiesUtil.getProperty("lllegal_appkey");
			String auth = signatrue(METHOD_POST, uri, date,
					entity.getContentLength(), secret);
			System.out.println(key + ":" + auth);
			System.out.println(date);
			post.addHeader("Host", host);
			post.addHeader("Authorization", key + ":" + auth);
			post.addHeader("Date", date);
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");

			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity());

			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("violations", ResultBean.class);

			QueueBean bean = (QueueBean) JSONObject.toBean(
					JSONObject.fromObject(result), QueueBean.class, classMap);
			return bean;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询违章查询任务执行结果
	 * 
	 * @param host
	 *            域名
	 * @param uri
	 *            bathpath，如/v2/jobs
	 * @return
	 */
	private static QueueBean httpClientGet(String host, String uri) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://" + host + uri);
		try {
			String date = DateUtils.fromatRFC1123(new Date());
			String secret = PropertiesUtil.getProperty("lllegal_app_secret");
			String key = PropertiesUtil.getProperty("lllegal_appkey");
			String auth = signatrue(METHOD_GET, uri, date, 0, secret);

			get.addHeader("Host", host);
			get.addHeader("Authorization", key + ":" + auth);
			get.addHeader("Date", date);
			get.addHeader("Content-Type", "application/x-www-form-urlencoded");

			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("violations", ResultBean.class);

			QueueBean bean = (QueueBean) JSONObject.toBean(
					JSONObject.fromObject(result), QueueBean.class, classMap);
			return bean;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 增加违章查询任务
	 * 
	 * @param carNumber
	 * @param engineNumber
	 * @param bodyNumber
	 * @param cityPinyin
	 */
	public static QueueBean search(String carNumber, String engineNumber,
			String bodyNumber, String cityPinyin) {
		String host = "api.buding.cn";

		String api = "/v2/jobs"; // 将查询放入队列
		String uri = "/v2/job/{1}"; // 读取结果
		List<NameValuePair> data = getData(carNumber, engineNumber, bodyNumber,
				cityPinyin);
		QueueBean bean = httpClientPost(host, api, data);
		return bean;
	}

	/**
	 * 查询违章查询任务执行结果
	 * 
	 * @param carNumber
	 * @param engineNumber
	 * @param bodyNumber
	 * @param cityPinyin
	 */
	public static QueueBean search2(String id) {
		String host = "api.buding.cn";
		String uri = "/v2/job/{1}"; // 读取结果
		try {
			uri = uri.replace("{1}", id);
			QueueBean result = httpClientGet(host, uri);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询支持的城市 ,建议30分钟请求一次
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CityBean> queryCity() {
		String host = "api.buding.cn";
		String uri = "/v2/cities";
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://" + host + uri);
		try {
			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			List<CityBean> cityList = JSONArray.toList(
					JSONArray.fromObject(result), new CityBean(),
					new JsonConfig());
			return cityList;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当天当前app剩余api调用次数
	 * 
	 * @return
	 */
	public static long querySycs() {
		String host = "api.buding.cn";
		String uri = "/v2/rate_limit_status";
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://" + host + uri);
		try {
			String date = DateUtils.fromatRFC1123(new Date());
			String secret = PropertiesUtil.getProperty("lllegal_app_secret");
			String key = PropertiesUtil.getProperty("lllegal_appkey");
			String auth = signatrue(METHOD_GET, uri, date, 0, secret);

			get.addHeader("Host", host);
			get.addHeader("Authorization", key + ":" + auth);
			get.addHeader("Date", date);
			get.addHeader("Content-Type", "application/x-www-form-urlencoded");

			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(result);
			return Long.parseLong(json.getString("remaining_hits"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}

	}

	/**
	 * 根据key 获取 value
	 * 
	 * @param key
	 * @return
	 */
	private static String getValue(String key) {
		for (Struct s : covertList) {
			if (s.getPinyin().equals(key)) {
				key = s.getPinyin();
				break;
			}
		}
		return key;
	}

	/**
	 * 根据key 获取 name
	 * 
	 * @param key
	 * @return
	 */
	public static String getName(String pinyin) {
		String name = "";
		for (Struct s : covertList) {
			if (s.getPinyin().equals(pinyin)) {
				name = s.getName();
				break;
			}
		}
		return name;
	}

	/**
	 * 根据key 获取 pinyin
	 * 
	 * @param key
	 * @return
	 */
	public static String getPinyin(String name) {
		String pinyin = "";
		for (Struct s : covertList) {
			if (s.getName().equals(name)) {
				pinyin = s.getPinyin();
				break;
			}
		}
		return pinyin;
	}

	/**
	 * 保存城市数据
	 * 
	 * @param struct
	 * @param bean
	 */
	private static void putstruct(List<StructCity> struct, CityBean bean) {
		String key = bean.getProvince_pinyin();
		System.out.println(key);
		key = getValue(key);
		boolean exists = false;
		for (StructCity struct2 : struct) {
			if (key.equals(struct2.getKey())) {
				List<CityBean> list = struct2.getList();
				list.add(bean);
				exists = true;
				break;
			}
		}

		if (!exists) {
			List<CityBean> list = new ArrayList<>();
			list.add(bean);
			StructCity st = new StructCity(key, list);
			struct.add(st);
		}
	}

	/**
	 * 获取城市
	 * 
	 * @param cities
	 * @return
	 */
	public static String getCityJson(List<CityBean> cities) {
		List<StructCity> struct = new ArrayList<>();
		for (CityBean bean : cities) {
			putstruct(struct, bean);
		}

		return JSONArray.fromObject(struct).toString();
	}

	/**
	 * 获取省
	 * 
	 * @return
	 */
	public static String getProviceJson() {
		return JSONArray.fromObject(covertList).toString();
	}

	/**
	 * 省份简写和全称转换
	 * 
	 */
	// private static void covert() {
	// covertList = new ArrayList<>();
	// covertList.add(new Struct("京", "beijing", "北京市"));
	// covertList.add(new Struct("沪", "shanghai", "上海市"));
	// covertList.add(new Struct("吉", "jilin", "吉林省"));
	// covertList.add(new Struct("鲁", "shandong", "山东省"));
	// covertList.add(new Struct("冀", "hebei", "河北省"));
	// covertList.add(new Struct("湘", "hunan", "湖南省"));
	// covertList.add(new Struct("青", "qinghai", "青海省"));
	// covertList.add(new Struct("苏", "jiangsu", "江苏省"));
	// covertList.add(new Struct("浙", "zhejiang", "浙江省"));
	// covertList.add(new Struct("粤", "guangdong", "广东省"));
	// covertList.add(new Struct("甘", "gansu", "甘肃省"));
	// covertList.add(new Struct("川", "sichuan", "四川省"));
	// covertList.add(new Struct("黑", "heilongjiang", "黑龙江省"));
	// covertList.add(new Struct("蒙", "neimenggu", "内蒙古"));
	// covertList.add(new Struct("新", "xinjiang", "新疆维吾尔"));
	// covertList.add(new Struct("津", "tianjin", "天津市"));
	// covertList.add(new Struct("渝", "chongqing", "重庆市"));
	// covertList.add(new Struct("辽", "liaoning", "辽宁省"));
	// covertList.add(new Struct("豫", "henan", "河南省"));
	// covertList.add(new Struct("鄂", "hubei", "湖北省"));
	// covertList.add(new Struct("晋", "shan1xi", "山西省"));
	// covertList.add(new Struct("皖", "anhui", "安徽省"));
	// covertList.add(new Struct("赣", "jiangxi", "江西省"));
	// covertList.add(new Struct("闽", "fujian", "福建省"));
	// covertList.add(new Struct("琼", "hainan", "海南省"));
	// covertList.add(new Struct("陕", "shan3xi", "陕西省"));
	// covertList.add(new Struct("云", "yunnan", "云南省"));
	// covertList.add(new Struct("贵", "guizhou", "贵州省"));
	// covertList.add(new Struct("藏", "xizang", "西藏"));
	// covertList.add(new Struct("宁", "ningxia", "宁夏回族"));
	// covertList.add(new Struct("桂", "guangxi", "广西壮族"));
	//
	// }

	public static void main(String[] args) {
		List<CityBean> list = queryCity();
		String pro = getProviceJson();
		String city = getCityJson(list);
		System.out.println(pro);
		System.out.println(city);
	}

}
