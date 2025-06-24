package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	Optional<UserProfile> findByEmail(String email);

    boolean existsByEmail(String email);
    
    List<UserProfile> findByRegistrationTimeAfter(LocalDateTime date);

    List<UserProfile> findByNameIgnoreCase(String name);
}
