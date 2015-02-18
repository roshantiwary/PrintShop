package com.print.domain;

public class BasePriceVO {

	private String extraParentType;
	private String extraItemType;
	private String extraItem;
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
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	private double basePrice;
	
}
