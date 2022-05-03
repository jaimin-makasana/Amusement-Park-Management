package com.team5.HAPark.ticket.model;

public class Ticket {

    private String ticketType;
    private double ticketPrice;

    public Ticket() {
    }

    public Ticket( String ticketType,double ticketPrice){
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
