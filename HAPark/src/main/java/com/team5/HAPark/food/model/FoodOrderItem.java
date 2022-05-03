package com.team5.HAPark.food.model;

import com.team5.HAPark.order.model.IOrderItem;

public class FoodOrderItem implements IOrderItem, IFoodOrderItem {
    private final Food food;
    private Integer quantity;

    public FoodOrderItem() {
        food = new Food();
    }

    @Override
    public Food getFood() {
        return food;
    }

    public FoodOrderItem(Food food, Integer quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getPrice(){
        return food.getPrice();
    }

    @Override
    public double getTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public void setPrice(Double price){
        food.setPrice(price);
    }

    @Override
    public String getName(){
        return food.getName();
    }

    @Override
    public void setName(String name){
        food.setName(name);
    }

    @Override
    public String getId(){
        return food.getId();
    }

    @Override
    public void setId(String id){
        food.setId(id);
    }
}
