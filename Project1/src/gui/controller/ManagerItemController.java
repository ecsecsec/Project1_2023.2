package gui.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import main.Event;
import main.EventList;
import main.User;

public class ManagerItemController {
	private Event event;
	private User user;
	
	public ManagerItemController(User user) {
		super();
		this.user = user;
	}
	public void setData(Event event) {
		this.event = event;
		if(event.isPrivate()) {
			lblIsPrivate.setText("-Private-");
		}else {
			lblIsPrivate.setText("-Public-");
		}

		lblEventName.setText(event.getEventName());
    	lblTime.setText(event.getStartTime() + "-" + event.getEndTime());
    	lblLocation.setText(event.getLocation());
    	lblDescription.setText(event.getDescription());
    	lblEventName.setText(event.getEventName());
	}
	@FXML
    private TableColumn<User, Boolean> colRequest;

    @FXML
    private TableColumn<User, Integer> colUserID;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblEventName;

    @FXML
    private Label lblIsPrivate;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblTime;

	
}
