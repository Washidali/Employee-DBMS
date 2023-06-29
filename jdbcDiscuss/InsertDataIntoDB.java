package jdbcDiscuss;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class InsertDataIntoDB {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclass";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		Statement stmt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("Driver loaded successfully");
			
			con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection established successfully");
			
			stmt = con.createStatement();
			
			String query1 = "insert into emp (`id`,`name`,`dsig`,`salary`) values (06,'Rohan','CMO',40000)";
			String query2 = "insert into emp (`id`,`name`,`dsig`,`salary`) values (07,'Joyti','SHM',35000)";
			String query3 = "insert into emp (`id`,`name`,`dsig`,`salary`) values (08,'Pushpa','TCO',30000)";
			
//			stmt.execute(query3);(when single-single query u want to add into DB usually !=prefer)
			stmt.addBatch(query1); //(batch process hit one time to HD and execute as many query u want to add)
			stmt.addBatch(query2);
			stmt.addBatch(query3);
			
			stmt.executeBatch();
			System.out.println("Queries Successfully execute");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
