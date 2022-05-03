package com.team5.HAPark.order.model;

import com.team5.HAPark.order.persistence.IOrderPersistence;
import com.team5.HAPark.order.persistence.IOrderPersistenceFactory;
import com.team5.HAPark.order.persistence.OrderPersistenceFactory;
import com.team5.HAPark.ticket.model.ITicketService;

public class TicketOrderFactory implements IOrderFactory {

    private final ITicketService ticketService;

    public TicketOrderFactory(ITicketService ticketService){
        this.ticketService = ticketService;
    }

    @Override
    public IOrderService createOrderService() {
        return new OrderService(createTicketOrderPersistence());
    }

    private IOrderPersistence createTicketOrderPersistence() {
        IOrderPersistenceFactory orderPersistenceFactory = new OrderPersistenceFactory();
        return orderPersistenceFactory.createTicketOrderPersistence(ticketService);
    }
}
