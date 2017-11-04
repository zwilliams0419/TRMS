package com.revature.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.trms.LoggingService;
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
