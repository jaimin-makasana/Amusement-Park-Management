package com.team5.HAPark.ticket.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class TicketPersistenceFactory implements ITicketPersistenceFactory {

    private static ITicketPersistence ticketPersistence;
    @Override
    public ITicketPersistence createTicketPersistence() {
        if (ticketPersistence == null) {
            ticketPersistence = new MySQLTicketPersistence(MySQLDatabase.getInstance());
        }
        return ticketPersistence;
    }
}
