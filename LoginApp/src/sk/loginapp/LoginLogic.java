package sk.loginapp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LoginLogic {

	public LoginLogic() {
		new LoginUI();

	}

	public LoginLogic(ResultSet rs) {
		this.process(rs);
	}

	private void process(ResultSet rs) {
		try {
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login Successful!");

			} else {
				JOptionPane.showMessageDialog(null, "Login Failed!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {

		new LoginLogic();

	}

}
