package com.team5.HAPark.init;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String welcome(ModelAndView modelAndView) {
        return "Welcome";
    }

    @RequestMapping("/")
    public RedirectView directHomeToWelcome() {
        RedirectView redirectView;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        if (name.equals("anonymousUser")) {
            redirectView = new RedirectView("/welcome");
        } else {
            redirectView = new RedirectView("/main");
        }
        return redirectView;
    }
}
