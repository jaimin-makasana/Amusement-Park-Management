package com.team5.HAPark.ticket.model;

public class TicketOrderItem implements ITicketOrderItem {
    private Ticket ticket;
    private Integer quantity;

    public TicketOrderItem() {
        this.ticket = new Ticket();
    }

    public TicketOrderItem(Ticket ticket, Integer quantity) {
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTicketType() {
        String ticketType = ticket.getTicketType();
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticket.setTicketType(ticketType);
    }

    public double getTicketPrice() {
        double ticketPrice = ticket.getTicketPrice();
        return ticketPrice;
    }

    public double getTotalPrice() {
        return getTicketPrice() * getQuantity();
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticket.setTicketPrice(ticketPrice);
    }
}
