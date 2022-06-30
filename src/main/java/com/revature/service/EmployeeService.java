package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.NoUserFoundException;
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
		Employee possibleEmp = edao.findByUsername(username);

		// Check for valid credentials
		if (possibleEmp.getId() != 0 && possibleEmp.getPassword().equals(password)) {
			return possibleEmp;
		} else if (possibleEmp.getPassword() == null) {
			try {
				throw new NoUserFoundException("User with username " + username + " does not exist!");
			} catch (NoUserFoundException e) {
				e.printStackTrace();
			}
		} else if (possibleEmp.getPassword() != null && possibleEmp.getPassword() != password) {
			try {
				throw new IncorrectPasswordException("Incorrect password!");
			} catch (IncorrectPasswordException e) {
				e.printStackTrace();
			}
		}

		return new Employee();
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