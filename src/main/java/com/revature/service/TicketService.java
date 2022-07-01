package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.TicketDao;
import com.revature.models.Status;
import com.revature.models.Ticket;

public class TicketService {

	private TicketDao tdao;
	private static Logger logger = LogManager.getLogger(EmployeeService.class);

	// Ensure TicketService objects ALWAYS have a TicketDao via Dependency Injection
	public TicketService(TicketDao tdao) {
		this.tdao = tdao;
	}

	public List<Ticket> getAll() {
		logger.info("Retried all tickets from DB");
		return tdao.findAll();
	}

	public int requestNewTicket(Ticket t) {
		logger.info("Ticket with ID " + t.getTransactionId() + " has been successfully created!");
		return tdao.insert(t);
	}

	public boolean updateTicket(Ticket t) {
		logger.info("Ticket with ID " + t.getTransactionId() + " has been successfully updated!");
		return tdao.update(t);
	}

	public Ticket getById(int id) {
		logger.warn("Ticket with ID " + id + " retrieved");
		return tdao.findById(id);
	}

	public boolean deleteTicket(int id) {
		logger.warn("Ticket with ID " + id + " has been deleted!");
		return tdao.delete(id);

	}

	public List<Ticket> getTicketsByUsername(String username) {
		logger.info("All tickets created by '" + username + "' retrieved");
		return tdao.getTicketsByUsername(username);
	}
	
	public List<Ticket> getTicketsByStatus(Status status) {
		return tdao.getTicketsByStatus(status);

	}

}