package com.revature.trms;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private int dep_id;
	private int supervisor_id;
	
	public Employee() {
		super();
	}
	
	public Employee(int i) {
		id = i;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public int getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}
	
	
	
	
}
