package com.team5.HAPark.food.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderItemTest {

    private FoodOrderItem foodOrderItem;

    @BeforeEach
    void setUp() {
        Food food = new Food("food","1",3);
        foodOrderItem = new FoodOrderItem(food,4);
    }

    @Test
    void getPrice() {
        assertEquals(3,foodOrderItem.getPrice());
    }

    @Test
    void getTotalPriceIntegerPrice() {
        assertEquals(12,foodOrderItem.getTotalPrice());
    }

    @Test
    void getTotalPriceDecimalPrice() {
        foodOrderItem.setPrice(2.25);
        assertEquals(9,foodOrderItem.getTotalPrice());
    }

    @Test
    void setPrice() {
        foodOrderItem.setPrice(2.5);
        assertEquals(2.5,foodOrderItem.getPrice());
    }

    @Test
    void getName() {
        assertEquals("food",foodOrderItem.getName());
    }

    @Test
    void setName() {
        foodOrderItem.setName("new food");
        assertEquals("new food",foodOrderItem.getName());
    }

    @Test
    void getId() {
        assertEquals("1",foodOrderItem.getId());
    }

    @Test
    void setId() {
        foodOrderItem.setId("2");
        assertEquals("2",foodOrderItem.getId());
    }
}