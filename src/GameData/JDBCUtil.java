package GameData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			// Register the Oracle JDBC driver with DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/game_data";
            String user = "btl";
            String password = "000000";
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
	
	public static void main(String[] args) {
//		try {
//			Connection connection = JDBCUtil.getConnection();
//			
//			Statement st = connection.createStatement();
//			
//			String sql = "INSERT INTO account (username, password) VALUES \r\n"
//					+ "('user1', 'password1'),\r\n"
//					+ "('user2', 'password2');";
//			
//			int check = st.executeUpdate(sql);
//			
//			System.out.println("Số dòng thay đổi: " + check);
//			
//			if(check > 0) {
//				System.out.println("Thêm dữ liệu thành công!");
//			}else {
//				System.out.println("Thêm dữ liệu thất bại!");
//			}
//			
//			JDBCUtil.closeConnection(connection);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
}
