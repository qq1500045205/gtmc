package com.tlan.wxkit.fakeid;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;

/**
 * 模拟登陆 获取数据 发送消息
 * 
 * @author wang
 * @version V1.0
 */
public class WeixinMain {

	// 模拟登陆的url
	public static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	// 模拟登陆后发送信息的url
	public static String SEND_MSG = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN";
	public final static String HOST = "http://mp.weixin.qq.com";

	public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
	public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
	public final static String POST_MSG = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";
	public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
	public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
	public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
	public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
	public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
	public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
	public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
	public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
	public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
	public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage";
	public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
	public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
	public final static String SINGLE_MSG_PAGE = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage";
	public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
	public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
	public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token=416919388&t=iframe-uploadfile&lang=zh_CN&formId=1";

	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String REFERER_H = "Referer";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String UTF_8 = "UTF-8";

	private HttpClient client;

	public static Cookie[] cookies;
	public static String cookiestr;
	public static String TOKEN;
	public static int loginErrCode;
	public static String loginErrMsg;
	public static int msgSendCode;
	public static String msgSendMsg;
	public static HashMap<String, String> cook;

	/**
	 * 模拟登录信息,记录cookie token等信息
	 * 
	 * @param loginUser
	 * @param loginPwd
	 * @return
	 */
	public FakeBean weixin_login(String loginUser, String loginPwd,
			String openid, String createtime) {
		try {
			client = new HttpClient();
			PostMethod post = new PostMethod(LOGIN_URL);
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			post.setRequestHeader(REFERER_H, INDEX_URL);
			NameValuePair[] params = new NameValuePair[] {
					new NameValuePair("username", loginUser),
					new NameValuePair("pwd", DigestUtils.md5Hex(loginPwd
							.getBytes())), new NameValuePair("f", "json"),
					new NameValuePair("imagecode", "") };
			post.setQueryString(params);
			int status = client.executeMethod(post);
			System.out.println("会话的返回值：" + status);
			if (status == HttpStatus.SC_OK) {
				String ret = post.getResponseBodyAsString();
				System.out.println(ret);
				LoginJson retcode = JSON.parseObject(ret, LoginJson.class);
				if (retcode.getRet() == 302 && retcode.getErrCode() == 0) {
					cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					cook = new HashMap<String, String>();
					for (Cookie c : client.getState().getCookies()) {
						cook.put(c.getName(), c.getValue());
						cookie.append(c.getName()).append("=")
								.append(c.getValue()).append(";");
					}
					cookiestr = cookie.toString();
					String errMsg = retcode.getErrMsg();
					TOKEN = errMsg.substring(errMsg.lastIndexOf("=") + 1,
							errMsg.length());
				}
				int errCode = retcode.getErrCode();
				switch (errCode) {
				case -1:
					loginErrMsg = "系统错误";
					System.out.println("系统错误");
					return null;
				case -2:
					loginErrMsg = "帐号或密码错误";
					System.out.println("帐号或密码错误");
					return null;
				case -3:
					loginErrMsg = "密码错误";
					System.out.println("密码错误");
					return null;
				case -4:
					loginErrMsg = "不存在该帐户";
					System.out.println("不存在该帐户");
					return null;
				case -5:
					loginErrMsg = "访问受限";
					System.out.println("访问受限");
					return null;
				case -6:
					loginErrMsg = "需要输入验证码";
					System.out.println("需要输入验证码");
					return null;
				case -7:
					loginErrMsg = "此帐号已绑定私人微信号，不可用于公众平台登录";
					System.out.println("此帐号已绑定私人微信号，不可用于公众平台登录");
					return null;
				case -8:
					loginErrMsg = "邮箱已存在";
					System.out.println("邮箱已存在");
					return null;
				case -200:
					loginErrMsg = "因频繁提交虚假资料，该帐号被拒绝登录";
					System.out.println("因频繁提交虚假资料，该帐号被拒绝登录");
					return null;
				case -94:
					loginErrMsg = "使用邮箱登陆";
					System.out.println("使用邮箱登陆");
					return null;
				case 0:
					loginErrMsg = "成功登陆";
					System.out.println("成功登陆");
					break;
				default:
					loginErrMsg = "未知的返回......";
					System.out.println("未知的返回......");
					return null;
				}
			}

			List<FansJson> flist = getFansOne();

			FakeBean fakeBean = null;
			if (flist != null) {
				fakeBean = new FakeBean();
				for (int i = 0; i < flist.size(); i++) {
					fakeBean.setFakeid(flist.get(i).getId());
					fakeBean.setNickname(flist.get(i).getNick_name());
					fakeBean.setOpenid(openid);
					fakeBean.setCreatetime(createtime);
					break;
				}
			}
			post.releaseConnection();
			return fakeBean;
		} catch (Exception e) {
			String info = "【登录失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			return null;
		}
	}

