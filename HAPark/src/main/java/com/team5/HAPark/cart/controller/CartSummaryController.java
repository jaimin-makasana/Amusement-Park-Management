package com.team5.HAPark.cart.controller;

import com.team5.HAPark.cart.model.ICartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartSummaryController {

    @Autowired
    ICartSummary iCartSummary;

    @GetMapping("/cartsummary")
    public String allCartItems(Model model) {
        model.addAttribute("cartSummary", iCartSummary);
        return "CartSummary";
    }
}
