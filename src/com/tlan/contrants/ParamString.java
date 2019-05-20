package com.tlan.contrants;

/**
 * 常量
 * 
 * @author magenm
 * 
 */
public class ParamString {

	public final static String GET_TOKEN_URL = "get_token_url";
	public final static String CREATE_MENU = "create_menu";
	public final static String TEMPLATE_MESSAGE = "template_message";
	public final static String ENCODING = "encoding";
	public final static String BASE_IP = "base_ip";
	public final static String GCLOUD_IP = "gcloud_ip";
	public static final String ACCESS_TOKEN = "access_token";
	public final static String QRCODE_URL = "qrcode_url";
	public static final String UPLOAD_FILE_PATH = "upload_file_path";
	public static final String UPLOAD_VIDEO_PATH = "upload_video_path";
	public static final String UPLOAD_MUSIC_PATH = "upload_music_path";
	public static final String UPLOAD_XLS_PATH = "upload_xls_path";
	public static final String IMAGE_MAX_SIZE = "image_max_size";
	public static final String VIDEO_MAX_SIZE = "video_max_size";
	public static final String XLS_MAX_SIZE = "xls_max_size";
	public static final String RESOURCES_PATH = "resources_path";
	public static final String API_BASE_URL = "api_base_url";
	public static final String BSEE_PATH = "base_path";
	public static final String WX_SCOPE = "wx_scope";
	public static final String WX_USER_INFO = "wx_user_info";

	public static final String IMAGE_TYPE = "image_type";
	public static final String VIDEO_TYPE = "video_type";
	public static final String MUSIC_TYPE = "music_type";
	public static final String XLS_TYPE = "xls_type";

	public static final String GZH_STATUS_DEPLOYED = "已发布";
	public static final String GZH_STATUS_DELETED = "已删除";
	public static final String GZH_STATUS_STOPPED = "已暂停";
	public static final String GZH_STATUS_WAITING = "待发布";

	public static final String USER_PRIVILEGE_ADMINISTRATOR = "0"; // 系统管理员
	public static final String USER_PRIVILEGE_AREA_MANAGER = "1"; // 地区管理员
	public static final String USER_PRIVILEGE_PROJECT_MANAGER = "2";// 项目管理员
	public static final String USER_PRIVILEGE_GZH_MANAGER = "3"; // 公共员管理员

	public static final String USER_LOGIN_SESSION_CHECK_TAG = "userlogin"; // 判断用户登陆
	public static final String USER_LOGIN_SESSION_LOGIN_NAME = "loginname"; // 用户登陆名
	public static final String USER_LOGIN_SESSION_DISPLAY_NAME = "displayname"; // 用户显示名

	// 用户权限
	public static final int USER_PRIVILEGE_ADMIN = 0;
	public static final int USER_PRIVILEGE_AREA = 1;
	public static final int USER_PRIVILEGE_PROJECT = 2;
	public static final int USER_PRIVILEGE_WX = 3;

	public static final String USER_LOGIN_SESSION_OBJECT = "loginObject"; // 保存登录信息

	public static final String WX_USER_SESSION_OBJECT = "wx-user-session-object";

	public static final String SMS_VALIDATION_CODE = "validation_code";

	public static final String TELEPHONE = "telephone";

	/*
	 * 验证码
	 */
	public static final String VALIDATE_CODE = "validatecode";
	/**
	 * 违章查询
	 */
	public static final String LLLAGEL_APPID = "lllegal_appid";
	public static final String LLLAGEL_APPKEY = "lllegal_appkey";
	/**
	 * 聊天配置
	 */
	public static final String OPENFIRE_PASSWORD = "openfire_password";
	public static final String OPENFIRE_HOST = "openfire_host";
	public static final String OPENFIRE_PORT = "openfire_port";
	public static final String OPENFIRE_SERVER = "openfire_server";
	/**
	 * GTMC(gcloud sms tact) 配置
	 */
	public static final String GTMC_USER_ID = "gtmc_user_id";
	public static final String GCLOUD_WS_URL = "gcloud_ws_url";
	public static final String GCLOUD_LOGIN_URL = "gcloud_login_url";
	public static final String MEDIA_CODE_BAOYANG = "media_code_baoyang";
	public static final String MEDIA_CODE_SHIJIA = "media_code_shijia";
	public static final String CONDUCT_TYPE = "conduct_type";
	public static final String GCLOUD_SMS_WS_URL = "gcloud_sms_ws_url";
	public static final String GCLOUD_SMS_WS_SIGN = "gcloud_sms_ws_sign";
	/**
	 * 百度地图api
	 */
	public static final String BDMAP_PLACE = "bdmap_place";
}
