package com.patientsessiontracker.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.patientsessiontracker.core.User;
import com.patientsessiontracker.dao.*;

public class UserLoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JComboBox userComboBox;

	private PatientDAO patientDAO;
	
	public void setPatientDAO(PatientDAO thePatientDAO) {
		patientDAO = thePatientDAO;
	}

	public void populateUsers(List<User> users) {
		userComboBox.setModel(new DefaultComboBoxModel(users.toArray(new User[0])));
	}

	/**
	 * Create the dialog.
	 */
	public UserLoginDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("User Login");
		setBounds(100, 100, 450, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("User");
			lblUser.setBounds(10, 13, 46, 14);
			contentPanel.add(lblUser);
		}
		{
			userComboBox = new JComboBox();
			userComboBox.setBounds(107, 10, 317, 20);
			contentPanel.add(userComboBox);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(10, 38, 87, 14);
			contentPanel.add(lblPassword);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(107, 35, 317, 20);
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// get the user id
						if (userComboBox.getSelectedIndex() == -1) {						
							JOptionPane.showMessageDialog(UserLoginDialog.this, "You must select a user.", "Error", JOptionPane.ERROR_MESSAGE);				
							return;
						}
						
						User theUser = (User) userComboBox.getSelectedItem();
						int userId = theUser.getId();
						
						// get the password
						char[] password = passwordField.getPassword();
						
						// TO DO: you should add code here to check user id and password again db
						//		  Since this is a demo, we will not perform this check
						
						// hide the login window
						setVisible(false);
						dispose();
						
						// now show the main app window
						PatientSessionTrackerApp frame = new PatientSessionTrackerApp(userId, patientDAO);
						frame.setLoggedInUserName(theUser.getFirstName(), theUser.getLastName());
						
						frame.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public JComboBox getUserComboBox() {
		return userComboBox;
	}
}