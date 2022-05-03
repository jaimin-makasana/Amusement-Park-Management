package com.team5.HAPark.order.controller;

import com.team5.HAPark.food.persistence.FoodPersistenceFactory;
import com.team5.HAPark.food.persistence.IFoodPersistenceFactory;
import com.team5.HAPark.food.model.FoodService;
import com.team5.HAPark.food.model.IFoodService;
import com.team5.HAPark.order.model.FoodOrderFactory;
import com.team5.HAPark.order.model.IOrder;
import com.team5.HAPark.order.model.IOrderFactory;
import com.team5.HAPark.order.model.IOrderService;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class FoodOrderController {

    @GetMapping(value = "/orders/food")
    public String foodOrderHistory(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        IFoodPersistenceFactory foodPersistenceFactory = new FoodPersistenceFactory();
        IFoodService foodService = new FoodService(foodPersistenceFactory.createFoodPersistence());
        IOrderFactory orderFactory = new FoodOrderFactory(foodService);
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        List<IOrder> orders = orderService.getAllOrdersForUser(email);
        dataBase.close();

        model.addAttribute("orders",orders);

        return "OrderHistory";
    }
}
