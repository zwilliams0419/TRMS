package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.trms.ConnectionFactory;
import com.revature.trms.EventType;
import com.revature.trms.GradeFormat;

public class GradeFormatDAOImp implements GradeFormatDAO {

	static Connection conn;
	public GradeFormat getGradeFormat(int g_ID) {
		
		GradeFormat e = new GradeFormat();
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from EVENT_TYPES WHERE EVENT_ID = ?");  
			stmt.setInt(1, e_Id);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{	
				e.setName(rs.getString("EVENT_NAME"));
				e.setRate(rs.getFloat("EVENT_COVERAGE"));
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;	
	}

	public List<GradeFormat> getAllGradeFormats() {
		// TODO Auto-generated method stub
		return null;
	}

}
