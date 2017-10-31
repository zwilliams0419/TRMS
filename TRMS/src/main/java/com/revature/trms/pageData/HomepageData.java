package com.revature.trms.pageData;

import java.util.ArrayList;
import java.util.List;

import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;

//We can use this class to find all of the data to be displayed on the homepage,
//convert it to JSON, and send it to the JS
public class HomepageData {
	private List<RequestSection> requestSections;
	private boolean isBenCo;
	
	public HomepageData(int emp_id) {
		super();
		createRequestSections(emp_id);
		//check if this person is a benco
	}
	
	public HomepageData() {
		super();
	}
	
	
	//Creates all applicable sections for the employee
	private void createRequestSections(int emp_id) {
		createMyRequestsSection(emp_id);
		
		
	}
	
	//Creates a request section for requests owned by the employee
	private void createMyRequestsSection(int emp_id) {
		RequestSection rseq = new RequestSection();
		rseq.setHeader("My requests");
		List<ReimbursementRequest> reqs = DAOHolder.reimbursementRequestDAO.getRequestsByEmpID(emp_id);
		for(ReimbursementRequest r : reqs) {
			rseq.addRequestInfo(r);
		}
		requestSections.add(rseq);
	}
	
	private void createPendingApprovalSection(int emp_id) {
		RequestSection rseq = new RequestSection();
		rseq.setHeader("Requests pending my approval");
		
		//TODO Get requests and add their info
		
		requestSections.add(rseq);
	}
	
	private class RequestSection {
		private String header;
		private List<String> requestSummary = new ArrayList<String>();
		private List<Integer> requestIDs = new ArrayList<Integer>();
		
		//Adds a few of the fields from r into requestInfo
		public void addRequestInfo(ReimbursementRequest r) {
			
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public List<String> getRequestInfo() {
			return requestSummary;
		}

		public void setRequestInfo(List<String> requestInfo) {
			this.requestSummary = requestInfo;
		}

		public List<Integer> getRequestIDs() {
			return requestIDs;
		}

		public void setRequestIDs(List<Integer> requestIDs) {
			this.requestIDs = requestIDs;
		}
	}
	
	public List<RequestSection> getRequestSections() {
		return requestSections;
	}

	public void setRequestSections(List<RequestSection> requestSections) {
		this.requestSections = requestSections;
	}

	public boolean isBenCo() {
		return isBenCo;
	}

	public void setBenCo(boolean isBenCo) {
		this.isBenCo = isBenCo;
	}
}
