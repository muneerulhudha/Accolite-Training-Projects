package com.au.assign;

public class Course {

	String courseName;
	String courseDescription;
	int duration;
	String admissionProcess;
	float eligibilityCriteria;
	Institution title;

	Course() {

	}

	Course(String courseName, String courseDescription,
			int duration, String admissionProcess, float eligibilityCriteria,
			Institution title) {
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.duration = duration;
		this.admissionProcess = admissionProcess;
		this.eligibilityCriteria = eligibilityCriteria;
		this.title = title;

	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAdmissionProcess() {
		return admissionProcess;
	}

	public void setAdmissionProcess(String admissionProcess) {
		this.admissionProcess = admissionProcess;
	}

	public float getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	public void setEligibilityCriteria(float eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	public Institution getTitle() {
		return title;
	}

	public void setTitle(Institution title) {
		this.title = title;
	}

}