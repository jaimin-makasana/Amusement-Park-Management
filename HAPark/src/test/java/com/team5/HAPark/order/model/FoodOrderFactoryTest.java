package com.team5.HAPark.order.model;

import com.team5.HAPark.food.model.IFoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class FoodOrderFactoryTest {

    private FoodOrderFactory foodOrderFactory;

    @BeforeEach
    void setUp() {
        IFoodService foodService = Mockito.mock(IFoodService.class);
        foodOrderFactory = new FoodOrderFactory(foodService);
    }

    @Test
    void createOrderServiceNotNull() {
        IOrderService orderService = foodOrderFactory.createOrderService();
        assertNotNull(orderService);
    }

    @Test
    void createOrderService() {
        IOrderService orderService = foodOrderFactory.createOrderService();
        assertTrue(orderService instanceof OrderService);
    }

}