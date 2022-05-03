package com.team5.HAPark.user.model;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class EmailPasswordValidationTest {

    private UserCredentials user;
    private EmailPasswordValidation emailPasswordValidation ;

    @BeforeEach
    void init(){
        user = new UserCredentials();
        user.setEmail("emailsample@email.com@123");
        emailPasswordValidation = new EmailPasswordValidation(user);
    }

    @Nested
    @DisplayName("validate password method tests")
    class ValidatePasswordTests {
        /* Test cases for Password Validation */
        @Test
        @WithMockUser(username = "user123")
        void validatePasswordFormatHasMinimum8Chars() {
            user.setPassword("Dal@123");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasMaximum12Chars(){
            user.setPassword("Dalhousie@123");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneUpperCaseChar()  {
            user.setPassword("dal@1234");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneLowerCaseChar()  {
            user.setPassword("DAL@1234");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneNumber()  {
            user.setPassword("Dal@housie");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }

        @Test
        void validatePasswordFormatHasAtleastOneSpecialChar() {
            user.setPassword("Dalhousie123");
            assertFalse(emailPasswordValidation.validatePasswordFormat());
        }
    }

    @Nested
    @DisplayName("validate email method tests")
    class ValidateEmailTests {
        @Test
        void validateEmailFormatNoAtSymbol() {
            user.setEmail("sample.gmail.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatMultipleAtSymbol() {
            user.setEmail("sample@email@gmail.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatNoDomain() {
            user.setEmail("sample@com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatLocalLongerThan64Char() {
            user.setEmail("samplesamplesamplesamplesamplesamplesamplesamplesamplesample12345@email.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatStartsWithPeriod() {
            user.setEmail(".sample@email.com");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatEndsWithPeriod() {
            user.setEmail("sample@email.com.");
            assertFalse(emailPasswordValidation.validateEmailFormat());
        }

        @Test
        void validateEmailFormatValidEmail() {
            user.setEmail("emailsample@email.com");
            assertTrue(emailPasswordValidation.validateEmailFormat());
        }
    }
}
