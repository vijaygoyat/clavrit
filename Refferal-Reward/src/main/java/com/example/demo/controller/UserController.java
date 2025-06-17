package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserWithRewardResponseDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody UserRequestDto request,
            @RequestParam(value = "ref", required = false) String referralCode) {

        try {
            if (referralCode != null && !referralCode.isEmpty()) {
                request.setReferralCode(referralCode);
                logger.info("Referral code set from URL: {}", referralCode);
            }

            logger.info("Registering new user with username: {}", request.getUsername());

            UserWithRewardResponseDTO result = userService.registerUser(request);

            logger.info("User registered successfully: {}", result.getUsername());
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException ex) {
            logger.warn("Registration failed due to invalid input: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected error during registration: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserWithReward(@PathVariable Long id) {
        logger.info("Fetching user details for ID: {}", id);
        try {
            UserWithRewardResponseDTO response = userService.getUserWithReward(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error fetching user {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
