package main;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Home extends JFrame{
	
	String username;
	public static void main(String[] args) {
		new Home("").setVisible(true);
	}
	
	public Home(String userName) {
		super("Event Management App");
		this.username = username;
		setForeground(Color.pink);
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("main/icon/home.png"));
		Image i2 = i1.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel newlb = new JLabel(i3);
		newlb.setBounds(0, 0, 1920, 1080);
		add(newlb);
		
		JLabel l1 = new JLabel("Event Management System");
		l1.setForeground(Color.pink);
		l1.setFont(getFont());
		l1.setBounds(500, 60, 1000, 100);
		newlb.add(l1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu m1 = new JMenu("USER");
		m1.setForeground(Color.blue);
		menuBar.add(m1);
		
		JMenuItem m11 =  new JMenuItem("UPDATE INFORMATION");
		m1.add(m11);
		
		JMenuItem m12 = new JMenuItem("EVENT LIST");
		m1.add(m12);
		
		JMenuItem m13 = new JMenuItem("LOG OUT");
		m1.add(m13);
		
		
		
		JMenu m2 = new JMenu("EVENT");
		m2.setForeground(Color.gray);
		menuBar.add(m2);
		
		JMenuItem m21 = new JMenuItem("ALL EVENT");
		m2.add(m21);
		
		JMenuItem m22 = new JMenuItem("SEARCH EVENT");
		m2.add(m22);
		
		JMenuItem m23 = new JMenuItem("CREATE EVENT");
		m2.add(m23);
		
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		getContentPane().setBackground(Color.white);
		
	}
}
