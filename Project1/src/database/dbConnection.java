package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKey;

import encyption.Encryption;
import encyption.hash;

public class dbConnection {
	private static String decryptedPass;
	private static String svName = "localhost";
    private static String USER_NAME = "root";
    private static String PASSWORD = "/JjvMNHHTlVY5JOxnMn2bA==";
    private static String DB_URL = "jdbc:mysql://"+svName +":3306/eventmanagementapp";
    private static dbConnection instance;
    private Connection con;
    private dbConnection() {
    	try {
			SecretKey secretKey = Encryption.generateKey();
            this.decryptedPass = Encryption.AESDecrypt(PASSWORD, secretKey);

    		connectToDB();
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Failed to connect to db!");
    	}
    }
    public void connectToDB() throws SQLException {
		if(con == null) {
			try {

				Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection(DB_URL, USER_NAME, decryptedPass);
	            System.out.println("connect successfully!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, decryptedPass);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
	
	public static dbConnection getInstance() {
		if(instance == null) {
			synchronized(dbConnection.class) {
				if(instance == null) {
					instance = new dbConnection();
				}
			}
		}
		return instance;
	}
	public void register(String userName, String password) {
		try {
			//String name =  Encryption.AESEncrypt(userName, Encryption.generateKey());
			String pass =  hash.SHA256pass(password);
			dbConnection db = new dbConnection();
			Connection con = db.getConnection();
			SecretKey secretKey = Encryption.generateKey();
			String createUser = "CREATE USER ?@'%' IDENTIFIED BY ?";		
			try (PreparedStatement st = con.prepareStatement(createUser)){
				st.setString(1, userName);
				st.setString(2,  pass);
				st.executeUpdate();
				st.close();
				
			}catch(SQLException e) {
				System.out.println("User had existed!");
				e.printStackTrace();
			}
			String grantPrivileges = "GRANT SELECT, INSERT, UPDATE, DELETE ON eventmanagementapp.* TO ?@'%'";
			try (PreparedStatement st = con.prepareStatement(grantPrivileges)){
				st.setString(1, userName);
				st.executeUpdate();
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			String flush = "FLUSH PRIVILEGES";
			try (PreparedStatement st = con.prepareStatement(flush)){
				st.executeUpdate();
				System.out.println("Created user successfully!");
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}