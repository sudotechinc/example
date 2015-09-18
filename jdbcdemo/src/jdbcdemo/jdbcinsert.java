package jdbcdemo;

import java.sql.*;

public class jdbcinsert {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String password = "student";
		try {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(url, user, password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3. Execute a query
			String sql = "insert into employees " +
			"(last_name, first_name, email)" +
			"values ('Brown','David','david.brown@foo.com')";
			
			myStmt.executeUpdate(sql);
			
			System.out.println("Insert Complete!");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
