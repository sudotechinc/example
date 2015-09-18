package com.patientsessiontracker.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.patientsessiontracker.core.*;
import com.patientsessiontracker.dao.*;

public class PatientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFieldFName;
	private JTextField txtFieldMName;
	private JTextField txtFieldLName;
	
	private PatientDAO patientDAO;
	private int userId;
	
	private PatientSessionTrackerApp patientSessionTrackerApp;
	
	private Patient previousPatient = null;
	private boolean updateMode = false;
	
	private JTextField txtFieldAQR;
	private JTextField txtFieldAT;
	private JTextField txtFieldAWT;
	private JTextField txtFieldCCT;
	private JTextField txtFieldCMT;
	private JTextField txtFieldEMS;
	private JTextField txtFieldGT;
	private JTextField txtFieldHKM;
	private JTextField txtFieldIST;
	private JTextField txtFieldLASER;
	private JTextField txtFieldNFB;
	private JTextField txtFieldNRT;
	private JTextField txtFieldNT;
	private JTextField txtFieldPMT;
	private JTextField txtFieldRIFE;
	private JTextField txtFieldRRT;
	private JTextField txtFieldSRT;
	private JTextField txtFieldTRX;
	private JTextField txtFieldUS;
	private JTextField txtFieldVIBE;
	
	public PatientDialog(PatientSessionTrackerApp thePatientSessionTracker, PatientDAO thePatientDAO, Patient thePreviousPatient, boolean theUpdateMode, int theUserId) {
		this();
		patientDAO = thePatientDAO;
		patientSessionTrackerApp = thePatientSessionTracker;
		
		previousPatient = thePreviousPatient;
		
		updateMode = theUpdateMode;
		
		if (updateMode) {
			setTitle("Update Patient");
			
			populateGui(previousPatient);
		}
	}
	
	private void populateGui(Patient thePatient) {
		
		txtFieldFName.setText(thePatient.getFirstName());
		txtFieldMName.setText(thePatient.getMiddleName());
		txtFieldLName.setText(thePatient.getLastName());
		
		txtFieldAQR.setText(String.valueOf(thePatient.getAQR()));
		txtFieldAT.setText(String.valueOf(thePatient.getAT()));
		txtFieldAWT.setText(String.valueOf(thePatient.getAWT()));
		txtFieldCCT.setText(String.valueOf(thePatient.getCCT()));
		txtFieldCMT.setText(String.valueOf(thePatient.getCMT()));
		txtFieldEMS.setText(String.valueOf(thePatient.getEMS()));
		txtFieldGT.setText(String.valueOf(thePatient.getGT()));
		txtFieldHKM.setText(String.valueOf(thePatient.getHKM()));
		txtFieldIST.setText(String.valueOf(thePatient.getIST()));
		txtFieldLASER.setText(String.valueOf(thePatient.getLASER()));
		txtFieldNFB.setText(String.valueOf(thePatient.getNFB()));
		txtFieldNRT.setText(String.valueOf(thePatient.getNRT()));
		txtFieldNT.setText(String.valueOf(thePatient.getNT()));
		txtFieldPMT.setText(String.valueOf(thePatient.getPMT()));
		txtFieldRIFE.setText(String.valueOf(thePatient.getRIFE()));
		txtFieldRRT.setText(String.valueOf(thePatient.getRRT()));
		txtFieldSRT.setText(String.valueOf(thePatient.getSRT()));
		txtFieldTRX.setText(String.valueOf(thePatient.getTRX()));
		txtFieldUS.setText(String.valueOf(thePatient.getUS()));
		txtFieldVIBE.setText(String.valueOf(thePatient.getVIBE()));
	}
	
	public PatientDialog(PatientSessionTrackerApp thePatientSessionTrackerApp,
			PatientDAO thePatientDAO, int theUserId) {
		this(thePatientSessionTrackerApp, thePatientDAO, null, false, theUserId);
	}

	/**
	 * Create the dialog.
	 */
	public PatientDialog() {
		setSize(500, 600);
		setResizable(false);
		setTitle("Add New Patient");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFName = new JLabel("<html>First Name<font color='red'>*</font></html>");
			lblFName.setBounds(10, 11, 104, 14);
			contentPanel.add(lblFName);

			txtFieldFName = new JTextField();
			txtFieldFName.setBounds(124, 8, 360, 20);
			contentPanel.add(txtFieldFName);
			txtFieldFName.setColumns(10);
		
			JLabel lblMName = new JLabel("Middle Name");
			lblMName.setBounds(10, 36, 104, 14);
			contentPanel.add(lblMName);
		
			txtFieldMName = new JTextField();
			txtFieldMName.setBounds(124, 33, 360, 20);
			contentPanel.add(txtFieldMName);
			txtFieldMName.setColumns(10);
		
			JLabel lblLName = new JLabel("<html>Last Name<font color='red'>*</font></html>");
			lblLName.setBounds(10, 61, 104, 14);
			contentPanel.add(lblLName);
		
			txtFieldLName = new JTextField();
			txtFieldLName.setBounds(124, 58, 360, 20);
			contentPanel.add(txtFieldLName);
			txtFieldLName.setColumns(10);
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("Save Patient");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					savePatient();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel lblAQR = new JLabel("AQR");
		lblAQR.setBounds(10, 108, 43, 14);
		contentPanel.add(lblAQR);
		
		txtFieldAQR = new JTextField();
		txtFieldAQR.setText("0");
		txtFieldAQR.setBounds(63, 105, 43, 20);
		contentPanel.add(txtFieldAQR);
		txtFieldAQR.setColumns(10);
		PlainDocument AQR = (PlainDocument) txtFieldAQR.getDocument();
		AQR.setDocumentFilter(new IntegerFilter());
		
		JLabel lblAT = new JLabel("AT");
		lblAT.setBounds(124, 108, 22, 14);
		contentPanel.add(lblAT);
		
		txtFieldAT = new JTextField();
		txtFieldAT.setText("0");
		txtFieldAT.setColumns(10);
		txtFieldAT.setBounds(156, 105, 43, 20);
		contentPanel.add(txtFieldAT);
		PlainDocument AT = (PlainDocument) txtFieldAT.getDocument();
		AT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblAWT = new JLabel("AWT");
		lblAWT.setBounds(209, 108, 43, 14);
		contentPanel.add(lblAWT);
		
		txtFieldAWT = new JTextField();
		txtFieldAWT.setText("0");
		txtFieldAWT.setColumns(10);
		txtFieldAWT.setBounds(262, 105, 43, 20);
		contentPanel.add(txtFieldAWT);
		PlainDocument AWT = (PlainDocument) txtFieldAWT.getDocument();
		AWT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblNoteSessions = new JLabel("Note: A zero must procede the number of sessions entered.");
		lblNoteSessions.setBounds(10, 83, 474, 14);
		contentPanel.add(lblNoteSessions);
		
		JLabel lblCCT = new JLabel("CCT");
		lblCCT.setBounds(315, 108, 43, 14);
		contentPanel.add(lblCCT);
		
		txtFieldCCT = new JTextField();
		txtFieldCCT.setText("0");
		txtFieldCCT.setColumns(10);
		txtFieldCCT.setBounds(368, 105, 43, 20);
		contentPanel.add(txtFieldCCT);
		PlainDocument CCT = (PlainDocument) txtFieldCCT.getDocument();
		CCT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblCMT = new JLabel("CMT");
		lblCMT.setBounds(10, 133, 43, 14);
		contentPanel.add(lblCMT);
		
		txtFieldCMT = new JTextField();
		txtFieldCMT.setText("0");
		txtFieldCMT.setColumns(10);
		txtFieldCMT.setBounds(63, 130, 43, 20);
		contentPanel.add(txtFieldCMT);
		PlainDocument CMT = (PlainDocument) txtFieldCMT.getDocument();
		CMT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblEMS = new JLabel("EMS");
		lblEMS.setBounds(124, 133, 43, 14);
		contentPanel.add(lblEMS);
		
		txtFieldEMS = new JTextField();
		txtFieldEMS.setText("0");
		txtFieldEMS.setColumns(10);
		txtFieldEMS.setBounds(156, 130, 43, 20);
		contentPanel.add(txtFieldEMS);
		PlainDocument EMS = (PlainDocument) txtFieldEMS.getDocument();
		EMS.setDocumentFilter(new IntegerFilter());
		
		JLabel lblGT = new JLabel("G T");
		lblGT.setBounds(209, 133, 43, 14);
		contentPanel.add(lblGT);
		
		txtFieldGT = new JTextField();
		txtFieldGT.setText("0");
		txtFieldGT.setColumns(10);
		txtFieldGT.setBounds(262, 130, 43, 20);
		contentPanel.add(txtFieldGT);
		PlainDocument GT = (PlainDocument) txtFieldGT.getDocument();
		GT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblHKM = new JLabel("HKM");
		lblHKM.setBounds(315, 133, 43, 14);
		contentPanel.add(lblHKM);
		
		txtFieldHKM = new JTextField();
		txtFieldHKM.setText("0");
		txtFieldHKM.setColumns(10);
		txtFieldHKM.setBounds(368, 130, 43, 20);
		contentPanel.add(txtFieldHKM);
		PlainDocument HKM = (PlainDocument) txtFieldHKM.getDocument();
		HKM.setDocumentFilter(new IntegerFilter());
		
		JLabel lblIST = new JLabel("IST");
		lblIST.setBounds(10, 158, 43, 14);
		contentPanel.add(lblIST);
		
		txtFieldIST = new JTextField();
		txtFieldIST.setText("0");
		txtFieldIST.setColumns(10);
		txtFieldIST.setBounds(63, 155, 43, 20);
		contentPanel.add(txtFieldIST);
		PlainDocument IST = (PlainDocument) txtFieldIST.getDocument();
		IST.setDocumentFilter(new IntegerFilter());
		
		JLabel lblLASER = new JLabel("LASER");
		lblLASER.setBounds(124, 158, 43, 14);
		contentPanel.add(lblLASER);
		
		txtFieldLASER = new JTextField();
		txtFieldLASER.setText("0");
		txtFieldLASER.setColumns(10);
		txtFieldLASER.setBounds(166, 155, 43, 20);
		contentPanel.add(txtFieldLASER);
		PlainDocument LASER = (PlainDocument) txtFieldLASER.getDocument();
		LASER.setDocumentFilter(new IntegerFilter());
		
		JLabel lblNFB = new JLabel("NFB");
		lblNFB.setBounds(219, 158, 43, 14);
		contentPanel.add(lblNFB);
		
		txtFieldNFB = new JTextField();
		txtFieldNFB.setText("0");
		txtFieldNFB.setColumns(10);
		txtFieldNFB.setBounds(262, 155, 43, 20);
		contentPanel.add(txtFieldNFB);
		PlainDocument NFB = (PlainDocument) txtFieldNFB.getDocument();
		NFB.setDocumentFilter(new IntegerFilter());
		
		JLabel lblNRT = new JLabel("NRT");
		lblNRT.setBounds(315, 158, 43, 14);
		contentPanel.add(lblNRT);
		
		txtFieldNRT = new JTextField();
		txtFieldNRT.setText("0");
		txtFieldNRT.setColumns(10);
		txtFieldNRT.setBounds(368, 155, 43, 20);
		contentPanel.add(txtFieldNRT);
		PlainDocument NRT = (PlainDocument) txtFieldNRT.getDocument();
		NRT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblNT = new JLabel("NT");
		lblNT.setBounds(10, 183, 22, 14);
		contentPanel.add(lblNT);
		
		txtFieldNT = new JTextField();
		txtFieldNT.setText("0");
		txtFieldNT.setColumns(10);
		txtFieldNT.setBounds(63, 180, 43, 20);
		contentPanel.add(txtFieldNT);
		PlainDocument NT = (PlainDocument) txtFieldNT.getDocument();
		NT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblPMT = new JLabel("PMT");
		lblPMT.setBounds(124, 183, 43, 14);
		contentPanel.add(lblPMT);
		
		txtFieldPMT = new JTextField();
		txtFieldPMT.setText("0");
		txtFieldPMT.setColumns(10);
		txtFieldPMT.setBounds(156, 180, 43, 20);
		contentPanel.add(txtFieldPMT);
		PlainDocument PMT = (PlainDocument) txtFieldPMT.getDocument();
		PMT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblRIFE = new JLabel("RIFE");
		lblRIFE.setBounds(209, 183, 43, 14);
		contentPanel.add(lblRIFE);
		
		txtFieldRIFE = new JTextField();
		txtFieldRIFE.setText("0");
		txtFieldRIFE.setColumns(10);
		txtFieldRIFE.setBounds(262, 180, 43, 20);
		contentPanel.add(txtFieldRIFE);
		PlainDocument RIFE = (PlainDocument) txtFieldRIFE.getDocument();
		RIFE.setDocumentFilter(new IntegerFilter());
		
		JLabel lblRRT = new JLabel("RRT");
		lblRRT.setBounds(315, 183, 43, 14);
		contentPanel.add(lblRRT);
		
		txtFieldRRT = new JTextField();
		txtFieldRRT.setText("0");
		txtFieldRRT.setColumns(10);
		txtFieldRRT.setBounds(368, 180, 43, 20);
		contentPanel.add(txtFieldRRT);
		PlainDocument RRT = (PlainDocument) txtFieldRRT.getDocument();
		RRT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblSRT = new JLabel("SRT");
		lblSRT.setBounds(10, 208, 43, 14);
		contentPanel.add(lblSRT);
		
		txtFieldSRT = new JTextField();
		txtFieldSRT.setText("0");
		txtFieldSRT.setColumns(10);
		txtFieldSRT.setBounds(63, 205, 43, 20);
		contentPanel.add(txtFieldSRT);
		PlainDocument SRT = (PlainDocument) txtFieldSRT.getDocument();
		SRT.setDocumentFilter(new IntegerFilter());
		
		JLabel lblTRX = new JLabel("TRX");
		lblTRX.setBounds(124, 208, 43, 14);
		contentPanel.add(lblTRX);
		
		txtFieldTRX = new JTextField();
		txtFieldTRX.setText("0");
		txtFieldTRX.setColumns(10);
		txtFieldTRX.setBounds(156, 205, 43, 20);
		contentPanel.add(txtFieldTRX);
		PlainDocument TRX = (PlainDocument) txtFieldTRX.getDocument();
		TRX.setDocumentFilter(new IntegerFilter());
		
		JLabel lblUS = new JLabel("US");
		lblUS.setBounds(209, 208, 43, 14);
		contentPanel.add(lblUS);
		
		txtFieldUS = new JTextField();
		txtFieldUS.setText("0");
		txtFieldUS.setColumns(10);
		txtFieldUS.setBounds(262, 205, 43, 20);
		contentPanel.add(txtFieldUS);
		PlainDocument US = (PlainDocument) txtFieldUS.getDocument();
		US.setDocumentFilter(new IntegerFilter());
		
		JLabel lblVIBE = new JLabel("VIBE");
		lblVIBE.setBounds(315, 208, 43, 14);
		contentPanel.add(lblVIBE);
		
		txtFieldVIBE = new JTextField();
		txtFieldVIBE.setText("0");
		txtFieldVIBE.setColumns(10);
		txtFieldVIBE.setBounds(368, 205, 43, 20);
		contentPanel.add(txtFieldVIBE);
		PlainDocument VIBE = (PlainDocument) txtFieldVIBE.getDocument();
		VIBE.setDocumentFilter(new IntegerFilter());		
	}
	
	protected void savePatient() {
		
		// get the patient info from the gui
		String firstName = txtFieldFName.getText();
		String middleName = txtFieldMName.getText();
		String lastName = txtFieldLName.getText();
		
		int AQR = Integer.parseInt(txtFieldAQR.getText());
		int AT = Integer.parseInt(txtFieldAT.getText());
		int AWT = Integer.parseInt(txtFieldAWT.getText());
		int CCT = Integer.parseInt(txtFieldCCT.getText());
		int CMT = Integer.parseInt(txtFieldCMT.getText());
		int EMS = Integer.parseInt(txtFieldEMS.getText());
		int GT = Integer.parseInt(txtFieldGT.getText());
		int HKM = Integer.parseInt(txtFieldHKM.getText());
		int IST = Integer.parseInt(txtFieldIST.getText());
		int LASER = Integer.parseInt(txtFieldLASER.getText());
		int NFB = Integer.parseInt(txtFieldNFB.getText());
		int NRT = Integer.parseInt(txtFieldNRT.getText());
		int NT = Integer.parseInt(txtFieldNT.getText());
		int PMT = Integer.parseInt(txtFieldPMT.getText());
		int RIFE = Integer.parseInt(txtFieldRIFE.getText());
		int RRT = Integer.parseInt(txtFieldRRT.getText());
		int SRT = Integer.parseInt(txtFieldSRT.getText());
		int TRX = Integer.parseInt(txtFieldTRX.getText());
		int US = Integer.parseInt(txtFieldUS.getText());
		int VIBE = Integer.parseInt(txtFieldVIBE.getText());
		
		Patient tempPatient = null;
		
		if (updateMode) {
			tempPatient = previousPatient;
			
			tempPatient.setFirstName(firstName);
			tempPatient.setMiddleName(middleName);
			tempPatient.setLastName(lastName);
			
			tempPatient.setAQR(AQR);
			tempPatient.setAT(AT);
			tempPatient.setAWT(AWT);
			tempPatient.setCCT(CCT);
			tempPatient.setCMT(CMT);
			tempPatient.setEMS(EMS);
			tempPatient.setGT(GT);
			tempPatient.setHKM(HKM);
			tempPatient.setIST(IST);
			tempPatient.setLASER(LASER);
			tempPatient.setNFB(NFB);
			tempPatient.setNRT(NRT);
			tempPatient.setNT(NT);
			tempPatient.setPMT(PMT);
			tempPatient.setRIFE(RIFE);
			tempPatient.setRRT(RRT);
			tempPatient.setSRT(SRT);
			tempPatient.setTRX(TRX);
			tempPatient.setUS(US);
			tempPatient.setVIBE(VIBE);
			
			
		} else {
			tempPatient = new Patient(firstName, middleName, lastName, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER,
				                          NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE);
		}
		
		//if ((firstName.trim().length() != 0) && (lastName.trim().length() != 0)) {	
		
			try{
				// save to the database
				if(updateMode) {
					patientDAO.updatePatient(tempPatient, userId);
				} else {
					patientDAO.addPatient(tempPatient, userId);
				}
			
				// close dialog
				setVisible(false);
				dispose();
			
				// refresh gui list
				//PatientSessionTrackerApp.refreshPatientsView();
			
				// show success message
				JOptionPane.showMessageDialog(patientSessionTrackerApp, "Patient added to the database.", 
					"Patient Added",
					JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(patientSessionTrackerApp, 
						"Error saving patient: " +
						exc.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} 
		//} else 
		//	JOptionPane.showMessageDialog(patientSessionTrackerApp, "<html>Please fill in all required fields as denoted by the <font color='red'>*</font>.</html>");
	}
}