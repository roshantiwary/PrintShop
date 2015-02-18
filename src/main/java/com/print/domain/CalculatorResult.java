package com.print.domain;

public class CalculatorResult {
	
	private double totalPrice;
	private double extraPrice;
	private double printingPrice;
	private long noOfSheets;
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getExtraPrice() {
		return extraPrice;
	}
	public void setExtraPrice(double extraPrice) {
		this.extraPrice = extraPrice;
	}
	public long getNoOfSheets() {
		return noOfSheets;
	}
	public void setNoOfSheets(long noOfSheets) {
		this.noOfSheets = noOfSheets;
	}
	public double getPrintingPrice() {
		return printingPrice;
	}
	public void setPrintingPrice(double printingPrice) {
		this.printingPrice = printingPrice;
	}
}
