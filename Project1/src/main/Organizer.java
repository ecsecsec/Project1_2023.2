package main;

import java.util.ArrayList;

public class Organizer extends User{

	private int orgID;
	private int jobID;
	private int eventID;
	private ArrayList<User> organizers = new ArrayList<User>();
	
	public Organizer(int userID, String userName, String password, int userAge, int userPhone, String userEmail) {
		super(userID, userName, password, userAge, userPhone, userEmail);

	}
	public int getOrgID() {
		return orgID;
	}
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public ArrayList<User> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(ArrayList<User> organizers) {
		this.organizers = organizers;
	}
	
	public Organizer(int userID, String userName, String password, int userPhone, String userEmail, int orgID,
			int jobID, int eventID, ArrayList<User> organizers) {
		super(userID, userName, password, userPhone, userEmail);
		this.orgID = orgID;
		this.jobID = jobID;
		this.eventID = eventID;
		this.organizers = organizers;
	}
	
}
