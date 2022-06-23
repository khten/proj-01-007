package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

// servlet -> calls service --> calls dao
public class EmployeeDao implements EmployeeDaoI {
	private Session ses = HibernateUtil.getSession();
	
	// CRUD methods
	
	// Create (think that in the service layer we'll have a REGISTER()
	@Override
	public int insert(Employee e) {
		
		// grab the session object
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// capture the pk returned when the session method save() is called
		int pk = (int) ses.save(e);
		

		tx.commit();  //MUST commit

		
		// return the pk
		return pk;
	}
	
	public Employee findByUsername(String username) {
		
		Employee e = ses.get(Employee.class, username);
		
		return e;
	}
	// Read
	@Override
	public List<Employee> findAll() {
		
		
		
		// make an HQL -- Hibernate Query Language: odd mix of OOP & native SQL
		 List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		 // return the list of employees
		return emps;
	}
	
	
	public Employee findById(int id) {
	     Employee e = (Employee) ses.get(Employee.class, id);
	     return e;
	}
	@Override
	public boolean delete(int id) {
		Employee e = findById(id); 
		ses.delete(e);
		return true;
	}
	
	@Override
	public boolean update(Employee e) {
		ses.update(e);
		return true;
	}
}
