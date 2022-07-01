package com.revature.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.TicketDao;
import com.revature.models.Employee;
import com.revature.models.Status;
import com.revature.models.Ticket;
import com.revature.service.TicketService;

public class TicketServiceTest {

    private TicketService tserv;
    private TicketDao mockdao;

    @Before
    public void setup() {
        mockdao = mock(TicketDao.class);
        tserv = new TicketService(mockdao);
    }

    @After
    public void teardown() {
        mockdao = null;
        tserv = null;
    }

    // ============ getAll() ============ //
    @Test
    public void testGetAll() {

        // Create fake tickets
        Ticket t1 = new Ticket(4, 754d, "Bought beans for lunchroom", new Employee(), Status.Approved, "snowman","");
        Ticket t2 = new Ticket(5, 345d, "Lost at casino", new Employee(), Status.Pending, "ernie", "");

        // Create list of tickets
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(t1);
        tickets.add(t2);

        // Mock findAll() to provide fake data
        when(mockdao.findAll()).thenReturn(tickets);

        // Capture the actual output of the method
        List<Ticket> actual = tserv.getAll();

        // Assert that they're equal
        assertEquals(actual, tickets);
    }

    // ============ requestNewTicket(Ticket t) ============ //
    @Test
    public void testRequestNewTicket() {

        // Create fake ticket
        Ticket t1 = new Ticket(4, 754d, "Bought beans for lunchroom", new Employee(), Status.Approved, "snowman", "");

        // Mock insert() to provide fake data
        when(mockdao.insert(t1)).thenReturn(4);

        // Capture the actual output of the method, set expected
        int actual = tserv.requestNewTicket(t1);
        int expected = t1.getTransactionId();

        // Assert that they're equal
        assertEquals(actual, expected);
    }

    // ============ updateTicket(Ticket t) ============ //
    @Test
    public void testUpdateTicket() {

        // Create fake ticket
        Ticket t1 = new Ticket(4, 754d, "Bought beans for lunchroom", new Employee(), Status.Approved, "snowman","");

        // Mock update() to provide fake data
        when(mockdao.update(t1)).thenReturn(true);

        // Capture the actual output of the method, set expected
        boolean actual = tserv.updateTicket(t1);
        boolean expected = true;

        // Assert that they're equal
        assertEquals(actual, expected);
    }

    // ============ getById(int id) ============ //
    @Test
    public void testGetById() {

        // Create fake Ticket
        Ticket t1 = new Ticket(4, 754d, "Bought beans for lunchroom", new Employee(), Status.Approved, "snowman", "");
        int id = 4;

        // Mock findById() to provide fake data
        when(mockdao.findById(id)).thenReturn(t1);

        // Capture the actual output of the method
        Ticket actual = tserv.getById(id);

        // Assert that they're equal
        assertEquals(actual, t1);
    }

    // ============ deleteTicket(int id) ============ //
    @Test
    public void testDeleteTicket() {

        // Set ID
        int id = 4;

        // Mock delete() to provide fake data
        when(mockdao.delete(id)).thenReturn(true);

        // Capture the actual output of the method, set expected
        boolean actual = tserv.deleteTicket(id);
        boolean expected = true;

        // Assert that they're equal
        assertEquals(actual, expected);
    }

    // ============ getTicketsByUsername(String username) ============ //
    @Test
    public void testGetTicketsByUsername() {

        // Create fake ticket
        Ticket t1 = new Ticket(4, 754d, "Bought beans for lunchroom", new Employee(), Status.Approved, "snowman", "");
        Ticket t2 = new Ticket(5, 345d, "Lost at casino", new Employee(), Status.Pending, "snowman", "");

        // Create list of tickets
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(t1);
        tickets.add(t2);

        String username = "snowman";

        // Mock getTicketsByUsername() to provide fake data
        when(mockdao.getTicketsByUsername(username)).thenReturn(tickets);

        // Capture the actual output of the method
        List<Ticket> actual = tserv.getTicketsByUsername(username);

        // Assert that they're equal
        assertEquals(actual, tickets);
    }
}
