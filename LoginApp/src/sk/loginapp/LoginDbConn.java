package sk.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class LoginDbConn {

	// DB related parameters
	String driverClass; // name of the oracle jdbc driver
	String dburl, dbuname, dbpwd; // db connection parameters
	Connection conn; // Connection object

	// SQL related parameters
	String sql; // SQL Query
	PreparedStatement pst; // type of query
	ResultSet rs; // holds query result

	/*
	 * public LoginDbConn() { // TODO Auto-generated constructor stub }
	 */

	public LoginDbConn(String uname, String pwd) {
		getConn(uname, pwd);
	}

	private void getConn(String uname, String pwd) {
		try {
			// 0. Register the JDBC drivers
			driverClass = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverClass);
			// or DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

			// 1. Get a connection to the Database
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			dbuname = "scott";
			dbpwd = "tiger";
			conn = DriverManager.getConnection(dburl, dbuname, dbpwd);

			// 2. Create a statement
			// String sql = "SELECT * FROM users WHERE name = '"+uname+"' and
			// password = '"+pwd+"'";
			// Statement st = conn.createStatement();
			sql = "select * from users where name = ? and password = ?";
			pst = conn.prepareStatement(sql);

			pst.setString(1, uname);
			pst.setString(2, pwd);

			// 3. Execute SQL query
			rs = pst.executeQuery();

			// 4. Process the result set
			new LoginLogic(rs); // pass result set to Logic class for processing

			// 5. Close Connection/ Clean up environment
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

}
