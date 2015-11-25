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
	private JLabel lblUname, lblPwd, lblPwdCpy;
	private JTextField tfUname;
	private JPasswordField pfPwd, pfPwdCpy;
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

	private void initUI() { // Initialize UI
		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {
			e.printStackTrace();
		}
		choice = JOptionPane.showConfirmDialog(null, "Proceed to SignUp?", "SignUp Prompt!", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION)
			createFrame();
		else
			System.exit(0);
	}

	private void createFrame() { // draw JFrame

		frame = new JFrame("SignUp Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frame.add(panel);
		placePanelComponents(panel); // UI components handler

		frame.setSize(330, 190);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void placePanelComponents(JPanel panel) { // Add components to panel

		panel.setLayout(null);

		lblUname = new JLabel("Enter a Username: ");
		lblUname.setBounds(40, 20, 120, 20);
		lblPwd = new JLabel("Enter a Password: ");
		lblPwd.setBounds(40, 50, 120, 20);
		lblPwdCpy = new JLabel("Re-Enter Password: ");
		lblPwdCpy.setBounds(40, 80, 120, 20);

		tfUname = new JTextField(10);
		tfUname.setBounds(160, 20, 120, 20);

		pfPwd = new JPasswordField(10);
		pfPwd.setEchoChar('\0');
		pfPwd.setBounds(160, 50, 120, 20);
		pfPwdCpy = new JPasswordField(10);
		pfPwdCpy.setEchoChar('\0');
		pfPwdCpy.setBounds(160, 80, 120, 20);

		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(50, 115, 95, 25);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(175, 115, 95, 25);

		panel.add(lblUname);
		panel.add(lblPwd);
		panel.add(lblPwdCpy);
		panel.add(tfUname);
		panel.add(pfPwd);
		panel.add(pfPwdCpy);
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
					String pwd = new String(pfPwd.getPassword()).trim();
					String pwdCpy = new String(pfPwdCpy.getPassword()).trim();
					if ((uname != null && pwd != null && pwdCpy != null)
							&& (!uname.isEmpty() && !pwd.isEmpty() && !pwdCpy.isEmpty())) {
						new SignUpLogic().process(uname, pwd, pwdCpy); // startDB
																		// after
																		// user
																		// presses
						// the SignUp button
					} else
						emptyFields();

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

		choice = JOptionPane.showConfirmDialog(null, "Do you wish to Login now?", "Login now?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (choice == JOptionPane.YES_OPTION) { // Login immediately
			frame.setVisible(false);
			frame.dispose();
			LoginUI.getInstance();
		} else {
			clearTF();
			System.exit(0);
		}
	}

	void userExists() { // Prevent username duplication

		JOptionPane.showMessageDialog(null, "User Exists. Please choose a different username.", "Duplicate Username",
				JOptionPane.WARNING_MESSAGE);
		clearTF();

	}

	private void clearTF() { // clear JTextField
		tfUname.setText("");
		pfPwd.setText("");
		pfPwdCpy.setText("");
	}

	private void emptyFields() {
		JOptionPane.showMessageDialog(null, "Username and/or Password cannot be empty!", "Empty Fields",
				JOptionPane.WARNING_MESSAGE);
		clearTF();
	}

	void passUnEqual() {
		JOptionPane.showMessageDialog(null, "Passwords don't match!", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
		clearTF();
	}

	void noSpaceAllowed() {
		JOptionPane.showMessageDialog(null, "Username and/or Password cannot contain space!", "No spaces allowed",
				JOptionPane.WARNING_MESSAGE);
		clearTF();
	}
}
