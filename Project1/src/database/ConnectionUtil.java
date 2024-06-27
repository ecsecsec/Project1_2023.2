package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import encyption.Encryption;
import encyption.hash;
import main.Event;
import main.User;

public class ConnectionUtil {
	private Connection con;
	private String svName, userName, password;
	public static Connection getConnection(String userName, String password) {
		String svName ="localhost";
		Connection conn = null;
		String DB_URL = "jdbc:mysql://"+svName+":3306/eventmanagementapp";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, userName, hash.SHA256pass(password));
            System.out.println("connect successfully!");
            conn.close();
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
		
	}
	
	public void requestUser(User user, Event event){
		//các cột id, request, join sẽ không cần mã hóa 
		try{
            String addEvent = "INSERT INTO request_user (user_id, event_id, requested, `join`) VALUES (?, ?, ?, ?)";
            try (Connection con = getConnection(  user.getUserName(), user.getPassword())) {
            	PreparedStatement pstmt = con.prepareStatement(addEvent);
            	if(event.isPrivate()) {
            		pstmt.setString(1, String.valueOf(user.getUserID()));
                    pstmt.setString(2, String.valueOf(event.getEventID()));
                    pstmt.setInt(3, 1);
                    pstmt.setInt(4, 0);
                    pstmt.executeUpdate();
                    System.out.println("Request Successfully");
            	}else {
            		pstmt.setString(1, String.valueOf(user.getUserID()));
                    pstmt.setString(2, String.valueOf(event.getEventID()));
                    pstmt.setInt(3, 1);
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    System.out.println("Request Successfully");
                    String sql = "INSERT INTO user_event(user_id, event_id) VALUES(?,?)";
                    try(PreparedStatement st = con.prepareStatement(sql)){
                    	st.setInt(1, user.getUserID());
                    	st.setInt(2, event.getEventID());
                    	st.executeUpdate();
                    	System.out.println("Join Successfully");
                    	st.close();
                    }
            	}pstmt.close();
            	con.close();
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
	public void inviteUser(User user, String userName, int event_id){
		int user_id = 0;
		try{
			Connection con = getConnection(  user.getUserName(), user.getPassword());
			String findUser = "SELECT * FROM user WHERE name =?";
			try(PreparedStatement st = con.prepareStatement(findUser)){
				st.setString(1, Encryption.AESEncrypt(userName, Encryption.generateKey()));
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					user_id = rs.getInt(1);
				}else {
					System.out.println("Cannot find user "+userName+"!");
				}
				st.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//kiểm tra xem user đã đồng ý lời mời trước đó nếu có chưa
			String sql = "SELECT COUNT(*) FROM request_user WHERE user_id =? AND event_id =?";
			try(PreparedStatement st = con.prepareStatement(sql)){
				st.setInt(1, user_id);
				st.setInt(2, event_id);
				ResultSet rs = st.executeQuery();
				rs.next();
				if(rs.getInt(1) == 0) {
					String addEvent = "INSERT INTO invited_user (user_id, event_id, invited, `join`) VALUES (?, ?, ?, ?)";
		            try (PreparedStatement pstmt = con.prepareStatement(addEvent)) {
		                pstmt.setString(1, String.valueOf(user_id));
		                pstmt.setString(2, String.valueOf(event_id));
		                pstmt.setInt(3, 1);
		                pstmt.setInt(4, 0);
		                pstmt.executeUpdate();
		                System.out.println("Invite Successfully");
		                System.out.println(userName + " has been invited to "+ event_id+"!"); 
		            }
				}else {
					System.out.println("Invite Successfully");
	                System.out.println(userName + " has been invited to "+ event_id+"!");
				}st.close();
				
			}con.close();
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
    public void updateJoinUser(String table, User host, User user, int event_id, boolean isSelect){
        try {
        	Connection con = getConnection(  host.getUserName(), host.getPassword());
            String addEvent = "UPDATE "+ table +" SET `join` = 1 WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(addEvent)){
                pstmt.setInt(1, user.getUserID());
                pstmt.setInt(2, event_id);
                pstmt.executeUpdate();
                pstmt.close();
            }
            if(!isSelect) {
            	String sql = "INSERT INTO user_event(user_id, event_id) VALUES(?,?)";
                try(PreparedStatement st = con.prepareStatement(sql)){
                	st.setInt(1, user.getUserID());
                	st.setInt(2, event_id);
                	st.executeUpdate();
                	System.out.println("Join Successfully");
                	st.close();
                }
                
            }con.close();
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
    public void unJoinUser(User host, User user, int event_id ){
        String addEvent = null;
        try {
        	Connection con = getConnection(  host.getUserName(), host.getPassword());
        	String findEvent1 = "SELECT COUNT(*) FROM request_user WHERE user_id = ? AND event_id = ?";
        	try(PreparedStatement st = con.prepareStatement(findEvent1)){
        		st.setInt(1, user.getUserID());
        		st.setInt(2, event_id);
        		st.execute();
        		ResultSet rs = st.executeQuery();
				rs.next();
				if(rs.getInt(1) == 1) {
					addEvent ="DELETE FROM request_user WHERE user_id = ? AND event_id = ?";
				}else if(rs.getInt(1) == 0) {
					addEvent ="UPDATE invited_user SET `join` = 0 WHERE user_id = ? AND event_id = ?";
				}
				st.close();
        	}

            
            try (PreparedStatement pstmt = con.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user.getUserID()));
                pstmt.setString(2,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Unjoin Successfully");
                pstmt.close();
            }

            String deleteListEvent = "DELETE FROM user_event WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(deleteListEvent)) {
                pstmt.setString(1, String.valueOf(user.getUserID()));
                pstmt.setString(2,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Delete Successfully");
                pstmt.close();
            } catch (SQLException ignored){
                System.out.print("Nothing here to delete");
            }
            con.close();

        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
    public void createEvent(User user) {
    	
    }
	
}
