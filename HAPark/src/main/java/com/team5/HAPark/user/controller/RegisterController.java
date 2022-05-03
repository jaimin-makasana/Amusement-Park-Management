package com.team5.HAPark.user.controller;

import com.team5.HAPark.user.model.Register;
import com.team5.HAPark.user.model.RegisterUser;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.persistence.IUserPersistenceFactory;
import com.team5.HAPark.user.persistence.UserPersistenceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
class RegisterController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("register", new RegisterUser());
        return "Register";
    }

    @PostMapping("/register")
    public RedirectView registerSubmit(@ModelAttribute RegisterUser registerUser, Model model, RedirectAttributes redirectAttributes) {

        IUserPersistenceFactory factory = new UserPersistenceFactory();
        IUserPersistence userPersistence = factory.createUserPersistence();
        Register register = new Register(registerUser);

        redirectAttributes.addFlashAttribute("result", register.register(userPersistence));
        return  new RedirectView("/register");
    }
}
