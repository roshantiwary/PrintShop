package com.print.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
	
	@Id
	private String id;
		
	public Role() {
		super();
	}
	
	public Role(String id) {
		super();
		this.setId(id);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}