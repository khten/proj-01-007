package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * This method will be responsible for determining what resource the client is
	 * requesting
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. URI rewriting
		// http://localhost:8080/employee-servlet-app/login -- we want to capture login
		// http://localhost:8080/employee-servlet-app/employees -- if they go here it
		// returns all employees in the DB
		final String URI = request.getRequestURI().replace("/proj-01-team07/", "");
		// we're capturing the very last part of the URI

		// set up a switch case statement in which we call the appropriate functionality
		// based on the URI returned
		switch (URI) {

			case "login":
				RequestHelper.processLogin(request, response);
				break;

			case "register":
				RequestHelper.processRegistration(request, response);
				break;

			case "employees":
				RequestHelper.processViewAllEmployees(request, response);
//TODO revisit for employee functionality
				break;
			
			case "admin":
				/*if (request.getParameter("view-all") != null) {
					RequestHelper.showAllTickets(request, response);

				}
				else */
				if (request.getParameter("approve") != null) {
					RequestHelper.processApproveTicket(request, response);
				} else if (request.getParameter("denied") != null) {
					RequestHelper.processDenyTicket(request, response);
				}
				break;
				
			case "tickets":
				RequestHelper.showAllTickets(request, response);
				break;
				
			default:
				// TODO: Add custom error page
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}