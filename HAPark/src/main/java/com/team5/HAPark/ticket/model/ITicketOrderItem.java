package com.team5.HAPark.ticket.model;

public interface ITicketOrderItem {
    Integer getQuantity();

    Ticket getTicket();

    void setTicket(Ticket ticket);

    void setQuantity(Integer quantity);

    String getTicketType() ;

    void setTicketType(String ticketType) ;

    double getTicketPrice() ;

    double getTotalPrice() ;
}
