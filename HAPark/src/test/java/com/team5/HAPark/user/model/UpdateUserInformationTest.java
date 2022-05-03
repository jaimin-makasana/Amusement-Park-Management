package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.persistence.mocks.UserPersistenceMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UpdateUserInformationTest {

    private static UpdateUserInformation updateUserInformation;
    private static UpdateableUser user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init() throws NoSuchAlgorithmException, SQLException {
        user = new UpdateableUser();
        user.setPassword("NewPass@123");
        user.setConfirmedPassword("NewPass@123");
        user.setEmail("test@gmail.com");
        user.setOldPassword("password");
        updateUserInformation = new UpdateUserInformation(user);
        String oldPassword = Encryption.encryptPassword("password");
        UserPersistenceMockFactory userPersistenceMockFactory = new UserPersistenceMockFactory();
        userPersistenceMock = userPersistenceMockFactory.createUserPersistenceMock();
        userPersistenceMock.saveUser("test@gmail.com","fname","lname",oldPassword);
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingOldAndCurrentPasswordAreSame() throws SQLException, NoSuchAlgorithmException {
        assertEquals(UpdateUserValidationResult.SUCCESSFUL,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingNewAndConfirmedPasswordAreSame() throws SQLException, NoSuchAlgorithmException {
        user.setConfirmedPassword("ConfPassword@123");
        assertEquals(UpdateUserValidationResult.PASSWORDMISMATCH,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingOldAndNewPasswordAreNotSame() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("password");
        user.setConfirmedPassword("password");
        assertEquals(UpdateUserValidationResult.NEWMATCHESOLDPASSWORD,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingThePasswordUpdatedIsInCorrectFormat() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("NewPassword@123");
        user.setConfirmedPassword("NewPassword@123");
        assertEquals(UpdateUserValidationResult.INVALIDPASSWORDFORMAT,updateUserInformation.updateUserPassword(userPersistenceMock));
    }


    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneDigit() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("NewPassword@");
        user.setConfirmedPassword("NewPassword@");
        assertEquals(UpdateUserValidationResult.INVALIDPASSWORDFORMAT, updateUserInformation.updateUserPassword(userPersistenceMock));
    }
    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneSpecialCharacter() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("NewPassword123");
        user.setConfirmedPassword("NewPassword123");
        assertEquals(UpdateUserValidationResult.INVALIDPASSWORDFORMAT,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneCapitalCharacter() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("newpass@123");
        user.setConfirmedPassword("newpass@123");
        assertEquals(UpdateUserValidationResult.INVALIDPASSWORDFORMAT,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void validatingUpdatedPasswordHasAtleastOneSmallCharacter() throws SQLException, NoSuchAlgorithmException {
        user.setPassword("NEWPASSWORD@123");
        user.setConfirmedPassword("NEWPASSWORD@123");
        assertEquals(UpdateUserValidationResult.INVALIDPASSWORDFORMAT,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void emailDoesntMatchCurrentUser() throws SQLException, NoSuchAlgorithmException {
        user.setEmail("wrongEmail");
        assertEquals(UpdateUserValidationResult.INVALIDCREDENTIALS,updateUserInformation.updateUserPassword(userPersistenceMock));
    }

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void passwordDoesntMatchCurrentUser() throws SQLException, NoSuchAlgorithmException {
        user.setOldPassword("wrongPassword");
        assertEquals(UpdateUserValidationResult.INVALIDCREDENTIALS,updateUserInformation.updateUserPassword(userPersistenceMock));
    }
}