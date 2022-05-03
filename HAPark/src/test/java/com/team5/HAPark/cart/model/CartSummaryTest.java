package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.model.FoodOrderItem;
import com.team5.HAPark.food.model.IFoodOrderItem;
import com.team5.HAPark.ticket.model.ITicketOrderItem;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartSummaryTest {
    private IFoodOrderItem foodOrderItem;
    private ITicketOrderItem ticketOrderItem;
    private CartSummary cartSummary;

    @BeforeEach
    void init() {
        foodOrderItem = new FoodOrderItem();
        ticketOrderItem =  new TicketOrderItem();
        cartSummary = new CartSummary();
    }

    @Test
    void validateIfTicketsAreAddedToCart() {
        ticketOrderItem.setQuantity(1);
        cartSummary.addTicketToCart(ticketOrderItem);
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertTrue(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateNoQuantityTicketsNotAddedToCart() {
        cartSummary.addTicketToCart(ticketOrderItem);
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodIsAddedToCart() {
        foodOrderItem.setQuantity(1);
        cartSummary.addFoodToCart(foodOrderItem);
        List<IFoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertTrue(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateNoQuantityFoodNotAddedToCart() {
        cartSummary.addFoodToCart(foodOrderItem);
        List<IFoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTicketsAreRemovedToCart() {
        cartSummary.removeTicketFromCart(ticketOrderItem);
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodAreRemovedToCart() {
        cartSummary.removeFoodFromCart(foodOrderItem);
        List<IFoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTicketsAmountIsCalculatedCorrectly() {
        cartSummary.getTicketAmount();
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodAmountIsCalculatedCorrectly() {
        cartSummary.getFoodAmount();
        List<IFoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTotalAmountIsCalculatedCorrectly() {
        double cartAmount = cartSummary.getTotalAmount();
        double ticketAmount = cartSummary.getTicketAmount();
        double foodAmount = cartSummary.getFoodAmount();
        assertEquals(cartAmount, (ticketAmount+foodAmount));
    }

    @Test
    void validateCartIsEmpty() {
        cartSummary.addFoodToCart(foodOrderItem);
        cartSummary.addTicketToCart(ticketOrderItem);
        cartSummary.empty();
        assertTrue(cartSummary.getFood().isEmpty()&&cartSummary.getTicket().isEmpty());
    }
}