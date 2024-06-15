package gui.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import database.dbConnection;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;

import main.*;

public class HomeController {
	private Event event;
	private User user;
	public HomeController(User user) {
		super();
		this.user = user;
	}
	
	@FXML
    private Button btnFind;

    @FXML
    private RadioButton btnIsPrivate;

    @FXML
    private Button btnMyAccount;

    @FXML
    private RadioButton btnName;

    @FXML
    private Button btnNewEvent;

    @FXML
    private Button btnUserEvent;

    @FXML
    private Button btngManagerEvent;

    @FXML
    private TextField filter;

    @FXML
    private GridPane gridPaneEvent;

    @FXML
    private DatePicker dateFilter;
    
    @FXML
    void dateFilterPicked(ActionEvent event) {

    }

    @FXML
    void btnFindPressed(ActionEvent event) {

    }

    @FXML
    void btnIsPrivateChoosed(ActionEvent event) {

    }
    
    @FXML
    void btnNameChoosed(ActionEvent event) {

    }
    
    @FXML
    void btnManagerEventClicked(ActionEvent evt) {
        gridPaneEvent.getChildren().clear();
        EventList evtList = new EventList();

        try {
            dbConnection con = new dbConnection();
            Connection c = con.getConnection();
            String sql = "SELECT * FROM event LEFT JOIN user_event ON event.event_id = user_event.event_id WHERE user_event.user_id = ?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, this.user.getUserID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setEventName(rs.getString(12));
                event.setLocation(rs.getString(7));
                event.setDescription(rs.getString(4));
                event.setPrivate(rs.getBoolean(3));
                evtList.addEvent(event);
            }

        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }

        final String MNG_FXML = "/gui/view/ManagerItem.fxml";
        int col = 0;
        int row = 1;
        int num = evtList.getItems().size();
        for (int i = 0; i < num; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(MNG_FXML));
                ManagerItemController managerController = new ManagerItemController(user);
                fxmlLoader.setController(managerController);

                AnchorPane anchorPane = fxmlLoader.load();
                managerController.setData(evtList.getItems().get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }
                gridPaneEvent.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 0, 0, 0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnUserEventClicked(ActionEvent evt) {
        gridPaneEvent.getChildren().clear();
        EventList evtList = new EventList();

        try {
            dbConnection con = new dbConnection();
            Connection c = con.getConnection();
            String sql = "SELECT * FROM event LEFT JOIN user_event ON event.event_id = user_event.event_id WHERE user_event.user_id = ?";
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, this.user.getUserID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setEventName(rs.getString(12));
                event.setLocation(rs.getString(7));
                event.setDescription(rs.getString(4));
                event.setPrivate(rs.getBoolean(3));
                evtList.addEvent(event);
            }

        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }

        final String ITEM_FXML = "/gui/view/UserItem.fxml";
        int col = 0;
        int row = 1;
        int num = evtList.getItems().size();
        for (int i = 0; i < num; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML));
                InvitedItemController invitedController = new InvitedItemController(user);
                fxmlLoader.setController(invitedController);

                AnchorPane anchorPane = fxmlLoader.load();
                invitedController.setData(evtList.getItems().get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }
                gridPaneEvent.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 0, 0, 0));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void imgMyAccountClicked(ActionEvent evt) {
    	gridPaneEvent.getChildren().clear();
    	
    	final String ACC_FXML = "/gui/view/Account.fxml";
    	int col =1;
    	int row = 1;
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(ACC_FXML));
			AccountController accController = new AccountController();
			fxmlLoader.setController(accController);
			
			AnchorPane anchorPane = new AnchorPane();
			anchorPane = fxmlLoader.load();
			//accController.setData();
			
			gridPaneEvent.add(anchorPane, col, row);
			GridPane.setMargin(anchorPane, null);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void imgNewEventClicked(ActionEvent evt) {
    	gridPaneEvent.getChildren().clear();

    	
    	final String ITEM_FXML = "/gui/view/EventItem.fxml";
    	int col = 0;
    	int row = 1;
    	//int num = this.eventList.getItems().size();
    	for(int i = 0; i < 10; i++) {
    		try {
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(getClass().getResource(ITEM_FXML));
    			EventItemController eventController = new EventItemController(user);
    			fxmlLoader.setController(eventController);
    			
    			AnchorPane anchorPane = new AnchorPane();
    			anchorPane = fxmlLoader.load();
    			eventController.setData(eventController.getEventList().getItems().get(i));
    			
    			 if(col == 1) {
    				 col = 0;
    				 row++;
    			 }
    			 gridPaneEvent.add(anchorPane, col++, row);
    			 GridPane.setMargin(anchorPane, new Insets(20, 0, 0, 0));
    			 
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}

