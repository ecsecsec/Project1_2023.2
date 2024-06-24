package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Event;
import main.EventList;

public class testdb {
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/eventmanagementapp";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1nhamrnhis2";

    public static void main(String[] args) {
        EventList eventList = new EventList();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            String sql = "SELECT * FROM `event`";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setEventName(rs.getString(12));
	        	event.setLocation(rs.getString(7));
	        	event.setDescription(rs.getString(4));
	        	event.setPrivate(rs.getBoolean(3));
                eventList.addEvent(event);
                
            }
            
            
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        System.out.println(eventList.getItems().size());
        eventList.print();
    }
}