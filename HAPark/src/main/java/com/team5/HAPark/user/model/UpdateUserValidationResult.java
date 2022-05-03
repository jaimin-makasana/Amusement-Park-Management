package com.team5.HAPark.user.model;

public enum UpdateUserValidationResult {
    SUCCESSFUL("success"),
    INVALIDPASSWORDFORMAT("Password format is not correct"),
    PASSWORDMISMATCH("confirmed password doesn't match"),
    INVALIDCREDENTIALS("email and old password do not match current user"),
    NEWMATCHESOLDPASSWORD("Old password matches new password");

    private final String result;

    public String getResultMessage() {
        return result;
    }

    UpdateUserValidationResult(String result) {
        this.result = result;
    }
}
