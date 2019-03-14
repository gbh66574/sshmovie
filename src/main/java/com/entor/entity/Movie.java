package com.entor.entity;

public class Movie {
	private int id;
	private String name;
	private int type;
	private String content;
	private int price;
	private String photo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", type=" + type + ", content=" + content + ", price=" + price
				+ ", photo=" + photo + "]";
	}
	public Movie(int id, String name, int type, String content, int price, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.price = price;
		this.photo = photo;
	}
	public Movie() {
		super();
	}
	
}
