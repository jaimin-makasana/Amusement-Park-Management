package com.team5.HAPark.order.persistence;

import com.team5.HAPark.food.model.IFoodService;
import com.team5.HAPark.ticket.model.ITicketService;

public interface IOrderPersistenceFactory {

    IOrderPersistence createFoodOrderPersistence(IFoodService foodService);

    IOrderPersistence createTicketOrderPersistence(ITicketService ticketService);
}
