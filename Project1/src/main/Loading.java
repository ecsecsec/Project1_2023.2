package main;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;

import javax.swing.*;

public class Loading extends JFrame implements Runnable{
	private JPanel panel;
	private JProgressBar progressBar;
	Connection c;
	String username;
	int s;
	Thread th;
	
	public static void main(String[] args) {
		new Loading("").setVisible(true);
		
	}
	public void setUpLoading() {
		setVisible(false);
		th.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i =0; i<200; i++) {
				s = s+1;
				int m = progressBar.getMaximum();
				int v = progressBar.getValue();
				if(v<m) {
					progressBar.setValue(v +1);
					
				}else {
					i = 201;
					setVisible(false);
					new Home(username).setVisible(true);
				}
				Thread.sleep(50);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Loading(String username) {
		this.username = username;
        th = new Thread((Runnable) this);

        setBounds(600, 300, 600, 400);
        panel = new JPanel();
        panel.setBackground(Color.pink);
        setContentPane(panel);
        panel.setLayout(null);
        
        JLabel lbM = new JLabel("Event Management App");
        lbM.setForeground(Color.blue);
        lbM.setFont(getFont());
        lbM.setBounds(50, 50, 700, 35);
        panel.add(lbM);
        
        progressBar = new JProgressBar();
        progressBar.setFont(getFont());
        progressBar.setStringPainted(true);
        progressBar.setBounds(130, 130, 300, 25);
        panel.add(progressBar);
        
        JLabel lbN = new JLabel("Please Wait....");
        lbN.setFont(getFont());
        lbN.setForeground(Color.white);
        lbN.setBounds(200, 165, 150, 20);
        panel.add(lbN);
        
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBounds(10, 10, 580, 380);
        panel.add(p);
        
        setUndecorated(true);
        setUpLoading();
	}

}
