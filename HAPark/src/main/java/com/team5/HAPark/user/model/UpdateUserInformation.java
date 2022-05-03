package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateUserInformation implements IUpdateUserInformation {

    private UpdateableUser user;

    private EmailPasswordValidation emailPasswordValidation ;

    public UpdateUserInformation(UpdateableUser user) {
        this.user = user;
        emailPasswordValidation = new EmailPasswordValidation(user);
    }

    public UpdateUserValidationResult updateUserPassword(IUserPersistence userPersistence)
            throws SQLException, NoSuchAlgorithmException, AuthenticationException {

        if (isCurrentUser(userPersistence)) {
            if (isNewPasswordDifferent()) {
                if (newPasswordMatchesConfirmedPassword()) {
                    if (emailPasswordValidation.validatePasswordFormat()) {
                        userPersistence.userUpdatedPassword(user.getPassword(), user.getEmail());
                        return UpdateUserValidationResult.SUCCESSFUL;
                    } else {
                        return UpdateUserValidationResult.INVALIDPASSWORDFORMAT;
                    }
                } else {
                    return UpdateUserValidationResult.PASSWORDMISMATCH;
                }
            } else {
                return UpdateUserValidationResult.NEWMATCHESOLDPASSWORD;
            }
        } else {
            return UpdateUserValidationResult.INVALIDCREDENTIALS;
        }
    }

    private boolean isCurrentUser(IUserPersistence userPersistence) throws SQLException, NoSuchAlgorithmException {
        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String currentPasswordEncrypted = userPersistence.getPassword(email);
        String oldPasswordEncrypted = Encryption.encryptPassword(user.getOldPassword());

        boolean emailIsCurrentUser = email.matches(user.getEmail());
        boolean oldPasswordIsCurrentPassword = currentPasswordEncrypted.matches(oldPasswordEncrypted);
        return emailIsCurrentUser && oldPasswordIsCurrentPassword;
    }

    private boolean isNewPasswordDifferent() throws NoSuchAlgorithmException {
        String oldPasswordEncrypted = Encryption.encryptPassword(user.getOldPassword());
        String newPasswordEncrypted = Encryption.encryptPassword(user.getPassword());

        return !oldPasswordEncrypted.matches(newPasswordEncrypted);
    }

    private boolean newPasswordMatchesConfirmedPassword() {
        return user.getPassword().matches(user.getConfirmedPassword());
    }

}
