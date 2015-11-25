package sk.loginapp;

// imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

class LoginLogic {

	private Connection conn; // DB Connection object

	// SQL Related parameters
	private PreparedStatement pst; // type of query
	// private Statement st;
	private ResultSet rs; // holds query result
	private String sql; // sql query

	// Login UI singleton object reference
	private LoginUI lg;

	private static int count = 0; // check Login attempts

	public LoginLogic() {
		conn = new DBConn().getConn(); // get connection from DBConn
		lg = LoginUI.getInstance(); // get Login UI Singleton object
	}

	void process(String uname, String pwd) { // query db and process ResultSet
												// to authenticate user
		try {

			// Statement version
			/*
			 * String sql = "SELECT * FROM users WHERE name = '"+uname+"' and
			 * password = '"+pwd+"'"; Statement st = conn.createStatement();
			 */

			// 2. Create a statement
			sql = "select * from users where name = ? and password = ?";
			pst = conn.prepareStatement(sql);

			pst.setString(1, uname);
			pst.setString(2, pwd);

			// 3. Execute SQL query
			rs = pst.executeQuery();

			// 4. Process the result set
			if (rs.next()) {
				lg.loginSuccess();

			} else {
				count++; // Counter for login attempts
				if (count < 3) { // Allow only 3 login attempts
					lg.loginFailed(count);
				} else
					lg.failExceeded();

			}
			// 5. Close Connection/ Clean up environment
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
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
