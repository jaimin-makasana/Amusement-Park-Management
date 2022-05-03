package com.team5.HAPark.order.controller;

import com.team5.HAPark.cart.model.ICartSummary;
import com.team5.HAPark.food.persistence.FoodPersistenceFactory;
import com.team5.HAPark.food.persistence.IFoodPersistenceFactory;
import com.team5.HAPark.food.model.FoodService;
import com.team5.HAPark.food.model.IFoodOrderItem;
import com.team5.HAPark.food.model.IFoodService;
import com.team5.HAPark.order.model.FoodOrderFactory;
import com.team5.HAPark.order.model.TicketOrderFactory;
import com.team5.HAPark.order.model.IOrder;
import com.team5.HAPark.order.model.IOrderService;
import com.team5.HAPark.order.model.TicketOrderItemAdapter;
import com.team5.HAPark.ticket.persistence.ITicketPersistenceFactory;
import com.team5.HAPark.ticket.persistence.TicketPersistenceFactory;
import com.team5.HAPark.ticket.model.ITicketOrderItem;
import com.team5.HAPark.ticket.model.ITicketService;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import com.team5.HAPark.database.mysql.MySQLDatabase;

import com.team5.HAPark.ticket.model.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class SubmitOrderController {

    @Autowired
    private ICartSummary cart;

    @GetMapping(value = "/submitorder")
    public RedirectView submitOrder(){
        saveTicketOrder();
        saveFoodOrder();
        cart.empty();
        return new RedirectView("/orders");
    }

    public void saveTicketOrder() {

        ArrayList<ITicketOrderItem> ticketOrderItems = cart.getTicket();
        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        ITicketPersistenceFactory ticketPersistenceFactory = new TicketPersistenceFactory();
        ITicketService ticketService = new TicketService(ticketPersistenceFactory.createTicketPersistence());
        TicketOrderFactory orderFactory = new TicketOrderFactory(ticketService);
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        ArrayList<TicketOrderItemAdapter> ticketOrderItemAdapters = new ArrayList<>();

        for (ITicketOrderItem ticketOrderItem:ticketOrderItems){
            ticketOrderItemAdapters.add(new TicketOrderItemAdapter((TicketOrderItem) ticketOrderItem));
        }

        IOrder order = orderService.createOrderFromItemQuantities(email,ticketOrderItemAdapters);
        orderService.saveOrder(order);

        dataBase.close();
    }

    public void saveFoodOrder() {

        ArrayList<IFoodOrderItem> foodOrderItems = cart.getFood();
        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        IFoodPersistenceFactory foodPersistenceFactory = new FoodPersistenceFactory();
        IFoodService foodService = new FoodService(foodPersistenceFactory.createFoodPersistence());
        FoodOrderFactory orderFactory = new FoodOrderFactory(foodService);
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        IOrder order = orderService.createOrderFromItemQuantities(email,foodOrderItems);
        orderService.saveOrder(order);

        dataBase.close();
    }
}
