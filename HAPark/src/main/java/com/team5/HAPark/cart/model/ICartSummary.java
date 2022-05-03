package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.model.IFoodOrderItem;
import com.team5.HAPark.ticket.model.ITicketOrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface ICartSummary {
    void addTicketToCart(ITicketOrderItem ticket);
    void addFoodToCart(IFoodOrderItem food);
    void showCart();
    void removeTicketFromCart(ITicketOrderItem t);
    void removeFoodFromCart(IFoodOrderItem f);
    double getTicketAmount();
    double getFoodAmount();
    double getTotalAmount();
    void empty();
    ArrayList<ITicketOrderItem> getTicket();
    ArrayList<IFoodOrderItem> getFood();
}
