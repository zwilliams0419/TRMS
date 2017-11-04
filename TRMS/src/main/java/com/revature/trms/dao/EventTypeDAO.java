package com.revature.trms.dao;

import java.util.List;

import com.revature.trms.EventType;


public interface EventTypeDAO {
	
	public EventType getEventType(int e_ID);
	
	public List<EventType> getAllEventTypes();

}
