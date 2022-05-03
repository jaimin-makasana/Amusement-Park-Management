package com.team5.HAPark.food.model;

import com.team5.HAPark.food.persistence.IFoodPersistence;
import com.team5.HAPark.food.persistence.mocks.FoodPersistenceMockFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceTest {

    static FoodService foodService;
    static IFoodPersistence foodPersistenceMock;

    @BeforeAll
    static void init() {
        FoodPersistenceMockFactory factory = new FoodPersistenceMockFactory();
        foodPersistenceMock = factory.createFoodPersistenceMock();
        foodService = new FoodService(foodPersistenceMock);
    }

    @Test
    void getFoodExists() throws SQLException {
        assertEquals("pizza",foodService.getFood("1").getName());
    }

    @Test
    void getFoodNotExists() throws SQLException {
        assertNull(foodService.getFood("2"));
    }

    @Test
    void getMenuHasCorrectSize() throws SQLException {
        assertEquals(1,foodService.getMenu().getFoodList().size());
    }

    @Test
    void getMenuHasCorrectFoodName() throws SQLException {
        assertEquals("pizza",foodService.getMenu().getName("1"));
    }

    @Test
    void getMenuHasCorrectFoodPrice() throws SQLException {
        assertEquals(5,foodService.getMenu().getPrice("1"));
    }
}