package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminUserRequestDto;
import com.example.demo.dto.AdminUserResponseDto;
import com.example.demo.service.AdminUserService;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
	
	@Autowired
    private AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody AdminUserRequestDto dto) {
        try {
            AdminUserResponseDto response = adminUserService.createAdmin(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Admin creation failed", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        try {
            AdminUserResponseDto response = adminUserService.getAdminById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<AdminUserResponseDto> response = adminUserService.getAllAdmins();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody AdminUserRequestDto dto) {
        try {
            AdminUserResponseDto response = adminUserService.updateAdmin(id, dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Admin updation failed", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
    	String message = adminUserService.deleteAdmin(id);
        return ResponseEntity.ok(message);
    }

}
