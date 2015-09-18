package com.patientsessiontracker.dao;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.patientsessiontracker.core.AuditHistory;
import com.patientsessiontracker.core.Patient;
import com.patientsessiontracker.core.User;

/**
 * 
 * @author Evan Pederson
 *
 */

public class PatientDAO {
	
	private Connection dbConn;
	
	public PatientDAO() throws Exception {
		
		// get db properties
		Properties config = new Properties();
		config.load(new FileInputStream("Patient Session Tracker Config/patientSessionTracker.properties"));
		
		String user = config.getProperty("user");
		String password = config.getProperty("password");
		String dburl = config.getProperty("dburl");
		
		// connect to database
		dbConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public void updatePatient(Patient thePatient, int userId) throws SQLException {
		PreparedStatement dbStmt = null;
		
		try {
			// prepare statement
			dbStmt = dbConn.prepareStatement("update patients" +
			                                 " set first_name=?, middle_name=?, last_name=?, AQR=?, AT=?, AWT=?, CCT=?, CMT=?, EMS=?, GT=?, HKM=?, IST=?, LASER=?, NFB=?, NRT=?, NT=?, PMT=?, RIFE=?, RRT=?, SRT=?, TRX=?, US=?, VIBE=?" +
					                         " where id=?");
			
			// set params
			dbStmt.setString(1, thePatient.getFirstName());
			dbStmt.setString(2, thePatient.getMiddleName());
			dbStmt.setString(3, thePatient.getLastName());
			dbStmt.setInt(4, thePatient.getAQR());
			dbStmt.setInt(5, thePatient.getAT());
			dbStmt.setInt(6, thePatient.getAWT());
			dbStmt.setInt(7, thePatient.getCCT());
			dbStmt.setInt(8, thePatient.getCMT());
			dbStmt.setInt(9, thePatient.getEMS());
			dbStmt.setInt(10, thePatient.getGT());
			dbStmt.setInt(11, thePatient.getHKM());
			dbStmt.setInt(12, thePatient.getIST());
			dbStmt.setInt(13, thePatient.getLASER());
			dbStmt.setInt(14, thePatient.getNFB());
			dbStmt.setInt(15, thePatient.getNRT());
			dbStmt.setInt(16, thePatient.getNT());
			dbStmt.setInt(17, thePatient.getPMT());
			dbStmt.setInt(18, thePatient.getRIFE());
			dbStmt.setInt(19, thePatient.getRRT());
			dbStmt.setInt(20, thePatient.getSRT());
			dbStmt.setInt(21, thePatient.getTRX());
			dbStmt.setInt(22, thePatient.getUS());
			dbStmt.setInt(23, thePatient.getVIBE());
			dbStmt.setInt(24, thePatient.getId());
			
			// execute SQL
			dbStmt.executeUpdate();
			
			// Add audit history
			
			// prepare statement
			dbStmt = dbConn.prepareStatement("insert into audit_history" +
			                                 " (user_id, patients_id, action, action_date_time)" +
					                         " values (?, ?, ?, ?)");
			
			// set params
			dbStmt.setInt(1, userId);
			dbStmt.setInt(2, thePatient.getId());
			dbStmt.setString(3, "Updated patient.");
			dbStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			// execute SQL
			dbStmt.executeUpdate();
		} finally {
			close(dbStmt);
		}
	}
	
	public void addPatient(Patient thePatient, int userId) throws Exception {
		PreparedStatement dbStmt = null;
		
		try {
			//prepare statement
			dbStmt = dbConn.prepareStatement("insert into patients " +
			         "(first_name, middle_name, last_name, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER, NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE) " +
					 "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			// set params
			dbStmt.setString(1, thePatient.getFirstName());
			dbStmt.setString(2, thePatient.getMiddleName());
			dbStmt.setString(3, thePatient.getLastName());
			dbStmt.setInt(4, thePatient.getAQR());
			dbStmt.setInt(5, thePatient.getAT());
			dbStmt.setInt(6, thePatient.getAWT());
			dbStmt.setInt(7, thePatient.getCCT());
			dbStmt.setInt(8, thePatient.getCMT());
			dbStmt.setInt(9, thePatient.getEMS());
			dbStmt.setInt(10, thePatient.getGT());
			dbStmt.setInt(11, thePatient.getHKM());
			dbStmt.setInt(12, thePatient.getIST());
			dbStmt.setInt(13, thePatient.getLASER());
			dbStmt.setInt(14, thePatient.getNFB());
			dbStmt.setInt(15, thePatient.getNRT());
			dbStmt.setInt(16, thePatient.getNT());
			dbStmt.setInt(17, thePatient.getPMT());
			dbStmt.setInt(18, thePatient.getRIFE());
			dbStmt.setInt(19, thePatient.getRRT());
			dbStmt.setInt(20, thePatient.getSRT());
			dbStmt.setInt(21, thePatient.getTRX());
			dbStmt.setInt(22, thePatient.getUS());
			dbStmt.setInt(23, thePatient.getVIBE());
			
			// execute the query
			dbStmt.executeUpdate();
			
			// get the generated patient id
			ResultSet generatedKeys = dbStmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				thePatient.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Error generating key for patient");
			}
			
			// Add audit history
			
			// Prepare statement
			dbStmt = dbConn.prepareStatement("insert into audit_history" +
			                                 " (user_id, patients_id, action, action_date_time)" +
					                         " values (?, ?, ?, ?)");
			
			// set params
			dbStmt.setInt(1, userId);
			dbStmt.setInt(2, thePatient.getId());
			dbStmt.setString(3, "Added new patient.");
			dbStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			// execute query
			dbStmt.executeUpdate();
			
		} finally {
			close(dbStmt);
		}
	}
	
	public List<Patient> getAllPatients() throws Exception {
		List<Patient> list = new ArrayList<Patient>();
		
		Statement dbStmt = null;
		ResultSet dbRs = null;
		
		try {
			dbStmt = dbConn.createStatement();
			dbRs = dbStmt.executeQuery("select * from patients order by first_name");
			
			while (dbRs.next()) {
				Patient tempPatient = convertRowToPatient(dbRs);
				list.add(tempPatient);
			}
			
			return list;
			
		} finally {
			close(dbStmt, dbRs);
		}
	}
	
	public List<Patient> searchPatients(String firstName, String lastName) throws Exception {
		List<Patient> list = new ArrayList<>();
		
		PreparedStatement dbStmt = null;
		ResultSet dbRs = null;
		
		try {
			firstName += "%";
			lastName += "%";
			dbStmt = dbConn.prepareStatement("select * from patients where first_name like ? and last_name like ?");
			
			dbStmt.setString(1, firstName);
			dbStmt.setString(2, lastName);
			
			dbRs = dbStmt.executeQuery();
			
			while (dbRs.next()) {
				Patient tempPatient = convertRowToPatient(dbRs);
				list.add(tempPatient);
			}
			
			return list;
		} finally {
			close(dbStmt, dbRs);
		}
	}
	
	private Patient convertRowToPatient(ResultSet dbRs) throws SQLException {
		
		int id = dbRs.getInt("id");
		String firstName = dbRs.getString("first_name");
		String middleName = dbRs.getString("middle_name");
		String lastName = dbRs.getString("last_name");
		int AQR = dbRs.getInt("AQR");
		int AT = dbRs.getInt("AT");
		int AWT = dbRs.getInt("AWT");
		int CCT = dbRs.getInt("CCT");
		int CMT = dbRs.getInt("CMT");
		int EMS = dbRs.getInt("EMS");
		int GT = dbRs.getInt("GT");
		int HKM = dbRs.getInt("HKM");
		int IST = dbRs.getInt("IST");
		int LASER = dbRs.getInt("LASER");
		int NFB = dbRs.getInt("NFB");
		int NRT = dbRs.getInt("NRT");
		int NT = dbRs.getInt("NT");
		int PMT = dbRs.getInt("PMT");
		int RIFE = dbRs.getInt("RIFE");
		int RRT = dbRs.getInt("RRT");
		int SRT = dbRs.getInt("SRT");
		int TRX = dbRs.getInt("TRX");
		int US = dbRs.getInt("US");
		int VIBE = dbRs.getInt("VIBE");
		
		Patient tempPatient = new Patient(firstName, middleName, lastName, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER,
				 NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE);
		
		return tempPatient;
	}
	
	private User convertRowToUser(ResultSet dbRs) throws SQLException {
		
		int id = dbRs.getInt("id");
		String lastName = dbRs.getString("last_name");
		String firstName = dbRs.getString("first_name");
		String email = dbRs.getString("email");
		
		User tempUser = new User(id, lastName, firstName, email);
		
		return tempUser;
	}
	
	private static void close(Connection dbConn, Statement dbStmt, ResultSet dbRs) throws SQLException {
		if (dbRs != null) 
			dbRs.close();

		if (dbStmt != null) 

		if (dbConn != null) 
			dbConn.close();
	}

	private void close(Statement dbStmt, ResultSet dbRs) throws SQLException {
		close(null, dbStmt, dbRs);
	}
	 
	private void close(Statement dbStmt) throws SQLException {
		close(null, dbStmt, null);		
	}
	
	public List<User> getUsers() throws Exception {
		List<User> list = new ArrayList<User>();
		
		Statement dbStmt = null;
		ResultSet dbRs = null;
		
		try {
			dbStmt = dbConn.createStatement();
			dbRs = dbStmt.executeQuery("select * from users order by last_name");
			
			while (dbRs.next()) {
				User tempUser = convertRowToUser(dbRs);
				list.add(tempUser);
			}

			return list;		
		}
		finally {
			close(dbStmt, dbRs);
		}
	}
	
	public List<AuditHistory> getAuditHistory(int patientId) throws Exception {
		List<AuditHistory> list = new ArrayList<AuditHistory>();
		
		Statement dbStmt = null;
		ResultSet dbRs = null;
		
		try {
			dbStmt = dbConn.createStatement();
			
			String sql = "select history.user_id, history.patients_id, history.action, history.action_date_time, users.first_name, users.last_name " +
			             " from audit_history history, users users" +
					     " where history.user_id=users.id and history.patients_id=" + patientId;
			
			dbRs = dbStmt.executeQuery(sql);
			
			while (dbRs.next()) {
				
				int userId = dbRs.getInt("history.user_id");
				String action = dbRs.getString("history.action");
				
				Timestamp timestamp = dbRs.getTimestamp("history.action_date_time");
				java.util.Date actionDateTime = new java.util.Date(timestamp.getTime());
				
				String userFirstName = dbRs.getString("users.first_name");
				String userLastName = dbRs.getString("users.last_name");
				
				AuditHistory temp = new AuditHistory(userId, patientId, action, actionDateTime, userFirstName, userLastName);
				
				list.add(temp);
			}
			
			return list;
		} finally {
			close(dbStmt, dbRs);
		}
	}
															
	public static void main(String[] args) throws Exception {
		
		PatientDAO dao = new PatientDAO();
		
		System.out.println(dao.getAllPatients());
	}
}
