package com.revature.trms.pageData;

import java.util.List;

import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;

//We can use this class to find all of the data to be displayed on the homepage,
//convert it to JSON, and send it to the JS
public class HomepageData {
	private String[] requestHeaders;
	private List<ReimbursementRequest>[] relatedRequests;
	private boolean isBenCo;
	
	public HomepageData() {
		super();
	}
	
	private void findRelatedRequests(int emp_id) {
		requestHeaders[0] = "My requests";
		relatedRequests[0] = DAOHolder.reimbursementRequestDAO.getRequestsByEmpID(emp_id);

	}
}
