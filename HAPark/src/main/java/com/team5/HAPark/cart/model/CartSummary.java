package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.model.IFoodOrderItem;
import com.team5.HAPark.ticket.model.ITicketOrderItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.ListIterator;

@Component
@SessionScope
public class CartSummary implements ICartSummary{

    private ArrayList<ITicketOrderItem> ticket;
    private ArrayList<IFoodOrderItem> food;
    double ticketAmount;
    double foodAmount;
    double totalAmount;

    public CartSummary() {
        this.ticket = new ArrayList<>();
        this.food = new ArrayList<>();
        this.totalAmount = 0;
    }

    public ArrayList<ITicketOrderItem> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<ITicketOrderItem> ticket) {
        this.ticket = ticket;
    }

    public ArrayList<IFoodOrderItem> getFood() {
        return food;
    }

    public void setFood(ArrayList<IFoodOrderItem> food) {
        this.food = food;
    }

    public void setTicketAmount(double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    //https://stackoverflow.com/questions/32394592/calculate-the-number-of-items-within-the-a-shopping-cart

    @Override
    public void addTicketToCart(ITicketOrderItem ticket) {
        if (ticket.getQuantity()!=null && ticket.getQuantity()>0){
            this.ticket.add(ticket);
        }
    }

    @Override
    public void addFoodToCart(IFoodOrderItem food) {
        if (food.getQuantity()!=null && food.getQuantity()>0){
            this.food.add(food);
        }
    }

    @Override
    public void showCart() {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        ListIterator<IFoodOrderItem> foodIterator = food.listIterator();
        while ((ticketIterator.hasNext() || (foodIterator.hasNext()))){
            ITicketOrderItem ticketItem = ticketIterator.next();
            IFoodOrderItem foodItem = foodIterator.next();
            System.out.println(ticketItem);
            System.out.println(foodItem);
        }
    }

    @Override
    public void removeTicketFromCart(ITicketOrderItem t) {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        while (ticketIterator.hasNext()) {
            ITicketOrderItem ticketItem = ticketIterator.next();
            if (ticketItem.getTicketType().equals(t.getTicketType())) {
                ticketItem.setQuantity(ticketItem.getQuantity()-t.getQuantity());
                if((ticketItem.getQuantity() <= 0 )) {
                    ticket.remove(ticketItem);
                }
                break;
            }
        }
    }

    @Override
    public void removeFoodFromCart(IFoodOrderItem f) {
        ListIterator<IFoodOrderItem> foodIterator = food.listIterator();
        while  (foodIterator.hasNext()){
            IFoodOrderItem foodItem = foodIterator.next();
            if (foodItem.getId().equals(f.getId())) {
                foodItem.setQuantity(foodItem.getQuantity()-f.getQuantity());
                if((foodItem.getQuantity() <= 0 )) {
                    food.remove(foodItem);
                }
                break;
            }
        }
    }

    @Override
    public double getTicketAmount() {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        this.ticketAmount = 0;
        while (ticketIterator.hasNext()) {
            ITicketOrderItem ticketItem = ticketIterator.next();
            this.ticketAmount = this.ticketAmount + (ticketItem.getQuantity() * ticketItem.getTicketPrice());
            }
        return this.ticketAmount;
    }

    @Override
    public double getFoodAmount() {
        ListIterator<IFoodOrderItem> foodIterator = food.listIterator();
        this.foodAmount = 0;
        while (foodIterator.hasNext()) {
            IFoodOrderItem foodItem = foodIterator.next();
            this.foodAmount = this.foodAmount + (foodItem.getPrice() * foodItem.getQuantity());
        }
        return this.foodAmount;
    }

    @Override
    public double getTotalAmount() {
        totalAmount = getTicketAmount() + getFoodAmount();
        return this.totalAmount;
    }

    public void empty() {
        ticket.clear();
        food.clear();
    }
}


