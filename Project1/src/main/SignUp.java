  package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.dbConnection;

public class SignUp{
	private String userName;
	private String password;
	private String email;
	private int count; 

	public SignUp(String userName, String email, String password) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.count = 0;
		try {
			dbConnection con = new dbConnection();
			Connection c = con.getConnection();
            String sql1 = "SELECT COUNT(*) FROM `user` WHERE name = ?";
            PreparedStatement st1 = c.prepareStatement(sql1);
            st1.setString(1, this.getUserName());
            ResultSet rs = st1.executeQuery();
            rs.next();
            if(rs.getInt(1) == 0) {
            	this.count = 1;
            	String sql2 = "insert into user(name, email, password) values(?, ?, ?) ";
            	PreparedStatement st2 = c.prepareStatement(sql2);
                st2.setString(1, this.getUserName());
                st2.setString(2, this.getEmail());
                st2.setString(3, this.getPassword());
                int rowsInserted = st2.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }

            }
            
            rs.close();
            st1.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}