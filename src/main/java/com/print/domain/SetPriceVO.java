package com.print.domain;

public class SetPriceVO {

	private String extraParentType;
	private String extraItemType;
	private String extraItem;
	
	private long itemPerSet;
	private double pricePerSet;
	
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
	public long getItemPerSet() {
		return itemPerSet;
	}
	public void setItemPerSet(long itemPerSet) {
		this.itemPerSet = itemPerSet;
	}
	public double getPricePerSet() {
		return pricePerSet;
	}
	public void setPricePerSet(double pricePerSet) {
		this.pricePerSet = pricePerSet;
	}
}
