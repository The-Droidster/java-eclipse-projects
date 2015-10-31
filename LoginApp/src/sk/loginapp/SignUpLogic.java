package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Unnecessary class, can be merged with SignUpDbConn

class SignUpLogic {

	private boolean user_exists; // check if user already exists

	public SignUpLogic() {
		// TODO Auto-generated constructor stub
	}

	boolean process(ResultSet rs) { // process ResultSet to check if user already exists
		try {
			if (rs.next()) {
				user_exists = true; //return true if user exists

			} else {
				user_exists = false; //return false other wise

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return user_exists;
	}
}
