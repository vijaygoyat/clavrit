package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentStarterController {
	
	Logger logger = LoggerFactory.getLogger(PaymentStarterController.class);

    
    @GetMapping("/checkout")
    public String showPaymentPage() {
        logger.info("Loading payment page");
        return "payment";
    }

}
