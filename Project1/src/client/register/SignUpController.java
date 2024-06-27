package client.register;
import client.login.LogInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.*;

public class SignUpController {
	private EventList eventList;
	private User user;
	@FXML
    private Button btnBack;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;
    
    @FXML
    void btnBackPressed(ActionEvent event) {
    	try {
    		final String LOGIN = "/gui/view/LogIn.fxml";
			FXMLLoader fxml = new FXMLLoader(getClass().getResource(LOGIN));
			fxml.setController(new LogInController());
			Parent root = fxml.load();
			
			
			Object eventSource = event.getSource();
    		Node sourceAsNode = (Node) eventSource;
    		Scene oldScene = sourceAsNode.getScene();
    		Window window = oldScene.getWindow();
    		Stage stage = (Stage)window;
    		
    		stage.setScene(new Scene(root));
    		stage.setTitle("Log In");
    		stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void btnSignUpPressed(ActionEvent event) {
    	if(password.getText().equals(confirmPassword.getText())) {
    		SignUp signup = new SignUp(userName.getText(), email.getText(), password.getText());
    		if(signup.isCount()) {
    			System.out.println("Created account. Please login to use!");
    			try {
    	    		final String LOGIN = "/gui/view/LogIn.fxml";
    				FXMLLoader fxml = new FXMLLoader(getClass().getResource(LOGIN));
    				fxml.setController(new LogInController());
    				Parent root = fxml.load();
    				
    				
    				Object eventSource = event.getSource();
    	    		Node sourceAsNode = (Node) eventSource;
    	    		Scene oldScene = sourceAsNode.getScene();
    	    		Window window = oldScene.getWindow();
    	    		Stage stage = (Stage)window;
    	    		
    	    		stage.setScene(new Scene(root));
    	    		stage.setTitle("Log In");
    	    		stage.show();
    	    	}catch(Exception e) {
    	    		e.printStackTrace();
    	    	}
    		}else {
    			System.out.println("Cannot create user. Please try again!");
    		}
    	}else {
    		System.out.println("Password is incorrect. Please try again!");
    	}
    }
}
