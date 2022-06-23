package com.revature.dao;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDaoI {

	//Create
	public int insert(Ticket t);
	public Ticket findById(int ticketId);
	public List<Ticket> findAll();
	public boolean update(Ticket t);
	public boolean delete(int ticketId);

	
}