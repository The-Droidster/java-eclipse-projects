package sk.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class LoginDbConn {

	/*public LoginDbConn() {
		// TODO Auto-generated constructor stub
	}*/

	public LoginDbConn(String uname, String pwd) {
		this.getConn(uname, pwd);
	}

	private void getConn(String uname, String pwd) {
		try {
			// 0. Register the JDBC drivers
			String driverClass = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverClass);
			// or DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

			// 1. Get a connection to the Database
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbuname = "scott";
			String dbpwd = "tiger";
			Connection conn = DriverManager.getConnection(dbUrl, dbuname, dbpwd);

			// 2. Create a statement
			// String sql = "SELECT * FROM users WHERE name = '"+uname+"' and
			// password = '"+pwd+"'";
			// Statement st = conn.createStatement();
			String sql = "select * from users where name = ? and password = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, uname);
			pst.setString(2, pwd);

			// 3. Execute SQL query
			ResultSet rs = pst.executeQuery();

			// 4. Process the result set
			new LoginLogic(rs);

			// 5. Close Connection
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {

		}

	}

}
