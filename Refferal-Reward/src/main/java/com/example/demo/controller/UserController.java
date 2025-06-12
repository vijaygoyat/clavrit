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
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto request) {
        logger.info("Registering new user with username: {}", request.getUsername());

        String result = userService.registerUser(request);

        if (result.toLowerCase().contains("success")) {
            logger.info("Registration result: {}", result);
            return ResponseEntity.ok(result);
        } else {
            logger.warn("Registration failed: {}", result);
            return ResponseEntity.badRequest().body(result);
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
