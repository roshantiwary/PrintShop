package com.print.domain;

public class BasePrice implements ExtraTypePrice{
	
	private String extraItemType;
	
	private double price;

	public String getExtraItemType() {
		return extraItemType;
	}

	public void setExtraItemType(String extraItemType) {
		this.extraItemType = extraItemType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
