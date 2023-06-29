package jdbcDiscuss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class Demo1 {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/jdbcclass";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Successfully loaded");
			
			con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection established");
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,0);
			String query = "select * from emp";
			res = stmt.executeQuery(query);
			System.out.println("Query executed successfully");
			
			ResultSetMetaData metaData = res.getMetaData();
			for(int i=1; i<=metaData.getColumnCount(); i++) {
				System.out.println(metaData.getColumnName(i)+ " " +metaData.getColumnTypeName(i));
			}
			
			while(res.next()) {
				System.out.println(res.getInt(1)+ " " +res.getString(2)+ " " +res.getString(3)+ " " +
						res.getInt(4));
			}	
			
				res.first();
				System.out.println(res.getInt(1)+ " " +res.getString(2)+ " " +res.getString(3)+ " " +
						res.getInt(4));
				res.absolute(2);
				System.out.println(res.getInt(1)+ " " +res.getString(2)+ " " +res.getString(3)+ " " +
						res.getInt(4));
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver  not Successfully loaded");
		} catch (SQLException e) {
			System.out.println("Connection not established");
		}
		
		try {
			res.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
