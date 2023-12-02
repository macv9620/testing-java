package com.macv.tests.payments;

public interface IPaymentGateway {
    PaymentResponse requestPayment(PaymentRequest request);
}
