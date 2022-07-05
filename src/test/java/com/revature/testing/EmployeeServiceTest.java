package com.revature.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

    // ============ confirmLogin(String username, String password) ============ //
    @Test
    public void testConfirmLogin_success() {

        // Create fake employee
        Employee e1 = new Employee(20, "John", "Snow", "snowman", "password", Role.Employee, null);
        Employee e2 = new Employee(24, "Bingo", "Bango", "bongo", "password", Role.Employee, null);

        // Create list of employees
        List<Employee> users = new ArrayList<Employee>();
        users.add(e1);
        users.add(e2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(users);

        // Capture the actual output of the method, set expected
        Employee actual = eserv.confirmLogin("snowman", "password");
        Employee expected = e1;

        // Assert that they're equal
        assertEquals(expected, actual);
    }

    @Test
    public void testConfirmLogin_fail() {

        // Create fake employee
        Employee e1 = new Employee(20, "John", "Snow", "snowman", "password", Role.Employee, null);
        Employee e2 = new Employee(24, "Bingo", "Bango", "bongo", "password", Role.Employee, null);

        // Create list of employees
        List<Employee> users = new ArrayList<Employee>();
        users.add(e1);
        users.add(e2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(users);

        // Capture the actual output of the method, set expected
        Employee actual = eserv.confirmLogin("dingo", "password");
        Employee expected = new Employee();

        // Assert that they're equal
        assertEquals(expected, actual);
    }

    // ============ register(Employee e) ============ //
    @Test
    public void testRegister_success() {

        // Create fake employees
        Employee e1 = new Employee(20, "John", "Snow", "snowman", "password", Role.Employee, null);

        // Mock insert() to provide fake data
        when(mockdao.insert(e1)).thenReturn(20);

        // Capture the actual output of the method, set expected
        int actual = eserv.register(e1);
        int expected = 20;

        // Assert that they're equal
        assertEquals(expected, actual);
    }

    @Test
    public void testRegister_fail() {

        // Create fake employees
        Employee e1 = new Employee(23, "John", "Snow", "snowman", "password", Role.Employee, null);

        // Mock insert() to provide fake data
        when(mockdao.insert(e1)).thenReturn(23);

        // Capture the actual output of the method, set expected
        int actual = eserv.register(e1);
        int expected = 20;

        // Assert that they're not equal
        assertNotEquals(expected, actual);
    }

    // ============ getAll() ============ //
    @Test
    public void testGetAll() {

        // Create fake employees
        Employee e1 = new Employee(23, "John", "Snow", "snowman", "password", Role.Employee, null);
        Employee e2 = new Employee(24, "Bingo", "Bango", "bongo", "password", Role.Employee, null);

        // Create list of employees
        List<Employee> users = new ArrayList<Employee>();
        users.add(e1);
        users.add(e2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(users);

        // Capture the actual output of the method
        List<Employee> actual = eserv.getAll();

        // Assert that they're equal
        assertEquals(actual, users);
    }

    // ============ update(Employee e) ============ //
    @Test
    public void testUpdate() {

        // Create fake employee
        Employee e1 = new Employee(23, "John", "Snow", "snowman", "password", Role.Employee, null);

        // Mock update() to provide fake data
        when(mockdao.update(e1)).thenReturn(true);

        // Capture the actual output of the method, set expected
        boolean actual = eserv.update(e1);
        boolean expected = true;

        // Assert that they're equal
        assertEquals(expected, actual);
    }

    // ============ delete(Employee e) ============ //
    @Test
    public void testDelete() {

        // Create fake employee
        Employee e1 = new Employee(23, "John", "Snow", "snowman", "password", Role.Employee, null);

        // Mock delete() to provide fake data
        when(mockdao.delete(e1.getId())).thenReturn(true);

        // Capture the actual output of the method, set expected
        boolean actual = eserv.delete(e1);
        boolean expected = true;

        // Assert that they're equal
        assertEquals(expected, actual);
    }
}
