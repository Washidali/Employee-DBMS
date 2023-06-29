package DB.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorFactory {
	static String url = "jdbc:mysql://localhost:3306/rooman";
	static String username = "root";
	static String password = "root";
	static Connection  con = null;
	
	public static Connection requestConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
		
		return con;
	}

}
