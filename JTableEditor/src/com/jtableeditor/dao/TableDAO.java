package com.jtableeditor.dao;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

import com.jtableeditor.core.*;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class TableDAO {

	private Connection myConn;
	
	public TableDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public List<Table> getAllSessions() throws Exception {
		List<Table> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from session");
			
			while (myRs.next()) {
				Table tempTable = convertRowToTable(myRs);
				list.add(tempTable);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Table> searchSessions(String lastName) throws Exception {
		List<Table> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from session where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Table tempTable = convertRowToTable(myRs);
				list.add(tempTable);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Table convertRowToTable(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String lastName = myRs.getString("last_name");
		String firstName = myRs.getString("first_name");
		String email = myRs.getString("email");
		BigDecimal salary = myRs.getBigDecimal("salary");
		
		Table tempTable = new Table(id, lastName, firstName, email, salary);
		
		return tempTable;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		TableDAO dao = new TableDAO();

		System.out.println(dao.getAllSessions());
	}
}
