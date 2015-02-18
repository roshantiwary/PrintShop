package com.print.domain;

public class MultiPriceVO {

	private String extraParentType;
	private String extraItemType;
	private String extraItem;
	
	private long thresholdQty;
	private double lowPrice;
	private double highPrice;
	
	private String customerType;
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getExtraParentType() {
		return extraParentType;
	}
	public void setExtraParentType(String extraParentType) {
		this.extraParentType = extraParentType;
	}
	public String getExtraItemType() {
		return extraItemType;
	}
	public void setExtraItemType(String extraItemType) {
		this.extraItemType = extraItemType;
	}
	public String getExtraItem() {
		return extraItem;
	}
	public void setExtraItem(String extraItem) {
		this.extraItem = extraItem;
	}
	public long getThresholdQty() {
		return thresholdQty;
	}
	public void setThresholdQty(long thresholdQty) {
		this.thresholdQty = thresholdQty;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
}
