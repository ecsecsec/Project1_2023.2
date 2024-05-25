package main;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.*;

public class Home extends JFrame{
	
	String userName;
	public static void main(String[] args) {
		new Home("").setVisible(true);
	}
	
	public Home(String userName) {
		super("Event Management App");
		this.userName = userName;
		setForeground(Color.pink);
		setLayout(null);
	}
}
