package main;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import database.dbConnection;

import java.sql.*;


public class LogIn extends JFrame implements ActionListener{
	private JPanel panel;
	private JTextField username;
	private JPasswordField password;
	private JButton b1, b2, b3;
	
	public LogIn() {
		
		setBackground(new Color(255, 255, 204));
		setBounds(550, 250, 700, 400);
		
		panel = new JPanel();
	
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("UserName: ");
		l1.setBounds(124, 89, 95, 24 );
		panel.add(l1);
		
		JLabel l2 = new JLabel("Password: ");
		l2.setBounds(124, 124, 95, 24 );
		panel.add(l2);
		
		username = new JTextField();
		username.setBounds(210, 93, 157, 20);
		panel.add(username);
		
		password = new JPasswordField();
		password.setBounds(210, 128, 157, 20);
		panel.add(password);
		
		JLabel l3 = new JLabel("");
		l3.setBounds(377, 79, 46, 34);
		panel.add(l3);

		JLabel l4 = new JLabel("");
		l4.setBounds(377, 124, 46, 34);
		panel.add(l4);
		
		b1 = new JButton("Log In");
		b1.addActionListener(this);
		
		b1.setForeground(new Color(255, 255, 255));
		b1.setBackground(new Color(255,192,203));
		b1.setBounds(149, 181, 113, 25);
		panel.add(b1);
		
		b2 = new JButton("Sign Up");
		b2.addActionListener(this);
		
		b2.setForeground(new Color(255, 255, 255));
		b2.setBackground(new Color(135,206,235));
		b2.setBounds(289, 181, 113, 25);
		panel.add(b2);
		
		b3 = new JButton("Forgot Password");
		b3.addActionListener(this);
		
		b3.setForeground(new Color(255, 255, 255));
		b3.setBackground(new Color(218,112,214));
		b3.setBounds(199, 231, 179, 25);
		panel.add(b3);
		
		JLabel l5 = new JLabel("Trouble in Login?");
		l5.setFont(getFont());
		l5.setForeground(new Color(199,21,133));
		l5.setBounds(70, 235, 110, 20);
		panel.add(l5);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getSource() == b1) {
			boolean status = false;
			try {
				dbConnection con = new dbConnection();
				Connection c = con.getConnection();
				String sql = "select * from User where username = ? and password = ?";
				PreparedStatement st = c.prepareStatement(sql);
				st.setString(1, username.getText());
				st.setString(2, password.getText());
				
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					this.setVisible(false);
					new Loading(username.getText()).setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Invalid Login!" );
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(evt.getSource() == b2) {
			setVisible(false);
			SignUp s = new SignUp();
			s.setVisible(true);
			
		}else if(evt.getSource() == b3) {
			setVisible(false);
			ForgotPassword forgot = new ForgotPassword();
			forgot.setVisible(true);
		}
		
	}
	public static void main(String[] args) {
		new LogIn().setVisible(true);
	}
}
