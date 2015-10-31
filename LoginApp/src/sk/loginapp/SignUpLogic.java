package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

class SignUpLogic {

	private boolean user_exists;

	public SignUpLogic() {

	}

	boolean process(ResultSet rs) { // process ResultSet to check if user already exists
		try {
			if (rs.next()) {
				user_exists = true;

			} else {
				user_exists = false;

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return user_exists;
	}
}
