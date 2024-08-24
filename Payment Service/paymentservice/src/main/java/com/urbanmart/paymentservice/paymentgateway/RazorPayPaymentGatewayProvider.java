package com.urbanmart.paymentservice.paymentgateway;

import org.springframework.stereotype.Service;

@Service
public class RazorPayPaymentGatewayProvider implements PaymentGatewayProvider{
    @Override
    public String getPaymentGatewayLink(Long orderId,String email,Long phoneNo,Long amount) {
        return null;
    }
}
