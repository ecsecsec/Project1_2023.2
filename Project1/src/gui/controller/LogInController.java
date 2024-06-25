package gui.controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.*;

public class LogInController {
	private User user;
	public LogInController() {
		super();
		
	}
	@FXML
	private Button btnForgotpassword;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button btnSignup;
	
	
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

	@FXML
	void btnLoginPressed(ActionEvent event) throws SQLException {
	    LogIn login = new LogIn(nameField.getText(), passwordField.getText());
	    this.user = login;
	    if (login.isCount()) {
	        try {
	        	
	            final String HOME = "/gui/view/Home.fxml";
	            FXMLLoader fxml = new FXMLLoader(getClass().getResource(HOME));
	            HomeController homeController = new HomeController(user); // Pass the eventList object
	            fxml.setController(homeController);
	            Parent root = fxml.load();

				Object eventSource = event.getSource();
	    		Node sourceAsNode = (Node) eventSource;
	    		Scene oldScene = sourceAsNode.getScene();
	    		Window window = oldScene.getWindow();
	    		Stage stage = (Stage)window;
	    		
	    		stage.setScene(new Scene(root));
	    		stage.setTitle("EVENT MANAGEMENT SYSTEM");
	    		stage.show();
	    		
			}catch(Exception e) {
				e.printStackTrace();
			}
	    }else {
	    	System.out.println("Password is wrong or User does not exits!");
	    	nameField.clear();
	    	passwordField.clear();
	    	
	    }
	}

	@FXML
	void btnSigupPressed(ActionEvent event) {
		try {
			final String SIGNUP = "/gui/view/SignUp.fxml";
			FXMLLoader fxml = new FXMLLoader(getClass().getResource(SIGNUP));
			fxml.setController(new SignUpController());
			Parent root = fxml.load();
			
			
			Object eventSource = event.getSource();
    		Node sourceAsNode = (Node) eventSource;
    		Scene oldScene = sourceAsNode.getScene();
    		Window window = oldScene.getWindow();
    		Stage stage = (Stage)window;
    		
    		stage.setScene(new Scene(root));
    		stage.setTitle("Sign Up");
    		stage.show();
    		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
