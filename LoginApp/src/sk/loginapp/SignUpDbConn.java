package sk.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SignUpDbConn {
	// DB related parameters
	String driverClass; // name of the oracle jdbc driver
	String dburl, dbuname, dbpwd; // db connection parameters
	Connection conn; // Connection object

	// SQL related parameters
	String sql; // SQL Query
	PreparedStatement pst; // type of query
	ResultSet rs; // holds query result

	private boolean userExists; // check if user already exists

	/*
	 * public LoginDbConn() { // TODO Auto-generated constructor stub }
	 */

	public SignUpDbConn(String uname, String pwd) {
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

			rs = checkUserExists(uname);

			// 4. Process the result set
			userExists = new SignUpLogic().process(rs); // pass result set to
														// Logic class for
														// processing

			if (userExists) {
				SignUpUI.getInstance().userExists();
			} else {
				insertUser(uname, pwd);
				SignUpUI.getInstance().signUpSuccess();
			}

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

	private ResultSet checkUserExists(String uname) {
		try {
			// 2. Create a statement
			sql = "select * from users where name = ?";
			pst = conn.prepareStatement(sql);

			pst.setString(1, uname);

			// 3. Execute SQL query
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private void insertUser(String uname, String pwd) {
		try {
			sql = "INSERT INTO users(name, password) VALUES(?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uname);
			pst.setString(2, pwd);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
