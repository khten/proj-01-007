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

		// Begin a tx
		Transaction tx = ses.beginTransaction();

		// Capture returned PK from save()
		int pk = (int) ses.save(e);

		// Commit transaction
		tx.commit();

		// Return PK
		return pk;
	}

	public Employee findByUsername(String username) {
		return ses.get(Employee.class, username);
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
		ses.update(e);
		return true;
	}

	@Override
	public boolean delete(int id) {
		Employee e = findById(id);
		ses.delete(e);
		return true;
	}
}