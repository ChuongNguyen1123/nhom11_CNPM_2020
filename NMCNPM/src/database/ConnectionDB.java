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
		String url = "jdbc:mysql://localhost:3306/TKB/";
		String user = "root";
		String password = "741852963";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost;database=TKB;user=sa;password=741852963;");
		System.out.println("connect success");
		return conn;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ConnectionDB().getConnection();
	}
}
