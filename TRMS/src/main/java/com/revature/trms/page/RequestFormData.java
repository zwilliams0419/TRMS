package com.revature.trms.page;

import com.revature.trms.Employee;
import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;


//Contains the data that is used to create a new reimbursement request
public class RequestFormData {
	private int employeeId, requestId;
	private String firstName, lastName, email;
	private String isNew; //Indicates whether the data is for a new form or an existing one
	
	public static RequestFormData getNewRequestFormData(int empId) {
		RequestFormData rfd = new RequestFormData();
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(empId));
		rfd.setEmployeeId(e.getId());
		rfd.firstName = e.getFirstName();
		rfd.lastName = e.getLastName();
		rfd.email = e.getEmail();
		
		rfd.isNew = String.valueOf(true);
		
		return rfd;
	}
	
	public static RequestFormData getExistingRequestFormData(int reqId) {
		RequestFormData rfd = new RequestFormData();
		ReimbursementRequest r = DAOHolder.reimbursementRequestDAO.getRequest(new ReimbursementRequest(reqId));
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(r.getEmployeeId()));
		
		//load all of the fields
		
		rfd.isNew = String.valueOf(false);
		
		return rfd;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}
