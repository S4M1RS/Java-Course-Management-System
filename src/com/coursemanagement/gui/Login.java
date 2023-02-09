package com.coursemanagement.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.border.LineBorder;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.coursemanagement.user.User;

public class Login {

	private JFrame frmLogin;
	private JTextField emailField;
	private JPasswordField passwordField;
	public static final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
	public static final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\\\$%\\\\^&\\\\*]).{8,}$");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setVisible(true);
		frmLogin.setTitle("Login");
		frmLogin.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String iconPath = "../Java Course Management/images/logo-normal.png";
		ImageIcon icon = new ImageIcon(iconPath);
		frmLogin.setIconImage(icon.getImage());
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new LineBorder(new Color(223, 223, 223), 1, true));
		loginPanel.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(165, Short.MAX_VALUE)
					.addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, 1046, GroupLayout.PREFERRED_SIZE)
					.addGap(139))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, 713, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel loginIcon = new JLabel();
		loginIcon.setSize(96,96);
		loginIcon.setVisible(true);
		loginIcon.setIcon(new ImageIcon("../Java Course Management/images/logo-normal.png"));
		loginPanel.add(loginIcon);
		
		
		JLabel lblNewLabel = new JLabel("Welcome to Course Management App");
		lblNewLabel.setForeground(new Color(23, 23, 23));
		lblNewLabel.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 25));
		SpringLayout sl_loginPanel = new SpringLayout();
		sl_loginPanel.putConstraint(SpringLayout.WEST, loginIcon, 468, SpringLayout.WEST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, loginIcon, -23, SpringLayout.NORTH, lblNewLabel);
		sl_loginPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 182, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 276, SpringLayout.WEST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, lblNewLabel, -273, SpringLayout.EAST, loginPanel);
		loginPanel.setLayout(sl_loginPanel);
		loginPanel.add(lblNewLabel);
		
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setForeground(new Color(115, 115, 115));
		emailLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		loginPanel.add(emailLabel);
		
		emailField = new JTextField();
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, emailField, -392, SpringLayout.SOUTH, loginPanel);
		emailField.setVisible(true);
		emailField.setSelectionStart(0);
		emailField.setSelectionEnd(0);
		sl_loginPanel.putConstraint(SpringLayout.NORTH, emailField, 276, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.WEST, emailField, 349, SpringLayout.WEST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, emailField, -349, SpringLayout.EAST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.WEST, emailLabel, 0, SpringLayout.WEST, emailField);
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, emailLabel, -6, SpringLayout.NORTH, emailField);
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (emailField.getText().equals("abc@example.com")) {
			            emailField.setText("");
			     }
		         emailField.setForeground(new Color(23, 23, 23));
				 emailField.setBackground(new Color(228, 243, 218));
			}
			@Override
			public void focusLost(FocusEvent e) {
				emailField.setBackground(new Color(229, 229, 229));
			}
		});
		emailField.setText("abc@example.com");
		emailField.setBorder(BorderFactory.createEmptyBorder(8,16,8,16));
		emailField.setBackground(new Color(229, 229, 229));
		emailField.setForeground(new Color(159, 159, 159));
		emailField.setFont(new Font("Argentum Sans", Font.PLAIN, 16));
		loginPanel.add(emailField);
		
		JLabel passwordLabel = new JLabel("Password");
		sl_loginPanel.putConstraint(SpringLayout.NORTH, passwordLabel, 353, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, emailLabel);
		passwordLabel.setForeground(new Color(115, 115, 115));
		passwordLabel.setFont(new Font("Argentum Sans Medium", Font.PLAIN, 16));
		loginPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, passwordLabel, -9, SpringLayout.NORTH, passwordField);
		sl_loginPanel.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, emailField);
		sl_loginPanel.putConstraint(SpringLayout.NORTH, passwordField, 377, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, passwordField, -291, SpringLayout.SOUTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, emailLabel);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (new String(passwordField.getPassword()).equals("Abc123@")) {
			            passwordField.setText("");
			            passwordField.setEchoChar('*');
			     }
				 passwordField.setForeground(new Color(23, 23, 23));
				 passwordField.setBackground(new Color(228, 243, 218));
			}
			@Override
			public void focusLost(FocusEvent e) {
				passwordField.setBackground(new Color(229, 229, 229));
			}
		});
		passwordField.setEchoChar((char)0);
		passwordField.setText("Abc123@");
		passwordField.setFont(new Font("Argentum Sans", Font.PLAIN, 16));
		passwordField.setBorder(BorderFactory.createEmptyBorder(8,16,8,16));
		passwordField.setBackground(new Color(229, 229, 229));
		passwordField.setForeground(new Color(159, 159, 159));
		loginPanel.add(passwordField);
		
		JLabel invalidEmailError = new JLabel("Invalid email format");
		
		
		sl_loginPanel.putConstraint(SpringLayout.NORTH, invalidEmailError, 325, SpringLayout.NORTH, loginPanel);
		JLabel invalidPasswordError = new JLabel("Invalid password format");
		sl_loginPanel.putConstraint(SpringLayout.NORTH, invalidPasswordError, 5, SpringLayout.SOUTH, passwordField);
		JLabel noUserError = new JLabel("No user with given credentials");
		
		
		JButton loginBtn = new JButton("Login");
		sl_loginPanel.putConstraint(SpringLayout.NORTH, loginBtn, 66, SpringLayout.SOUTH, noUserError);
		sl_loginPanel.putConstraint(SpringLayout.WEST, loginBtn, 348, SpringLayout.WEST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.SOUTH, loginBtn, -132, SpringLayout.SOUTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, loginBtn, -350, SpringLayout.EAST, loginPanel);
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setForeground(new Color(255, 255, 255));
				loginBtn.setBackground(new Color(72, 118, 41));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setForeground(new Color(255, 255, 255));
				loginBtn.setBackground(new Color(120, 196, 68));
			}
		});
		loginBtn.setFocusPainted(false);
		loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(120, 196, 68));
		loginBtn.setBorder(BorderFactory.createEmptyBorder());
		loginBtn.setFont(new Font("Argentum Sans SemiBold", Font.PLAIN, 16));
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invalidPasswordError.setVisible(false);
				invalidEmailError.setVisible(false);
				noUserError.setVisible(false);
				
				String emailInput = emailField.getText();
				char[] passwordInput = passwordField.getPassword();
				String a = new String(passwordInput);
				
				boolean checkEmailFormat = validateEmail(emailInput);
				boolean checkPasswordFormat = validatePassword(a);
				
				if(checkEmailFormat == true && checkPasswordFormat == true) {
					User tempUser = new User(emailInput, a);
					int status = tempUser.login(tempUser.getEmail(), tempUser.getPassword());
					switch (status) {
						case 1:
							frmLogin.setVisible(false);	
							new StudentDashboard();
							break;
							
						case 2:
							frmLogin.setVisible(false);	
							new StudentDashboard();
							break;
						case 3:
							frmLogin.setVisible(false);	
							new AdminDashboard();
							break;
						case 0:
							DisplayMessage.showErrorMessage("Invalid email or password");
							break;
					}
				}
				else if(checkEmailFormat==false && checkPasswordFormat==false) {
					invalidPasswordError.setVisible(true);
					invalidEmailError.setVisible(true);
				}
				else if(checkEmailFormat==true && checkPasswordFormat==false) {
					invalidPasswordError.setVisible(true);
				}
				else if(checkEmailFormat==false && checkPasswordFormat==true) {
					invalidEmailError.setVisible(true);
				}
				else {
					noUserError.setVisible(true);
				}
			}
		});
		loginPanel.add(loginBtn);
		
		JLabel lblNewLabel_1 = new JLabel((String) null);
		lblNewLabel_1.setForeground(new Color(24, 39, 14));
		sl_loginPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 10, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -10, SpringLayout.EAST, loginPanel);
		loginPanel.add(lblNewLabel_1);
		
		JButton signUpBtnSwitch = new JButton("<html><u>Sign Up</u></html>");
		sl_loginPanel.putConstraint(SpringLayout.NORTH, signUpBtnSwitch, 67, SpringLayout.NORTH, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, signUpBtnSwitch, -74, SpringLayout.EAST, loginPanel);
		signUpBtnSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpBtnSwitch.setForeground(new Color(120, 196, 68));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signUpBtnSwitch.setForeground(new Color(115, 115, 115));
			}
		});
		signUpBtnSwitch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpBtnSwitch.setFocusPainted(false);
		signUpBtnSwitch.setBorder(BorderFactory.createEmptyBorder());
		signUpBtnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signup();
				frmLogin.setVisible(false);
			}
		});
		signUpBtnSwitch.setForeground(new Color(115, 115, 115));
		signUpBtnSwitch.setBackground(new Color(255, 255, 255));
		signUpBtnSwitch.setFont(new Font("Argentum Sans SemiBold", Font.BOLD, 14));
		loginPanel.add(signUpBtnSwitch);
		sl_loginPanel.putConstraint(SpringLayout.WEST, invalidEmailError, 0, SpringLayout.WEST, emailLabel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, invalidEmailError, 508, SpringLayout.WEST, loginPanel);
		invalidEmailError.setForeground(new Color(176, 16, 48));
		invalidEmailError.setFont(new Font("Argentum Sans", Font.PLAIN, 12));
		invalidEmailError.setVisible(false);
		loginPanel.add(invalidEmailError);
		
		sl_loginPanel.putConstraint(SpringLayout.EAST, invalidPasswordError, 159, SpringLayout.WEST, emailLabel);
		invalidPasswordError.setForeground(new Color(176, 16, 48));
		invalidPasswordError.setFont(new Font("Argentum Sans", Font.PLAIN, 12));
		invalidPasswordError.setVisible(false);
		sl_loginPanel.putConstraint(SpringLayout.WEST, invalidPasswordError, 0, SpringLayout.WEST, emailLabel);
		loginPanel.add(invalidPasswordError);
		
		
		sl_loginPanel.putConstraint(SpringLayout.NORTH, noUserError, 7, SpringLayout.SOUTH, invalidPasswordError);
		sl_loginPanel.putConstraint(SpringLayout.WEST, noUserError, 350, SpringLayout.WEST, loginPanel);
		sl_loginPanel.putConstraint(SpringLayout.EAST, noUserError, 542, SpringLayout.WEST, loginPanel);
		noUserError.setForeground(new Color(176, 16, 48));
		noUserError.setFont(new Font("Argentum Sans", Font.PLAIN, 12));
		noUserError.setVisible(false);
		loginPanel.add(noUserError);
		
		frmLogin.getContentPane().setLayout(groupLayout);

	}
	
	public static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.find();
	}
	public static boolean validatePassword(String password) {
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.find();
	}
}
