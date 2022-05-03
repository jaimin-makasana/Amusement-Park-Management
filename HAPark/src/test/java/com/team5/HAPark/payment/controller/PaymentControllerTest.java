package com.team5.HAPark.payment.controller;

import com.team5.HAPark.payment.model.Payment;
import com.team5.HAPark.payment.model.PaymentError;
import org.junit.*;

public class PaymentControllerTest {

    @Test
    public void addpayment() {
        PaymentController pc = new PaymentController();
        String resultMessage = PaymentError.SUCCESSFUL.getResultMessage();
        Assert.assertEquals(resultMessage, pc.PaymentSubmit(new Payment("c", "4536234536335264", "10", "23", "234"), null));
    }

}
