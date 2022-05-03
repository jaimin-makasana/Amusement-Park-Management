package com.team5.HAPark.user.model;

public class UpdateableUser extends UserCredentials {

    private String oldPassword;
    private String confirmedPassword;

    public UpdateableUser() {
        super();
    }

    public UpdateableUser(String email, String password, String oldPassword, String confirmedPassword) {
        super(email, password);
        this.oldPassword = oldPassword;
        this.confirmedPassword = confirmedPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
