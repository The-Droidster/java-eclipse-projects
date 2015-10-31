package sk.loginapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginUI {

	// Container declarations
	private JFrame frame;
	private JPanel panel;

	// Component declarations
	private JLabel lblUname, lblPwd;
	private JTextField tfUname;
	private JPasswordField pfPwd;
	private JButton btnLogin, btnCancel;

	private static int count = 0;
	private int choice;
	private static final LoginUI lu = new LoginUI(); // immutable singleton
														// object

	private LoginUI() { // prevent external instantiation
		initUI();
	}

	static LoginUI getInstance() { // getter for the singleton object
		return lu;
	}

	private void initUI() {
		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {

		}
		choice = JOptionPane.showConfirmDialog(null, "Proceed to Login?", "Login Prompt!", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION)
			createFrame();
		else
			System.exit(0);
	}

	private void createFrame() {

		frame = new JFrame("Login App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.add(panel);
		placePanelComponents(panel); // UI components handler

		frame.setSize(300, 160);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void placePanelComponents(JPanel panel) {

		panel.setLayout(null);

		lblUname = new JLabel("Username: ");
		lblUname.setBounds(50, 20, 120, 20);
		lblPwd = new JLabel("Password: ");
		lblPwd.setBounds(50, 50, 120, 20);

		tfUname = new JTextField(10);
		tfUname.setBounds(120, 20, 120, 20);

		pfPwd = new JPasswordField(10);
		pfPwd.setEchoChar('*');
		pfPwd.setBounds(120, 50, 120, 20);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 85, 90, 25);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(150, 85, 90, 25);

		panel.add(lblUname);
		panel.add(lblPwd);
		panel.add(tfUname);
		panel.add(pfPwd);
		panel.add(btnLogin);
		panel.add(btnCancel);

		eventListener(); // delegate action listener to separate method
	}

	private void eventListener() { // event handler for buttons
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				count++; // Counter for login attempts
				try {
					String uname = tfUname.getText().trim();
					String pwd = new String(pfPwd.getPassword());
					new LoginDbConn(uname, pwd); // startDB after user presses
					// the login button
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clearTF();
					System.exit(0);
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});

	}

	void loginSuccess() {
		JOptionPane.showMessageDialog(null, "Login Successful!");
		clearTF();
		System.exit(0);
	}

	void loginFailed() {
		if (count > 2) { // Allow only 3 login attempts
			JOptionPane.showMessageDialog(null, "Exceeded Max Login Attempts! Exiting!", null, JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(null, "Login Failed! "+(3-count)+" Login attempt/s left!");
			clearTF();
		}
	}

	private void clearTF() { // clear JTextField
		tfUname.setText("");
		pfPwd.setText("");
	}

}