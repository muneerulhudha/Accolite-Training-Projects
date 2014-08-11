package com.au.assign;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "courses")
public class Courses {
	
		@XmlElement(name = "course", type = Course.class)
		private List<Course> courses = new ArrayList<Course>();
	
		public Courses() {
	
		}
	
		public Courses(List<Course> courses) {
			this.courses = courses;
		}
	
		public List<Course> getCourses() {
			return courses;
		}
	
		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
	
}
