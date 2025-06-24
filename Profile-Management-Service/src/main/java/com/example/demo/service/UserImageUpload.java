package com.example.demo.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UserProfileResponseDto;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;

@Service
public class UserImageUpload {
	
	@Value("${file.upload.user_image}")
	private String basePath;
	
	Logger logger = LoggerFactory.getLogger(UserImageUpload.class);

    @Autowired
    private UserProfileRepository repository;

	public UserProfileResponseDto uploadImageForUser(Long id, MultipartFile file) {
		logger.info("Starting image upload for user ID: {}", id);

        try {
            Optional<UserProfile> optionalProfile = repository.findById(id);
            if (optionalProfile.isEmpty()) {
                logger.warn("User not found with ID: {}", id);
                throw new RuntimeException("User profile not found with ID: " + id);
            }

            UserProfile profile = optionalProfile.get();

            if (file != null && !file.isEmpty()) {
                
                if (profile.getImage_url() != null) {
                    File oldFile = new File(profile.getImage_url());
                    if (oldFile.exists()) {
                        boolean deleted = oldFile.delete();
                        logger.info("Old image file deleted: {}", deleted);
                    }
                }

                File dir = new File(basePath);
                if (!dir.exists()) {
                    dir.mkdirs(); 
                }

                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String fullPath = basePath + uniqueFileName;

                File dest = new File(fullPath);
                file.transferTo(dest);
                logger.info("New image saved at: {}", fullPath);

                
                profile.setImage_url(fullPath);
            }

            profile.setLastUpdatedTime(LocalDateTime.now());
            UserProfile updatedProfile = repository.save(profile);

            
            UserProfileResponseDto dto = new UserProfileResponseDto();
            dto.setId(updatedProfile.getId());
            dto.setName(updatedProfile.getName());
            dto.setGender(updatedProfile.getGender());
            dto.setPhoneNumber(updatedProfile.getPhoneNumber());
            dto.setEmail(updatedProfile.getEmail());
            dto.setAddress(updatedProfile.getAddress());
            dto.setPreferences(updatedProfile.getPreferences());
            dto.setRegistrationTime(updatedProfile.getRegistrationTime());
            dto.setLastUpdatedTime(updatedProfile.getLastUpdatedTime());
            dto.setImage_url(updatedProfile.getImage_url());

            return dto;

        } catch (Exception e) {
            logger.error("Error while uploading image for user ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to upload image for user ID " + id + ": " + e.getMessage());
        }
		
	}
}
