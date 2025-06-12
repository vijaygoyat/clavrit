package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false, unique = true)
    private String referralCode;

    private Long referredBy;

    private List<Long> referredUsers = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public Long getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(Long referredBy) {
		this.referredBy = referredBy;
	}

	public List<Long> getReferredUsers() {
		return referredUsers;
	}

	public void setReferredUsers(List<Long> referredUsers) {
		this.referredUsers = referredUsers;
	}

	public User(String username, String referralCode, Long referredBy, List<Long> referredUsers) {
		super();
		this.username = username;
		this.referralCode = referralCode;
		this.referredBy = referredBy;
		this.referredUsers = referredUsers;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
}
