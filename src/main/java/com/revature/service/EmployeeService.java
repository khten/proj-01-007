package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDao edao;

	// Ensure EmployeeService objects ALWAYS have a TicketDao via Dependency
	// Injection
	public EmployeeService(EmployeeDao edao) {
		this.edao = edao;
	}

	/**
	 * Our Servlet will pass the username and the password to this method invocation
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Employee confirmLogin(String username, String password) {

		// Stream through all returned employees
		Optional<Employee> possibleEmp = edao.findAll().stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();

		// Return employee if present, or return empty employee object
		return (possibleEmp.isPresent() ? possibleEmp.get() : new Employee());

		// TODO: Setup custom exception
	}

	// Inserts employee into DB, returns PK from DAO
	public int register(Employee e) {
		return edao.insert(e);
	}

	// Gets list of all employees in the DB
	public List<Employee> getAll() {
		return edao.findAll();
	}
}