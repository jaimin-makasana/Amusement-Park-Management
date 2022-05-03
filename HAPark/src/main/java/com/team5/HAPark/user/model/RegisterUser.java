package com.team5.HAPark.user.model;

public class RegisterUser extends User{

    private String confirmedPassword;

    public RegisterUser() {
        super();
    }

    public RegisterUser(String email, String password, String firstName, String lastName, String confirmedPassword) {
        super(email, password, firstName, lastName);
        this.confirmedPassword = confirmedPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
