package com.urbanmart.paymentservice.services;

import com.urbanmart.paymentservice.PaymentGatewayChooser.PaymentGatewayChooserStrategy;
import com.urbanmart.paymentservice.paymentgateway.PaymentGatewayProvider;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private final PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;
    public PaymentServices(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy){
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }
    public String initiatePayment(){
        PaymentGatewayProvider paymentGatewayProvider = this.paymentGatewayChooserStrategy.getBestAvailablePaymentGatewayProvider();
        String callBackURL = paymentGatewayProvider.getPaymentGatewayLink(123L,"",1234L,12L);
        return null;
    }
}
