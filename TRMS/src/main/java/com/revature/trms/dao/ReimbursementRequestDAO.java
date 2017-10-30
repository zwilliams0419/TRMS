package com.revature.trms.dao;

import com.revature.trms.ReimbursementRequest;

public interface ReimbursementRequestDAO {
	
	public void createRequest(ReimbursementRequest r);
	
	public void updateRequest(ReimbursementRequest r);
	
	public void getRequest(ReimbursementRequest r);

}
