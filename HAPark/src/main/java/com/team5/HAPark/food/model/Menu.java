package com.team5.HAPark.food.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private LinkedHashMap<String, Food> menu;

    public Menu(){
        menu = new LinkedHashMap<>();
    }

    public String getName(String id){
        Food food = menu.get(id);
        String name = food.getName();

        return name;
    }

    public Double getPrice(String id){
        Food food = menu.get(id);
        Double price = food.getPrice();

        return price;
    }

    public void addFoodToMenu(Food food){
        String id = food.getId();
        menu.put(id,food);
    }

    public List<Food> getFoodList(){
        ArrayList<Food> foodList = new ArrayList<>();

        for (Map.Entry<String,Food> entry : menu.entrySet()){
            foodList.add(entry.getValue());
        }
        return foodList;
    }
}
