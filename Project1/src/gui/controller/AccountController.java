package gui.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
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
    	lblPhoneNumber.setText(String.valueOf(user.getUserPhone()));
}
	@FXML
    private Button btnSUbmit;

    @FXML
    private ImageView imgAvt;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhoneNumber;

    @FXML
    private Label lblUserName;

    @FXML
    void btnSubmitPressed(ActionEvent event) {

    }

    @FXML
    void imgAvatarChange(MouseEvent event) {

    }

    @FXML
    void lblEmailChange(MouseEvent event) {

    }

    @FXML
    void lblPhoneNumberChange(ContextMenuEvent event) {

    }

    @FXML
    void lblUserNameChange(MouseEvent event) {

    }
}
