package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.TicketDao;
import com.revature.models.Ticket;

public class TicketService {
	
	private TicketDao tdao;
	
	/**
	 * Dependency Injection via Constructor Injection
	 * 
	 * Constructor Injection is a sophisticated way of ensuring 
	 * that the TicketService object ALWAYS has an TicketDao object
	 * 
	 */
	public TicketService(TicketDao tdao) {
		
		this.tdao = tdao;
		
	}
	
	public List<Ticket> getAll() {
		
		return tdao.findAll();
		
	}

    public int requestNewTicket(Ticket t){
        return tdao.insert(t);

    }

    public boolean updateTicket(Ticket t){
        return tdao.update(t);

    }

    public boolean deleteTicket(int id){
        return tdao.delete(id);

    }

	

}