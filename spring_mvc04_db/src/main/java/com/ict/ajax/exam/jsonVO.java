package com.ict.ajax.exam;

public class jsonVO {
	private String city;
	private long totalCount, firstCount,  secondCount;
	private double firstPercent, secondPercent;
	
	public jsonVO(String city, long totalCount, long firstCount, long secondCount, double firstPercent,
			double secondPercent) {
		super();
		this.city = city;
		this.totalCount = totalCount;
		this.firstCount = firstCount;
		this.secondCount = secondCount;
		this.firstPercent = firstPercent;
		this.secondPercent = secondPercent;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getFirstCount() {
		return firstCount;
	}
	
	public void setFirstCount(long firstCount) {
		this.firstCount = firstCount;
	}
	
	public long getSecondCount() {
		return secondCount;
	}
	
	public void setSecondCount(long secondCount) {
		this.secondCount = secondCount;
	}
	
	public double getFirstPercent() {
		return firstPercent;
	}
	
	public void setFirstPercent(double firstPercent) {
		this.firstPercent = firstPercent;
	}
	
	public double getSecondPercent() {
		return secondPercent;
	}
	
	public void setSecondPercent(double secondPercent) {
		this.secondPercent = secondPercent;
	}
}
