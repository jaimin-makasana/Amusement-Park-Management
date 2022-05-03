package com.team5.HAPark.user.model;
import com.team5.HAPark.user.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login {

    private UserCredentials user;

    public Login(UserCredentials user) {
        this.user = user;
    }

    public boolean login(IUserPersistence userPersistence) {

        boolean loggedIn = false;

        try {
            if (userPersistence.doesUserExist(user.getEmail())) {
                String enteredEncryptedPassword = Encryption.encryptPassword(user.getPassword());
                String savedPassword = userPersistence.getPassword(user.getEmail());
                if (enteredEncryptedPassword.equals(savedPassword)) {
                    user = userPersistence.loadUser(user.getEmail());
                    loggedIn = true;
                }
            }

        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }
        return loggedIn;
    }
}