	/**
	 * 访问用户管理 获取关注列表
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	public List<FansJson> getFansOne() throws HttpException, IOException {
		GetMethod get = new GetMethod(
				"http://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=1&pageidx=0&type=0&groupid=0&token="
						+ TOKEN + "&lang=zh_CN");
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader(REFERER_H, INDEX_URL);
		get.setRequestHeader("Cookie", cookiestr);
		int status = client.executeMethod(get);
		System.out.println("getFansOne status:" + status);
		if (status == HttpStatus.SC_OK) {
			String sr = get.getResponseBodyAsString();
			return parseFansCount(sr);
		}
		return null;
	}

	/**
	 * 解析返回的页面数据 获取组的数据和当前组的10个人员信息
	 * 
	 * @param text
	 * @return
	 */
	private List<FansJson> parseFansCount(String text) {
		try {
			StringBuffer json = new StringBuffer();
			// 先取出最外出的json对象的String
			final String start = "cgiData=";
			for (int i = text.indexOf(start) + start.length(), len = text
					.length(); i < len; i++) {
				char ci = text.charAt(i);
				if (ci == ';') {
					break;
				}
				json.append(text.charAt(i));
			}
			System.out.println("json.toString()==" + json.toString());
			/**
			 * 总的数据结构JSON string数据
			 */
			String temp = json.toString();
			// 在取出group的json
			StringBuffer grouptempjson = new StringBuffer();
			final String grouptempstart = "\"groups\":";
			for (int i = temp.indexOf(grouptempstart) + grouptempstart.length(), len = temp
					.length(); i < len; i++) {
				char ci = temp.charAt(i);
				if (ci == ']') {
					grouptempjson.append(temp.charAt(i));
					break;
				}
				grouptempjson.append(temp.charAt(i));
			}
			System.out.println(grouptempjson.toString());
			List<FansCount> fans = JSON.parseArray(grouptempjson.toString(),
					FansCount.class);
			if (null != fans && !fans.isEmpty()) {
				int count = 0;
				for (FansCount fan : fans) {
					System.out.println(fan.getName() + "======" + fan.getCnt());
					count = count + fan.getCnt();
				}
				System.out.println("目前粉丝总数为：" + count);
			}
			// 在取出用户的JSON数据
			StringBuffer friendtempjson = new StringBuffer();
			final String friendtempstart = "\"contacts\":";
			for (int i = temp.indexOf(friendtempstart)
					+ friendtempstart.length(), len = temp.length(); i < len; i++) {
				char ci = temp.charAt(i);
				if (ci == ']') {
					friendtempjson.append(temp.charAt(i));
					break;
				}
				friendtempjson.append(temp.charAt(i));
			}
			System.out.println(friendtempjson.toString());
			List<FansJson> friends = JSON.parseArray(friendtempjson.toString(),
					FansJson.class);
			if (null != friends && !friends.isEmpty()) {
				for (FansJson fan : friends) {
					System.out
							.println(fan.getId() + "======"
									+ fan.getNick_name() + "===="
									+ fan.getRemark_name() + "===="
									+ fan.getGroup_id());
				}
			}
			return friends;
		} catch (Exception e) {
			String info = "【解析粉丝数失败】 " + "\t\n【文本：】\t\n" + "\t\n" + "【发生异常："
					+ e.getMessage() + "】";
			System.err.println(info);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		WeixinMain weixin = new WeixinMain();
		FakeBean bean = weixin.weixin_login("info@t-lan.net", "Fly@2013",
				"ovK7Ijj0_dkEgNzQbfSgFoZGkyjQ", "1393918278");
		System.out.println(bean);
	}

}
