package com.team5.HAPark.user.controller;

import com.team5.HAPark.user.model.IUpdateUserInformation;
import com.team5.HAPark.user.model.UpdateUserInformation;
import com.team5.HAPark.user.model.UpdateUserValidationResult;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.persistence.UserPersistenceFactory;
import com.team5.HAPark.user.model.UpdateableUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@org.springframework.stereotype.Controller
@Slf4j
public class UpdateUserInfoController {

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) {
        model.addAttribute("user", new UpdateableUser());
        return "UpdateUserInfo";
    }

    @PostMapping("/updateuserinfo")
    public RedirectView updateUserInfo(@ModelAttribute("user") UpdateableUser user, RedirectAttributes redirectAttributes) throws SQLException, NoSuchAlgorithmException {
        IUpdateUserInformation updateUserInformation = new UpdateUserInformation(user);
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        log.info("{}",username);
        log.info("{} email {} oldpassword {} newpassword {} confirmedpassword ",
                user.getEmail(),user.getOldPassword(),user.getPassword(),user.getConfirmedPassword());

        UserPersistenceFactory userPersistenceFactory = new UserPersistenceFactory();
        IUserPersistence userPersistence = userPersistenceFactory.createUserPersistence();
        UpdateUserValidationResult result = updateUserInformation.updateUserPassword(userPersistence);

        redirectAttributes.addFlashAttribute("result",result);
        return new RedirectView("/updateuserinfo");
    }

}
