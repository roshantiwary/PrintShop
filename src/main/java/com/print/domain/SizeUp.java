package com.print.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SizeUp {

	@Id
	private String id;
	
	private String name;
	
	@Override
	public String toString() {
		return "SizeUp [id=" + id + ", name=" + name + ", width_up=" + width_up
				+ ", heigh_up=" + height_up + "]";
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

	public double getWidth_up() {
		return width_up;
	}

	public void setWidth_up(double width_up) {
		this.width_up = width_up;
	}

	public double getHeight_up() {
		return height_up;
	}

	public void setHeight_up(double height_up) {
		this.height_up = height_up;
	}

	private double width_up;
	
	private double height_up;
	
}
