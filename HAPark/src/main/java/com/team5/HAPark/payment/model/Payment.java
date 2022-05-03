package com.team5.HAPark.payment.model;

public class Payment {

    private String ctype;
    private String cno;
    private String mm;
    private String yy;
    private String cvv;
    private PaymentError errormsg;

    public Payment(String ctype, String cno, String mm, String yy, String cvv) {
        this.ctype = ctype;
        this.cno = cno;
        this.mm = mm;
        this.yy = yy;
        this.cvv = cvv;

    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public PaymentError getErrormsg() {
        return errormsg;
    }
    public void setErrormsg(PaymentError errormsg) {
        this.errormsg = errormsg;
    }

    public PaymentError Validate() {
        PaymentError paymentError;

        if( cno.equals("") || mm.equals("") || yy.equals("") || cvv.equals("")) {
            paymentError = PaymentError.EMPTYFIELD;
        } else {
            if(cvv.length()!=3 || cno.length()!=16) {
                paymentError = PaymentError.INVALIDNUMBERLENGTH;
            } else {
                if(!cvv.chars().allMatch(Character::isDigit) || !cno.chars().allMatch(Character::isDigit)) {
                    paymentError = PaymentError.INVALIDIGITFORMAT;
                } else {
                    paymentError = PaymentError.SUCCESSFUL;
                }
            }
        }
        return paymentError;
    }

}
