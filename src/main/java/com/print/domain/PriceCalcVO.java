package com.print.domain;

import java.util.List;

public class PriceCalcVO {
	
    public String getCustTypeId() {
		return custTypeId;
	}
	public void setCustTypeId(String custTypeId) {
		this.custTypeId = custTypeId;
	}
	public String getVarTypeId() {
		return varTypeId;
	}
	public void setVarTypeId(String varTypeId) {
		this.varTypeId = varTypeId;
	}
	public String getSizeUpId() {
		return sizeUpId;
	}
	public void setSizeUpId(String sizeUpId) {
		this.sizeUpId = sizeUpId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getNumberOfUp() {
		return numberOfUp;
	}
	public void setNumberOfUp(int numberOfUp) {
		this.numberOfUp = numberOfUp;
	}
	public List<SelectedExtra> getSelectedExtraArray() {
		return selectedExtraArray;
	}
	public void setSelectedExtraArray(List<SelectedExtra> selectedExtraArray) {
		this.selectedExtraArray = selectedExtraArray;
	}
	public NumberOfUpJsonVO getNoOfUp() {
		return noOfUp;
	}
	public void setNoOfUp(NumberOfUpJsonVO noOfUp) {
		this.noOfUp = noOfUp;
	}
	
	private String custTypeId;
    private String varTypeId;
	private String sizeUpId;
	private int qty;
	private int numberOfUp;
	private List<SelectedExtra> selectedExtraArray;
	private NumberOfUpJsonVO noOfUp;
	
}
