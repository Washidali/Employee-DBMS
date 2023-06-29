package jdbcDiscuss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class UpdateDataIntoDB {

	public static void main(String[] args) {
		String path = "C:/Users/syedw/eclipse-workspace1/jdbcDiscuss/src/utilities/mysql_info.properties";
		FileInputStream fis = null;
		Properties p = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			fis = new FileInputStream(path);
			p = new Properties();
			p.load(fis);
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			stmt = con.createStatement();
			
			String query = "update `emp` set `salary` = `salary`*1.20";
			System.out.println("record affected: " +stmt.executeUpdate(query)); 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}

}
