package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RewardSetup;
import com.example.demo.repository.RewardSetupRepository;

@Service
public class RewardSetupService {

    @Autowired
    private RewardSetupRepository rewardSetupRepository;

    public String saveOrUpdateReward(RewardSetup setup) {
        List<RewardSetup> existing = rewardSetupRepository.findAll();

        if (!existing.isEmpty()) {
            RewardSetup existingSetup = existing.get(0);
            existingSetup.setReferralPoint(setup.getReferralPoint());
            rewardSetupRepository.save(existingSetup);
            return "Reward setup updated successfully.";
        } else {
            rewardSetupRepository.save(setup);
            return "Reward setup created successfully.";
        }
    }

    public RewardSetup getCurrentRewardSetup() {
        List<RewardSetup> setups = rewardSetupRepository.findAll();
        return setups.isEmpty() ? new RewardSetup(null, 0) : setups.get(0);
    }

}
