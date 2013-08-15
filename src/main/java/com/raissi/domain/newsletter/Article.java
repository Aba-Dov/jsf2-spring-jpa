package com.raissi.domain.newsletter;

import java.io.Serializable;

public class Article implements Serializable{
	private static final long serialVersionUID = 2999207145055407788L;

	private String title;
	private String image;
	private String description;
	
	public Article() {
		super();
	}
	public Article(String title, String image, String description) {
		super();
		this.title = title;
		this.image = image;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
