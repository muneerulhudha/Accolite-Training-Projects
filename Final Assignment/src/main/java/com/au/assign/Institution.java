package com.au.assign;

import java.util.Set;


public class Institution {

	String title;
	String description;
	String location;
	String branches;
	String url;
	private Set<Course> courses;
	
	Institution() {

	}

	Institution(String title, String description, String location,
			String branches, String url) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.branches = branches;
		this.url = url;

	}
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBranches() {
		return branches;
	}

	public void setBranches(String branches) {
		this.branches = branches;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}