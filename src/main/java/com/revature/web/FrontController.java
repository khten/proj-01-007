package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Status;

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
				RequestHelper.processEmployees(request, response);
				break;

			case "admin":
				RequestHelper.processAdmin(request, response);
				break;

			case "viewemps":
				RequestHelper.processViewAllEmployees(request, response);

				break;

			case "approve_ticket":

				RequestHelper.processApproveTicket(request, response);
				break;

			case "deny_ticket":
				RequestHelper.processDenyTicket(request, response);
				break;

			case "pending_tickets":
				RequestHelper.processTicketsByStatus(request, response, Status.Pending);
				break;

			case "tickets_by_username":
				RequestHelper.processTicketsByUsername(request, response);

				break;

			case "view_pending":
				RequestHelper.processTicketsByStatus(request, response, Status.Pending);
				break;

			case "view_approved":
				RequestHelper.processTicketsByStatus(request, response, Status.Approved);
				break;

			case "view_denied_tickets":
				RequestHelper.processTicketsByStatus(request, response, Status.Denied);
				break;

			case "approved_tickets":
				RequestHelper.processTicketsByStatus(request, response, Status.Approved);
				break;

			case "denied_tickets":
				RequestHelper.processTicketsByStatus(request, response, Status.Denied);
				break;

			case "resolved_tickets":
				RequestHelper.processStatusResolved(request, response);
				break;

			case "all_pending_tickets":
				RequestHelper.processAdminPending(request, response);
				break;

			case "all_resolved_tickets":
				RequestHelper.processAdminResolved(request, response);
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