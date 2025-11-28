package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_controller {
	
	public static Connection connectToDB() {
		Connection conn = null;
		try {
            conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/sys?allowLoadLocalInfile=true&serverTimezone=Asia/Jerusalem&useSSL=false",
                "root",
                "Aa123456"
            );
            System.out.println("SQL connection succeed");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
	}
}
