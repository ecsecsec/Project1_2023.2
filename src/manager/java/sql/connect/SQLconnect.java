package manager.java.sql.connect;

import java.sql.*;

public class SQLconnect {
    private static String url = "jdbc:mysql://localhost:3306/eventmanager";
    private static String username = "root";
    private static String DBpassword = "Tien23112004";

    public void createUser(String userName, int age, int phone, String email, String password) {
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String createUser = "CREATE USER ?@localhost IDENTIFIED BY ?";
            try (PreparedStatement pstmt = conn.prepareStatement(createUser)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
                System.out.println("Create User Successfully");
            } catch (SQLException e) {
                System.out.println("User name duplicate, please choose another name");
                e.printStackTrace();
            }
            String addUser = "INSERT INTO user (name, age, phone, email, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addUser)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, String.valueOf(age));
                pstmt.setString(3, String.valueOf(phone));
                pstmt.setString(4, email);
                pstmt.setString(5, password);
                pstmt.executeUpdate();
                System.out.println("Add User Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createJob(String job_title, int event_id, int job_rent){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addOrg = "INSERT INTO job (job_title, event_id, job_rent) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addOrg)) {
                pstmt.setString(1, job_title);
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setString(3, String.valueOf(job_rent));
                pstmt.executeUpdate();
                System.out.println("Create Job Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    public void createOrg (int user_id, int event_id, String org_name, int job_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addOrg = "INSERT INTO organiser (user_id, event_id, org_name, job_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addOrg)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setString(3, org_name);
                pstmt.setString(4, String.valueOf(job_id));
                pstmt.executeUpdate();
                System.out.println("Create Organiser Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    public void createEvent(int manager_id, int private_event, String description, String start_time, String location, String link_app_event, int min_num_att, int max_num_att, int num_org){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO event (manager_id, private_event, description, start_time, location, link_app_event, min_num_att, max_num_att, num_org) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(manager_id));
                pstmt.setString(2, String.valueOf(private_event));
                pstmt.setString(3, description);
                pstmt.setString(4, start_time);
                pstmt.setString(5, location);
                pstmt.setString(6, link_app_event);
                pstmt.setString(7, String.valueOf(min_num_att));
                pstmt.setString(8, String.valueOf(max_num_att));
                pstmt.setString(9, String.valueOf(num_org));
                pstmt.executeUpdate();
                System.out.println("Create Event Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    public void requestUser(int user_id, int event_id, int requested, int add_by_orgID, int join){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO request_user (user_id, event_id, requested, add_by_orgID, `join`) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setString(3, String.valueOf(requested));
                pstmt.setString(4, String.valueOf(add_by_orgID));
                pstmt.setString(5, String.valueOf(join));
                pstmt.executeUpdate();
                System.out.println("Request Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }

    }
}
