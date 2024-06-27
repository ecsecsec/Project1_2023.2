package client.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import database.ConnectionUtil;
import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.Event;
import main.EventList;
import main.User;

public class UserItemController {

	private Event event;
	private EventList eventList;
	private User user;
	
	public UserItemController(User user) {
		super();
		this.user = user;
		
	}
	
	public void setData(Event event) {
		this.event = event;
		if(event.isPrivate()) {
			lblIsPrivate.setText("-Private-");
		}else {
			lblIsPrivate.setText("-Public-");
		}
		lblEventName.setText(event.getEventName());
    	lblTime.setText(event.getStartTime() + "-" + event.getEndTime());
    	lblLocation.setText(event.getLocation());
    	lblDescription.setText(event.getDescription());
    	lblEventName.setText(event.getEventName());
	}
    @FXML
    private Button btnInvite;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblEventName;

    @FXML
    private Label lblIsPrivate;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblTime;

    @FXML
    void btnInvitePressed(ActionEvent evt) {
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

    @FXML
    void btnRemovePressed(ActionEvent evt) {
    	try {
    		ConnectionUtil con = new ConnectionUtil();
    		con.unJoinUser(this.user, this.user, this.event.getEventID());
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
