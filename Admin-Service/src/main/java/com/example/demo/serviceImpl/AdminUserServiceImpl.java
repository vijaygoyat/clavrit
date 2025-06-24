package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminUserRequestDto;
import com.example.demo.dto.AdminUserResponseDto;
import com.example.demo.entity.AdminUser;
import com.example.demo.enums.Role;
import com.example.demo.repository.AdminUserRepository;
import com.example.demo.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired
    private AdminUserRepository repository;

    @Override
    public AdminUserResponseDto createAdmin(AdminUserRequestDto dto) {
        logger.info("Creating admin user with username: {}", dto.getUsername());

        try {
            if (repository.existsByUsername(dto.getUsername())) {
                String message = "Admin with username already exists: " + dto.getUsername();
                logger.warn(message);
                throw new RuntimeException(message);
            }

            AdminUser user = new AdminUser();
            user.setUsername(dto.getUsername());
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            user.setFeatureToggle(dto.isFeatureToggle());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            AdminUser saved = repository.save(user);
            logger.info("Admin user created successfully with ID: {}", saved.getId());
            return toDto(saved);
        } catch (Exception e) {
            logger.error("Error while creating admin user: {}", e.getMessage());
            throw new RuntimeException("Failed to create admin user: " + e.getMessage());
        }
    }

    @Override
    public AdminUserResponseDto getAdminById(Long id) {
        logger.info("Fetching admin user with ID: {}", id);

        try {
            Optional<AdminUser> optional = repository.findById(id);
            if (optional.isEmpty()) {
                String message = "Admin user not found with ID: " + id;
                logger.warn(message);
                throw new RuntimeException(message);
            }

            return toDto(optional.get());
        } catch (Exception e) {
            logger.error("Error while fetching admin user: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch admin user: " + e.getMessage());
        }
    }

    @Override
    public List<AdminUserResponseDto> getAllAdmins() {
        logger.info("Fetching all admin users");

        try {
            List<AdminUser> users = repository.findAll();
            List<AdminUserResponseDto> responseList = new ArrayList<>();

            for (AdminUser user : users) {
                responseList.add(toDto(user));
            }

            logger.info("Total admins fetched: {}", responseList.size());
            return responseList;
        } catch (Exception e) {
            logger.error("Error while fetching admin list: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch admin users: " + e.getMessage());
        }
    }

    @Override
    public AdminUserResponseDto updateAdmin(Long id, AdminUserRequestDto dto) {
        logger.info("Updating admin user with ID: {}", id);

        try {
            Optional<AdminUser> optional = repository.findById(id);
            if (optional.isEmpty()) {
                String message = "Admin user not found with ID: " + id;
                logger.warn(message);
                throw new RuntimeException(message);
            }

            AdminUser user = optional.get();
            user.setUsername(dto.getUsername());
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            user.setFeatureToggle(dto.isFeatureToggle());
            user.setUpdatedAt(LocalDateTime.now());

            AdminUser updated = repository.save(user);
            logger.info("Admin user updated successfully with ID: {}", updated.getId());
            return toDto(updated);
        } catch (Exception e) {
            logger.error("Error while updating admin user: {}", e.getMessage());
            throw new RuntimeException("Failed to update admin user: " + e.getMessage());
        }
    }

    @Override
    public String deleteAdmin(Long id) {
        logger.info("Attempting to delete admin user with ID: {}", id);

        try {
            if (!repository.existsById(id)) {
                String message = "Admin user not found with ID: " + id;
                logger.warn(message);
                return message;
            }

            repository.deleteById(id);
            logger.info("Admin user deleted with ID: {}", id);
            return "Admin user deleted successfully with ID: " + id;
        } catch (Exception e) {
            logger.error("Error while deleting admin user: {}", e.getMessage());
            return "Failed to delete admin user: " + e.getMessage();
        }
    }
    
    private AdminUserResponseDto toDto(AdminUser user) {
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setFeatureToggle(user.isFeatureToggle());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    
}
