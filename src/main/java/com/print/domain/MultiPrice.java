package com.print.domain;

public class MultiPrice implements ExtraTypePrice{
	
	private String extraItemType;
	
	private double highThresholdPrice;

	private long thresholdQty;
	
	private double lowThresholdPrice;

	public String getExtraItemType() {
		return extraItemType;
	}

	public void setExtraItemType(String extraItemType) {
		this.extraItemType = extraItemType;
	}

	public long getThresholdQty() {
		return thresholdQty;
	}

	public void setThresholdQty(long thresholdQty) {
		this.thresholdQty = thresholdQty;
	}

	public double getLowThresholdPrice() {
		return lowThresholdPrice;
	}

	public void setLowThresholdPrice(double lowThresholdPrice) {
		this.lowThresholdPrice = lowThresholdPrice;
	}

	public double getHighThresholdPrice() {
		return highThresholdPrice;
	}

	public void setHighThresholdPrice(double highThresholdPrice) {
		this.highThresholdPrice = highThresholdPrice;
	}
}
