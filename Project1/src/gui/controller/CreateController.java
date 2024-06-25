package gui.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
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
import main.*;

public class CreateController {
	private User user;
	private Event event;
	public CreateController(User user) {
		super();
		this.user = user;
	}
	
	@FXML
	public void initialize(){
		startTime.setValue(LocalDate.now());
		endTime.setValue(LocalDate.now());
	}

    @FXML
    private TextField LocationField;

    @FXML
    private Button btnCreate;

    @FXML
    private CheckBox checkboxIsPrivate;

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
    void btnCreatePressed(ActionEvent evt) {//nên tạo hàm ở connectionutil
    	if(!LocationField.getText().isEmpty() && !nameField.getText().isEmpty() && !descriptionField.getText().isEmpty()) {
    		try {
        		ConnectionUtil con = new ConnectionUtil();
    			Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
    			String sql = "INSERT INTO event(host_id, private_event, description, start_time, end_time, location, event_Name) VALUES(?, ?, ?, ?, ?, ?, ?) ";
    			PreparedStatement st = c.prepareStatement(sql);
    			st.setInt(1, this.user.getUserID());
    			st.setBoolean(2, checkboxIsPrivate.isSelected());//cần sửa fxml
    			st.setString(3, Encryption.AESEncrypt(descriptionField.getText(), Encryption.generateKey()));
    			st.setTimestamp(4, Timestamp.valueOf(startTime.getValue().atStartOfDay()));
    			st.setTimestamp(5, Timestamp.valueOf(endTime.getValue().atStartOfDay()));
    			st.setString(6, Encryption.AESEncrypt(LocationField.getText(), Encryption.generateKey()));
    			st.setString(7, Encryption.AESEncrypt(nameField.getText(), Encryption.generateKey()));
    			st.executeUpdate();
    			System.out.println("Create event successfully!");
    			LocationField.clear();
    			nameField.clear();
    			descriptionField.clear();
    			checkboxIsPrivate.setSelected(false);
    			st.close();
    			c.close();
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}else {
    		System.out.println("Please fill all of information!");
    	}
    }
}
