package sk.loginapp;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIStart {

	private int choice;

	public UIStart() {
		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {

		}
		choice = JOptionPane.showConfirmDialog(null, "Do You have an account?", "Login or SignUp?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		switch (choice) {		
		case JOptionPane.YES_OPTION: // Account exists, proceed to Login
			LoginUI.getInstance();
			break;
			
		case JOptionPane.NO_OPTION:
			SignUpUI.getInstance(); // New user, proceed to Sign Up
			break;
			
		default:
			System.exit(0);
			break;
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new UIStart();

			}
		});

	}

}
