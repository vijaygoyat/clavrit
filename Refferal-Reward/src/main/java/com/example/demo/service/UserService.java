package com.example.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserBasicDTO;
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

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RewardSetupRepository rewardSetupRepository;
    
    @Value("${app.referral.base-url}")
    private String referralBaseUrl;

    private int getReferralRewardValue() {
        return rewardSetupRepository.findAll().stream()
                .findFirst()
                .map(RewardSetup::getReferralPoint)
                .orElse(0);
    }

    public UserWithRewardResponseDTO registerUser(UserRequestDto request) {
        try {
            int referralReward = getReferralRewardValue();
            String generatedReferralCode = UUID.randomUUID().toString().substring(0, 8);

            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setReferralCode(generatedReferralCode);

            User referrer = null;

            if (request.getReferralCode() != null && !request.getReferralCode().isEmpty()) {
                Optional<User> referrerOpt = userRepository.findByReferralCode(request.getReferralCode());
                if (referrerOpt.isPresent()) {
                    referrer = referrerOpt.get();
                    newUser.setReferrer(referrer);
                } else {
                    logger.warn("Invalid referral code used: {}", request.getReferralCode());
                    throw new IllegalArgumentException("Invalid referral code. Please try again.");
                }
            }

            User savedUser = userRepository.save(newUser);

            Reward newUserReward = new Reward(savedUser, 0);
            rewardRepository.save(newUserReward);

            if (referrer != null) {
                Reward referrerReward = rewardRepository.findByUser(referrer)
                        .orElse(new Reward(referrer, 0));
                referrerReward.setPoints(referrerReward.getPoints() + referralReward);
                rewardRepository.save(referrerReward);

                logger.info("User {} registered with referral: {}", savedUser.getUsername(), referrer.getUsername());
            } else {
                logger.info("User {} registered without referral.", savedUser.getUsername());
            }

            UserBasicDTO referrerDTO = referrer != null
                    ? new UserBasicDTO(referrer.getId(), referrer.getUsername(), referrer.getReferralCode(),
                        rewardRepository.findByUser(referrer).map(Reward::getPoints).orElse(0))
                    : null;
            
            String referralLink = referralBaseUrl + savedUser.getReferralCode();

            return new UserWithRewardResponseDTO(
                    savedUser.getId(),
                    savedUser.getUsername(),
                    savedUser.getReferralCode(),
                    rewardRepository.findByUser(savedUser).map(Reward::getPoints).orElse(0),
                    referralLink,
                    referrerDTO,
                    new ArrayList<>()
            );

        } catch (Exception e) {
            logger.error("Registration failed due to error: {}", e.getMessage());
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }

    public UserWithRewardResponseDTO getUserWithReward(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Reward userReward = rewardRepository.findByUser(user)
                .orElse(new Reward(user, 0));

        
        UserBasicDTO referredByDTO = null;
        if (user.getReferrer() != null) {
            User referrer = user.getReferrer();
            int referrerPoints = rewardRepository.findByUser(referrer).map(Reward::getPoints).orElse(0);
            referredByDTO = new UserBasicDTO(referrer.getId(), referrer.getUsername(),
                    referrer.getReferralCode(), referrerPoints);
        }

        
        List<UserBasicDTO> referredUsersDTOs = user.getReferredUsers().stream().map(referredUser -> {
            int referredUserPoints = rewardRepository.findByUser(referredUser).map(Reward::getPoints).orElse(0);
            return new UserBasicDTO(referredUser.getId(), referredUser.getUsername(),
                    referredUser.getReferralCode(), referredUserPoints);
        }).collect(Collectors.toList());
        
        String referralLink = referralBaseUrl + user.getReferralCode(); 

        return new UserWithRewardResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getReferralCode(),
                userReward.getPoints(),
                referralLink,
                referredByDTO,
                referredUsersDTOs
        );
    }
}
