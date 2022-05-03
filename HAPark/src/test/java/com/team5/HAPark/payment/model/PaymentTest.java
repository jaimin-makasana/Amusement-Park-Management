package com.team5.HAPark.payment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @Test
    public void validate() {
        Payment p = new Payment("credit", "3574747389293745", "10-03-2021", "2021", "234");
        assertEquals(PaymentError.SUCCESSFUL, p.Validate());
    }

    @Test
    public void emptyfield() {
        Payment p = new Payment("credit", "", "10-03-2020", "2020", "234");
        assertEquals(PaymentError.EMPTYFIELD, p.Validate());
    }

    @Test
    public void invalidnumberlength() {
        Payment p = new Payment("credit", "3574747389293745", "07-03-2022", "2022", "23");
        assertEquals(PaymentError.INVALIDNUMBERLENGTH, p.Validate());
    }

    @Test
    public void invalidnumberlength2() {
        Payment p = new Payment("credit", "357473745", "11-01-2016", "2016", "237");
        assertEquals(PaymentError.INVALIDNUMBERLENGTH, p.Validate());
    }

    @Test
    public void invaliddigit() {
        Payment p = new Payment("credit", "hs5f3hj8df46fh3r", "17-03-2018", "2018", "841");
        assertEquals(PaymentError.INVALIDIGITFORMAT, p.Validate());
    }

    @Test
    public void invaliddigit2() {
        Payment p = new Payment("credit", "8756354624354657", "14-04-2013", "2013", "hfs");
        assertEquals(PaymentError.INVALIDIGITFORMAT, p.Validate());
    }
}
