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
	public List<Ticket> findByEmpId(int id) {
		return ses.createQuery("from Ticket where employee_id = " + id, Ticket.class).list();
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