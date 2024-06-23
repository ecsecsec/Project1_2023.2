package gui.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import database.ConnectionUtil;
import database.dbConnection;
import encyption.Encryption;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;

import main.*;

public class HomeController {
	private EventList eventList;
	private Event event;
	private User user;
	private Connection c;
	public HomeController(User user) {
		super();
		this.user = user;
		try {
			ConnectionUtil con = new ConnectionUtil();
			c = con.getConnection("localhost", user.getUserName(), user.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

    @FXML
    private Button btnCreateEvent;

    @FXML
    private Button btnMyAccount;

    @FXML
    private Button btnNewEvent;

    @FXML
    private Button btnUserEvent;

    @FXML
    private Button btngManagerEvent;

    @FXML
    private GridPane gridPaneEvent;

  	//Thiếu tạo event mới
    @FXML
    void btnManagerEventClicked(ActionEvent evt) {
    	
    	gridPaneEvent.getChildren().clear();
    	//Change btn color
    	btnNewEvent.setStyle("-fx-background-color: #393E46;");
    	btnUserEvent.setStyle("-fx-background-color: #393E46;");
    	btngManagerEvent.setStyle("-fx-background-color: #00ADB5;");
    	btnMyAccount.setStyle("-fx-background-color: #393E46;");
    	btnCreateEvent.setStyle("-fx-background-color: #393E46;");
    	
        this.eventList = new EventList();
    	//Khởi tạo list ManagerEvent của user
        try {
            String sql = "SELECT event.* FROM event JOIN user ON event.host_id = user.user_id WHERE user.user_id = ?";
            //đúng
            try(PreparedStatement st = c.prepareStatement(sql)){
            	st.setInt(1, this.user.getUserID());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Event event = new Event();
                    event.setEventID(rs.getInt(1));
                    event.setHostID(rs.getInt(2));
                    event.setStartTime(rs.getTimestamp(5));
                    event.setEndTime(rs.getTimestamp(6));
                    event.setEventName(Encryption.AESDecrypt(rs.getString(12), Encryption.generateKey()));
                    event.setLocation(Encryption.AESDecrypt(rs.getString(7), Encryption.generateKey()));
                    event.setDescription(Encryption.AESDecrypt(rs.getString(4), Encryption.generateKey()));
                    event.setPrivate(rs.getBoolean(3));
                    this.eventList.addEvent(event);
                }
            }            
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        //Khởi tạo gridPane
        final String MNG_FXML = "/gui/view/ManagerItem.fxml";
        int col = 0;
        int row = 1;
        int num = this.eventList.getItems().size();
        for (int i = 0; i < num; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(MNG_FXML));
                ManagerItemController managerController = new ManagerItemController(user);
                fxmlLoader.setController(managerController);

                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println(this.eventList.getItems().get(i));
                managerController.setData(this.eventList.getItems().get(i));
                
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
    	//Change btn color
    	btnNewEvent.setStyle("-fx-background-color: #393E46;");
    	btnUserEvent.setStyle("-fx-background-color: #00ADB5;");
    	btngManagerEvent.setStyle("-fx-background-color: #393E46;");
    	btnMyAccount.setStyle("-fx-background-color: #393E46;");
    	btnCreateEvent.setStyle("-fx-background-color: #393E46;");
    	
        this.eventList = new EventList();
        //Khởi tạo list UserEvent của user
        try {
            String sql = "SELECT * FROM event JOIN user_event ON event.event_id = user_event.event_id WHERE user_event.user_id = ?";
        	//đúng
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, this.user.getUserID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setHostID(rs.getInt(2));
                event.setStartTime(rs.getTimestamp(5));
                event.setEndTime(rs.getTimestamp(6));
                event.setEventName(Encryption.AESDecrypt(rs.getString(12), Encryption.generateKey()));
                event.setLocation(Encryption.AESDecrypt(rs.getString(7), Encryption.generateKey()));
                event.setDescription(Encryption.AESDecrypt(rs.getString(4), Encryption.generateKey()));
                event.setPrivate(rs.getBoolean(3));
                this.eventList.addEvent(event);
            }

        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        //Khởi tạo gridPane
        final String ITEM_FXML = "/gui/view/UserItem.fxml";
        int col = 0;
        int row = 1;
        int num = this.eventList.getItems().size();
        for (int i = 0; i < num; i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML));
                UserItemController userController = new UserItemController(user);
                fxmlLoader.setController(userController);

                AnchorPane anchorPane = fxmlLoader.load();
                userController.setData(this.eventList.getItems().get(i));

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
    	//Change btn color
    	btnNewEvent.setStyle("-fx-background-color: #393E46;");
    	btnUserEvent.setStyle("-fx-background-color: #393E46;");
    	btngManagerEvent.setStyle("-fx-background-color: #393E46;");
    	btnMyAccount.setStyle("-fx-background-color: #00ADB5;");
    	btnCreateEvent.setStyle("-fx-background-color: #393E46;");
    	
    	final String ACC_FXML = "/gui/view/Account.fxml";
    	int col =1;
    	int row = 1;
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(ACC_FXML));
			AccountController accController = new AccountController(user);
			fxmlLoader.setController(accController);
			
			AnchorPane anchorPane = new AnchorPane();
			anchorPane = fxmlLoader.load();
			accController.setData(user);
			
			gridPaneEvent.add(anchorPane, col, row);
			GridPane.setMargin(anchorPane, null);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void imgNewEventClicked(ActionEvent evt) {
    	gridPaneEvent.getChildren().clear();
    	//Change btn color
    	btnNewEvent.setStyle("-fx-background-color: #00ADB5;");
    	btnUserEvent.setStyle("-fx-background-color: #393E46;");
    	btngManagerEvent.setStyle("-fx-background-color: #393E46;");
    	btnMyAccount.setStyle("-fx-background-color: #393E46;");
    	btnCreateEvent.setStyle("-fx-background-color: #393E46;");
    	//Khởi tạo list NewEvent của user
    	this.eventList = new EventList();
    	try {
            String sql = "SELECT * FROM event e WHERE (e.private_event = 0 AND e.event_id NOT IN (SELECT event_id FROM user_event WHERE user_id = ?)) OR (e.private_event = 1 AND e.event_id IN (SELECT event_id FROM invited_user WHERE user_id = ?))";
            //chưa fix
        	PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, this.user.getUserID());
            st.setInt(2, user.getUserID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt(1));
                event.setHostID(rs.getInt(2));
                event.setStartTime(rs.getTimestamp(5));
                event.setEndTime(rs.getTimestamp(6));
                event.setEventName(Encryption.AESDecrypt(rs.getString(12), Encryption.generateKey()));
                event.setLocation(Encryption.AESDecrypt(rs.getString(7), Encryption.generateKey()));
                event.setDescription(Encryption.AESDecrypt(rs.getString(4), Encryption.generateKey()));
                event.setPrivate(rs.getBoolean(3));
                this.eventList.addEvent(event);       
            }
 
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
    	//Khởi tạo gridPane
    	final String ITEM_FXML = "/gui/view/EventItem.fxml";
    	int col = 0;
    	int row = 1;
    	int num = this.eventList.getItems().size();
    	for(int i = 0; i < num; i++) {
    		try {
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(getClass().getResource(ITEM_FXML));
    			EventItemController eventController = new EventItemController(user);
    			fxmlLoader.setController(eventController);
    			
    			AnchorPane anchorPane = new AnchorPane();
    			anchorPane = fxmlLoader.load();
    			eventController.setData(this.eventList.getItems().get(i));
    			
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
    
    @FXML
    void btnCreateEventClicked(ActionEvent event) {
    	gridPaneEvent.getChildren().clear();
    	//Change btn color
    	btnNewEvent.setStyle("-fx-background-color: #393E46;");
    	btnUserEvent.setStyle("-fx-background-color: #393E46;");
    	btngManagerEvent.setStyle("-fx-background-color: #393E46;");
    	btnMyAccount.setStyle("-fx-background-color: #393E46;");
    	btnCreateEvent.setStyle("-fx-background-color: #00ADB5;");
    	
    	final String CREATE_FXML = "/gui/view/CreateEvent.fxml";
    	int col =1;
    	int row = 1;
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(CREATE_FXML));
			CreateController create = new CreateController(this.user);
			fxmlLoader.setController(create);
			
			AnchorPane anchorPane = new AnchorPane();
			anchorPane = fxmlLoader.load();
			
			gridPaneEvent.add(anchorPane, col, row);
			GridPane.setMargin(anchorPane, null);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}

