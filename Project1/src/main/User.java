package main;

import java.util.List;

import javafx.scene.control.CheckBox;

public abstract class User {
	private int userID;
	private String userName;
	private String password;
	private int userAge;
	private int userPhone;
	private String userEmail;
	private CheckBox checkbox;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(int string) {
		this.userPhone = string;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CheckBox getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(CheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public User(int userID, String userName, String password, int userAge, int userPhone, String userEmail) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userAge = userAge;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
	}
	public User( String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}	
	public User(String userName, int userPhone, String userEmail, CheckBox checkbox) {
		super();
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.checkbox = checkbox;
	}
	public User(int userID, String userName, int userAge, int userPhone, String userEmail, CheckBox checkbox) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userAge = userAge;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.checkbox = checkbox;
	}
	public User() {
		super();
	}

	public boolean equalUser(String name, String password) {
		boolean equal = false;
		if(this.userName.equals(name) && this.password.equals(password)) {
			equal = true;
		}else if(this.userName.equals(name) && !this.password.equals(password)) {
			System.out.println("The password that you've entered is incorrect.");
		}
		return equal;
	}

}
