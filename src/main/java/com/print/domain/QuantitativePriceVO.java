package com.print.domain;

public class QuantitativePriceVO {

	private String extraParentType;
	private String extraItemType;
	private String extraItem;
	
	private long minQty;
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
	public long getMinQty() {
		return minQty;
	}
	public void setMinQty(long minQty) {
		this.minQty = minQty;
	}
	public double getPricePerItem() {
		return pricePerItem;
	}
	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	private double pricePerItem;
	private double minPrice;
	
}
