package com.team5.HAPark.payment.controller;

import com.team5.HAPark.payment.model.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    @GetMapping("/payment")
    public String PaymentForm(Model model) {
        model.addAttribute("payment", new Payment("","","","",""));
        return "Payment";
    }

    @PostMapping("/payment")
    public String PaymentSubmit(@ModelAttribute Payment payment, Model model) {
        model.addAttribute("payment", payment);
        payment.setErrormsg(payment.Validate());
        return "Payment";
    }
}
