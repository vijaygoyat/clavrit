package com.example.demo.dto;

public class AdminUserRequestDto {

	private String username;
    private String role;
    private boolean featureToggle;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isFeatureToggle() {
		return featureToggle;
	}
	public void setFeatureToggle(boolean featureToggle) {
		this.featureToggle = featureToggle;
	}
	public AdminUserRequestDto(String username, String role, boolean featureToggle) {
		super();
		this.username = username;
		this.role = role;
		this.featureToggle = featureToggle;
	}
	public AdminUserRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
