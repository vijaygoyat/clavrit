package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.service.StripePaymentService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @Autowired
    private StripePaymentService stripePaymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        try {
        	logger.info("payment request for amount: {}", request.getAmount()/100);
            PaymentResponse response = stripePaymentService.createPaymentIntent(request);
            logger.info("Paymentcreated successfully");
            
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
        	
            logger.error("StripeException occurred while creating payment: "+e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponse("Failed to create payment"+e.getMessage()));
        } catch (Exception ex) {
        	
            logger.error("Unexpected error: "+ex.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponse("An unexpected error occurred"));
        }
    }
}
