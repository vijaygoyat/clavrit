package com.example.demo.dto;

import java.util.Map;

public class UserWithRewardResponseDTO {
	
	private Long id;
    private String username;
    private String referralCode;
    private Map<Long, String> referredBy;   
    private Map<Long, String> referredUsers;
    private int points;
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
	public UserWithRewardResponseDTO(Long id, String username, String referralCode, Map<Long, String> referredBy,
			Map<Long, String> referredUsers, int points) {
		super();
		this.id = id;
		this.username = username;
		this.referralCode = referralCode;
		this.referredBy = referredBy;
		this.referredUsers = referredUsers;
		this.points = points;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Map<Long, String> getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(Map<Long, String> referredBy) {
		this.referredBy = referredBy;
	}
	public Map<Long, String> getReferredUsers() {
		return referredUsers;
	}
	public void setReferredUsers(Map<Long, String> referredUsers) {
		this.referredUsers = referredUsers;
	}
    
    


}
