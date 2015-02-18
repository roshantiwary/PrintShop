package com.print.domain;

public class PriceJSONVO {
	
	public double getEachPrice() {
		return eachPrice;
	}

	public void setEachPrice(double eachPrice) {
		this.eachPrice = eachPrice;
	}

	public double getPriceAbove10() {
		return priceAbove10;
	}

	public void setPriceAbove10(double priceAbove10) {
		this.priceAbove10 = priceAbove10;
	}

	public double getPriceAbove50() {
		return priceAbove50;
	}

	public void setPriceAbove50(double priceAbove50) {
		this.priceAbove50 = priceAbove50;
	}

	public double getPriceAbove100() {
		return priceAbove100;
	}

	public void setPriceAbove100(double priceAbove100) {
		this.priceAbove100 = priceAbove100;
	}

	public double getPriceAbove200() {
		return priceAbove200;
	}

	public void setPriceAbove200(double priceAbove200) {
		this.priceAbove200 = priceAbove200;
	}

	public double getPriceAbove500() {
		return priceAbove500;
	}

	public void setPriceAbove500(double priceAbove500) {
		this.priceAbove500 = priceAbove500;
	}
	
	public String getPricingId() {
		return pricingId;
	}

	public void setPricingId(String pricingId) {
		this.pricingId = pricingId;
	}

	private String pricingId;
	
	private double eachPrice;
		
	private double priceAbove10;
	
	private double priceAbove50;
	
	private double priceAbove100;
	
	private double priceAbove200;
	
	private double priceAbove500;
}
