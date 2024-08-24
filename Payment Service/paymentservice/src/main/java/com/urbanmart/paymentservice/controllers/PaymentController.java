package com.urbanmart.paymentservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/{orderId}")
    public String initiatePayment(@PathVariable String orderId){
        return "received the order Id"+orderId;
    }
}
