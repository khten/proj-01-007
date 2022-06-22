package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {

	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before
	public void setup() {
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);
	}
	@After
	public void tearDown() {
		mockdao = null;
		eserv = null;
	}
	
	@Test
	public  void testConfirmLogin_success() {
		//1. create a fake list of emps (dummy data fed to mockito)
		
		Employee e1= new Employee(20, "Bruce", "Banner", "theHulk", "green");

		Employee e2= new Employee(20, "Bruce", "Wayne", "theBat", "black");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		//2. set up dao behavior (findAll() behavior to provide the fake data
		when(mockdao.findAll()).thenReturn(emps);
		//3. capture the actual output of method
		Employee actual = eserv.confirmLogin("theHulk", "green");
		
		//   capture the expected output of the method
		Employee expected = e1;
		//assert that they are equal
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConfirmLogin_fail() {

		Employee e1= new Employee(20, "Bruce", "Banner", "theHulk", "green");

		Employee e2= new Employee(20, "Bruce", "Wayne", "theBat", "black");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		
		//2. set up dao behavior (findAll() behavior to provide the fake data
		when(mockdao.findAll()).thenReturn(emps);
		//3. capture the actual output of method
		Employee actual = eserv.confirmLogin("theHulk", "blue");
		
		//   capture the expected output of the method
		Employee expected = new Employee();
		//assert that they are equal
		assertEquals(expected, actual);
	}
}
