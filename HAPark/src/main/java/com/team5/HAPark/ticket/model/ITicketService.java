package com.team5.HAPark.ticket.model;

import java.sql.SQLException;
import java.util.List;

public interface ITicketService {
    Ticket getTicket(String ticketType) throws SQLException;

    List<Ticket> getAllTickets() throws SQLException;
}
