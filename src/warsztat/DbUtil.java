package warsztat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {


	private static String DB_URL = "jdbc:mysql://localhost:3306/workshop?useSSL=false";
	private static String DB_USRNM = "root";
	private static String DB_PSSWRD = "coderslab";
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch (Exception e) {
			
		}
		
		return DriverManager.getConnection(DB_URL, DB_USRNM, DB_PSSWRD); 
	}
		
	

}
