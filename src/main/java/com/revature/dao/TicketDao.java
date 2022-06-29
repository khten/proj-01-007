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
		return (Ticket) ses.get(Ticket.class, ticketId);
	}

	@Override
	public List<Ticket> findAll() {
		return ses.createQuery("from Ticket", Ticket.class).list();
	}

	@Override
	public List<Ticket> getTicketsByUsername(String username) {
		String user = "from Ticket t where t.requestedBy = '" + username + "'";
		System.out.println(username);
		return ses.createQuery(user, Ticket.class).list();
		// return ses.createQuery("from Ticket t where t.requested_by = " + username,
		// Ticket.class).list();
	}

	@Override
	public boolean update(Ticket t) {
		Transaction tx = ses.beginTransaction();
		ses.update(t);
		tx.commit();

		return true;
	}

	@Override
	public boolean delete(int id) {
		Ticket t = findById(id);
		Transaction tx = ses.beginTransaction();
		ses.delete(t);
		tx.commit();

		return true;
	}
}