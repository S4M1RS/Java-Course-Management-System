package com.coursemanagement.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.coursemanagement.database.Database;

public class Course {
	private String courseId;
	private String courseName;
	private boolean isActive;
	
	public Course(String courseId, String courseName, boolean isActive) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.isActive = isActive;
	}
	
	public void addModule(String moduleId, String moduleName, int moduleLevel, boolean isOptional) {
		Module module = new Module(moduleId, moduleName, moduleLevel, isOptional, courseId);
		 try {
		    	Connection connect = new Database().getConnection();
				String query = "INSERT INTO modules (module_id, module_name, module_level, optional, course_id) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt = connect.prepareStatement(query);
				stmt.setString(1, module.getModuleId());
			    stmt.setString(2, module.getModuleName());
			    stmt.setInt(3, module.getModuleLevel());
			    if (module.isOptional() == true) {
			    	stmt.setInt(4, 1);
			    } else {
			    	stmt.setInt(4, 0);
			    }
			    stmt.setString(5, this.courseId);
				stmt.executeUpdate();
		 } catch (SQLException e) {
		      e.printStackTrace();
		 }
	}
	
	public void deleteModule(String moduleId) {
		Connection connect = new Database().getConnection();
		String query = "DELETE FROM modules WHERE course_id = ? and module_name = ?";
		try {
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, this.courseId);
		    stmt.setString(2, moduleId);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
