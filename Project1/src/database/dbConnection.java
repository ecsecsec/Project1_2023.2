package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
	private static String DB_URL = "jdbc:mysql://localhost:3306/eventmanagementapp";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1nhamrnhis2";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    public void requestUser(int user_id, int event_id){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
            String addEvent = "INSERT INTO request_user (user_id, event_id, requested, `join`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setInt(3, 1);
                pstmt.setInt(4, 0);
                pstmt.executeUpdate();
                System.out.println("Request Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
    public void updateJoinUser(String table, int user_id, int event_id ){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
            String addEvent = "UPDATE ? SET `join` = 1 WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, table);
                pstmt.setString(2, String.valueOf(user_id));
                pstmt.setString(3,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Join Successfully");
            }


        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }
}