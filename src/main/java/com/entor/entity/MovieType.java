package com.entor.entity;

public class MovieType {
private int id;
private String name;
private String content;
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
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "MovieType [id=" + id + ", name=" + name + ", content=" + content + "]";
}
public MovieType(int id, String name, String content) {
	super();
	this.id = id;
	this.name = name;
	this.content = content;
}
public MovieType() {
	super();
}

}
