package com.print.domain;

public class QuantitativePrice implements ExtraTypePrice{
	
	private String extraItemType;
	
	private long minimumQty;
	
	private double minimumPrice;
	
	private double pricePerItem;

	public long getMinimumQty() {
		return minimumQty;
	}

	public void setMinimumQty(long minimumQty) {
		this.minimumQty = minimumQty;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public double getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public String getExtraItemType() {
		return extraItemType;
	}

	public void setExtraItemType(String extraItemType) {
		this.extraItemType = extraItemType;
	}

}
