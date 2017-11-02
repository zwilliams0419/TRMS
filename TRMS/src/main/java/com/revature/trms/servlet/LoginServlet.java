package com.revature.trms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.trms.dao.DAOHolder;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uName = request.getParameter("username");
		String password = request.getParameter("password");

		Integer userEmpId = null;
		userEmpId = DAOHolder.employeeDAO.loginCheck(uName, password);
		if(userEmpId != null) {
			request.getSession().setAttribute("userID", userEmpId);
			response.sendRedirect("HomepageServlet"); //Sends a GET request to the homepage servlet
		}
		else {
			response.sendRedirect("Login.html?action=loginerror"); //Return to login page with a login error
		}
	}

}
