package com.example.demo.controller;

import com.example.demo.dto.UserProfileRequestDto;
import com.example.demo.dto.UserProfileResponseDto;
import com.example.demo.service.UserImageUpload;
import com.example.demo.service.UserProfileService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;
    
    @Autowired UserImageUpload userImageUpload;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody UserProfileRequestDto dto) {
        try {
            UserProfileResponseDto createdProfile = userProfileService.createProfile(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", "Server error: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        try {
            UserProfileResponseDto profile = userProfileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProfiles() {
        try {
            List<UserProfileResponseDto> profiles = userProfileService.getAllProfiles();
            return ResponseEntity.ok(profiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id,
                                           @RequestBody UserProfileRequestDto dto) {
        try {
            UserProfileResponseDto updatedProfile = userProfileService.updateProfile(id, dto);
            return ResponseEntity.ok(updatedProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", "Server error while updating profile"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
    	
    	String result = userProfileService.deleteProfile(id);
        return ResponseEntity.ok(Collections.singletonMap("message", result));
    }
    
    @PostMapping("/image/{id}")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile file,@PathVariable Long id) {
        try {
        	if (file == null || file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(Collections.singletonMap("error", "Please upload a valid file."));
            }
        	UserProfileResponseDto responseDto = userImageUpload.uploadImageForUser(id, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.singletonMap("error", "Server error: " + e.getMessage()));
        }
    }
}
