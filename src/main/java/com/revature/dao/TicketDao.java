package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Ticket;
import com.revature.util.HibernateUtil;

public class TicketDao implements TicketDaoI {

	private Session ses = HibernateUtil.getSession();

	@Override
	public int insert(Ticket t) {

		Transaction tx = ses.beginTransaction();
		int pk = (int) (ses.save(t));
		tx.commit();

		return pk;
	}

	@Override
	public Ticket findById(int ticketId) {
<<<<<<< HEAD
		Ticket t = (Ticket) ses.get(Ticket.class, ticketId);
		return t;

=======
			return (Ticket) ses.get(Ticket.class, ticketId);
			
		
>>>>>>> 03050b8c72ce3f98121c1eb30cf8c39da7a4555a
	}

	@Override
	public List<Ticket> findAll() {
<<<<<<< HEAD
		List<Ticket> allTickets = ses.createQuery("from Ticket", Ticket.class).list();
		return allTickets;
=======
		return ses.createQuery("from Ticket", Ticket.class).list(); 
		
>>>>>>> 03050b8c72ce3f98121c1eb30cf8c39da7a4555a
	}

	@Override
	public boolean update(Ticket t) {
		ses.update(t);
		return true;
	}

	@Override
	public boolean delete(int id) {
		Ticket t = findById(id);
		ses.delete(t);

		return true;
	}

}
