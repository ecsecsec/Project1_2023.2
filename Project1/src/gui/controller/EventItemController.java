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
		try {
        	dbConnection con = new dbConnection();
        	Connection c = con.getConnection();
            String sql = "SELECT * FROM `event` where ";
            PreparedStatement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setEventName(rs.getString(12));
	        	event.setLocation(rs.getString(7));
	        	event.setDescription(rs.getString(4));
	        	event.setPrivate(rs.getBoolean(3));
                this.eventList.addEvent(event);       
            }
 
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
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
    private Button btnRequest;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblEventName;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblTime;

    @FXML
    void btnAcceptPressed(ActionEvent event) {

    }

    @FXML
    void btnRequestPressed(ActionEvent event) {
    	try {
    		dbConnection con = new dbConnection();
        	Connection c = con.getConnection();
        	con.requestUser(this.user.getUserID(), this.event.getEventID());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
   
}
