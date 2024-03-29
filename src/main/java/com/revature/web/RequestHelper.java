package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.dao.EmployeeDao;
import com.revature.dao.TicketDao;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Ticket;
import com.revature.service.EmployeeService;
import com.revature.service.TicketService;

public class RequestHelper {
	public static Employee employee;

	protected static TicketService tserv = new TicketService(new TicketDao());
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();

	public static void processAdmin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getParameter("username") != null) {
			String username = request.getParameter("username");

			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");

			List<Ticket> allTickets = tserv.getTicketsByUsername(username);

			String jsonString = om.writeValueAsString(allTickets);

			PrintWriter out = response.getWriter();
			// out.println("<p>Reached</p>");
			out.write(jsonString);

			// processViewAllEmployees(request, response);
		} else if (request.getParameter("approve") != null) {
			processApproveTicket(request, response);
		} else if (request.getParameter("deny") != null) {
			processDenyTicket(request, response);
		}
	}

	/**
	 * Employee landing page for after login
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getParameter("submitTicket") != null) {
			// do some stuff
			String description = request.getParameter("desc");
			Employee e = (Employee) request.getSession().getAttribute("the-user");

			double amount = Double.valueOf(request.getParameter("amount"));

			String username = e.getUsername();

			Ticket t = new Ticket(amount, description, e, Status.Pending, username, "");
			tserv.requestNewTicket(t);
			e.getTicketList().add(t);

			eserv.update(e);

			PrintWriter out = response.getWriter();
			out.write("Processed submit ticket...but does it persist???");

		} else if (request.getParameter("password") != null) {
			Employee e = (Employee) request.getSession().getAttribute("the-user");
			String passWord = request.getParameter("password");
			e.setPassword(passWord);

			PrintWriter out = response.getWriter();
			out.write("Processed password change...persist???");
			eserv.update(e);
		}
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
			try {
				employee = e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			PrintWriter out = response.getWriter();
			// out.println("before role" + e.getRole());

			if (e.getRole() == Role.Admin) {

				request.getRequestDispatcher("admin.html").forward(request, response);
				out.println("<h3>You have successfully logged in as Admin!</h3>");

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
		}
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Employee e = new Employee(firstname, lastname, username, password, Role.Employee, new ArrayList<Ticket>());

		int pk = eserv.register(e);

		HttpSession session = request.getSession();
		session.setAttribute("the-user", e);

		// 4. check it's ID...if it's > 0 it's successfull
		if (pk > 0) {

			e.setId(pk);
			request.getRequestDispatcher("/index.html").forward(request, response);
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

	public static void processViewAllEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Employee> emps = eserv.getAll();

		String jsonString = om.writeValueAsString(emps);

		PrintWriter out = response.getWriter();
		out.write(jsonString);
	}

	public static void showAllTickets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Ticket> allTickets = tserv.getAll();

		String jsonString = om.writeValueAsString(allTickets);

		PrintWriter out = response.getWriter();
		out.write(jsonString);
	}

	public static void processApproveTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		new JsonObject();

		InputStreamReader p = new InputStreamReader(request.getInputStream());

		JsonElement root = JsonParser.parseReader(p);

		JsonObject rootobj = root.getAsJsonObject();

		int id = rootobj.get("ticketId").getAsInt();

		Ticket t = tserv.getById(id);
		String e = employee.getUsername();

		if (t != null) {
			t.setResolvedBy(e);
			t.setStatus(Status.Approved);
			tserv.updateTicket(t);

			String jsonString = om.writeValueAsString(t);

			out.write(jsonString);
		} else {
			out.write("error id does not exist");
		}
	}

	public static void processDenyTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		new JsonObject();

		InputStreamReader p = new InputStreamReader(request.getInputStream());

		JsonElement root = JsonParser.parseReader(p);

		JsonObject rootobj = root.getAsJsonObject();

		int id = rootobj.get("ticketId").getAsInt();

		Ticket t = tserv.getById(id);

		if (t != null) {
			t.setResolvedBy(employee.getUsername());
			t.setStatus(Status.Denied);
			tserv.updateTicket(t);

			String jsonString = om.writeValueAsString(t);

			out.write(jsonString);
		} else {
			out.write("error id does not exist");
		}

	}

	public static void processTicketsByUsername(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		// required to get the json object from the response
		new JsonObject();

		InputStreamReader p = new InputStreamReader(request.getInputStream());

		JsonElement root = JsonParser.parseReader(p);

		JsonObject rootobj = root.getAsJsonObject();

		String u = rootobj.get("username").getAsString();

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Ticket> allTickets = tserv.getAll().stream().filter(t -> t.getRequestedBy().equals(u))
				.collect(Collectors.toList());

		// // transform the list to a string
		String jsonString = om.writeValueAsString(allTickets);

		// // 4. write it out

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

	public static void processTicketsByStatus(HttpServletRequest request, HttpServletResponse response, Status status)
			throws IOException {

		// set the content type and headers
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Ticket> ticketsByUsername = tserv.getAll().stream().filter(t -> t.getStatus().equals(status))
				.collect(Collectors.toList());

		String jsonString = om.writeValueAsString(ticketsByUsername);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body
	}

	public static void processStatus(HttpServletRequest request, HttpServletResponse response, Status s)
			throws IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// Employee user = (Employee) session.getAttribute("the-user");
		String username = employee.getUsername();

		System.out.println(username);
		// TEST
		List<Ticket> ticketList = tserv.getAll().stream()
				.filter(t -> (t.getRequestedBy()).equals(username) && t.getStatus() == Status.Pending)

				.collect(Collectors.toList());

		String jsonString = om.writeValueAsString(ticketList);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

	public static void processStatusResolved(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// Employee user = (Employee) session.getAttribute("the-user");
		String username = employee.getUsername();
		//
		// TEST
		List<Ticket> allTickets = tserv.getAll().stream().filter(t -> t.getRequestedBy().equals(username))
				.filter(t -> !t.getStatus().equals(Status.Pending)).collect(Collectors.toList());

		String jsonString = om.writeValueAsString(allTickets);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

	public static void processAdminPending(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// TEST
		List<Ticket> allTickets = tserv.getAll().stream()
				.filter(t -> t.getStatus().equals(Status.Pending)).collect(Collectors.toList());

		String jsonString = om.writeValueAsString(allTickets);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

	public static void processAdminResolved(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// System.out.println("Status: " + s );

		// TEST
		List<Ticket> allTickets = tserv.getAll().stream()
				.filter(t -> !t.getStatus().equals(Status.Pending)).collect(Collectors.toList());

		String jsonString = om.writeValueAsString(allTickets);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

	public static void processChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// //required to get the json object from the response
		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		@SuppressWarnings("unused")
		JsonObject payload = new JsonObject();

		InputStreamReader p = new InputStreamReader((InputStream) request.getInputStream());

		JsonElement root = JsonParser.parseReader(p);

		JsonObject rootobj = root.getAsJsonObject();

		String pwd = rootobj.get("password").getAsString();

		System.out.println("retrieved new password from JSON:" + pwd);

		Employee e = employee;

		e.setPassword(pwd);
		eserv.update(e);

	}

	public static void processMakeNewTicket(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		// required to get the json object from the response
		Gson gson = new Gson();
		gson = new GsonBuilder().create();

		@SuppressWarnings("unused")
		JsonObject payload = new JsonObject();

		InputStreamReader p = new InputStreamReader((InputStream) request.getInputStream());

		JsonElement root = JsonParser.parseReader(p);

		JsonObject rootobj = root.getAsJsonObject();

		double amount = rootobj.get("amount").getAsDouble();
		String description = rootobj.get("description").getAsString();

		Status status = Status.Pending;

		final String requestedBy = employee.getUsername();
		Employee e = employee;
		Ticket t = new Ticket(amount, description, e, status, requestedBy, "");

		tserv.requestNewTicket(t);

	}

	public static void viewEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Employee> list = new LinkedList<Employee>();
		list.add(employee);
		String jsonString = om.writeValueAsString(list);

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the string to the response body

	}

}