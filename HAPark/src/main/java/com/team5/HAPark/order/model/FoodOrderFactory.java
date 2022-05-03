package com.team5.HAPark.order.model;

import com.team5.HAPark.food.model.IFoodService;
import com.team5.HAPark.order.persistence.IOrderPersistence;
import com.team5.HAPark.order.persistence.IOrderPersistenceFactory;
import com.team5.HAPark.order.persistence.OrderPersistenceFactory;

public class FoodOrderFactory implements IOrderFactory {

    private final IFoodService foodService;

    public FoodOrderFactory(IFoodService foodService){
        this.foodService = foodService;
    }

    @Override
    public IOrderService createOrderService() {
        return new OrderService(createFoodOrderPersistence());
    }

    private IOrderPersistence createFoodOrderPersistence() {
        IOrderPersistenceFactory orderPersistenceFactory = new OrderPersistenceFactory();
        return orderPersistenceFactory.createFoodOrderPersistence(foodService);
    }
}