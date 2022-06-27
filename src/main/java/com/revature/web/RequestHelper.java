package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.TicketDao;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

public class RequestHelper {

	protected static TicketService tserv = new TicketService(new TicketDao());

	// employeeservice
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	// object mapper (for frontend)
	private static ObjectMapper om = new ObjectMapper();

	public static void processEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// if (request.getParameter("submitTicket") != null) {
		// // do some stuff
		// String description = request.getParameter("desc");
		// Employee e = (Employee) request.getSession().getAttribute("the-user");

		// Ticket t = new Ticket(description, e, Status.Pending);
		// tserv.requestNewTicket(t);
		// e.getTicketList().add(t);

		// eserv.update(e);

		// PrintWriter out = response.getWriter();
		// out.write("Processed submit ticket...but does it persist???");

		// } else if (request.getParameter("password") != null) {
		// Employee e = (Employee) request.getSession().getAttribute("the-user");
		// String passWord = request.getParameter("password");
		// e.setPassword(passWord);

		// PrintWriter out = response.getWriter();
		// out.write("Processed password change...persist???");
		// eserv.update(e);
		// }
		// 1. set the content type .... application/json
		// TODO This is to process view all employees...seems to be issue with the
		// jsonString;
		// response.setContentType("text/html");
		response.setContentType("application/json");
		// 2. Call the getAll() method form the employee service
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Employee> emps = eserv.getAll();
		// 3. transform the list to a string
		String jsonString = om.writeValueAsString(emps);
		// 4. write it out
		// get printwriter
		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body
	}

	/**
	 * What does this method do?
	 * 
	 * It extracts the parameters from a request (username and password) from the UI
	 * It will call the confirmLogin() method from the EmployeeService and
	 * see if a user with that username and password exists
	 * 
	 * Who will provide the method with the HttpRequest? The UI
	 * We need to build an html doc with a form that will send these prameters to
	 * the method
	 */
	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 1. Extract the parameters from the request (username & password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// grab the session
		HttpSession session = request.getSession();

		// 2. call the confirm login(0 method from the employeeService and see what it
		// returns
		Employee e = eserv.confirmLogin(username, password);

		// 3. If the user exists, lets print their info to the screen
		if (e.getId() > 0) {

			session.setAttribute("the-user", e);

			PrintWriter out = response.getWriter();
			out.println("before role" + e.getRole());

			if (e.getRole() == Role.Admin) {

				request.getRequestDispatcher("admin.html").forward(request, response);
				out.println("<h3>You have successfully logged in Admin!</h3>");

			} else if (e.getRole() == Role.Employee) {

				request.getRequestDispatcher("employees.html").forward(request, response);
				out.println("<h3>You have successfully logged in as Employee!</h3>");
			}

			// print out the user's data with the print writer

			response.setContentType("text/html");
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");

			// you COULD print the object out as a JSON string

		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");

			// Shout out to Gavin for figuring this out -- 204 doesn't return a response
			// body
			// response.setStatus(204); // 204 meants successful connection to the server,
			// but no content found
		}
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. extract all values from the parameters
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 2. construct a new employee object
		Employee e = new Employee(firstname, lastname, username, password, Role.Employee, new ArrayList<Ticket>());

		// 3. call the register() method from the service layer
		int pk = eserv.register(e);

		// 4. check it's ID...if it's > 0 it's successfull
		if (pk > 0) {

			e.setId(pk);
			// add the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);

			request.getRequestDispatcher("employee.html").forward(request, response);
			// using the request dispatcher, forward the request and response to a new
			// resource...
			// send the user to a new page -- welcome.html

		} else {
			// if it's -1, that means the register method failed (and there's probably a
			// duplicate user)
			// use the PrintWriter to print out

			// TODO: provide better logic in the Service layer to check for PSQL exceptions

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println("<h1>Registration failed.  Username already exists</h1>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}

	public static void showAllTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Ticket> allTickets = tserv.getAll();

		String jsonString = om.writeValueAsString(allTickets);

		PrintWriter out = response.getWriter();
		// out.println("<p>Reached</p>");
		out.write(jsonString);
	}

	public static void processApproveOrDenyTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("approve") != null) {
			processApproveTicket(request, response);
		} else if (request.getParameter("deny") != null) {
			processDenyTicket(request, response);
		} else {
			// TODO nothing error chekc
		}

	}

	public static void processApproveTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("acct-id"));
		PrintWriter out = response.getWriter();

		Ticket t = tserv.getById(id);
		if (t != null) {
			out.write(t + "\n");
			t.setStatus(Status.Approved);
			tserv.updateTicket(t);
			out.write("Approved Ticket " + id + "\n");
			out.write(t.toString());

		} else {
			out.write("Ticket is null");
		}
	}

	public static void processDenyTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("acct-id"));
		Ticket t = tserv.getById(id);
		if (t != null) {
			t.setStatus(Status.Denied);

			tserv.updateTicket(t);
		}
		PrintWriter out = response.getWriter();
		out.write("Denied Ticket " + id);
	}
}