package jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDelete {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String password = "student";
		
		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			myConn = DriverManager.getConnection(url, user, password);
			
			myStmt = myConn.createStatement();
			
			String sql = "delete from employees where last_name = 'Brown'";
			
			int rowsAffected = myStmt.executeUpdate(sql);
			
			System.out.println("Rows affected: " + rowsAffected);
			System.out.println("Delete complete.");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myStmt != null) 
				myStmt.close();
			
			if (myConn != null)
				myConn.close();
			}
		}
	}

