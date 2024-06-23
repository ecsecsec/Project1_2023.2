package main;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Event {

	private int eventID;
	private String eventName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String description;
	private String location;
	private boolean isPrivate;
	private int hostID;
	
	public Event(String eventName, Timestamp startTime, Timestamp endTime, String description, String location,
			boolean isPrivate, int hostID) {
		super();
		this.eventName = eventName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.location = location;
		this.isPrivate = isPrivate;
		this.hostID = hostID;
	}
	
	
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

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
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
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
	public int getHostID() {
		return hostID;
	}
	public void setHostID(int hostID) {
		this.hostID = hostID;
	}



	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", description=" + description + ", location=" + location + ", isPrivate=" + isPrivate
				+ ", hostID=" + hostID + "]";
	}
	
	


}