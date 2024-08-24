package com.urbanmart.paymentservice.PaymentGatewayChooser;

import com.urbanmart.paymentservice.paymentgateway.PaymentGatewayProvider;
import com.urbanmart.paymentservice.paymentgateway.RazorPayPaymentGatewayProvider;
import com.urbanmart.paymentservice.paymentgateway.StripePaymentGatewayProvider;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewayChooserStrategy {
    private StripePaymentGatewayProvider stripePaymentGatewayProvider;
    private RazorPayPaymentGatewayProvider razorPayPaymentGatewayProvider;
    public PaymentGatewayChooserStrategy(
            RazorPayPaymentGatewayProvider razorPayPaymentGatewayProvider,
            StripePaymentGatewayProvider stripePaymentGatewayProvider
    ){
        this.razorPayPaymentGatewayProvider = razorPayPaymentGatewayProvider;
        this.stripePaymentGatewayProvider = stripePaymentGatewayProvider;
    }

    public PaymentGatewayProvider getBestAvailablePaymentGatewayProvider(){
        int placeholder = new Random().nextInt();
        if(placeholder % 2 == 0){
            return this.stripePaymentGatewayProvider;
        }

        return this.razorPayPaymentGatewayProvider;
    }
}
