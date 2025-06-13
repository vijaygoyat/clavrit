package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.service.MailService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        String result = mailService.sendMail(request.getEmail(), request.getSubject(), request.getMessage());

        if (result.toLowerCase().contains("successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
