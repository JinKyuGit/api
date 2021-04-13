package com.java.main.bo;

import java.io.Serializable;

public class CommonBo implements Serializable {
	
	protected String userId;
	protected String sysDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	
	

}
