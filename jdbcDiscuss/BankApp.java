package jdbcDiscuss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;
import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) {
		String path = "C:/Users/syedw/eclipse-workspace1/jdbcDiscuss/src/utilities/mysql_bankinfo.properties";
		FileInputStream fis = null;
		Properties p = null;
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet res1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet res2 = null;
		PreparedStatement pstmt5 = null;
		ResultSet res3 = null;
		
		
		try {
			fis = new FileInputStream(path);
			p = new Properties();
			p.load(fis);
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
			con.setAutoCommit(false);
			
//			login detail
			Scanner scan = new Scanner(System.in);
			System.out.println("<----Welcome to Tap Bank---->");
			System.out.println("Account Number:");
			int acc_num = scan.nextInt();
			System.out.println("Enter pin:");
			int pin = scan.nextInt();
			
			String query1 = "SELECT `name`,`balance` FROM `account` WHERE `acc_num`= ? AND `pin`= ?";
			pstmt1 = con.prepareStatement(query1);
			pstmt1.setInt(1, acc_num);
			pstmt1.setInt(2, pin);
			
			res1 = pstmt1.executeQuery();
			res1.next();
			System.out.println("Wlecome "+res1.getString(1)+"!"+"\n"+
			"Your available balance: "+res1.getInt(2));
			
			
//			transfer module
			System.out.println("<----Transfer Details---->");
			System.out.println("Enter beneficiary Account Number:");
			int bacc_num = scan.nextInt();
			System.out.println("Enter Amount to be transfer:");
			int amount = scan.nextInt();
			while (amount<0) {
				System.out.println("Invalid Amount...please try with correct amount!");
				amount = scan.nextInt();
				if(amount<0) {
					continue;
				}
				
			}
			
			Savepoint s = con.setSavepoint();
			String query2 = "UPDATE `account` SET `balance`= balance - ? WHERE `acc_num`= ?";
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setInt(1, amount);
			pstmt2.setInt(2, acc_num);
			
			pstmt2.executeUpdate();
			
//			confirm detail by beneficiary
			if (res1.getInt(2)>=amount) {
				System.out.println("<----Incomming Credit Request---->");
				System.out.println(res1.getString(1)+" "+"account no "+acc_num+"\n"+"wants to transfer "
						+amount+" Rupees");
				System.out.println("Enter your choice:Y/N");
				String choice = scan.next();
				
				  if (choice.equalsIgnoreCase("Y")) {
						String query3 = "UPDATE `account` SET `balance`= balance + ? WHERE `acc_num`= ?";
						pstmt3 = con.prepareStatement(query3);
						pstmt3.setInt(1, amount);
						pstmt3.setInt(2, bacc_num);
						
						pstmt3.executeUpdate();
						
						String query4 = "SELECT `balance` FROM `account` WHERE `acc_num`= ?";
						pstmt4 = con.prepareStatement(query4);
						pstmt4.setInt(1, bacc_num);
						
						res2 = pstmt4.executeQuery();
						while (res2.next()) {
							System.out.println("Updated Balance: "+res2.getInt(1));
						}
					
				  }  else if(choice.equalsIgnoreCase("N")){
					  	con.rollback();
						String query5 = "SELECT `balance` FROM `account` WHERE `acc_num`= ?";
						pstmt5 = con.prepareStatement(query5);
						pstmt5.setInt(1, bacc_num);
						res3 = pstmt5.executeQuery();
						while (res3.next()) {
							System.out.println("Existing Balance: "+res3.getInt(1));
						}
	
				  }  else {
					  	con.rollback();
					  	System.out.println("Wrong Choice Transaction Failed");
				  }
				  
			} else {
				con.rollback();
				System.out.println("Insufficent Fund! Transaction Failed....");
			}
			
			con.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (res3!=null) {
					res3.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt5!=null) {
					pstmt5.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (res2!=null) {
					res2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt4!=null) {
					pstmt4.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt3!=null) {
					pstmt3.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt2!=null) {
					pstmt2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (res1!=null) {
					res1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt1!=null) {
					pstmt1.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

} 
