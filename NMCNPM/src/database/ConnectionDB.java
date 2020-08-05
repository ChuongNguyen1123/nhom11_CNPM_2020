package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	public static Connection connection;

	public ConnectionDB() throws ClassNotFoundException, SQLException {
		connection = getConnection();

	}

	@SuppressWarnings("unused")
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
//		---- Cau hinh connect vs SQL server ----- 

//		String url = "jdbc:mysql://localhost:3306/TKB/";
//		String user = "root";
//		String password = "741852963";
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		Connection conn = DriverManager
//				.getConnection("jdbc:sqlserver://localhost;database=TKB;user=sa;password=741852963;");

//		----------- Cau hinh connect vs mySQL -----------
		String hostName = "localhost";
		String dbName = "database_cnpm2020";
		String userName = "root";
		String password = "Thanh Tinh";
		String url = "jdbc:mysql://" + hostName + ":3306/" + dbName
				+ "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
		Connection conn = DriverManager.getConnection(url, userName, password);
		System.out.println("connect success");
		return conn;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionDB.getConnection();
	}
}