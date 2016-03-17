

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConn {
    // DB related parameters
    private String driverClass; // name of the oracle jdbc driver
    private String dburl, dbuname, dbpwd; // db connection parameters
    private Connection conn; // Connection object

    public DBConn() {

    }

    Connection getConn() {
        try {
            // 0. Register the JDBC drivers
            driverClass = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverClass);
            // or DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // 1. Get a connection to the Database
            dburl = "jdbc:oracle:thin:@localhost:1521:xe";
            dbuname = "scott";
            dbpwd = "tiger";
            conn = DriverManager.getConnection(dburl, dbuname, dbpwd);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}