package com.team5.HAPark.user.persistence;

import com.team5.HAPark.user.model.User;

import java.sql.SQLException;

public interface IUserPersistence {

    void saveUser(String email, String firstName, String lastName, String password) throws SQLException;

    boolean doesUserExist(String email) throws SQLException;

    String getPassword(String email) throws SQLException;

    User loadUser(String email) throws SQLException;

    void userUpdatedPassword(String confirmedPassword, String email) throws SQLException;
}
