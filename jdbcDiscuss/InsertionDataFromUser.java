package jdbcDiscuss;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertionDataFromUser {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclass";
		String un = "root";
		String pwd = "root";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
			
			con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection Estd");
			
			con.setAutoCommit(false);
			
			String query = "insert into emp (`id`,`name`,`dsig`,`salary`) values (?,?,?,?)";
			
			pstmt = con.prepareStatement(query);
			
			System.out.println("Enter no of records you want to add to DB");
			Scanner scan = new Scanner(System.in);
			int n = scan.nextInt();
			for (int i = 1; i <= n; i++) {
				System.out.println("Enter ID");
				int id = scan.nextInt();
				System.out.println("Enter Name");
				String name = scan.next();
				System.out.println("Enter Designation");
				String dsig = scan.next();
				System.out.println("Enter Salary");
				int salary = scan.nextInt();
				
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, dsig);
				pstmt.setInt(4, salary);
/* this may result in incomplete DB if abrupt termination takes place coz query executing one by one
 and commiting to DB simultaneously*/
				pstmt.execute();
			}
			con.commit();
			
			System.out.println("Query Executed Successfully");
			
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
