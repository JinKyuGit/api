package com.java.main.bo;

public class ScheduleBo extends CommonBo {
	
	private String today;
	private String yesterday;
	private int currentHour;
	private String tomorrow;
	private String job;
	
	
	
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public int getCurrentHour() {
		return currentHour;
	}
	public void setCurrentHour(int currentHour) {
		this.currentHour = currentHour;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getTomorrow() {
		return tomorrow;
	}
	public void setTomorrow(String tomorrow) {
		this.tomorrow = tomorrow;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	

	
}
