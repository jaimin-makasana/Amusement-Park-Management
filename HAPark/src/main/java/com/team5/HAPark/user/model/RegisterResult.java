package com.team5.HAPark.user.model;

public enum RegisterResult {
    SUCCESSFUL("success"),
    EMPTYFIELD("empty field"),
    ALREADYEXISTS("email already belongs to a registered user"),
    PASSWORDMISMATCH("password and confirmed password do not match"),
    INVALIDEMAIL("Not a valid email format"),
    INVALIDPASSWORD("Password should have at least one uppercase, one lower case, one special character,one digit.");

    private final String result;

    public String getResultMessage() {
        return result;
    }

    RegisterResult(String result) {
        this.result = result;
    }
}
