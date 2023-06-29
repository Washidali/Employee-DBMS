package DB.DTO;

import java.util.Date;

public class Employee {
	private int emp_id;
	private String first_name;
	private String last_name;
	private String email;
	private Date hire_date;
	private int salary;
	private int dept_id;
	
	public Employee() {
		
	}
	
	public Employee(int emp_id, String first_name, String last_name, String email, Date hire_date,
			int salary, int dept_id) {
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.hire_date = hire_date;
		this.salary = salary;
		this.dept_id = dept_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	
	public String toString() {
		return emp_id + " " + first_name + " " + last_name + " " + email + " " + hire_date + " " + 
	" " + salary + " " + dept_id;
	}
	
	
	
}
