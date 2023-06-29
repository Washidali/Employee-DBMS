package jdbcDiscuss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDataFromDB {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclass";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,un,pwd);
			
			stmt = con.createStatement();
			String query = "DELETE FROM emp WHERE `salary` BETWEEN 80000 AND 100000";
			System.out.println(stmt.executeUpdate(query));
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
