package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.swing.SwingUtilities;

class LoginLogic {

	/**
	 * Possible alternative to Singleton pattern:
	 * static final LoginUI lu = new LoginUI();
	 * without the need for any change in the LoginUI class
	 *//*
	static final LoginUI lu = LoginUI.getInstance(); // immutable singleton
														// object reference

	public LoginLogic() {
		// TODO Auto-generated constructor stub
	}*/

	public LoginLogic() { // default constructor
		
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

	/*public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {// delegate GUI updated to
													// EDT

			@Override
			public void run() {
				new LoginLogic();

			}
		});

	}*/

}
