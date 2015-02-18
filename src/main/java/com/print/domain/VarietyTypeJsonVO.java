package com.print.domain;

import java.util.Set;

public class VarietyTypeJsonVO {

	private Set<SizeUp> sizeUp;
	
	private String varietyId;
	
	private String varietyName;
	
	
	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public String getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(String varietyId) {
		this.varietyId = varietyId;
	}

	public String getVarTypeName() {
		return varTypeName;
	}

	public void setVarTypeName(String varTypeName) {
		this.varTypeName = varTypeName;
	}

	private String varTypeName;
	

	public Set<SizeUp> getSizeUp() {
		return sizeUp;
	}

	public void setSizeUp(Set<SizeUp> sizeUp) {
		this.sizeUp = sizeUp;
	}
	
}
