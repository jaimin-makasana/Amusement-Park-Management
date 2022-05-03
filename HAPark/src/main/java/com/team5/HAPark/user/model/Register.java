package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register {

    private final RegisterUser user;
    private final IEmailPasswordValidation emailPasswordValidation ;

    public Register(RegisterUser user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(this.user);
    }

    public RegisterResult register(IUserPersistence userPersistence) {
        RegisterResult registerResult = validateUserInfo();
        if (registerResult == RegisterResult.SUCCESSFUL) {
            try {
                if (!userPersistence.doesUserExist(user.getEmail())) {
                    userPersistence.saveUser(user.getEmail(), user.getFirstName(),
                            user.getLastName(), Encryption.encryptPassword(user.getPassword()));
                    return RegisterResult.SUCCESSFUL;
                } else {
                    return RegisterResult.ALREADYEXISTS;
                }
            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return registerResult;
    }

    private RegisterResult validateUserInfo() {
        if (fieldIsPresent(user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getConfirmedPassword())) {
            if (emailPasswordValidation.validateEmailFormat()) {
                if (emailPasswordValidation.validatePasswordFormat()) {
                    if (user.getPassword().matches(user.getConfirmedPassword())) {
                        return RegisterResult.SUCCESSFUL;
                    } else {
                        return RegisterResult.PASSWORDMISMATCH;
                    }
                } else {
                    return RegisterResult.INVALIDPASSWORD;
                }
            } else {
                return RegisterResult.INVALIDEMAIL;
            }
        }
        return RegisterResult.EMPTYFIELD;
    }

    private boolean fieldIsPresent(String ... fields) {
        for (String field : fields){
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}