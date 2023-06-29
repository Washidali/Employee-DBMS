package jdbcDiscuss;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DB.DAO.EmployeeDAOImp;
import DB.DTO.Employee;

public class DriverDAO {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		EmployeeDAOImp emplDAOImp = new EmployeeDAOImp();
		List<Employee> employees = emplDAOImp.getEmployees();
		
		System.out.println("<----Welcome to TapAcademy Database---->");
		System.out.println("1.See all employee details Of TapAcademy");
		System.out.println("2.See the employee details by emp_id");
		System.out.println("3.Update detail of Employee");
		System.out.println("4.Insert new Employee details to database");
		System.out.println("5.Delete Employee from database");
		System.out.println("Enter your Choice:");
		
		int choice = scan.nextInt();
		
		if (choice==1) {
			for(Employee e : employees) {
				System.out.println(e);
			}
		} else if(choice==2){
			System.out.println("Enter the emp id you want to see details of:");
			int emp_id = scan.nextInt();
			Employee e= emplDAOImp.getEmployee(emp_id);
				if (e!=null) {
					System.out.println(e);
				}else {
					System.out.println("Invalid Emp ID/Employee Not Found!");
				}
		} else if(choice==3) {
			System.out.println("Enter the emp id you want to update detail:");
			int emp_id = scan.nextInt();
			Employee e= emplDAOImp.getEmployee(emp_id);
			if (e!=null) {
				System.out.println("What you want to update:\n"
						+ "1.first_name\n"
						+ "2.last_name\n"
						+ "3.email\n"
						+ "4.salary\n"
						+ "5.dept_id\n"
						+ "6.All");
				int updateChoice = scan.nextInt();
				if(updateChoice==1) {
					System.out.println("Please enter your new name:");
					String newFirstName = scan.next();
					e.setFirst_name(newFirstName);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("First Name Got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				}else if (updateChoice==2) {
					System.out.println("Please enter your new lastName:");
					String newLastName = scan.next();
					e.setLast_name(newLastName);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("Last Name Got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				}else if (updateChoice==3) {
					System.out.println("Please enter your new email:");
					String newEmail = scan.next();
					e.setEmail(newEmail);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("Email Got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				}else if (updateChoice==4) {
					System.out.println("Please enter the salary you want to update:");
					int updateSalary = scan.nextInt();
					e.setSalary(updateSalary);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("Salary Got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				}else if (updateChoice==5) {
					System.out.println("Please enter the new department id:");
					int newDep_Id = scan.nextInt();
					e.setDept_id(newDep_Id);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("Dept_Id Got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				}else if (updateChoice==6) {
					System.out.println("Please enter your new name:");
					String newFirstName = scan.next();
					e.setFirst_name(newFirstName);
					System.out.println("Please enter your new lastName:");
					String newLastName = scan.next();
					e.setLast_name(newLastName);	
					System.out.println("Please enter your new email:");
					String newEmail = scan.next();
					e.setEmail(newEmail);
					System.out.println("Please enter the salary you want to update:");
					int updateSalary = scan.nextInt();
					e.setSalary(updateSalary);
					System.out.println("Please enter the new department id:");
					int newDep_Id = scan.nextInt();
					e.setDept_id(newDep_Id);
					boolean testCase = emplDAOImp.updateEmpoloyee(e);
					if (testCase==true) {
						System.out.println("Necessary Data got Updated Successfully");
					}else {
						System.out.println("Error In Changes");
					}
				} else {
					System.out.println("You entered the wrong choice...please try again!");
					return;
				}
			} else {
				System.out.println("Employee Not Found!");
			}
			
		} else if (choice==4) {
			System.out.println("Enter new emp_id");
			int emp_id = scan.nextInt();
			System.out.println("Enter First Name of an Employee:");
			String first_name = scan.nextLine();
			System.out.println("Enter Last Name of an Employee:");
			String last_name = scan.nextLine();
			System.out.println("Enter Email of an Employee:");
			String email = scan.next();
			System.out.println("Enter Date of joining of an Employee:");
			String hire_date = scan.next();
			
			
			
//			Date date = null;
//			try {
//				date = new SimpleDateFormat("yyyy-MM-dd").parse(hire_date);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			System.out.println("Enter Salary of an Employee:");
			int salary = scan.nextInt();
			System.out.println("Enter Department_id of an Employee:");
			int dept_id = scan.nextInt();
			boolean testCase = emplDAOImp.insertEmployee(emp_id, first_name, last_name, email, hire_date, salary, dept_id);
			if (testCase==true) {
				System.out.println("New Employee Detail Got Added Successfully");
			}else {
				System.out.println("Error In Changes");
			}
		} else if(choice==5) {
			System.out.println("Enter the emp_id of whom record you wanted to delete:");
			int emp_id = scan.nextInt();
			String testCase = emplDAOImp.deleteEmployee(emp_id);
			if (testCase=="true") {
				System.out.println("Employee Detail Got Deleted Successfully");
			} else if (testCase=="false") {
				System.out.println("Invalid Emp ID");
			} else if (testCase=="NO") {
				System.out.println("No Changes In DataBase");
			} else if (testCase=="") {
				System.out.println("You entered a wrong choice!");
			}
		} else {
			System.out.println("You Entered The Wrong Choice....Please try again!");
			return;
	    }

	}
}
