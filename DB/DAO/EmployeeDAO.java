package DB.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import DB.DTO.Employee;

public interface EmployeeDAO {
	
	List getEmployees();
	
	Employee getEmployee(int emp_id);
	
	boolean insertEmployee(int emp_id, String first_name, String last_name, String email, 
			String hire_date, int salary, int dept_id);
	
	boolean updateEmpoloyee(Employee e);
	
	String deleteEmployee(int emp_id);
	
}
