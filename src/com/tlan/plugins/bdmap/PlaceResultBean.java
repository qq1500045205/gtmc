package com.tlan.plugins.bdmap;

/**
 * 根据位置获取坐标（经纬度坐标）
 * 
 * @PackageName:com.tlan.plugins.bdmap
 * @ClassName:PlaceBean
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月26日 下午1:26:10
 * 
 */
public class PlaceResultBean {

	/**
	 * 经纬度坐标
	 */
	private Location location;
	/**
	 * 位置的附加信息，是否精确查找。1为精确查找，0为不精确。
	 */
	private String precise;
	/**
	 * 可信度
	 */
	private int confidence;
	/**
	 * 地址类型,如：道路，商务大厦
	 */
	private String level;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPrecise() {
		return precise;
	}

	public void setPrecise(String precise) {
		this.precise = precise;
	}

	public int getConfidence() {
		return confidence;
	}

	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "PlaceBean [location=[" + location.getLat() + ","
				+ location.getLng() + "]" + ", precise=" + precise
				+ ", confidence=" + confidence + ", level=" + level + "]";
	}

}
