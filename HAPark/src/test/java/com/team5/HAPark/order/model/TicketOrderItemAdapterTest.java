package com.team5.HAPark.order.model;

import com.team5.HAPark.ticket.model.Ticket;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderItemAdapterTest {

    private Ticket ticket1;
    private Ticket ticket2;
    private TicketOrderItem ticketOrderItem;
    private TicketOrderItemAdapter orderItem;

    @BeforeEach
    void setUp() {
        ticket1 = new Ticket("item",15.25);
        ticket2 = new Ticket("item",15);
        ticketOrderItem = new TicketOrderItem(ticket1,3);
        orderItem = new TicketOrderItemAdapter(ticketOrderItem);
    }

    @Test
    void getTotalPrice() {
        assertEquals(45.75,orderItem.getTotalPrice());
    }

    @Test
    void getId() {
        assertEquals("item",orderItem.getId());
    }

    @Test
    void getQuantity() {
        assertEquals(3,orderItem.getQuantity());
    }

    @Test
    void getPrice() {
        assertEquals(15.25,orderItem.getPrice());
    }

    @Test
    void getName() {
        assertEquals("item",orderItem.getName());
    }

}