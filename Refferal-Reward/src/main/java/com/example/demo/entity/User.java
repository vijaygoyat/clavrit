package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false, unique = true)
    private String referralCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referrer_id")
    private User referrer;

    @OneToMany(mappedBy = "referrer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> referredUsers = new ArrayList<>();
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Reward reward;

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
	

	public User getReferrer() {
		return referrer;
	}

	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}

	public List<User> getReferredUsers() {
		return referredUsers;
	}

	public void setReferredUsers(List<User> referredUsers) {
		this.referredUsers = referredUsers;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public User(Long id, String username, String referralCode, User referrer, List<User> referredUsers, Reward reward) {
		super();
		this.id = id;
		this.username = username;
		this.referralCode = referralCode;
		this.referrer = referrer;
		this.referredUsers = referredUsers;
		this.reward = reward;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
}
