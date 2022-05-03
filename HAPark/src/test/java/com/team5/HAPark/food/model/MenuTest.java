package com.team5.HAPark.food.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private static Menu menu;
    private static Food pizza;
    private static Food burger;
    private static Food fries;

    @BeforeAll
    static void init(){
        menu = new Menu();
        pizza = new Food("pizza","1",5);
        burger = new Food("burger","2",10);
        fries = new Food("fries","3",4);
        menu.addFoodToMenu(pizza);
        menu.addFoodToMenu(fries);
    }

    @Test
    void getName() {
        assertEquals("pizza",menu.getName("1"));
    }

    @Test
    void getPrice() {
        assertEquals(5,menu.getPrice("1"));
    }

    @Test
    void addFoodToMenu() {
        menu.addFoodToMenu(burger);
        assertEquals("burger",menu.getName("2"));
    }

    @Test
    void getFoodList() {
        List<Food> foods = menu.getFoodList();
        assertTrue(foods.contains(fries));
        assertTrue(foods.contains(pizza));
    }
}