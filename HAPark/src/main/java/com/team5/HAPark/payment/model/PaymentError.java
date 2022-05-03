package com.team5.HAPark.payment.model;

public enum PaymentError {
    SUCCESSFUL("success"),
    EMPTYFIELD("empty field"),
    INVALIDNUMBERLENGTH("number of digits is incorrect for card number or security code"),
    INVALIDIGITFORMAT("CVV and Card number should be numeric");


    private final String result;

    public String getResultMessage() {
        return result;
    }

    PaymentError(String result) {
        this.result = result;
    }
}
