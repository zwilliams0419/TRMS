package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.ConnectionFactory;
import com.revature.trms.EventType;


public class EventTypeDAOImp implements EventTypeDAO 
{

	static Connection conn;
	
	public EventType getEventType(int e_Id) 
	{
		EventType e = new EventType();
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from EVENT_TYPES WHERE EVENT_ID = ?");  
			stmt.setInt(1, e_Id);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{	
				e.setId(rs.getInt("EVENT_ID"));
				e.setName(rs.getString("EVENT_NAME"));
				e.setRate(rs.getFloat("EVENT_COVERAGE"));
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
	}

	public List<EventType> getAllEventTypes() 
	{
		List<EventType> myEvents = new ArrayList<EventType>();
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from EVENT_TYPES");  
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{	
				EventType e = new EventType();
				e.setId(rs.getInt("EVENT_ID"));
				e.setName(rs.getString("EVENT_NAME"));
				e.setRate(rs.getFloat("EVENT_COVERAGE"));
				myEvents.add(e);
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}

		return myEvents;
	}

}
