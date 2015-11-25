package sk.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConn {
	// DB related parameters
	String driverClass; // name of the oracle jdbc driver
	String dburl, dbuname, dbpwd; // db connection parameters
	Connection conn; // Connection object

	public DBConn() {
		// Default Constructor
	}

	Connection getConn() {
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
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn; // Return Connection object to the logic classes
	}
}