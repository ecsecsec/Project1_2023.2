package gui.controller;
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
import main.*;


public class EventItemController {
	private Event event;
	private EventList eventList;
	private User user;
	private int count;
	public EventItemController(User user) {
		this.user = user;
				
	}
	
	public void setData(Event event) {
	    	this.event = event;

	    	try {
	    		ConnectionUtil con = new ConnectionUtil();
				Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
				
				String sql = "SELECT COUNT(*) FROM request_user WHERE user_id = ? AND event_id = ?";
				try(PreparedStatement st = c.prepareStatement(sql)){
					st.setInt(1, this.user.getUserID());
					st.setInt(2, this.event.getEventID());
					
					ResultSet rs = st.executeQuery();
					rs.next();
					if(rs.getInt(1) == 1) {
						btnRequest.setStyle("-fx-background-color: #00ADB5");
					}
				}String s = "SELECT COUNT(*) FROM invited_user WHERE user_id = ? AND event_id = ? ";
				try(PreparedStatement st = c.prepareStatement(s)){
					st.setInt(1, this.user.getUserID());
					st.setInt(2, this.event.getEventID());
					
					ResultSet rs = st.executeQuery();
					rs.next();
					this.count = rs.getInt(1);
				}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	if(event.isPrivate()) {
				lblIsPrivate.setText("-Private-");
				btnAccept.setVisible(true);
				btnRequest.setVisible(false);
			}else {
				lblIsPrivate.setText("-Public-");
				if(this.count == 0) {
					btnAccept.setVisible(false);
					btnRequest.setVisible(true);
					HBox.setMargin(btnRequest, new Insets(30, 50, 0, 50));
				}else {
					btnAccept.setVisible(true);
					btnRequest.setVisible(false);
				}
			}
	    	lblEventName.setText(event.getEventName());
	    	lblTime.setText(event.getStartTime() + "-" + event.getEndTime());
	    	lblLocation.setText(event.getLocation());
	    	lblDescription.setText(event.getDescription());
	    	lblEventName.setText(event.getEventName());
	    	
	}
	@FXML
    private Button btnAccept;

    @FXML
    private Button btnRequest;

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
    void btnAcceptPressed(ActionEvent evt) {
    	try {
    		ConnectionUtil con = new ConnectionUtil();
            Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
            con.updateJoinUser("invited_user", this.user, this.user, this.event.getEventID(), this.event.isPrivate());
            if(this.event.isPrivate()) {
            	con.requestUser(user, event);
            	JOptionPane.showMessageDialog(null, "Accept successfully! Please wait to approval.");
            }else {
            	JOptionPane.showMessageDialog(null, "Accept successfully!");
            	con.requestUser(this.user, this.event);
            }
            String sql = "SELECT COUNT(*) FROM invited_user WHERE user_id = ? AND event_id = ? AND `join` = 1";
            try(PreparedStatement st = c.prepareStatement(sql);){
            	st.setInt(1, this.user.getUserID());
    			st.setInt(2, this.event.getEventID());
    			
    			ResultSet rs = st.executeQuery();
    			rs.next();
    			if(rs.getInt(1)!= 0) {
    				btnAccept.setStyle("-fx-background-color: #00ADB5");
    			}
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnRequestPressed(ActionEvent evt) {
    	//đã fix
        try {
            ConnectionUtil con = new ConnectionUtil();
            Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
            String sql = "SELECT COUNT(*) FROM request_user WHERE user_id = ? AND event_id = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, this.user.getUserID());
			st.setInt(2, this.event.getEventID());
			
			ResultSet rs = st.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0) {
				btnRequest.setStyle("-fx-background-color: #00ADB5");
				con.requestUser(this.user, this.event);//public -> auto join
				JOptionPane.showMessageDialog(null, "Join successfully!");
			}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void setEventList(EventList eventList) {
		this.eventList = eventList;
	}
}
