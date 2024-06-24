package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.dbConnection;

public class EventList {
	private ArrayList<Event>items = new ArrayList<Event>();
	public ArrayList<Event> getItems() {
		return items;
	}

	public void setItems(ArrayList<Event> items) {
		this.items = items;
	}
	
	public void createEvent(Event event) {
		
	}
	public void addEvent(Event event) {
		items.add(event);
	}
	public void removeEvent(Event event) {
		items.remove(event);
	}

	
	public void print() {
		for(Event e: items) {
			System.out.println(e.toString());
		}

	}

}
