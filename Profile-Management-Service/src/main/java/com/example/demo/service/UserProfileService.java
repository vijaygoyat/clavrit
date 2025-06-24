package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserProfileRequestDto;
import com.example.demo.dto.UserProfileResponseDto;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository repository;

    
    public UserProfileResponseDto createProfile(UserProfileRequestDto dto) {
        logger.info("Creating user profile for email: {}", dto.getEmail());

        try {
            if (repository.existsByEmail(dto.getEmail())) {
                String message = "A profile already exists with email: " + dto.getEmail();
                logger.warn(message);
                throw new RuntimeException(message);
            }

            UserProfile profile = toEntity(dto);
            profile.setRegistrationTime(LocalDateTime.now());
            profile.setLastUpdatedTime(LocalDateTime.now());

            UserProfile saved = repository.save(profile);
            logger.info("Profile successfully created with ID: {}", saved.getId());
            return toDTO(saved);

        } catch (Exception e) {
            logger.error("Error while creating user profile: {}", e.getMessage());
            throw new RuntimeException("Failed to create user profile: " + e.getMessage());
        }
    }



    public UserProfileResponseDto getProfileById(Long id) {
        logger.info("Fetching profile with ID: {}", id);

        try {
            Optional<UserProfile> optionalProfile = repository.findById(id);

            if (optionalProfile.isEmpty()) {
                String message = "User profile not found with ID: " + id;
                logger.warn(message);
                throw new RuntimeException(message);
            }

            UserProfile profile = optionalProfile.get();
            logger.info("Profile found for ID: {}", id);
            return toDTO(profile);

        } catch (RuntimeException e) {
            logger.error("Profile fetch failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching profile ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Internal error while fetching profile"+e.getMessage());
        }
    }


    public List<UserProfileResponseDto> getAllProfiles() {
        logger.info("Fetching all user profiles");

        try {
            List<UserProfile> profiles = repository.findAll();

            if (profiles.isEmpty()) {
                logger.warn("No user profiles found.");
                return new ArrayList<>(); 
            }

            List<UserProfileResponseDto> responseList = new ArrayList<>();
            for (UserProfile profile : profiles) {
                responseList.add(toDTO(profile));
            }

            logger.info("Total profiles fetched: {}", responseList.size());
            return responseList;

        } catch (Exception e) {
            logger.error("Error while fetching profiles: {}", e.getMessage());
            return new ArrayList<>(); 
        }
    }


    public UserProfileResponseDto updateProfile(Long id, UserProfileRequestDto dto) {
        logger.info("Attempting to update profile with ID: {}", id);

        try {
            Optional<UserProfile> optionalProfile = repository.findById(id);
            if (optionalProfile.isEmpty()) {
                String message = "Update failed: User profile not found with ID: " + id;
                logger.warn(message);
                throw new RuntimeException(message);
            }

            UserProfile existing = optionalProfile.get();
            existing.setName(dto.getName());
            existing.setGender(dto.getGender());
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setEmail(dto.getEmail());
            existing.setAddress(dto.getAddress());
            existing.setPreferences(dto.getPreferences());
            existing.setLastUpdatedTime(LocalDateTime.now());

            UserProfile updated = repository.save(existing);
            logger.info("Profile updated successfully for ID: {}", id);
            return toDTO(updated);

        }catch (RuntimeException e) {
            logger.error("Profile updation failed: {}", e.getMessage());
            throw e; 
        }catch (Exception e) {
            logger.error("Unexpected error while updating profile ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Internal error during profile update. Please try again.");
        }
    }


    public String deleteProfile(Long id) {
        logger.info("Attempting to delete profile with ID: {}", id);
        try {
            if (!repository.existsById(id)) {
                String errorMessage = "User profile not found with ID: " + id;
                logger.warn(errorMessage);
                return errorMessage;
            }

            repository.deleteById(id);
            String successMessage = "Profile deleted successfully with ID: " + id;
            logger.info(successMessage);
            return successMessage;

        } catch (Exception e) {
            logger.error("Unexpected error during deletion: {}", e.getMessage());
            return "Deletion failed: " + e.getMessage();

        }
    }

	
	public UserProfile toEntity(UserProfileRequestDto dto) {
	    UserProfile profile = new UserProfile();
	    profile.setName(dto.getName());
	    profile.setGender(dto.getGender());
	    profile.setPhoneNumber(dto.getPhoneNumber());
	    profile.setEmail(dto.getEmail());
	    profile.setAddress(dto.getAddress());
	    profile.setPreferences(dto.getPreferences());
	    return profile;
	}

	public UserProfileResponseDto toDTO(UserProfile profile) {
	    UserProfileResponseDto dto = new UserProfileResponseDto();
	    dto.setId(profile.getId());
	    dto.setName(profile.getName());
	    dto.setGender(profile.getGender());
	    dto.setPhoneNumber(profile.getPhoneNumber());
	    dto.setEmail(profile.getEmail());
	    dto.setAddress(profile.getAddress());
	    dto.setPreferences(profile.getPreferences());
	    dto.setRegistrationTime(profile.getRegistrationTime());
	    dto.setLastUpdatedTime(profile.getLastUpdatedTime());
	    dto.setImage_url(profile.getImage_url());
	    return dto;
	}


}
