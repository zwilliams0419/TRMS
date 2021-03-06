package com.revature.trms.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;

//We can use this class to find all of the data to be displayed on the homepage,
//as a JSON string
public class HomepageData implements Serializable {
	private List<RequestSection> requestSections;
	private boolean isBenCo;
	
	public HomepageData() {
		super();
	}
	
	public static String getHomepageData(int emp_id) throws JsonProcessingException {
		HomepageData hpd = new HomepageData();
		hpd.createRequestSections(emp_id);
		//check if this person is a benco
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(hpd);
	}
	
	
	//Creates all applicable sections for the employee
	private void createRequestSections(int emp_id) {
		createMyRequestsSection(emp_id);
		
		//TODO	create the other sections	
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
