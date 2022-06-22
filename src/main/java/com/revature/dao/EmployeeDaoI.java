package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDaoI {
	
	public int insert(Employee e);
	
	public List<Employee> findAll();
	
	public boolean delete(int id);
	
	public boolean update(Employee e);
}
