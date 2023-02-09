package com.coursemanagement.user;


public class Instructor extends User{
	private InstructorInfo instructorinfo;
	public Instructor(int userId, String name, String email, String password, String role) {
		super(userId, name, email, password, role);
		// TODO Auto-generated constructor stub
	}
	public Instructor(int userId, String name, String email, String password, String role, InstructorInfo instructorinfo) {
		super(userId, name, email, password, role);
		// TODO Auto-generated constructor stub
		this.instructorinfo = instructorinfo;
	}
	public InstructorInfo getInstructorinfo() {
		return instructorinfo;
	}
	public void setInstructorinfo(InstructorInfo instructorinfo) {
		this.instructorinfo = instructorinfo;
	}
}
