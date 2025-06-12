package com.example.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserWithRewardResponseDTO;
import com.example.demo.entity.Reward;
import com.example.demo.entity.RewardSetup;
import com.example.demo.entity.User;
import com.example.demo.repository.RewardRepository;
import com.example.demo.repository.RewardSetupRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;
    
    @Autowired
    private RewardSetupRepository rewardSetupRepository;

    private int getReferralRewardValue() {
        List<RewardSetup> list = rewardSetupRepository.findAll();
        return list.isEmpty() ? 0 : list.get(0).getReferralPoint();
    }

    

    public String registerUser(UserRequestDto request) {
        try {
        	int referralReward = getReferralRewardValue();
            String generatedReferralCode = UUID.randomUUID().toString().substring(0, 8);

            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setReferralCode(generatedReferralCode);
            newUser.setReferredUsers(new ArrayList<>());

            if (request.getReferralCode() != null && !request.getReferralCode().isEmpty()) {
                Optional<User> referrerOpt = userRepository.findByReferralCode(request.getReferralCode());
                if (referrerOpt.isPresent()) {
                    User referrer = referrerOpt.get();

                    newUser.setReferredBy(referrer.getId());
                    User savedUser = userRepository.save(newUser);

                    referrer.getReferredUsers().add(savedUser.getId());
                    userRepository.save(referrer);

                    Reward referrerReward = rewardRepository.findByUserId(referrer.getId())
                            .orElse(new Reward(referrer.getId(), 0));
                    referrerReward.setPoints(referrerReward.getPoints() + referralReward);
                    rewardRepository.save(referrerReward);

                    Reward newUserReward = new Reward(savedUser.getId(), 0);
                    rewardRepository.save(newUserReward);

                    logger.info("User {} registered using referral code {}.", savedUser.getUsername(), request.getReferralCode());
                    return "User registered successfully with referral.";
                } else {
                    logger.warn("Invalid referral code used: {}", request.getReferralCode());
                    return "Registration failed: Invalid referral code. Please try again.";
                }
            } else {
                User savedUser = userRepository.save(newUser);
                Reward newUserReward = new Reward(savedUser.getId(), 0);
                rewardRepository.save(newUserReward);

                logger.info("User {} registered without referral.", savedUser.getUsername());
                return "User registered successfully without referral.";
            }

        } catch (Exception e) {
            logger.error("Registration failed due to error: {}", e.getMessage());
            return "Registration failed: " + e.getMessage();
        }
    }

    public UserWithRewardResponseDTO getUserWithReward(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOpt.get();
        Reward reward = rewardRepository.findByUserId(userId).orElse(new Reward(userId, 0));

        Map<Long, String> referredByMap = new HashMap<>();
        if (user.getReferredBy() != null) {
            userRepository.findById(user.getReferredBy()).ifPresent(referrer ->
                    referredByMap.put(referrer.getId(), referrer.getUsername()));
        }

        Map<Long, String> referredUsersMap = user.getReferredUsers().stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(User::getId, User::getUsername));

        return new UserWithRewardResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getReferralCode(),
                referredByMap,
                referredUsersMap,
                reward.getPoints()
        );
    }
}
