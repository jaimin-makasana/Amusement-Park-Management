package com.team5.HAPark.ticket.model;

import com.team5.HAPark.ticket.persistence.ITicketPersistence;

import java.sql.SQLException;
import java.util.List;


public class TicketService implements ITicketService {

    private final ITicketPersistence ticketPersistence;

    public TicketService(ITicketPersistence ticketPersistence) {
        this.ticketPersistence = ticketPersistence;
    }

    @Override
    public Ticket getTicket(String ticketType) throws SQLException {
        Ticket ticket = ticketPersistence.loadTicket(ticketType);

        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> Tickets =ticketPersistence.getAllTickets();
        return Tickets;
    }
}





