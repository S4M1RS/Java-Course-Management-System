package com.coursemanagement.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.coursemanagement.database.Database;
import com.coursemanagement.gui.DisplayMessage;
import com.mysql.cj.xdevapi.Statement;

public class Admin extends User {
	public Admin(int userId, String name, String email, String role) {
		super(userId, name, email, "", role);
	}

	public String getCount(String input) {
		try {
			Connection connect = new Database().getConnection();
			String type = null;
			String query = "";
			if (input.equals("courses")) {
				query = "SELECT COUNT(*) as count FROM courses WHERE active=?";
				type = "1";
			} else if (input.equals("students")) {
				query = "SELECT COUNT(*) as count FROM users WHERE user_role=?";
				type = "student";
			} else if (input.equals("instructors")) {
				query = "SELECT COUNT(*) as count FROM users WHERE user_role=?";
				type = "instructor";
			} else if (input.equals("modules")) {
				query = "SELECT COUNT(*) as count FROM modules WHERE course_id=?";
				type = "BIT";
			}
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, type);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("count"));
				return rs.getString("count");
			}
		} catch (SQLException ex) {
			ex.getMessage();
		}
		return null;
	}

	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<>();

		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT users.user_id, users.user_fullname, users.user_email, users.user_role, courses.course_code,studentinfo.student_level, studentinfo.course_id FROM users LEFT JOIN studentinfo ON users.user_id = studentinfo.user_id LEFT JOIN courses ON studentinfo.course_id = courses.course_id WHERE users.user_role = 'student'";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("user_id");
				String name = resultSet.getString("user_fullname");
				String email = resultSet.getString("user_email");
				String role = resultSet.getString("user_role");
				String level = resultSet.getString("student_level");
				int course = resultSet.getInt("course_id");
				String courseCode = resultSet.getString("course_code");
				Student a = new Student(id, name, email, role, new StudentInfo(id, level, course));
				a.getStudentInfo().courseCode = courseCode;
				students.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}
	
	public List<Student> getStudent(int userId) {
		List<Student> students = new ArrayList<>();
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT users.user_id, users.user_fullname, users.user_email, courses.course_code, studentinfo.student_level, studentinfo.course_id FROM users LEFT JOIN studentinfo ON users.user_id = studentinfo.user_id LEFT JOIN courses ON studentinfo.course_id = courses.course_id WHERE users.user_id = ?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("user_id");
				String name = resultSet.getString("user_fullname");
				String email = resultSet.getString("user_email");
				String level = resultSet.getString("student_level");
				int course = resultSet.getInt("course_id");
				String courseCode = resultSet.getString("course_code");
				
				Student a = new Student(id, name, email, "student", new StudentInfo(id, level, course));
				a.getStudentInfo().courseCode = courseCode;
				students.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public List<Instructor> getInstructor(int userId) {
		List<Instructor> instructors = new ArrayList<>();
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT users.user_id, users.user_fullname, users.user_email FROM users WHERE users.user_id = ?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("user_id");
				String name = resultSet.getString("user_fullname");
				String email = resultSet.getString("user_email");
				InstructorInfo.moduleCodes = getInstructorModules(id);
				String moduleCodes = "";
				for (int i = 0; i < InstructorInfo.moduleCodes.size(); i++) {
				      moduleCodes = moduleCodes+" "+InstructorInfo.moduleCodes.get(i);
				 }
				Instructor a = new Instructor(id,name,email,"","instructor", new InstructorInfo(id));
				a.getInstructorinfo().setModuleCodeString(moduleCodes);
				instructors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instructors;
	}

	public void addNewCourse(String courseId, String courseName, boolean isActive) {
		Course course = new Course(courseId, courseName, isActive);
		try {
			Connection connect = new Database().getConnection();
			String query = "INSERT INTO courses (course_id, course_name, active) VALUES (?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, course.getCourseId());
			stmt.setString(2, course.getCourseName());
			if (course.isActive() == true) {
				stmt.setInt(3, 1);
			} else {
				stmt.setInt(3, 0);
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addStudent(int userId, String fullName, String email, String password) {
		try {
			Connection connect = new Database().getConnection();
			String query = "INSERT INTO users (user_id, user_fullname, user_email, user_password, user_role) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setString(2, fullName);
			stmt.setString(3, email);
			stmt.setString(4, password);
			stmt.setString(5, "student");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DisplayMessage.showSuccessMessage("Student added successfully");
	}
	public void deleteUser(int userId) {
		try {
			Connection connect = new Database().getConnection();
			String query = "DELETE FROM users where user_id=?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DisplayMessage.showSuccessMessage("Deleted successfully");
	}

	public void updateStudent(int userId, String fullName, String email, String level, int courseId) {
		// TODO Auto-generated method stub
		try {
			Connection connect = new Database().getConnection();
			String query = "UPDATE users SET user_fullname=?, user_email=? WHERE user_id = ?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, fullName);
			stmt.setString(2, email);
			stmt.setInt(3, userId);
			stmt.executeUpdate();
			
			query = "UPDATE studentinfo SET course_id = ?, student_level=? WHERE user_id = ?";
			stmt = connect.prepareStatement(query);
			stmt.setInt(1, courseId);
			stmt.setString(2, level);
			stmt.setInt(3, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateInstructor(int userId, String fullName, String email) {
		try {
			Connection connect = new Database().getConnection();
			String query = "UPDATE users SET user_fullname = ?, user_email = ? WHERE user_id = ?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, fullName);
			stmt.setString(2, email);
			stmt.setInt(3, userId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Instructor> getAllInstructors() {

		List<Instructor> instructors = new ArrayList<>();

		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT users.user_id, users.user_fullname, users.user_email, users.user_role FROM users WHERE users.user_role = 'instructor'";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("user_id");
				String name = resultSet.getString("user_fullname");
				String email = resultSet.getString("user_email");
				String role = resultSet.getString("user_role");
				InstructorInfo.moduleCodes = getInstructorModules(id);
				String moduleCodes = "";
				for (int i = 0; i < InstructorInfo.moduleCodes.size(); i++) {
				      moduleCodes = moduleCodes+" "+InstructorInfo.moduleCodes.get(i);
				 }
				Instructor instructor = new Instructor(id,name,email,"",role, new InstructorInfo(id));
				instructor.getInstructorinfo().setModuleCodeString(moduleCodes);
				instructors.add(instructor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instructors;
	}
	
	public ArrayList<String> getInstructorModules(int userId){
		ArrayList<String> modules = new ArrayList<>();
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT instructorinfo.module_id, modules.module_code FROM instructorinfo LEFT JOIN modules ON instructorinfo.module_id=modules.module_id WHERE instructorinfo.user_id=?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String moduleCode = rs.getString("module_code");
				modules.add(moduleCode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return modules;
	}
	
	public void getStudentReportData(JTable table, int id) {
		String query = "select users.user_fullname,modules.module_name,studentreport.marks from users left join studentreport on users.user_id = studentreport.user_id left join modules on modules.module_id=studentreport.module_id where users.user_id =?;";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
        	Connection connect = new Database().getConnection();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] colName = new String[colCount];
            for (int i = 0; i < colCount; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
            }
            while (res.next()) {
                String student = String.valueOf(res.getString("user_fullname"));
                String marks = String.valueOf(res.getString("marks"));
                String module = String.valueOf(res.getString("module_name"));
                String[] tbData = { student, module, marks };
                System.out.println(res.getString("user_fullname"));
                model.addRow(tbData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public HashMap<String,ArrayList<String>> getCourseData() {
		HashMap<String,ArrayList<String>> data = new HashMap<String,ArrayList<String>>();
		ArrayList<String> course_name = new ArrayList<>();
		ArrayList<String> course_code = new ArrayList<>();
		String query = "SELECT * FROM COURSES";
		try {
			Connection connect = new Database().getConnection();
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				course_name.add(res.getString("course_name"));
				course_code.add(res.getString("course_code"));
			}
			data.put("course_name",course_name);
			data.put("course_code", course_code);
		}catch(SQLException e) {
			
		}
		return data;
	}
	
	public void addInstructor(int userId, String fullName, String email, String password) {
		try {
			Connection connect = new Database().getConnection();
			String query = "INSERT INTO users (user_id, user_fullname, user_email, user_password, user_role) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setInt(1, userId);
			stmt.setString(2, fullName);
			stmt.setString(3, email);
			stmt.setString(4, password);
			stmt.setString(5, "instructor");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DisplayMessage.showSuccessMessage("Instructor added successfully");
	}
}
