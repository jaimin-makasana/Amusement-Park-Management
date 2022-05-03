package com.team5.HAPark.user.persistence.mocks;

import com.team5.HAPark.user.model.Encryption;
import com.team5.HAPark.user.model.User;
import com.team5.HAPark.user.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UserPersistenceMock implements IUserPersistence {

    private HashMap<String,User> users;

    public UserPersistenceMock() {
        this.users = new HashMap<>();
    }

    @Override
    public void saveUser(String email, String firstName, String lastName, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        if (!users.containsKey(email)){
            users.put(email,user);
        }
    }

    @Override
    public boolean doesUserExist(String email) {
        return users.containsKey(email);
    }

    @Override
    public String getPassword(String email) {
        return loadUser(email).getPassword();
    }

    @Override
    public User loadUser(String email) {
        return users.get(email);
    }

    @Override
    public void userUpdatedPassword(String confirmedPassword, String email) {
        try {
            loadUser(email).setPassword(Encryption.encryptPassword(confirmedPassword));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
