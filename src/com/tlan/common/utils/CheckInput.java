/**
 * @File:CheckInput.java
 * @Package cn.com.canon.qrc.utils
 * @Description:TODO
 * @Copyright:Copyright(c)2012
 * @Company:Canon
 * @author:chengcheng
 * @date:2012-11-20 下午2:51:08
 * @version:V1.0
 */

package com.tlan.common.utils;

import com.tlan.contrants.ParamString;

/**
 * @ClassName:CheckInput
 * @Description:TODO
 * @author:chengcheng
 * @date:2012-11-20 下午2:51:08
 */
public class CheckInput {

	/**
	 * 
	 * @Title:checkName
	 * @Description:检查用户名是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @return boolean
	 * @throws
	 */
	public static boolean checkName(String str, String reg) {
		boolean bret = true;
		if (!str.matches(reg)) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;
	}

	/**
	 * 
	 * @Title:checkName
	 * @Description:检查用户名是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @param len
	 *            限制的长度
	 * @return boolean
	 * @throws
	 */
	public static boolean checkName(String str, String reg, int lenmin,
			int lenmax) {
		boolean bret = true;
		if (null == (str) || "".equals(str.trim())) {
			bret = false;
		} else if (!str.matches(reg)) {
			bret = false;
		} else if (lenmax < str.length() || lenmin > str.length()) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;
	}

	/**
	 * 
	 * @Title:checkNames
	 * @Description:检查用户名是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @param len
	 *            限制的长度
	 * @return String 返回消息
	 * @throws
	 */
	public static String checkNames(String str, String reg, int lenmin,
			int lenmax) {
		String loginf = "";
		if (!str.matches(reg)) {
			loginf = "格式不正确！";
		} else if (lenmax < str.length() || lenmin > str.length()) {
			loginf = "长度不在" + lenmin + "-" + lenmax + "之内！";
		} else {
			loginf = null;
		}
		return loginf;
	}

	/**
	 * 
	 * @Title:checkPwd
	 * @Description:检查密码是否合法
	 * @param str
	 *            需要验证的字符
	 * @return boolean
	 * @throws
	 */
	public static boolean checkPwd(String str) {
		boolean bret = true;
		return bret;
	}

	/**
	 * 
	 * @Title:checkPwd
	 * @Description:检查密码是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @return boolean
	 * @throws
	 */
	public static boolean checkPwd(String str, String reg) {
		boolean bret = true;
		return bret;
	}

	/**
	 * 
	 * @Title:checkPwd
	 * @Description:检查密码是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @param len
	 *            密码长度
	 * @return boolean
	 * @throws
	 */
	public static boolean checkPwd(String str, String reg, int lenmin,
			int lenmax) {
		boolean bret = true;

		if (null == (str) || "".equals(str.trim())) {
			bret = false;
		} else if (!str.matches(reg)) {
			bret = false;
		} else if (lenmax < str.length() || lenmin > str.length()) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;

	}

	/**
	 * 
	 * @Title:checkPwds
	 * @Description:检查密码是否合法
	 * @param str
	 *            需要验证的字符
	 * @param reg
	 *            正则表达式
	 * @param len
	 *            密码长度
	 * @return String 返回消息
	 * @throws
	 */
	public static String checkPwds(String str, String reg, int lenmin,
			int lenmax) {
		String loginf = "";

		if (!str.matches(reg)) {
			loginf = "密码格式不正确！";
		} else if (lenmax < str.length() || lenmin > str.length()) {
			loginf = "密码长度不在" + lenmin + "-" + lenmax + "之内！";
		} else {
			loginf = null;
		}
		return loginf;
	}

	/**
	 * 
	 * @Title:checkTel
	 * @Description:检查代码是否合法
	 * @param str
	 *            检查的字符
	 * @param reg
	 *            正则表达式
	 * @return boolean
	 * @throws
	 */
	public static boolean checkTel(String str, String reg) {
		boolean bret = true;
		if (!str.matches(reg)) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;
	}

	/**
	 * 
	 * @Title:checkTel
	 * @Description:检查代码是否合法
	 * @param str
	 *            检查的字符
	 * @param reg
	 *            正则表达式
	 * @return boolean
	 * @throws
	 */
	public static boolean checkTel(String str) {
		String reg = PropertiesUtil.getProperty(ParamString.TELEPHONE);
		return checkTel(str, reg);
	}

	/**
	 * 
	 * @Title:checkTels
	 * @Description:TODO
	 * @param @param str
	 * @param @param reg
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String checkTels(String str, String reg) {
		String loginf = "";
		reg = PropertiesUtil.getProperty(ParamString.TELEPHONE);
		if (!str.matches(reg)) {
			loginf = "电话号码格式不正确!";
		} else {
			loginf = null;
		}
		return loginf;
	}

	/**
	 * 
	 * @Title:checkEmail
	 * @Description:TODO
	 * @param @param str
	 * @param @param reg
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean checkEmail(String str, String reg) {
		boolean bret = true;
		if (!str.matches(reg)) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;
	}

	/**
	 * 
	 * @Title:checkPostcode
	 * @Description:验证邮编
	 * @param @param str
	 * @param @param reg
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean checkPostcode(String str, String reg) {
		boolean bret = true;
		if (!str.matches(reg)) {
			bret = false;
		} else {
			bret = true;
		}
		return bret;
	}

	/**
	 * 
	 * @Title:checkLength
	 * @Description:检验字符串长度
	 * @param @param str
	 * @param @param reg
	 * @param @param lenmin
	 * @param @param lenmax
	 * @param @return
	 * @return int
	 * @throws
	 */
	public static boolean checkLength(String str, String lenmin, String lenmax) {
		if (str.length() < Integer.parseInt(lenmin)
				|| str.length() > Integer.parseInt(lenmax)) {
			return false;
		} else {
			return true;
		}

	}

}
