package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class LoginLogic {

	static final LoginUI lu = LoginUI.getInstance(); // immutable singleton
														// object reference

	public LoginLogic() {
		// TODO Auto-generated constructor stub
	}

	public LoginLogic(ResultSet rs) { // parameterized constructor
		process(rs);
	}

	void process(ResultSet rs) { // process ResultSet to authenticate login
		try {
			if (rs.next()) {
				lu.loginSuccess();

			} else {
				lu.loginFailed();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {// delegate GUI updated to
													// EDT

			@Override
			public void run() {
				new LoginLogic();

			}
		});

	}

}
