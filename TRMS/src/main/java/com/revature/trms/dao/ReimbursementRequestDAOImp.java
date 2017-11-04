package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.trms.ConnectionFactory;
import com.revature.trms.ReimbursementRequest;

public class ReimbursementRequestDAOImp implements ReimbursementRequestDAO 
{

	static Connection conn;
	
	
	public void createRequest(ReimbursementRequest r) 
	{
		if(r.getApproval() > 2)
		{
			//TODO log warning
			//convert to proper logger
			return;
		}
		String eventDateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
		try{
			eventDateString = sdfr.format( r.getEventDate() );
		}
		catch (Exception ex ){
		System.out.println(ex);
		}
			
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("insert into (REQ_ID, REQ_REQUESTER_ID, REQ_GRADE_FORMAT, REQ_EVENT_TYPE, REQ_EVENT_DATE, REQ_ADDRESS, REQ_STATE, REQ_CITY, REQ_ZIP, REQ_DESCRIPTION, REQ_COST, REQ_JUSTIFICATION, REQ_APPROVAL) REIMBURSEMENT_REQUESTS VALUES = ?,?,?,?,TO_DATE(?, 'YY-MM-DD HH24:MI'),?,?,?,?,?,?,?,?");  
			stmt.setInt(1, r.getRequestId());
			stmt.setInt(2, r.getEmployeeId());
			stmt.setInt(3, r.getGradeFormat());
			stmt.setInt(4, r.getEventType());
			stmt.setString(5, eventDateString);
			stmt.setString(6, r.getAddress());
			stmt.setString(7, r.getState());
			stmt.setString(8, r.getCity());
			stmt.setInt(9, r.getZip());
			stmt.setString(10, r.getDescription());
			stmt.setFloat(11, r.getCost());
			stmt.setString(12, r.getJustification());
			stmt.setInt(13, r.getApproval());
			stmt.executeUpdate(); 
	
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	public void updateRequest(ReimbursementRequest r) 
	{
		String eventDateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
		try{
			eventDateString = sdfr.format( r.getEventDate() );
		}
		catch (Exception ex ){
			System.out.println(ex);
		}
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("UPDATE REIMBURSEMENT_REQUESTS SET REQ_REQUESTER_ID=?, REQ_BENCO_ID=?, REQ_GRADE_FORMAT=?, REQ_EVENT_TYPE=?, REQ_EVENT_DATE=?, REQ_ADDRESS=?, REQ_STATE=?, REQ_CITY=?, REQ_ZIP=?, REQ_DESCRIPTION=?, REQ_COST=?, REQ_JUSTIFICATION=?, REQ_APPROVAL=? WHERE REQ_ID=?");  
			stmt.setInt(1, r.getEmployeeId());
			stmt.setInt(2, r.getBencoId());
			stmt.setInt(3, r.getGradeFormat());
			stmt.setInt(4, r.getEventType());
			stmt.setString(5, eventDateString);
			stmt.setString(6, r.getAddress());
			stmt.setString(7, r.getState());
			stmt.setString(8, r.getCity());
			stmt.setInt(9, r.getZip());
			stmt.setString(10, r.getDescription());
			stmt.setFloat(11, r.getCost());
			stmt.setString(12, r.getJustification());
			stmt.setInt(13, r.getApproval());
			stmt.setInt(14, r.getRequestId());
			stmt.executeUpdate();  
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	public ReimbursementRequest getRequest(ReimbursementRequest r) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from REIMBURSEMENT_REQUESTS WHERE REQ_ID = ?");  
			stmt.setInt(1, r.getRequestId());
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{
				r.setEmployeeId(rs.getInt("REQ_REQUESTER_ID"));
				r.setBencoId(rs.getInt("REQ_BENCO_ID"));
				r.setGradeFormat(rs.getInt("REQ_GRADE_FORMAT"));
				r.setEventType(rs.getInt("REQ_EVENT_TYPE"));
				//setting date a bit grosser
				Date date = formatter.parse(rs.getString("REQ_EVENT_DATE"));
				r.setEventDate(date);
				r.setAddress(rs.getString("REQ_ADDRESS"));
				r.setState(rs.getString("REQ_STATE"));
				r.setCity(rs.getString("REQ_CITY"));
				r.setZip(rs.getInt("REQ_ZIP"));
				r.setDescription(rs.getString("REQ_DESCRIPTION"));
				r.setCost(rs.getFloat("REQ_COST"));
				r.setJustification(rs.getString("REQ_JUSTIFICATION"));
				r.setApproval(rs.getInt("REQ_APPROVAL"));
				Date date2 = formatter.parse(rs.getString("REQ_CREATION_DATE"));
				r.setCreationDate(date2);

				
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return r;
	}

	public List<ReimbursementRequest> getRequestsByEmpID(int emp_id) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		List<ReimbursementRequest> myRequests = new ArrayList<ReimbursementRequest>();
		ReimbursementRequest r = new ReimbursementRequest();
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from REIMBURSEMENT_REQUESTS WHERE REQ_REQUESTER_ID = ?");  
			stmt.setInt(1, emp_id);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{
				r.setRequestId(rs.getInt("REQ_ID"));
				r.setEmployeeId(emp_id);
				r.setBencoId(rs.getInt("REQ_BENCO_ID"));
				r.setGradeFormat(rs.getInt("REQ_GRADE_FORMAT"));
				r.setEventType(rs.getInt("REQ_EVENT_TYPE"));
				//setting string to a date
				Date date = formatter.parse(rs.getString("REQ_EVENT_DATE"));
				r.setEventDate(date);
				r.setAddress(rs.getString("REQ_ADDRESS"));
				r.setState(rs.getString("REQ_STATE"));
				r.setCity(rs.getString("REQ_CITY"));
				r.setZip(rs.getInt("REQ_ZIP"));
				r.setDescription(rs.getString("REQ_DESCRIPTION"));
				r.setCost(rs.getFloat("REQ_COST"));
				r.setJustification(rs.getString("REQ_JUSTIFICATION"));
				r.setApproval(rs.getInt("REQ_APPROVAL"));
				//setting string to a date
				Date date2 = formatter.parse(rs.getString("REQ_CREATION_DATE"));
				r.setCreationDate(date2);
				myRequests.add(r);
				
			}

		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return myRequests;
	}

}
