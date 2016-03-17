import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Logic {

    private Connection conn;
    private PreparedStatement pst; // type of query
    private ResultSet rs; // holds query result
    private String sql;
    private String hashedPassword;

    private static int count = 0;

    private UIMain ui = UIMain.getInstance();

    public Logic() {
        conn = new DBConn().getConn();
    }

    void process(String uname, String pwd) { // process ResultSet to authenticate login



        try {

            //TODO: Call passwordHash();
            hashedPassword = DigestUtils.sha256Hex(pwd);
            // 2. Create a statement
            // String sql = "SELECT * FROM users WHERE name = '"+uname+"' and
            // password = '"+pwd+"'";
            // Statement st = conn.createStatement();
            sql = "select * from userdemo where uname = ? and passhash = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, uname);
            pst.setString(2, hashedPassword);

            // 3. Execute SQL query
            rs = pst.executeQuery();

            // 4. Process the result set
            if (rs.next()) {
                ui.loginSuccess();

            } else {
                count++; // Counter for login attempts
                if (count < 3) { // Allow only 3 login attempts
                    ui.loginFailed(count);
                } else ui.failExceeded();

            }
            // 5. Close Connection/ Clean up environment
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    void process(String uname, String pwd, String pwdCpy) { //



        try {
            rs = checkUserExists(uname);
            if (rs.next()) {
                ui.userExists(); //
            } else {
                checkPassword(uname, pwd, pwdCpy);
            }
            // 5. Close Connection/ Clean up environment
            rs.close();
            pst.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    private ResultSet checkUserExists(String uname) {
        try {
            // 2. Create a statement
            sql = "select * from userdemo where uname = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, uname);

            // 3. Execute SQL query
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void insertUser(String uname, String pwd) {
        try {
            // TODO: Call passwordHash();
            hashedPassword = DigestUtils.sha256Hex(pwd);

            sql = "INSERT INTO userdemo(uname, passhash) VALUES(?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, uname);
            pst.setString(2, hashedPassword);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkPassword(String uname, String pwd, String pwdCpy) {
        if (pwd.equals(pwdCpy)) {
            if (!(pwd.contains(" ") || uname.contains(" "))) {
                insertUser(uname, pwd);
                ui.signUpSuccess(); //
            } else {
                ui.noSpaceAllowed();
            }
        } else {
            ui.passUnEqual();
        }
    }
}
