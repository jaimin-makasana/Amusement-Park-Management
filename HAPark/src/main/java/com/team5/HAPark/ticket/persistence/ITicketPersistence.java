package com.team5.HAPark.ticket.persistence;

import com.team5.HAPark.ticket.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketPersistence {
    Ticket loadTicket(String type) throws SQLException;

    List<Ticket> getAllTickets()  throws SQLException;
}
