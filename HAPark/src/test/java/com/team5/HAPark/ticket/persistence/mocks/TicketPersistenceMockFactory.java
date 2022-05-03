package com.team5.HAPark.ticket.persistence.mocks;

import com.team5.HAPark.ticket.persistence.ITicketPersistence;

public class TicketPersistenceMockFactory implements ITicketPersistenceMockFactory {

    @Override
    public ITicketPersistence getTicketPersistenceMock() {
        return new TicketPersistenceMock();
    }
}
