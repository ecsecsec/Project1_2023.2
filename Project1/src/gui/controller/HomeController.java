package gui.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.*;

public class HomeController {

	private EventList eventList;
	private Event event;
	private User user;
	public HomeController(User user) {
		super();
		this.user = user;
		this.eventList = new EventList();
		try {
        	dbConnection con = new dbConnection();
        	Connection c = con.getConnection();
        	String sql = "SELECT * FROM `event`";
            //String sql = "SELECT * FROM `event` join user_event on event.event_id != user_event.event_id where user_event.user_id = ?";
            PreparedStatement st = c.prepareStatement(sql);
            //st.setInt(1, this.user.getUserID());
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
		
		
		
		
	}
	public void initialize() {
	 	
	}
	@FXML
    private Button btnNewEvent;

    @FXML
    private Button btnUserEvent;

    @FXML
    private Button btngManagerEvent;

    @FXML
    private GridPane gridPaneEvent;
    @FXML
    void btnManagerEventClicked(ActionEvent event) {
    	
    }

    @FXML
    void btnUserEventClicked(ActionEvent event) {

    }

    @FXML
    void imgNewEventClicked(ActionEvent event) {
    	final String ITEM_FXML = "/gui/view/EventItem.fxml";
    	int col = 0;
    	int row = 1;
    	int num = this.eventList.getItems().size();
    	for(int i = 0; i < num; i++) {
    		try {
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(getClass().getResource(ITEM_FXML));
    			EventItemController eventItemController = new EventItemController(user);
    			fxmlLoader.setController(eventItemController);
    			
    			AnchorPane anchorPane = new AnchorPane();
    			anchorPane = fxmlLoader.load();
    			eventItemController.setData(eventList.getItems().get(i));
    			
    			 if(col == 1) {
    				 col = 0;
    				 row++;
    			 }
    			 gridPaneEvent.add(anchorPane, col++, row);
    			 GridPane.setMargin(anchorPane, new Insets(20, 0, 0, 0));
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
    }


}
