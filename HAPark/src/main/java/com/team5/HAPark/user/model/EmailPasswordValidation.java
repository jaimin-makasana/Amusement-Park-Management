package com.team5.HAPark.user.model;

public class EmailPasswordValidation implements IEmailPasswordValidation {

    private final UserCredentials user;

    public EmailPasswordValidation(UserCredentials user) {
        this.user = user;
    }

    public boolean validateEmailFormat(){

        String email = user.getEmail();
        String[] emailSplit = email.split("@");

        if (emailSplit.length == 2 && emailSplit[0].length()<65){
            String[] domainSplit = emailSplit[1].split("\\.");
            if (domainSplit.length >= 2) {
                if (Character.toString(email.charAt(email.length()-1)).matches("[0-9a-zA-Z]")) {
                    return user.getEmail().matches("[0-9a-zA-Z][0-9a-zA-Z.!#$%&'*+-/=?^_`{|]*@[0-9a-zA-Z!#$%&'*+-/=?^_`{|]*.[0-9a-zA-Z.!#$%&'*+-/=?^_`{|]*");
                }
            }
        }
        return false;
    }

    /* Password validation */
    /*Refactored the code to pass the test cases*/
    public  boolean validatePasswordFormat() {

        String password = user.getPassword();

        boolean passwordValid = true;

        if (password.length() < 8 || password.length() > 12)
        {
            passwordValid = false;
        }
        String oneUpperCaseChar = "(.*[A-Z].*)";
        if (!password.matches(oneUpperCaseChar))
        {
           passwordValid = false;
        }
        String oneLowerCaseChar = "(.*[a-z].*)";
        if (!password.matches(oneLowerCaseChar))
        {
            passwordValid = false;
        }
        String oneNumber = "(.*[0-9].*)";
        if (!password.matches(oneNumber))
        {
           passwordValid = false;
        }
        String oneSpecialChars = "(.*[@#$%].*)";
        if (!password.matches(oneSpecialChars))
        {
           passwordValid = false;
        }
        return passwordValid;
    }

}
