package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleJdbcDemo {

	public static void main(String[] args) {
		try {

			// 0. Register the JDBC drivers
			String driverClass = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverClass); 
			// or DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

			// 1. Get a connection to the Database
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String uname = "scott";
			String pwd = "tiger";
			Connection conn = DriverManager.getConnection(dbUrl, uname, pwd);

			// 2. Create a statement
			Statement st = conn.createStatement();

			// 3. Execute SQL query
			String sql = "select * from user_demo";
			ResultSet rs = st.executeQuery(sql);

			// 4. Process the result set
			while (rs.next()) {
				System.out.println(rs.getString(1) + "  " + rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
