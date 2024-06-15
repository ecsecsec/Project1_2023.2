package main;

import database.dbConnection;
import java.sql.*;

public class LogIn {
	private String username;
	private String password;
	private User user;
	private int count;
	public LogIn(String username, String pass) {
		this.username = username;
		this.password = pass;
		this.count = 0;
		try {
			dbConnection con = new dbConnection();
			Connection c = con.getConnection();
            String sql1 = "SELECT COUNT(*) FROM `user` WHERE name = ?";
            PreparedStatement st = c.prepareStatement(sql1);
            st.setString(1, this.getUsername());
            ResultSet rs = st.executeQuery();
            rs.next();
            //count = 0 -> dont exist, count == 1 -> exist, count = 2 -> exist but wrong pass
            if(rs.getInt(1) == 1) {
            	String sq2 = "SELECT COUNT(*) FROM `user` WHERE name = ? AND password = ?";
                PreparedStatement st2 = c.prepareStatement(sq2);
                st2.setString(1, this.getUsername());
                st2.setString(2, this.getPassword());
                ResultSet rs2 = st2.executeQuery();
                rs2.next();
                if(rs2.getInt(1) == 1) {
                	this.count = 1;
                	String sql3 = "SELECT * FROM `user` WHERE name = ? AND password = ?";
                    PreparedStatement st3 = c.prepareStatement(sql3);
                    st3.setString(1, this.getUsername());
                    st3.setString(2, this.getPassword());
                    ResultSet rs3 = st3.executeQuery();
                    rs3.next();
                    //Tạo user
                    this.user.setUserID(rs3.getInt(1));
                    this.user.setUserName(rs3.getString(2));
                    this.user.setUserAge(rs3.getInt(3));
                    this.user.setUserPhone(rs3.getInt(4));
                    this.user.setUserEmail(rs3.getString(5));
                    this.user.setPassword(rs3.getString(6));
                    
                    
                }else {
                	this.count = 2;
                }
            }

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
