package com.revature.trms.dao;

public class DAOHolder {
	public static EmployeeDAO employeeDAO = new EmployeeDAOImp();
	public static ReimbursementRequestDAO reimbursementRequestDAO = new ReimbursementRequestDAOImp();
}
