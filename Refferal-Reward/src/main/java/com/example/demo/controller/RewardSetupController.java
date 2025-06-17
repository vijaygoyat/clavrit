package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RewardSetup;
import com.example.demo.service.RewardSetupService;

@RestController
@RequestMapping("/reward")
public class RewardSetupController {

    @Autowired
    private RewardSetupService rewardSetupService;

    @PostMapping("/save")
    public ResponseEntity<String> saveOrUpdate(@RequestBody RewardSetup rewardSetup) {
        String result = rewardSetupService.saveOrUpdateReward(rewardSetup);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/current")
    public ResponseEntity<RewardSetup> getCurrentReward() {
        return ResponseEntity.ok(rewardSetupService.getCurrentRewardSetup());
    }
}
