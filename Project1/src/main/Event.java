package main;
import java.util.Date;
import java.util.List;

public class Event {
	private int eventID;
	private String eventName;
	private String startTime;
	private String endTime;
	private String description;
	private String location;
	private boolean isPrivate;
	private List<User> attendees;
	private List<Organizer> orgs;
	
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public List<Organizer> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Organizer> orgs) {
		this.orgs = orgs;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

	public Event(String eventName, String startTime, String endTime, String description, String location,
			boolean isPrivate, List<User> attendees, List<Organizer> orgs) {
		super();
		this.eventName = eventName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.location = location;
		this.isPrivate = isPrivate;
		this.attendees = attendees;
		this.orgs = orgs;
	}

}