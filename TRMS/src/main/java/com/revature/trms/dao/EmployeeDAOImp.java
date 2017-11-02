package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.Employee;
import com.revature.trms.ConnectionFactory;

public class EmployeeDAOImp implements EmployeeDAO {
	
	static Connection conn;

	public void updateEmployeeEmail(int id, String email) 
	{
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("UPDATE EMPLOYEE SET EMP_EMAIL=? WHERE E_ID=?");  
			stmt.setString(1,email); 
			stmt.setInt(2,id);  
			stmt.executeUpdate();  
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public Integer loginCheck(String username , String pass)
	{
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT emp_id, emp_password from EMPLOYEES WHERE EMP_USERNAME = ?");  
			stmt.setString(1, username);
			ResultSet rs=stmt.executeQuery(); 
			if(rs.next())
			{
				int pass2 = rs.getInt("emp_password");
				if(pass.hashCode() == pass2)
				{
					int E_ID = rs.getInt("emp_id");
					return E_ID;
				}
				
			}

		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		return null;
	}

	public Employee getEmployee(Employee e) 
	{ 
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

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
		
	}

}
