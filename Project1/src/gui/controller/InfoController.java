package gui.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import database.ConnectionUtil;
import encyption.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Event;
import main.LogIn;
import main.User;

public class InfoController {
	private Event event;
	private User user;
	public InfoController(User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}
	public void setData(Event event) {
		this.event = event;
	}

    @FXML
    private TextField LocationField;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox checkIsPrivate;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endTime;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblEndTime;

    @FXML
    private Label lblEventName;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblStartTime;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker startTime;

    @FXML
    void btnSavePressed(ActionEvent event) {
    	try {
			ConnectionUtil con = new ConnectionUtil();
			Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
			if(!nameField.getText().isEmpty()) {
	    		String sql = "UPDATE event SET event_Name =? WHERE event_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setString(1, Encryption.AESEncrypt(nameField.getText(), Encryption.generateKey()));
	    			st.setInt(2, this.event.getEventID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			st.close();
	    		}
	    	}if(!LocationField.getText().isEmpty()) {
	    		String sql = "UPDATE event SET location =? WHERE event_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setString(1, Encryption.AESEncrypt(LocationField.getText(), Encryption.generateKey()));
	    			st.setInt(2, this.event.getEventID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			st.close();
	    		}
	    	}if(!descriptionField.getText().isEmpty()) {
	    		String sql = "UPDATE event SET description =? WHERE event_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setString(1, Encryption.AESEncrypt(descriptionField.getText(), Encryption.generateKey()));
	    			st.setInt(2, this.event.getEventID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			st.close();
	    		}
	    	}c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

    }

}
