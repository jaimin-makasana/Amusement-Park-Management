package com.team5.HAPark.food.persistence.mocks;

import com.team5.HAPark.food.persistence.IFoodPersistence;
import com.team5.HAPark.food.model.Food;
import com.team5.HAPark.food.model.Menu;

import java.util.HashMap;
import java.util.Map;

public class FoodPersistenceMock implements IFoodPersistence {

    private HashMap<String, Food> foods;

    public FoodPersistenceMock() {
        this.foods = new HashMap<>();
        Food pizza = new Food("pizza","1",5);
        foods.put("1",pizza);
    }

    @Override
    public Food loadFood(String id) {
        return foods.get(id);
    }

    @Override
    public Menu loadMenu() {
        Menu menu = new Menu();
        for (Map.Entry<String,Food> foodEntry : foods.entrySet()) {
            menu.addFoodToMenu(foodEntry.getValue());
        }
        return menu;
    }
}
