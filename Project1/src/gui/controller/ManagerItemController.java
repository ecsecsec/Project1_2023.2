package gui.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.ConnectionUtil;
import encyption.Encryption;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.*;

public class ManagerItemController extends User {
	private Event event;
	private User user;
	private User u;
	private ObservableList<User> listUser = FXCollections.observableArrayList();
	
	public ManagerItemController(User user) {
		super();
		this.user = user;
	}
	public void setData(Event event) {
		this.event = event;
		lblEventName.setText(event.getEventName());	
		try {
			ConnectionUtil con = new ConnectionUtil();
			Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
			String sql1 = "SELECT * FROM user JOIN request_user ON user.user_id = request_user.user_id WHERE request_user.event_id =?";
			try (PreparedStatement st = c.prepareStatement(sql1)){
				st.setInt(1, this.event.getEventID());
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
				    CheckBox ch = new CheckBox();
				    User log = new LogIn(rs.getInt(1),
				    		Encryption.AESDecrypt(rs.getString(2), Encryption.generateKey()),
				    		Encryption.AESDecrypt(rs.getInt(3), Encryption.generateKey()),
				    		Encryption.AESDecrypt(rs.getInt(4), Encryption.generateKey()),
				    		Encryption.AESDecrypt(rs.getString(5), Encryption.generateKey()), ch);
				    String sql = "SELECT COUNT(*) FROM user_event WHERE user_id = ? AND event_id = ?";
		            try(PreparedStatement st1 = c.prepareStatement(sql);){
		            	st1.setInt(1, log.getUserID());
		    			st1.setInt(2, this.event.getEventID());
		      			ResultSet rs1 = st1.executeQuery();
		    			rs1.next();
		    			if(rs1.getInt(1) > 0) {
		    				ch.setSelected(true);			
		    				//ai đã tham gia -> checkbox is selected
		    			}st1.close();
		    			rs1.close();
		            }
				    listUser.add(log);
				}st.close();
				rs.close();
			}c.close();
			//chỉ nhận user từ bảng request, bảng invited khi nhận event thì mới chuyển qua bảng request
			/*String sql2 = "SELECT * FROM user JOIN invited_user ON user.user_id = invited_user.user_id WHERE invited_user.event_id =?";
			try (PreparedStatement st = c.prepareStatement(sql2)){
				st.setInt(1, this.event.getEventID());
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					CheckBox ch = new CheckBox();
					LogIn log = new LogIn(Encryption.AESDecrypt(rs.getString(2), Encryption.generateKey()),
				    		Encryption.AESDecrypt(rs.getInt(4), Encryption.generateKey()),
				    		Encryption.AESDecrypt(rs.getString(5), Encryption.generateKey()), ch);
				    listUser.add(log);
				}
			}*/	
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	@FXML
	public void initialize() {

		colUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("userEmail"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<User, Integer>("userPhone"));
		colRequest.setCellValueFactory(new PropertyValueFactory<User, CheckBox>("checkbox"));
		
		tblUserEvent.setItems(listUser);
		
	}
	
    @FXML
    private Button btnInvite;
    
	@FXML
    private Button btnEdit;
	
    @FXML
    private Button btnUpdate;
    
    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, Integer> colPhoneNumber;

    @FXML
    private TableColumn<User, CheckBox> colRequest;

    @FXML
    private TableColumn<User, String> colUserName;

    @FXML
    private TableView<User> tblUserEvent;
    
    @FXML
    private Label lblEventName;
    
    @FXML
    void btnEditPressed(ActionEvent evt) {
    	final String INFO_FXML = "/gui/view/Info.fxml";
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(INFO_FXML));
    		InfoController info = new InfoController(this.user);
    		fxmlLoader.setController(info);
    		System.out.println(this.event);
    		info.setData(this.event);
    		Parent  root = fxmlLoader.load();
    		
    		Stage stage = new Stage();
    		stage.setTitle("INFORMATION");
    		stage.setScene(new Scene(root));
    		stage.show();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	public ObservableList<User> getListUser() {
		return listUser;
	}
	public void setListUser(ObservableList<User> listUser) {
		this.listUser = listUser;
	}
	@FXML
	void joinUser(ActionEvent evt){
		ConnectionUtil con = new ConnectionUtil();
		if(this.event.isPrivate()) {
			for(User u: listUser){
				if(u.getCheckbox().isSelected()) {
					System.out.println(this.event.getEventID() + " " + u.getUserID());
					con.updateJoinUser("request_user",this.user, u, this.event.getEventID(), false);
					System.out.println(u.getUserName() + " has been added!");
				}else if(!u.getCheckbox().isSelected()) {
					con.unJoinUser(this.user, u, this.event.getEventID());
					System.out.println(u.getUserName() + " has been kicked!");
				}
			}
		}else {
			for(User u: listUser) {
				System.out.println(u.getCheckbox().isSelected());
				if(u.getCheckbox().isSelected()) {
					con.updateJoinUser("request_user",this.user, u, this.event.getEventID(), false);
					System.out.println(u.getUserName() + " has been added!");
				}else if(!u.getCheckbox().isSelected()) {
					con.unJoinUser(this.user, u, this.event.getEventID());
					System.out.println(u.getUserName() + " has been kicked!");
				}
			}
		}
	}

    @FXML
    void btnInvitePressed(ActionEvent event) {
    	try {
    		ConnectionUtil con = new ConnectionUtil();
    		String friend;
    		friend = JOptionPane.showInputDialog("Please enter friend's name:");
    		con.inviteUser(user, friend, this.event.getEventID());
    		JOptionPane.showMessageDialog(null, "Sent invitation successfully!");
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
