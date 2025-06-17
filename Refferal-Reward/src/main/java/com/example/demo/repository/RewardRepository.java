package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reward;
import com.example.demo.entity.User;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    Optional<Reward> findByUserId(Long userId);

    Optional<Reward> findByUser(User user);
}
