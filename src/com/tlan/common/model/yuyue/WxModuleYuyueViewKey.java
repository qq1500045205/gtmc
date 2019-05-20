package com.tlan.common.model.yuyue;

import javax.persistence.Embeddable;

/**
 * @author magenm email:magenm(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Embeddable
public class WxModuleYuyueViewKey implements java.io.Serializable {

	private static final long serialVersionUID = 8552312486698173404L;

	public WxModuleYuyueViewKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String yuyueGuid;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((yuyueGuid == null) ? 0 : yuyueGuid.hashCode());
		 
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
		WxModuleYuyueViewKey other = (WxModuleYuyueViewKey) obj;
		if(this.getYuyueGuid().equals(other.getYuyueGuid())){
			return true;
		}else{
			return false;
		}
	}

	public String getYuyueGuid() {
		return yuyueGuid;
	}

	public void setYuyueGuid(String yuyueGuid) {
		this.yuyueGuid = yuyueGuid;
	}

}
