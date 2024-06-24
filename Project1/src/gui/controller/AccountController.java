package gui.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;

import database.ConnectionUtil;
import encyption.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.*;

public class AccountController {
	public AccountController(User user) {
		super();
		this.user = user;
	}
	private User user;
	
	public void setData(User user) {
    	this.user = user;
    	lblUserName.setText(user.getUserName());
    	lblEmail.setText(user.getUserEmail());
    	lblPhoneNumber.setText(Integer.toString(user.getUserPhone()));
    	lblAge.setText(Integer.toString(user.getUserAge()));
    	editVbox.setVisible(false);
}

    @FXML
    private TextField ageField;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSUbmit;

    @FXML
    private VBox editVbox;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView imgAvt;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhoneNumber;

    @FXML
    private Label lblUserName;

    @FXML
    private TextField phoneField;

    @FXML
    void btnEditPressed(ActionEvent event) {
    	editVbox.setVisible(true);
    }

    @FXML
    void btnSubmitPressed(ActionEvent evt) {
    	try {
    		ConnectionUtil con = new ConnectionUtil();
			Connection c = con.getConnection("localhost", user.getUserName(), user.getPassword());
			if(!emailField.getText().isEmpty()) {
	    		String sql = "UPDATE user SET email =? WHERE user_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setString(1, Encryption.AESEncrypt(emailField.getText(), Encryption.generateKey()));
	    			st.setInt(2, this.user.getUserID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			emailField.clear();
	    		}
	    	}if(!phoneField.getText().isEmpty()) {
	    		String sql = "UPDATE user SET phone =? WHERE user_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setInt(1, Encryption.AESEncrypt(Integer.parseInt(phoneField.getText()), Encryption.generateKey()));
	    			st.setInt(2, this.user.getUserID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			phoneField.clear();
	    		}
	    	}if(!ageField.getText().isEmpty()) {
	    		String sql = "UPDATE user SET age =? WHERE user_id = ?";
	    		try(PreparedStatement st = c.prepareStatement(sql)){
	    			st.setInt(1, Encryption.AESEncrypt(Integer.parseInt(ageField.getText()), Encryption.generateKey()));
	    			st.setInt(2, this.user.getUserID());
	    			st.executeUpdate();
	    			System.out.println("Update successfully!");
	    			ageField.clear();
	    		}
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void imgAvatarChange(MouseEvent event) {

    }
}
