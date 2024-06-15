package gui.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.*;


public class EventItemController {
	private Event event;
	private EventList eventList;
	private User user;
	public EventItemController(User user) {
		this.user = user;
				
	}
	
	public void setData(Event event) {
	    	this.event = event;
	    	lblEventName.setText(event.getEventName());
	    	lblTime.setText(event.getStartTime() + "-" + event.getEndTime());
	    	lblLocation.setText(event.getLocation());
	    	lblDescription.setText(event.getDescription());
	    	lblEventName.setText(event.getEventName());
	}
	@FXML
    private Button btnAccept;

    @FXML
    private Button btnInvite;

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
    void btnAcceptPressed(ActionEvent event) {

    }

    @FXML
    void btnInvitePressed(ActionEvent event) {
    	try {
    		dbConnection con = new dbConnection();
        	Connection c = con.getConnection();
        	con.requestUser(this.user.getUserID(), this.event.getEventID());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	public void setEventList(EventList eventList) {
		this.eventList = eventList;
	}

	public EventList getEventList() {
		// TODO Auto-generated method stub
		return eventList;
	}

}
