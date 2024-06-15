package main;

import database.dbConnection;
import java.sql.*;

public class LogIn extends User {
	private int count;
	public LogIn(String userName, String password) {
		super(userName, password);
		this.count = 0;
		try {
			dbConnection con = new dbConnection();
			Connection c = con.getConnection();
            String sql1 = "SELECT COUNT(*) FROM `user` WHERE name = ?";
            PreparedStatement st = c.prepareStatement(sql1);
            st.setString(1, this.getUserName());
            ResultSet rs = st.executeQuery();
            rs.next();
            //count = 0 -> dont exist, count == 1 -> exist, count = 2 -> exist but wrong pass
            if(rs.getInt(1) == 1) {
            	String sq2 = "SELECT COUNT(*) FROM `user` WHERE name = ? AND password = ?";
                PreparedStatement st2 = c.prepareStatement(sq2);
                st2.setString(1, this.getUserName());
                st2.setString(2, this.getPassword());
                ResultSet rs2 = st2.executeQuery();
                rs2.next();
                if(rs2.getInt(1) == 1) {
                	this.count = 1;
                	String sql3 = "SELECT * FROM `user` WHERE name = ? AND password = ?";
                    PreparedStatement st3 = c.prepareStatement(sql3);
                    st3.setString(1, this.getUserName());
                    st3.setString(2, this.getPassword());
                    ResultSet rs3 = st3.executeQuery();
                    rs3.next();
                    //Tạo user

                    this.setUserID(rs3.getInt(1));
                    this.setUserName(rs3.getString(2));
                    this.setUserAge(rs3.getInt(3));
                    this.setUserPhone(rs3.getInt(4));
                    this.setUserEmail(rs3.getString(5));
                    this.setPassword(rs3.getString(6));
                }else {
                	this.count = 2;
                }
            }

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
