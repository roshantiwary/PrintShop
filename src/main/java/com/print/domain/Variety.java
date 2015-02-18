package com.print.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Variety {
	@Id
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	
	//varPaper varSticker
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	//Paper, Sticker
	public void setName(String name) {
		this.name = name;
	}
}
