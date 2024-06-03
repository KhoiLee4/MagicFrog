package GameData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;

		try {

			// Register the Oracle JDBC driver with DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			String url = "jdbc:mysql://LAPTOP-9UDVFSM0/game_data";
//			String user = "btl";
//			String password = "000000";
			
			// String url = "jdbc:mysql://localhost:3306/game_data";
			// String user = "root";
			// String password = "792004+zZ";

			  String url = "jdbc:mysql://localhost:3306/game_data";
			  String user = "root";
			  String password = "khoile";

			c = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printInfo(Connection c) {

		try {
			if (c != null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
