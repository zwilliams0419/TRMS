package com.revature.trms.page;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.Employee;
import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;


//Contains the data that is used to create a new reimbursement request
public class RequestFormData implements Serializable {
	private int employeeId, requestId;
	private String firstName, lastName, email;
	
	public RequestFormData() {
		super();
	}
	
	public static String getNewRequestFormData(int empId) throws JsonProcessingException {
		RequestFormData rfd = new RequestFormData();
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(empId));
		rfd.employeeId = e.getId();
		rfd.firstName = e.getFirstName();
		rfd.lastName = e.getLastName();
		rfd.email = e.getEmail();
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(rfd);
	}
	
	public static String getExistingRequestFormData(int reqId) throws JsonProcessingException {
		RequestFormData rfd = new RequestFormData();
		ReimbursementRequest r = DAOHolder.reimbursementRequestDAO.getRequest(new ReimbursementRequest(reqId));
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(r.getEmployeeId()));
		
		//load all of the fields
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(rfd);
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
	
}
