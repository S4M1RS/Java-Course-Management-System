package com.coursemanagement.user;

public class StudentInfo {
	private int id;
	private String level;
	private int courseId;
	public String courseCode;

	public StudentInfo(int id, String level, int courseId) {
		this.id = id;
		this.level = level;
		this.courseId = courseId;
	}

	public StudentInfo(String level, int courseId) {
		this.level = level;
		this.courseId = courseId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getCourse() {
		return this.courseId;
	}

	public void setCourse(int course) {
		this.courseId = course;
	}
}
