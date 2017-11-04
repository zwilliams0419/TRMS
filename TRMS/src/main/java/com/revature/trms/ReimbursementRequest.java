package com.revature.trms;

import java.util.Date;

public class ReimbursementRequest {
	private int requestId;
	private int employeeId;
	private int bencoId;
	private int gradeFormat;
	private int eventType;
	private Date eventDate;
	private String address;
	private String state;
	private String city;
	private int zip;
	private String description;
	private float cost;
	private String justification;
	private int approval;
	private Date creationDate;
	private Date statusChangeDate;
	private int passingGrade;
	private float finalGrade;
	
	
	
	public ReimbursementRequest() {
		super();
	}
	
	public ReimbursementRequest(int i) {
		requestId = i;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getBencoId() {
		return bencoId;
	}

	public void setBencoId(int bencoId) {
		this.bencoId = bencoId;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date startDate) {
		this.creationDate = startDate;
	}

	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	public float getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(float finalGrade) {
		this.finalGrade = finalGrade;
	}
}
