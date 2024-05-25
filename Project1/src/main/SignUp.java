  package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.*;

import database.dbConnection;

public class SignUp extends JFrame implements ActionListener {
	private JPanel panel;
	private JTextField username;
	private JTextField email;
	private JPasswordField password;
	private JTextField age;
	private JButton b1, b2;

	
	
	public SignUp() {
		setBounds(600, 250, 700, 406);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel lbname = new JLabel("Username *");
		lbname.setForeground(Color.DARK_GRAY);
		lbname.setFont(getFont());
		lbname.setBounds(100, 50, 100, 25);
		panel.add(lbname);
		
		JLabel lbemail = new JLabel("Email *");
		lbemail.setForeground(Color.DARK_GRAY);
		lbemail.setFont(getFont());
		lbemail.setBounds(100, 100, 100, 25);
		panel.add(lbemail);
		
		JLabel lbpassword = new JLabel("Password *");
		lbpassword.setForeground(Color.DARK_GRAY);
		lbpassword.setFont(getFont());
		lbpassword.setBounds(100, 150, 100, 25);
		panel.add(lbpassword);
		
		JLabel lbage = new JLabel("Age");
		lbage.setForeground(Color.DARK_GRAY);
		lbage.setFont(getFont());
		lbage.setBounds(100, 200, 100, 25);
		panel.add(lbage);
		
		username = new JTextField();
		username.setBounds(100, 75, 100, 25);
		panel.add(username);
		username.setColumns(10);
		
		email = new JTextField();
		email.setBounds(100, 125, 100, 25);
		email.setColumns(10);
		panel.add(email);
		
		password = new JPasswordField();
		password.setBounds(100, 175, 100, 25);
		password.setColumns(10);
		panel.add(password);
		
		age = new JTextField();
		age.setBounds(100, 225, 100, 25);
		age.setColumns(10);
		panel.add(age);
		
		b1 = new JButton("Create");
		b1.addActionListener(this);
		b1.setFont(getFont());
		b1.setBounds(140, 275, 100, 30);
		b1.setBackground(Color.pink);
		b1.setForeground(Color.DARK_GRAY);
		panel.add(b1);
		
		b2 = new JButton("Back");
		b2.addActionListener(this);
		b2.setFont(getFont());
		b2.setBounds(400, 275, 100, 30);
		b2.setBackground(Color.pink);
		b2.setForeground(Color.DARK_GRAY);
		panel.add(b2);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setForeground(Color.green);
		contentPanel.setBorder(new TitledBorder( new LineBorder(Color.green, 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, Color.green  ));
		contentPanel.setBounds(30, 30, 640, 310);
		contentPanel.setBackground(Color.WHITE);
		panel.add(contentPanel);
	}
	
	
	
	public static void main(String[] args) {
		new SignUp().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			dbConnection con = new dbConnection();
			Connection c = con.getConnection();
			if(evt.getSource() == b1) {
				String sql = "insert into user(username, email, password, age) value(?, ?, ?, ?)";
				PreparedStatement st = c.prepareStatement(sql);
				
				st.setString(1, username.getText());
				st.setString(2, email.getText());
				st.setString(3, password.getText());
				st.setString(4, age.getText());
				
				int i = st.executeUpdate();
				if(i>0) {
					JOptionPane.showMessageDialog(null, "Account Created Successfully!");
					
				}
				username.setText("");
				email.setText("");
				password.setText("");
				age.setText("");
				
			}else if(evt.getSource() == b2) {
				this.setVisible(false);
				new LogIn().setVisible(true);
			}
						
		}catch(Exception e) {
			System.out.println(e);
			
		}
		
	}

}
