import javax.swing.*;

public class UIMain {

    // Container declarations
    private JFrame frame;
    private JPanel panel;

    // Component declarations
    private JLabel lblUname, lblPwd, lblPwdCpy;
    private JTextField tfUname;
    private JPasswordField pfPwd, pfPwdCpy;
    private JButton btnSubmit, btnCancel;

    private int choice;
    private static final UIMain ui = new UIMain();

    static UIMain getInstance(){
        return ui;
    }

    private UIMain() {
        // set native OS UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
                | InstantiationException e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);


        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //================Login UI Start==============

    void loginUI(){
        frame.setTitle("Login Form");
        panel.setLayout(null);

        lblUname = new JLabel("Username: ");
        lblUname.setBounds(100, 40, 120, 20);
        lblPwd = new JLabel("Password: ");
        lblPwd.setBounds(100, 70, 120, 20);

        tfUname = new JTextField(10);
        tfUname.setBounds(170, 40, 120, 20);

        pfPwd = new JPasswordField(10);
        pfPwd.setBounds(170, 70, 120, 20);

        btnSubmit = new JButton("Login");
        btnSubmit.setBounds(100, 110, 90, 25);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(200, 110, 90, 25);

        panel.add(lblUname);
        panel.add(lblPwd);
        panel.add(tfUname);
        panel.add(pfPwd);
        panel.add(btnSubmit);
        panel.add(btnCancel);

        loginEventListener(); // delegate action listener to separate method
    }


    private void loginEventListener() { // event handler for buttons
        btnSubmit.addActionListener(e -> {


            try {
                String uname = tfUname.getText().trim();
                String pwd = new String(pfPwd.getPassword());
                new Logic().process(uname, pwd); // startDB after user presses
                // the login button
            } catch (Exception ex) {
                // TODO: handle exception
            }

        });

        btnCancel.addActionListener(e -> {
            try {
                clearLoginTF();
                System.exit(0);
            } catch (Exception ex) {
                // TODO: handle exception
            }

        });

    }

    void loginSuccess() {
        JOptionPane.showMessageDialog(null, "Logged in successfully!");
        clearLoginTF();
        System.exit(0);
    }

    void loginFailed(int count) {

        JOptionPane.showMessageDialog(null, "Login Failed! " + (3 - count) + " Login attempt/s left!");
        clearLoginTF();

    }

    private void clearLoginTF() { // clear JTextField
        tfUname.setText("");
        pfPwd.setText("");
    }

    void failExceeded() {
        JOptionPane.showMessageDialog(null, "Exceeded Max Login Attempts! Exiting!", "Too many tries", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
    //================Login UI End==============

    //================SignUp UI Start===========
    void signUpUI(){
        frame.setTitle("SignUp Form");

        panel.setLayout(null);

        lblUname = new JLabel("Enter a Username: ");
        lblUname.setBounds(70, 20, 120, 20);
        lblPwd = new JLabel("Enter a Password: ");
        lblPwd.setBounds(70, 50, 120, 20);
        lblPwdCpy = new JLabel("Re-Enter Password: ");
        lblPwdCpy.setBounds(70, 80, 120, 20);

        tfUname = new JTextField(10);
        tfUname.setBounds(190, 20, 130, 20);

        pfPwd = new JPasswordField(10);
        pfPwd.setEchoChar('\0');
        pfPwd.setBounds(190, 50, 130, 20);
        pfPwdCpy = new JPasswordField(10);
        pfPwdCpy.setEchoChar('\0');
        pfPwdCpy.setBounds(190, 80, 130, 20);

        btnSubmit = new JButton("Sign Up");
        btnSubmit.setBounds(75, 120, 110, 25);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(200, 120, 110, 25);

        panel.add(lblUname);
        panel.add(lblPwd);
        panel.add(lblPwdCpy);
        panel.add(tfUname);
        panel.add(pfPwd);
        panel.add(pfPwdCpy);
        panel.add(btnSubmit);
        panel.add(btnCancel);

        signUpEventListener(); // delegate action listener to separate method
    }

    private void signUpEventListener() { // event handler for buttons
        btnSubmit.addActionListener(e -> {

            try {
                String uname = tfUname.getText().trim();
                String pwd = new String(pfPwd.getPassword()).trim();
                String pwdCpy = new String(pfPwdCpy.getPassword()).trim();
                if ((uname != null && pwd != null && pwdCpy != null) && (!uname.isEmpty() && !pwd.isEmpty() && !pwdCpy.isEmpty())) {
                    new Logic().process(uname, pwd, pwdCpy); // startDB after user presses
                    // the SignUp button
                } else emptyFields();

            } catch (Exception ex) {
                // TODO: handle exception
            }

        });

        btnCancel.addActionListener(e -> {
            try {
                clearSignUpTF();
                System.exit(0);
            } catch (Exception ex) {
                // TODO: handle exception
            }

        });

    }

    void signUpSuccess() {
        JOptionPane.showMessageDialog(null, "Successfully Signed Up!");

        choice = JOptionPane.showConfirmDialog(null, "Do you wish to Login now?", "Login now?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) { // Login immediately
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            loginUI();
        } else {
            clearSignUpTF();
            System.exit(0);
        }
    }

    void userExists() { // Prevent username duplication

        JOptionPane.showMessageDialog(null, "User Exists. Please choose a different username.", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
        clearSignUpTF();

    }

    private void clearSignUpTF() { // clear JTextField
        tfUname.setText("");
        pfPwd.setText("");
        pfPwdCpy.setText("");
    }

    private void emptyFields() {
        JOptionPane.showMessageDialog(null, "Username and/or Password cannot be empty!", "Empty Fields", JOptionPane.WARNING_MESSAGE);
        clearSignUpTF();
    }

    void passUnEqual() {
        JOptionPane.showMessageDialog(null, "Passwords don't match!", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
        //clearTF();
        pfPwd.setText("");
        pfPwdCpy.setText("");
    }

    void noSpaceAllowed() {
        JOptionPane.showMessageDialog(null, "Username and/or Password cannot contain space!", "No spaces allowed", JOptionPane.WARNING_MESSAGE);
        clearSignUpTF();
    }

    //================SignUp UI End==============
}
