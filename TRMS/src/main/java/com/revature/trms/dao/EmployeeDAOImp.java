package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.Employee;
import com.revature.trms.ConnectionFactory;

public class EmployeeDAOImp implements EmployeeDAO {
	
	public static ConnectionFactory cf = new ConnectionFactory();


	public void updateEmployeeEmail(int id, String email) 
	{
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("UPDATE EMPLOYEE SET EMP_EMAIL=? WHERE E_ID=?");  
			stmt.setString(1,email); 
			stmt.setInt(2,id);  
			stmt.executeUpdate();  
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public Integer loginCheck(String username , String pass)
	{
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from EMPLOYEES WHERE EMP_USERNAME = ?");  
			stmt.setString(1, username);
			ResultSet rs=stmt.executeQuery(); 
			if(rs.next())
			{
				String pass2 = rs.getString(8);
				if(pass.equals(pass2))
				{
					int E_ID = rs.getInt(1);
					conn.close();
					return E_ID;
				}
				
			}
			conn.close();

		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		return null;
	}

	public Employee getEmployee(Employee e) 
	{
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * from EMPLOYEES WHERE EMP_ID = ?");  
			stmt.setInt(1, e.getId());
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())
			{
				
				//TODO dont know if we wanted to save username and password for something?
				//e.setId(rs.getInt(1));
				e.setFirstName(rs.getString(2));
				e.setLastName(rs.getString(3));
				e.setSupervisor_id(rs.getInt(4));
				e.setDep_id(rs.getInt(5));
				e.setEmail(rs.getString(6));
				
				
			}
			conn.close();

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
		
	}

}
