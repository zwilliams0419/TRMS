package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.ConnectionFactory;
import com.revature.trms.GradeFormat;

public class GradeFormatDAOImp implements GradeFormatDAO {

	static Connection conn;
	public GradeFormat getGradeFormat(int g_Id) {

		GradeFormat g = new GradeFormat();
		
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from GRADE_FORMAT WHERE GF_ID = ?");  
			stmt.setInt(1, g_Id);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{	
				g.setName(rs.getString("GF_NAME"));
				g.setMaxGrade(rs.getInt("GF_MAX_GRADE"));
				g.setDefaultPassing(rs.getInt("GF_DEFAULT_PASSING"));
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return g;	
	}

	public List<GradeFormat> getAllGradeFormats() {

		List<GradeFormat> myGradeFormats = new ArrayList<GradeFormat>();

		GradeFormat g = new GradeFormat();

		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from GRADE_FORMAT");  
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{	
				g.setName(rs.getString("GF_NAME"));
				g.setMaxGrade(rs.getInt("GF_MAX_GRADE"));
				g.setDefaultPassing(rs.getInt("GF_DEFAULT_PASSING"));
				myGradeFormats.add(g);
			}

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return myGradeFormats;
	}
}
