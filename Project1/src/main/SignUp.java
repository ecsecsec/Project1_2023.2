  package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.crypto.SecretKey;

import database.ConnectionUtil;
import database.dbConnection;
import encyption.Encryption;

public class SignUp{
	private String userName;
	private String password;
	private String email;
	private boolean count; 

	public SignUp(String userName, String email, String password) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.count = false;
		try {
			dbConnection conn = dbConnection.getInstance();
			conn.register(userName, password);
			ConnectionUtil c = new ConnectionUtil();
			Connection con = c.getConnection("localhost", userName, password);
			//lưu vào db tài khoản đã mã hóa => mã hóa cả username
			String sql1 = "insert into user(name, email, password) values(?, ?, ?) ";
        	PreparedStatement st1 = con.prepareStatement(sql1);
            st1.setString(1, Encryption.AESEncrypt(this.getUserName(), Encryption.generateKey()));
            st1.setString(2, Encryption.AESEncrypt(this.getEmail(), Encryption.generateKey()));
            st1.setString(3, Encryption.AESEncrypt(this.getPassword(), Encryption.generateKey()));
            int rowsInserted = st1.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
                this.count = true;
            } else {
                System.out.println("Failed to insert data.");
            }
            
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
	public boolean isCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}

}