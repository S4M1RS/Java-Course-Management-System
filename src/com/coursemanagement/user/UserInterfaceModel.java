package com.coursemanagement.user;

public interface UserInterfaceModel {
	public int getId();
	public String getName();
	public String getPassword();
	public String getEmail();
	
	public void setId(int Id);
	public void setName(String name);
	public void setPassword(String password);
	public void setEmail(String email);
	public String getRole();
	public void setRole(String role);
	
	public int login(String enteredEmail, String enteredPassword);
	public boolean logout();
	public void signUp(String fullname, String enteredEmail, String enteredPassword, String role);
	void signUp(String fullname, String enteredEmail, String enteredPassword, String role, String level, int course);
	
}
