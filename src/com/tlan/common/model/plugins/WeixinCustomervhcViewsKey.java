package com.tlan.common.model.plugins;

/**
 * 
 * @PackageName:com.tlan.common.model
 * @ClassName:WeixinCustomervhcViewsKey
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年2月28日 上午11:16:51
 * 
 */
public class WeixinCustomervhcViewsKey implements java.io.Serializable {

	private static final long serialVersionUID = -4680174009133186599L;
	
	private String dealercode;
	private String registerno;
	private String vinno;

	public String getDealercode() {
		return dealercode;
	}

	public void setDealercode(String dealercode) {
		this.dealercode = dealercode;
	}

	public String getRegisterno() {
		return registerno;
	}

	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}

	public String getVinno() {
		return vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dealercode == null) ? 0 : dealercode.hashCode());
		result = prime * result
				+ ((registerno == null) ? 0 : registerno.hashCode());
		result = prime * result + ((vinno == null) ? 0 : vinno.hashCode());
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
		WeixinCustomervhcViewsKey other = (WeixinCustomervhcViewsKey) obj;
		if (dealercode == null) {
			if (other.dealercode != null)
				return false;
		} else if (!dealercode.equals(other.dealercode))
			return false;
		if (registerno == null) {
			if (other.registerno != null)
				return false;
		} else if (!registerno.equals(other.registerno))
			return false;
		if (vinno == null) {
			if (other.vinno != null)
				return false;
		} else if (!vinno.equals(other.vinno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeixinCustomervhcViewsKey [dealercode=" + dealercode
				+ ", registerno=" + registerno + ", vinno=" + vinno + "]";
	}

}
