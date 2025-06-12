package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StripePaymentService {

    Logger logger = LoggerFactory.getLogger(StripePaymentService.class);

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    public PaymentResponse createPaymentIntent(PaymentRequest request) throws StripeException {
    	
        Stripe.apiKey = stripeSecretKey;

        logger.info("Creating PaymentIntent for amount: {}, currency: {}",request.getAmount(), request.getCurrency());

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", request.getAmount());
            params.put("currency", request.getCurrency());
            params.put("description", request.getDescription());
            params.put("receipt_email", request.getEmail());
            
            Map<String, String> metadata = new HashMap<>();
            metadata.put("order_id", "ORD-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000));
            
            params.put("metadata", metadata);

            PaymentIntent intent = PaymentIntent.create(params);

            logger.info("PaymentIntent created with ID: {}", intent.getId());
            return new PaymentResponse(intent.getClientSecret());
        } catch (StripeException e) {
            logger.error("StripeException while creating PaymentIntent: "+ e.getMessage());
            throw e;
        }
    }
}



