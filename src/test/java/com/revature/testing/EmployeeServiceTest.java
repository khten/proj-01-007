package com.revature.testing;

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
import com.revature.models.Role;
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
    public void teardown() {
        mockdao = null;
        eserv = null;
    }

    @Test
    public void testConfirmLogin_success() {

        // Create fake employees
        Employee e1 = new Employee(20, "John", "Snow", "snowman", "password", Role.Employee, null);
        Employee e2 = new Employee(21, "Clint", "Barton", "hawkeye", "arrows", Role.Admin, null);

        List<Employee> emps = new ArrayList<Employee>();
        emps.add(e1);
        emps.add(e2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(emps);

        // Capture the actual output of the method, set expected
        Employee actual = eserv.confirmLogin("snowman", "password");
        Employee expected = e1;

        // Assert that they're equal
        assertEquals(expected, actual);
    }

    @Test
    public void testConfirmLogin_fail() {

        // Create fake employees
        Employee e1 = new Employee(20, "John", "Snow", "snowman", "password", Role.Employee, null);
        Employee e2 = new Employee(21, "Clint", "Barton", "hawkeye", "arrows", Role.Admin, null);

        List<Employee> emps = new ArrayList<Employee>();
        emps.add(e1);
        emps.add(e2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(emps);

        // Capture the actual output of the method, set expected
        Employee actual = eserv.confirmLogin("Clint", "green");
        Employee expected = new Employee();

        // Assert that they're equal
        assertEquals(expected, actual);
    }
}
