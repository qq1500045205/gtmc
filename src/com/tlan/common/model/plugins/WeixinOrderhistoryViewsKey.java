package com.tlan.common.model.plugins;

/**
 * 复合主键
 * 
 * @PackageName:com.tlan.common.model
 * @ClassName:WeixinCustomerViewKey
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月28日 上午11:14:14
 * 
 */
public class WeixinOrderhistoryViewsKey implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private String dealercode;
	private String orderno;

	public String getDealercode() {
		return dealercode;
	}

	public void setDealercode(String dealercode) {
		this.dealercode = dealercode;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderno == null) ? 0 : orderno.hashCode());
		result = prime * result
				+ ((dealercode == null) ? 0 : dealercode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeixinOrderhistoryViewsKey other = (WeixinOrderhistoryViewsKey) obj;
		if (orderno == null) {
			if (other.orderno != null)
				return false;
		} else if (!orderno.equals(other.orderno))
			return false;
		if (dealercode == null) {
			if (other.dealercode != null)
				return false;
		} else if (!dealercode.equals(other.dealercode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeixinCustomerViewKey [dealercode=" + dealercode
				+ ", customercode=" + orderno + "]";
	}

}
