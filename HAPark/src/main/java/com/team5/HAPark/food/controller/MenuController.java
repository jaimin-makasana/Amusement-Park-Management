package com.team5.HAPark.food.controller;

import com.team5.HAPark.cart.model.ICartSummary;
import com.team5.HAPark.food.model.*;
import com.team5.HAPark.food.persistence.FoodPersistenceFactory;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;

@Controller
public class MenuController {

    @Autowired private ICartSummary cart;

    @GetMapping(value = "/menu")
    public String displayFoods(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        IFoodService foodService = new FoodService(new FoodPersistenceFactory().createFoodPersistence());

        Menu menu = foodService.getMenu();
        dataBase.close();

        model.addAttribute("menu", menu.getFoodList());

        return "Menu";
    }

    @PostMapping(value = "/menu/update")
    public RedirectView addFoodsToCart(@ModelAttribute("foodOrderList") FoodOrderList foodOrderList, RedirectAttributes redirectAttributes){

        for(IFoodOrderItem foodOrderItem: foodOrderList.getFoodOrderList()){
                cart.addFoodToCart(foodOrderItem);
        }
        redirectAttributes.addFlashAttribute("message", "Cart updated");

        return new RedirectView("/menu");
    }
}

