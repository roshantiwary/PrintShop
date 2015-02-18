package com.print.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ExtraType {

	@Id
	private String id;
	private String name;
	private String parentExtraName;
	private String customerTypeName;
	
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	private ExtraTypePrice extraTypePrice;
	
	public ExtraTypePrice getExtraTypePrice() {
		return extraTypePrice;
	}
	public void setExtraTypePrice(ExtraTypePrice extraTypePrice) {
		this.extraTypePrice = extraTypePrice;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentExtraName() {
		return parentExtraName;
	}
	public void setParentExtraName(String parentExtraName) {
		this.parentExtraName = parentExtraName;
	}
	
}
