package com.team5.HAPark.order.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Order implements IOrder{

    private Integer orderId;
    private String mailId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List<? extends IOrderItem> orderItems;

    public Order() {}

    public Order(Integer orderId, String mailId, LocalDate orderDate, LocalTime orderTime, List<? extends IOrderItem> orderItems) {
        this.orderId = orderId;
        this.mailId = mailId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
    }

    @Override
    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getMailId() {
        return mailId;
    }

    @Override
    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Override
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public LocalTime getOrderTime() {
        return orderTime;
    }

    @Override
    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public List<? extends IOrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public void setOrderItems(List<? extends IOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
