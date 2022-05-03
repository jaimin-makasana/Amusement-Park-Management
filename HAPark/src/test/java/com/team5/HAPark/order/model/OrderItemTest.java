package com.team5.HAPark.order.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    private OrderItem orderItem;

    @Test
    void getTotalPriceWithIntegerPrice() {
        orderItem = new OrderItem(5,3,"1","item");
        assertEquals(15,orderItem.getTotalPrice());
    }

    @Test
    void getTotalPriceWithDecimalPrice() {
        orderItem = new OrderItem(5,3.25,"1","item");
        assertEquals(16.25,orderItem.getTotalPrice());
    }
}