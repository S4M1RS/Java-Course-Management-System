package com.coursemanagement.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.coursemanagement.database.Controller;
import com.coursemanagement.database.Database;
import com.coursemanagement.gui.DisplayMessage;

public class User implements UserInterfaceModel {
	protected int userId;
	private String name;
	private String password;
	private String email;
	private String role;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password, String role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(int userId, String name, String email, String password, String role) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public void setId(int Id) {
		// TODO Auto-generated method stub
		this.userId = Id;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	@Override
	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role = role;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int login(String enteredEmail, String enteredPassword) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT * FROM users WHERE user_email='" + enteredEmail + "'";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				// Get the password from the database
				String passwordFromDB = rs.getString("user_password");
				// Compare the password from the database with the entered password
				if (passwordFromDB.equals(enteredPassword)) {
					System.out.println("Login successful!");
					DisplayMessage.showSuccessMessage("Login successful");
					String emailFromDB = rs.getString("user_email");
					int idFromDB = rs.getInt("user_id");
					String nameFromDB = rs.getString("user_fullname");
					String roleFromDB = rs.getString("user_role");

					switch (roleFromDB) {
					case "student":
						Controller.setCurrentUser(new Student(idFromDB, nameFromDB, emailFromDB, roleFromDB));
						return 1;
					case "instructor":
						Controller.setCurrentUser(new Instructor(idFromDB, nameFromDB, emailFromDB,"", roleFromDB));
						return 2;
					case "admin":
						Controller.setCurrentUser(new Admin(idFromDB, nameFromDB, emailFromDB, roleFromDB));
						return 3;
					}

				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void signUp(String fullname, String enteredEmail, String enteredPassword, String role) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT * FROM users WHERE user_email = '" + enteredEmail + "'";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				DisplayMessage.showErrorMessage("Cannot create account because user with given email already exists");
			} else {
				query = "INSERT INTO users (user_fullname, user_email, user_password, user_role) " + "VALUES ('"
						+ fullname + "', '" + enteredEmail + "', '" + enteredPassword + "', '" + role + "')";
				stmt = connect.prepareStatement(query);
				stmt.executeUpdate(query);

				query = "SELECT users.user_id FROM users WHERE user_email=?";
				stmt = connect.prepareStatement(query);
				stmt.setString(1, enteredEmail);
				rs = stmt.executeQuery();
				if (rs.next()) {
					int userIdFromDB = rs.getInt("user_id");
					System.out.println(userIdFromDB);
					query = "INSERT INTO studentinfo(user_id) VALUES(?)";
					stmt = connect.prepareStatement(query);
					stmt.setInt(1, userIdFromDB);
					stmt.executeUpdate();

					System.out.println("Sign up successful");
					DisplayMessage.showSuccessMessage("Signup successful!");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void signUp(String fullname, String enteredEmail, String enteredPassword, String role, String level,
			int course) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT * FROM users WHERE user_email = '" + enteredEmail + "'";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				DisplayMessage.showErrorMessage("Cannot create account because user with given email already exists");
			} else {
				query = "INSERT INTO users (user_fullname, user_email, user_password, user_role) " + "VALUES (?, ?, ?, ?)";
				stmt = connect.prepareStatement(query);
				stmt.setString(1, fullname);
				stmt.setString(2, enteredEmail);
				stmt.setString(3, enteredPassword);
				stmt.setString(4, role);
				stmt.executeUpdate();
				System.out.println(fullname+" "+enteredEmail+" "+enteredPassword+" "+role);

				query = "SELECT users.user_id FROM users WHERE user_email=?";
				stmt = connect.prepareStatement(query);
				stmt.setString(1, enteredEmail);
				rs = stmt.executeQuery();
				if (rs.next()) {
					int userIdFromDB = rs.getInt("user_id");
					System.out.println(userIdFromDB);
					String studentinfoSQL = "INSERT INTO studentinfo (user_id, course_id, student_level) SELECT user_id, ?, ? FROM users WHERE user_email = ?";
			        stmt = connect.prepareStatement(studentinfoSQL);
			        stmt.setInt(1, course);
			        stmt.setString(2, level);
			        stmt.setString(3, enteredEmail);
			        stmt.executeUpdate();

					System.out.println("Sign up successful");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
