package com.revature.trms.dao;

import com.revature.trms.Employee;

public interface EmployeeDAO {

	public Employee getEmployee(Employee e);
	
	public void updateEmployeeEmail(int id, String email);
	
	public Integer loginCheck(String username , String pass);
}
