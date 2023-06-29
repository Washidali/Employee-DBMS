package DB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DB.Connector.ConnectorFactory;
import DB.DTO.Employee;

public class EmployeeDAOImp implements EmployeeDAO {

	@Override
	public List getEmployees() {
		ArrayList<Employee> emplist = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			emplist = new ArrayList<Employee>();
			con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM `employee`";
			stmt = con.createStatement();
			res = stmt.executeQuery(query);
			while(res.next()) {
				int emp_id = res.getInt(1);
				String first_name = res.getString(2);
				String last_name = res.getString(3);
				String email = res.getString(4);
				Date hire_date = res.getDate(5);
				int salary = res.getInt(6);
				int dept_id = res.getInt(7);
				Employee e = new Employee(emp_id, first_name, last_name, email, hire_date, salary, dept_id);
				emplist.add(e);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emplist;
	}

	@Override
	public Employee getEmployee(int emp_id) {
		Employee e = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			con = ConnectorFactory.requestConnection();
			String query = ("SELECT * FROM employee WHERE emp_id = ?");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, emp_id);
			res = pstmt.executeQuery();
			if (res.next()) {
			 	e = new Employee(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5), res.getInt(6), res.getInt(7));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} 
		finally {
				try {
					res.close();
					pstmt.close();
					con.close();
				} catch (SQLException e1) {
				e1.printStackTrace();
				}
		}
		return e;
	}

	@Override
	public boolean insertEmployee(int emp_id, String first_name, String last_name, String email,
			String hire_date, int salary, int dept_id) {
		int i=0;
		Connection con = null;
		try {
			con = ConnectorFactory.requestConnection();
			con.setAutoCommit(false);
			Employee e = getEmployee(emp_id);
			if (e!=null) {
				System.out.println("Employee ID already exixt try with different one!");
			} else {
				String query = "INSERT INTO employee VALUES (?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, emp_id);
				pstmt.setString(2, first_name);
				pstmt.setString(3, last_name);
				pstmt.setString(4, email);
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				  LocalDate localDate = LocalDate.parse(hire_date, formatter);
				  java.sql.Date dob   = java.sql.Date.valueOf(localDate);
				
				pstmt.setDate(5, dob);
				pstmt.setInt(6, salary);
				pstmt.setInt(7, dept_id);
				i = pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		if (i==1) {
			try {
				con.commit();
				con.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateEmpoloyee(Employee e) {
		int i = 0;
		Connection con = null;
		try {
			con = ConnectorFactory.requestConnection();
			con.setAutoCommit(false);
			String query = "UPDATE `employee` SET `first_name`= ?,`last_name`= ?,`email`= ?,"
					+ "`salary`= ?,`dept_id`= ? WHERE `emp_id`= ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, e.getFirst_name());
			pstmt.setString(2, e.getLast_name());
			pstmt.setString(3, e.getEmail());
			pstmt.setInt(4, e.getSalary());
			pstmt.setInt(5, e.getDept_id());
			pstmt.setInt(6, e.getEmp_id());
			i = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		if(i==1) {
			try {
				con.commit();
				con.close();
				return true;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String deleteEmployee(int emp_id) {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		Connection con = null;
		try {
			con = ConnectorFactory.requestConnection();
			con.setAutoCommit(false);
			String query1 = ("SELECT * FROM employee WHERE emp_id = ?");
			PreparedStatement pstmt1 = con.prepareStatement(query1);
			pstmt1.setInt(1, emp_id);
			ResultSet res = pstmt1.executeQuery();
			if (res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+
						res.getString(4)+" "+res.getDate(5)+" "+res.getInt(6)+" "+res.getInt(7));
						
						System.out.println("Type YES if you want to delete the data Else NO ");
						String choice = scan.next();
						if (choice.equalsIgnoreCase("YES")) {
							String query2 = "DELETE FROM employee WHERE `emp_id`= ?";
							PreparedStatement pstmt2 = con.prepareStatement(query2);
							pstmt2.setInt(1, emp_id);
							i = pstmt2.executeUpdate();
						} else if ((choice.equalsIgnoreCase("NO"))) {
							return "NO";
						} else {
							return "";
						}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (i==1) {
			try {
				con.commit();
				con.close();
				return "true";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "false";
	}
}

