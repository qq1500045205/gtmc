package com.tlan.plugins.bdmap;

public class PlaceBaseBean {
	/**
	 * 0 正常 1 服务器内部错误 2 请求参数非法 3 权限校验失败 4 配额校验失败 5 ak不存在或者非法 101 服务禁用 102
	 * 不通过白名单或者安全码不对 2xx 无权限 3xx 配额错误
	 */
	private int status;
	/**
	 * 结果数据
	 */
	private PlaceResultBean result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PlaceResultBean getResult() {
		return result;
	}

	public void setResult(PlaceResultBean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PlaceBaseBean [status=" + status + ", result=" + result + "]";
	}

}
