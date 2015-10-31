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

class SignUpUI {
	// Container declarations
	private JFrame frame;
	private JPanel panel;

	// Component declarations
	private JLabel lblUname, lblPwd;
	private JTextField tfUname;
	private JPasswordField pfPwd;
	private JButton btnSignUp, btnCancel;

	private int choice;
	private static final SignUpUI su = new SignUpUI(); // immutable singleton
														// object

	private SignUpUI() { // prevent external instantiation
		initUI();
	}

	static SignUpUI getInstance() { // getter for the singleton object
		return su;
	}

	private void initUI() {
		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {

		}
		choice = JOptionPane.showConfirmDialog(null, "Proceed to SignUp?", "SignUp Prompt!", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION)
			createFrame();
		else
			System.exit(0);
	}

	private void createFrame() {

		frame = new JFrame("SignUp Form");
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
		// pfPwd.setEchoChar('*');
		pfPwd.setBounds(120, 50, 120, 20);

		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(50, 85, 90, 25);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(150, 85, 90, 25);

		panel.add(lblUname);
		panel.add(lblPwd);
		panel.add(tfUname);
		panel.add(pfPwd);
		panel.add(btnSignUp);
		panel.add(btnCancel);

		eventListener(); // delegate action listener to separate method
	}

	private void eventListener() { // event handler for buttons
		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String uname = tfUname.getText().trim();
					String pwd = new String(pfPwd.getPassword());
					new SignUpDbConn(uname, pwd); // startDB after user presses
					// the SignUp button
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

	void signUpSuccess() {
		JOptionPane.showMessageDialog(null, "Successfully Signed Up!");
		choice = JOptionPane.showConfirmDialog(null, "Do you wish to Login now?", "Login now?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			frame.setVisible(false);
			frame.dispose();
			LoginUI.getInstance();
		}
		else {
			clearTF();
			System.exit(0);
		}
	}

	void userExists() {

		JOptionPane.showMessageDialog(null, "User Exists. Please choose a different username.");
		clearTF();

	}

	private void clearTF() { // clear JTextField
		tfUname.setText("");
		pfPwd.setText("");
	}
}
