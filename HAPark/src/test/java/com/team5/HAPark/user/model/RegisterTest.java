package com.team5.HAPark.user.model;

import com.team5.HAPark.user.persistence.IUserPersistence;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegisterTest {

    private static Register register;
    private static RegisterUser user;
    private static IUserPersistence userPersistenceMock;

    @BeforeEach
    void init(){
        user = new RegisterUser("email@testmail.com","Password@123","fname","lname","Password@123");
        register = new Register(user);
        userPersistenceMock = Mockito.mock(IUserPersistence.class);
    }

    @Nested
    @DisplayName("registration tests")
    class RegistrationTests {

        @Test
        void registerFailsEmailEmpty() {
            user.setEmail("");
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsFnameEmpty() {
            user.setFirstName("");
            assertEquals(RegisterResult.EMPTYFIELD,register.register( userPersistenceMock));
        }

        @Test
        void registerFailsLnameEmpty() {
            user.setLastName("");
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsPasswordEmpty() {
            user.setPassword("");
            assertEquals(RegisterResult.EMPTYFIELD,register.register( userPersistenceMock));
        }

        @Test
        void registerFailsConfirmPasswordEmpty() {
            user.setConfirmedPassword("");
            assertEquals(RegisterResult.EMPTYFIELD,register.register( userPersistenceMock));
        }

        @Test
        void registerFailsEmailNull() {
            user.setEmail(null);
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsFnameNull() {
            user.setFirstName(null);
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsLnameNull() {
            user.setLastName(null);
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsPasswordNull() {
            user.setPassword(null);
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsConfirmPasswordNull() {
            user.setConfirmedPassword(null);
            assertEquals(RegisterResult.EMPTYFIELD,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsPasswordsDontMatch() {
            user.setConfirmedPassword("diffPassword");
            assertEquals(RegisterResult.PASSWORDMISMATCH,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsUserAlreadyExists() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(true);
            assertEquals(RegisterResult.ALREADYEXISTS,register.register(userPersistenceMock));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
        }

        @Test
        void registerFailsBadEmailFormat() {
            user.setEmail("notAnEmail");
            assertEquals(RegisterResult.INVALIDEMAIL,register.register(userPersistenceMock));
        }

        @Test
        void registerFailsBadPasswordFormat() {
            user.setPassword("badPassword");
            user.setConfirmedPassword("badPassword");
            assertEquals(RegisterResult.INVALIDPASSWORD,register.register(userPersistenceMock));
        }

        @Test
        void registerSuccessful() throws SQLException {
            when(userPersistenceMock.doesUserExist(user.getEmail())).thenReturn(false);
            assertEquals(RegisterResult.SUCCESSFUL,register.register(userPersistenceMock));
            verify(userPersistenceMock,times(1)).doesUserExist(user.getEmail());
            Mockito.verify(userPersistenceMock, times(1))
                    .saveUser(user.getEmail(),user.getFirstName(),user.getLastName(),"ff7bd97b1a7789ddd2775122fd6817f3173672da9f802ceec57f284325bf589f");
        }
    }
}

