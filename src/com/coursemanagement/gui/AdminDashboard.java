package com.coursemanagement.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.coursemanagement.database.Controller;
import com.coursemanagement.user.Admin;
import com.coursemanagement.user.Instructor;
import com.coursemanagement.user.InstructorInfo;
import com.coursemanagement.user.Student;
import com.coursemanagement.user.User;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.Component;
import javax.swing.table.TableModel;

public class AdminDashboard {

	private JFrame frame;
	private CardLayout cardlayout = new CardLayout(0, 0);
	private Color primaryColor = new Color(120, 196, 68);
	Admin user = (Admin) Controller.getCurrentUser();
	private JTable table;
	DefaultTableModel studentTable = new DefaultTableModel();
	DefaultTableModel instructorTable = new DefaultTableModel();
	private JTable table_1;
	private JTable studentReportTable;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard window = new AdminDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();

		frame.setVisible(true);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		String iconPath = "../Java Course Management/images/logo-normal.png";
		ImageIcon icon = new ImageIcon(iconPath);
		frame.setIconImage(icon.getImage());

		JPanel sidebar = new JPanel();
		sidebar.setBackground(Color.WHITE);
		sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(229, 229, 229)));
		frame.getContentPane().add(sidebar, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(40, 40, 1000, 680);
		mainpanel.setBackground(Color.WHITE);
		panel.add(mainpanel);
		mainpanel.setLayout(cardlayout);

		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setBackground(Color.WHITE);
		mainpanel.add(dashboardPanel, "name_97879519032200");
		dashboardPanel.setLayout(null);

		JLabel cardLabel = new JLabel("Welcome " + user.getName());
		cardLabel.setForeground(new Color(23, 23, 23));
		cardLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		cardLabel.setBounds(40, 20, 432, 65);
		dashboardPanel.add(cardLabel);

		JPanel totalStudentsPanel = new JPanel();
		totalStudentsPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
		totalStudentsPanel.setBackground(Color.WHITE);
		totalStudentsPanel.setBounds(40, 101, 347, 200);
		dashboardPanel.add(totalStudentsPanel);
		totalStudentsPanel.setLayout(null);

		JLabel totalStudentsLabel = new JLabel("Total Students");
		totalStudentsLabel.setForeground(new Color(82, 82, 82));
		totalStudentsLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
		totalStudentsLabel.setBounds(24, 24, 110, 13);
		totalStudentsPanel.add(totalStudentsLabel);

		JLabel studentCountLabel = new JLabel(user.getCount("students"));
		studentCountLabel.setForeground(primaryColor);
		studentCountLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 40));
		studentCountLabel.setBounds(24, 72, 166, 54);
		totalStudentsPanel.add(studentCountLabel);

		JPanel totalInstructorsPanel = new JPanel();
		totalInstructorsPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
		totalInstructorsPanel.setLayout(null);
		totalInstructorsPanel.setBackground(Color.WHITE);
		totalInstructorsPanel.setBounds(548, 101, 347, 200);
		dashboardPanel.add(totalInstructorsPanel);

		JLabel totalStudentsLabel_1 = new JLabel("Total Instructors");
		totalStudentsLabel_1.setForeground(new Color(82, 82, 82));
		totalStudentsLabel_1.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
		totalStudentsLabel_1.setBounds(24, 24, 121, 13);
		totalInstructorsPanel.add(totalStudentsLabel_1);

		JLabel instructorCountLabel = new JLabel(user.getCount("instructors"));
		instructorCountLabel.setForeground(primaryColor);
		instructorCountLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 40));
		instructorCountLabel.setBounds(24, 72, 166, 54);
		totalInstructorsPanel.add(instructorCountLabel);

		JPanel totalCoursesPanel = new JPanel();
		totalCoursesPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
		totalCoursesPanel.setLayout(null);
		totalCoursesPanel.setBackground(Color.WHITE);
		totalCoursesPanel.setBounds(40, 377, 347, 200);
		dashboardPanel.add(totalCoursesPanel);

		JLabel lblTotalCourses = new JLabel("Total Courses");
		lblTotalCourses.setForeground(new Color(82, 82, 82));
		lblTotalCourses.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
		lblTotalCourses.setBounds(24, 24, 110, 13);
		totalCoursesPanel.add(lblTotalCourses);

		JLabel courseCountLabel = new JLabel(user.getCount("courses"));
		courseCountLabel.setForeground(primaryColor);
		courseCountLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 40));
		courseCountLabel.setBounds(25, 70, 166, 54);
		totalCoursesPanel.add(courseCountLabel);

		JPanel totalModulesPanel = new JPanel();
		totalModulesPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
		totalModulesPanel.setLayout(null);
		totalModulesPanel.setBackground(Color.WHITE);
		totalModulesPanel.setBounds(548, 377, 347, 200);
		dashboardPanel.add(totalModulesPanel);

		JLabel lblTotalModules = new JLabel("Total Modules");
		lblTotalModules.setForeground(new Color(82, 82, 82));
		lblTotalModules.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
		lblTotalModules.setBounds(24, 24, 110, 13);
		totalModulesPanel.add(lblTotalModules);

		JLabel moduleCountLabel = new JLabel(user.getCount("modules"));
		moduleCountLabel.setForeground(primaryColor);
		moduleCountLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 40));
		moduleCountLabel.setBounds(20, 72, 166, 54);
		totalModulesPanel.add(moduleCountLabel);

		JPanel studentPanel = new JPanel();
		studentPanel.setBackground(Color.WHITE);
		mainpanel.add(studentPanel, "name_97907333393800");
		studentPanel.setLayout(null);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setBounds(40, 40, 340, 46);
		lblStudents.setForeground(new Color(23, 23, 23));
		lblStudents.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		studentPanel.add(lblStudents);

		JButton addStudentBtn = new JButton("Add Student");
		addStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentForm();
			}
		});
		addStudentBtn.setForeground(Color.WHITE);
		addStudentBtn.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));
		addStudentBtn.setFocusPainted(false);
		addStudentBtn.setBorder(BorderFactory.createEmptyBorder());
		addStudentBtn.setBackground(new Color(120, 196, 68));
		addStudentBtn.setBounds(816, 52, 156, 32);
		studentPanel.add(addStudentBtn);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(40, 96, 931, 570);
		studentPanel.add(scrollPane);
		updateStudentTable(studentTable);
		table = new JTable(studentTable);
		table.setFont(new Font("Argentum Sans", Font.PLAIN, 14));
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setBackground(primaryColor);
		header.setFont(new Font("Argentum Sans Bold", Font.PLAIN, 14));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow >= 0) {
						int userId = (int) table.getValueAt(selectedRow, 0);
						showEditForm(userId);
					}
				}
			}
		});

		JPanel studentReportPanel = new JPanel();
		studentReportPanel.setBackground(Color.WHITE);
		mainpanel.add(studentReportPanel, "name_97925432303700");
		studentReportPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 99, 980, 42);
		studentReportPanel.add(panel_3);
		panel_3.setLayout(null);

		textField = new JTextField();
		textField.setBounds(0, 0, 541, 42);
		panel_3.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generate Report");
		setMenuBtnActive(btnNewButton);

		btnNewButton.setBounds(643, 0, 178, 42);
		panel_3.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(10, 152, 959, 520);
		studentReportPanel.add(scrollPane_1);

		studentReportTable = new JTable();
		studentReportTable.setBackground(new Color(128, 255, 0));
		scrollPane_1.setViewportView(studentReportTable);

		JLabel lblStudentResults = new JLabel("Student Results");
		lblStudentResults.setForeground(new Color(23, 23, 23));
		lblStudentResults.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		lblStudentResults.setBounds(10, 30, 340, 46);
		studentReportPanel.add(lblStudentResults);

		btnNewButton.addActionListener(e -> {
			user.getStudentReportData(studentReportTable, Integer.parseInt(textField.getText()));
		});

		JPanel lecturerpanel = new JPanel();
		lecturerpanel.setBackground(Color.WHITE);
		mainpanel.add(lecturerpanel, "name_97976725706200");
		lecturerpanel.setLayout(null);

		JLabel instructorsLabel = new JLabel("Instructors");
		instructorsLabel.setForeground(new Color(23, 23, 23));
		instructorsLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		instructorsLabel.setBounds(40, 40, 340, 46);
		lecturerpanel.add(instructorsLabel);

		JButton btnAddInstructor = new JButton("Add Instructor");
		btnAddInstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInstructorForm();
			}
		});
		btnAddInstructor.setForeground(Color.WHITE);
		btnAddInstructor.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));
		btnAddInstructor.setFocusPainted(false);
		btnAddInstructor.setBorder(BorderFactory.createEmptyBorder());
		btnAddInstructor.setBackground(new Color(120, 196, 68));
		btnAddInstructor.setBounds(813, 52, 156, 32);
		lecturerpanel.add(btnAddInstructor);

		JScrollPane scrollPane_1l = new JScrollPane(table_1);
		scrollPane_1l.setBackground(Color.WHITE);
		scrollPane_1l.setBounds(40, 96, 929, 570);
		lecturerpanel.add(scrollPane_1l);
		updateInstructorTable(instructorTable);

		table_1 = new JTable(instructorTable);
		scrollPane_1l.setViewportView(table_1);
		table_1.setFont(new Font("Argentum Sans", Font.PLAIN, 14));
		table_1.setCellSelectionEnabled(false);
		JTableHeader header1 = table_1.getTableHeader();
		header1.setForeground(Color.WHITE);
		header1.setBackground(primaryColor);
		header1.setFont(new Font("Argentum Sans Bold", Font.PLAIN, 14));

		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_1.getSelectedRow();
					if (selectedRow >= 0) {
						int userId = (int) table_1.getValueAt(selectedRow, 0);
						System.out.println("Edit instructor");
						showInstructorEditForm(userId);
					}
				}
			}
		});

		JPanel coursesPanel = new JPanel();
		coursesPanel.setBackground(Color.WHITE);
		mainpanel.add(coursesPanel, "name_97998948470800");
		coursesPanel.setLayout(null);

		JLabel coursesLabel = new JLabel("Courses");
		coursesLabel.setForeground(new Color(23, 23, 23));
		coursesLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		coursesLabel.setBounds(40, 40, 340, 46);
		coursesPanel.add(coursesLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 97, 983, 591);
		coursesPanel.add(panel_1);
		panel_1.setLayout(null);
		HashMap<String, ArrayList<String>> courseData = user.getCourseData();
		addCourseCard(courseData, panel_1);

		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewCourseForm();
			}
		});
		btnAddCourse.setForeground(Color.WHITE);
		btnAddCourse.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));
		btnAddCourse.setFocusPainted(false);
		btnAddCourse.setBorder(BorderFactory.createEmptyBorder());
		btnAddCourse.setBackground(new Color(120, 196, 68));
		btnAddCourse.setBounds(834, 59, 156, 32);
		coursesPanel.add(btnAddCourse);

		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setLayout(null);

		ImageIcon dashboardIcon = new ImageIcon("../Java Course Management/images/dashboard/sidebar/dashboard.png");
		ImageIcon activeDashboardIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/dashboard-active.png");
		ImageIcon coursesIcon = new ImageIcon("../Java Course Management/images/dashboard/sidebar/courses.png");
		ImageIcon activeCoursesIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/courses-active.png");
		ImageIcon instructorIcon = new ImageIcon("../Java Course Management/images/dashboard/sidebar/instructors.png");
		ImageIcon activeInstructorIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/instructors-active.png");
		ImageIcon studentIcon = new ImageIcon("../Java Course Management/images/dashboard/sidebar/students.png");
		ImageIcon activeStudentIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/students-active.png");
		ImageIcon studentReportIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/studentreport.png");
		ImageIcon activeStudentReportIcon = new ImageIcon(
				"../Java Course Management/images/dashboard/sidebar/studentreport-active.png");

		JButton dashboardBtn = new JButton("Dashboard");
		JButton studentBtn = new JButton("Students");
		JButton studentReportBtn = new JButton("Student Report");
		JButton instructorBtn = new JButton("Instructors");
		JButton courseBtn = new JButton("Courses");

		dashboardBtn.setBounds(16, 178, 216, 36);
		defaultBtn(dashboardBtn, dashboardIcon);
		dashboardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(dashboardBtn);
				dashboardBtn.setIcon(activeDashboardIcon);
				defaultBtn(studentBtn, studentIcon);
				defaultBtn(studentReportBtn, studentReportIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(courseBtn, coursesIcon);

				cardlayout.show(mainpanel, "name_97879519032200");
			}
		});
		menu.add(dashboardBtn);

		defaultBtn(studentBtn, studentIcon);
		studentBtn.setBounds(16, 272, 216, 36);
		studentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(studentBtn);
				studentBtn.setIcon(activeStudentIcon);
				defaultBtn(dashboardBtn, dashboardIcon);
				defaultBtn(studentReportBtn, studentReportIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(courseBtn, coursesIcon);

				cardlayout.show(mainpanel, "name_97907333393800");
			}
		});
		menu.add(studentBtn);

		defaultBtn(studentReportBtn, studentReportIcon);
		studentReportBtn.setBounds(16, 316, 216, 36);
		studentReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(studentReportBtn);
				studentReportBtn.setIcon(activeStudentReportIcon);
				defaultBtn(studentBtn, studentIcon);
				defaultBtn(dashboardBtn, dashboardIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(courseBtn, coursesIcon);

				cardlayout.show(mainpanel, "name_97925432303700");
			}
		});
		menu.add(studentReportBtn);

		instructorBtn.setBounds(16, 414, 216, 36);
		defaultBtn(instructorBtn, instructorIcon);

		instructorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(instructorBtn);
				instructorBtn.setIcon(activeInstructorIcon);
				defaultBtn(studentBtn, studentIcon);
				defaultBtn(studentReportBtn, studentReportIcon);
				defaultBtn(dashboardBtn, dashboardIcon);
				defaultBtn(courseBtn, coursesIcon);

				cardlayout.show(mainpanel, "name_97976725706200");
			}
		});
		menu.add(instructorBtn);

		courseBtn.setBounds(16, 512, 216, 36);
		defaultBtn(courseBtn, coursesIcon);

		courseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(courseBtn);
				courseBtn.setIcon(activeCoursesIcon);
				defaultBtn(studentBtn, studentIcon);
				defaultBtn(studentReportBtn, studentReportIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(dashboardBtn, dashboardIcon);

				cardlayout.show(mainpanel, "name_97998948470800");
			}
		});
		menu.add(courseBtn);

		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		menuLabel.setBounds(16, 156, 200, 13);
		menuLabel.setForeground(new Color(115, 115, 115));
		menu.add(menuLabel);

		JLabel studentLabel = new JLabel("Student");
		studentLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		studentLabel.setBounds(16, 250, 200, 13);
		studentLabel.setForeground(new Color(115, 115, 115));
		menu.add(studentLabel);

		JLabel instructorLabel = new JLabel("Instructor");
		instructorLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		instructorLabel.setBounds(16, 392, 200, 13);
		instructorLabel.setForeground(new Color(115, 115, 115));
		menu.add(instructorLabel);

		JLabel courseLabel = new JLabel("Course");
		courseLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		courseLabel.setBounds(16, 490, 200, 13);
		courseLabel.setForeground(new Color(115, 115, 115));
		menu.add(courseLabel);

		JPanel sidebarProfile = new JPanel();
		sidebarProfile.setBackground(Color.WHITE);
		sidebarProfile.setBounds(16, 623, 216, 53);
		menu.add(sidebarProfile);
		sidebarProfile.setLayout(null);

		JLabel sidebarNameLabel = new JLabel(user.getName());
		sidebarNameLabel.setForeground(new Color(82, 82, 82));
		sidebarNameLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 14));
		sidebarNameLabel.setBounds(8, 8, 150, 24);
		sidebarProfile.add(sidebarNameLabel);

		JLabel lblNewLabel_1 = new JLabel(user.getEmail());
		lblNewLabel_1.setForeground(new Color(82, 82, 82));
		lblNewLabel_1.setFont(new Font("Argentum Sans Light", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(8, 30, 150, 18);
		sidebarProfile.add(lblNewLabel_1);

		JButton logoutBtn = new JButton("Log out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login();
				DisplayMessage.showSuccessMessage("Logged out successfully");
			}
		});
		logoutBtn.setFont(new Font("Argentum Sans", Font.PLAIN, 12));
		logoutBtn.setForeground(new Color(183, 51, 51));
		logoutBtn.setBounds(131, 10, 85, 21);
		logoutBtn.setBackground(new Color(255, 255, 255));
		logoutBtn.setFocusPainted(false);
		logoutBtn.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		sidebarProfile.add(logoutBtn);

		JLabel logo = new JLabel("Course Management App");
		logo.setBounds(16, 10, 209, 19);
		menu.add(logo);
		logo.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));
		GroupLayout gl_sidebar = new GroupLayout(sidebar);
		gl_sidebar.setHorizontalGroup(gl_sidebar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidebar.createSequentialGroup()
						.addComponent(menu, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_sidebar.setVerticalGroup(
				gl_sidebar.createParallelGroup(Alignment.LEADING).addGroup(gl_sidebar.createSequentialGroup().addGap(31)
						.addComponent(menu, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)));
		sidebar.setLayout(gl_sidebar);

	}

	void setMenuBtnActive(JButton a) {
		a.setForeground(new Color(255, 255, 255));
		a.setBackground(new Color(120, 196, 68));
		a.setFocusPainted(false);
		a.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		a.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
		a.setHorizontalAlignment(SwingConstants.LEFT);
	}

	void defaultBtn(JButton a, ImageIcon defaultIcon) {
		a.setForeground(new Color(115, 115, 115));
		a.setBackground(new Color(255, 255, 255));
		a.setFocusPainted(false);
		a.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
		a.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 14));
		a.setHorizontalAlignment(SwingConstants.LEFT);
		a.setIcon(defaultIcon);
	}

	public void AddStudentForm() {
		JFrame frame;
		JTextField userIdField;
		JTextField fullNameField;
		JTextField emailField;
		JTextField passwordField;
		JButton submitButton;
		frame = new JFrame("Add Student");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		JLabel userIdLabel = new JLabel("User ID:");
		userIdLabel.setBounds(20, 20, 80, 25);
		frame.getContentPane().add(userIdLabel);

		userIdField = new JTextField();
		userIdField.setBounds(100, 20, 160, 25);
		frame.getContentPane().add(userIdField);

		JLabel fullNameLabel = new JLabel("Full Name:");
		fullNameLabel.setBounds(20, 50, 80, 25);
		frame.getContentPane().add(fullNameLabel);

		fullNameField = new JTextField();
		fullNameField.setBounds(100, 50, 160, 25);
		frame.getContentPane().add(fullNameField);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(20, 80, 80, 25);
		frame.getContentPane().add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(100, 80, 160, 25);
		frame.getContentPane().add(emailField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 110, 80, 25);
		frame.getContentPane().add(passwordLabel);

		passwordField = new JTextField();
		passwordField.setBounds(100, 110, 160, 25);
		frame.getContentPane().add(passwordField);

		submitButton = new JButton("Add Student");
		submitButton.setBounds(100, 140, 80, 25);
		frame.getContentPane().add(submitButton);
		setMenuBtnActive(submitButton);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userIdString = userIdField.getText();
				String fullName = fullNameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();
				System.out.println("userId: " + userIdString + " fullname: " + fullName + " email: " + email
						+ " password: " + password);
				if (userIdString.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
					DisplayMessage.showErrorMessage("Please fill in all the fields");
				} else {
					boolean checkEmailFormat = Login.validateEmail(email);
					boolean checkPasswordFormat = Login.validatePassword(password);
					if (checkEmailFormat == true && checkPasswordFormat == true) {
						int userId = Integer.parseInt(userIdField.getText());
						user.addStudent(userId, fullName, email, password);
						updateStudentTable(studentTable);
						frame.dispose();
					} else if (checkEmailFormat == false && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid email and password format");
					} else if (checkEmailFormat == true && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid password format");
					} else if (checkEmailFormat == false && checkPasswordFormat == true) {
						DisplayMessage.showErrorMessage("Invalid email format");
					} else {
						DisplayMessage.showErrorMessage("Please fill all the required fields");
					}
				}
			}
		});
	}

	private void updateStudentTable(DefaultTableModel studentTable) {
		studentTable.setRowCount(0);
		studentTable.setColumnIdentifiers(
				new String[] { "Student Id", "Student Name", "Student Level", "Enrolled Course" });
		List<Student> students = user.getAllStudents();
		for (Student student : students) {
			studentTable.addRow(new Object[] { student.getId(), student.getName(), student.getStudentInfo().getLevel(),
					student.getStudentInfo().courseCode });
		}
	}

	private void updateInstructorTable(DefaultTableModel instructorTable) {
		// TODO Auto-generated method stub
		instructorTable.setRowCount(0);
		instructorTable.setColumnIdentifiers(new String[] { "Instructor Id", "Instructor Name", "Instructor Modules" });
		List<Instructor> instructors = user.getAllInstructors();
		for (Instructor instructor : instructors) {
			instructorTable.addRow(new Object[] { instructor.getId(), instructor.getName(),
					instructor.getInstructorinfo().getModuleCodeString() });
		}
	}

	private void showEditForm(int userId) {

		// create and display the edit form
		JFrame editFrame = new JFrame("Edit Student");
		// retrieve the student data from the database
		List<Student> students = user.getStudent(userId);
		for (Student student : students) {
			JLabel fullNameLabel = new JLabel("Full Name:");
			JTextField fullNameField = new JTextField(student.getName());
			JLabel emailLabel = new JLabel("Email:");
			JTextField emailField = new JTextField(student.getEmail());
			JLabel levelLabel = new JLabel("Level:");
			JTextField levelField = new JTextField(student.getStudentInfo().getLevel());
			JLabel courseIdLabel = new JLabel("Course ID:");
			JTextField courseIdField = new JTextField(student.getStudentInfo().courseCode);
			JButton saveButton = new JButton("Save");
			JButton deleteButton = new JButton("Delete");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int courseId = 0;
					String courseName = courseIdField.getText();
					if (courseName.equals("BIBM")) {
						courseId = 1;
					} else if (courseName.equals("BIT")) {
						courseId = 2;
					} else {
						courseId = 3;
					}
					// update the student in the database
					user.updateStudent(userId, fullNameField.getText(), emailField.getText(), levelField.getText(),
							courseId);

					// update the table with the latest data
					updateStudentTable(studentTable);
					DisplayMessage.showSuccessMessage("Changes applied successfully");
					// close the edit form
					editFrame.dispose();
				}
			});

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					// update the student in the database
					user.deleteUser(userId);
					// update the table with the latest data
					updateStudentTable(studentTable);
					DisplayMessage.showSuccessMessage("Changes applied successfully");
					// close the edit form
					editFrame.dispose();
				}
			});

			editFrame.getContentPane().add(fullNameLabel);
			editFrame.getContentPane().add(fullNameField);
			editFrame.getContentPane().add(emailLabel);
			editFrame.getContentPane().add(emailField);
			editFrame.getContentPane().add(levelLabel);
			editFrame.getContentPane().add(levelField);
			editFrame.getContentPane().add(courseIdLabel);
			editFrame.getContentPane().add(courseIdField);
			editFrame.getContentPane().add(new JLabel());
			editFrame.getContentPane().add(new JLabel());
			editFrame.getContentPane().add(deleteButton);
			editFrame.getContentPane().add(saveButton);
		}

		// layout the form components
		editFrame.getContentPane().setLayout(new GridLayout(6, 2));

		// display the form
		editFrame.setSize(300, 200);
		((JPanel) editFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		editFrame.setLocationRelativeTo(null);
		editFrame.setVisible(true);
	}

	private void showInstructorEditForm(int userId) {

		// create and display the edit form
		JFrame editFrame = new JFrame("Edit Instructor");
		// retrieve the student data from the database
		List<Instructor> instructors = user.getInstructor(userId);
		for (Instructor instructor : instructors) {
			JLabel fullNameLabel = new JLabel("Full Name:");
			JTextField fullNameField = new JTextField(instructor.getName());
			JLabel emailLabel = new JLabel("Email:");
			JTextField emailField = new JTextField(instructor.getEmail());
			JLabel courseIdLabel = new JLabel("Modules Enrolled:");
			JTextField courseIdField = new JTextField(instructor.getInstructorinfo().getModuleCodeString());
			JButton saveButton = new JButton("Save");
			JButton deleteButton = new JButton("Delete");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					// update the student in the database
					user.updateInstructor(userId, fullNameField.getText(), emailField.getText());

					// update the table with the latest data
					updateInstructorTable(instructorTable);
					DisplayMessage.showSuccessMessage("Changes applied successfully");
					// close the edit form
					editFrame.dispose();
				}
			});

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					// update the student in the database
					user.deleteUser(userId);
					// update the table with the latest data
					updateStudentTable(studentTable);
					DisplayMessage.showSuccessMessage("Changes applied successfully");
					// close the edit form
					editFrame.dispose();
				}
			});

			editFrame.getContentPane().add(fullNameLabel);
			editFrame.getContentPane().add(fullNameField);
			editFrame.getContentPane().add(emailLabel);
			editFrame.getContentPane().add(emailField);
			editFrame.getContentPane().add(courseIdLabel);
			editFrame.getContentPane().add(courseIdField);
			editFrame.getContentPane().add(new JLabel());
			editFrame.getContentPane().add(new JLabel());
			editFrame.getContentPane().add(deleteButton);
			editFrame.getContentPane().add(saveButton);
		}

		// layout the form components
		editFrame.getContentPane().setLayout(new GridLayout(6, 2));

		// display the form
		editFrame.setSize(300, 200);
		((JPanel) editFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		editFrame.setLocationRelativeTo(null);
		editFrame.setVisible(true);
	}

	private void addCourseCard(HashMap<String, ArrayList<String>> data, JPanel parent_panel) {
		int size = data.get("course_name").size();
		int half = size / 2;
		int x = 31;
		int y = 45;
		for (int i = 0; i < size; i++) {
			if (i > half) {
				x += 400;
				y = 45;
			} else {
				y += i * 280;
			}
			JPanel coursePanelCard = new JPanel();
			coursePanelCard.setLayout(null);
			coursePanelCard.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
			coursePanelCard.setBackground(Color.WHITE);
			coursePanelCard.setBounds(x, y, 347, 200);
			parent_panel.add(coursePanelCard);

			JLabel lblBit = new JLabel(data.get("course_code").get(i));
			lblBit.setForeground(new Color(82, 82, 82));
			lblBit.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 14));
			lblBit.setBounds(24, 24, 110, 13);
			coursePanelCard.add(lblBit);

			JLabel lblBachelorInInformation = new JLabel(data.get("course_name").get(i));
			lblBachelorInInformation.setForeground(new Color(120, 196, 68));
			lblBachelorInInformation.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
			lblBachelorInInformation.setBounds(24, 58, 286, 68);
			coursePanelCard.add(lblBachelorInInformation);

			JLabel lblNewLabel = new JLabel("active");
			lblNewLabel.setFont(new Font("Argentum Sans", Font.PLAIN, 12));
			lblNewLabel.setBounds(291, 175, 46, 14);
			coursePanelCard.add(lblNewLabel);
		}
	}

	public void addInstructorForm() {
		JFrame frame;
		JTextField userIdField;
		JTextField fullNameField;
		JTextField emailField;
		JTextField passwordField;
		JButton submitButton;
		frame = new JFrame("Add Instructor");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		JLabel userIdLabel = new JLabel("User ID:");
		userIdLabel.setBounds(20, 20, 80, 25);
		frame.getContentPane().add(userIdLabel);

		userIdField = new JTextField();
		userIdField.setBounds(100, 20, 160, 25);
		frame.getContentPane().add(userIdField);

		JLabel fullNameLabel = new JLabel("Full Name:");
		fullNameLabel.setBounds(20, 50, 80, 25);
		frame.getContentPane().add(fullNameLabel);

		fullNameField = new JTextField();
		fullNameField.setBounds(100, 50, 160, 25);
		frame.getContentPane().add(fullNameField);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(20, 80, 80, 25);
		frame.getContentPane().add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(100, 80, 160, 25);
		frame.getContentPane().add(emailField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(20, 110, 80, 25);
		frame.getContentPane().add(passwordLabel);

		passwordField = new JTextField();
		passwordField.setBounds(100, 110, 160, 25);
		frame.getContentPane().add(passwordField);

		submitButton = new JButton("Add Instructor");
		submitButton.setBounds(100, 140, 80, 25);
		setMenuBtnActive(submitButton);
		frame.getContentPane().add(submitButton);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userIdString = userIdField.getText();
				String fullName = fullNameField.getText();
				String email = emailField.getText();
				String password = passwordField.getText();
				System.out.println("userId: " + userIdString + " fullname: " + fullName + " email: " + email
						+ " password: " + password);
				if (userIdString.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
					DisplayMessage.showErrorMessage("Please fill in all the fields");
				} else {
					boolean checkEmailFormat = Login.validateEmail(email);
					boolean checkPasswordFormat = Login.validatePassword(password);
					if (checkEmailFormat == true && checkPasswordFormat == true) {
						int userId = Integer.parseInt(userIdField.getText());
						user.addInstructor(userId, fullName, email, password);
						updateInstructorTable(instructorTable);
						frame.dispose();
					} else if (checkEmailFormat == false && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid email and password format");
					} else if (checkEmailFormat == true && checkPasswordFormat == false) {
						DisplayMessage.showErrorMessage("Invalid password format");
					} else if (checkEmailFormat == false && checkPasswordFormat == true) {
						DisplayMessage.showErrorMessage("Invalid email format");
					} else {
						DisplayMessage.showErrorMessage("Please fill all the required fields");
					}
				}
			}
		});
	}

	public void AddNewCourseForm() {
	    	JFrame frame;
	 	    JPanel panel;
	 	    JLabel courseIdLabel, courseNameLabel;
	 	    JTextField courseIdField, courseNameField;
	 	    JCheckBox isActiveCheckBox;
	 	    JButton addButton;
	        frame = new JFrame("Add New Course");
	        panel = new JPanel();
	        courseIdLabel = new JLabel("Course ID:");
	        courseNameLabel = new JLabel("Course Name:");
	        courseIdField = new JTextField(20);
	        courseNameField = new JTextField(20);
	        isActiveCheckBox = new JCheckBox("Active");
	        addButton = new JButton("Add");

	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.insets = new Insets(5, 5, 5, 5);

	        constraints.gridx = 0;
	        constraints.gridy = 0;
	        panel.add(courseIdLabel, constraints);

	        constraints.gridx = 1;
	        panel.add(courseIdField, constraints);

	        constraints.gridx = 0;
	        constraints.gridy = 1;
	        panel.add(courseNameLabel, constraints);

	        constraints.gridx = 1;
	        panel.add(courseNameField, constraints);

	        constraints.gridx = 0;
	        constraints.gridy = 2;
	        panel.add(isActiveCheckBox, constraints);

	        constraints.gridx = 1;
	        constraints.gridy = 3;
	        panel.add(addButton, constraints);

	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String courseId = courseIdField.getText();
	                String courseName = courseNameField.getText();
	                boolean isActive = isActiveCheckBox.isSelected();
	                user.addNewCourse(courseId, courseName, isActive);
	                DisplayMessage.showSuccessMessage("Course added successfully.");
	            }
	        });

	        frame.add(panel);
	        frame.setSize(400, 200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }

}
