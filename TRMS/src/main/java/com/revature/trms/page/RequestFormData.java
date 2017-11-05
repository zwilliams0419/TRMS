package com.revature.trms.page;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
	private int gradeFormat, eventType, zip, approval, passingGrade;
	private float cost, reimbursementAmount, finalGrade;
	private String firstName, lastName, email, eventDate, address, city, state, description, justification, creationDate;
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
		SimpleDateFormat sdfr = new SimpleDateFormat("MM/dd/yyyy");
		
		RequestFormData rfd = new RequestFormData();
		ReimbursementRequest r = DAOHolder.reimbursementRequestDAO.getRequest(new ReimbursementRequest(reqId));
		Employee e = DAOHolder.employeeDAO.getEmployee(new Employee(r.getEmployeeId()));
		
		rfd.requestId = r.getRequestId();
		rfd.requesterId = e.getId();
		rfd.firstName = e.getFirstName();
		rfd.lastName = e.getLastName();
		rfd.email = e.getEmail();
		rfd.gradeFormat = r.getGradeFormat();
		rfd.eventType = r.getEventType();
		rfd.zip = r.getZip();
		rfd.approval = r.getApproval();
		rfd.passingGrade = r.getPassingGrade();
		rfd.cost = r.getCost();
		rfd.eventDate = sdfr.format(r.getEventDate());
		rfd.address = r.getAddress();
		rfd.city = r.getCity();
		rfd.state = r.getState();
		rfd.description = r.getDescription();
		rfd.justification = r.getJustification();
		rfd.creationDate = sdfr.format(r.getCreationDate());
		rfd.reimbursementAmount = r.getReimbursementAmount();
		rfd.finalGrade = r.getFinalGrade();
		rfd.setEventTypes(DAOHolder.eventTypeDAO.getAllEventTypes());
		rfd.setGradeFormats(DAOHolder.gradeFormatDAO.getAllGradeFormats());
		
		
		rfd.role = 1;
		//TODO determine the role properly
		
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

	public int getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(float reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public float getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(float finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}	
}
