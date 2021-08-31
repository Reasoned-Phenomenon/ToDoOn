package co.yedam.on;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	
	Connection con;
	Statement stmt;
	ResultSet rs;

	String user = "tdo";
	String pass = "tdo";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	public Connection connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
//			System.out.println("연결완료");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public void disconnect() {
		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	

}
