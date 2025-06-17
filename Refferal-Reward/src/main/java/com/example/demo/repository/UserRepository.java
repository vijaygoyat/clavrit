package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByReferralCode(String referralCode);

    List<User> findByReferrer(User referrer);

    boolean existsByUsername(String username);

}
