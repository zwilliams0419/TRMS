package com.revature.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.trms.LoggingService;
import com.revature.trms.ReimbursementRequest;
import com.revature.trms.dao.DAOHolder;
import com.revature.trms.page.RequestFormData;

/**
 * Servlet implementation class SaveReimbursementRequestServlet
 */
public class ReimbursementFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String rfdJSON = null;
		
		if(id != null) {
			rfdJSON = RequestFormData.getExistingRequestFormData(Integer.parseInt(id));
		}
		else {
			int userId = (Integer) request.getSession().getAttribute("userId");
			rfdJSON = RequestFormData.getNewRequestFormData(userId);
		}
		
		LoggingService.getInstance().getLogger().info(rfdJSON);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(rfdJSON);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementRequest r = fillNewReimbursementRequest(request);
		
		DAOHolder.reimbursementRequestDAO.createRequest(r);
		DAOHolder.employeeDAO.updateEmployeeEmail(Integer.parseInt(request.getParameter("requesterId")), request.getParameter("email"));
		
		response.sendRedirect("Home.html");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private ReimbursementRequest fillNewReimbursementRequest(HttpServletRequest request) {
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
		
		ReimbursementRequest r = new ReimbursementRequest();
		r.setEmployeeId(Integer.parseInt(request.getParameter("requesterId")));
		r.setGradeFormat(Integer.parseInt(request.getParameter("gradeFormat")));
		r.setEventType(Integer.parseInt(request.getParameter("eventType")));
		r.setAddress(request.getParameter("address"));
		r.setState(request.getParameter("state"));
		r.setCity(request.getParameter("city"));
		r.setZip(Integer.parseInt(request.getParameter("zip")));
		r.setDescription(request.getParameter("desc"));
		r.setCost(Float.parseFloat(request.getParameter("cost")));
		r.setJustification(request.getParameter("workJustification"));
		r.setApproval(0);
		//TODO allow this to be approved via file upload at creation time
		r.setCreationDate(new Date());
		r.setStatusChangeDate(new Date());
		r.setPassingGrade(Integer.parseInt(request.getParameter("passingGrade")));
		
		//Set the reimbursement amount at creation based on the cost and the event type. A benco can change it later
		r.setReimbursementAmount(r.getCost() * DAOHolder.eventTypeDAO.getEventType(r.getEventType()).getRate());
		
		try {
			r.setEventDate(sdfr.parse( request.getParameter("date")));
		} catch (ParseException e) {
			LoggingService.getInstance().getLogger().warn("Invalid event date format");
			return null;
		}
		
		return r;
	}
}
