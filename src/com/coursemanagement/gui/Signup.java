package com.coursemanagement.gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import com.coursemanagement.database.Database;
import com.coursemanagement.user.Student;
import com.coursemanagement.user.StudentInfo;
import com.coursemanagement.user.User;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Signup {
	private JFrame frame;
	private JTextField emailField;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JRadioButton studentRadioBtn, teacherRadioBtn;
	private ButtonGroup radioButtonGroup;
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
	JComboBox<String> courseComboBox;
	private final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
	private final Pattern passwordPattern = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\\\$%\\\\^&\\\\*]).{8,}$");

	public Signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Signup");
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String iconPath = "../Java Course Management/images/logo-normal.png";
		ImageIcon icon = new ImageIcon(iconPath);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setLayout(null);

		JPanel signUpPanel = new JPanel();
		signUpPanel.setBorder(new LineBorder(new Color(223, 223, 223), 1, true));
		signUpPanel.setBackground(Color.WHITE);
		signUpPanel.setBounds(131, 11, 1046, 713);
		frame.getContentPane().add(signUpPanel);
		SpringLayout sl_signUpPanel = new SpringLayout();
		signUpPanel.setLayout(sl_signUpPanel);

		JLabel lblNewLabel = new JLabel("Create a new Account");
		lblNewLabel.setForeground(new Color(23, 23, 23));
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 61, SpringLayout.NORTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 391, SpringLayout.WEST, signUpPanel);
		lblNewLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 24));
		signUpPanel.add(lblNewLabel);

		JButton logInBtnSwitch = new JButton("<html><u>Log in</u></html>");
		sl_signUpPanel.putConstraint(SpringLayout.EAST, lblNewLabel, -253, SpringLayout.WEST, logInBtnSwitch);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, logInBtnSwitch, 60, SpringLayout.NORTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, logInBtnSwitch, -77, SpringLayout.EAST, signUpPanel);
		logInBtnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.setVisible(false);
			}
		});
		logInBtnSwitch.setForeground(new Color(115, 115, 115));
		logInBtnSwitch.setFont(new Font("Argentum Sans SemiBold", Font.BOLD, 14));
		logInBtnSwitch.setFocusPainted(false);
		logInBtnSwitch.setBorder(BorderFactory.createEmptyBorder());
		logInBtnSwitch.setBackground(Color.WHITE);

		logInBtnSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logInBtnSwitch.setForeground(new Color(120, 196, 68));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logInBtnSwitch.setForeground(new Color(115, 115, 115));
			}
		});
		logInBtnSwitch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpPanel.add(logInBtnSwitch);

		emailField = new JTextField();
		sl_signUpPanel.putConstraint(SpringLayout.WEST, emailField, 345, SpringLayout.WEST, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, emailField, -353, SpringLayout.EAST, signUpPanel);
		emailField.setVisible(true);
		emailField.setSelectionStart(0);
		emailField.setSelectionEnd(0);
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (emailField.getText().equals("abc@example.com")) {
					emailField.setText("");
				}
				emailField.setBackground(new Color(228, 243, 218));
				emailField.setForeground(new Color(23, 23, 23));
			}

			@Override
			public void focusLost(FocusEvent e) {
				emailField.setBackground(new Color(229, 229, 229));
			}
		});
		emailField.setText("abc@example.com");
		emailField.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		emailField.setBackground(new Color(229, 229, 229));
		emailField.setForeground(new Color(159, 159, 159));
		emailField.setFont(new Font("Argentum Sans", Font.PLAIN, 16));
		signUpPanel.add(emailField);

		JLabel emailLabel = new JLabel("Email");
		sl_signUpPanel.putConstraint(SpringLayout.WEST, emailLabel, 345, SpringLayout.WEST, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, emailField, 53, SpringLayout.SOUTH, emailLabel);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, emailField, 4, SpringLayout.SOUTH, emailLabel);
		emailLabel.setForeground(new Color(115, 115, 115));
		emailLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		signUpPanel.add(emailLabel);

		JLabel passwordLabel = new JLabel("Password");
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, passwordLabel, 22, SpringLayout.SOUTH, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, passwordLabel, 86, SpringLayout.WEST, emailField);
		passwordLabel.setForeground(new Color(115, 115, 115));
		passwordLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		signUpPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, passwordLabel, -6, SpringLayout.NORTH, passwordField);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, passwordField, 50, SpringLayout.SOUTH, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, passwordField, -312, SpringLayout.SOUTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, passwordField, -353, SpringLayout.EAST, signUpPanel);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (new String(passwordField.getPassword()).equals("John123@")) {
					passwordField.setText("");
					passwordField.setEchoChar('*');
				}
				passwordField.setBackground(new Color(228, 243, 218));
				passwordField.setForeground(new Color(23, 23, 23));
			}

			@Override
			public void focusLost(FocusEvent e) {
				passwordField.setBackground(new Color(229, 229, 229));
			}
		});
		passwordField.setEchoChar((char) 0);
		passwordField.setText("John123@");
		passwordField.setFont(new Font("Argentum Sans", Font.PLAIN, 16));
		passwordField.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		passwordField.setBackground(new Color(229, 229, 229));
		passwordField.setForeground(new Color(159, 159, 159));
		signUpPanel.add(passwordField);

		JLabel nameLabel = new JLabel("Name");
		sl_signUpPanel.putConstraint(SpringLayout.WEST, nameLabel, 345, SpringLayout.WEST, signUpPanel);
		nameLabel.setForeground(new Color(115, 115, 115));
		nameLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		signUpPanel.add(nameLabel);

		nameField = new JTextField();
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, emailLabel, 23, SpringLayout.SOUTH, nameField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, nameLabel, -7, SpringLayout.NORTH, nameField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, nameField, 211, SpringLayout.NORTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, nameField, 168, SpringLayout.NORTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, nameField, 344, SpringLayout.WEST, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, nameField, 689, SpringLayout.WEST, signUpPanel);
		nameField.setVisible(true);
		nameField.setSelectionStart(0);
		nameField.setSelectionEnd(0);
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameField.getText().equals("John Doe")) {
					nameField.setText("");
				}
				nameField.setBackground(new Color(228, 243, 218));
				nameField.setForeground(new Color(23, 23, 23));
			}

			@Override
			public void focusLost(FocusEvent e) {
				nameField.setBackground(new Color(229, 229, 229));
			}
		});
		nameField.setText("John Doe");
		nameField.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		nameField.setBackground(new Color(229, 229, 229));
		nameField.setForeground(new Color(159, 159, 159));
		nameField.setFont(new Font("Argentum Sans", Font.PLAIN, 16));
		signUpPanel.add(nameField);

		JButton signUpBtn = new JButton("Signup");
		sl_signUpPanel.putConstraint(SpringLayout.WEST, signUpBtn, 1, SpringLayout.WEST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, signUpBtn, -45, SpringLayout.SOUTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, signUpBtn, 0, SpringLayout.EAST, emailField);
		signUpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpBtn.setForeground(new Color(255, 255, 255));
				signUpBtn.setBackground(new Color(72, 118, 41));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				signUpBtn.setForeground(new Color(255, 255, 255));
				signUpBtn.setBackground(new Color(120, 196, 68));
			}
		});
		signUpBtn.setFocusPainted(false);
		signUpBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpBtn.setForeground(new Color(255, 255, 255));
		signUpBtn.setBackground(new Color(120, 196, 68));
		signUpBtn.setBorder(BorderFactory.createEmptyBorder());
		signUpBtn.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));

		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameInput = nameField.getText();
				String emailInput = emailField.getText();
				char[] passwordInput = passwordField.getPassword();
				String a = new String(passwordInput);

				boolean checkEmailFormat = validateEmail(emailInput);
				boolean checkPasswordFormat = validatePassword(a);

				if (studentRadioBtn.isSelected()) {
					String role = "student";
					String selectedCourse = (String) courseComboBox.getSelectedItem();
					int courseid = getCourseId(selectedCourse);
					String level = getCourseStartLevel(selectedCourse);
					Student tempUser = new Student(nameInput, emailInput, a, role, new StudentInfo(level, courseid));
					if (checkEmailFormat == true && checkPasswordFormat == true && role != null) {
						tempUser.signUp(tempUser.getName(), tempUser.getEmail(), tempUser.getPassword(),
								tempUser.getRole(), tempUser.getStudentInfo().getLevel(), tempUser.getStudentInfo().getCourse());
						DisplayMessage.showSuccessMessage("Sign up successful");
					} else if (checkEmailFormat == false && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid email and password format");
					} else if (checkEmailFormat == true && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid password format");
					} else if (checkEmailFormat == false && checkPasswordFormat == true) {
						DisplayMessage.showErrorMessage("Invalid email format");
					} else {
						DisplayMessage.showErrorMessage("Please fill all the required fields");
					}
				} else if (teacherRadioBtn.isSelected()) {
					String role = "instructor";
				}

			}
		});
		signUpPanel.add(signUpBtn);

		JLabel selectRole = new JLabel("Choose your role");
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, signUpBtn, 177, SpringLayout.SOUTH, selectRole);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, selectRole, 28, SpringLayout.SOUTH, passwordField);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, selectRole, 0, SpringLayout.WEST, emailField);
		selectRole.setForeground(new Color(115, 115, 115));
		selectRole.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		signUpPanel.add(selectRole);

		JLabel selectCourseLabel = new JLabel("Select a course");
		sl_signUpPanel.putConstraint(SpringLayout.WEST, selectCourseLabel, 346, SpringLayout.WEST, signUpPanel);
		selectCourseLabel.setVisible(false);
		selectCourseLabel.setForeground(new Color(115, 115, 115));
		selectCourseLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		signUpPanel.add(selectCourseLabel);

		getCourses(model);
		courseComboBox = new JComboBox<>(model);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, selectCourseLabel, -6, SpringLayout.NORTH, courseComboBox);
		sl_signUpPanel.putConstraint(SpringLayout.NORTH, courseComboBox, 554, SpringLayout.NORTH, signUpPanel);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, courseComboBox, 0, SpringLayout.WEST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, courseComboBox, -35, SpringLayout.NORTH, signUpBtn);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, courseComboBox, 0, SpringLayout.EAST, emailField);
		courseComboBox.setFont(new Font("Argentum Sans", Font.PLAIN, 14));
		courseComboBox.setVisible(false);
		signUpPanel.add(courseComboBox);

		ImageIcon studentRoleIcon = new ImageIcon("../Java Course Management/images/dashboard/signup/studentrole.png");
		ImageIcon activeStudentRoleIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/signup/studentrole-active.png");
		ImageIcon teacherRoleIcon = new ImageIcon("../Java Course Management/images/dashboard/signup/teacherrole.png");
		ImageIcon activeTeacherRoleIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/signup/teacherrole-active.png");

		studentRadioBtn = new JRadioButton("Student");

		sl_signUpPanel.putConstraint(SpringLayout.NORTH, studentRadioBtn, 6, SpringLayout.SOUTH, selectRole);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, studentRadioBtn, 0, SpringLayout.WEST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, studentRadioBtn, 70, SpringLayout.SOUTH, selectRole);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, studentRadioBtn, 160, SpringLayout.WEST, emailField);
		studentRadioBtn.setIcon(studentRoleIcon);
		studentRadioBtn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		studentRadioBtn.setForeground(new Color(115, 115, 115));
		studentRadioBtn.setBackground(new Color(255, 255, 255));
		studentRadioBtn.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		studentRadioBtn.setFocusPainted(false);
		studentRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpPanel.add(studentRadioBtn);

		studentRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentRadioBtn.setIcon(activeStudentRoleIcon);
				studentRadioBtn.setForeground(new Color(96, 157, 54));
				studentRadioBtn.setBackground(new Color(228, 243, 218));

				teacherRadioBtn.setIcon(teacherRoleIcon);
				teacherRadioBtn.setForeground(new Color(115, 115, 115));
				teacherRadioBtn.setBackground(new Color(255, 255, 255));
				selectCourseLabel.setVisible(true);
				courseComboBox.setVisible(true);
			}
		});

		teacherRadioBtn = new JRadioButton("Teacher");

		sl_signUpPanel.putConstraint(SpringLayout.NORTH, teacherRadioBtn, 0, SpringLayout.NORTH, studentRadioBtn);
		sl_signUpPanel.putConstraint(SpringLayout.WEST, teacherRadioBtn, -160, SpringLayout.EAST, emailField);
		sl_signUpPanel.putConstraint(SpringLayout.SOUTH, teacherRadioBtn, 0, SpringLayout.SOUTH, studentRadioBtn);
		sl_signUpPanel.putConstraint(SpringLayout.EAST, teacherRadioBtn, 0, SpringLayout.EAST, emailField);
		teacherRadioBtn.setIcon(teacherRoleIcon);
		teacherRadioBtn.setForeground(new Color(115, 115, 115));
		teacherRadioBtn.setBackground(new Color(255, 255, 255));
		teacherRadioBtn.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		teacherRadioBtn.setFocusPainted(false);
		teacherRadioBtn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		teacherRadioBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpPanel.add(teacherRadioBtn);

		teacherRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseComboBox.setVisible(false);
				selectCourseLabel.setVisible(false);
				teacherRadioBtn.setIcon(activeTeacherRoleIcon);
				teacherRadioBtn.setForeground(new Color(96, 157, 54));
				teacherRadioBtn.setBackground(new Color(228, 243, 218));

				studentRadioBtn.setIcon(studentRoleIcon);
				studentRadioBtn.setForeground(new Color(115, 115, 115));
				studentRadioBtn.setBackground(new Color(255, 255, 255));
			}
		});

		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(studentRadioBtn);
		radioButtonGroup.add(teacherRadioBtn);

	}

	public boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.find();
	}

	public boolean validatePassword(String password) {
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.find();
	}

	public void getCourses(DefaultComboBoxModel<String> model) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT course_name FROM courses WHERE active=1";
			PreparedStatement stmt = connect.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				model.addElement(rs.getString("course_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCourseStartLevel(String courseName) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT course_start_level FROM courses where course_name=?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, courseName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String level = rs.getString("course_start_level");
				return level;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getCourseId(String courseName) {
		try {
			Connection connect = new Database().getConnection();
			String query = "SELECT course_id FROM courses where course_name=?";
			PreparedStatement stmt = connect.prepareStatement(query);
			stmt.setString(1, courseName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int courseId = rs.getInt("course_id");
				return courseId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
