package manager.java.sql.connect;

import java.sql.*;

import manager.java.encryption.Encrytion;
import manager.java.encryption.hash;

import javax.crypto.SecretKey;


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
                pstmt.setString(2, String.valueOf(age));;
                pstmt.setString(3, String.valueOf(phone));;
                pstmt.setString(4, email);
                pstmt.setString(5, password);
                pstmt.executeUpdate();
                System.out.println("Add User Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void createEvent(int host_id, int private_event, String description, String start_time, String end_time, String location, String link_app_event, int min_num_att, int max_num_att, int num_org){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {

            String addEvent = "INSERT INTO event (host_id, private_event, description, start_time, end_time, location, link_app_event, min_num_att, max_num_att, num_org) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(host_id));
                pstmt.setString(2, String.valueOf(private_event));
                pstmt.setString(3, description);
                pstmt.setString(4, start_time);
                pstmt.setString(5, end_time);
                pstmt.setString(6, location);
                pstmt.setString(7, link_app_event);
                pstmt.setString(8, String.valueOf(min_num_att));
                pstmt.setString(9, String.valueOf(max_num_att));
                pstmt.setString(10, String.valueOf(num_org));
                pstmt.executeUpdate();
                System.out.println("Create Event Successfully");

            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    public void updateEvent(String column, String value, int event_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "UPDATE event SET " + column + " = ? WHERE event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, value);
                pstmt.setInt(2, event_id);
                pstmt.executeUpdate();
                System.out.println("Update Event Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Error occurred while updating event");
            e.printStackTrace();
        }
    }

    public void updateEvent(String column, int value, int event_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "UPDATE event SET " + column + " = ? WHERE event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setInt(1, value);
                pstmt.setInt(2, event_id);
                pstmt.executeUpdate();
                System.out.println("Update Event Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Error occurred while updating event");
            e.printStackTrace();
        }
    }

    public void requestUser(int user_id, int event_id, int requested, int join){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO request_user (user_id, event_id, requested, 'join') VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setString(3, String.valueOf(requested));
                pstmt.setString(4, String.valueOf(join));
                pstmt.executeUpdate();
                System.out.println("Request Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }

    }

    public void inviteUser(int user_id, int event_id, int invited, int join){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO invited_user (user_id, event_id, invited, 'join') VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.setString(3, String.valueOf(invited));
                pstmt.setString(4, String.valueOf(join));
                pstmt.executeUpdate();
                System.out.println("Invited Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }

    }

    public void listHostEvent(int host_id, int event_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO host_event (host_id, event_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(host_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Add List Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }

    }

    public void listUserEvent(int user_id, int event_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "INSERT INTO user_event (user_id, event_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2, String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Add List Successfully");
            }
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }

    }

    public void updateJoinUser(String table, int user_id, int event_id ){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "UPDATE ? SET [join] = 1 WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, table);
                pstmt.setString(2, String.valueOf(user_id));
                pstmt.setString(3,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Join Successfully");
            }

            listUserEvent(user_id, event_id);
        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    public void unJoinUser(String table, int user_id, int event_id ){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String addEvent = "UPDATE ? SET [join] = 0 WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addEvent)) {
                pstmt.setString(1, table);
                pstmt.setString(2, String.valueOf(user_id));
                pstmt.setString(3,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Unjoin Successfully");
            }

            String deleteListEvent = "DELETE FROM user_event WHERE user_id = ? AND event_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteListEvent)) {
                pstmt.setString(1, String.valueOf(user_id));
                pstmt.setString(2,String.valueOf(event_id));
                pstmt.executeUpdate();
                System.out.println("Delete Successfully");
            } catch (SQLException ignored){
                System.out.print("Nothing here to delete");
            }

        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }

    
    public void selectUserEvent(int user_id){
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {
            String selectEvent = "SELECT * FROM user_event WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(selectEvent)) {
                pstmt.setInt(1, user_id);
                pstmt.executeUpdate();
                System.out.println("Select Successfully");
            }

        } catch (SQLException e) {
            System.out.print("Syntax error");
            e.printStackTrace();
        }
    }



    public void updateEncryptUser(int user_id, int age, int phone, String email, String password) throws Exception{
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {

            hash SHA2 = new hash();
            String pass =  SHA2.SHA256pass(password);

            SecretKey key = Encrytion.generateKey();
            int new_age = Encrytion.AESEncrypt(age, key);
            int new_phone = Encrytion.AESEncrypt(phone, key);
            String new_email = Encrytion.AESEncrypt(email, key);

            String encryptUser = "UPDATE user SET age = ?, phone = ?, email = ?, password = ? WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(encryptUser)) {
                pstmt.setString(1, String.valueOf(new_age));
                pstmt.setString(2, String.valueOf(new_phone));
                pstmt.setString(3, new_email);
                pstmt.setString(4, pass);
                pstmt.setString(5, String.valueOf(user_id));
                pstmt.executeUpdate();
                System.out.println("Encrypt User Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEncryptEvent(int event_id, int host_id,  String description, String start_time, String end_time, String location, String link_app_event, int min_num_att, int max_num_att, int num_org) throws Exception{
        try (Connection conn = DriverManager.getConnection(url, username, DBpassword)) {

            SecretKey key = Encrytion.generateKey();
            String new_description = Encrytion.AESEncrypt(description, key);
            String new_location = Encrytion.AESEncrypt(location, key);
            String new_link_app_event = Encrytion.AESEncrypt(link_app_event, key);
            int new_min_num_att = Encrytion.AESEncrypt(min_num_att, key);
            int new_max_num_att = Encrytion.AESEncrypt(max_num_att, key);
            int new_num_org = Encrytion.AESEncrypt(num_org, key);

            String encryptUser = "UPDATE event SET description = ?, location = ?, link_app_event = ?, min_num_att = ?, max_num_att = ?, num_org = ? WHERE event_id = ? AND host_id = ? AND private_event = 1";
            try (PreparedStatement pstmt = conn.prepareStatement(encryptUser)) {
                pstmt.setString(1, new_description);;
                pstmt.setString(2, new_location);
                pstmt.setString(3, new_link_app_event);
                pstmt.setString(4, String.valueOf(new_min_num_att));
                pstmt.setString(5, String.valueOf(new_max_num_att));
                pstmt.setString(6, String.valueOf(new_num_org));
                pstmt.setString(7, String.valueOf(event_id));
                pstmt.setString(8, String.valueOf(host_id));
                pstmt.executeUpdate();
                System.out.println("Encrypt Event Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Not exists");
        }
    }



}