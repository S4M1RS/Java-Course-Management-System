package com.coursemanagement.user;

import java.util.ArrayList;

public class InstructorInfo {
	private int userId;
	public static ArrayList<String> moduleCodes;
	private String moduleCodeString;

	public InstructorInfo(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return this.userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getModuleCodeString() {
		return moduleCodeString;
	}

	public void setModuleCodeString(String moduleCodeString) {
		this.moduleCodeString = moduleCodeString;
	}

}
