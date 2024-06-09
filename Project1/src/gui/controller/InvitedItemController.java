package gui.controller;
import java.sql.Connection;

import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Event;
import main.EventList;
import main.User;

public class InvitedItemController {

	private Event event;
	private EventList eventList;
	private User user;
	
	public InvitedItemController(User user) {
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
    void btnAcceptPressed(ActionEvent evt) {
    	try {
    		dbConnection con = new dbConnection();
        	Connection c = con.getConnection();
        	con.updateJoinUser("invited_user", this.user.getUserID(), this.event.getEventID());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void btnInvitePressed(ActionEvent event) {

    }	
}
