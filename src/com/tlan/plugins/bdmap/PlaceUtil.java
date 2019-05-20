package com.tlan.plugins.bdmap;

import java.util.Map;

import net.sf.json.JSONObject;

import com.google.gxp.com.google.common.collect.Maps;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;
import com.tlan.plugins.sms.HttpClientUtil;

/**
 * 百度地图API
 * 
 * @PackageName:com.tlan.plugins.bdmap
 * @ClassName:PlaceUtil
 * @Description:TODO(百度地图API)
 * @author magenming@tlan.com.cn
 * @date 2014年2月26日 下午12:58:40
 * 
 */
public class PlaceUtil {

	/**
	 * 获取位置信息
	 * 
	 * @Title: getPoint
	 * @Description: TODO
	 * @param @param place
	 * @param @param city
	 * @param @return
	 * @return PlaceBaseBean
	 * @throws
	 * @Date 2014年2月26日 下午1:56:34
	 */
	public static PlaceBaseBean getPoint(String place, String city) {

		if (DataUtils.isNotNullOrEmpty(place)) {
			try {
				String url = PropertiesUtil
						.getProperty(ParamString.BDMAP_PLACE);
				url = url.replace("{1}", place.replace(" ", ""));// 去除地址中的空格
				url = url.replace("{2}", null == city ? "" : city);
				// System.out.println(url);
				Map classMap = Maps.newHashMap();
				classMap.put("location", Location.class);
				classMap.put("result", PlaceResultBean.class);

				String res = HttpClientUtil.get(url);
				JSONObject data = JSONObject.fromObject(res);
				if (data.getInt("status") == 0) {
					return (PlaceBaseBean) JSONObject.toBean(data,
							PlaceBaseBean.class, classMap);
				}
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}

}
