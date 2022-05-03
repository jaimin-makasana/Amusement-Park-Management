package com.team5.HAPark.ticket.model;

import com.team5.HAPark.ticket.persistence.ITicketPersistence;
import com.team5.HAPark.ticket.persistence.mocks.TicketPersistenceMockFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class TicketServiceTest {

    private static TicketService ticketService;

    @BeforeAll
     static void setUp() {
        TicketPersistenceMockFactory factory = new TicketPersistenceMockFactory();
        ITicketPersistence ticketPersistenceMock = factory.getTicketPersistenceMock();
        ticketService = new TicketService(ticketPersistenceMock);
    }

    @Test
    void validateChildTicketIsFetched() throws SQLException {
        assertNotNull(ticketService.getTicket("Child"));
    }

    @Test
    void validateChildTicketIsCorrect() throws SQLException {
        Ticket ticket = ticketService.getTicket("Child");
        assertEquals("Child",ticket.getTicketType());
    }

    @Test
    void validateAdultTicketIsFetched() throws SQLException {
        assertNotNull(ticketService.getTicket("Adult"));
    }

    @Test
    void validateAdultTicketIsCorrect() throws SQLException {
        Ticket ticket = ticketService.getTicket("Adult");
        assertEquals("Adult",ticket.getTicketType());
    }

    @Test
    void validateTicketsAreFetchedNotNull() throws  SQLException {
        assertNotNull(ticketService.getAllTickets());
    }

    @Test
    void validateAllTicketAreFetchedCorrectly() throws  SQLException {
        assertEquals(2,ticketService.getAllTickets().size());
    }
}