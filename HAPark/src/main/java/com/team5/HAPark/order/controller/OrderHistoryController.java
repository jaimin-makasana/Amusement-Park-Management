package com.team5.HAPark.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderHistoryController {

    @GetMapping(value = "/orders")
    public String orderHistoryPage(){
        return "OrderHistory";
    }

}
