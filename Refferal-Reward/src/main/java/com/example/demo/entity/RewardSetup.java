package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RewardSetup {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int referralPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getReferralPoint() {
		return referralPoint;
	}

	public void setReferralPoint(int referralPoint) {
		this.referralPoint = referralPoint;
	}

	public RewardSetup(Long id, int referralPoint) {
		super();
		this.id = id;
		this.referralPoint = referralPoint;
	}

	public RewardSetup() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
