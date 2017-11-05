package com.revature.trms.page;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.Employee;
import com.revature.trms.EventType;
import com.revature.trms.GradeFormat;
import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;


//Contains the data that is used to create a new reimbursement request
public class RequestFormData implements Serializable {
	private int requesterId, requestId;
	private int role; //1 for creator, 2 for approver, 3 for Benco
	private String firstName, lastName, email;
	private List<EventType> eventTypes;
	private List<GradeFormat> gradeFormats;
	
	public RequestFormData() {
		super();
	}
	
	public static String getNewRequestFormData(int empId) throws JsonProcessingException {
		RequestFormData rfd = new RequestFormData();
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(empId));
		rfd.requesterId = e.getId();
		rfd.firstName = e.getFirstName();
		rfd.lastName = e.getLastName();
		rfd.email = e.getEmail();
		rfd.setEventTypes(DAOHolder.eventTypeDAO.getAllEventTypes());
		rfd.setGradeFormats(DAOHolder.gradeFormatDAO.getAllGradeFormats());
		
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

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}

	public List<GradeFormat> getGradeFormats() {
		return gradeFormats;
	}

	public void setGradeFormats(List<GradeFormat> gradeFormats) {
		this.gradeFormats = gradeFormats;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}	
}
