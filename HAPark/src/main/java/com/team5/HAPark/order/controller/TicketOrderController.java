package com.team5.HAPark.order.controller;

import com.team5.HAPark.order.model.TicketOrderFactory;
import com.team5.HAPark.order.model.IOrder;
import com.team5.HAPark.order.model.IOrderService;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.ticket.persistence.ITicketPersistenceFactory;
import com.team5.HAPark.ticket.persistence.TicketPersistenceFactory;
import com.team5.HAPark.ticket.model.ITicketService;
import com.team5.HAPark.ticket.model.TicketService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class TicketOrderController {

    @GetMapping(value = "/orders/tickets")
    public String ticketOrderHistory(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        ITicketPersistenceFactory ticketPersistenceFactory = new TicketPersistenceFactory();
        ITicketService ticketService = new TicketService(ticketPersistenceFactory.createTicketPersistence());
        TicketOrderFactory orderFactory = new TicketOrderFactory(ticketService);
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        List<IOrder> orders = orderService.getAllOrdersForUser(email);
        dataBase.close();

        model.addAttribute("orders",orders);

        return "OrderHistory";
    }

}
