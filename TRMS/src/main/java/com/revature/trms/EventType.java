package com.revature.trms;

import java.io.Serializable;

public class EventType implements Serializable {
	private String name;
	private float rate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
}
