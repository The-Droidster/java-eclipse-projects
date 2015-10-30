package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class LoginLogic {

	public LoginLogic() {
		new LoginUI();
	}

	static void process(ResultSet rs) {
		try {
			if (rs.next()) {
				LoginUI.loginSuccess();

			} else {
				LoginUI.loginFailed();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginLogic();

			}
		});

	}

}
