package com.coursemanagement.user;

public class Module {
	private String moduleId;
	private String moduleName;
	private int moduleLevel;
	private boolean isOptional;
	private String courseId;
	
	public Module(String moduleId, String moduleName, int moduleLevel, boolean isOptional, String courseId) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleLevel = moduleLevel;
		this.isOptional = isOptional;
		this.courseId = courseId;
	}
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public int getModuleLevel() {
		return moduleLevel;
	}
	public void setModuleLevel(int moduleLevel) {
		this.moduleLevel = moduleLevel;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public boolean isOptional() {
		return isOptional;
	}
	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
