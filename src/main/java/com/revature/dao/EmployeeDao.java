package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao implements EmployeeDaoI {

	// Grab server object
	private Session ses = HibernateUtil.getSession();

	@Override
	public int insert(Employee e) {

		// Insert Employee object into DB
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(e);
		tx.commit();

		return pk;
	}

	public Employee findByUsername(String username) {

		// Find user from username by querying list
		List<Employee> users = ses.createQuery("from Employee", Employee.class).list();
		Employee e = users.get(0);
		return e;
	}

	@Override
	public List<Employee> findAll() {
		return ses.createQuery("from Employee", Employee.class).list();
	}

	public Employee findById(int id) {
		return (Employee) ses.get(Employee.class, id);
	}

	@Override
	public boolean update(Employee e) {
		Transaction tx = ses.beginTransaction();
		ses.update(e);
		tx.commit();
		return true;
	}

	@Override
	public boolean delete(int id) {
		Transaction tx = ses.beginTransaction();
		Employee e = findById(id);
		ses.delete(e);
		tx.commit();
		return true;
	}
}