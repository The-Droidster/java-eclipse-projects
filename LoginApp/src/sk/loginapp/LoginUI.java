package sk.loginapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginUI {

	public LoginUI() {
		this.createFrame();
	}

	private void createFrame() {

		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {

		}

		JFrame frame = new JFrame("Login App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		this.placePanelComponents(panel);

		frame.setSize(300, 160);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void placePanelComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel lblUname, lblPwd;
		JTextField tfUname, tfPwd;
		JButton btnLogin, btnCancel;

		lblUname = new JLabel("Username: ");
		lblUname.setBounds(50, 20, 120, 20);
		lblPwd = new JLabel("Password: ");
		lblPwd.setBounds(50, 50, 120, 20);

		tfUname = new JTextField(10);
		tfUname.setBounds(120, 20, 120, 20);
		tfPwd = new JTextField(10);
		tfPwd.setBounds(120, 50, 120, 20);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(50, 85, 90, 25);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(150, 85, 90, 25);

		panel.add(lblUname);
		panel.add(lblPwd);
		panel.add(tfUname);
		panel.add(tfPwd);
		panel.add(btnLogin);
		panel.add(btnCancel);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String uname = tfUname.getText().trim();
					String pwd = tfPwd.getText().trim();
					new LoginDbConn(uname, pwd);//startDB after user presses the login button
				} catch (Exception ex) {
					// TODO: handle exception
				}

			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				} catch (Exception ex) {
					// TODO: handle exception
				}
				
			}
		});

	}

}
