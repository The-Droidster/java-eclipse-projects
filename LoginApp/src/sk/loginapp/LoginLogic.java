package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class LoginLogic {

	LoginUI lu = LoginUI.getInstance();

	public LoginLogic() {
		// TODO Auto-generated constructor stub
	}

	public LoginLogic(ResultSet rs) {
		process(rs);
	}

	void process(ResultSet rs) {
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

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginLogic();

			}
		});

	}

}
