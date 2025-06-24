package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
    private String username;
    
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean featureToggle;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

	public AdminUser(String username, Role role, boolean featureToggle, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.username = username;
		this.role = role;
		this.featureToggle = featureToggle;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AdminUser() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isFeatureToggle() {
		return featureToggle;
	}

	public void setFeatureToggle(boolean featureToggle) {
		this.featureToggle = featureToggle;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
    
    
    
}
