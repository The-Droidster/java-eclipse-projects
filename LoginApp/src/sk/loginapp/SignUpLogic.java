package sk.loginapp;

// imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SignUpLogic {

	private Connection conn; // DB Connection object

	// SQL Related parameters
	private PreparedStatement pst; // type of query
	private ResultSet rs; // holds query result
	private String sql; // sql query

	// SignUp UI singleton object reference
	private SignUpUI su;

	public SignUpLogic() {
		conn = new DBConn().getConn(); // get connection from DBConn
		su = SignUpUI.getInstance();
	}

	void process(String uname, String pwd, String pwdCpy) { //
		try {
			rs = checkUserExists(uname);
			if (rs.next()) {
				su.userExists(); //
			} else {
				checkPassword(uname, pwd, pwdCpy);
			}
			// 5. Close Connection/ Clean up environment
			rs.close();
			pst.close();
			conn.commit();
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

	private void checkPassword(String uname, String pwd, String pwdCpy) {
		if (pwd.equals(pwdCpy)) {
			if (!(pwd.contains(" ") || uname.contains(" "))) {
				insertUser(uname, pwd);
				su.signUpSuccess(); //
			} else {
				su.noSpaceAllowed();
			}
		} else {
			su.passUnEqual();
		}
	}

}
