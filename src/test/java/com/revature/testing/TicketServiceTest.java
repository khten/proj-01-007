package com.revature.testing;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

import com.revature.dao.TicketDao;
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
}
