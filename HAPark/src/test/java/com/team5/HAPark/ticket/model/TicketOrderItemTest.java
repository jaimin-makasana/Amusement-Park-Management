package com.team5.HAPark.ticket.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketOrderItemTest {

    private TicketOrderItem ticketOrderItem;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        Ticket ticket = new Ticket("item",15);
        ticketOrderItem = new TicketOrderItem(ticket,3);
        ticket2 = new Ticket("item",15.25);
    }

    @Test
    void getTicketType() {
        assertEquals("item",ticketOrderItem.getTicketType());
    }

    @Test
    void setTicketType() {
        ticketOrderItem.setTicketType("new type");
        assertEquals("new type",ticketOrderItem.getTicketType());
    }

    @Test
    void getTicketPrice() {
        assertEquals(15,ticketOrderItem.getTicketPrice());
    }

    @Test
    void setTicketPrice() {
        ticketOrderItem.setTicketPrice(5);
        assertEquals(5,ticketOrderItem.getTicketPrice());
    }

    @Test
    void getTotalPriceWithIntegerPrice() {
        assertEquals(45,ticketOrderItem.getTotalPrice());
    }

    @Test
    void getTotalPriceWithDecimalPrice() {
        ticketOrderItem = new TicketOrderItem(ticket2,3);
        assertEquals(45.75,ticketOrderItem.getTotalPrice());
    }

}