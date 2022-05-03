package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;
import org.springframework.security.core.AuthenticationException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IUpdateUserInformation {
     UpdateUserValidationResult updateUserPassword(IUserPersistence userPersistence)
            throws SQLException, NoSuchAlgorithmException, AuthenticationException;
}
