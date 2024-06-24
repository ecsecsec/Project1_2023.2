 package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.dbConnection;
import gui.controller.HomeController;
import gui.controller.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.Event;
import main.EventList;

public class Test extends Application{
	private static EventList eventList;
	public static void main(String[] args) {
        
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception {

        
		final String LOGIN = "/gui/view/LogIn.fxml";
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(LOGIN));
		fxml.setController(new LogInController());
		Parent root = fxml.load();
		
		primaryStage.setTitle("Home");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
