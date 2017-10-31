package com.revature.trms.dao;

import java.util.List;

import com.revature.trms.ReimbursementRequest;

public interface ReimbursementRequestDAO {
	
	public void createRequest(ReimbursementRequest r);
	
	public void updateRequest(ReimbursementRequest r);
	
	public ReimbursementRequest getRequest(ReimbursementRequest r);
	
	public List<ReimbursementRequest> getRequestsByEmpID(int emp_id);
		
}
