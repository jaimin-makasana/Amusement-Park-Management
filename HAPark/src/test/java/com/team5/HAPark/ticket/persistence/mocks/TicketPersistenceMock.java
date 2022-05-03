package com.team5.HAPark.ticket.persistence.mocks;

import com.team5.HAPark.ticket.persistence.ITicketPersistence;
import com.team5.HAPark.ticket.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketPersistenceMock implements ITicketPersistence {

    private HashMap<String,Ticket> tickets;

    public TicketPersistenceMock() {
        tickets = new HashMap<>();
        Ticket ticket1 = new Ticket("Child",10);
        Ticket ticket2 = new Ticket("Adult",15);
        tickets.put("Child",ticket1);
        tickets.put("Adult",ticket2);
    }

    @Override
    public Ticket loadTicket(String type) {
        return tickets.get(type);
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        for (Map.Entry<String,Ticket> ticket : tickets.entrySet()) {
            ticketList.add(ticket.getValue());
        }
        return ticketList;
    }
}
