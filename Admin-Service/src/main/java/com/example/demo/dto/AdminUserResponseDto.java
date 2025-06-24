package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.enums.Role;

public class AdminUserResponseDto {

	private Long id;
    private String username;
    private Role role;
    private boolean featureToggle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
	public AdminUserResponseDto(Long id, String username, Role role, boolean featureToggle, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.featureToggle = featureToggle;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	public AdminUserResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
