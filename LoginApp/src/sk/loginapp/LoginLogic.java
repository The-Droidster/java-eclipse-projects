package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Unnecessary class, can be merged with LoginDbConn

class LoginLogic {

	public LoginLogic() {
		// TODO Auto-generated constructor stub
	}

	void process(ResultSet rs) { // process ResultSet to authenticate login
		try {
			if (rs.next()) {
				LoginUI.getInstance().loginSuccess();

			} else {
				LoginUI.getInstance().loginFailed();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

}
