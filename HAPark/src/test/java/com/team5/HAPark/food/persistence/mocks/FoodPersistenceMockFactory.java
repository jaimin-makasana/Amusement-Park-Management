package com.team5.HAPark.food.persistence.mocks;

import com.team5.HAPark.food.persistence.IFoodPersistence;

public class FoodPersistenceMockFactory implements IFoodPersistenceMockFactory {

    @Override
    public IFoodPersistence createFoodPersistenceMock() {
        return new FoodPersistenceMock();
    }
}
