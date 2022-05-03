package com.team5.HAPark.order.model;

public class OrderItem implements IOrderItem{
    private Integer quantity;
    private double price;
    private String id;
    private String name;

    public OrderItem() {}

    public OrderItem(Integer quantity, double price, String id, String name) {
        this.quantity = quantity;
        this.price = price;
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getTotalPrice() {
        return quantity * price;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
