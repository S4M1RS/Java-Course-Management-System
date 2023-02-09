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

public class Student extends User{
	private StudentInfo studentInfo;
	public Student() {
		super("", "", "", "");
	}
	public Student(int userId,String name, String email, String role) {
		super(userId,name,email,"",role);
	}
	
	public Student(int userId,String name, String email, String role, StudentInfo studentInfo) {
		super(userId,name,email,"",role);
		this.setStudentInfo(studentInfo);
	}
	
	public Student(String name, String email, String password, String role, StudentInfo studentInfo) {
		super(name,email,password,role);
		this.setStudentInfo(studentInfo);
	}
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
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

	public HashMap<String,ArrayList<String>> getCourseData() {
		HashMap<String,ArrayList<String>> data = new HashMap<String,ArrayList<String>>();
		ArrayList<String> course_name = new ArrayList<>();
		ArrayList<String> course_code = new ArrayList<>();
		String query = "SELECT * FROM COURSES WHERE ACTIVE=1;";
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
	
	public void getStudentReportData(JTable table) {
		String query = "select users.user_fullname,modules.module_name,studentreport.marks from users left join studentreport on users.user_id = studentreport.user_id left join modules on modules.module_id=studentreport.module_id where users.user_id =?;";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
        	Connection connect = new Database().getConnection();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, this.getId());
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
	public void getModuleData(JTable table) {
		String query = "select modules.module_name,modules.module_level,enrollments.status from modules left join enrollments on modules.module_id=enrollments.module_id where enrollments.user_id=?";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
        	Connection connect = new Database().getConnection();
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, this.getId());
            ResultSet res = stmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] colName = new String[colCount];
            for (int i = 0; i < colCount; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
            }
            while (res.next()) {
                String module = String.valueOf(res.getString("module_name"));
                String level = String.valueOf(res.getInt("module_level"));
                String status = String.valueOf(res.getString("status"));
                String[] tbData = { module, level, status };
                model.addRow(tbData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
