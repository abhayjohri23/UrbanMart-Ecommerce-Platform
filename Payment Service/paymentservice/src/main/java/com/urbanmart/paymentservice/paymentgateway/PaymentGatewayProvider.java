package com.urbanmart.paymentservice.paymentgateway;

public interface PaymentGatewayProvider {

    public String getPaymentGatewayLink(Long orderId,String email,Long phoneNo,Long amount);
}
