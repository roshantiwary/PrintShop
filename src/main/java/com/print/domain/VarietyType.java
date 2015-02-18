package com.print.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VarietyType {
	
	@Id
	private String id;
	
	private String name;
	
	private Variety variety;
	
	public Variety getVariety() {
		return variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

	private Set<SizeUp> sizeUp;

	public Set<SizeUp> getSizeUp() {
		return sizeUp;
	}

	public void setSizeUp(Set<SizeUp> sizeUp) {
		this.sizeUp = sizeUp;
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
	
}
