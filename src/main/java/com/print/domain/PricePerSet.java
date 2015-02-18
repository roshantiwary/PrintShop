package com.print.domain;

public class PricePerSet implements ExtraTypePrice {
	
	private String extraItemType;
	
	private double pricePerSet;
	
	private long itemPerSet;

	public String getExtraItemType() {
		return extraItemType;
	}

	public void setExtraItemType(String extraItemType) {
		this.extraItemType = extraItemType;
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
