package main;

import database.*;
import encyption.Encryption;
import javafx.scene.control.CheckBox;

import java.sql.*;

public class LogIn extends User {
	private boolean count;
	public LogIn(String userName, String password) {
		super(userName, password);
		this.count = false;
		try {
			ConnectionUtil con = new ConnectionUtil();
			Connection c = con.getConnection("localhost", userName, password);
            String sql = "SELECT * FROM user WHERE name = ?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, Encryption.AESEncrypt(this.getUserName(), Encryption.generateKey()));
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
            	System.out.println("Log in successfully!");
            	this.count = true;
                //Tạo user với thông tin giải mã
                this.setUserID(rs.getInt(1));
                this.setUserName(Encryption.AESDecrypt(rs.getString(2), Encryption.generateKey()));
                this.setUserAge(Encryption.AESDecrypt(rs.getInt(3), Encryption.generateKey()));
                this.setUserPhone(Encryption.AESDecrypt(rs.getInt(4), Encryption.generateKey()));
                this.setUserEmail(Encryption.AESDecrypt(rs.getString(5), Encryption.generateKey()));
                this.setPassword(Encryption.AESDecrypt(rs.getString(6), Encryption.generateKey()));
            }            
            rs.close();
            st.close();
            c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean isCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}

	public LogIn(int userID, String userName, int userAge, int userPhone, String userEmail, CheckBox checkbox) {
		super(userID, userName, userAge, userPhone, userEmail, checkbox);
	}

	public LogIn(String userName, int userPhone, String userEmail, CheckBox checkbox) {
		super(userName, userPhone, userEmail, checkbox);
	}
}
