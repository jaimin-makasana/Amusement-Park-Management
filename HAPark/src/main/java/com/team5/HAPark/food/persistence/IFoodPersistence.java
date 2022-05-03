package com.team5.HAPark.food.persistence;

import com.team5.HAPark.food.model.Food;
import com.team5.HAPark.food.model.Menu;

import java.sql.SQLException;

public interface IFoodPersistence {
    Food loadFood(String id) throws SQLException;
    Menu loadMenu() throws SQLException;
}
