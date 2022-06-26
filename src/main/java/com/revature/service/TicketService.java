package com.revature.service;

import java.util.List;

import com.revature.dao.TicketDao;
import com.revature.models.Ticket;

public class TicketService {

	private TicketDao tdao;

	// Ensure TicketService objects ALWAYS have a TicketDao via Dependency Injection
	public TicketService(TicketDao tdao) {
		this.tdao = tdao;
	}

	public List<Ticket> getAll() {
		return tdao.findAll();
	}

	public int requestNewTicket(Ticket t) {
		return tdao.insert(t);
	}

	public boolean updateTicket(Ticket t) {
		return tdao.update(t);
	}

	public boolean deleteTicket(int id) {
		return tdao.delete(id);
	}
}