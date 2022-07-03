package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDao edao;
	private static Logger logger = LogManager.getLogger(EmployeeService.class);

	// Ensure EmployeeService objects ALWAYS have a TicketDao via Dependency
	// Injection
	public EmployeeService(EmployeeDao edao) {
		this.edao = edao;
	}

	public Employee confirmLogin(String username, String password) {

		// Find employee with username
		Optional<Employee> possibleEmp = edao.findAll().stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		return (possibleEmp.isPresent() ? possibleEmp.get() : new Employee());
	}

	public int register(Employee e) {
		logger.info("Employee with ID " + e.getId() + " has been successfully created!");
		return edao.insert(e);
	}

	public List<Employee> getAll() {
		logger.info("Retrieved all employees from DB");
		return edao.findAll();
	}

	public boolean update(Employee e) {
		logger.info("Employee with ID " + e.getId() + " has been successfully updated!");
		return edao.update(e);
	}

	public boolean delete(Employee e) {
		logger.warn("Employee with ID " + e.getId() + " has been deleted!");
		return edao.delete(e.getId());
	}
}