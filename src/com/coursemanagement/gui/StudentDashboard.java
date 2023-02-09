package com.coursemanagement.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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

public class StudentDashboard {

	private JFrame frame;
	private CardLayout cardlayout = new CardLayout(0, 0);
	private Color primaryColor = new Color(120, 196, 68);
	Student user = (Student) Controller.getCurrentUser();
	DefaultTableModel studentTable = new DefaultTableModel();
	DefaultTableModel instructorTable = new DefaultTableModel();
	private JTable table_1;
	private JTable studentsResultsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboard window = new StudentDashboard();
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
	public StudentDashboard() {
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
		mainpanel.setBounds(100, 40, 1100, 720);
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

		JPanel totalCoursesPanel = new JPanel();
		totalCoursesPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 229, 229), 1));
		totalCoursesPanel.setLayout(null);
		totalCoursesPanel.setBackground(Color.WHITE);
		totalCoursesPanel.setBounds(40, 111, 347, 200);
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
		totalModulesPanel.setBounds(542, 111, 347, 200);
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
		;

		JPanel lecturerpanel = new JPanel();
		lecturerpanel.setBackground(Color.WHITE);
		mainpanel.add(lecturerpanel, "name_97976725706200");
		lecturerpanel.setLayout(null);
		
		JLabel instructorsLabel = new JLabel("Instructors");
		instructorsLabel.setForeground(new Color(23, 23, 23));
		instructorsLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 32));
		instructorsLabel.setBounds(40, 40, 340, 46);
		lecturerpanel.add(instructorsLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(40, 96, 1007, 570);
		lecturerpanel.add(scrollPane_1);
		updateInstructorTable(instructorTable);
		
		table_1 = new JTable(instructorTable);
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(new Font("Argentum Sans", Font.PLAIN, 14));
		table_1.setCellSelectionEnabled(false);
		JTableHeader header1 = table_1.getTableHeader();
		header1.setForeground(Color.WHITE);
		header1.setBackground(primaryColor);
		header1.setFont(new Font("Argentum Sans Bold", Font.PLAIN, 14));
		

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
		panel_1.setBounds(10, 97, 1011, 591);
		coursesPanel.add(panel_1);
		panel_1.setLayout(null);
		HashMap<String,ArrayList<String>> courseData = user.getCourseData();
		addCourseCard(courseData,panel_1);
		
		JButton btnNewButton = new JButton("Modules");
		btnNewButton.setBounds(863, 37, 158, 49);
		coursesPanel.add(btnNewButton);
		
		btnNewButton.addActionListener(e->{
			JFrame moduleFrame = new JFrame();
			moduleFrame.setBounds(100, 100, 911, 688);
			moduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			moduleFrame.getContentPane().setLayout(null);
			
			JButton enrollButton = new JButton("Enroll");
			btnNewButton.setForeground(new Color(0, 0, 0));
			btnNewButton.setBackground(new Color(128, 255, 0));
			btnNewButton.setBounds(737, 10, 150, 51);
			moduleFrame.getContentPane().add(enrollButton);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 71, 877, 570);
			moduleFrame.getContentPane().add(scrollPane);
			
			JTable moduleTable = new JTable();
			scrollPane.setViewportView(moduleTable);
			user.getModuleData(moduleTable);
			moduleFrame.setVisible(true);
		});
		
		JPanel studentResultsPanel = new JPanel();
		studentResultsPanel.setLayout(null);
		studentResultsPanel.setBackground(Color.WHITE);
		mainpanel.add(studentResultsPanel, "name_2299548770768700");
		
		JLabel lblStudentsResults = new JLabel("Students Results");
		lblStudentsResults.setForeground(new Color(23, 23, 23));
		lblStudentsResults.setFont(new Font("Dialog", Font.PLAIN, 32));
		lblStudentsResults.setBounds(40, 40, 340, 46);
		studentResultsPanel.add(lblStudentsResults);
		
		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		scrollPane_1_1.setBackground(Color.WHITE);
		scrollPane_1_1.setBounds(40, 96, 1007, 570);
		studentResultsPanel.add(scrollPane_1_1);
		
		studentsResultsTable = new JTable();
		scrollPane_1_1.setViewportView(studentsResultsTable);
		user.getStudentReportData(studentsResultsTable);

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
		JButton instructorBtn = new JButton("Instructors");
		JButton studentResultsB = new JButton("Student Results");
		JButton courseBtn_1 = new JButton("Courses");

		dashboardBtn.setBounds(16, 178, 216, 36);
		defaultBtn(dashboardBtn, dashboardIcon);
		dashboardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(dashboardBtn);
				dashboardBtn.setIcon(activeDashboardIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(studentResultsB, studentReportIcon);
				defaultBtn(courseBtn_1,coursesIcon);
				cardlayout.show(mainpanel, "name_97879519032200");
			}
		});
		menu.add(dashboardBtn);

		instructorBtn.setBounds(16, 297, 216, 36);
		defaultBtn(instructorBtn, instructorIcon);

		instructorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(instructorBtn);
				instructorBtn.setIcon(activeInstructorIcon);
				defaultBtn(dashboardBtn, dashboardIcon);
				defaultBtn(studentResultsB, coursesIcon);
				defaultBtn(courseBtn_1,coursesIcon);
				cardlayout.show(mainpanel, "name_97976725706200");
			}
		});
		menu.add(instructorBtn);

		studentResultsB.setBounds(16, 506, 216, 36);
		defaultBtn(studentResultsB, coursesIcon);

		studentResultsB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuBtnActive(studentResultsB);
				studentResultsB.setIcon(activeCoursesIcon);
				defaultBtn(instructorBtn, instructorIcon);
				defaultBtn(dashboardBtn, dashboardIcon);
				defaultBtn(courseBtn_1,coursesIcon);
				cardlayout.show(mainpanel, "name_2299548770768700");
			}
		});
		menu.add(studentResultsB);

		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		menuLabel.setBounds(16, 156, 200, 13);
		menuLabel.setForeground(new Color(115, 115, 115));
		menu.add(menuLabel);

		JLabel instructorLabel = new JLabel("Instructor");
		instructorLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		instructorLabel.setBounds(16, 273, 200, 13);
		instructorLabel.setForeground(new Color(115, 115, 115));
		menu.add(instructorLabel);

		JLabel courseLabel = new JLabel("Course");
		courseLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 12));
		courseLabel.setBounds(16, 376, 200, 13);
		courseLabel.setForeground(new Color(115, 115, 115));
		menu.add(courseLabel);

		JPanel sidebarProfile = new JPanel();
		sidebarProfile.setBackground(Color.WHITE);
		sidebarProfile.setBounds(16, 630, 216, 53);
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
		gl_sidebar.setHorizontalGroup(gl_sidebar.createParallelGroup(Alignment.LEADING).addComponent(menu,
				GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE));
		gl_sidebar.setVerticalGroup(
				gl_sidebar.createParallelGroup(Alignment.LEADING).addGroup(gl_sidebar.createSequentialGroup().addGap(31)
						.addComponent(menu, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)));
		
		courseBtn_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setMenuBtnActive(courseBtn_1);
					courseBtn_1.setIcon(activeCoursesIcon);
					defaultBtn(instructorBtn, instructorIcon);
					defaultBtn(dashboardBtn, dashboardIcon);
					defaultBtn(studentResultsB,studentReportIcon);
					cardlayout.show(mainpanel, "name_97998948470800");
				}
		});
		courseBtn_1.setBounds(16, 411, 216, 36);
		menu.add(courseBtn_1);
		defaultBtn(courseBtn_1,coursesIcon);
		
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setForeground(new Color(115, 115, 115));
		lblStudent.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStudent.setBounds(16, 483, 200, 13);
		menu.add(lblStudent);
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

	private void addCourseCard(HashMap<String,ArrayList<String>> data,JPanel parent_panel) {
		int size = data.get("course_name").size();
		int half = size/2;
		int x = 31;
		int y = 45;
		for(int i=0;i<size;i++) {
			if(i>half){
				x +=400;
				y=45;
			}else {
				y+=i*280;
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
	
	private void updateInstructorTable(DefaultTableModel instructorTable) {
		// TODO Auto-generated method stub
		instructorTable.setRowCount(0);
		instructorTable.setColumnIdentifiers(
				new String[] { "Instructor Id", "Instructor Name", "Instructor Modules"});
		List<Instructor> instructors = user.getAllInstructors();
		for (Instructor instructor : instructors) {
			instructorTable.addRow(new Object[] { instructor.getId(), instructor.getName(), instructor.getInstructorinfo().getModuleCodeString()});
		}
	}
}
