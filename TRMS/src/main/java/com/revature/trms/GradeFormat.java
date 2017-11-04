package com.revature.trms;

import java.io.Serializable;

public class GradeFormat implements Serializable {
	private String name;
	private int maxGrade;
	private int defaultPassing;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxGrade() {
		return maxGrade;
	}
	public void setMaxGrade(int maxGrade) {
		this.maxGrade = maxGrade;
	}
	public int getDefaultPassing() {
		return defaultPassing;
	}
	public void setDefaultPassing(int defaultPassing) {
		this.defaultPassing = defaultPassing;
	}
}
