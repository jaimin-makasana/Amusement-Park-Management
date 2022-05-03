package com.team5.HAPark.order.model;

public interface IOrderItem {

    Integer getQuantity();

    double getPrice();

    double getTotalPrice();

    String getName();

    String getId();
}
